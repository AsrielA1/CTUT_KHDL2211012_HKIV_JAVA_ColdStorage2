package coldstorage2.models.details;

import coldstorage2.models.MProduct;

public class MStorageDetail {
    private MProduct product;
    private float weight;

    public MStorageDetail(MProduct product, float weight) {
        this.product = product;
        this.weight = weight;
    }

    public MProduct getProduct() {
        return product;
    }

    public void setProduct(MProduct product) {
        this.product = product;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

}
