import java.util.ArrayList;

/**
 * Created by Nikita on 14.09.2016.
 */

public class Main {
    public static void main(String[] args){
        Solver solver = new Solver();
        ArrayList<ArrayList<String>> ans = solver.solve("C:\\Other\\notes3.txt");
        System.out.println("M = " + solver.getM());
        for(int i = 0; i < ans.size(); ++i){
            System.out.println("Set #" + (i + 1));
            for(int j = 0; j < ans.get(i).size(); ++j){
                System.out.println(ans.get(i).get(j));
            }
        }
    }
}
