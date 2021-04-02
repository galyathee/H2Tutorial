# H2Tutorial
Basic operations on [H2](http://h2database.com/html/main.html) database in Java

# Technologies
- Java
- H2 1v.4.200

# Notes
That demo uses the following Maven configuration:
```
<dependency>
  <groupId>com.h2database</groupId>
  <artifactId>h2</artifactId>
  <version>1.4.200</version>
  <scope>test</scope>
</dependency>
<dependency>
  <groupId>com.h2database</groupId>
  <artifactId>h2</artifactId>
  <version>1.4.200</version>
  <scope>compile</scope>
</dependency>
```

The demo class `org.galyathee.h2.H2Demo` does the following
- Load H2 JDBC driver
- Create a new DB
- Create / Drop a table
- CRUD operations on records
- Close resources

A very minimal benchmark is made on inserting records in one table. Here is an extract of the print:
```
---- Insert 1000 records
Time to insert 1000 records (ms): 159
---- Insert 10 000 records
Time to insert 10 000 records (ms): 405
---- Insert 1000 000 records
Time to insert 1000 000 records (ms): 4735
---- Delete 1000 000 records
Time to delete 1000 000 records (ms): 8636
```
