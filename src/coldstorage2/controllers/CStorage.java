package coldstorage2.controllers;

import coldstorage2.general.Database;
import coldstorage2.models.MStorage;
import coldstorage2.models.details.MStorageDetail;

import java.util.ArrayList;

import java.sql.DriverManager;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class CStorage extends Database{
    private final MStorage storageFn = new MStorage();
    
    public CStorage(){}
    
    public boolean insert(MStorage _storage){
        try{            
            Class.forName("org.postgresql.Driver");            
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            query = "INSERT INTO danhmuc_kho(suc_chua, ghi_chu) VALUES (?, ?);";
            pstmt = connection.prepareStatement(query);
            pstmt.setFloat(1, _storage.getMaxWeight());
            pstmt.setString(2, _storage.getStorageNote());
            pstmt.executeUpdate();
            
            return true;
        }
        catch(Exception e){
            System.out.println("Error in CStorage.insert\n" + e);
        }
        
        return false;
    }
    
    public boolean delete(MStorage _storage){
        try {            
            Class.forName("org.postgresql.Driver");            
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            query = "DELETE FROM danhmuc_kho WHERE ma_kho = ?;";
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, _storage.getStorageId());
            pstmt.executeUpdate();
            
            return true;
        }
        catch(Exception e){
            System.out.println("Error in CStorage.delete\n" + e);
        }
        
        return false;
    }
    
    public boolean update(MStorage _storage){
        try{            
            Class.forName("org.postgresql.Driver");            
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
        
            query = """
                    UPDATE danhmuc_kho
                    SET suc_chua = ?, ghi_chu = ?
                    WHERE ma_kho = ?;
                    """;
            pstmt = connection.prepareStatement(query);
            pstmt.setFloat(1, _storage.getMaxWeight());
            pstmt.setString(2, _storage.getStorageNote());
            pstmt.setInt(3, _storage.getStorageId());
            pstmt.executeUpdate();
            
            return true;
        }
        catch (Exception e){
            System.out.println("Error in CStorage.update\n" + e);
        }
        
        return false;
    }
    
    public void show(JTable _tblStorage){
        DefaultTableModel dtModel = (DefaultTableModel)_tblStorage.getModel();
        dtModel.setRowCount(0);
        
        try {
            for (MStorage _storage: storageFn.getAll()){
                dtModel.addRow(_storage.toObjectArr());
            }
        }
        catch (Exception e){
            System.out.println("Error in CStorage.show");
        }
    }
    
    public void showDetail(JTextField _tfStorageId, JTable _tblStorageDetail){
        DefaultTableModel dtModel = (DefaultTableModel)_tblStorageDetail.getModel();
        dtModel.setRowCount(0);
        
        int _storageId = Integer.parseInt(_tfStorageId.getText());
        
        try{
            for (MStorageDetail _storageDetail: storageFn.getDetail(_storageId)){
                dtModel.addRow(_storageDetail.toObjArr());
            }
        }
        catch (Exception e){
            System.out.println("Error in CStorage.showDetail");
        }
    }
    
    public int newId(){
        try{                        
            Class.forName("org.postgresql.Driver");            
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
        
            query = "SELECT MAX(ma_kho) + 1 FROM danhmuc_kho;";
            pstmt = connection.prepareStatement(query);
            
            rs = pstmt.executeQuery();
            while(rs.next()){
                return rs.getInt(1);
            }
        }
        catch (Exception e){
            System.out.println("Error in CStorage.getMax");
        }
        
        return 0;
    }
}
