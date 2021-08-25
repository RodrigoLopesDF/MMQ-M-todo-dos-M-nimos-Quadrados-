
package Main;

import Work.FunctionPair;
import Work.InvalidEntryException;
import Work.MMQ;
import Work.NullPivotException;
import static Work.Solver.solveFunction;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws NullPivotException, InvalidEntryException {
        int order = 17;
        int quantity = 2048;
        
        ArrayList<FunctionPair> solutions = solveFunction(quantity);
        ArrayList<Double> results = MMQ.perform(order, solutions);
        results.forEach(value -> {
            System.out.printf("%d\n", Math.round(value));
        });
    }
}
