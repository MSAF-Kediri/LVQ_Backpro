/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lvq_backpro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import jxl.Sheet;
import jxl.Workbook;

/**
 *
 * @author ACER
 */
public class FrameLvq extends javax.swing.JFrame {

    /**
     * Creates new form FrameData
     */
    public FrameLvq() {
        initComponents();
        fileChooser.setFileFilter(new FileNameExtensionFilter("MS Excel Documents(*.xls, *.xlsx)", "xls", "xlsx"));
        progressBar.setVisible(false);
        path = FrameDepan.path;

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileChooser = new javax.swing.JFileChooser();
        jFrame1 = new javax.swing.JFrame();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        hasilTable = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        errorTextField = new javax.swing.JTextField();
        masukPengujianButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        epohTextField = new javax.swing.JTextField();
        learningRateTextField = new javax.swing.JTextField();
        decLearningRtTextField = new javax.swing.JTextField();
        minLearningRtTextField = new javax.swing.JTextField();
        pilihDLatihButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        dataLatihTable = new javax.swing.JTable();
        trainButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        progressBar = new javax.swing.JProgressBar();

        fileChooser.setCurrentDirectory(new java.io.File("C:\\Users\\ACER\\Documents\\NetBeansProjects\\LVQ_Backpro"));
        fileChooser.setDialogTitle("Pilih Data");

        jFrame1.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Hasil Pelatihan");

        hasilTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(hasilTable);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Error");

        errorTextField.setEditable(false);
        errorTextField.setBackground(new java.awt.Color(255, 255, 255));

        masukPengujianButton.setText("Masuk Pengujian");
        masukPengujianButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                masukPengujianButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jFrame1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(errorTextField))
                    .addGroup(jFrame1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(masukPengujianButton, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE))
                .addContainerGap())
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(errorTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(masukPengujianButton)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("LVQ Pelatihan");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("PELATIHAN LVQ");

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Banyak Epoh");

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Learning Rate");

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Pengurangan Learning Rate");

        jLabel5.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Minimum Learning Rate ");

        epohTextField.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        epohTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                epohTextFieldActionPerformed(evt);
            }
        });
        epohTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                epohTextFieldKeyTyped(evt);
            }
        });

        learningRateTextField.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        learningRateTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                learningRateTextFieldKeyTyped(evt);
            }
        });

        decLearningRtTextField.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        decLearningRtTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                decLearningRtTextFieldKeyTyped(evt);
            }
        });

        minLearningRtTextField.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        minLearningRtTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                minLearningRtTextFieldKeyTyped(evt);
            }
        });

        pilihDLatihButton.setText("Pilih Data");
        pilihDLatihButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pilihDLatihButtonActionPerformed(evt);
            }
        });

        dataLatihTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        dataLatihTable.setEnabled(false);
        jScrollPane1.setViewportView(dataLatihTable);

        trainButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        trainButton.setForeground(new java.awt.Color(51, 255, 51));
        trainButton.setText("Latih");
        trainButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trainButtonActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Data Latih");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pilihDLatihButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(61, 61, 61)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(decLearningRtTextField, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(learningRateTextField, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(epohTextField, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(minLearningRtTextField)))
                            .addComponent(trainButton, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33))
                    .addComponent(progressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pilihDLatihButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(epohTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(learningRateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(decLearningRtTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(minLearningRtTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(trainButton, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {decLearningRtTextField, epohTextField, learningRateTextField, minLearningRtTextField});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel2, jLabel3, jLabel4, jLabel5});

        setSize(new java.awt.Dimension(756, 695));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void pilihDLatihButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pilihDLatihButtonActionPerformed
        // TODO add your handling code here:

        int returnVal = fileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            excelFile = fileChooser.getSelectedFile();
            try {
                // Tampikan data
                muatData(excelFile);

                dataLatih = new DataManagement();
                dataLatih.setInputFile(excelFile.toString());
                dataLatih.read2();
                dataLatih.normalisasi();
            } catch (Exception ex) {
                System.out.println("Terdapat masalah dalam akses file " + excelFile.getAbsolutePath());
            }
        } else {
            System.out.println("Akses file dibatalkan");
        }

    }//GEN-LAST:event_pilihDLatihButtonActionPerformed

    private void trainButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trainButtonActionPerformed
        try {
            lvq = new LVQ(path);
            progressBar.setValue(0);
            int banyakEpoh = Integer.valueOf(epohTextField.getText());
            double learningR = Double.valueOf(learningRateTextField.getText());
            double decLearningR = Double.valueOf(decLearningRtTextField.getText());
            double minLearningR = Double.valueOf(minLearningRtTextField.getText());
            lvq.train(dataLatih, banyakEpoh, learningR, decLearningR, minLearningR);

            hasilPelatihan();

        } catch (Exception ex) {

        }
    }//GEN-LAST:event_trainButtonActionPerformed

    private void epohTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_epohTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_epohTextFieldActionPerformed

    private void epohTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_epohTextFieldKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();

        if (!Character.isDigit(c)) {
            System.out.println(true);
            evt.consume();
        }


    }//GEN-LAST:event_epohTextFieldKeyTyped

    private void learningRateTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_learningRateTextFieldKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();

        if (!(c == '.')) {
            if (!Character.isDigit(c)) {
                System.out.println(true);
                evt.consume();
            }

        }
    }//GEN-LAST:event_learningRateTextFieldKeyTyped

    private void decLearningRtTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_decLearningRtTextFieldKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();

        if (!(c == '.')) {
            if (!Character.isDigit(c)) {
                System.out.println(true);
                evt.consume();
            }

        }
    }//GEN-LAST:event_decLearningRtTextFieldKeyTyped

    private void minLearningRtTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_minLearningRtTextFieldKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();

        if (!(c == '.')) {
            if (!Character.isDigit(c)) {
                System.out.println(true);
                evt.consume();
            }

        }
    }//GEN-LAST:event_minLearningRtTextFieldKeyTyped

    private void masukPengujianButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_masukPengujianButtonActionPerformed
        // TODO add your handling code here:
        lvqUji = new FrameLvqUji();
        lvqUji.path = path;
        lvqUji.lvq = lvq;
        lvqUji.setVisible(true);
    }//GEN-LAST:event_masukPengujianButtonActionPerformed

    private void muatData(File file) {
        File excelFile = file;

        // buat model untuk file excel
        if (excelFile.exists()) {
            try {
                Workbook workbook = Workbook.getWorkbook(excelFile);
                Sheet sheet = workbook.getSheets()[0];

                TableModel model = new DefaultTableModel(sheet.getRows(), sheet.getColumns());
                for (int row = 0; row < sheet.getRows(); row++) {
                    for (int column = 0; column < sheet.getColumns(); column++) {
                        String content = sheet.getCell(column, row).getContents();
                        model.setValueAt(content, row, column);
                    }
                }

                dataLatihTable.setModel(model);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e);
            }

        } else {
            JOptionPane.showMessageDialog(null, "File does not exist");
        }
    }

    private void hasilPelatihan() {
        errorTextField.setText(lvq.logErrorPelatihan.get(lvq.logErrorPelatihan.size() - 1).toString());
        TableModel model = new DefaultTableModel(lvq.logErrorPelatihan.size(), 2);
        for (int i = 0; i < model.getRowCount(); i++) {
            model.setValueAt(i + 1, i, 0);
            for (int j = 0; j < model.getColumnCount(); j++) {
                model.setValueAt(lvq.logErrorPelatihan.get(i), i, 1);
            }
        }
        hasilTable.setModel(model);
        jFrame1.pack();
        jFrame1.setVisible(true);
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
            java.util.logging.Logger.getLogger(FrameLvq.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameLvq.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameLvq.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameLvq.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameLvq().setVisible(true);
            }
        });
    }

    File excelFile = null;
    LVQ lvq;
    String path;
    DataManagement dataLatih;
    FrameLvqUji lvqUji;
    Timer timer;
    ActionListener action;
    public FrameDepan frDepan;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable dataLatihTable;
    private javax.swing.JTextField decLearningRtTextField;
    private javax.swing.JTextField epohTextField;
    private javax.swing.JTextField errorTextField;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JTable hasilTable;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextField learningRateTextField;
    private javax.swing.JButton masukPengujianButton;
    private javax.swing.JTextField minLearningRtTextField;
    private javax.swing.JButton pilihDLatihButton;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JButton trainButton;
    // End of variables declaration//GEN-END:variables

}