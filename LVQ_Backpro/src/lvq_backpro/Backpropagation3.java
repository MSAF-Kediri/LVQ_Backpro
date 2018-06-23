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
public class Backpropagation3 {

    DataManagement d;
    ArrayList<double[][]> bobot;
    public int fungsiAktivasi;
    int[] banyakLDanNeuron;
    ArrayList<Double> logRMSE;
    ArrayList<Integer> logError;
    double[][] hasil;
    double error;
    double akurasi;

    public Backpropagation3(int[] nLnN, String path) {
        d = new DataManagement();
        bobot = new ArrayList<>();
        banyakLDanNeuron = nLnN;
        d.setInputFile(path);
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
                    bobot.get(i)[j][k] = 0 + (0 + 1) * r.nextDouble();
                }

            }
        }
    }

    public void train(DataManagement d, int maxEpoh, double learningRate, double targetError) {
        //Normalisasi Data
        d.normalisasi();
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

        logError = new ArrayList<>();
        logRMSE = new ArrayList<>();

        /*Proses Pelatihan*/
        double rmse = 1;
        int epoh = 0;
        int errorPerEpoh;
        while (epoh < maxEpoh && rmse > targetError) {
            double totalError = 0;
            errorPerEpoh = 0;
            for (int i = 0; i < dataLatih.length; i++) {
                ArrayList<double[]> signalInPerNeuron = new ArrayList<>();
                ArrayList<double[]> signalOutPerNeuron = new ArrayList<>();
                double error = 0;
                /*Langkah Maju*/
                for (int j = 0; j < bobot.size(); j++) {
                    double[] signalIn;
                    double[] signalOut;
                    if (j == bobot.size() - 1) {
                        signalIn = new double[bobot.get(j).length];
                        signalOut = new double[bobot.get(j).length];
                        System.out.println("Layer output neuronnya " + signalIn.length);
                    } else {
                        signalIn = new double[bobot.get(j).length + 1];
                        signalOut = new double[bobot.get(j).length + 1];
                    }
                    for (int k = 0; k < bobot.get(j).length; k++) {
                        for (int l = 0; l < bobot.get(j)[k].length; l++) {
                            if (j == 0) {
                                signalIn[k] = hitungSignal(dataLatih[i], bobot.get(j)[k]);
                                signalOut[k] = fungsiAktivasi(signalIn[k], fungsiAktivasi);
                            } else if (j == bobot.size() - 1) {
                                //untuk output layer
                                signalIn[k] = hitungSignal(signalOutPerNeuron.get(j - 1), bobot.get(j)[k]);
                                signalOut[k] = fungsiAktivasi(signalIn[k], 2);
                            } else {
                                signalIn[k] = hitungSignal(signalOutPerNeuron.get(j - 1), bobot.get(j)[k]);
                                signalOut[k] = fungsiAktivasi(signalIn[k], fungsiAktivasi);
                            }
                        }
                        if (j != bobot.size() - 1) {
                            signalIn[signalIn.length - 1] = 1;
                            signalOut[signalOut.length - 1] = 1;
                        }
                        System.out.println(signalIn[k] + "      " + signalOut[k]);

                    }
                    if (j != bobot.size() - 1) {
                        //   System.out.println(signalIn[signalIn.length - 1] + "                    " + signalOut[signalOut.length - 1]);
                    }
                    signalInPerNeuron.add(signalIn);
                    signalOutPerNeuron.add(signalOut);
                    // System.out.println("  " + signalInPerNeuron.size());
                }


                /*Langkah Mundur*/
                ArrayList<double[][]> bobotMundur = new ArrayList<>();
                for (int j = bobot.size() - 1; j > -1; j--) {
                    if (j != 0) {
                        //System.out.println(bobot.get(j-1).length +" "+ bobot.get(j).length);
                        bobotMundur.add(new double[bobot.get(j - 1).length + 1][bobot.get(j).length]);
                    } else {
                        // System.out.println(d.namaAtribut.length +" "+ bobot.get(j).length);
                        bobotMundur.add(new double[d.namaAtribut.length][bobot.get(j).length]);
                    }
                }

                int a = bobot.size() - 1;
                for (int j = 0; j < bobotMundur.size(); j++) {
                    for (int k = 0; k < bobotMundur.get(j).length; k++) {
                        //   System.out.println("------" + k);
                        for (int l = 0; l < bobotMundur.get(j)[k].length; l++) {
                            bobotMundur.get(j)[k][l] = bobot.get(a)[l][k];
                            //  System.out.println(j+" "+k+" "+l+" "+bobot.get(a)[l][k]);

                        }
                    }
                    a--;
                    //System.out.println("");
                }
                ArrayList<double[]> koreksiBobotperNeuron = new ArrayList<>();
                ArrayList<double[]> deltaIn = new ArrayList<>();
                double e = 0;
                int iii = 0;
//                System.out.println("\nLangkah Mundur");
                for (int j = bobot.size() - 1; j > -1; j--) {

                    double[] infError = new double[bobot.get(j).length];
//                    System.out.println("\n---------------Layer " + j);
                    for (int k = 0; k < bobot.get(j).length; k++) {
                        double[] korBot = new double[bobot.get(j)[k].length];
//                        System.out.println("\nNeuron-" + k);
                        for (int l = 0; l < bobot.get(j)[k].length; l++) {
                            if (j == bobot.size() - 1) {
                                e = dataTarget[i] - signalOutPerNeuron.get(j)[k];
                                //Untuk Menghitung RMSE
                                error = e * e;

                                infError[k] = e * fungsiTurunan(signalInPerNeuron.get(j)[k], 10);
                                korBot[l] = learningRate * infError[k] * signalOutPerNeuron.get(j - 1)[l];

//                                System.out.println("Error :" + e);
//                                System.out.println(k + " :" + infError[k]);
//                                System.out.println(l + " :" + korBot[l]);
                            } else if (j == 0) {
                                e = hitungSignal(deltaIn.get(signalOutPerNeuron.size() - 2 - j), bobotMundur.get(signalInPerNeuron.size() - 2 - j)[k]);

                                infError[k] = e * fungsiTurunan(signalInPerNeuron.get(j)[k], fungsiAktivasi);
                                korBot[l] = learningRate * infError[k] * dataLatih[i][l];

                            } else {
//                                System.out.println("index - " + deltaIn.get(signalOutPerNeuron.size() - 2 - j).length);
//                                System.out.println("bobot - " + bobotMundur.get(signalInPerNeuron.size() - 2 - j)[k].length);
                                e = hitungSignal(deltaIn.get(signalOutPerNeuron.size() - 2 - j), bobotMundur.get(signalInPerNeuron.size() - 2 - j)[k]);

                                infError[k] = e * fungsiTurunan(signalInPerNeuron.get(j)[k], fungsiAktivasi);
//                                System.out.println(infError[k]);
                                korBot[l] = learningRate * infError[k] * signalOutPerNeuron.get(j - 1)[l];

                            }
//                            System.out.println(korBot[l]);
                        }
//                        System.out.println(korBot.length);

                        koreksiBobotperNeuron.add(korBot);
                    }
                    deltaIn.add(infError);
                }
                if (e != 0) {
                    errorPerEpoh++;
                }

//                System.out.println("");
//                for (int j = 0; j < koreksiBobotperNeuron.size(); j++) {
////                    System.out.println("N " + j);
//                    for (int k = 0; k < koreksiBobotperNeuron.get(j).length; k++) {
////                        System.out.println(koreksiBobotperNeuron.get(j)[k]);
//                    }
//
//                }
                /*Perbarui Bobot*/
                int count = koreksiBobotperNeuron.size() - 1;
                for (int j = 0; j < bobot.size(); j++) {
                    for (int k = bobot.get(j).length - 1; k > -1; k--) {
                        for (int l = 0; l < bobot.get(j)[k].length; l++) {
                            bobot.get(j)[k][l] = bobot.get(j)[k][l] + koreksiBobotperNeuron.get(count)[l];
                        }
                        count--;
                    }
                }
                totalError += error;
                System.out.println("Error = " + totalError);
                //System.out.println(count);
                System.out.println("data-" + (i + 1));
                // i = 11222;
            }
            System.out.println("Wpoh -------------- " + epoh);
            totalError = totalError / dataLatih.length;
            //System.out.println(totalError);
            rmse = Math.sqrt(totalError);
            logRMSE.add(rmse);
            System.out.println("RMSE = " + rmse);
            System.out.println("Errorper Epoh = " + errorPerEpoh);
            logError.add(errorPerEpoh);
            epoh++;
        }

    }

    public void test(DataManagement d) {
        //Normalisasi Data
        d.normalisasi();
        //inisialisasi data uji
        double[][] dataUji = new double[d.data.length - 1][d.namaAtribut.length];
        //System.out.println(dataLatih.length + "  " + dataLatih[0].length);
        for (int i = 0; i < dataUji.length; i++) {
            for (int j = 0; j < dataUji[i].length; j++) {
                dataUji[i][j] = d.data[i + 1][j];
                if (j == 10) {
                    dataUji[i][j] = 1;
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

        this.hasil = new double[dataUji.length][2];
        this.hasil[0] = dataTarget;
        /*Proses Pengujian*/
        double rmse = 1;
        int epoh = 0;
        double[] nilai = new double[dataUji.length];
        double[] prediksi = new double[dataUji.length];

        double totalError = 0;
        int error = 0;
        for (int i = 0; i < dataUji.length; i++) {
            ArrayList<double[]> signalInPerNeuron = new ArrayList<>();
            ArrayList<double[]> signalOutPerNeuron = new ArrayList<>();
            

            /*Langkah Maju*/
            for (int j = 0; j < bobot.size(); j++) {
                double[] signalIn;
                double[] signalOut;
                if (j == bobot.size() - 1) {
                    signalIn = new double[bobot.get(j).length];
                    signalOut = new double[bobot.get(j).length];
                    // System.out.println("Layer output neuronnya " + signalIn.length);
                } else {
                    signalIn = new double[bobot.get(j).length + 1];
                    signalOut = new double[bobot.get(j).length + 1];
                }
                for (int k = 0; k < bobot.get(j).length; k++) {
                    for (int l = 0; l < bobot.get(j)[k].length; l++) {
                        if (j == 0) {
                            signalIn[k] = hitungSignal(dataUji[i], bobot.get(j)[k]);
                            signalOut[k] = fungsiAktivasi(signalIn[k], fungsiAktivasi);
                        } else if (j == bobot.size() - 1) {
                            //untuk output layer
                            signalIn[k] = hitungSignal(signalOutPerNeuron.get(j - 1), bobot.get(j)[k]);
                            signalOut[k] = fungsiAktivasi(signalIn[k], 2);

                            nilai[i] = signalIn[k];
                            prediksi[i] = signalOut[k];
                        } else {
                            signalIn[k] = hitungSignal(signalOutPerNeuron.get(j - 1), bobot.get(j)[k]);
                            signalOut[k] = fungsiAktivasi(signalIn[k], fungsiAktivasi);
                        }
                    }
                    if (j != bobot.size() - 1) {
                        signalIn[signalIn.length - 1] = 1;
                        signalOut[signalOut.length - 1] = 1;
                    }
                    // System.out.println(signalIn[k] + "      " + signalOut[k]);

                }
                if (j != bobot.size() - 1) {
                    //System.out.println(signalIn[signalIn.length - 1] + "                    " + signalOut[signalOut.length - 1]);
                }
                signalInPerNeuron.add(signalIn);
                signalOutPerNeuron.add(signalOut);
                //System.out.println("  " + signalInPerNeuron.size());
            }
            if (prediksi[i] != dataTarget[i]) {
                 error++;
            }
        }
        
        for (int i = 0; i < prediksi.length; i++) {
            System.out.println("nilai In = " + nilai[i]);
            System.out.println("Hasil Prediksi data-" + (i + 1) + " = " + prediksi[i]);
            System.out.println("");
        }
        System.out.println("Error = " + error);
        this.error = error;
        this.akurasi = (dataTarget.length - this.error)/dataTarget.length * 100;
        System.out.println("Akurasi =" + akurasi);
        this.hasil[1] = prediksi;
        

    }

    private double hitungSignal(double[] a, double[] b) {
        double signal = 0;
        for (int i = 0; i < b.length; i++) {
            signal = signal + (a[i] * b[i]);
        }

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
                hasil = (1 - Math.exp(-zIn)) / (1 + Math.exp(-zIn));

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

    private double fungsiTurunan(double zIn, int type) {
        double hasil = 0;
        switch (type) {
            case 0:
                /*turunan sigmoid biner*/
                double x = (1.0 / (1.0 + Math.exp(-zIn)));
                hasil = (x * (1 - x));
                break;
            case 1:
                double x2 = (1 - Math.exp(-zIn)) / (1 + Math.exp(-zIn));
                hasil = 0.5 * (1 + x2) * (1 - x2);
                break;
            case 2:
                /*turunan Pembulatan*/
                hasil = Math.round(zIn);
                break;
            default:
                /*turunan identitas*/
                hasil = 1;

        }
        return hasil;
    }

    public static void main(String[] args) {
        //int[] nHnN = {5, 4, 1};
        int[] nHnN = {3, 1};
        Backpropagation3 b = new Backpropagation3(nHnN, "DataLatih.xls");

        DataManagement dataL = new DataManagement();
        dataL.setInputFile("DataLatih2.xls");
        DataManagement dataUji = new DataManagement();
        //dataUji.setInputFile("DataUji2.xls");
        dataUji.setInputFile("DataLatih2Copy.xls");

        try {
            dataL.read2();
            dataUji.read2();
        } catch (IOException ex) {
            Logger.getLogger(LVQ.class.getName()).log(Level.SEVERE, null, ex);
        }

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

        //Pilih Fungsi Aktivasi
        b.fungsiAktivasi = 1;

        //train
        b.train(dataL, 100, 0.5, 0.1);

        //b.train(b.d, 100, 0.5, 0.004);
        System.out.println("\nTesting");
        b.test(dataUji);

        //print jaringgan
//        System.out.println("Bobot baru");
//        for (int i = 0; i < b.bobot.size(); i++) {
//            for (int j = 0; j < b.bobot.get(i).length; j++) {
//                System.out.println("------" + j);
//                for (int k = 0; k < b.bobot.get(i)[j].length; k++) {
//                    System.out.println(b.bobot.get(i)[j][k]);
//                }
//
//            }
//            System.out.println("");
//        }
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
