package process;

import models.Station;
import models.Stations;
import models.Trajet;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InterCite_Reader {

    private ArrayList<Trajet> listeTrajets = new ArrayList<Trajet>();
    private ArrayList<ArrayList<String>> dureeTrajets = new ArrayList<>();

    public ArrayList<Trajet> reader(Stations stations) throws FileNotFoundException {
        File file = new File("res/InterCites.txt");
        Scanner sc = new Scanner(file);

        boolean isTrajet = false;  //permet de déterminer si on est en train de lire les données des trajets aller retours
        boolean isDuree = false;  //permet de déterminer si on est en train de lire les données des trajets aller retours

        ArrayList<ArrayList<String>> circuit = new ArrayList<>(); //Liste récupérant les trajets du circuit

        // Boucle récupérant la liste des stations
        while (sc.hasNextLine()) {

            String ligne = sc.nextLine();
            //System.out.println(ligne);

            if(ligne.equals("% depart arrivee heure-depart")){
                ligne = sc.nextLine();
                ligne = sc.nextLine();
                isDuree = true;
            }

            if(isDuree){
                ArrayList<String> dureeTrajet = new ArrayList();
                String trajetString = ligne;
                String str[] = trajetString.split("\t");
                List<String> al = new ArrayList<String>();
                al = Arrays.asList(str);

                for (int i = 0; i < al.size(); i++) {
                    dureeTrajet.add(al.get(i));
                }

                dureeTrajets.add(dureeTrajet);

            }

            if (ligne.equals("//")) {

                ligne = sc.nextLine();
                isTrajet = true;
                isDuree = false;

            }

            if(isTrajet){
                //System.out.println(ligne);

                String trajetString = ligne;
                String str[] = trajetString.split("\t");
                List<String> al = new ArrayList<String>();
                al = Arrays.asList(str);

                ArrayList<String> listeStringTrajet = new ArrayList<>();

                for (int i = 0; i < al.size(); i++) {
                    listeStringTrajet.add(al.get(i));
                }

                for (int i = 0; i < listeStringTrajet.size(); i++) {
                    if(listeStringTrajet.get(i).length() < 4){
                        listeStringTrajet.remove(i);
                    }
                }
                //System.out.println(listeStringTrajet);
                Station depart = stations.addStation(new Station(listeStringTrajet.get(0)));
                Station arrive = stations.addStation(new Station(listeStringTrajet.get(1)));
                listeTrajets.add(new Trajet(depart, arrive, listeStringTrajet.get(2), 0));
            }

            if (ligne.equals("")) {

                //ligne = sc.nextLine();
                isTrajet = false;

            }

        }

        for (int i = 0; i < dureeTrajets.size(); i++) {
            for (int j = 0; j < dureeTrajets.get(i).size(); j++) {
                if(dureeTrajets.get(i).get(j).length() < 2){
                    dureeTrajets.get(i).remove(j);
                }
            }
        }

        this.dureeTrajets.remove(this.dureeTrajets.size() - 1);

        miseAjourDuree();

        return listeTrajets;
    }

    public void miseAjourDuree(){
        for (Trajet t: listeTrajets
             ) {
            String depart = t.getDepart().getNom();
            String arrive = t.getArrive().getNom();

            for (ArrayList<String> l: dureeTrajets
                 ) {
                if((depart.equals(l.get(0)) && arrive.equals(l.get(1))) || depart.equals(l.get(1)) && arrive.equals(l.get(0))){
                    t.setDuree(Integer.parseInt(l.get(2)));
                }
            }
        }
    }
}
