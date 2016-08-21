/*==================================================================
*  IMPORTANT        : File for academic project for course CSE564
*  Category         : Academic project
*  Type             : Java Program
*  Description      : Code for project 2- CSE564
*  Author           : Varun Gaur
*  Modification Log :
*  Author           Date		Reason
*  Varun Gaur       2-Apr-2016		Original
* =================================================================*/
package gradeBookClient;

import java.net.URL;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import com.sun.jersey.api.client.ClientResponse;
import javax.ws.rs.core.Response;
import javax.swing.JFrame;
import javax.xml.bind.JAXBException;
import java.net.URI;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
/**
 *
 * @author Varun_Gaur
 */
public class StudentUI extends javax.swing.JFrame {
    
    public String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>";
    private GradeBookClient gradeBookCl;
    private URI resourceURI;
    /**
     * Creates new form StudentUI
     */
    public StudentUI() {
        initComponents();
        handleCreateClick();
        gradeBookCl = new GradeBookClient();
        tFeedback.disable();
        tGrades.disable();
        tWeightage.disable();
        tMediaType.disable();
        tResponseCode.disable();
    }
    private void clearFormFields()
    {
            tStudentId.setText("");
            tFeedback.setText("");
            tGradeItemName.setText("");
            tGrades.setText("");
            tWeightage.setText("");
            tAppealText.setText("");
            tMediaType.setText("");
            tResponseCode.setText("");
    }
    private void initializeData()
    {
        if (tStudentId.getText().equals(""))
            tStudentId.setText("0");
        if (tFeedback.getText().equals(""))
            tFeedback.setText("-");
        if (tGradeItemName.getText().equals(""))
            tGradeItemName.setText("NA");
        if (tGrades.getText().equals(""))
            tGrades.setText("0");
        if (tWeightage.getText().equals(""))
            tWeightage.setText("0");
        if (tAppealText.getText().equals(""))
            tAppealText.setText("-");       
    }
    private void displayErrorDialog( String errorMessage)
    {
        JOptionPane optionPane = new JOptionPane(errorMessage, JOptionPane.ERROR_MESSAGE);    
        JDialog dialog = optionPane.createDialog("Message");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }
    
    static boolean isInt(String s)
    {
        try
        { int i = Integer.parseInt(s); return true; }

        catch(NumberFormatException er)
        { return false; }
    }
    
        private GradeBook convertFormToXMLString(){
        GradeBook gradeBookClientBean = new GradeBook();
        GradeBook.Student.GradeItem gi = new GradeBook.Student.GradeItem();
        GradeBook.Student st = new GradeBook.Student();
        ArrayList<GradeBook.Student.GradeItem> GrItemList = new ArrayList<GradeBook.Student.GradeItem>();
        ArrayList<GradeBook.Student> StudentList = new ArrayList<GradeBook.Student>();
        
        initializeData();
        
        st.setId(Integer.parseInt(tStudentId.getText()));
        gi.setFeedback(tFeedback.getText());
        gi.setappealText(tAppealText.getText());        
        gi.setItemName(tGradeItemName.getText());
        gi.setValue(Integer.parseInt(tGrades.getText()));
        gi.setWeight(Integer.parseInt(tWeightage.getText()));
        GrItemList.add(gi);
        st.setGradeItem(GrItemList);
        StudentList.add(st);
        gradeBookClientBean.setStudent(StudentList);

        gradeBookClientBean.student.add(st);

        return gradeBookClientBean;
    }
    
