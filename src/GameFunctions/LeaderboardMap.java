package GameFunctions;
import MainMenu.menuFXMLController;
import javafx.scene.control.Label;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class LeaderboardMap {
    public static void main (String[] args) throws IOException {
        Map<String, Integer> unsortedMap = new HashMap<String, Integer>();
        Map<String, Integer> tmpMap = new HashMap<String, Integer>();

        /* unsortedMap.put("Billy", 20);
        //writeToFile("Billy", 20);

        unsortedMap.put("Joel", 60);
        //writeToFile("Joel", 60);

        unsortedMap.put("Bob", 90);
        //writeToFile("Bob", 90);

        unsortedMap.put("Jane", 200);
        //writeToFile("Jane", 200);

        unsortedMap.put("Jobe", 70);
        //writeToFile("Jobe", 70);

        unsortedMap.put("Kroll", 30);
        //writeToFile("Kroll", 30);

        unsortedMap.put("Maddy", 37);
        //writeToFile("Maddy", 37);

        unsortedMap.put("Montey", 87);
        //writeToFile("Billy", 87);

        unsortedMap.put("Cristo", 120);
        //writeToFile("Cristo", 120);

        unsortedMap.put("New", 500);*/

        unsortedMap.put("New3", 250);
        writeToFile(unsortedMap);

        //Map<String, Integer> sortedMap = sortByValue(unsortedMap);
        tmpMap.clear();
        dumpFromFile(tmpMap);
        Map<String, Integer> sortedMap = sortByValue(tmpMap);
        clearFile();
        writeToFile(sortedMap);
    }

    public static Map<String, Integer> sortByValue(Map<String, Integer> unsortMap) {
        //make a list of map from the map
        List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());
        
        //sort list
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        //loop sorted list and put it into a new insertion orer Map
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;

    }

    //prints the scores
    public static <K, V> void printMap(Map<String, Integer> map) {
        int count = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            int value = entry.getValue();
            System.out.println(key + ": " + value);
        }
    }

    //prints top scores
    public static <K, V> void printTopMap(Map<String, Integer> map) {
        int count = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            int value = entry.getValue();
            System.out.println(key + ": " + value);
            count++;
            if (count == 5) {
                return;
            }
        }
    }

    //write map to file
    public static void writeToFile (Map<String, Integer> map) {
        //String newLine = System.getProperty("line.separator");
        File file = new File("Saves/playerScore.txt");
  
        BufferedWriter bf = null;
  
        try {
            // create new BufferedWriter for the output file
            bf = new BufferedWriter(new FileWriter(file, true));
  
            // iterate map entries
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
  
                // put key and value separated by a colon
                bf.write(entry.getKey() + ":"
                         + entry.getValue());
  
                // new line
                bf.newLine();
            }
  
            bf.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
  
                // always close the writer
                bf.close();
            }
            catch (Exception e) {
            }
        }
    }

    public static void clearFile() {
        try {
            FileWriter fw = new FileWriter("Saves/playerScore.txt", false);
            PrintWriter pw = new PrintWriter(fw, false);
            pw.flush();
            pw.close();
            fw.close();
        }
        catch (Exception e){
            System.out.println("Error detected.");
        }
    }

    //dumps file's content into a map
    public static void dumpFromFile(Map<String, Integer> map) {
        try {
            File file = new File("Saves/playerScore.txt");

            BufferedReader br = new BufferedReader(new FileReader(file));

            String line = null;
            while ((line = br.readLine()) != null) {
                //splitting the line with :
                String[] parts = line.split(":");

                //first part is name, second is number
                String name = parts[0].trim();
                Integer number = Integer.parseInt(parts[1].trim());

                //put name and score into HashMap if they aren't empty
                if (!name.equals("") && !number.equals("")) {
                    map.put(name, number);
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        sortByValue(map);
        writeToFile(map);

    }

}
