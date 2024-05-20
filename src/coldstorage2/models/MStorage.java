package coldstorage2.models;

import coldstorage2.general.Database;
import coldstorage2.models.MProduct;
import coldstorage2.models.details.MStorageDetail;

import java.sql.DriverManager;
import java.util.ArrayList;
import javax.swing.JTextField;

record Pair<K, V>(K key, V value) {
    // intentionally empty
}

public class MStorage extends Database{
    private int storageId;
    private float currentWeight;
    private float maxWeight;
    private String storageNote;
    
    public MStorage(){}

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
        this.maxWeight = Float.parseFloat(_tfMaxWeight.getText());
        this.storageNote = _tfStorageNote.getText();
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
            System.out.println("Error in MStorage.getAll()");
        }
        
        return storageData;
    }
    
    public ArrayList<MStorageDetail> getDetail(){
        ArrayList<MStorageDetail> _storageDetail = new ArrayList<>();
        
        int _productId;
        String _productName, _productNote;
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
            pstmt.setInt(1, storageId);
            
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
