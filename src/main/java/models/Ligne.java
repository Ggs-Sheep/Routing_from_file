package models;

import java.util.ArrayList;
import models.Trajet;

public class Ligne{

    private String nom;
    private ArrayList<Trajet> trajets;

    public Ligne(String nom) {
        this.nom = nom;
        this.trajets = new ArrayList<Trajet>();
    }

    public Ligne(String nom, ArrayList<Trajet> trajets) {
        this.nom = nom;
        this.trajets = trajets;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<Trajet> getTrajets() {
        return trajets;
    }

    public void setTrajets(ArrayList<Trajet> trajets) {
        this.trajets = trajets;
    }

    public void addTrajets(Trajet trajet) {
        this.trajets.add(trajet);
    }

    @Override
    public String toString() {
        return "Ligne{" +
                "nom='" + nom + '\'' +
                ", trajets=" + trajets +
                '}';
    }
}
