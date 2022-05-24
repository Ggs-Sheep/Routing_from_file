package process;

import models.Station;
import models.Stations;
import models.Trajet;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Metro_Reader {

    public void readMetro(Stations stations) throws FileNotFoundException {
        // pass the path to the file as a parameter
        File file = new File("res/metro.txt");
        Scanner sc = new Scanner(file);

        boolean isTrajet = false;

        while (sc.hasNextLine()) {

            String ligne = sc.nextLine();
            //System.out.println(ligne);

            // Stations
            if (ligne.equals("% stations")) {
                //System.out.println("yes");
                String metro_stations = sc.nextLine();

                //System.out.println(metro_stations);
                ArrayList<Station> tram_stations = Xml_Reader.get_stations(metro_stations);
                for (Station station :
                        tram_stations) {
                    stations.addStation(station);
                    //System.out.println(station);
                }
            }
        }

        sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String ligne = sc.nextLine();
            // Trajets
            if(ligne.equals("% depart arrivee duree")){
                isTrajet = true;
                ligne = sc.nextLine();
            }

            // Est sur un trajet
            if(isTrajet){
                if(ligne != ""){
                    String trajetString = ligne;
                    String str[] = trajetString.split(" ");
                    List<String> al = new ArrayList<String>();
                    al = Arrays.asList(str);
                    System.out.println(al);

                    Calendar date = Calendar.getInstance();
                    long timeInSecs = date.getTimeInMillis();
                    Date heure_depart = new Date(timeInSecs + (0));

                    for(int i = 0; i < 13; i ++){
                        Station depart = stations.addStation(new Station(al.get(0)));
                        Station arrive = stations.addStation(new Station(al.get(1)));
                        Trajet trajet = new Trajet(depart, arrive, "07:00", 15);
                        heure_depart = new Date(timeInSecs + (10 * 60 * 1000));
                    }
                }

                // Check si on a fini de passer sur les trajtes

                if(ligne.equals("")){
                    isTrajet = false;
                }

                // Est sur un trajet

            }

    }}
    }

