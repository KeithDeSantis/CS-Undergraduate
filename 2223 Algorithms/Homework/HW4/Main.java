import java.util.Random;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        System.out.println("Keith DeSantis Homework 4");

        //TODO Task Set 1 Number 1)
        System.out.println("\nTask Set 1 Number 1:");
        System.out.println("\nInitializing graph...");
        JSONScanner JScan = new JSONScanner("network_large_directed_multiEdge.json");
        SymbolGraph ourGraph = JScan.scan();
        System.out.println("Graph finished.\n\n");

        //TODO Task Set 1 Number 2)
        System.out.println("Task Set 1 Number 2:\n");
        System.out.println("Proof of DFS and BFS:\n");
        BreadthFirstPaths BFP = new BreadthFirstPaths(ourGraph.G, 0);
        DepthFirstPaths DFP = new DepthFirstPaths(ourGraph.G, 0);
        System.out.println("Searches complete. Here is one example path from DPS:");
        Iterable<Integer> firstPath = DFP.pathTo(7);
        firstPath = (Stack<Integer>) firstPath;
        for(int u = 0; u < 23; u++)
        {
            System.out.println(ourGraph.nodeST.get(ourGraph.keys[((Stack<Integer>) firstPath).first.getItem()]).longName);
            if(u!=22) System.out.println("v");
            ((Stack<Integer>) firstPath).first= ((Stack<Integer>) firstPath).first.next;
        }

        //TODO Task Set 2 Number 1)
        System.out.println("\n\nTask Set 2 Number 1:\n");
        System.out.println("Finding all North American Neighbors of giCentre...");
        int giCentreIndex = ourGraph.index("giCentre");
        int[] usedAlready = new int[10];
        for(Bag.NODE neighbor = ourGraph.G.adj[giCentreIndex].first; neighbor.next != null; neighbor = neighbor.next)
        {
            boolean isValid = true;
            if(ourGraph.nodeST.get(ourGraph.name((int) neighbor.getItem())).continent.equals("North America")) {
                for (int integer : usedAlready) {
                    if ((int) neighbor.getItem() == integer) isValid = false;
                }
                if (isValid)
                {
                    System.out.println("Found " + ourGraph.name((int) neighbor.getItem()) + " as a North American neighbor of giCentre.\n\n");
                    usedAlready[0] = (int) neighbor.getItem();
                }
            }
        }

        //TODO Task Set 2 Number 2)
        System.out.println("Task Set 2 Number 2:\n");
        int laneIndex = ourGraph.index("Lane Harrison");
        BreadthFirstPaths BFP2 = new BreadthFirstPaths(ourGraph.G, laneIndex);

        //TODO get each institution
        // get shortest path from lane to each institution
        // get shortest path from institution to rob
        // get total length of those two sub-paths
        // choose shortest one

        int[] indicesOfInstitutions = new int[11];
        int count = 0;
        for(int indexForNodes = 0; indexForNodes < ourGraph.keys.length; indexForNodes++) // Get all indices of nodes that are institutions
        {
            if (ourGraph.nodeST.get(ourGraph.keys[indexForNodes]).isInstitution)
            {
                indicesOfInstitutions[count] = indexForNodes;
                count++;
            }
        }
        int[] lengthsOfPaths = new int[11];
        for (int i = 0; i < indicesOfInstitutions.length; i++)
        {
            Iterable<Integer> tempLength = BFP2.pathTo(indicesOfInstitutions[i]);
            int lengthOfPath = 0;
            for (Integer k : tempLength) lengthOfPath++;
            BreadthFirstPaths tempBFPFromInstitute = new BreadthFirstPaths(ourGraph.G, indicesOfInstitutions[i]);
            Iterable<Integer> tempLength2 = tempBFPFromInstitute.pathTo(indicesOfInstitutions[i]);
            for (Integer k : tempLength2) lengthOfPath++;
            lengthsOfPaths[i] = lengthOfPath;
        }

        int min = lengthsOfPaths[0]; // Find the minimum length and the index that correlates to it
        int indexOfMin = 0;
        for(int j = 0; j < lengthsOfPaths.length; j++)
        {
            if(lengthsOfPaths[j] < min)
            {
                min = lengthsOfPaths[j];
                indexOfMin = j;
            }
        }
        System.out.println("The institute on the shortest path between Lane and Rob is \"" + ourGraph.keys[indicesOfInstitutions[indexOfMin]] + "\" with length of " + min + ".\n\n");

        //TODO Task Set 3 Exploratory Analysis
        // I will be testing the average time of DFS and BFS over many iterations from different nodes, as well as seeing
        // which type of search is more susceptible to edge cases in runtime.

        System.out.println("Task Set 3: Testing Search Times for DFS and BFS:\n");

        System.out.println("Performing Depth First and Breadth First Searches on 50 random nodes...\n");

        int[] allowList = IntStream.generate( () ->
                new Random().nextInt(75)).limit(50).toArray();
        Integer[] realInput = IntStream.of( allowList ).boxed().toArray( Integer[]::new ); // 25 random nodes to test from

        Stopwatch dfsTimer = new Stopwatch();
        for(Integer node : realInput)
        { DepthFirstPaths temp = new DepthFirstPaths(ourGraph.G, node); }
        double dfsTime = dfsTimer.elapsedTime();
        Stopwatch bfsTimer = new Stopwatch();
        for(Integer node : realInput)
        { BreadthFirstPaths temp = new BreadthFirstPaths(ourGraph.G, node); }
        double bfsTime = bfsTimer.elapsedTime();

        System.out.println("Time elapsed for Depth First Time on 50 random starting nodes: " + dfsTime);
        System.out.println("\nTime elapsed for Breadth First Time on 50 random starting nodes: " + bfsTime);

        System.out.println("\n\nAssignment finished.\n\n");
    }
}