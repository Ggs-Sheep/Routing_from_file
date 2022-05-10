package process;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

public class Bus_Reader {
    private ArrayList<String> stations;

    public Bus_Reader() {
        stations = new ArrayList<>();
    }

    public void lectureFichier(){
        try {
            // create object mapper instance
            ObjectMapper mapper = new ObjectMapper();

            // convert JSON file to map
            Map<?, ?> map = mapper.readValue(Paths.get("res/bus.json").toFile(), Map.class);

            // print map entries

            System.out.println(map.get("horaires"));

            for (Map.Entry<?, ?> entry : map.entrySet()) {
                //System.out.println(entry.getKey('horaires'));
                //System.out.println(entry.getKey() + "=" + entry.getValue() + "\n");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}