/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lvq_backpro.arsitektur;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * BISMILLAHIRRAHMANIRRAHIIM
 *
 * @author MSAF
 */
public class HiddenLayer extends Layer {

    private int hiddenLayerKe;

    public HiddenLayer(int hiddenLayerKe, int jumlahNeuron, Layer layerSebelum) {
        this.hiddenLayerKe = hiddenLayerKe;
        ArrayList<Double> bobotPerNeuron = new ArrayList<>();
        Random r = new Random();
        this.setJumlahNeuronDalamLayer(jumlahNeuron);

        for (int i = 0; i < jumlahNeuron; i++) {
            this.addNeuron(new Neuron());
            for (int j = 0; j < layerSebelum.getJumlahNeuronDalamLayer(); j++) {
                double bobotRandom = -1 + (1 + 1) * r.nextDouble();
                bobotPerNeuron.add(bobotRandom);
            }
            this.getNeuron(i).setBobot(bobotPerNeuron);
            bobotPerNeuron = new ArrayList<>();

        }

    }

    public void printLayer() {
        System.out.println("#### Hidden Layer ke - " + hiddenLayerKe + " ####");
        int n = 1;
        for (Neuron neuron : this.getKumpulanNeuron()) {
            System.out.println("   Neuron " + n);
            System.out.println("\tBobot Masuk:");
            System.out.println("\t" + Arrays.deepToString(neuron.getBobot().toArray()));
            System.out.println();
            n++;
        }
    }

    public int getHiddenLayerKe() {
        return hiddenLayerKe;
    }

}
