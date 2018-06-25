/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lvq_backpro;

/**
 * BISMILLAHIRRAHMANIRRAHIIM
 *
 * @author MSAF
 */
import java.util.HashSet;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;

public class LineChart {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                double[][] data = {{1, 2, 3}, {1, 2, 7}};
                double[][] data2 = {{1, 2, 3}, {3, 2, 3}};
                showChart("Grafik Pelatihan", data, "Grafik", "Epoh", "Error");
                showChart2Line("Grafik Pelatihan", data, "Data Aktual", data2, "Prediksi", "Epoh", "Error");

            }
        });

    }

    private static XYDataset createDataset(double[][] data, String lineName) {

        DefaultXYDataset ds = new DefaultXYDataset();

        ds.addSeries(lineName, data);

        return ds;
    }

    private static XYDataset createDataset2(double[][] data, String lineName, double[][] data2, String lineName2) {

        DefaultXYDataset ds = new DefaultXYDataset();

        ds.addSeries(lineName, data);

        ds.addSeries(lineName2, data2);

        return ds;
    }

    public static void showChart(String title, double[][] data, String lineName, String xName, String yName) {
        JFrame frame = new JFrame(title);

        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        XYDataset ds = createDataset(data, lineName);
        JFreeChart chart = ChartFactory.createXYLineChart(title,
                xName, yName, ds, PlotOrientation.VERTICAL, true, true,
                false);

        ChartPanel cp = new ChartPanel(chart);

        frame.getContentPane().add(cp);
    }

    public static void showChart2Line(String title, double[][] data, String lineName,
            double[][] data2, String lineName2, String xName, String yName
    ) {
        JFrame frame = new JFrame(title);

        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        XYDataset ds = createDataset2(data, lineName, data2, lineName2);
        JFreeChart chart = ChartFactory.createXYLineChart(title,
                xName, yName, ds, PlotOrientation.VERTICAL, true, true,
                false);
        
        XYPlot plot = (XYPlot) chart.getPlot();
        // Create an NumberAxis for scalling
        NumberAxis xAxis = (NumberAxis) plot.getDomainAxis();
        xAxis.setTickUnit(new NumberTickUnit(1));
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setTickUnit(new NumberTickUnit(1));

        ChartPanel cp = new ChartPanel(chart);

        frame.getContentPane().add(cp);
    }

}
