package org.galyathee.h2;

import org.h2.jdbc.JdbcSQLSyntaxErrorException;
import java.sql.*;

/**
 * That demo class shows:
 * - how to create a H2 DB via JDBC
 * - how to create / drop a table
 * - how to insert records
 * - how to read, update, delete records
 *
 * Note: the DB does not need to be 'started'. The activation of the JDBC interaction brings the DB live.
 */
public class H2Demo {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";
    // use "jdbc:h2:~/handsOnLearningH2DB" to create DB in the basic folder of your computer
    static final String DB_URL = "jdbc:h2:./db/handsOnLearningH2DB";

    //  Database credentials
    static final String USER = "sa";
    static final String PASS = "";

    public static void main(String[] args) {
        System.out.println("---- Work on H2 via JDBC");
        Connection conn = null;
        Statement stmt = null;
        try {
            // 1: Register JDBC driver
            System.out.println("---- Load JDBC driver");
            Class.forName(JDBC_DRIVER);

            // 2: Open a connection
            System.out.println("---- Connect to DB / Create DB if not exist");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 3: Drop table if exists
            System.out.println("---- Drop working table if exists");
            stmt = conn.createStatement();
            String sql =  "DROP TABLE IF EXISTS PROGL";
            stmt.executeUpdate(sql);

            // 4: Create table
            System.out.println("---- Creating a table");
            sql =  "CREATE TABLE PROGL " +
                    "(id INTEGER not NULL, " +
                    " conceptor VARCHAR(300), " +
                    " language VARCHAR(50), " +
                    " creationDate DATE," +
                    " PRIMARY KEY ( id ))";
            stmt.executeUpdate(sql);

            // 5: Insert records
            insertProglRecords(stmt);

            // 6: Read / print inserted records
            System.out.println("---- Read records");
            printProglRecords(stmt);

            // 7: Update records
            System.out.println("---- Update records");
            sql = "UPDATE PROGL " + "SET conceptor = 'Adin D. Falkoff' WHERE id in (1, 100)";
            stmt.executeUpdate(sql);
            printProglRecords(stmt);

            // 8: delete records
            System.out.println("---- Delete 'Delphi' record");
            sql = "DELETE FROM PROGL WHERE id = 16";
            stmt.executeUpdate(sql);
            printProglRecords(stmt);

            // #: benchmark insertion time
            benchmarkH2(stmt);

            // ∞: Close resources
            stmt.close();
            conn.close();
        } catch(SQLException se) {
            se.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            // close resources whatever happens
            try{
                if (stmt != null) stmt.close();
            } catch(SQLException se2) {
                // cannot react to this !
            }
            try {
                if (conn != null) conn.close();
            } catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Enjoy learning!");
    }

    /**
     * Insert records in DB
     * @param stmt
     * @throws SQLException
     */
    private static void insertProglRecords(Statement stmt) throws SQLException {
        System.out.println("---- Insert records");
        String sql = "INSERT INTO PROGL " + "VALUES (1, 'Kenneth E. Iverson', 'APL', '1966-11-27')";
        stmt.executeUpdate(sql);
        sql = "INSERT INTO PROGL " + "VALUES (2, 'Thomas E. Kurz', 'BASIC', '1964-05-01')";
        stmt.executeUpdate(sql);
        sql = "INSERT INTO PROGL " + "VALUES (3, 'Charles H. Moore', 'FORTH', '1970-01-01')";
        stmt.executeUpdate(sql);
        sql = "INSERT INTO PROGL " + "VALUES (4, 'Robin Milner', 'ML', '1973-01-01')";
        stmt.executeUpdate(sql);
        sql = "INSERT INTO PROGL " + "VALUES (5, 'Donald D. Chamberlin', 'SQL', '1974-01-01')";
        stmt.executeUpdate(sql);
        sql = "INSERT INTO PROGL " + "VALUES (6, 'Alfred Aho, Peter Weinberg, Brian Kernighan', 'AWK', '1977-01-01')";
        stmt.executeUpdate(sql);
        sql = "INSERT INTO PROGL " + "VALUES (7, 'John Warnock, Chuck Geschke, Doug Brotz, Ed Taft, Bill Paxton', 'PostScript', '1982-01-01')";
        stmt.executeUpdate(sql);
        sql = "INSERT INTO PROGL " + "VALUES (8, 'Bjarne Stroustrup', 'C++', '1985-01-01')";
        stmt.executeUpdate(sql);
        sql = "INSERT INTO PROGL " + "VALUES (9, 'Bertrand Meyer', 'Eiffel', '1986-01-01')";
        stmt.executeUpdate(sql);
        sql = "INSERT INTO PROGL " + "VALUES (10, 'Tom Love and Brad Cox', 'Objective-C', '1984-01-01')";
        stmt.executeUpdate(sql);
        sql = "INSERT INTO PROGL " + "VALUES (11, 'Larry Wall', 'Perl', '1987-12-18')";
        stmt.executeUpdate(sql);
        sql = "INSERT INTO PROGL " + "VALUES (12, 'Lennart Augustsson, Dave Barton, Brian Boutel, Warren Burton, Joseph Fasel, Kevin Hammond, Ralf Hinze, Paul Hudak, John Hughes, Thomas Johnsson, Mark Jones, Simon Peyton Jones, John Launchbury, Erik Meijer, John Peterson, Alastair Reid, Colin Runciman, Philip Wadler', 'Haskell', '1990-01-01')";
        stmt.executeUpdate(sql);
        sql = "INSERT INTO PROGL " + "VALUES (13, 'Guido van Rossum', 'Python', '1991-02-01')";
        stmt.executeUpdate(sql);
        sql = "INSERT INTO PROGL " + "VALUES (14, 'Roberto Ierusalimschy, Waldemar Celes, Luiz Henrique de Figueiredo', 'Lua', '1993-01-01')";
        stmt.executeUpdate(sql);
        sql = "INSERT INTO PROGL " + "VALUES (15, 'James Gosling', 'Java', '1995-05-23')";
        stmt.executeUpdate(sql);
        sql = "INSERT INTO PROGL " + "VALUES (16, 'Larry Tesler (Apple), Niklaus Wirth (for Apple), Anders Hejlsberg (Borland)', 'Delphi', '1986-01-01')";
        stmt.executeUpdate(sql);
    }

    /**
     * Very poor design for print example means only!
     * @param stmt
     * @throws SQLException
     */
    private static void printProglRecords(Statement stmt) throws SQLException {
        String sql = "SELECT id, conceptor, language, creationDate FROM PROGL";
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()) {
            // Retrieve by column name
            int id  = rs.getInt("id");
            String conceptor = rs.getString("conceptor");
            String language = rs.getString("language");
            String creationDate = rs.getString("creationDate");

            // Display values
            System.out.print("ID: " + id);
            System.out.print(", Conceptor: " + conceptor);
            System.out.print(", Language: " + language);
            System.out.println(", Creation date: " + creationDate);
        }
        rs.close();
    }

