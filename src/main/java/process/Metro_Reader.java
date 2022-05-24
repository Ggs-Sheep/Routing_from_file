package process;

import models.Station;
import models.Stations;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Metro_Reader {

    public void readMetro(Stations stations) throws FileNotFoundException {
        // pass the path to the file as a parameter
        File file = new File("res/metro.txt");
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()){
            String ligne = sc.nextLine();
            //System.out.println(sc.nextLine());
            if(ligne.equals("%stations")){
                System.out.println("yes");
                String metro_stations = sc.nextLine();

                System.out.println(metro_stations);
                ArrayList<Station> tram_stations = Xml_Reader.get_stations(metro_stations);
                for (Station station:
                        tram_stations){
                    stations.addStation(station);
                    System.out.println(station);
                }


            }
    }}
    }

