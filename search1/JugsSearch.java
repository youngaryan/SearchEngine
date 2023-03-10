
/**
*	JugsSearch.java
*
*	search for jugs problems
* Phil Green 2013 version
* Heidi Chrisensen (heidi.christensen@sheffield.ac.uk) 2021 version
*
*/

import java.util.*;

public class JugsSearch extends Search {

    private int jug1Capacity; // capacity of jug1
    private int jug2Capacity; // ........... jug2
    private int targetMeasure; // target amount to be measured

    /**
     * constructor takes jug capacities and target
     * 
     * @param c1  capacity of jug1
     * @param c2  capacity of jug2
     * @param tar target amount to be measured
     */

    public JugsSearch(int c1, int c2, int tar) {

        jug1Capacity = c1;
        jug2Capacity = c2;
        targetMeasure = tar;

    }

    /**
     * accessor for jug1 capacity
     */

    public int getJug1Capacity() {
        return jug1Capacity;
    }

    /**
     * accessor for jug2 capacity
     */

    public int getJug2Capacity() {
        return jug2Capacity;
    }

    /**
     * accessor for targetMeasure
     */

    public int getTargetMeasure() {
        return targetMeasure;
    }
}
