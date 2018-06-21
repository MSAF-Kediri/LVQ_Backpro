/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lvq_backpro;

import java.io.IOException;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * BISMILLAHIRRAHMANIRRAHIIM
 *
 * @author MSAF
 */
public class LVQ {

    double[][] bobot;
    DataManagement d;

    public LVQ() {
        d = new DataManagement();
        d.setInputFile("DataLatih.xls");
        try {
            d.read();
            d.normalisasi();
            int banyakFitur = d.namaAtribut.length - 1;
            int banyakKlas = (int) d.getDataTertinggi()[d.data[0].length - 1] + 1;
            System.out.println(banyakFitur + " " + banyakKlas);
            bobot = new double[banyakKlas][banyakFitur];
            inisialisasiBobotAwal();

        } catch (IOException ex) {
            Logger.getLogger(LVQ.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void inisialisasiBobotAwal() {
        Random r = new Random();
        int i = 0;
        while (i <= d.getDataTertinggi()[d.namaAtribut.length - 1]) {
            int random = r.nextInt(d.data.length - 1) + 1;
            //System.out.println(random);
            if (i == d.data[random][d.namaAtribut.length - 1]) {
                for (int j = 0; j < d.data[random].length - 1; j++) {
                    bobot[i][j] = d.data[random][j];

                }
                i++;
            }

        }

    }

    public void train(DataManagement d, int maxEpoh, double learningRate, double decLearningRate, double minLearningRate) {
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
            System.out.println("  learningRate = " + learningRate);
            learningRate = learningRate - (learningRate * decLearningRate);
            System.out.println(learningRate);
            epoh++;
        }

    }

    public void test(DataManagement d) {
        //inisialisasi data latih
        double[][] dataUji = new double[d.data.length - 1][d.namaAtribut.length - 1];
        //System.out.println(dataLatih.length +"  "+ dataLatih[0].length);
        for (int i = 0; i < dataUji.length; i++) {
            for (int j = 0; j < dataUji[i].length; j++) {
                dataUji[i][j] = d.data[i + 1][j];
            }
        }
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
            System.out.println(klasTerdekat + "  " +dataTarget[i]);

        }
        System.out.println("error = " + error);
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

    public static void main(String[] args) {
        LVQ lvq = new LVQ();
        for (int i = 0; i < lvq.bobot.length; i++) {
            System.out.println("bobotMenujuKlas-" + i);
            for (int j = 0; j < lvq.bobot[i].length; j++) {
                System.out.println(lvq.bobot[i][j]);
            }
            System.out.println("");
        }

        lvq.train(lvq.d, 1000, 0.1, 0.01, 0.0005);

        System.out.println("Setelah di latih");
        for (int i = 0; i < lvq.bobot.length; i++) {
            System.out.println("bobotMenujuKlas-" + i);
            for (int j = 0; j < lvq.bobot[i].length; j++) {
                System.out.println(lvq.bobot[i][j]);
            }
            System.out.println("");
        }
        
        lvq.test(lvq.d);

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
