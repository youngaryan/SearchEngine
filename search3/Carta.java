
/*
 * Carta.java
 *
 * a map for searching
 * called Carta to avoid confusion with Interface map
 *
 *
 * 
 * Phil Green 2013 version
 * Heidi Christensen (heidi.christensen@sheffield.ac.uk) 2021 version
 */

import java.util.*;
import java.io.*;


public class Carta {
  private ArrayList<MapLink> links; // all the links
  private HashSet cities; // all the cities

  // accessors
  public ArrayList<MapLink> getAllLinks() {
    return links;
  }

  public HashSet getCities() {
    return cities;
  }

  // constructor - empty map
  public Carta() {
    links = new ArrayList<MapLink>();
  }

  /*
   * addLink adds a link to a map
   * 
   * @param c1 city 1
   * @param c2 city 2
   * @param c  cost of the link
   */
  public void addLink(String c1, String c2, int c) {
    MapLink ml = new MapLink(c1, c2, c);
    links.add(ml);
  }

  public String toString() {
    StringBuffer buf = new StringBuffer("MAP WITH LINKS\n");
    for (MapLink lnk : links) {
      String lstr = lnk.toString();
      buf.append(lstr + "\n");
    }
    return buf.toString();
  }

  /*
   * getLinks returns all links to/from a given city
   * 
   * @param city - the city
   * @return ArrayList of links
   */
  public ArrayList<MapLink> getLinks(String city) {
    ArrayList<MapLink> clinks = new ArrayList<MapLink>();
    for (MapLink l : links) {
      if ((city.compareTo(l.getCity1()) == 0) || (city.compareTo(l.getCity2()) == 0))
        clinks.add(l);
    }

    return clinks;
  }

  /*
   * costbetween returns cost between 2 cities
   * 
   * @param c1 city 1
   * @param c2 city 2
   */
  public int costbetween(String c1, String c2) {
    ArrayList<MapLink> c1links = getLinks(c1);
    int ans = -1;
    Iterator i = c1links.iterator();
    while (i.hasNext() && (ans < 0)) {
      MapLink l = (MapLink) i.next();
      if (c2.equals(l.getCity1()) || c2.equals(l.getCity2())) {
        ans = l.getCost();
      }
    }
    return ans;
  }

  /*
   * mapFromFile reads a map from file
   * 
   * @param fname - the file name
   */

  public void mapFromFile(String fname) {
    File mapFile = new File (fname);

    Scanner scanner;
    try {
      scanner = new Scanner (mapFile);
  
      scanner.useDelimiter(",");

      String c1;
      String c2;
      int dist;
      String fline;
      while (scanner.hasNext()) {
        
        // read the next line from scanner and split 
        String[] inputArray = scanner.nextLine().split(",");
        c1 = inputArray[0];
        c2 = inputArray[1];
        dist = Integer.parseInt(inputArray[2]);
       
        //System.out.print("c1 =" + c1 + "; c2 =" + c1 + "; dist= " + dist +"\n");

        links.add(new MapLink(c1, c2, dist));
      }
      scanner.close();
    }
    catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    findcities(); // uses the links to find the cities
  }

  // find all cities on the map
  private void findcities() {
    cities = new HashSet();
    for (MapLink l : links) {
      cities.add(l.getCity1());
      cities.add(l.getCity2());
    }
  }
}
