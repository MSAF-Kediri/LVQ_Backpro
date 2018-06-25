/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lvq_backpro;

import java.io.IOException;
import java.util.ArrayList;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

/**
 * BISMILLAHIRRAHMANIRRAHIIM
 *
 * @author MSAF
 */
public class LVQ {

    double[][] bobot;
    double error;
    double akurasi;
    double[][] hasil;
    ArrayList<Integer> logErrorPelatihan;
    DataManagement d;

    public LVQ(String path) {
        d = new DataManagement();
        d.setInputFile(path);
        try {
            d.read();
            d.normalisasi();
            int banyakFitur = d.namaAtribut.length - 1;
            int banyakKlas = (int) d.getDataTertinggi()[d.data[0].length - 1] + 1;
            // System.out.println(banyakFitur + " " + banyakKlas);
            bobot = new double[banyakKlas][banyakFitur];
//            inisialisasiBobotAwal();

        } catch (IOException ex) {
            Logger.getLogger(LVQ.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setPathData(String path) {
        this.d.setInputFile(path);
    }

    private void inisialisasiBobotAwal(DataManagement dataLatih) {
        Random r = new Random();
        int i = 0;
        while (i <= dataLatih.getDataTertinggi()[dataLatih.namaAtribut.length - 1]) {
            int random = r.nextInt(dataLatih.data.length - 1) + 1;
            //System.out.println(random);
            if (i == dataLatih.data[random][dataLatih.namaAtribut.length - 1]) {
                for (int j = 0; j < dataLatih.data[random].length - 1; j++) {
                    bobot[i][j] = dataLatih.data[random][j];

                }
                i++;
            }

        }

    }

    public void train(DataManagement d, int maxEpoh, double learningRate, double decLearningRate, double minLearningRate) {
        inisialisasiBobotAwal(d);
        //inisialisasi data latih
        double[][] dataLatih = new double[d.data.length - 1][d.namaAtribut.length - 1];
        //System.out.println(dataLatih.length +"  "+ dataLatih[0].length);
        for (int i = 0; i < dataLatih.length; i++) {
            for (int j = 0; j < dataLatih[i].length; j++) {
                dataLatih[i][j] = d.data[i + 1][j];
            }
        }
        //inisialisasi target
        double[] dataTarget = new double[d.data.length - 1];
        for (int i = 0; i < dataTarget.length; i++) {
            dataTarget[i] = d.data[i + 1][d.namaAtribut.length - 1];
        }

        logErrorPelatihan = new ArrayList<>();

        /*Proses Pelatihan*/
        int error;
        double[] jarakThdKlas = new double[(int) d.getDataTertinggi()[d.namaAtribut.length - 1] + 1];
        int epoh = 0;
        while (epoh < maxEpoh && learningRate > minLearningRate) {
            error = 0;
            for (int i = 0; i < dataLatih.length; i++) {
                for (int j = 0; j < jarakThdKlas.length; j++) {
                    jarakThdKlas[j] = hitungJarakEuclidean(dataLatih[i], bobot[j]);
                    //System.out.println(jarakThdKlas[j]);
                }
                int klasTerdekat = cariKlasTerdekat(jarakThdKlas);
                //System.out.println(klasTerdekat);

                /*Update Bobot*/
                if (klasTerdekat == dataTarget[i]) {
                    for (int j = 0; j < bobot[klasTerdekat].length; j++) {
                        bobot[klasTerdekat][j] = bobot[klasTerdekat][j] + learningRate * (dataLatih[i][j] - bobot[klasTerdekat][j]);
                    }
                } else {
                    error++;
                    for (int j = 0; j < bobot[klasTerdekat].length; j++) {
                        bobot[klasTerdekat][j] = bobot[klasTerdekat][j] - learningRate * (dataLatih[i][j] - bobot[klasTerdekat][j]);
                    }
                }

            }
            System.out.println("Epoh" + (epoh + 1) + "\n  Error = " + error);
            logErrorPelatihan.add(error);
            System.out.println("  learningRate = " + learningRate);
            learningRate = learningRate - (learningRate * decLearningRate);
            System.out.println(learningRate);
            epoh++;
        }

    }

    public void test(DataManagement d) {
//        //Normalisasi Data
//        d.normalisasi();
        //inisialisasi data latih
        double[][] dataUji = new double[d.data.length - 1][d.namaAtribut.length - 1];
        //System.out.println(dataLatih.length +"  "+ dataLatih[0].length);
        for (int i = 0; i < dataUji.length; i++) {
            for (int j = 0; j < dataUji[i].length; j++) {
                dataUji[i][j] = d.data[i + 1][j];
            }
        }
        this.hasil = new double[dataUji.length][2];
        //inisialisasi target
        double[] dataTarget = new double[d.data.length - 1];
        for (int i = 0; i < dataTarget.length; i++) {
            dataTarget[i] = d.data[i + 1][d.namaAtribut.length - 1];
        }

        int error = 0;
        double[] jarakThdKlas = new double[(int) d.getDataTertinggi()[d.namaAtribut.length - 1] + 1];
        for (int i = 0; i < dataUji.length; i++) {
            for (int j = 0; j < jarakThdKlas.length; j++) {
                jarakThdKlas[j] = hitungJarakEuclidean(dataUji[i], bobot[j]);
                //System.out.println(jarakThdKlas[j]);
            }

            int klasTerdekat = cariKlasTerdekat(jarakThdKlas);
            if (klasTerdekat != dataTarget[i]) {
                error++;
            }
            System.out.println(dataTarget[i] + "  " + klasTerdekat);
            this.hasil[i][0] = dataTarget[i];
            this.hasil[i][1] = klasTerdekat;
        }
        System.out.println("error = " + error);
        this.error = error;
        this.akurasi = (dataUji.length - this.error) / dataUji.length * 100;
    }

    private int cariKlasTerdekat(double[] d) {
        double min = d[0];
        int klas = 0;
        for (int i = 1; i < d.length; i++) {
            if (d[i] < min) {
                min = d[i];
                klas = i;
            }
        }
        return klas;
    }

    private double hitungJarakEuclidean(double[] input, double[] bobot) {
        double jarak = 0;
        for (int i = 0; i < bobot.length; i++) {
            jarak = jarak + Math.pow(input[i] - bobot[i], 2.0);
        }
        return Math.sqrt(jarak);
    }

    public void tampilkanGrafikPelatihan() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                double[] epoh = new double[logErrorPelatihan.size()];
                double[] error = new double[logErrorPelatihan.size()];
                for (int i = 0; i < epoh.length; i++) {
                    epoh[i] = i + 1;
                    error[i] = logErrorPelatihan.get(i);
                }

                double[][] data = new double[2][epoh.length];
                data[0] = epoh;
                data[1] = error;
                LineChart.showChart("Grafik Pelatihan", data, "Grafik", "Epoh", "Error");
            }
        });
    }

    public void tampilkanGrafikPengujian() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                double[] banyakData = new double[hasil.length];
                double[] dataTarget = new double[hasil.length];
                double[] prediksi = new double[hasil.length];
                for (int i = 0; i < banyakData.length; i++) {
                    banyakData[i] = i + 1;
                    dataTarget[i] = hasil[i][0];
                    prediksi[i] = hasil[i][1];
                }

                double[][] data = new double[2][banyakData.length];
                data[0] = banyakData;
                data[1] = dataTarget;
                double[][] data2 = new double[2][banyakData.length];
                data2[0] = banyakData;
                data2[1] = prediksi;
                LineChart.showChart2Line("Grafik Hasil Pengujian", data, "Data Aktual", data2, "Prediksi", "n-Data", "Kelas");
            }
        });
    }

    public static void main(String[] args) {
        LVQ lvq = new LVQ("SemuaData.xls");

        DataManagement dataL = new DataManagement();
        dataL.setInputFile("SemuaData.xls");
        DataManagement dataUji = new DataManagement();
        //dataUji.setInputFile("DataUji2.xls");
        dataUji.setInputFile("SemuaDataCopy.xls");

        try {
            dataL.read2();
            dataL.normalisasi();
            dataUji.read2();
            dataUji.normalisasi();
        } catch (IOException ex) {
            Logger.getLogger(LVQ.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 0; i < lvq.bobot.length; i++) {
            System.out.println("bobotMenujuKlas-" + i);
            for (int j = 0; j < lvq.bobot[i].length; j++) {
                System.out.println(lvq.bobot[i][j]);
            }
            System.out.println("");
        }

        lvq.train(dataL, 100, 0.1, 0.01, 0.0005);
        lvq.tampilkanGrafikPelatihan();

        System.out.println("Setelah di latih");
        for (int i = 0; i < lvq.bobot.length; i++) {
            System.out.println("bobotMenujuKlas-" + i);
            for (int j = 0; j < lvq.bobot[i].length; j++) {
                System.out.println(lvq.bobot[i][j]);
            }
            System.out.println("");
        }

        lvq.test(dataUji);
        System.out.println("Akurasi = " + lvq.akurasi);
        
        lvq.tampilkanGrafikPengujian();
        int i = 1;

        /*
         for (int i = 0; i < lvq.bobot.length; i++) {
         System.out.println("bobotMenujuKlas-" + i);
         for (int j = 0; j < lvq.bobot[i].length; j++) {
         System.out.println(lvq.bobot[i][j]);
         }
         System.out.println("");
         }
         double[] x = {4,0};
         double[] y = {0,3};
         System.out.println(lvq.jarakEuclidean(x, y));
         */
    }

}
