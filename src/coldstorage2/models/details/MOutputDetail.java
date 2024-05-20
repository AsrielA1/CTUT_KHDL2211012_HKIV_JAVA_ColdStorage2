package coldstorage2.models.details;

import coldstorage2.general.Database;

public class MOutputDetail extends Database{
    private int outputId;
    private int outputNum;
    private int storageId;
    private float weight;
    private String outputNote;

    public MOutputDetail(int outputNum, int storageId, float weight, String outputNote) {
        this.outputNum = outputNum;
        this.storageId = storageId;
        this.weight = weight;
        this.outputNote = outputNote;
    }

    public MOutputDetail(int outputId, int outputNum, int storageId, float weight, String outputNote) {
        this.outputId = outputId;
        this.outputNum = outputNum;
        this.storageId = storageId;
        this.weight = weight;
        this.outputNote = outputNote;
    }

    public int getOutputId() {
        return outputId;
    }

    public void setOutputId(int outputId) {
        this.outputId = outputId;
    }

    public int getOutputNum() {
        return outputNum;
    }

    public void setOutputNum(int outputNum) {
        this.outputNum = outputNum;
    }

    public int getStorageId() {
        return storageId;
    }

    public void setStorageId(int storageId) {
        this.storageId = storageId;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getOutputNote() {
        return outputNote;
    }

    public void setOutputNote(String outputNote) {
        this.outputNote = outputNote;
    }
    
    
}
