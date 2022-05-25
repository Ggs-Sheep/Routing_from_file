package process;

import models.Station;
import models.Stations;
import models.Trajet;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Metro_Reader {

    public ArrayList<Trajet> readMetro(Stations stations) throws FileNotFoundException, ParseException {
        // Initialisation de la liste des trajets
        ArrayList<Trajet> allTrajets = new ArrayList<>();


        // Récupère le fichier
        File file = new File("res/metro.txt");
        Scanner sc = new Scanner(file);

        boolean isTrajet = false;  //permet de déterminer si on est en train de lire les données des trajets aller retours

        ArrayList<ArrayList<String>> circuit = new ArrayList<>(); //Liste récupérant les trajets du circuit

        // Boucle récupérant la liste des stations
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

        // Boucle récupérant tous les trajets des trajets aller retour
        while (sc.hasNextLine()) {
            String ligne = sc.nextLine();
            // Trajets
            if (ligne.equals("% liaisons A/R")) {
                isTrajet = true;
                ligne = sc.nextLine();
                ligne = sc.nextLine();
            }

            // Est sur un trajet aller retour
            if (isTrajet) {
                if (ligne != "") {
                    String trajetString = ligne;
                    String str[] = trajetString.split(" ");
                    List<String> al = new ArrayList<String>();
                    al = Arrays.asList(str);
                    //System.out.println(al);
                    if (al.get(0).equals("Gare")) {


                        // Identique pour les 2 aller
                        //aller retour de 07 à 09
                        String date_depart_aller = "07:00";
                        String date_depart_retour = "07:00";
                        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
                        Date heure_depart_aller = formatter.parse(date_depart_aller);
                        Date heure_depart_retour = formatter.parse(date_depart_retour);
                        Calendar cal_aller = Calendar.getInstance();
                        Calendar cal_retour = Calendar.getInstance();
                        cal_aller.setTime(heure_depart_aller);
                        cal_retour.setTime(heure_depart_retour);
                        cal_retour.add(Calendar.MINUTE, Integer.parseInt(al.get(2)) + 2);
                        String time_aller = formatter.format(cal_aller.getTime());
                        String time_retour = formatter.format(cal_retour.getTime());
                        heure_depart_retour = formatter.parse(time_retour);

                        for (int i = 0; i < 13; i++) {
                            Station depart = stations.addStation(new Station(al.get(0)));
                            Station arrive = stations.addStation(new Station(al.get(1)));
                            Trajet trajetAller = new Trajet(depart, arrive, time_aller, Integer.parseInt(al.get(2)));
                            Trajet trajetRetour = new Trajet(depart, arrive, time_retour, Integer.parseInt(al.get(2)));

                            allTrajets.add(trajetAller);
                            allTrajets.add(trajetRetour);

                            cal_aller.setTime(heure_depart_aller);
                            cal_retour.setTime(heure_depart_retour);
                            cal_aller.add(Calendar.MINUTE, 10);
                            cal_retour.add(Calendar.MINUTE, 10);
                            time_aller = formatter.format(cal_aller.getTime());
                            time_retour = formatter.format(cal_retour.getTime());
                            heure_depart_aller = formatter.parse(time_aller);
                            heure_depart_retour = formatter.parse(time_retour);

                            //System.out.println("Aller: " + trajetAller);
                            //System.out.println("Retour: " + trajetRetour);
                        }

                        //aller retour de 09:20 à 16:20
                        date_depart_aller = "09:20";
                        date_depart_retour = "09:37";
                        formatter = new SimpleDateFormat("HH:mm");
                        heure_depart_aller = formatter.parse(date_depart_aller);
                        heure_depart_retour = formatter.parse(date_depart_retour);
                        cal_aller = Calendar.getInstance();
                        cal_retour = Calendar.getInstance();
                        cal_aller.setTime(heure_depart_aller);
                        cal_retour.setTime(heure_depart_retour);
                        cal_retour.add(Calendar.MINUTE, Integer.parseInt(al.get(2)) + 2);
                        time_aller = formatter.format(cal_aller.getTime());
                        time_retour = formatter.format(cal_retour.getTime());

                        for (int i = 0; i < 22; i++) {
                            Station depart = stations.addStation(new Station(al.get(0)));
                            Station arrive = stations.addStation(new Station(al.get(1)));
                            Trajet trajetAller = new Trajet(depart, arrive, time_aller, Integer.parseInt(al.get(2)));
                            Trajet trajetRetour = new Trajet(depart, arrive, time_retour, Integer.parseInt(al.get(2)));

                            allTrajets.add(trajetAller);
                            allTrajets.add(trajetRetour);

                            cal_aller.setTime(heure_depart_aller);
                            cal_retour.setTime(heure_depart_retour);
                            cal_aller.add(Calendar.MINUTE, 10);
                            cal_retour.add(Calendar.MINUTE, 10);
                            time_aller = formatter.format(cal_aller.getTime());
                            time_retour = formatter.format(cal_retour.getTime());
                            heure_depart_aller = formatter.parse(time_aller);
                            heure_depart_retour = formatter.parse(time_retour);

                            //System.out.println("Aller: " + trajetAller);
                            //System.out.println("Retour: " + trajetRetour);
                        }

                        //aller retour de 16:30 à 18:00
                        date_depart_aller = "16:30";
                        date_depart_retour = "16:47";
                        formatter = new SimpleDateFormat("HH:mm");
                        heure_depart_aller = formatter.parse(date_depart_aller);
                        heure_depart_retour = formatter.parse(date_depart_retour);
                        cal_aller = Calendar.getInstance();
                        cal_retour = Calendar.getInstance();
                        cal_aller.setTime(heure_depart_aller);
                        cal_retour.setTime(heure_depart_retour);
                        cal_retour.add(Calendar.MINUTE, Integer.parseInt(al.get(2)) + 2);
                        time_aller = formatter.format(cal_aller.getTime());
                        time_retour = formatter.format(cal_retour.getTime());

                        for (int i = 0; i < 10; i++) {
                            Station depart = stations.addStation(new Station(al.get(0)));
                            Station arrive = stations.addStation(new Station(al.get(1)));
                            Trajet trajetAller = new Trajet(depart, arrive, time_aller, Integer.parseInt(al.get(2)));
                            Trajet trajetRetour = new Trajet(depart, arrive, time_retour, Integer.parseInt(al.get(2)));

                            allTrajets.add(trajetAller);
                            allTrajets.add(trajetRetour);

                            cal_aller.setTime(heure_depart_aller);
                            cal_retour.setTime(heure_depart_retour);
                            cal_aller.add(Calendar.MINUTE, 10);
                            cal_retour.add(Calendar.MINUTE, 10);
                            time_aller = formatter.format(cal_aller.getTime());
                            time_retour = formatter.format(cal_retour.getTime());
                            heure_depart_aller = formatter.parse(time_aller);
                            heure_depart_retour = formatter.parse(time_retour);

                            //System.out.println("Aller: " + trajetAller);
                            //System.out.println("Retour: " + trajetRetour);
                        }

                        //aller retour de 18:20 à 23:00
                        date_depart_aller = "18:20";
                        date_depart_retour = "18:37";
                        formatter = new SimpleDateFormat("HH:mm");
                        heure_depart_aller = formatter.parse(date_depart_aller);
                        heure_depart_retour = formatter.parse(date_depart_retour);
                        cal_aller = Calendar.getInstance();
                        cal_retour = Calendar.getInstance();
                        cal_aller.setTime(heure_depart_aller);
                        cal_retour.setTime(heure_depart_retour);
                        cal_retour.add(Calendar.MINUTE, Integer.parseInt(al.get(2)) + 2);
                        time_aller = formatter.format(cal_aller.getTime());
                        time_retour = formatter.format(cal_retour.getTime());

                        for (int i = 0; i < 15; i++) {
                            Station depart = stations.addStation(new Station(al.get(0)));
                            Station arrive = stations.addStation(new Station(al.get(1)));
                            Trajet trajetAller = new Trajet(depart, arrive, time_aller, Integer.parseInt(al.get(2)));
                            Trajet trajetRetour = new Trajet(depart, arrive, time_retour, Integer.parseInt(al.get(2)));

                            allTrajets.add(trajetAller);
                            allTrajets.add(trajetRetour);

                            cal_aller.setTime(heure_depart_aller);
                            cal_retour.setTime(heure_depart_retour);
                            cal_aller.add(Calendar.MINUTE, 10);
                            cal_retour.add(Calendar.MINUTE, 10);
                            time_aller = formatter.format(cal_aller.getTime());
                            time_retour = formatter.format(cal_retour.getTime());
                            heure_depart_aller = formatter.parse(time_aller);
                            heure_depart_retour = formatter.parse(time_retour);

                            //System.out.println("Aller: " + trajetAller);
                            //System.out.println("Retour: " + trajetRetour);
                        }
                    }

                }

                // Check si on a fini de passer sur les trajtes

                if (ligne.equals("") || ligne.equals("% liaisons circuit")) {
                    //System.out.println(ligne);
                    isTrajet = false;
                }

                // Est sur un trajet

            }

        }

        sc = new Scanner(file);

        // Boucle récupérant les trajets du circuit
        while (sc.hasNextLine()) {
            String ligne = sc.nextLine();
            // Trajets
            if (ligne.equals("% liaisons circuit")) {
                isTrajet = true;
                ligne = sc.nextLine();
                ligne = sc.nextLine();
            }

            // Est sur un trajet aller retour
            if (isTrajet) {
                if (ligne != "") {
                    String trajetString = ligne;
                    String str[] = trajetString.split(" ");
                    List<String> al = new ArrayList<String>();
                    al = Arrays.asList(str);
                    //System.out.println(al);
                    ArrayList<String> trajetLigne = new ArrayList<>();
                    for (String s: al
                         ) {
                        trajetLigne.add(s);
                    }
                    circuit.add(trajetLigne);
                }

                // Check si on a fini de passer sur les trajtes

                if (ligne.equals("") || ligne.equals("% liaisons circuit")) {
                    //System.out.println(ligne);
                    isTrajet = false;
                }

                // Est sur un trajet
            }
        }

        //circuit de 07 à 09
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");

        String date_depart_gare = "07:00";
        Date heure_depart_gare = formatter.parse(date_depart_gare);
        Calendar cal_gare = Calendar.getInstance();
        cal_gare.setTime(heure_depart_gare);
        String time_gare = formatter.format(cal_gare.getTime());
        heure_depart_gare = formatter.parse(time_gare);

        for (int i = 0; i < 13; i++) {

            Date heure_depart_aller = formatter.parse(time_gare);
            Calendar cal_aller = Calendar.getInstance();
            cal_aller.setTime(heure_depart_gare);
            String time_aller = formatter.format(cal_aller.getTime());

            for (ArrayList<String> c: circuit
                 ) {
                Station depart = stations.addStation(new Station(c.get(0)));
                Station arrive = stations.addStation(new Station(c.get(1)));

                Trajet trajetAller = new Trajet(depart, arrive, time_aller, Integer.parseInt(c.get(2)));

                allTrajets.add(trajetAller);

                cal_aller.setTime(heure_depart_aller);
                cal_aller.add(Calendar.MINUTE, Integer.parseInt(c.get(2)) + 2);
                time_aller = formatter.format(cal_aller.getTime());
                heure_depart_aller = formatter.parse(time_aller);

                //System.out.println ("Aller: " + trajetAller);
            }

            cal_gare.setTime(heure_depart_gare);
            cal_gare.add(Calendar.MINUTE, 10);
            time_gare = formatter.format(cal_gare.getTime());
            heure_depart_gare = formatter.parse(time_gare);
        }

        //circuit de 09:20 à 16:20
        formatter = new SimpleDateFormat("HH:mm");

        date_depart_gare = "09:20";
        heure_depart_gare = formatter.parse(date_depart_gare);
        cal_gare = Calendar.getInstance();
        cal_gare.setTime(heure_depart_gare);
        time_gare = formatter.format(cal_gare.getTime());
        heure_depart_gare = formatter.parse(time_gare);

        for (int i = 0; i < 22; i++) {

            Date heure_depart_aller = formatter.parse(time_gare);
            Calendar cal_aller = Calendar.getInstance();
            cal_aller.setTime(heure_depart_gare);
            String time_aller = formatter.format(cal_aller.getTime());

            for (ArrayList<String> c: circuit
            ) {
                Station depart = stations.addStation(new Station(c.get(0)));
                Station arrive = stations.addStation(new Station(c.get(1)));

                Trajet trajetAller = new Trajet(depart, arrive, time_aller, Integer.parseInt(c.get(2)));

                allTrajets.add(trajetAller);

                cal_aller.setTime(heure_depart_aller);
                cal_aller.add(Calendar.MINUTE, Integer.parseInt(c.get(2)) + 2);
                time_aller = formatter.format(cal_aller.getTime());
                heure_depart_aller = formatter.parse(time_aller);

                //System.out.println("Aller: " + trajetAller);
            }

            cal_gare.setTime(heure_depart_gare);
            cal_gare.add(Calendar.MINUTE, 20);
            time_gare = formatter.format(cal_gare.getTime());
            heure_depart_gare = formatter.parse(time_gare);
        }

        //circuit de 16:30 à 18:00
        formatter = new SimpleDateFormat("HH:mm");

        date_depart_gare = "16:30";
        heure_depart_gare = formatter.parse(date_depart_gare);
        cal_gare = Calendar.getInstance();
        cal_gare.setTime(heure_depart_gare);
        time_gare = formatter.format(cal_gare.getTime());
        heure_depart_gare = formatter.parse(time_gare);

        for (int i = 0; i < 10; i++) {

            Date heure_depart_aller = formatter.parse(time_gare);
            Calendar cal_aller = Calendar.getInstance();
            cal_aller.setTime(heure_depart_gare);
            String time_aller = formatter.format(cal_aller.getTime());

            for (ArrayList<String> c: circuit
            ) {
                Station depart = stations.addStation(new Station(c.get(0)));
                Station arrive = stations.addStation(new Station(c.get(1)));

                Trajet trajetAller = new Trajet(depart, arrive, time_aller, Integer.parseInt(c.get(2)));

                allTrajets.add(trajetAller);

                cal_aller.setTime(heure_depart_aller);
                cal_aller.add(Calendar.MINUTE, Integer.parseInt(c.get(2)) + 2);
                time_aller = formatter.format(cal_aller.getTime());
                heure_depart_aller = formatter.parse(time_aller);

                //System.out.println("Aller: " + trajetAller);
            }

            cal_gare.setTime(heure_depart_gare);
            cal_gare.add(Calendar.MINUTE, 10);
            time_gare = formatter.format(cal_gare.getTime());
            heure_depart_gare = formatter.parse(time_gare);
        }

        //circuit de 18:20 à 23:00
        formatter = new SimpleDateFormat("HH:mm");

        date_depart_gare = "18:20";
        heure_depart_gare = formatter.parse(date_depart_gare);
        cal_gare = Calendar.getInstance();
        cal_gare.setTime(heure_depart_gare);
        time_gare = formatter.format(cal_gare.getTime());
        heure_depart_gare = formatter.parse(time_gare);

        for (int i = 0; i < 15; i++) {

            Date heure_depart_aller = formatter.parse(time_gare);
            Calendar cal_aller = Calendar.getInstance();
            cal_aller.setTime(heure_depart_gare);
            String time_aller = formatter.format(cal_aller.getTime());

            for (ArrayList<String> c: circuit
            ) {
                Station depart = stations.addStation(new Station(c.get(0)));
                Station arrive = stations.addStation(new Station(c.get(1)));

                Trajet trajetAller = new Trajet(depart, arrive, time_aller, Integer.parseInt(c.get(2)));

                allTrajets.add(trajetAller);

                cal_aller.setTime(heure_depart_aller);
                cal_aller.add(Calendar.MINUTE, Integer.parseInt(c.get(2)) + 2);
                time_aller = formatter.format(cal_aller.getTime());
                heure_depart_aller = formatter.parse(time_aller);

                //System.out.println("Aller: " + trajetAller);
            }

            cal_gare.setTime(heure_depart_gare);
            cal_gare.add(Calendar.MINUTE, 20);
            time_gare = formatter.format(cal_gare.getTime());
            heure_depart_gare = formatter.parse(time_gare);
        }

        return allTrajets;

    }
}

