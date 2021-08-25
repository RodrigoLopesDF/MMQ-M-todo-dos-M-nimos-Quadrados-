
package Work;

import java.util.ArrayList;

public class MMQ {
    public static ArrayList<Double> perform (int order,ArrayList<FunctionPair> solutions) throws NullPivotException{
        order ++; // Aumenta o termo para que se tenha no mínimo uma matriz 1x1;
        ArrayList<Double> result = new ArrayList(order);
        ArrayList<ArrayList<Double>> matrix = getMatrix(order,solutions); // Adquire a matriz que contenha o método dos mínimos quadrados;
        
        matrix = organizeMatrix(matrix); // Organiza a matrix, para garantir que todos os pivot tenham o maior valor absoluto possível, em relação a coluna abaixo;
        for(int i=0;i<order;i++)
            if (matrix.get(i).get(i) == 0) // Em alguns casos, o pivot pode se tornar nulo, o que deverá resultar em um erro quando zerar a coluna;
                throw new NullPivotException("Solving for the matrix resulted in a null pivot, unable to continue!");
            else
                matrix = nullifyCollumn(i,matrix); // Torna a coluna nula, com exceção do pivot, cujo o valor é igual a 1;
        
        // A matriz resultante é de 
        for(ArrayList<Double> array:matrix)
            result.add(array.get(order));
        return result;
    }
    
    private static ArrayList<ArrayList<Double>> nullifyCollumn(int collumn, ArrayList<ArrayList<Double>> matrix){
        ArrayList<Double> mainLine = matrix.get(collumn);
        ArrayList<Double> line;
        
        // Adquire o pivot, isto é, o termo da diagonal principal;
        double pivot = mainLine.get(collumn);
        for(int i=0;i<mainLine.size();i++) // Divide todos os termos da linha pelo pivot, incluindo o próprio pivot;
            mainLine.set(i, mainLine.get(i)/pivot);
        
        for(int i=0;i<matrix.size();i++)
            if(i==collumn) // Troca a linha principal pela sua versão com o pivot igual a 1;
                matrix.set(i, mainLine);
            else { 
                line = matrix.get(i);
                pivot = line.get(collumn);
                for(int j=0;j<line.size();j++) // Subtrai todas as linhas (com exceção da principal) por um múltiplo da linha principal, efetivamente zerando a coluna
                    line.set(j, line.get(j) - (mainLine.get(j) * pivot));
                matrix.set(i, line);
            }
        return matrix;
    }
    
    private static ArrayList<ArrayList<Double>> organizeMatrix(ArrayList<ArrayList<Double>> matrix) throws NullPivotException{
        int size = matrix.size();
        ArrayList<ArrayList<Double>> resulting_matrix = new ArrayList(size);
        
        double max;
        double newValue;
        int maxIndex;
        
        for(int i=0;i<size;i++) {
            max = Math.signum(matrix.get(i).get(i));
            maxIndex = i;
            for(int j=i+1;j<size;j++){
                newValue = Math.signum(matrix.get(j).get(i));
                // Determina a linha com o maior valor em módulo da coluna analisada.
                if(max < newValue){
                    max = newValue;
                    maxIndex = j;
                }
            }
            if(max==0) // Se o maior valor encontrado é nulo, retorna um erro por não ser possível pivotear o código com este resultado
                throw new NullPivotException("The greater pivot value for line "+i+" is null, unable to continue!");
            else { // troca a maior linha para ques esta esteja na maior posição;
                resulting_matrix.add(matrix.get(maxIndex));
                if(maxIndex != i)
                    matrix.set(maxIndex, matrix.get(i));
            }
        }
        return resulting_matrix;
    }
    
    private static ArrayList<ArrayList<Double>> getMatrix(int order, ArrayList<FunctionPair> solutions){
        
        // Adquire as somas de potências de x. É adquirido potências até da ordem 2m-1;
        ArrayList<Double> sums_of_x = new ArrayList(2*order);
        for (int i=0;i<(2*order-1);i++){
            double sum = 0;
            for(FunctionPair s:solutions)
                sum += Math.pow(s.getX(),i);
            sums_of_x.add(sum);
        }
        
        // Adquire as somas de potências de x com y. É adquirido potências até da ordem m;
        ArrayList<Double> sums_of_y = new ArrayList(order);
        for (int i=0;i<order;i++){
            double sum = 0;
            for(FunctionPair s:solutions)
                sum += s.getY()*Math.pow(s.getX(),i);
            sums_of_y.add(sum);
        }
        
        // Organiza a matrix a solução com os somatóris realizados;
        ArrayList<ArrayList<Double>> matrix = new ArrayList(order+1);
        for (int i=0;i<order;i++){
            ArrayList<Double> array = new ArrayList(order+2);
            for (int j=0;j<=order;j++){
                if(j!=order)
                    array.add(sums_of_x.get(i+j));
                else
                    array.add(sums_of_y.get(i));
            }
            matrix.add(array);
        }
        return matrix;
    }
}
