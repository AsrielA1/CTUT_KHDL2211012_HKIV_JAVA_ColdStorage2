package coldstorage2.models;

import coldstorage2.general.Database;
import javax.swing.JTextField;

public class MProduct extends Database{
    private int productId;
    private String productName;
    private String productNote;
    private float productPrice;
    private float productRevenue;
    
    public MProduct(String productName, String productNote) {
        this.productName = productName;
        this.productNote = productNote;
    }    

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
    
}
