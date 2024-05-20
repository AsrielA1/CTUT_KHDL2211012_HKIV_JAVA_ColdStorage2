package coldstorage2.general;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {
    protected String url = "jdbc:postgresql://localhost:5432/postgres";
    protected String dbUsername = "admin";
    protected String dbPassword = "admin";
    
    protected Connection connection;
    protected PreparedStatement pstmt;
    protected Statement stmt;
    protected ResultSet rs;
    protected String query;
}
