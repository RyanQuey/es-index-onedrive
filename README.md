Apache Tika integration built in scala for indexing OneDrive files into ElasticSearch. 

# Why build this tool?
Because Windows search functionality [just doesn't cut it](https://answers.microsoft.com/en-us/windows/forum/windows_10-win_cortana-winpc/why-is-windows-10-search-so-bad-at-its-job/6041e4bf-c30c-4575-a52c-b6916a1e7326). 

## Use Cases
- Primarily, for indexing all my notes that I keep in OneDrive
- Also can use to index files stored in external hard drives

# How does it work?
Well...right now it doesn't. It's a work in progress. But the idea is that Apache Tika can parse my .doc, .pdf, .docx, .pptx, .ppt, and [even OneNote files](https://stackoverflow.com/a/41274560/6952495) so that they are machine readable. Then I will use an elasticsearch java client to index all these files. 

For a better user experience, I can then make an easy GUI (probably a simple web server on localhost, using play framework? Maybe Electron? React Native?). Ideally it would be able to run on Windows 10 (unfortunately a requirement, but this is the primary use case and rationale for building in the first place) and indexing often enough that it finds changes made in last couple days. 

Honestly, Windows 10 finder search works well enough (if you use `content:"your phrase"`) to get by for a quick filename search or minor content search, but bugs out too often, and doesn't get consistent results. This tool is for when you want to find all docs that mention a topic, and you want it to work reliably and not miss anything. So indexing even once a day is probably sufficient. 


# Setup
## Install SBT
(see sbt instructions)
## Start ElasticSearch
```
docker-compose up -d
```

## Compile and run
```
sbt compile
```
And then: 
```
sbt run
```

# Run test script to see if tika is working
Just a quick script to get up and running, as a POC
```
./tika-test.sh
```
Should get result of something like this:

![screenshot](https://github.com/RyanQuey/es-index-onedrive/raw/master/screenshots/test-tika.stdout.png)


# Feature Ideas
- Create different indexes for complex searches
- Create sample queries for commonly used use cases that would be difficult to do in Windows search or OneDrive search by default using their GUI
- Can programmatically access and scrape my OneDrive files and use perhaps in other programs, such as [my intertextuality graph project](https://github.com/RyanQuey/intertextuality-graph).
- Add other integrations, for adding more features. For example, can use Rsync to copy OneDrive files into linux, since linux doesn't have a client for it. Then, can index within a linux box if needed. 
- Improved GUI over the Windows 10 finder search, with expandable results for each file, so I can quickly see if the text that returned a hit is relevant to what I'm actually looking for.

# Credits 
- Got some ideas from this blog and the related project: 
    * https://dontpaniclabs.com/blog/post/2020/03/03/searching-word-and-pdf-documents-with-elasticsearch-and-apache-tika/
    * https://github.com/chadmichel/DocumentSearch

- TikaTest.scala based almost entirely on this: https://gist.github.com/dportabella/f6bee43ab543798813e0
- Based on g8 project using 
    ```
    sbt new scala/scala-seed.g8
    ```

