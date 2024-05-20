package coldstorage2.models;

import coldstorage2.general.Database;
import coldstorage2.models.details.MInputDetail;
import java.sql.DriverManager;
import java.util.ArrayList;

import java.util.Date;
import javax.swing.JTextField;

public class MInput extends Database{
    private int inputId;
    private String inputDate;
    private String inputTime;
    private Date inputDateTime;
    private float totalWeight;
    private float totalCost;
    private String inputNote;
    
    public MInput(){}

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

    public MInput(int inputId, String inputDate, String inputTime, float totalWeight, float totalCost, String inputNote) {
        this.inputId = inputId;
        this.inputDate = inputDate;
        this.inputTime = inputTime;
        this.totalWeight = totalWeight;
        this.totalCost = totalCost;
        this.inputNote = inputNote;
    }

    public String getInputDate() {
        return inputDate;
    }

    public void setInputDate(String inputDate) {
        this.inputDate = inputDate;
    }

    public String getInputTime() {
        return inputTime;
    }

    public void setInputTime(String inputTime) {
        this.inputTime = inputTime;
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
    
    public Object[] toObjArr(){
        return new Object[]{inputId, inputDate, inputTime, totalWeight, totalCost, inputNote};
    }
    
    public ArrayList<MInput> getAll(){
        ArrayList<MInput> inputs = new ArrayList<>();
        
        int inputId;
        String inputDate;
        String inputTime;
        float totalWeight;
        float totalCost;
        String inputNote;
        
        try{                    
            Class.forName("org.postgresql.Driver");            
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            query = "SELECT * FROM lichsu_nhap";
            pstmt = connection.prepareStatement(query);
            
            rs = pstmt.executeQuery();
            while(rs.next()){
                inputId = rs.getInt(1);
                inputDate = rs.getString(2);
                inputTime = rs.getString(3);
                totalWeight = rs.getFloat(4);
                totalCost = rs.getFloat(5);
                inputNote = rs.getString(6);
                
                inputs.add(new MInput(inputId, inputDate, inputTime, totalWeight, totalCost, inputNote));
            }
            
            return inputs;
        }
        catch(Exception e){
            System.out.println("Error in MInput.getAll()");
        }
        
        return inputs;
    }
    
    public ArrayList<MInputDetail> getDetail(){
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
