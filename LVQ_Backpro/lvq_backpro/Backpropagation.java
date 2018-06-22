/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lvq_backpro;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * BISMILLAHIRRAHMANIRRAHIIM
 *
 * @author MSAF
 */
public class Backpropagation {

    DataManagement d;
    double[][] bobot;
    double[][][] bobot2;
    int[] banyakHidDanNeuron;
    int banyakNeuronAktifPerhitungan;

    public Backpropagation(int[] nHnN) {
        banyakHidDanNeuron = nHnN;
        banyakNeuronAktifPerhitungan = 0;
        for (int i : nHnN) {
            banyakNeuronAktifPerhitungan += i;
        }
        banyakNeuronAktifPerhitungan += 1;
        int banyakHiddenLayer = nHnN.length;
        d = new DataManagement();
        d.setInputFile("DataLatih.xls");
        try {
            d.read();
            d.normalisasi();
        } catch (IOException ex) {
            Logger.getLogger(Backpropagation.class.getName()).log(Level.SEVERE, null, ex);
        }

        bobot = new double[nHnN[0]][d.namaAtribut.length];
        System.out.println(bobot.length + "  " + bobot[0].length);
        bobot2 = new double[banyakHiddenLayer][10][10];
        System.out.println(bobot2.length + "  " + bobot2[0].length + "  " + bobot2[0][0].length);

        /*Inisialisasi bobot secara random*/
        Random r = new Random();
        int countHid = 0;
        for (int i = 0; i < bobot.length; i++) {
            for (int j = 0; j < bobot[i].length; j++) {
                bobot[i][j] = -1 + (1 + 1) * r.nextDouble();
            }
        }
        for (int i = 0; i < bobot2.length; i++) {
            if (countHid == banyakHiddenLayer - 1) {
                for (int j = 0; j < banyakHidDanNeuron[countHid] + 1; j++) {
                    bobot2[i][0][j] = -1 + (1 + 1) * r.nextDouble();
                }
            } else {
                for (int j = 0; j < banyakHidDanNeuron[countHid + 1]; j++) {
                    for (int k = 0; k < banyakHidDanNeuron[countHid] + 1; k++) {
                        bobot2[i][j][k] = -1 + (1 + 1) * r.nextDouble();
                    }
                }
                //System.out.println(countHid);
                countHid++;
            }
        }
    }

    public void train(DataManagement d, int maxEpoh, double learningRate, double targetError) {
        //inisialisasi data latih
        double[][] dataLatih = new double[d.data.length - 1][d.namaAtribut.length];
        System.out.println(dataLatih.length + "  " + dataLatih[0].length);
        for (int i = 0; i < dataLatih.length; i++) {
            for (int j = 0; j < dataLatih[i].length; j++) {
                dataLatih[i][j] = d.data[i + 1][j];
                if (j == 10) {
                    dataLatih[i][j] = 1;
                }
                //System.out.println(dataLatih[i][j]);
            }
            //System.out.println("");
        }

        //inisialisasi target
        double[] dataTarget = new double[d.data.length - 1];
        for (int i = 0; i < dataTarget.length; i++) {
            dataTarget[i] = d.data[i + 1][d.namaAtribut.length - 1];
            //System.out.println(dataTarget[i]);
        }

        /*Proses Pelatihan*/
        double rmse = 1;
        int epoh = 0;
        double[] signalIn = new double[banyakNeuronAktifPerhitungan];
        int countN;
        int nLayer = banyakHidDanNeuron.length + 1;
        int countL;
        while (epoh < maxEpoh && rmse > targetError) {

            for (int i = 0; i < dataLatih.length; i++) {
                double[] dataTemp;
                countN = 0;
                countL = 0;
                /*Langkah Maju*/
                if (countL == 0) {
                    for (int j = 0; j < 1; j++) {
                        for (int k = 0; k < banyakHidDanNeuron[0]; k++) {
                            signalIn[countN] = hitungSignal(dataLatih[i], bobot[k]);
                            System.out.println(signalIn[countN]);
                            countN++;
                        }
                    }
                    double[] bias = {1};
                    dataTemp = new double[countN + bias.length];
                    int count = 0;
                    System.out.println("");
                    for (int j = 0; j < countN; j++) {
                        dataTemp[j] = signalIn[j];
                        System.out.println(dataTemp[j]);
                        count++;
                    }
                    for (int j = 0; j < bias.length; j++) {
                        dataTemp[count] = bias[j];
                        System.out.println(dataTemp[count]);
                        count++;
                    }
                    countL++;
               Stop Pusing Istirahat dulu;
               System.out.println("");
                } else {
//                    int banyakHiddenLayer = banyakHidDanNeuron.length;
//                    int countHid = 0;
//                    for (int a = 0; a < bobot2.length; a++) {
//                        if (countHid == banyakHiddenLayer - 1) {
//                            for (int j = 0; j < banyakHidDanNeuron[countHid] + 1; j++) {
//                                System.out.println(bobot2[a][0][j]);
//                            }
//                            System.out.println("");
//                        } else {
//                            for (int j = 0; j < banyakHidDanNeuron[countHid + 1]; j++) {
//                                for (int k = 0; k < banyakHidDanNeuron[countHid] + 1; k++) {
//                                    System.out.println(bobot2[a][j][k]);
//
//                                }
//                                System.out.println("");
//                            }
//                            System.out.println("");
//                            //System.out.println(countHid);
//                            countHid++;
//                        }
//                    }

                }

            }

            epoh++;
        }
    }

