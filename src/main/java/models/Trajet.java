package models;

public class Trajet {
    private Station depart;
    private Station arrive;
    private String heure_depart;
    private int duree;

    public Trajet() {
    }

    public Trajet(Station depart, Station arrive, String heure_deprat, int duree) {
        this.depart = depart;
        this.arrive = arrive;
        this.heure_depart = heure_deprat;
        this.duree = duree;
    }

    public String getHeure_deprat() {
        return heure_depart;
    }

    public void setHeure_deprat(String heure_deprat) {
        this.heure_depart = heure_deprat;
    }

    public Station getDepart() {
        return depart;
    }

    public void setDepart(Station depart) {
        this.depart = depart;
    }

    public Station getArrive() {
        return arrive;
    }

    public void setArrive(Station arrive) {
        this.arrive = arrive;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String toString() {
        return "Trajet de " + this.depart.toString() + " vers " + this.arrive.toString() + " à " + this.heure_depart + " pour une durée de " + this.duree + " minutes.";
    }
}
