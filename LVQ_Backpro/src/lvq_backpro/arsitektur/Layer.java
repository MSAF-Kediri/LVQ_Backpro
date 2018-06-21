/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lvq_backpro.arsitektur;

import java.util.ArrayList;

/**
 * BISMILLAHIRRAHMANIRRAHIIM
 *
 * @author MSAF
 */
public abstract class Layer {
 private ArrayList<Neuron> kumpulanNeuron = new ArrayList<>();
    private int jumlahNeuronDalamLayer;

    public ArrayList<Neuron> getKumpulanNeuron() {
        return kumpulanNeuron;
    }

    public void setKumpulanNeuron(ArrayList<Neuron> kumpulanNeuron) {
        this.kumpulanNeuron = kumpulanNeuron;
    }

    public int getJumlahNeuronDalamLayer() {
        return jumlahNeuronDalamLayer;
    }

    public void setJumlahNeuronDalamLayer(int jumlahNeuronDalamLayer) {
        this.jumlahNeuronDalamLayer = jumlahNeuronDalamLayer;
    }

    public Neuron getNeuron(int indeks) {
        return kumpulanNeuron.get(indeks);
    }
    
    public void addNeuron(Neuron neuron) {
        kumpulanNeuron.add(neuron);
    }

    public abstract void printLayer();
}
