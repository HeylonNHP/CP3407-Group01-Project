package GUI.Panels;

import GUI.Window;
import database.Reading;
import database.WeatherStation;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;

public class StationChartViewer extends JPanel {

    JComboBox<String> attributeSelector = new JComboBox<>();

    public StationChartViewer(WeatherStation station){
        setLayout(null);
        Dimension size = Window.getSize();
        setPreferredSize(size);

        //Back button
        JButton backButton = new JButton("< Back");
        backButton.setBounds(size.width - 125, 25, 100, 25);
        add(backButton);

        //Attribute selector
        attributeSelector.addItem("Temperature");
        attributeSelector.addItem("Humidity");
        attributeSelector.setBounds(25, 25, 200, 25);
        add(attributeSelector);

        //https://www.tutorialspoint.com/jfreechart/index.htm
        JPanel chartJPanel = new JPanel();
        chartJPanel.setLayout(new GridBagLayout());
        GridBagConstraints cp = new GridBagConstraints();
        cp.fill = GridBagConstraints.BOTH;
        cp.weighty = 1;
        cp.weightx = 1;
        chartJPanel.setBounds(50,50,size.width-100,400);

        ChartPanel chartPanel = generateChart(station);
        chartJPanel.add(chartPanel,cp);

        add(chartJPanel);

        //Add event listeners
        backButton.addActionListener((e -> {
            Window.showStationViewer(station);
        }));

        attributeSelector.addActionListener((e -> {
            chartJPanel.removeAll();
            chartJPanel.add(generateChart(station),cp);
            Window.requestRepaint();
        }));
    }

    private ChartPanel generateChart(WeatherStation station){
        try{
            Reading[] readings = database.databaseInterface.getWeatherStationReadings(station);
            DefaultCategoryDataset chartData = new DefaultCategoryDataset();

            for(Reading reading : readings){
                if(attributeSelector.getSelectedItem().equals("Temperature")){
                    chartData.addValue(reading.getReadingTemperature(),"Temperature",reading.getReadingDate().toString());
                }else if(attributeSelector.getSelectedItem().equals("Humidity")){
                    chartData.addValue(reading.getReadingHumidity(),"Humidity",reading.getReadingDate().toString());
                }
            }

            JFreeChart lineChart = ChartFactory.createLineChart(attributeSelector.getSelectedItem() + " VS Time", "Record time",
                    attributeSelector.getSelectedItem().toString(), chartData, PlotOrientation.VERTICAL,true,true,false);
            ChartPanel chartPanel = new ChartPanel(lineChart);
            return chartPanel;


        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
}
