package coldstorage2.models.details;

import coldstorage2.general.Database;

public class MInputDetail extends Database{
    private int inputId;
    private int inputNum;
    private int storageId;
    private float inputWeight;
    private String inputNote;

    public MInputDetail(int inputId, int inputNum, int storageId, float inputWeight, String inputNote) {
        this.inputId = inputId;
        this.inputNum = inputNum;
        this.storageId = storageId;
        this.inputWeight = inputWeight;
        this.inputNote = inputNote;
    }
    
    public MInputDetail(int inputNum, int storageId, float inputWeight, String inputNote) {
        this.inputNum = inputNum;
        this.storageId = storageId;
        this.inputWeight = inputWeight;
        this.inputNote = inputNote;
    }

    public int getInputId() {
        return inputId;
    }

    public void setInputId(int inputId) {
        this.inputId = inputId;
    }

    public int getInputNum() {
        return inputNum;
    }

    public void setInputNum(int inputNum) {
        this.inputNum = inputNum;
    }

    public int getStorageId() {
        return storageId;
    }

    public void setStorageId(int storageId) {
        this.storageId = storageId;
    }

    public float getInputWeight() {
        return inputWeight;
    }

    public void setInputWeight(float inputWeight) {
        this.inputWeight = inputWeight;
    }

    public String getInputNote() {
        return inputNote;
    }

    public void setInputNote(String inputNote) {
        this.inputNote = inputNote;
    }
    
    
}
