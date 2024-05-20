package coldstorage2.models.details;

import coldstorage2.general.Database;

public class MStorageDetail {
    private int productId;
    private String productName;
    private float weight;

    public MStorageDetail(int productId, String productName, float weight) {
        this.productId = productId;
        this.productName = productName;
        this.weight = weight;
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

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
    
    public Object[] toObjArr(){
        return new Object[]{productId, productName, weight};
    }
    
}
