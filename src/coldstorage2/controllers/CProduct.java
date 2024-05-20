package coldstorage2.controllers;

import coldstorage2.general.Database;
import coldstorage2.models.MProduct;
import java.sql.DriverManager;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CProduct extends Database{
    
    private final MProduct prodcuctFn = new MProduct();
   
    public CProduct(){}
   
    public boolean insert(MProduct _product){
        try{
            Class.forName("org.postgresql.Driver");            
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            query = "INSERT INTO(ten_sp, ghi_chu) danhmuc_sanpham VALUES(?, ?)";
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, _product.getProductName());
            pstmt.setString(2, _product.getProductNote());            
            pstmt.executeUpdate();
            
            query = """
                    INSERT INTO giatri_sanpham(chiphi_nhap, doanhthu_xuat)
                    VALUES (?, ?)
                    WHERE ma_sp = MAX(ma_sp);
                    """;
            pstmt = connection.prepareStatement(query);
            pstmt.setFloat(1, _product.getProductPrice());
            pstmt.setFloat(2, _product.getProductRevenue());            
            pstmt.executeUpdate();
            
            return true;
        }
        catch(Exception e){
            System.out.println("Error in CProduct.insert\n" + e);
        }
        
        return false;
    }
    
    public boolean delete(MProduct _product){
        /*
            Tao trigger khi xoa bang nay se anh huong bang khac
        */
        try {
            Class.forName("org.postgresql.Driver");            
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            query = "DELETE FROM danhmuc_sanpham WHERE ma_sp = ?;";
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, _product.getProductId());
            pstmt.executeUpdate();
            
            return true;
        }
        catch(Exception e){            
            System.out.println("Error in CProduct.delete\n" + e);
        }
        
        return false;
    }
    
    public boolean update(MProduct _product){
        /*
            Tao them trigger cap nhat bang giatri_sanpham neu gia tri khac voi du lieu cu
        */
        try{
            Class.forName("org.postgresql.Driver");            
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
        
            query = """
                    UPDATE danhmuc_sanpham
                    SET ten_sp = ?, ghi_chu = ?
                    WHERE ma_sp = ?;
                    """;
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, _product.getProductName());
            pstmt.setString(2, _product.getProductNote());
            pstmt.setInt(3, _product.getProductId());            
            pstmt.executeUpdate();
            
            return true;
        }
        catch(Exception e){
            System.out.println("Error in CProduct.update\n" + e);
        }
        
        return false;
    }
    
    public void show(JTable tblProduct){
        DefaultTableModel dtModel = (DefaultTableModel)tblProduct.getModel();
        dtModel.setRowCount(0);
        
        try{
            for (MProduct _product: prodcuctFn.getAll()){
                dtModel.addRow(_product.toObjectArr());
            }
        }
        catch (Exception e){
            System.out.println("Error in CProduct.show\n" + e);
        }
    }
}
