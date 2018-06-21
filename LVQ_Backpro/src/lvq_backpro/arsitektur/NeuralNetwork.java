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
public class NeuralNetwork {

    private OutputLayer outputLayer;
    private HiddenLayer hiddenLayer;
    private ArrayList<HiddenLayer> kumpulanHiddenLayer;
    private InputLayer inputLayer;

    public NeuralNetwork(int jumlahNeuronDalamInputLayer, int jumlahHiddenLayer, int jumlahNeuronDalamHiddenLayer, int jumlahNeuronDalamOutputLayer) {

        inputLayer = new InputLayer(jumlahNeuronDalamInputLayer);
        kumpulanHiddenLayer = new ArrayList<>();
        Layer layerSebelum = inputLayer;
        for (int i = 0; i < jumlahHiddenLayer; i++) {
            hiddenLayer = new HiddenLayer((i + 1), jumlahNeuronDalamHiddenLayer, layerSebelum);
            kumpulanHiddenLayer.add(hiddenLayer);
            layerSebelum = hiddenLayer;
        }
        outputLayer = new OutputLayer(jumlahNeuronDalamOutputLayer, layerSebelum);

    }

    public void printNet() {
        inputLayer.printLayer();
        System.out.println("------------------------------------------------------");
        for (int i = 0; i < kumpulanHiddenLayer.size(); i++) {
            kumpulanHiddenLayer.get(i).printLayer();
        }
        System.out.println("------------------------------------------------------");
        outputLayer.printLayer();
    }

}
