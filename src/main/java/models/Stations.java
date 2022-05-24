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

    public void addStation(Station s){
        if(!stations.contains(s)){
            stations.add(s);
        }
    }
}
