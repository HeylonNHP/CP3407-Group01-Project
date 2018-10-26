package GUI.Panels;

import GUI.Window;
import database.WeatherStation;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;

public class StationChartViewer extends JPanel {
    public StationChartViewer(WeatherStation station){
        setLayout(null);
        Dimension size = Window.getSize();
        setPreferredSize(size);

        //Data to display
        DefaultCategoryDataset chartData = new DefaultCategoryDataset();
        chartData.addValue(29,"Temperature", "12AM");
        chartData.addValue(28,"Temperature", "1AM");
        chartData.addValue(27.5,"Temperature", "2AM");
        chartData.addValue(27,"Temperature", "3AM");

        //Chart
        JFreeChart lineChart = ChartFactory.createLineChart("Temperature VS Time", "Hour of day","Temperature", chartData, PlotOrientation.VERTICAL,true,true,false);
        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setBounds(50,50,400,400);
        add(chartPanel);
    }
}
