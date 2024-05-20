package coldstorage2.views.storage;

import coldstorage2.controllers.CStorage;
import coldstorage2.general.Database;
import coldstorage2.models.MStorage;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class VStorage extends javax.swing.JFrame {
    
    private boolean inEdit = false;
    private boolean inAdd = false;
    private boolean firstPress = true;

    private final Database allFun = new Database();
    private final CStorage storageFn = new CStorage();
    
    public VStorage() {
        initComponents();
        this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        
        storageFn.show(tblStorage);
        tfStorageId.setEditable(false);
        tfCurrentWeight.setEditable(false);
        tfMaxWeight.setEditable(false);
        tfNote.setEditable(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblStorage = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDel = new javax.swing.JButton();
        tfMaxWeight = new javax.swing.JTextField();
        tfStorageId = new javax.swing.JTextField();
        tfCurrentWeight = new javax.swing.JTextField();
        tfNote = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        btnView = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        btnSearch = new javax.swing.JButton();
        tfSearch = new javax.swing.JTextField();
        btnConfirm = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblStorage.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã kho", "Khối lượng hiện tại", "Sức chứa", "Ghi chú"
            }
        ));
        tblStorage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblStorageMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblStorage);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(491, 0, 790, 720));

        jLabel1.setText("Mã kho");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, -1));

        jLabel2.setText("Khối lượng hiện tại");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, -1));

        jLabel3.setText("Sức chứa");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, -1));

        jLabel4.setText("Ghi chú");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, -1, -1));

        btnAdd.setText("Thêm");
        btnAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddMouseClicked(evt);
            }
        });
        getContentPane().add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 420, -1, -1));

        btnEdit.setText("Sửa");
        btnEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEditMouseClicked(evt);
            }
        });
        getContentPane().add(btnEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 470, -1, -1));

        btnDel.setText("Xóa");
        btnDel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDelMouseClicked(evt);
            }
        });
        getContentPane().add(btnDel, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 420, -1, -1));
        getContentPane().add(tfMaxWeight, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 200, 250, -1));
        getContentPane().add(tfStorageId, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, 70, -1));
        getContentPane().add(tfCurrentWeight, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 160, 250, -1));

        tfNote.setColumns(20);
        tfNote.setRows(5);
        getContentPane().add(tfNote, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 250, -1, -1));
        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 250, -1, -1));

        btnView.setText("Xem chi tiết");
        btnView.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnViewMouseClicked(evt);
            }
        });
        getContentPane().add(btnView, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 470, -1, -1));

        jLabel5.setText("Danh mục kho");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 220, 50));

        btnSearch.setText("Tìm");
        getContentPane().add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 520, -1, -1));
        getContentPane().add(tfSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 520, 270, -1));

        btnConfirm.setText("Xác nhận");
        btnConfirm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnConfirmMouseClicked(evt);
            }
        });
        getContentPane().add(btnConfirm, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 680, -1, -1));

        btnCancel.setText("Hủy");
        btnCancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCancelMouseClicked(evt);
            }
        });
        getContentPane().add(btnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 670, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseClicked
        tfStorageId.setText(String.valueOf(storageFn.newId()));
        tfMaxWeight.setEditable(true);
        tfNote.setEditable(true);
        
        tfMaxWeight.setText("");
        tfNote.setText("");
        
        inAdd = true;
    }//GEN-LAST:event_btnAddMouseClicked

    private void btnDelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDelMouseClicked
        boolean success = storageFn.delete(new MStorage().getFromTable(tblStorage));
        if (success){
            JOptionPane.showMessageDialog(rootPane, "Xóa thành công");
            storageFn.show(tblStorage);
        }
    }//GEN-LAST:event_btnDelMouseClicked

    private void btnViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnViewMouseClicked
        
    }//GEN-LAST:event_btnViewMouseClicked

    private void btnEditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditMouseClicked
        tfMaxWeight.setEditable(true);
        tfNote.setEditable(true);
        inEdit = true;
    }//GEN-LAST:event_btnEditMouseClicked

    private void tblStorageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblStorageMouseClicked
        if (!inAdd && !inEdit){
            tfStorageId.setText(storageFn.getTableValue(tblStorage, 0));
            tfCurrentWeight.setText(storageFn.getTableValue(tblStorage, 1));
            tfMaxWeight.setText(storageFn.getTableValue(tblStorage, 2));
            tfNote.setText(storageFn.getTableValue(tblStorage, 3));
        }
    }//GEN-LAST:event_tblStorageMouseClicked

    private void btnConfirmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfirmMouseClicked
        MStorage storage = new MStorage().checkValid(tfMaxWeight, tfNote);
        if (storage != null){
            boolean success;
            if (inEdit){
                storage.setStorageId(Integer.parseInt(tfStorageId.getText()));
                success = storageFn.update(storage);
                JOptionPane.showMessageDialog(rootPane, "Sửa thành công");
            }
            if (inAdd){
                tfStorageId.setText(String.valueOf(storageFn.newId()));
                success = storageFn.insert(storage);
                JOptionPane.showMessageDialog(rootPane, "Thêm thành công");
            }
        }
        
        System.out.println(storage);
        
        storageFn.show(tblStorage);
    }//GEN-LAST:event_btnConfirmMouseClicked

    private void btnCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseClicked
        tfMaxWeight.setEditable(false);
        tfNote.setEditable(false);
        
        tfMaxWeight.setText("");
        tfNote.setText("");
        
        inAdd = false;
        inEdit = false;
    }//GEN-LAST:event_btnCancelMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VStorage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VStorage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VStorage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VStorage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VStorage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnConfirm;
    private javax.swing.JButton btnDel;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnView;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblStorage;
    private javax.swing.JTextField tfCurrentWeight;
    private javax.swing.JTextField tfMaxWeight;
    private javax.swing.JTextArea tfNote;
    private javax.swing.JTextField tfSearch;
    private javax.swing.JTextField tfStorageId;
    // End of variables declaration//GEN-END:variables
}
