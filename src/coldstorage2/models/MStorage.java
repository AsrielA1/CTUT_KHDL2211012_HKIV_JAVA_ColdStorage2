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
    
    public ArrayList<MStorageDetail> getDetail(){
        ArrayList<MStorageDetail> _storageDetail = new ArrayList<>();
        
        int _productId;
        String _productName, _productNote;
        float _productWeight;
        
        try{            
            Class.forName("org.postgresql.Driver");            
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
        
            query = """
                    SELECT danhmuc_sanpham.*, chitiet_kho.khoiluong
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
                _productNote = rs.getString(3);
                _productWeight = rs.getFloat(4);
                MProduct product = new MProduct(_productId, _productName, _productNote);
                
                _storageDetail.add(new MStorageDetail(product,_productWeight));
                
                return _storageDetail;
            }
            
        }
        catch (Exception e){
            System.out.println("Error in MStorage.getDetail\n" + e);
        }
        
        return _storageDetail;
    }
}
