package coldstorage2.controllers;

import coldstorage2.general.Database;
import coldstorage2.models.MProvider;
import java.sql.DriverManager;

public class CProvider extends Database{
    public CProvider(){}
    
    public boolean insert(MProvider _provider){
        try {
            Class.forName("org.postgresql.Driver");            
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            query = "INSERT INTO nha_cungung(ten_dv, diachi_email, so_dienthoai, ghi_chu) VALUES (?, ?, ?, ?)";
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, _provider.getProviderName());
            pstmt.setString(2, _provider.getProviderEmail());
            pstmt.setString(3, _provider.getProviderNumber());
            pstmt.setString(4, _provider.getProviderNote());
            pstmt.executeUpdate();
            
            return true;
        }
        catch (Exception e){
            System.out.println("Error in CProvider.insert");
        }
        
        return false;
    }
    
    public boolean delete(MProvider _provider){
        try {
            Class.forName("org.postgresql.Driver");            
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            query = "DELETE FROM nha_cungung WHERE ma_nhacungung = ?;";
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, _provider.getProviderId());
            pstmt.executeUpdate();
            
            return true;            
        }
        catch(Exception e){
            System.out.println("Error in CProvider.update");
        }
        
        return true;
    }
    
    public boolean update(MProvider _provider){
        try{            
            Class.forName("org.postgresql.Driver");            
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            query = """
                    UPDATE nha_cungung
                    SET ten_dv = ?,
                        diachi_email = ?
                        so_dienthoai = ?
                        ghi_chu = ?
                    WHERE ma_nhacungung = ?;
                    """;
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, _provider.getProviderName());
            pstmt.setString(2, _provider.getProviderEmail());
            pstmt.setString(3, _provider.getProviderNumber());
            pstmt.setString(4, _provider.getProviderNote());
            pstmt.setInt(5, _provider.getProviderId());
            pstmt.executeUpdate();
            
            return true;
        }
        catch (Exception e){
            System.out.println("Error in CProvider.update");
        }
        
        return false;
    }
}
