package models;

public class Station {
    private String nom;


    public Station(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public String toString(){
        return "Station " + this.getNom();
    }
}