    private void populateForm(ClientResponse clientResponse,char mode){       
    String      entity = clientResponse.getEntity(String.class);
    try{
        if ((clientResponse.getStatus() == Response.Status.OK.getStatusCode()) ||
            (clientResponse.getStatus() == Response.Status.CREATED.getStatusCode())){
            GradeBook gradeBookClientBean = (GradeBook)Converter.convertFromXmlToObject(entity, GradeBook.class);

            tStudentId.setText(Integer.toString(gradeBookClientBean.getStudent().get(0).getId()));
            tGradeItemName.setText(gradeBookClientBean.getStudent().get(0).getGradeItem().get(0).getItemName());
            tFeedback.setText(gradeBookClientBean.getStudent().get(0).getGradeItem().get(0).getFeedback());
            tGrades.setText(String.valueOf(gradeBookClientBean.getStudent().get(0).getGradeItem().get(0).getValue()));
            tWeightage.setText(String.valueOf(gradeBookClientBean.getStudent().get(0).getGradeItem().get(0).getWeight()));
            tAppealText.setText(String.valueOf(gradeBookClientBean.getStudent().get(0).getGradeItem().get(0).getappealText()));
            if(mode == 'A')
                displayErrorDialog("Appeal lodged Successfully !!");
        } else {
            tStudentId.setText("");
            tGradeItemName.setText("");
            tFeedback.setText("");
            tGrades.setText("");
            tWeightage.setText("");
            tAppealText.setText("");
            if(mode == 'A')
                displayErrorDialog("Appeal lodging Failed !!");
        }
        // Populate HTTP Header Information
        tResponseCode.setText(Integer.toString(clientResponse.getStatus()));
        tMediaType.setText(clientResponse.getType().getType() + "/" + clientResponse.getType().getSubtype());          
            
        } catch (JAXBException e){
            e.printStackTrace();
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        SstdIdLabel = new javax.swing.JLabel();
        SgrItemLabel = new javax.swing.JLabel();
        SweightLabel = new javax.swing.JLabel();
        SgradeLabel = new javax.swing.JLabel();
        SfeedbackLabel = new javax.swing.JLabel();
        tStudentId = new javax.swing.JTextField();
        tGradeItemName = new javax.swing.JTextField();
        tWeightage = new javax.swing.JTextField();
        tGrades = new javax.swing.JTextField();
        tFeedback = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tMediaType = new javax.swing.JTextField();
        tResponseCode = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        AppealLabel = new javax.swing.JLabel();
        Sclose = new javax.swing.JButton();
        SappealButton = new javax.swing.JButton();
        SfetchButton = new javax.swing.JButton();
        tAppealText = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Fetch Grade Item details");

        SstdIdLabel.setText("Student Id");

        SgrItemLabel.setText("Grade Item Name");

        SweightLabel.setText("Weightage (in %)");

        SgradeLabel.setText("Marks/Grades (in %)");

        SfeedbackLabel.setText("Feedback");

        jLabel2.setText("Response Code");

        jLabel3.setText("Media Type");

        AppealLabel.setText("Write Appeal");

        Sclose.setText("Back");
        Sclose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ScloseActionPerformed(evt);
            }
        });

        SappealButton.setText("Appeal");
        SappealButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SappealButtonActionPerformed(evt);
            }
        });

        SfetchButton.setText("Fetch");
        SfetchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SfetchButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(56, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tMediaType, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(SgradeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(SweightLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(SfeedbackLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(SgrItemLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(230, 230, 230)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(tStudentId, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tGradeItemName, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tWeightage, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tGrades, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tFeedback, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(AppealLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(25, 25, 25)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(tResponseCode, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel3))
                                        .addComponent(tAppealText)))))
                        .addComponent(SstdIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(63, 63, 63))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(268, 268, 268)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(203, 203, 203)
                        .addComponent(SfetchButton)
                        .addGap(41, 41, 41)
                        .addComponent(SappealButton)
                        .addGap(38, 38, 38)
                        .addComponent(Sclose)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tStudentId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SstdIdLabel))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tGradeItemName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SgrItemLabel))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tWeightage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SweightLabel))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tGrades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SgradeLabel))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tFeedback, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SfeedbackLabel))
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tResponseCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(tMediaType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tAppealText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AppealLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SfetchButton)
                    .addComponent(SappealButton)
                    .addComponent(Sclose))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(685, 503));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ScloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ScloseActionPerformed
        CRUDBlackBoard_UI mainUI = new CRUDBlackBoard_UI();
        mainUI.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_ScloseActionPerformed

    private void SfetchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SfetchButtonActionPerformed
        if((tStudentId.getText().equalsIgnoreCase("")) || (tGradeItemName.getText().equalsIgnoreCase("")))
        {
            displayErrorDialog("Student Id and Grade Item Name are mandatory !!!");
            return;
        }
        if(!(isInt(tStudentId.getText())))
        {
            displayErrorDialog("Student Id should be an Integer !!!");
            return;
        } 
        ClientResponse clientResponse = gradeBookCl.retrieveGradeBookCl(ClientResponse.class, tStudentId.getText(),tGradeItemName.getText());
        this.populateForm(clientResponse,'F');  
        
    }//GEN-LAST:event_SfetchButtonActionPerformed

    private void SappealButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SappealButtonActionPerformed
        if((tStudentId.getText().equalsIgnoreCase("")) || (tGradeItemName.getText().equalsIgnoreCase("")))
        {
            displayErrorDialog("Student Id and Grade Item Name are mandatory !!!");
            return;
        }
        if(tAppealText.getText().equalsIgnoreCase(""))
        {
            displayErrorDialog("Please provide appeal details for Grade Appeal !!!");
            return;
        }
        if(!(isInt(tStudentId.getText())))
        {
            displayErrorDialog("Student Id should be an Integer !!!");
            return;
        } 
        ClientResponse clientResponse = gradeBookCl.updateAppealGradeBookCl(this.convertFormToXMLString());
        resourceURI = clientResponse.getLocation();
        this.populateForm(clientResponse,'A');
    }//GEN-LAST:event_SappealButtonActionPerformed
    
    private void handleCreateClick()
    {
        
        tAppealText.setVisible(true);
        AppealLabel.setVisible(true);          
    }
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
            java.util.logging.Logger.getLogger(StudentUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AppealLabel;
    private javax.swing.JButton SappealButton;
    private javax.swing.JButton Sclose;
    private javax.swing.JLabel SfeedbackLabel;
    private javax.swing.JButton SfetchButton;
    private javax.swing.JLabel SgrItemLabel;
    private javax.swing.JLabel SgradeLabel;
    private javax.swing.JLabel SstdIdLabel;
    private javax.swing.JLabel SweightLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextField tAppealText;
    private javax.swing.JTextField tFeedback;
    private javax.swing.JTextField tGradeItemName;
    private javax.swing.JTextField tGrades;
    private javax.swing.JTextField tMediaType;
    private javax.swing.JTextField tResponseCode;
    private javax.swing.JTextField tStudentId;
    private javax.swing.JTextField tWeightage;
    // End of variables declaration//GEN-END:variables
}