    private double hitungSignal(double[] a, double[] b) {
        double signal = 0;
        for (int i = 0; i < b.length; i++) {
            signal = signal + (a[i] * b[i]);
        }
        signal = fungsiAktifasi(signal);
        return signal;
    }

    private double fungsiAktifasi(double zIn) {
        /*bipolar*/
        double hasil = (1.0 / (1.0 + Math.exp(-zIn)));
        return hasil;
    }

    public void test() {

    }

    public static void main(String[] args) {
        //int[] nueronDalamSetiapHiddenL = {4,3, 2, 4};
        int[] nueronDalamSetiapHiddenL = {4, 3};
        Backpropagation back = new Backpropagation(nueronDalamSetiapHiddenL);

//        int banyakHiddenLayer = nueronDalamSetiapHiddenL.length;
//        int countHid = 0;
//        for (int i = 0; i < back.bobot2.length; i++) {
//            if (countHid == banyakHiddenLayer - 1) {
//                for (int j = 0; j < back.banyakHidDanNeuron[countHid] + 1; j++) {
//                    System.out.println(back.bobot2[i][0][j]);
//                }
//                System.out.println("");
//            } else {
//                for (int j = 0; j < back.banyakHidDanNeuron[countHid + 1]; j++) {
//                    for (int k = 0; k < back.banyakHidDanNeuron[countHid] + 1; k++) {
//                        System.out.println(back.bobot2[i][j][k]);
//
//                    }
//                    System.out.println("");
//                }
//                System.out.println("");
//                //System.out.println(countHid);
//                countHid++;
//            }
//        }
        back.train(back.d, 1, 0, 0);

        /*for (int i = 0; i < back.bobot.length; i++) {
         System.out.println("bobotKeNeuron-" + (i + 1));
         for (int j = 0; j < back.bobot[i].length; j++) {
         System.out.print(i + "" + j + "" + " = ");
         System.out.println(back.bobot[i][j]);
         }
         System.out.println("");
         }
         System.out.println("---------");
         int banyakHiddenLayer = nueronDalamSetiapHiddenL.length;
         int countHid = 1;
         for (int i = 0; i < back.bobot2.length; i++) {

         for (int j = 0; j < back.bobot2[0].length; j++) {

         for (int k = 0; k < back.bobot2[0][0].length; k++) {

         if (back.bobot2[i][j][k] != 0) {
         System.out.print(i + "" + j + "" + k + " = ");
         System.out.println(back.bobot2[i][j][k]);
         }

         }
         System.out.println("");
         }
         System.out.println("");
         }*/
    }
}
