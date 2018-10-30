package GUI.Panels;

import GUI.Window;
import database.WeatherStation;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.interfaces.ICoordinate;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;
import org.openstreetmap.gui.jmapviewer.interfaces.TileCache;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.Iterator;
import java.util.List;

public class StationSelectorMap extends JPanel {

    JMapViewer map = new JMapViewer();
    WeatherStation[] stations = new WeatherStation[]{};

    public StationSelectorMap() {
        setLayout(null);
        Dimension size = Window.getSize();
        setPreferredSize(size);

        //Back button
        JButton backButton = new JButton("< Back");
        backButton.setBounds(size.width - 125, 25, 100, 25);
        add(backButton);

        JLabel titleLabel = new JLabel("Map view");
        titleLabel.setBounds(25, 25, 100, 25);
        add(titleLabel);

        //Map panel

        map.setBounds(50, 55, size.width - 100, size.height - 120);
        map.setDisplayPosition(new Coordinate(-27.470125, 153.021072), 5);

        add(map);

        //Map marker
        //MapMarker marker1 = new MapMarkerDot("Townsville marker",new Coordinate(-19.258965,146.816956));
        //map.addMapMarker(marker1);
        populateMapWithWeatherStations();

        //Event listeners
        backButton.addActionListener((e) -> {
            Window.showStartScreen();
        });

        //Get weather stations
        try {
            stations = database.databaseInterface.getWeatherStationList();
        } catch (Exception ex) {

        }

        map.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                System.out.printf("Click?\n");

                if (e.getClickCount() == 1 && e.getButton() == MouseEvent.BUTTON1) {

                    Point p = e.getPoint();
                    int X = p.x + 3;
                    int Y = p.y + 3;
                    List<MapMarker> ar = map.getMapMarkerList();
                    Iterator<MapMarker> i = ar.iterator();
                    while (i.hasNext()) {

                        MapMarker mapMarker = (MapMarker) i.next();

                        Point MarkerPosition = map.getMapPosition(mapMarker.getLat(), mapMarker.getLon());
                        if (MarkerPosition != null) {

                            int centerX = MarkerPosition.x;
                            int centerY = MarkerPosition.y;

                            // calculate the radius from the touch to the center of the dot
                            double radCircle = Math.sqrt((((centerX - X) * (centerX - X)) + (centerY - Y) * (centerY - Y)));

                            // if the radius is smaller then 23 (radius of a ball is 5), then it must be on the dot
                            if (radCircle < 8) {
                                System.out.printf("Clicked: %s - Name: %s\n", mapMarker.toString(), mapMarker.getName());
                                for (WeatherStation station : stations) {
                                    if (mapMarker.getName().equals(station.getStationName())) {
                                        StationViewer.backToMap = true;
                                        Window.showStationViewer(station);
                                    }
                                }
                            }

                        }
                    }
                }

                //else if (doubleClickZoomEnabled && e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                //    map.zoomIn(e.getPoint());
                //}

            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
            }

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                super.mouseWheelMoved(e);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
            }
        });
    }

    private void populateMapWithWeatherStations() {

        try {
            WeatherStation[] stations = database.databaseInterface.getWeatherStationList();

            for (WeatherStation station : stations) {
                MapMarker marker = new MapMarkerDot(station.getStationName(), new Coordinate(station.getLatitude(), station.getLongitude()));
                map.addMapMarker(marker);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
