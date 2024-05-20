package coldstorage2.controllers;

import coldstorage2.general.Database;
import coldstorage2.models.MOutput;
import coldstorage2.models.details.MOutputDetail;
import java.sql.DriverManager;

public class COutput extends Database{
    public COutput(){}
        
    public boolean insert(MOutput _output){
        String dateStr, timeStr;
        
        try {
            dateStr = super.DateToDateStr(_output.getOutputDateTime());
            timeStr = super.DateToTimeStr(_output.getOutputDateTime());
            
            Class.forName("org.postgresql.Driver");            
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            query = "INSERT INTO lichsu_xuat(ngay_xuat, thoigian_xuat, ghi_chu) VALUES(?, ?, ?);";
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, dateStr);
            pstmt.setString(2, timeStr);
            pstmt.setString(3, _output.getOutputNote());
            pstmt.executeUpdate();
            
            return true;            
        }
        catch (Exception e){
            System.out.println("Error in COutput.insert(MOutput)");
        }
        
        return false;
    }
    
    public boolean delete(MOutput _output){
        try {
            Class.forName("org.postgresql.Driver");            
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            query = "DELETE FROM lichsu_xuat WHERE ma_xuatkho = ?;";
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, _output.getOutputNote());
            pstmt.executeUpdate();            
            
            return true;
        }
        catch (Exception e){
            System.out.println("Error in COutput.delete(MOutput)");
        }
        
        return false;
    }
    
    public boolean update(MOutput _output){
        try {
            Class.forName("org.postgresql.Driver");            
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            query = """
                    UPDATE lichsu_xuat
                    SET ghi_chu = ?
                    WHERE ma_xuatkho = ?;
                    """;
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, _output.getOutputNote());
            pstmt.setInt(2, _output.getOutputId());
            pstmt.executeUpdate();            
            
            return true;
        }
        catch (Exception e){
            System.out.println("Error in COutput.delete(MOutput)");
        }
        
        return false;    
    }
    
    public boolean insert(MOutputDetail _outputDetail){
        try {
            Class.forName("org.postgresql.Driver");            
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            query = """
                    INSERT INTO lichsu_xuat(ma_kho, khoi_luong, ghi_chu) VALUES (?, ?, ?);
                    """;
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, _outputDetail.getOutputId());
            pstmt.setFloat(2, _outputDetail.getWeight());
            pstmt.setString(3, _outputDetail.getOutputNote());
            pstmt.executeUpdate();
            
            return true;
        }
        catch (Exception e){
            System.out.println("Error in COutput.insert(MOutputDetail)");
        }
        
        return false;
    }
    
    public boolean delete(MOutputDetail _outputDetail){
        try{
            Class.forName("org.postgresql.Driver");            
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            query = """
                    DELETE FROM chitiet_xuat
                    WHERE ma_xuatkho = ? AND so_thutu = ?;
                    """;
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, _outputDetail.getOutputId());
            pstmt.setInt(2, _outputDetail.getOutputNum());
            pstmt.executeUpdate();
            
            return true;
        }
        catch (Exception e){
            System.out.println("Error in COutput.delete(MOutputDetail)");
        }
        
        return false;
    }
    
    public boolean update(MOutputDetail _outputDetail){
        try {
            Class.forName("org.postgresql.Driver");            
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            query = """
                    UPDATE chitiet_xuat
                    SET ghi_chu = ?
                    WHERE ma_xuatkho = ? AND so_thutu = ?;
                    """;
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, _outputDetail.getOutputNote());
            pstmt.setInt(2, _outputDetail.getOutputId());
            pstmt.setInt(3, _outputDetail.getOutputNum());
            pstmt.executeUpdate();
            
            return true;
        }
        catch (Exception e){
            System.out.println("Error in COutput.update(MOutputDetail)");
        }
        
        return false;
    }
}
