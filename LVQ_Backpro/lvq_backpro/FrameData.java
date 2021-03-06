/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lvq_backpro;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import jxl.Sheet;
import jxl.Workbook;

/**
 *
 * @author ACER
 */
public class FrameData extends javax.swing.JFrame {

    /**
     * Creates new form FrameData
     *
     * @param data
     */
    public FrameData() {
        initComponents();
        //fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("PDF Documents", "pdf"));
        fileChooser.setFileFilter(new FileNameExtensionFilter("MS Excel Documents(*.xls, *.xlsx)", "xls", "xlsx"));
        
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
        pilihData = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        okBtn = new javax.swing.JButton();

        fileChooser.setCurrentDirectory(new java.io.File("C:\\Users\\ACER\\Documents\\NetBeansProjects\\LVQ_Backpro"));
        fileChooser.setDialogTitle("Pilih Data");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pilihData.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        pilihData.setText("Pilih Data");
        pilihData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pilihDataActionPerformed(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(table);

        okBtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        okBtn.setText("OK");
        okBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 754, Short.MAX_VALUE)
                    .addComponent(pilihData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(okBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pilihData)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(okBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(790, 483));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void pilihDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pilihDataActionPerformed
        // TODO add your handling code here:
        int returnVal = fileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            excelFile = fileChooser.getSelectedFile();
            try {
                // Tampikan data
                muatData(excelFile);

            } catch (Exception ex) {
                System.out.println("Terdapat masalah dalam akses file " + excelFile.getAbsolutePath());
            }
        } else {
            System.out.println("Akses file dibatalkan");
        }
    }//GEN-LAST:event_pilihDataActionPerformed

    private void okBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okBtnActionPerformed
        // TODO add your handling code here:
        if (excelFile == null) {
            JOptionPane.showMessageDialog(new JFrame(), "Harap Pilih Data !!!!!");
        } else {
            if (FrameDepan.s == null) {
                FrameDepan.s = "Data terisi";
            } else {
                String string = " lagi ...";
                FrameDepan.s += string;
            }
            System.out.println(FrameDepan.s);
            System.out.println(excelFile.toString());
            FrameDepan.path = excelFile.toString();
            FrameDepan.data.setInputFile(excelFile.toString());
            this.dispose();
        }


    }//GEN-LAST:event_okBtnActionPerformed

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

                table.setModel(model);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e);
            }

        } else {
            JOptionPane.showMessageDialog(null, "File does not exist");
        }
    }

    //variables
    public FrameDepan frDepan = null;
    File excelFile = null;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton okBtn;
    private javax.swing.JButton pilihData;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables

}
