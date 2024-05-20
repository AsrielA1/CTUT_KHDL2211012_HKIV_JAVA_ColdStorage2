package coldstorage2.models;

import coldstorage2.general.Database;
import coldstorage2.models.details.MOutputDetail;
import java.sql.DriverManager;
import java.util.ArrayList;

import java.util.Date;

public class MOutput extends Database{
    private int outputId;
    private String outputDate;
    private String outputTime;
    private Date outputDateTime;
    private float totalWeight;
    private float totalRevenue;
    private String outputNote;
    
    public MOutput(){}

    public String getOutputDate() {
        return outputDate;
    }

    public void setOutputDate(String outputDate) {
        this.outputDate = outputDate;
    }

    public String getOutputTime() {
        return outputTime;
    }

    public void setOutputTime(String outputTime) {
        this.outputTime = outputTime;
    }

    public MOutput(int outputId, String outputDate, String outputTime, float totalWeight, float totalRevenue, String outputNote) {
        this.outputId = outputId;
        this.outputDate = outputDate;
        this.outputTime = outputTime;
        this.totalWeight = totalWeight;
        this.totalRevenue = totalRevenue;
        this.outputNote = outputNote;
    }

    public MOutput(int outputId, Date outputDateTime, String outputNote) {
        this.outputId = outputId;
        this.outputDateTime = outputDateTime;
        this.totalWeight = 0;
        this.totalRevenue = 0;
        this.outputNote = outputNote;
    }

    public MOutput(int outputId, Date outputDateTime, float totalWeight, float totalRevenue, String outputNote) {
        this.outputId = outputId;
        this.outputDateTime = outputDateTime;
        this.totalWeight = totalWeight;
        this.totalRevenue = totalRevenue;
        this.outputNote = outputNote;
    }

    public int getOutputId() {
        return outputId;
    }

    public void setOutputId(int outputId) {
        this.outputId = outputId;
    }

    public Date getOutputDateTime() {
        return outputDateTime;
    }

    public void setOutputDateTime(Date outputDateTime) {
        this.outputDateTime = outputDateTime;
    }

    public float getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(float totalWeight) {
        this.totalWeight = totalWeight;
    }

    public float getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(float totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public String getOutputNote() {
        return outputNote;
    }

    public void setOutputNote(String outputNote) {
        this.outputNote = outputNote;
    }
    
    public ArrayList<MOutput> getAll(){
        ArrayList<MOutput> outputs = new ArrayList<>();
        
        int outputId;
        String outputDate;
        String outputTime;
        float totalWeight;
        float totalCost;
        String outputNote;
        
        try{                    
            Class.forName("org.postgresql.Driver");            
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            query = "SELECT * FROM lichsu_xuat";
            pstmt = connection.prepareStatement(query);
            
            rs = pstmt.executeQuery();
            while(rs.next()){
                outputId = rs.getInt(1);
                outputDate = rs.getString(2);
                outputTime = rs.getString(3);
                totalWeight = rs.getFloat(4);
                totalCost = rs.getFloat(5);
                outputNote = rs.getString(6);
                
                outputs.add(new MOutput(outputId, outputDate, outputTime, totalWeight, totalCost, outputNote));
            }
            
            return outputs;
        }
        catch(Exception e){
            System.out.println("Error in MOutput.getAll()");
        }
        
        return outputs;
    }
    
    public ArrayList<MOutputDetail> getDetail(){
        ArrayList<MOutputDetail> outputDetails = new ArrayList<>();
        
        int _outputNum, _storageId;
        float _weight;
        String _outputNote;
        
        try{
            Class.forName("org.postgresql.Driver");            
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
        
            query = """
                    SELECT chitiet_xuat.*
                    FROM chitiet_xuat
                    JOIN lichsu_xuat ON lichsu_xuat.ma_xuatkho = chitiet_xuat.ma_xuatkho
                    WHERE ma_xuatkho = ?;
                    """;
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, outputId);
            
            rs = pstmt.executeQuery();
            while(rs.next()){
                _outputNum = rs.getInt(2);
                _storageId = rs.getInt(3);
                _weight = rs.getFloat(4);
                _outputNote = rs.getString(5);
                
                outputDetails.add(new MOutputDetail(_outputNum, _storageId, _weight, _outputNote));
            }
            
            return outputDetails;
        }
        catch(Exception e){
            System.out.println("Error in MOutput.getDetail\n" + e);
        }
    
        return outputDetails;
    }
}
