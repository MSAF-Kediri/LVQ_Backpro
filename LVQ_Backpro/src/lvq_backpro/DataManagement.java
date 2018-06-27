package lvq_backpro;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * BISMILLAHIRRAHMANIRRAHIIM
 *
 * @author MSAF
 */
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import jxl.CellView;
import jxl.WorkbookSettings;
import jxl.format.UnderlineStyle;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class DataManagement {

    public double data[][];
    public String namaAtribut[];
    private static double dataTertinggi[];
    private static double dataTerendah[];
    private String inputFile;
    private String outputFile;

    private WritableCellFormat timesBoldUnderline;
    private WritableCellFormat times;

    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    public void setOutputFile(String outputFile) {
        this.outputFile = outputFile;
    }

    public void write() throws IOException, WriteException {
        File file = new File(outputFile);
        WorkbookSettings wbSettings = new WorkbookSettings();
        try {
            wbSettings.setLocale(new Locale("en", "EN"));
            WritableWorkbook workbook = Workbook.createWorkbook(file, wbSettings);
            workbook.createSheet("Report", 0);
            WritableSheet excelSheet = workbook.getSheet(0);
            createLabel(excelSheet);
            createContent(excelSheet);
            workbook.write();
            workbook.close();
        } catch (Exception e) {
            System.out.println("file masih dibuka tak dapat disimpan");
        }
    }

    private void createLabel(WritableSheet sheet)
            throws WriteException {
        // Lets create a times font
        WritableFont times10pt = new WritableFont(WritableFont.ARIAL, 10);
        // Define the cell format
        times = new WritableCellFormat(times10pt);
        // Lets automatically wrap the cells
        times.setWrap(false);

        // create create a bold font with unterlines
        WritableFont times10ptBoldUnderline = new WritableFont(
                WritableFont.ARIAL, 10, WritableFont.BOLD, false,
                UnderlineStyle.NO_UNDERLINE);
        timesBoldUnderline = new WritableCellFormat(times10ptBoldUnderline);
        // Lets automatically wrap the cells
        timesBoldUnderline.setWrap(true);

        CellView cv = new CellView();
        //cv.setFormat(times);
        cv.setFormat(timesBoldUnderline);
        cv.setAutosize(false);

        // Write a few headers
        for (int i = 0; i < namaAtribut.length; i++) {
            addCaption(sheet, i, 0, namaAtribut[i]);
        }

    }

    private void createContent(WritableSheet sheet) throws WriteException,
            RowsExceededException {
        // Write a few number
        for (int i = 1; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                addNumber(sheet, j, i, data[i][j]);
            }
        }
    }

    private void addCaption(WritableSheet sheet, int column, int row, String s)
            throws RowsExceededException, WriteException {
        Label label;
        label = new Label(column, row, s, timesBoldUnderline);
        sheet.addCell(label);
    }

    private void addNumber(WritableSheet sheet, int column, int row,
            Double dou) throws WriteException, RowsExceededException {
        Number number;
        number = new Number(column, row, dou, times);
        sheet.addCell(number);
    }

    private void addLabel(WritableSheet sheet, int column, int row, String s)
            throws WriteException, RowsExceededException {
        Label label;
        label = new Label(column, row, s, times);
        sheet.addCell(label);
    }

    //Read Tanpa set nilai max dan min
    public void read2() throws IOException {
        File inputWorkbook = new File(inputFile);
        Workbook w;
        try {
            w = Workbook.getWorkbook(inputWorkbook);
            // Get the first sheet
            Sheet sheet = w.getSheet(0);

            namaAtribut = new String[sheet.getColumns()];

            //Catatan baris ke-0 dianggap tak digunakan
            data = new double[sheet.getRows()][sheet.getColumns()];

            // Loop over first 10 column and lines
            for (int j = 0; j < sheet.getColumns(); j++) {

                for (int i = 0; i < sheet.getRows(); i++) {
                    Cell cell = sheet.getCell(j, i);
                    CellType type = cell.getType();
                    if (type == CellType.LABEL) {
                        namaAtribut[j] = cell.getContents();
                    }
                    if (type == CellType.NUMBER) {
                        data[i][j] = Double.parseDouble(cell.getContents());

                    }

                }
            }

        } catch (BiffException e) {
            e.printStackTrace();
        }

    }

    public void read() throws IOException {

        File inputWorkbook = new File(inputFile);
        Workbook w;
        try {
            w = Workbook.getWorkbook(inputWorkbook);
            // Get the first sheet
            Sheet sheet = w.getSheet(0);

            namaAtribut = new String[sheet.getColumns()];
            dataTertinggi = new double[sheet.getColumns()];
            dataTerendah = new double[sheet.getColumns()];

            //Catatan baris ke-0 dianggap tak digunakan
            data = new double[sheet.getRows()][sheet.getColumns()];

            // Loop over first 10 column and lines
            for (int j = 0; j < sheet.getColumns(); j++) {
                dataTertinggi[j] = -99999.0;
                dataTerendah[j] = 99999.0;
                for (int i = 0; i < sheet.getRows(); i++) {
                    Cell cell = sheet.getCell(j, i);
                    CellType type = cell.getType();
                    if (type == CellType.LABEL) {
                        namaAtribut[j] = cell.getContents();
                    }
                    if (type == CellType.NUMBER) {
                        data[i][j] = Double.parseDouble(cell.getContents());
                        if (dataTertinggi[j] < data[i][j]) {
                            dataTertinggi[j] = data[i][j];
                        }
                        if (dataTerendah[j] > data[i][j]) {
                            dataTerendah[j] = data[i][j];
                        }
                    }

                }
            }

        } catch (BiffException e) {
            e.printStackTrace();
        }

    }

    public double[] sortingAtributKe(int i) {
        double[] a;
        a = new double[data.length - 1];
        for (int j = 1; j <= a.length; j++) {
            a[j - 1] = data[j][i];
        }
        Arrays.sort(a);
        return a;
    }

    public double[][] getDataBarisKe(int i) {
        double[][] d = new double[1][data[i].length];
        for (int j = 0; j < data[i].length; j++) {
            d[0][j] = data[i][j];
        }
        return d;

    }

    public void normalisasi() {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length - 1; j++) {
                //Normal Normalisasi (0,1)
               data[i][j] = (data[i][j] - dataTerendah[j]) / (dataTertinggi[j] - dataTerendah[j]);
                //Modif (0.1-0.9)
               // data[i][j] = (data[i][j] - dataTerendah[j]) / (dataTertinggi[j] - dataTerendah[j]) * 0.8 + 0.1;
            }
        }
    }

    public static double[] getDataTertinggi() {
        return dataTertinggi;
    }

    public static double[] getDataTerendah() {
        return dataTerendah;
    }

    public static void main(String[] args) throws IOException, WriteException {
        DataManagement test = new DataManagement();
        DataManagement test2 = new DataManagement();
        test.setInputFile("C:\\Users\\ACER\\Documents\\NetBeansProjects\\LVQ_Backpro\\DataMaxMin.xls");
        test2.setInputFile("DataLatih.xls");
        test.setOutputFile("Data Ternormalisasi.xls");
        test.read();
        test2.read2();

        for (String namaAtribut : test2.namaAtribut) {
            System.out.format("%-24s", namaAtribut);
        }
        System.out.println();

        for (int i = 1; i < test2.data.length; i++) {
            for (int j = 0; j < test2.data[i].length; j++) {
                System.out.format("%-24.3f", test2.data[i][j]);
            }

            System.out.println();
        }
        System.out.println("");
        System.out.println("Nilai tertinggi");
        for (int i = 0; i < test.dataTertinggi.length; i++) {
            System.out.format("%-24.3f", test.dataTertinggi[i]);
        }
        System.out.println("\nNilai terendah");
        for (int i = 0; i < test.dataTerendah.length; i++) {
            System.out.format("%-24.3f", test.dataTerendah[i]);
        }
        System.out.println("\n");

        test2.normalisasi();
        System.out.println("Data Ternormalisasi");
        for (String namaAtribut : test2.namaAtribut) {
            System.out.format("%-24s", namaAtribut);
        }
        System.out.println();

        for (int i = 1; i < test2.data.length; i++) {
            for (int j = 0; j < test2.data[i].length; j++) {
                System.out.format("%-24.3f", test2.data[i][j]);
            }

            System.out.println();
        }

        //test.write();
        System.out.println("\n");

        test2.normalisasi();
        System.out.println("Data Ternormalisasi 2");
        for (String namaAtribut : test2.namaAtribut) {
            System.out.format("%-24s", namaAtribut);
        }
        System.out.println();

        for (int i = 1; i < test2.data.length; i++) {
            for (int j = 0; j < test2.data[i].length; j++) {
                System.out.format("%-24.3f", test2.data[i][j]);
            }

            System.out.println();
        }

        /* double[] d = test.sortingAtributKe(1);
         for (int i = 0; i < d.length; i++) {
         System.out.println(d[i]);
         }

         System.out.println();
         double[][] r = test.getDataBarisKe(1);
         for (int i = 0; i < r.length; i++) {
         for (int j = 0; j < r[i].length; j++) {
         System.out.println(r[i][j]);
         }
         }*/
    }

}
