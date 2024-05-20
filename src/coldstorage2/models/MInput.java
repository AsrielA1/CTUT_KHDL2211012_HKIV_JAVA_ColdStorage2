package coldstorage2.models;

import coldstorage2.general.Database;
import coldstorage2.models.details.MInputDetail;
import java.sql.DriverManager;
import java.util.ArrayList;

import java.util.Date;
import javax.swing.JTextField;

public class MInput extends Database{
    private int inputId;
    private Date inputDateTime;
    private float totalWeight;
    private float totalCost;
    private String inputNote;

    public MInput(int inputId, Date inputDateTime, float totalWeight, float totalCost, String inputNote) {
        this.inputId = inputId;
        this.inputDateTime = inputDateTime;
        this.totalWeight = totalWeight;
        this.totalCost = totalCost;
        this.inputNote = inputNote;
    }
    
    public MInput(JTextField _tfInputNote) {
        this.totalWeight = 0;
        this.totalCost = 0;
        this.inputNote = _tfInputNote.getText();
    }

    public int getInputId() {
        return inputId;
    }

    public void setInputId(int inputId) {
        this.inputId = inputId;
    }

    public Date getInputDateTime() {
        return inputDateTime;
    }

    public void setInputDateTime(Date inputDateTime) {
        this.inputDateTime = inputDateTime;
    }

    public float getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(float totalWeight) {
        this.totalWeight = totalWeight;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

    public String getInputNote() {
        return inputNote;
    }

    public void setInputNote(String inputNote) {
        this.inputNote = inputNote;
    }
    
    public ArrayList<MInputDetail> getInputDetail(){
        ArrayList<MInputDetail> inputDetails = new ArrayList<>();
        
        int _inputNum, _storageId;
        float _inputWeight;
        String _inputNote;
        
        try{            
            Class.forName("org.postgresql.Driver");            
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            query = """
                    SELECT lichsu_nhap.*
                    FROM lichsu_nhap
                    JOIN chitiet_nhap ON chitiet_nhap.ma_nhapkho = lichsu_nhap.ma_nhapkho
                    WHERE ma_nhapkho = ?;
                    """;
            pstmt.setInt(1, inputId);
            
            rs = pstmt.executeQuery();
            while (rs.next()){
                _inputNum = rs.getInt(2);
                _storageId = rs.getInt(3);
                _inputWeight = rs.getFloat(4);
                _inputNote = rs.getString(5);
                
                inputDetails.add(new MInputDetail(inputId, _inputNum, _storageId, _inputWeight, _inputNote));
            }
            
            return inputDetails;
        }
        catch (Exception e){
            System.out.println("Error in MInput.getInputDetail");
        }
        
        return inputDetails;
    }
}
