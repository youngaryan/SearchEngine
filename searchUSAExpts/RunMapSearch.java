
/**
  * RunMapSearch.java
  *
  *
  * Created: Fri Dec  1 12:59:33 2000
  *
  * 
  * Phil Green 2013 version
  * Heidi Christensen 2021 version
  run a map traversal
  this version allows for A*

*/

import java.util.*;

public class RunMapSearch {
  public static void main(String[] arg) {

    Carta mapUSA = new Carta();
    mapUSA.mapFromFile("USA_links.csv");
    mapUSA.estsFromFile("DenverEsts90.csv");

    String[] USACities = { "Albany", "Atlanta", "Augusta", "Austin", "Bismarck", "Boise", "Boston", "Chicago", "Dallas",
        "Denver", "Detroit", "Helena", "Indianapolis", "Jefferson City", "Las Vegas", "Los Angeles", "Memphis", "Miami",
        "New Orleans", "New York", "Oklahoma City", "Phoenix", "Pierre", "Salem", "Salt Lake City", "San Francisco",
        "Santa Fe", "Seattle", "Tallahassee", "Washington DC" };
//
//     System.out.println(map1.toString());
//     System.out.println(map1.getLinks("Start"));

    // add your solution code here ....
  }
}
