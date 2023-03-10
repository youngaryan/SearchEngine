
/**
*	Search.java - intended as parent class; see JugsSearch for example of how to inherit for a specialised search engine
*   Phil Green 2013 version
*   Heidi Christensen (heidi.christensen@sheffield.ac.uk) 2021 version
*/

import java.util.*;

public abstract class Search {

	protected SearchNode initNode; // initial node
	protected SearchNode currentNode; // current node

	protected ArrayList<SearchNode> open; // open - list of SearchNodes
	protected ArrayList<SearchNode> closed; // closed - .......
	protected ArrayList<SearchNode> successorNodes; // used in expand & vetSuccessors

	/**
	 * run a search
	 * 
	 * @param initState initial state
	 * @return indication of success or failure
	 */

	public String runSearch(SearchState initState) {

		// create initial node using initState
		initNode = new SearchNode(initState);
		System.out.println("Starting Search");

		// initialised the open and closed lists
		open = new ArrayList<SearchNode>();
		open.add(initNode);
		closed = new ArrayList<SearchNode>();

		// counts the number of iterations
		int iterationCount = 1;

		// core of the search
		// loop over all nodes in open;
		while (!open.isEmpty()) {

			// first print contents of open
			System.out.println("-------------------------");
			System.out.println("iteration no " + iterationCount);
			System.out.println("open is");
			for (SearchNode nn : open) {
				String nodeString = nn.toString();
				System.out.println(nodeString);
			}

			// next, select which node to explore next; selectNode() will save the selected
			// in currentNode and remove it from the open list
			selectNode();
			System.out.println("Current node " + currentNode.toString());

			// now check whether the new currentNode matched our goal
			// if we've reached the goal return
			if (currentNode.goalPredicate(this))
				return "Search Succeeds after a total of " + iterationCount + " iterations"; // success

			// if not successful call expand to explore further
			expand(); // go again

			// and update the closed list with the new currentNode
			closed.add(currentNode); // put current node on closed

			iterationCount = iterationCount + 1;
		}

		// if we've been through all nodes in the open list and haven't reached our
		// target, we will reach to here and we know that the search has failed.
		return "Search Fails after a total of " + iterationCount + " iterations"; // out of the while loop - failure

	}

	//
	// expand from current node and add those nodes to the open list
	//
	private void expand() {

		// get all successor nodes - ArrayList SearchNodes
		successorNodes = currentNode.getSuccessors(this); // pass search instance

		// filter out unwanted nodes; dynamic programming check.
		vetSuccessors();

		// add all surviving nodes to open
		for (SearchNode snode : successorNodes)
			open.add(snode);

	}

	//
	// vet the successors - reject any whose states are on open or closed
	//
	private void vetSuccessors() {

		ArrayList<SearchNode> vslis = new ArrayList<SearchNode>();

		for (SearchNode snode : successorNodes) {
			if (!(onClosed(snode)) && !(onOpen(snode)))
				vslis.add(snode);
		}

		successorNodes = vslis;
	}

	//
	// onClosed - is the state for a node the same as one on closed?
	//
	private boolean onClosed(SearchNode newNode) {
		boolean ans = false;
		for (SearchNode closedNode : closed) {
			if (newNode.sameState(closedNode))
				ans = true;
		}
		return ans;
	}

	//
	// onOpen - is the state for a node the same as one on closed?
	//
	private boolean onOpen(SearchNode newNode) {
		boolean ans = false;
		for (SearchNode openNode : open) {
			if (newNode.sameState(openNode))
				ans = true;
		}
		return ans;
	}

	//
	// select the next node - depth-first for now - remove it from open
	//
	private void selectNode() {
		int osize = open.size();
		currentNode = (SearchNode) open.get(osize - 1); // last node added to open
		open.remove(osize - 1); // remove it
	}

}
