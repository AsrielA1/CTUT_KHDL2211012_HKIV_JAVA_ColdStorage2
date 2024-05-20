package coldstorage2.controllers;

import coldstorage2.general.Database;
import coldstorage2.models.MInput;
import coldstorage2.models.details.MInputDetail;

import java.sql.DriverManager;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

interface ICInput{
    
}

public class CInput extends Database{
    private final MInput inputFn = new MInput();
    private final MInputDetail inputDetailFn = new MInputDetail();
    
    public CInput(){}
    
    public boolean insert(MInput _input){
        String dateStr, timeStr;
        
        try {
            dateStr = super.DateToDateStr(_input.getInputDateTime());
            timeStr = super.DateToTimeStr(_input.getInputDateTime());
            
            Class.forName("org.postgresql.Driver");            
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            query = "INSERT INTO lichsu_nhap(ngay_nhap, thoigian_nhap, ghi_chu) VALUES(?, ?, ?);";
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, dateStr);
            pstmt.setString(2, timeStr);
            pstmt.setString(3, _input.getInputNote());
            pstmt.executeUpdate();
            
            return true;            
        }
        catch (Exception e){
            System.out.println("Error in CInput.insert(MInput)");
        }
        
        return false;
    }
    
    public boolean delete(MInput _input){
        try {
            Class.forName("org.postgresql.Driver");            
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            query = "DELETE FROM lichsu_nhap WHERE ma_nhapkho = ?;";
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, _input.getInputNote());
            pstmt.executeUpdate();            
            
            return true;
        }
        catch (Exception e){
            System.out.println("Error in CInput.delete(MInput)");
        }
        
        return false;
    }
    
    public boolean update(MInput _input){
        try {
            Class.forName("org.postgresql.Driver");            
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            query = """
                    UPDATE lichsu_nhap
                    SET ghi_chu = ?
                    WHERE ma_nhapkho = ?;
                    """;
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, _input.getInputNote());
            pstmt.setInt(2, _input.getInputId());
            pstmt.executeUpdate();            
            
            return true;
        }
        catch (Exception e){
            System.out.println("Error in CInput.delete(MInput)");
        }
        
        return false;    
    }
    
    public void show(JTable _tblInput){
        DefaultTableModel dtModel = (DefaultTableModel)_tblInput.getModel();
        dtModel.setRowCount(0);
        
        try{
            for (MInput _input: inputFn.getAll()){
                dtModel.addRow(_input.toObjArr());
            }
        }
        catch (Exception e){
            System.out.println("Error in CInput.show");
        }
    }
    
    public boolean insert(MInputDetail _inputDetail){
        try {
            Class.forName("org.postgresql.Driver");            
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            query = """
                    INSERT INTO lichsu_nhap(ma_kho, khoi_luong, ghi_chu) VALUES (?, ?, ?);
                    """;
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, _inputDetail.getInputId());
            pstmt.setFloat(2, _inputDetail.getInputWeight());
            pstmt.setString(3, _inputDetail.getInputNote());
            pstmt.executeUpdate();
            
            return true;
        }
        catch (Exception e){
            System.out.println("Error in CInput.insert(MInputDetail)");
        }
        
        return false;
    }
    
    public boolean delete(MInputDetail _inputDetail){
        try{
            Class.forName("org.postgresql.Driver");            
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            query = """
                    DELETE FROM chitiet_nhap
                    WHERE ma_nhapkho = ? AND so_thutu = ?;
                    """;
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, _inputDetail.getInputId());
            pstmt.setInt(2, _inputDetail.getInputNum());
            pstmt.executeUpdate();
            
            return true;
        }
        catch (Exception e){
            System.out.println("Error in CInput.delete(MInputDetail)");
        }
        
        return false;
    }
    
    public boolean update(MInputDetail _inputDetail){
        try {
            Class.forName("org.postgresql.Driver");            
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            query = """
                    UPDATE chitiet_xuat
                    SET ghi_chu = ?
                    WHERE ma_xuatkho = ? AND so_thutu = ?;
                    """;
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, _inputDetail.getInputNote());
            pstmt.setInt(2, _inputDetail.getInputId());
            pstmt.setInt(3, _inputDetail.getInputNum());
            pstmt.executeUpdate();
            
            return true;
        }
        catch (Exception e){
            System.out.println("Error in CInput.update(MInputDetail)");
        }
        
        return false;
    }
    
    public void showDetail(JTable _tblInputDetail){
        DefaultTableModel dtModel = (DefaultTableModel)_tblInputDetail.getModel();
        dtModel.setRowCount(0);
        
        try{
            for (MInputDetail _inputDetail: inputFn.getDetail()){
                dtModel.addRow(_inputDetail.toObjArr());
            }
        }
        catch (Exception e){
            System.out.println("Error in CInput.showDetail");
        }
    }
}
