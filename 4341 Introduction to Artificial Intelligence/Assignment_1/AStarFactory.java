public class AStarFactory {
    private AStarFactory(){
    }

    public static AStar produceAstarWithSpecificHeuristics(int choice){
        switch (choice){
            case 1: return new AStar(HeuristicFunction.NoHeuristics);
            case 2: return new AStar(HeuristicFunction.MinOfVerticalAndHorizontal);
            case 3: return new AStar(HeuristicFunction.MaxOfVerticalAndHorizontal);
            case 4: return new AStar(HeuristicFunction.SumOfVerticalAndHorizontal);
            case 5: return new AStar(HeuristicFunction.CustomHeuristic);
            case 6: return new AStar(HeuristicFunction.HeuristicSix);
            default: throw new IllegalArgumentException("unrecognized choice of heuristics");
        }
    }
}
