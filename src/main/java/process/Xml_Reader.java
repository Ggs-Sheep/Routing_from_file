package process;

import models.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import models.Station;

public abstract class Xml_Reader
{
    public ArrayList<Ligne> readAll(Stations stations,String path) throws ParserConfigurationException, SAXException
    {
        ArrayList<Ligne> lignes = new ArrayList<Ligne>();
        try {
            File file = new File(path);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(file);
            document.getDocumentElement().normalize();
            
            // Tram
            if (document.getDocumentElement().getNodeName().equals("reseau")) {
                NodeList nList = document.getElementsByTagName("ligne");

                for (int temp = 0; temp < nList.getLength(); temp++) {
                    Node nNode = nList.item(temp);
                    Ligne ligne = new Ligne(get_line_name(nNode.getTextContent()));
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;
                        ArrayList<Station> tram_stations = get_stations(eElement.getElementsByTagName("stations").item(0).getTextContent());
                        for (Station station:
                                tram_stations){
                            stations.addStation(station);
                        }
                        for (int i = 1; i < tram_stations.size(); i++) {
                            for (int a = 0; a < eElement.getElementsByTagName("heures-passage").getLength();a++) {
                                String[] horaires = eElement.getElementsByTagName("heures-passage").item(a).getTextContent().split(" ");
                                ligne.addTrajets(new Trajet(tram_stations.get(i-1),tram_stations.get(i),horaires[i-1],get_duration(horaires[i-1],horaires[i])));
                            }
                        }
                    }
                    lignes.add(ligne);
                }
            } else if (document.getDocumentElement().getNodeName().equals("horaires")) {

            }

        }
        catch(IOException e) {
            System.out.println(e);
        }
        return lignes;
    }

    public static ArrayList<Station> get_stations(String to_split) {

        ArrayList<Station> stations = new ArrayList<Station>();
        for (String station:
             to_split.split(" ")) {
            stations.add(new Station(station));
        }
        return stations;
    }

    public String get_line_name(String content) {
        return content.split("\n")[0];
    }

    public String[] get_time(String content) {
        return content.split(" ");
    }

    public int get_duration(String timeCode1, String timeCode2) {
        int hour1 = Integer.parseInt(timeCode1.substring(0, (timeCode1.length()/2)));
        int minute1 = Integer.parseInt(timeCode1.substring((timeCode1.length()/2)));

        int hour2 = Integer.parseInt(timeCode2.substring(0, (timeCode2.length()/2)));
        int minute2 = Integer.parseInt(timeCode2.substring((timeCode2.length()/2)));

        int duration = 0;

        while (!(Integer.toString(hour1)+":"+Integer.toString(minute1)).equals(Integer.toString(hour2)+":"+Integer.toString(minute2)))  {
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
