package models;

public class Trajet {
    private Station depart;
    private Station arrive;
    private int duree;

    public Trajet() {
    }

    public Trajet(Station depart, Station arrive, int duree) {
        this.depart = depart;
        this.arrive = arrive;
        this.duree = duree;
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
}
