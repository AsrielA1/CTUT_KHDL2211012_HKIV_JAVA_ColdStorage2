package coldstorage2.general;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.Date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Database {
    protected String url = "jdbc:postgresql://localhost:5432/postgres";
    protected String dbUsername = "admin";
    protected String dbPassword = "admin";
    
    protected Connection connection;
    protected PreparedStatement pstmt;
    protected Statement stmt;
    protected ResultSet rs;
    protected String query;
    
    protected String DateToDateStr(Date _date){
        if (_date == null)
            return "";
        
        String _datePattern = "dd/MM/yyyy";            
        DateFormat _dateFormat = new SimpleDateFormat(_datePattern);
        String _dateString = _dateFormat.format(_date);
            
        return _dateString;
    }
    
    protected String DateToTimeStr(Date _date){
        if (_date == null)
            return "";
        
        String timePattern = "HH:mm:ss";            
        DateFormat timeFormat = new SimpleDateFormat(timePattern);
        String timeString = timeFormat.format(_date);
            
        return timeString;
    }
}
