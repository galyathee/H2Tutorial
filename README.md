# H2Tutorial
Basic operations on [H2](http://h2database.com/html/main.html) Database in Java

H2 DB is a quite simple DB to incorporate into projects. It is written in Java, and it can be started from Java.

The current project shows the class `org.galyathee.h2.H2Demo` doing the following
- Loading H2 JDBC driver
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
