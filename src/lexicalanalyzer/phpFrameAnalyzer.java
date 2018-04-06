/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexicalanalyzer;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Oscar
 */
public class phpFrameAnalyzer extends javax.swing.JFrame {

    /**
     * Creates new form phpFrameAnalyzer
     */
    
    String fileName;
    
    public phpFrameAnalyzer() {
        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        saveFile_btn = new javax.swing.JButton();
        openFile_btn = new javax.swing.JButton();
        analyze_btn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jTextArea2.setEditable(false);
        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        saveFile_btn.setText("Guardar archivo");
        saveFile_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveFile_btnActionPerformed(evt);
            }
        });

        openFile_btn.setText("Abrir Archivo");
        openFile_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFile_btnActionPerformed(evt);
            }
        });

        analyze_btn.setText("Analizar");
        analyze_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                analyze_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(367, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(analyze_btn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(openFile_btn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(saveFile_btn, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(532, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(159, 159, 159)
                .addComponent(openFile_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(analyze_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(saveFile_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(184, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void openFile_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openFile_btnActionPerformed
        // TODO add your handling code here:
        // Limpia el texto que haya en los textArea
        jTextArea1.setText(null);
        jTextArea2.setText(null);       
              
        JFileChooser openAsmFile = new JFileChooser(); // File Chooser is like OpenFileDialog (C#)
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("PHP","php");
        File phpFile;
        String rutaArchivo;
        openAsmFile.setFileFilter(filtro);
        int valor = openAsmFile.showOpenDialog(this);
        
        if(valor == JFileChooser.APPROVE_OPTION){
            phpFile = openAsmFile.getSelectedFile();
            fileName = Utilities.removeExtension(phpFile.getName());;
            try (BufferedReader br = new BufferedReader(new FileReader(phpFile))) 
            {
                String line;
                while ((line = br.readLine()) != null) {
                    //line = line.replaceFirst("\\s","");
                    line = line.trim();
                    jTextArea2.append(line + "\n");      
                }
                
                // Habilitar el boton para traducir
                if (!jTextArea2.getText().equals(""))
                    analyze_btn.setEnabled(true);
            }           
            catch (IOException ex) {
                Logger.getLogger(phpFrameAnalyzer.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
    }//GEN-LAST:event_openFile_btnActionPerformed

    private void analyze_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_analyze_btnActionPerformed
        try {
            // TODO add your handling code here:
            Utilities.analyzeFile(jTextArea2, jTextArea1);
        } catch (IOException ex) {
            Logger.getLogger(phpFrameAnalyzer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_analyze_btnActionPerformed

    private void saveFile_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveFile_btnActionPerformed
        // TODO add your handling code here:
        if  ("".equals(jTextArea2.getText()))
            analyze_btn.setEnabled(false);
        
        else{
        JFileChooser saveHackFile = new JFileChooser();
        File hackFileToSave = new File(fileName+".out");
        saveHackFile.setSelectedFile(hackFileToSave);
        saveHackFile.setDialogTitle("Guardar archivo");   
        FileNameExtensionFilter filter = new FileNameExtensionFilter("OUT","out");
        saveHackFile.setFileFilter(filter);
        int userSelection = saveHackFile.showSaveDialog(saveHackFile);
 
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            hackFileToSave = saveHackFile.getSelectedFile();            
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(hackFileToSave)))
            {
                bw.write(jTextArea1.getText());
            } 
            catch (IOException ex) {     
                Logger.getLogger(phpFrameAnalyzer.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Save as file: " + hackFileToSave.getAbsolutePath());
        }
        }
    }//GEN-LAST:event_saveFile_btnActionPerformed

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
            java.util.logging.Logger.getLogger(phpFrameAnalyzer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(phpFrameAnalyzer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(phpFrameAnalyzer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(phpFrameAnalyzer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new phpFrameAnalyzer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton analyze_btn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JButton openFile_btn;
    private javax.swing.JButton saveFile_btn;
    // End of variables declaration//GEN-END:variables
}
