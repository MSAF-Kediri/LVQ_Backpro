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

/**
 * BISMILLAHIRRAHMANIRRAHIIM
 *
 * @author MSAF
 */
public class Backpropagation2 {

    DataManagement d;
    ArrayList<double[][]> bobot;
    int[] banyakLDanNeuron;

    public Backpropagation2(int[] nLnN) {
        d = new DataManagement();
        bobot = new ArrayList<>();
        banyakLDanNeuron = nLnN;
        d.setInputFile("DataLatih.xls");
        try {
            d.read();
            d.normalisasi();
        } catch (IOException ex) {
            Logger.getLogger(Backpropagation.class.getName()).log(Level.SEVERE, null, ex);
        }

        /*Pembentukan Layer dan Neuronnya*/
        for (int i = 0; i < nLnN.length; i++) {
            if (i == 0) {
                bobot.add(new double[nLnN[0]][d.namaAtribut.length]);
            } else {
                bobot.add(new double[nLnN[i]][nLnN[i - 1] + 1]);
            }
        }

        /*Inisialisasi bobot secara random*/
        Random r = new Random();
        for (int i = 0; i < bobot.size(); i++) {
            for (int j = 0; j < bobot.get(i).length; j++) {
                for (int k = 0; k < bobot.get(i)[j].length; k++) {
                    bobot.get(i)[j][k] = -1 + (1 + 1) * r.nextDouble();
                }

            }
        }
    }

    public void train(DataManagement d, int maxEpoh, double learningRate, double targetError) {
        //inisialisasi data latih
        double[][] dataLatih = new double[d.data.length - 1][d.namaAtribut.length];
        //System.out.println(dataLatih.length + "  " + dataLatih[0].length);
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
            // System.out.println(dataTarget[i]);
        }

        /*Proses Pelatihan*/
        double rmse = 1;
        int epoh = 0;
        while (epoh < maxEpoh && rmse > targetError) {
            double totalError = 999;
            for (int i = 0; i < dataLatih.length; i++) {
                ArrayList<double[]> signalInPerNeuron = new ArrayList<>();
                double error = 0;
                /*Langkah Maju*/
                for (int j = 0; j < bobot.size(); j++) {
                    double[] signalIn;
                    if (j == bobot.size() - 1) {
                        signalIn = new double[bobot.get(j).length];
                        System.out.println("Layer output neuronnya " + signalIn.length);
                    } else {
                        signalIn = new double[bobot.get(j).length + 1];
                    }
                    for (int k = 0; k < bobot.get(j).length; k++) {
                        for (int l = 0; l < bobot.get(j)[k].length; l++) {
                            if (j == 0) {
                                signalIn[k] = hitungSignal(dataLatih[i], bobot.get(j)[k], 0);
                            } else if (j == bobot.size() - 1) {
                                //untuk output layer
                                signalIn[k] = hitungSignal(signalInPerNeuron.get(j - 1), bobot.get(j)[k], 2);
                            } else {
                                signalIn[k] = hitungSignal(signalInPerNeuron.get(j - 1), bobot.get(j)[k], 0);
                            }
                        }
                        if (j != bobot.size() - 1) {
                            signalIn[signalIn.length - 1] = 1;
                        }
                        System.out.println(signalIn[k]);

                    }
                    if (j != bobot.size() - 1) {
                        System.out.println(signalIn[signalIn.length - 1]);
                    }
                    signalInPerNeuron.add(signalIn);

                    System.out.println("  " + signalInPerNeuron.size());
                }
//                for (int j = 0; j < signalInPerNeuron.size(); j++) {
//                    System.out.println("");
//                    for (int k = 0; k < signalInPerNeuron.get(j).length; k++) {
//                        System.out.println(signalInPerNeuron.get(j)[k]);
//                    }
//                }

                /*Langkah Mundur*/
                ArrayList<double[]> koreksiBobotperNeuron = new ArrayList<>();
                System.out.println("\nLangkah Mundur");
                for (int j = signalInPerNeuron.size() - 1; j > -1; j--) {
                    //System.out.println("");
                    double[] perbaikanBobot;
                    for (int k = 0; k < signalInPerNeuron.get(j).length; k++) {
                        //System.out.println(signalInPerNeuron.get(j)[k]);
                        if (j == signalInPerNeuron.size() - 1) {
                            perbaikanBobot = new double[signalInPerNeuron.get(j - 1).length];
                            error = dataTarget[i] - signalInPerNeuron.get(j)[k];
                            System.out.println("Error " + error);
                            for (int l = 0; l < bobot.get(j).length; l++) {
                                for (int m = 0; m < bobot.get(j)[l].length; m++) {
                                    perbaikanBobot[m] = learningRate * error * signalInPerNeuron.get(j - 1)[m];
                                    System.out.println(perbaikanBobot[m]);
                                }
                                koreksiBobotperNeuron.add(perbaikanBobot);
                            }

                        } else {
                            if (j != 0) {
                                System.out.println(signalInPerNeuron.get(j-1).length);
                                perbaikanBobot = new double[signalInPerNeuron.get(j-1).length];
                            }
                            //perbaikanBobot = new double[bobot];
                            //System.out.println(signalInPerNeuron.get(j).length);
                        }
                    }
                }

                //System.out.println("data-" + (i + 1));
                i = 11;
            }

            epoh++;
        }

    }

    private double hitungSignal(double[] a, double[] b, int jenisAktivasi) {
        double signal = 0;
        for (int i = 0; i < b.length; i++) {
            signal = signal + (a[i] * b[i]);
        }
        signal = fungsiAktivasi(signal, jenisAktivasi);
        return signal;
    }

    private double fungsiAktivasi(double zIn, int type) {
        double hasil = 0;
        switch (type) {
            case 0:
                /*sigmoid biner*/
                hasil = (1.0 / (1.0 + Math.exp(-zIn)));
                break;
            case 1:
                hasil = 0;
                break;
            case 2:
                /*Pembulatan*/
                hasil = Math.round(zIn);
                break;
            default:
                /*identitas*/
                hasil = zIn;

        }
        return hasil;

    }

    public static void main(String[] args) {
        int[] nHnN = {4, 3, 1};
        //int[] nHnN = {4, 3, 5, 1};
        Backpropagation2 b = new Backpropagation2(nHnN);

        //print jaringgan
        for (int i = 0; i < b.bobot.size(); i++) {
            for (int j = 0; j < b.bobot.get(i).length; j++) {
                System.out.println("------" + j);
                for (int k = 0; k < b.bobot.get(i)[j].length; k++) {
                    System.out.println(b.bobot.get(i)[j][k]);
                }

            }
            System.out.println("");
        }

        //train
        b.train(b.d, 1, 0.1, 0);
    }

//    public static void main(String[] args) {
//        ArrayList<double[][]> d = new ArrayList<>();
//        double [][] bobot = {{2,5},{3,7}};
//        for (int i = 0; i < bobot.length; i++) {
//            for (int j = 0; j < bobot[i].length; j++) {
//                System.out.println(bobot[i][j]);
//            }
//            System.out.println("-----");
//        }
//        
//        d.add(bobot);
//        
//        
//        for (int i = 0; i < d.size(); i++) {
//            for (int j = 0; j < d.get(i).length; j++) {
//                for (int k = 0; k < d.get(i)[j].length; k++) {
//                    System.out.println(d.get(i)[j][k]);
//                }
//                System.out.println("");
//            }
//            
//        }
//        
//        double n = 0.44444;
//        System.out.println(Math.round(n));
//    }
}
