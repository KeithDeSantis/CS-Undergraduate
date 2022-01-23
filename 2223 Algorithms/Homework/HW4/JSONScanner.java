import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class JSONScanner {
    String pathToJSON;

    public JSONScanner(String thePathToJSON)
    {
        pathToJSON = thePathToJSON;
    }

    public SymbolGraph scan()
    {

        JSONObject JSON = null;

        try {
            JSON = (JSONObject) new JSONParser().parse(new FileReader(pathToJSON));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }


        SymbolGraph ourGraph = new SymbolGraph(75);
        JSONArray nodes = (JSONArray) Objects.requireNonNull(JSON).get("nodes");

        int indexOfNodeOn = 0;
        for (Object currentNode : nodes) // adding Nodes
        {
            JSONObject theNodeJSON = (JSONObject) currentNode;
            Node theNode = new Node();

            theNode.continent = (String) theNodeJSON.get("continent");
            theNode.username = (String) theNodeJSON.get("shortName");
            theNode.id = (long) theNodeJSON.get("id");
            theNode.isInstitution = (theNodeJSON.get("type")).equals("institution");
            theNode.longName = (String) theNodeJSON.get("name");

            ourGraph.nodeST.put(theNode.longName, theNode); // Placing node in node HT
            ourGraph.st.put(theNode.longName, indexOfNodeOn); // Placing node in index HT
            ourGraph.keys[indexOfNodeOn] = theNode.longName; // Placing the key in the adj parallel array
            indexOfNodeOn++;
        }


        int count = 0;

        for (Object currentNode : nodes) // adding Edges
        { //add edges
            JSONObject theNewNodeJSON = (JSONObject) currentNode;
            for(Object neighID : (JSONArray) theNewNodeJSON.get("neighbors"))
            {
                int otherEnd = -999;
                for (String tempName : ourGraph.keys)
                {
                    if(ourGraph.nodeST.get(tempName).id == (long) neighID) { otherEnd = ourGraph.index(tempName); }
                }
                ourGraph.G.addEdge(count, otherEnd);
            }
            count++;
        }

        return ourGraph;
    }
}
