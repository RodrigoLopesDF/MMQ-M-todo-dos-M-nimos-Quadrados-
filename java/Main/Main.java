
package Main;

import Work.FunctionPair;
import Work.InvalidEntryException;
import Work.MMQ;
import Work.NullPivotException;
import static Work.Solver.solveFunction;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws NullPivotException, InvalidEntryException {
        int order = 12;
        int quantity = 2048;
        
        ArrayList<FunctionPair> solutions = solveFunction(quantity);
        ArrayList<Double> results = MMQ.perform(order, solutions);
        int i = 0;
        for(Double value:results){
            if(i==0)
                System.out.printf("(1/%d)", Math.round(1/value));
            else if(i==1)
                System.out.printf(" + (1/%d)x", Math.round(1/value));
            else
                System.out.printf(" + (1/%d)x^%d", Math.round(1/value), i);
            i++;
        }
        System.out.println();
    }
}

