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
public class TeacherUI extends javax.swing.JFrame {

    public String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>";
    private GradeBookClient gradeBookCl;
    private URI resourceURI;
    private char delSelected = 'N';
    
    /**
     * Creates new form TeacherUI
     */
    public TeacherUI() {
        initComponents();
        createGrButton.setSelected(true);
        handleCreateClick();
        gradeBookCl = new GradeBookClient();
        tAppealText.disable();
        
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
            tLocationUri.setText("");
            tResponseCode.setText("");
    }
    private void initializeData()
    {
        if (tStudentId.getText().equals(""))
            tStudentId.setText("0");
        if (tFeedback.getText().equals(""))
            tFeedback.setText("");
        if (tGradeItemName.getText().equals(""))
            tGradeItemName.setText("");
        if (tGrades.getText().equals(""))
            tGrades.setText("0");
        if (tWeightage.getText().equals(""))
            tWeightage.setText("0");
        if (tAppealText.getText().equals(""))
            tAppealText.setText("");       
    }
    
    private void displayErrorDialog( String errorMessage)
    {
        JOptionPane optionPane = new JOptionPane(errorMessage, JOptionPane.ERROR_MESSAGE);    
        JDialog dialog = optionPane.createDialog("Error Message");
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
    
    private GradeBook convertAllFormToXMLString(){
        GradeBook gradeBookClientBean = new GradeBook();
        GradeBook.Student.GradeItem gi = new GradeBook.Student.GradeItem();
        GradeBook.Student st = new GradeBook.Student();
        ArrayList<GradeBook.Student.GradeItem> GrItemList = new ArrayList<GradeBook.Student.GradeItem>();
        ArrayList<GradeBook.Student> StudentList = new ArrayList<GradeBook.Student>();
        
        initializeData();
        
        st.setId(919191988);
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
    
    
    private void populateForm(ClientResponse clientResponse){
        
        if(delSelected == 'Y')
        {
            try{
                tResponseCode.setText(Integer.toString(clientResponse.getStatus()));
                tMediaType.setText(clientResponse.getType().toString());
                return;
            }
            catch(Exception e1){
                tMediaType.setText("application/xml");
                return;
            }
        }
        if(delSelected == 'A')
        {
            tResponseCode.setText(Integer.toString(clientResponse.getStatus()));
            tMediaType.setText(clientResponse.getType().toString());
            if (clientResponse.getStatus() == Response.Status.CREATED.getStatusCode()){
                tLocationUri.setText(clientResponse.getLocation().toString());
            } else {
                tLocationUri.setText("");
            } 
            return;
        }
        if(delSelected == 'N')
        {
            String      entity = clientResponse.getEntity(String.class);

            try{
                if ((clientResponse.getStatus() == Response.Status.OK.getStatusCode()) ||
                    (clientResponse.getStatus() == Response.Status.CREATED.getStatusCode())){
                    GradeBook gradeBookClientBean = (GradeBook)Converter.convertFromXmlToObject(entity, GradeBook.class);

                    tStudentId.setText(Integer.toString(gradeBookClientBean.getStudent().get(0).getId()));
                    tGradeItemName.setText(gradeBookClientBean.getStudent().get(0).getGradeItem().get(0).getItemName());
                    tFeedback.setText(gradeBookClientBean.getStudent().get(0).getGradeItem().get(0).getFeedback());
                    tAppealText.setText(String.valueOf(gradeBookClientBean.getStudent().get(0).getGradeItem().get(0).getappealText()));
                    tGrades.setText(String.valueOf(gradeBookClientBean.getStudent().get(0).getGradeItem().get(0).getValue()));
                    tWeightage.setText(String.valueOf(gradeBookClientBean.getStudent().get(0).getGradeItem().get(0).getWeight()));

                } else {
                    tStudentId.setText("");
                    tGradeItemName.setText("");
                    tFeedback.setText("");
                    tGrades.setText("");
                    tWeightage.setText("");
                    tAppealText.setText("");
                }
            } catch (JAXBException e){
                e.printStackTrace();
            }
        }
        // Populate HTTP Header Information
        tResponseCode.setText(Integer.toString(clientResponse.getStatus()));
        tMediaType.setText(clientResponse.getType().getType() + "/" + clientResponse.getType().getSubtype());
        
        
        // The Location filed is only populated when a Resource is created
        if (clientResponse.getStatus() == Response.Status.CREATED.getStatusCode()){
            tLocationUri.setText(clientResponse.getLocation().toString());
        } else {
            tLocationUri.setText("");
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

        createGrButton = new javax.swing.JRadioButton();
        updateGrButton = new javax.swing.JRadioButton();
        deleteGrButton = new javax.swing.JRadioButton();
        stdIdLabel = new javax.swing.JLabel();
        grItemLabel = new javax.swing.JLabel();
        weightLabel = new javax.swing.JLabel();
        gradeLabel = new javax.swing.JLabel();
        feedbackLabel = new javax.swing.JLabel();
        tGradeItemName = new javax.swing.JTextField();
        tAppealText = new javax.swing.JTextField();
        tStudentId = new javax.swing.JTextField();
        tWeightage = new javax.swing.JTextField();
        tGrades = new javax.swing.JTextField();
        AppealLabel = new javax.swing.JLabel();
        fetchGrButton = new javax.swing.JRadioButton();
        tSubmitButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tResponseCode = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tLocationUri = new javax.swing.JTextField();
        tMediaType = new javax.swing.JTextField();
        tFeedback = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        tBackButton = new javax.swing.JButton();
        allCheckBox = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        createGrButton.setText("Create Grade Items");
        createGrButton.setBorder(new javax.swing.border.MatteBorder(null));
        createGrButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                createGrButtonMouseClicked(evt);
            }
        });
        createGrButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createGrButtonActionPerformed(evt);
            }
        });
        getContentPane().add(createGrButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        updateGrButton.setText("Update Grade Items");
        updateGrButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateGrButtonMouseClicked(evt);
            }
        });
        getContentPane().add(updateGrButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, -1, -1));

        deleteGrButton.setText("Delete Grade Items");
        deleteGrButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteGrButtonMouseClicked(evt);
            }
        });
        getContentPane().add(deleteGrButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 30, -1, -1));

        stdIdLabel.setText("Student Id");
        getContentPane().add(stdIdLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, -1, -1));

        grItemLabel.setText("Grade Item Name");
        getContentPane().add(grItemLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, -1, -1));

        weightLabel.setText("Weightage (in %)");
        getContentPane().add(weightLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, -1, -1));

        gradeLabel.setText("Marks/Grades (in %)");
        getContentPane().add(gradeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, -1, -1));

        feedbackLabel.setText("Feedback");
        getContentPane().add(feedbackLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 230, -1, -1));
        getContentPane().add(tGradeItemName, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 140, 210, -1));

        tAppealText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tAppealTextActionPerformed(evt);
            }
        });
        getContentPane().add(tAppealText, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 260, 210, -1));
        getContentPane().add(tStudentId, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 110, 210, -1));
        getContentPane().add(tWeightage, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 170, 210, -1));
        getContentPane().add(tGrades, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 200, 210, -1));

        AppealLabel.setText("View Appeal");
        getContentPane().add(AppealLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 260, -1, -1));

        fetchGrButton.setText("Fetch Grade Item");
        fetchGrButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fetchGrButtonMouseClicked(evt);
            }
        });
        getContentPane().add(fetchGrButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 30, -1, -1));

        tSubmitButton.setText("Submit");
        tSubmitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tSubmitButtonActionPerformed(evt);
            }
        });
        getContentPane().add(tSubmitButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 390, 100, 30));

        jLabel1.setText("Media Type");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 320, -1, -1));

        jLabel2.setText("Response Code");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, -1, -1));
        getContentPane().add(tResponseCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 320, 170, -1));

        jLabel3.setText("Location URI");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, -1, -1));
        getContentPane().add(tLocationUri, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 360, 490, -1));
        getContentPane().add(tMediaType, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 320, 200, -1));
        getContentPane().add(tFeedback, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 230, 210, -1));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 570, 10));
        getContentPane().add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 590, 10));
        getContentPane().add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 570, 10));

        tBackButton.setText("Back");
        tBackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tBackButtonActionPerformed(evt);
            }
        });
        getContentPane().add(tBackButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 390, 100, 30));

        allCheckBox.setText("Create / Delete Grade Item for all students !!");
        allCheckBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                allCheckBoxMouseClicked(evt);
            }
        });
        allCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allCheckBoxActionPerformed(evt);
            }
        });
        getContentPane().add(allCheckBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 70, -1, -1));

        setSize(new java.awt.Dimension(675, 467));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private boolean generalCheck()
    {
        if((tStudentId.getText().equalsIgnoreCase("")) || (tGradeItemName.getText().equalsIgnoreCase("")))
        {
            displayErrorDialog("Student Id and Grade Item Name are mandatory !!!");
            return false;
        }
        if(!(isInt(tStudentId.getText())))
        {
            displayErrorDialog("Student Id should be an Integer !!!");
            return false;
        }  
        return true;
    }
    private boolean createDelCheck()
    {
        if(allCheckBox.isSelected() == false)
        {
            if((tStudentId.getText().equalsIgnoreCase("")) || (tGradeItemName.getText().equalsIgnoreCase("")))
            {
                displayErrorDialog("Student Id and Grade Item Name are mandatory !!!");
                return false;
            }
            if(!(isInt(tStudentId.getText())))
            {
                displayErrorDialog("Student Id should be an Integer !!!");
                return false;
            }   
        }
        else
        {
            if(tGradeItemName.getText().equalsIgnoreCase(""))
            {
                displayErrorDialog("Grade Item is mandatory !!!");
                return false;
            }
            return true;
        }
        return true;
    }
        
    private void tSubmitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tSubmitButtonActionPerformed
         
        /* CREATE */
        if((createGrButton.isSelected() == true) && (allCheckBox.isSelected() == false))
        {
            if(!(createDelCheck()))
                return;
            
            delSelected = 'N';
            if(!(tWeightage.getText().equalsIgnoreCase("")))
            {
                if(!(isInt(tWeightage.getText())))
                {
                    displayErrorDialog("Weightage should be an Integer !!!");
                    return;
                }           
            }
            
            ClientResponse clientResponse = gradeBookCl.createGradeBookCl(this.convertFormToXMLString());
            resourceURI = clientResponse.getLocation();
            this.populateForm(clientResponse);
        }
        /* READ */
        if(fetchGrButton.isSelected() == true)
        {
            if(!(generalCheck()))
                return;
            delSelected = 'N';
            ClientResponse clientResponse = gradeBookCl.retrieveGradeBookCl(ClientResponse.class, tStudentId.getText(),tGradeItemName.getText());
            this.populateForm(clientResponse);  
        }
        
        /* UPDATE */
        if(updateGrButton.isSelected() == true)
        {
            if(!(generalCheck()))
                return;
            delSelected = 'N';
            if((tGrades.getText().equalsIgnoreCase("")) && (tFeedback.getText().equalsIgnoreCase("")))
            {
                displayErrorDialog("Provide either Grades or Feedback !!!");
                return;
            }
            
            if(!(isInt(tGrades.getText())))
            {
                displayErrorDialog("Grades should be an Integer !!!");
                return;
            }
            
            ClientResponse clientResponse = gradeBookCl.updateGradeBookCl(this.convertFormToXMLString());
            resourceURI = clientResponse.getLocation();
            this.populateForm(clientResponse);
        }
        
        /* DELETE */
        if((deleteGrButton.isSelected() == true) && (allCheckBox.isSelected() == false))
        {
            if(!(createDelCheck()))
                return;
            delSelected = 'Y';
            ClientResponse clientResponse = gradeBookCl.deleteGradeBookCl(tStudentId.getText(),tGradeItemName.getText());
            this.populateForm(clientResponse);  
        }
        
        /* CREATE for ALL !!! */
        if((createGrButton.isSelected() == true) && (allCheckBox.isSelected() == true))
        {
            delSelected = 'A';
            if(!(tWeightage.getText().equalsIgnoreCase("")))
            {
                if(!(isInt(tWeightage.getText())))
                {
                    displayErrorDialog("Weightage should be an Integer !!!");
                    return;
                }           
            }
            ClientResponse clientResponse = gradeBookCl.createAllGradeBookCl(this.convertAllFormToXMLString());
            resourceURI = clientResponse.getLocation();
            this.populateForm(clientResponse);
        }
        
        /* DELETE All Grade Books !! */
        if((deleteGrButton.isSelected() == true) && (allCheckBox.isSelected() == true))
        {
            delSelected = 'Y';
            ClientResponse clientResponse = gradeBookCl.deleteAllGradeBookCl(tGradeItemName.getText());
            this.populateForm(clientResponse);  
        }
        
    }//GEN-LAST:event_tSubmitButtonActionPerformed

    private void handleCreateClick()
    {
        updateGrButton.setSelected(false);
        fetchGrButton.setSelected(false);
        deleteGrButton.setSelected(false);
     
        tStudentId.setVisible(true);
        tGradeItemName.setVisible(true);
        tWeightage.setVisible(true);
        tGrades.setVisible(false);
        tFeedback.setVisible(false);
        tAppealText.setVisible(false);
        allCheckBox.setVisible(true);
             
        stdIdLabel.setVisible(true);
        grItemLabel.setVisible(true);
        weightLabel.setVisible(true);
        gradeLabel.setVisible(false);
        feedbackLabel.setVisible(false);
        AppealLabel.setVisible(false);          
    }
    private void createGrButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_createGrButtonMouseClicked
        clearFormFields();
        handleCreateClick();   
        handleCheckBox();
    }//GEN-LAST:event_createGrButtonMouseClicked

    private void updateGrButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateGrButtonMouseClicked
        clearFormFields();
        createGrButton.setSelected(false);
        fetchGrButton.setSelected(false);
        deleteGrButton.setSelected(false);
        allCheckBox.setVisible(false);
       
        tStudentId.setVisible(true);
        tGradeItemName.setVisible(true);
        tWeightage.setVisible(true);
        tGrades.setVisible(true);
        tFeedback.setVisible(true);
        tAppealText.setVisible(false);
        
        stdIdLabel.setVisible(true);
        grItemLabel.setVisible(true);
        weightLabel.setVisible(true);
        gradeLabel.setVisible(true);
        feedbackLabel.setVisible(true);
        AppealLabel.setVisible(false);
        

    }//GEN-LAST:event_updateGrButtonMouseClicked

    private void createGrButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createGrButtonActionPerformed
        // TODO add your handling code here:
        if(createGrButton.isSelected() == true)
            tAppealText.setVisible(false);
    }//GEN-LAST:event_createGrButtonActionPerformed

    private void deleteGrButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteGrButtonMouseClicked
        clearFormFields();
       
        updateGrButton.setSelected(false);
        fetchGrButton.setSelected(false);
        createGrButton.setSelected(false);
        allCheckBox.setVisible(true);
     
        tStudentId.setVisible(true);
        tGradeItemName.setVisible(true);
        tWeightage.setVisible(false);
        tGrades.setVisible(false);
        tFeedback.setVisible(false);
        tAppealText.setVisible(false);
             
        stdIdLabel.setVisible(true);
        grItemLabel.setVisible(true);
        weightLabel.setVisible(false);
        gradeLabel.setVisible(false);
        feedbackLabel.setVisible(false);
        AppealLabel.setVisible(false);
        handleCheckBox();
    }//GEN-LAST:event_deleteGrButtonMouseClicked

    private void fetchGrButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fetchGrButtonMouseClicked
        clearFormFields();
        updateGrButton.setSelected(false);
        deleteGrButton.setSelected(false);
        createGrButton.setSelected(false);
        allCheckBox.setVisible(false);
     
        tStudentId.setVisible(true);
        tGradeItemName.setVisible(true);
        tWeightage.setVisible(true);
        tGrades.setVisible(true);
        tFeedback.setVisible(true);
        tAppealText.setVisible(true);
             
        stdIdLabel.setVisible(true);
        grItemLabel.setVisible(true);
        weightLabel.setVisible(true);
        gradeLabel.setVisible(true);
        feedbackLabel.setVisible(true);
        AppealLabel.setVisible(true); 
              
    }//GEN-LAST:event_fetchGrButtonMouseClicked

    private void tAppealTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tAppealTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tAppealTextActionPerformed

    private void tBackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tBackButtonActionPerformed
        CRUDBlackBoard_UI mainUI = new CRUDBlackBoard_UI();
        mainUI.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_tBackButtonActionPerformed
    
    private void handleCheckBox()
    {
        if(allCheckBox.isSelected() == true)
        {
            tStudentId.setVisible(false);
            stdIdLabel.setVisible(false);
        }
        if(allCheckBox.isSelected() == false)
        {
            tStudentId.setVisible(true);
            stdIdLabel.setVisible(true);
        }
    }
    
    private void allCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allCheckBoxActionPerformed
        handleCheckBox();
    }//GEN-LAST:event_allCheckBoxActionPerformed

    private void allCheckBoxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_allCheckBoxMouseClicked
        //handleCheckBox();
    }//GEN-LAST:event_allCheckBoxMouseClicked

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
            java.util.logging.Logger.getLogger(TeacherUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TeacherUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TeacherUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TeacherUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TeacherUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AppealLabel;
    private javax.swing.JCheckBox allCheckBox;
    private javax.swing.JRadioButton createGrButton;
    private javax.swing.JRadioButton deleteGrButton;
    private javax.swing.JLabel feedbackLabel;
    private javax.swing.JRadioButton fetchGrButton;
    private javax.swing.JLabel grItemLabel;
    private javax.swing.JLabel gradeLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JLabel stdIdLabel;
    private javax.swing.JTextField tAppealText;
    private javax.swing.JButton tBackButton;
    private javax.swing.JTextField tFeedback;
    private javax.swing.JTextField tGradeItemName;
    private javax.swing.JTextField tGrades;
    private javax.swing.JTextField tLocationUri;
    private javax.swing.JTextField tMediaType;
    private javax.swing.JTextField tResponseCode;
    private javax.swing.JTextField tStudentId;
    private javax.swing.JButton tSubmitButton;
    private javax.swing.JTextField tWeightage;
    private javax.swing.JRadioButton updateGrButton;
    private javax.swing.JLabel weightLabel;
    // End of variables declaration//GEN-END:variables
}
