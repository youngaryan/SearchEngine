public class RunJugsSearch2 {
    public static void main(String[] arg) {

        // Define the search problem's parameter: create instance of main class with
        // parameters (size of jug1, size of jug2,
        // target measure amount)
        JugsSearch searcher = new JugsSearch(7, 4, 1);

        // Define the start (initial) state as both jugs being empty
        SearchState initState = (SearchState) new JugsState(0, 0);

        // Set off the search engine
        String res = searcher.runSearch(initState);

        // Print out the results
        System.out.println(res);
    }
}


