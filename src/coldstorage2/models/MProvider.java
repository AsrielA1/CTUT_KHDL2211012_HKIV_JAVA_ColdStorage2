package coldstorage2.models;

import coldstorage2.general.Database;
import java.sql.DriverManager;
import java.util.ArrayList;
import javax.swing.JTextField;

public class MProvider extends Database{
    private int providerId;
    private String providerName;
    private String providerEmail;
    private String providerNumber;
    private String providerNote;

    public MProvider(int providerId, String providerName, String providerEmail, String providerNumber, String providerNote) {
        this.providerId = providerId;
        this.providerName = providerName;
        this.providerEmail = providerEmail;
        this.providerNumber = providerNumber;
        this.providerNote = providerNote;
    }
    
    public MProvider(JTextField _tfProviderName, JTextField _tfProviderEmail, JTextField _tfProviderNumber, JTextField _tfProviderNote){
        this.providerName = _tfProviderName.getText();
        this.providerEmail = _tfProviderEmail.getText();
        this.providerNumber = _tfProviderNumber.getText();
        this.providerNote = _tfProviderNote.getText();        
    }

    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getProviderEmail() {
        return providerEmail;
    }

    public void setProviderEmail(String providerEmail) {
        this.providerEmail = providerEmail;
    }

    public String getProviderNumber() {
        return providerNumber;
    }

    public void setProviderNumber(String providerNumber) {
        this.providerNumber = providerNumber;
    }

    public String getProviderNote() {
        return providerNote;
    }

    public void setProviderNote(String providerNote) {
        this.providerNote = providerNote;
    }
    
    public ArrayList<MProduct> getProductOfferings(){
        ArrayList<MProduct> _products = new ArrayList<>();
        
        int _productId;
        String _productName, _productNote;
        
        try{
            Class.forName("org.postgresql.Driver");            
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            
            query = """
                    SELECT danhmuc_sanpham.* 
                    FROM danhmuc_sanpham
                    JOIN sp_cungcap ON sp_cungcap.ma_sp = danhmuc_sanpham.ma_sp
                    WHERE sp_cungcap.ma_nhacungung = ?;
                    """;
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, providerId);
            
            rs = pstmt.executeQuery();
            while(rs.next()){
                _productId = rs.getInt(1);
                _productName = rs.getString(2);
                _productNote = rs.getString(3);
                
                _products.add(new MProduct(_productId, _productName, _productNote));
            }
            
            return _products;
        }
        catch(Exception e){
            System.out.println("MProvider.getProductOfferings\n" + e);
        }
        
        return _products;
    }
}
