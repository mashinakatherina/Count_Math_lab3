package Part1.lab3.gui;

import Part1.lab3.Function;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.markers.SeriesMarkers;

import javax.swing.*;
import java.awt.*;

public class GraphMaker {

    private Function baseFunction;
    private double[] xData;

    public GraphMaker (Function baseFunction,  double[] xData) {
        this.baseFunction = baseFunction;
        this.xData = xData;
    }

    public JPanel getChart(int width, int height, double solutionX, double solutionY) {
        XYChart chart = new XYChart(width, height);
        chart.getStyler().setXAxisMin(xData[0]);
        chart.getStyler().setXAxisMax(xData[xData.length - 1]);

        double[] yData = new double[xData.length];
        for (int i = 0; i < yData.length; i++)
            yData[i] = baseFunction.getValue(xData[i]);


        XYSeries changeSeries = chart.addSeries("Решение", new double[]{solutionX}, new double[]{solutionY});
        changeSeries.setMarker(SeriesMarkers.CIRCLE);
        changeSeries.setMarkerColor(Color.RED);
        changeSeries.setLineColor(Color.WHITE);

        double step = Math.abs(xData[xData.length - 1] - xData[0]) / width;
        double[] xGraphing = new double[width];
        double[] yBaseFunction = new double[width];
        for (int i = 0; i < yBaseFunction.length; i++) {
            double arg = xData[0] + step * i;
            yBaseFunction[i] = baseFunction.getValue(arg);
            xGraphing[i] = arg;
        }

        chart.addSeries("Исходная функция", xGraphing, yBaseFunction).
                setMarker(SeriesMarkers.NONE);

        return new XChartPanel<>(chart);
    }
}