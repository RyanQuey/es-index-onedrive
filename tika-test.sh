cd ./test-files
if [ -d ./cache/ ]; then
  echo "test files already downloaded and ready!"
else
  curl http://www.gutenberg.org/cache/epub/feeds/rdf-files.tar.bz2 | tar -jxf -
fi
cd ..
sbt run
