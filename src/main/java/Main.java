import models.Ligne;
import models.Ligne;
import models.Station;
import models.Stations;
import org.json.simple.parser.ParseException;
import process.Bus_Reader;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
        public static void main(String[] args) throws IOException, ParseException, java.text.ParseException {

            ArrayList<Ligne> lignes = new ArrayList<Ligne>();
            Stations stations = new Stations();

            Bus_Reader bus_reader = new Bus_Reader(stations);
            Ligne ligneBus = bus_reader.lectureFichier();

            System.out.println(ligneBus.toString());

        }
}
