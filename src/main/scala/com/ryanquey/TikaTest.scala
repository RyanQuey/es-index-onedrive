// https://gist.github.com/dportabella/f6bee43ab543798813e0

import java.io.File
import java.nio.charset.Charset
import org.apache.tika.detect._
import org.apache.tika.metadata._
import org.apache.tika.mime._
import org.apache.tika.io._
import org.apache.tika.parser.txt._
// from scala-arm...only using for this file probably
import resource._

object TikaTest extends App {
  def recursiveListFiles(f: File): List[File] = {
      val these = f.listFiles.toList
      these.filter(!_.isDirectory) ++ these.filter(_.isDirectory).flatMap(recursiveListFiles)
  }

  def isTextFile(file: File) = managed(TikaInputStream.get(file)).map(input => {
      val mediaType = new TextDetector().detect(input, null)
      mediaType.compareTo(MediaType.TEXT_PLAIN) == 0
  }).opt.get

  def getEncoding(file: File): Charset = managed(TikaInputStream.get(file)).map(input => {
      new UniversalEncodingDetector().detect(input, new Metadata())
  }).opt.get

  def read(file: File) = {
      io.Source.fromFile(file, getEncoding(file).name)
  }

  def processFileLines(file: File, matches: (String) => Boolean) {
      val lines = read(file).getLines().zipWithIndex.filter {case (text, _) => matches(text)}
      if (lines.nonEmpty) {
        println(Console.YELLOW + file + ":" + Console.GREEN)
        lines.foreach{ case (text, index) =>  println(index + ": " + text)}
        println()
      }
  }

  val dir = "./test-files/"
  def matches(line: String) = line.matches("(?i).*(?!superstition)(?!supernatural)super.*")
  val files = recursiveListFiles(new File(dir)).filter(isTextFile)
  files.foreach(file => processFileLines(file, matches))

}

/*
 sbt:
 libraryDependencies += "org.apache.tika" % "tika-core" % "1.5"
 libraryDependencies += "org.apache.tika" % "tika-parsers" % "1.5"
 libraryDependencies += "com.jsuereth" %% "scala-arm" % "1.3"
 download the list of free books from gutenberg.org

 $ cd ~/Downloads
 $ curl http://www.gutenberg.org/cache/epub/feeds/rdf-files.tar.bz2 | tar -jxf -
 $ scala ExampleScalaAck.scala
 Users/david/Downloads/cache/epub/3328/pg3328.rdf:
89:     <dcterms:title>Man and Superman: A Comedy and a Philosophy</dcterms:title>
/Users/david/Downloads/cache/epub/36437/pg36437.rdf:
134:         <pgterms:alias>Super, Ovando Byron</pgterms:alias>
136:         <pgterms:name>Super, O. B. (Ovando Byron)</pgterms:name>
/Users/david/Downloads/cache/epub/41481/pg41481.rdf:
71:     <dcterms:title>Astounding Stories of Super-Science January 1930</dcterms:title>

*/
