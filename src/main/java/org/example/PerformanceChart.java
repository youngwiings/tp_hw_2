package org.example;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.ChartUtilities;

import java.awt.Color;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class PerformanceChart extends ApplicationFrame {

    public PerformanceChart(String title) {
        super(title);
        JFreeChart xylineChart = ChartFactory.createXYLineChart(
                "Зависимость времени выполнения функции `_mult` от кол-ва чисел.",
                "Количество чисел",
                "Время выполнения (нс)",
                createDataset(),
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(xylineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(1000, 800));
        final XYPlot plot = xylineChart.getXYPlot();

        // Наводим красоту
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.BLUE);
        renderer.setSeriesShapesVisible(0, true);
        renderer.setSeriesShape(0, new java.awt.geom.Ellipse2D.Double(-3, -3, 6, 6));


        plot.setRenderer(renderer);
        setContentPane(chartPanel);

        // Сохраняем график в формате PNG
        saveChartAsPNG(xylineChart);
    }

    private void saveChartAsPNG(JFreeChart chart) {
        File imageFile = new File("assets/performance_chart.png");
        try {
            ChartUtilities.saveChartAsPNG(imageFile, chart, 1000, 800);
        } catch (IOException e) {
            System.err.println("Ошибка сохранения графика: " + e.getMessage());
        }
    }

    private XYSeriesCollection createDataset() {
        final XYSeries series = new XYSeries("_mult");

        try {
            for (int size = 10000; size <= 1000000; size += 10000) {
                List<Integer> numbers = generateNumbers(size);

                long startTime = System.nanoTime();
                NumberProcessor._mult(numbers);
                long endTime = System.nanoTime();

                long duration = endTime - startTime;

                series.add(size, duration);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        return dataset;
    }

    private List<Integer> generateNumbers(int count) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter("chart_numbers.txt"))) {
            for (int i = 1; i <= count; i++)
                writer.print(i + " ");

        }
        return NumberProcessor.readNumbersFromFile("chart_numbers.txt");
    }

    public static void main(String[] args) {
        PerformanceChart chart = new PerformanceChart("Зависимость времени выполнения функции `_mult` от кол-ва чисел.");
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
    }
}
