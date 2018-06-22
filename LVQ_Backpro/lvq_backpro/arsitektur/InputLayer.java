/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lvq_backpro.arsitektur;

import java.util.Random;

/**
 * BISMILLAHIRRAHMANIRRAHIIM
 *
 * @author MSAF
 */
public class InputLayer extends Layer {

    public InputLayer(int jumlahNeuron) {
        Random r = new Random();
        this.setJumlahNeuronDalamLayer(jumlahNeuron);

        for (int i = 0; i < jumlahNeuron; i++) {
            this.addNeuron(new Neuron());

        }
    }

    public void printLayer() {
        System.out.println("******* Input Layer *******");
        int n = 1;
        for (Neuron neuron : this.getKumpulanNeuron()) {
            System.out.println("Neuron " + n);
            System.out.println("\t***\n");
            n++;
        }
    }
}
