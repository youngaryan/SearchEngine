
/**
* JugsState.java
* State in a jugs problem
* Phil Green 2013 version
* Heidi Christensen (heidi.christensen@sheffield.ac.uk) 2021 version
*/

import java.util.*;

public class JugsState extends SearchState {

  private int jug1Content; // content of jug1
  private int jug2Content; // content of jug2

  /**
   * constructor
   * 
   * @param j1c content of jug 1
   * @param j2c content of jug2
   */

  public JugsState(int j1c, int j2c) {
    jug1Content = j1c;
    jug2Content = j2c;
  }

  /**
   * accessor for content of jug1
   */

  public int getJug1Content() {
    return jug1Content;
  };

  /**
   * accessor for content of jug2
   */

  public int getJug2Content() {
    return jug2Content;
  };

  /**
   * goalPredicate
   * 
   * @param searcher - the current search engine
   * 
   *                 method will check whether this state matched the target
   *                 (goal) state as provided by the search engine instance
   */

  public boolean goalPredicate(Search searcher) {
    JugsSearch jugSearcher = (JugsSearch) searcher;
    int target = jugSearcher.getTargetMeasure(); // get target amount
    return ((jug1Content == target) || (jug2Content == target));
  }

  /**
   * getSuccessors
   * 
   * @param searcher - the current search engine
   * 
   *                 method will return list of possible successors
   */

  public ArrayList<SearchState> getSuccessors(Search searcher) {

    // get specific capactity parameters for this search
    JugsSearch jugSearcher = (JugsSearch) searcher;
    int cap1 = jugSearcher.getJug1Capacity();
    int cap2 = jugSearcher.getJug2Capacity();

    // calculate how much space is left in each jug
    int jug1Space = cap1 - jug1Content;
    int jug2Space = cap2 - jug2Content;

    ArrayList<JugsState> jugsStatesList = new ArrayList<JugsState>(); // the list of jugs states
    ArrayList<SearchState> searchStatesList = new ArrayList<SearchState>(); // the list of search states

    if (jug1Space > 0)
      jugsStatesList.add(new JugsState(cap1, jug2Content)); // fill jug1
    if (jug2Space > 0)
      jugsStatesList.add(new JugsState(jug1Content, cap2)); // fill jug2
    if (jug1Content != 0)
      jugsStatesList.add(new JugsState(0, jug2Content)); // empty j1
    if (jug2Content != 0)
      jugsStatesList.add(new JugsState(jug1Content, 0)); // empty j2
    if ((jug1Content != 0) && (jug2Space != 0)) { // pour from j1 into j2
      if (jug1Content > jug2Space) {
        jugsStatesList.add(new JugsState(jug1Content - jug2Space, cap2));
      } else {
        jugsStatesList.add(new JugsState(0, jug1Content + jug2Content));
      }
      ;
    }
    ;
    if ((jug2Content != 0) && (jug1Space != 0)) { // pour from j2 into j1
      if (jug2Content > jug1Space) {
        jugsStatesList.add(new JugsState(cap1, jug2Content - jug1Space));
      } else {
        jugsStatesList.add(new JugsState(jug1Content + jug2Content, 0));
      }
      ;
    }
    ;

    // cast the jugs states as search states in searchStatesList

    for (JugsState js : jugsStatesList)
      searchStatesList.add((SearchState) js);

    return searchStatesList;

  }

  /**
   * sameState - do 2 JugsSearchNodes have the same state?
   * 
   * @param s2 second state
   */

  public boolean sameState(SearchState s2) {
    JugsState js2 = (JugsState) s2;

    return ((jug1Content == js2.getJug1Content()) && (jug2Content == js2.getJug2Content()));
  }

  /**
   * toString
   */

  public String toString() {
    return "Jug State: Jug1-> " + jug1Content + " Jug2-> " + jug2Content;
  }

}
