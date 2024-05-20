package coldstorage2.models;

import coldstorage2.general.Database;
import coldstorage2.models.MProduct;
import coldstorage2.models.details.MStorageDetail;

import java.sql.DriverManager;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

record Pair<K, V>(K key, V value) {
    // intentionally empty
}

public class MStorage extends Database{
    private int storageId;
    private float currentWeight;
    private float maxWeight;
    private String storageNote;
    
    public MStorage(){}

    public MStorage(int storageId) {
        this.storageId = storageId;
    }
    
    

    public MStorage(int storageId, float currentWeight, float maxWeight, String storageNote) {
        this.storageId = storageId;
        this.currentWeight = currentWeight;
        this.maxWeight = maxWeight;
        this.storageNote = storageNote;
    }

    public MStorage(int storageId, float maxWeight, String storageNote) {
        this.storageId = storageId;
        this.currentWeight = 0;
        this.maxWeight = maxWeight;
        this.storageNote = storageNote;
        
    }
    
    public MStorage(JTextField _tfMaxWeight, JTextField _tfStorageNote) {
        this.currentWeight = 0;
        
        this.storageNote = _tfStorageNote.getText();
    }

    public MStorage(float maxWeight, String storageNote) {
        this.maxWeight = maxWeight;
        this.storageNote = storageNote;
    }
    
    public MStorage checkValid(JTextField _tfMaxWeight, JTextArea _tfStorageNote){
        
        try{
            float _maxWeight = Float.parseFloat(_tfMaxWeight.getText());
            return new MStorage(_maxWeight, _tfStorageNote.getText());
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(_tfMaxWeight, "Sức chứa phải là số");
        }
        
        if (Float.parseFloat(_tfMaxWeight.getText()) <= 0){
            JOptionPane.showMessageDialog(_tfMaxWeight, "Sức chứa phải lớn hơn 0");
            return null;
        }
        
        return null;
    }
    
    public MStorage getFromTable(JTable _tblStorage){
        int row = _tblStorage.getSelectedRow();
        DefaultTableModel dtModel = (DefaultTableModel)_tblStorage.getModel();
        
        int _storageId = Integer.parseInt(dtModel.getValueAt(row, 0).toString());
        
        return new MStorage(_storageId);
    }

    public int getStorageId() {
        return storageId;
    }

    public void setStorageId(int storageId) {
        this.storageId = storageId;
    }

    public float getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(float currentWeight) {
        this.currentWeight = currentWeight;
    }

    public float getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(float maxWeight) {
        this.maxWeight = maxWeight;
    }

    public String getStorageNote() {
        return storageNote;
    }

    public void setStorageNote(String storageNote) {
        this.storageNote = storageNote;
    }
    
    public float getUsedPercent(int _storageId){
        float current, max;
        
        try{                        
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            query = """
                    SELECT khoiluong_hientai, suc_chua
                    FROM danhmuc_kho
                    WHERE ma_kho = ?;
                    """;
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, _storageId);
            
            rs = pstmt.executeQuery();
            while(rs.next()){
                current = rs.getFloat(1);
                max = rs.getFloat(2);
                
                return current/max;
            }
        }
        catch (Exception e){
            System.out.println("MStorage.getUsedPercent\n"+e);
        }
        
        return 0;
    }
    
    public Object[] toObjectArr(){
        return new Object[]{storageId, currentWeight, maxWeight, storageNote};
    }
    
   
    public ArrayList<MStorage> getAll(){
        ArrayList<MStorage> storageData = new ArrayList<>();
        
        int _storageId;
        float _currentWeight;
        float _maxWeight;
        String _storageNote;
        
        try {            
            Class.forName("org.postgresql.Driver");            
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            query = "SELECT * FROM danhmuc_kho";
            pstmt = connection.prepareStatement(query);
            
            rs = pstmt.executeQuery();
            while(rs.next()){
                _storageId = rs.getInt(1);
                _currentWeight = rs.getFloat(2);
                _maxWeight = rs.getFloat(3);
                _storageNote = rs.getString(4);
                
                storageData.add(new MStorage(_storageId, _currentWeight, _maxWeight, _storageNote));
            }
        }
        catch (Exception e){
            System.out.println("Error in MStorage.getAll()\n" + e);
        }
        
        return storageData;
    }
    
    public ArrayList<MStorageDetail> getDetail(int _storageId){
        ArrayList<MStorageDetail> _storageDetail = new ArrayList<>();
        
        int _productId;
        String _productName;
        float _productWeight;
        
        try{            
            Class.forName("org.postgresql.Driver");            
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
        
            query = """
                    SELECT danhmuc_sanpham.ma_sp, danhmuc_sanpham.ten_sp, chitiet_kho.khoiluong
                    FROM chitiet_kho
                    JOIN danhmuc_sanpham ON danhmuc_sanpham.ma_sp = chitiet_kho.ma_sp
                    WHERE chitiet_kho.ma_kho = ?;
                    """;
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, _storageId);
            
            rs = pstmt.executeQuery();
            while (rs.next()){
                _productId = rs.getInt(1);
                _productName = rs.getString(2);
                _productWeight = rs.getFloat(4);
                
                _storageDetail.add(new MStorageDetail(_productId, _productName,_productWeight));
                
                return _storageDetail;
            }
            
        }
        catch (Exception e){
            System.out.println("Error in MStorage.getDetail\n" + e);
        }
        
        return _storageDetail;
    }
}
