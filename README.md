
# Run test script to see if tika is working
```
./tika-test.sh
```

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

