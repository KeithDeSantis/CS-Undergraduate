public class HeuristicFunction {

    @FunctionalInterface
    public interface heuristicFunction{
        int getHeuristics(Coordinate cur, Coordinate g);
    }

    public static heuristicFunction NoHeuristics = HeuristicFunction::noHeuristics;
    public static heuristicFunction MinOfVerticalAndHorizontal = HeuristicFunction::minOfVerticalAndHorizontal;
    public static heuristicFunction MaxOfVerticalAndHorizontal = HeuristicFunction::maxOfVerticalAndHorizontal;

    public static heuristicFunction SumOfVerticalAndHorizontal = HeuristicFunction::sumOfVerticalAndHorizontal;
    public static heuristicFunction CustomHeuristic = HeuristicFunction::customHeuristic;
    public static heuristicFunction HeuristicSix = HeuristicFunction::heuristicSix;

    static int noHeuristics(Coordinate cur, Coordinate g){
        return 0;
    }

    static int minOfVerticalAndHorizontal(Coordinate cur, Coordinate g){
        return Math.min(Math.abs(cur.getI() - g.i), Math.abs(cur.getJ() - g.j));
    }

    static int maxOfVerticalAndHorizontal(Coordinate cur, Coordinate g){
        return Math.max(Math.abs(cur.getI() - g.i), Math.abs(cur.getJ() - g.j));
    }

    static int sumOfVerticalAndHorizontal(Coordinate cur, Coordinate g){
        return Math.abs(cur.getI() - g.i) + Math.abs(cur.getJ() - g.j);
    }

    static int customHeuristic(Coordinate cur, Coordinate g){
        if (cur.getI() == g.getI() || cur.getJ() == g.getJ())
            return Math.abs(cur.getI() - g.i) + Math.abs(cur.getJ() - g.j);
        else
            return Math.abs(cur.getI() - g.i) + Math.abs(cur.getJ() - g.j) + 1;
    }

    static int heuristicSix(Coordinate cur, Coordinate g){
        return customHeuristic(cur, g) * 3;
    }



}
