Apache Tika integration built in scala for indexing OneDrive files into ElasticSearch. 

# Why Build this?
Because Windows search functionality [just doesn't cut it](https://answers.microsoft.com/en-us/windows/forum/windows_10-win_cortana-winpc/why-is-windows-10-search-so-bad-at-its-job/6041e4bf-c30c-4575-a52c-b6916a1e7326). 

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


# Ideas
- Create different indexes for complex searches
- Create sample queries for commonly used use cases that would be difficult to do in Windows search or OneDrive search by default using their GUI
- Can programmatically access and scrape my OneDrive files and use perhaps in other programs, such as [my intertextuality graph project](https://github.com/RyanQuey/intertextuality-graph).
- Add other integrations, for adding more features. For example, can use Rsync to copy OneDrive files into linux, since linux doesn't have a client for it. Then, can index within a linux box if needed. 

# Credits 
- Got some ideas from this blog and the related project: 
    * https://dontpaniclabs.com/blog/post/2020/03/03/searching-word-and-pdf-documents-with-elasticsearch-and-apache-tika/
    * https://github.com/chadmichel/DocumentSearch

- TikaTest.scala based almost entirely on this: https://gist.github.com/dportabella/f6bee43ab543798813e0
- Based on g8 project using 
    ```
    sbt new scala/scala-seed.g8
    ```

