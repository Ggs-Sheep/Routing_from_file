import models.*;
import models.Ligne;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;
import process.Bus_Reader;
import process.Metro_Reader;
import process.Xml_Reader;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
        public static void main(String[] args) throws IOException, ParseException, java.text.ParseException, ParserConfigurationException, SAXException {

            ArrayList<Ligne> lignes = new ArrayList<Ligne>();
            Stations stations = new Stations();

            // Readers
            Bus_Reader bus_reader = new Bus_Reader(stations);
            Metro_Reader metro_reader = new Metro_Reader();
            ArrayList<Ligne> lignesXML = Xml_Reader.readAll(stations, "res/tram.xml");

            // Lignes
            Ligne ligneBus = bus_reader.lectureFichier();
            Ligne ligneMetro = new Ligne("metro", metro_reader.readMetro(stations));
            Ligne ligneTram1 = lignesXML.get(0);
            Ligne ligneTram2 = lignesXML.get(1);


            // Affichage
            //System.out.println(ligneMetro);
            //System.out.println(ligneBus);
            //System.out.println(ligneTram1);
            //System.out.println(ligneTram2);

        }
}