    /**
     * Benchmark insertion time
     * @param stmt
     * @throws SQLException
     */
    private static void benchmarkH2(Statement stmt) throws SQLException {
        String sql = "";

        // empty test table
        sql = "DELETE FROM PROGL";
        stmt.executeUpdate(sql);

        System.out.println("---- Insert 1000 records");
        long ctime = System.currentTimeMillis();
        for(int i=0; i < 1000; i++) {
            sql = "INSERT INTO PROGL " + "VALUES (" + i + ", 'Joe Armstrong, Robert Virding, Mike Williams', 'Erlang', '1986-01-01')";
            stmt.executeUpdate(sql);
        }
        long etime = System.currentTimeMillis();
        System.out.println("Time to insert 1000 records (ms): " + Long.valueOf(etime - ctime));

        // empty test table
        sql = "DELETE FROM PROGL";
        stmt.executeUpdate(sql);

        System.out.println("---- Insert 10 000 records");
        ctime = System.currentTimeMillis();
        for(int i=0; i < 10000; i++) {
            sql = "INSERT INTO PROGL " + "VALUES (" + i + ", 'Joe Armstrong, Robert Virding, Mike Williams', 'Erlang', '1986-01-01')";
            stmt.executeUpdate(sql);
        }
        etime = System.currentTimeMillis();
        System.out.println("Time to insert 10 000 records (ms): " + Long.valueOf(etime - ctime));

        // empty test table
        sql = "DELETE FROM PROGL";
        stmt.executeUpdate(sql);

        System.out.println("---- Insert 1000 000 records");
        ctime = System.currentTimeMillis();
        for(int i=0; i < 1000000; i++) {
            sql = "INSERT INTO PROGL " + "VALUES (" + i + ", 'Joe Armstrong, Robert Virding, Mike Williams', 'Erlang', '1986-01-01')";
            stmt.executeUpdate(sql);
        }
        etime = System.currentTimeMillis();
        System.out.println("Time to insert 1000 000 records (ms): " + Long.valueOf(etime - ctime));

        System.out.println("---- Delete 1000 000 records");
        ctime = System.currentTimeMillis();
        sql = "DELETE FROM PROGL";
        stmt.executeUpdate(sql);
        etime = System.currentTimeMillis();
        System.out.println("Time to delete 1000 000 records (ms): " + Long.valueOf(etime - ctime));
    }
}
