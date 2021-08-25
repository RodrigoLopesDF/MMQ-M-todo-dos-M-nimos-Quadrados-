
package Work;

import java.util.ArrayList;

public class Solver {
    // Uma função: f(x) = e^x
    private static double f(double x){
        return Math.pow(Math.E ,x);
    }
    
    // Uma função: g(x) = cos (x)
    private static double g(double x){
        return Math.cos(x);
    }
    
    // Uma função: h(x) = sin (x)
    private static double h(double x){
        return Math.sin(x);
    }
    
    // Uma função: p(x) = x^5
    private static double p(double x){
        return Math.pow(x,5);
    }
    
    // Gera o conjunto de q soluções entre 1 e 0 para 
    public static ArrayList<FunctionPair> solveFunction(int q) throws InvalidEntryException{
        ArrayList<FunctionPair> array = new ArrayList();
        // Uma quantidade negativa de itens gera uma exceção
        if(q<=0)
            throw new InvalidEntryException("The value "+q+" is an invalid entry!");
        
        double x;
        for(double i=1;i<=q;i++){
            x = (2*i/(q+1)) - 1;
            array.add(new FunctionPair(x,p(x)));
        }
        
        return array;
    }
}
