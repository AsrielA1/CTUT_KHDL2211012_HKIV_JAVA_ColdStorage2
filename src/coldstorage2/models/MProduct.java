package coldstorage2.models;

import coldstorage2.general.Database;
import java.sql.DriverManager;
import java.util.ArrayList;
import javax.swing.JTextField;

public class MProduct extends Database{
    private int productId;
    private String productName;
    private String productNote;
    private float productPrice;
    private float productRevenue;
    
    public MProduct(){}

    public MProduct(JTextField _tfProductName, JTextField _tfProductNote){
        this.productName = _tfProductName.getText();
        this.productNote = _tfProductNote.getText();
    }
    
    public MProduct(int productId, String productName, String productNote) {
        this.productId = productId;
        this.productName = productName;
        this.productNote = productNote;
    }

    public MProduct(int productId, float productPrice, float productRevenue) {
        this.productId = productId;
        this.productPrice = productPrice;
        this.productRevenue = productRevenue;
    }

    public MProduct(int productId, String productName, String productNote, float productPrice, float productRevenue) {
        this.productId = productId;
        this.productName = productName;
        this.productNote = productNote;
        this.productPrice = productPrice;
        this.productRevenue = productRevenue;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductNote() {
        return productNote;
    }

    public void setProductNote(String productNote) {
        this.productNote = productNote;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public float getProductRevenue() {
        return productRevenue;
    }

    public void setProductRevenue(float productRevenue) {
        this.productRevenue = productRevenue;
    }    
    
    public Object[] toObjectArr(){
        return new Object[]{productId, productName, productNote, productPrice, productRevenue};
    }
    
    public ArrayList<MProduct> getAll(){
        ArrayList<MProduct> products = new ArrayList<>();
        
        int _productId;
        String _productName;
        String _productNote;
        float _productPrice;
        float _productRevenue;
        
        try {            
            Class.forName("org.postgresql.Driver");            
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            query = """
                    SELECT san_pham.*, giatri_sanpham.chiphi_nhap, chiphi_nhap.doanhthu_xuat  
                    FROM san_pham
                    JOIN giatri_sanpham ON giatri_sanpham.ma_sp = san_pham.ma_sp;
                    """;
            pstmt = connection.prepareStatement(query);
            
            rs = pstmt.executeQuery();
            while (rs.next()){
                _productId = rs.getInt(1);
                _productName = rs.getString(2);
                _productNote = rs.getString(3);
                _productPrice = rs.getFloat(4);
                _productRevenue = rs.getFloat(5);
                
                products.add(new MProduct(_productId, _productName, _productNote, _productPrice, _productRevenue));
            }
            
            return products;
        }
        catch (Exception e){
            System.out.println("Error in MProduct.getAll");
        }
        
        return products;
    }
}
