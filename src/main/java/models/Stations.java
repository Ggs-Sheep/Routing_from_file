package models;

import java.util.ArrayList;

public class Stations {
    private ArrayList<Station> stations;

    public Stations() {
        this.stations = new ArrayList<>();
    }

    public ArrayList<Station> getStations() {
        return stations;
    }

    public Station getStation(Station s){
        for (Station station:stations
             ) {
            if(s.getNom() == station.getNom()){
                return station;
            }
        }
        return null;
    }

    public Station addStation(Station s){
        for (Station station:
             stations) {
            if (station.getNom().equals(s.getNom())) {
                return s;
            }
        }
        this.stations.add(s);
        return s;
    }
}
