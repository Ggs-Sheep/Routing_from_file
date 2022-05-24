package process;

import models.Ligne;
import models.Station;
import models.Stations;
import models.Trajet;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class Bus_Reader {
    private Stations stations;

    public Bus_Reader(Stations s) {
        stations = s;
    }


    public Ligne lectureFichier() throws IOException, ParseException, java.text.ParseException {
        Ligne ligne = new Ligne("Bus");

        ArrayList<String> garesAller = new ArrayList<>();
        ArrayList<String> garesRetour = new ArrayList<>();
        ArrayList<ArrayList<String>> passagesAller = new ArrayList<>();
        ArrayList<ArrayList<String>> passagesRetour = new ArrayList<>();

        Object ob = new JSONParser().parse(new FileReader("res/bus.json"));
        JSONObject js = (JSONObject) ob; //création du JSON OBJECT
        JSONArray horaires = (JSONArray) js.get("horaires"); // récuparation de la liste Json avec la clé horaire

        AtomicReference<Integer> c1 = new AtomicReference<>(0);

        horaires.forEach(horaire -> {
            JSONObject d = (JSONObject) horaire;
            JSONArray stations = (JSONArray) d.get("stations"); //récupération de la liste des stations
            JSONArray passages = (JSONArray) d.get("passages"); //récupération de la liste des heures de passages
            //System.out.println(passages);

            stations.forEach(station -> {
                JSONObject s = (JSONObject) station;
                if (c1.get() == 0) {
                    garesAller.add((String) s.get("station").toString());
                } else {
                    garesRetour.add((String) s.get("station").toString());
                }

                //System.out.println(s.get("station"));
            });


            passages.forEach(passage -> {
                if (c1.get() == 0) {
                    passagesAller.add((ArrayList<String>) passage);
                } else {
                    passagesRetour.add((ArrayList<String>) passage);
                }
                //System.out.println(passage);
            });
            c1.set(1);

        });

        /*
        System.out.println("Gares Aller = " + garesAller);


        for (ArrayList<String> p : passagesAller
        ) {
            System.out.println("Passage Aller = " + p);
        }

        System.out.println("Gares Retou = " + garesRetour);
        for (ArrayList<String> p : passagesRetour
        ) {
            System.out.println("Passage Retou = " + p);
        }

         */

        // Construction des trajets Aller
        for (ArrayList<String> p : passagesAller
        ) {
            for (int i = 0; i < p.size() - 1; i++) {
                Integer index = i + 1;

                Station depart = stations.addStation(new Station(garesAller.get(i)));
                Station arrive = stations.addStation(new Station(garesAller.get(index)));

                String stringDepart = p.get(i);
                String stringArrive = p.get(i + 1);

                int duree = get_duration(stringDepart, stringArrive);

                Trajet t = new Trajet(depart, arrive, p.get(i), duree);
                //System.out.println(t.toString());
            }
        }

        // Construction des trajets Retour
        for (ArrayList<String> p : passagesRetour
        ) {
            for (int i = 0; i < p.size() - 1; i++) {
                Integer index = i + 1;
                Station depart = stations.addStation(new Station(garesRetour.get(i)));
                Station arrive = stations.addStation(new Station(garesRetour.get(index)));

                String stringDepart = p.get(i);
                String stringArrive = p.get(i + 1);

                int duree = get_duration(stringDepart, stringArrive);

                Trajet t = new Trajet(depart, arrive, p.get(i), duree);
                ligne.addTrajets(t);
            }



        }
        return ligne;
    }

    public static int get_duration(String timeCode1, String timeCode2){
        int hour1 = Integer.parseInt(timeCode1.substring(0, (timeCode1.length() / 2)));
        int minute1 = Integer.parseInt(timeCode1.substring((timeCode1.length() / 2)));

        int hour2 = Integer.parseInt(timeCode2.substring(0, (timeCode2.length() / 2)));
        int minute2 = Integer.parseInt(timeCode2.substring((timeCode2.length() / 2)));

        int duration = 0;

        while (!(Integer.toString(hour1) + ":" + Integer.toString(minute1)).equals(Integer.toString(hour2) + ":" + Integer.toString(minute2))) {
            if (minute1 < 59) {
                minute1 = minute1 + 1;
            } else {
                minute1 = 0;
                hour1 = hour1 + 1;
            }
            duration = duration + 1;
        }
        return duration;
    }
}