package GUI.Panels;

import GUI.Window;
import database.Reading;
import database.WeatherStation;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.sql.Array;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class StationChartViewer extends JPanel {

    JComboBox<String> attributeSelector = new JComboBox<>();

    public StationChartViewer(WeatherStation station) {
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
        chartJPanel.setBounds(50, 50, size.width - 100, 400);

        ChartPanel chartPanel = generateChart(station);
        chartJPanel.add(chartPanel, cp);

        add(chartJPanel);

        //Add event listeners
        backButton.addActionListener((e -> {
            Window.showStationViewer(station);
        }));

        attributeSelector.addActionListener((e -> {
            chartJPanel.removeAll();
            chartJPanel.add(generateChart(station), cp);
            Window.requestRepaint();
        }));
    }

    private ChartPanel generateChart(WeatherStation station) {
        try {
            Reading[] readings = database.databaseInterface.getWeatherStationReadings(station);
            DefaultCategoryDataset chartData = new DefaultCategoryDataset();

            //Get time 24 hrs ago
            Instant instant = Instant.now().minus(24, ChronoUnit.HOURS);
            Timestamp thresholdTimestamp = Timestamp.from(instant);

            //Cull old data that occurs before the thresholdTimestamp
            ArrayList<Reading> timeFrameReadings = new ArrayList<>();
            for (Reading reading : readings) {
                if (reading.getReadingDate().after(thresholdTimestamp)) {
                    System.out.printf("%s - hour of day", reading.getReadingDate().getHours());
                    timeFrameReadings.add(reading);
                }
            }

            //Average data into 1 hour segments
            int currentSum = 0;
            int currentIterations = 0;
            int currentHour = timeFrameReadings.get(0).getReadingDate().getHours();
            for (Reading reading : timeFrameReadings) {
                if (reading.getReadingDate().getHours() == currentHour) {

                } else {
                    //Reached the end of an hour segment - calculate average from data in segment and add to chart data
                    int average = currentSum / currentIterations;
                    chartData.addValue(average, "Units", String.format("%02d hrs (UTC)", reading.getReadingDate().getHours()));
                    currentHour = timeFrameReadings.get(0).getReadingDate().getHours();
                    currentSum = 0;
                    currentIterations = 0;
                }

                //Add reading to average
                if (attributeSelector.getSelectedItem().equals("Temperature")) {
                    currentSum += reading.getReadingTemperature();
                }
                if (attributeSelector.getSelectedItem().equals("Humidity")) {
                    currentSum += reading.getReadingHumidity();
                }

                currentIterations += 1;
            }

            //Generate chart
            String chartTitle = String.format("%s - %s VS Time", station.getStationName(), attributeSelector.getSelectedItem());
            JFreeChart lineChart = ChartFactory.createLineChart(chartTitle, "Record time",
                    attributeSelector.getSelectedItem().toString(), chartData, PlotOrientation.VERTICAL, false, true, false);
            //Set vertical x-axis labels
            CategoryAxis axis = lineChart.getCategoryPlot().getDomainAxis();
            axis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);

            ChartPanel chartPanel = new ChartPanel(lineChart);
            return chartPanel;


        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
