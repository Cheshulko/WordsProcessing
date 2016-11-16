import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Nikita on 15.09.2016.
 */
public class Graph {
    private ArrayList<ArrayList<Integer>> listOfEdges;
    private ArrayList<ArrayList<Integer>> listOfNOTEdges;
    private int dimension;
    private int M;

    private int getHammingDistance(String f, String s){
        int res = Math.abs(f.length() - s.length());
        for(int i = 0; i < Math.min(f.length(), s.length()); ++i){
            if(f.charAt(i) != s.charAt(i)) res++;
        }
        return res;
    }

    public int getM(){
        return M;
    }

    public Graph(ArrayList<String> words){
        dimension = words.size();
        listOfEdges = new ArrayList<>();
        listOfNOTEdges = new ArrayList<>();
        for(int i = 0; i < dimension; ++i){
            listOfEdges.add(new ArrayList<>());
            listOfNOTEdges.add(new ArrayList<>());
        }
        M = 0;
        for(int i = 0; i < words.size(); ++i){
            for(int j = i + 1; j < words.size(); ++j){
                M = Math.max(M, getHammingDistance(words.get(i), words.get(j)));
            }
        }

        for(int i = 0; i < words.size(); ++i){
            for(int j = i + 1; j < words.size(); ++j){
                if(getHammingDistance(words.get(i), words.get(j)) == M){
                    listOfEdges.get(i).add(j);
                    listOfEdges.get(j).add(i);
                }
                else{
                    listOfNOTEdges.get(i).add(j);
                    listOfNOTEdges.get(j).add(i);
                }
            }
        }
    }

    private void subtractSet(ArrayList<Integer> set, int vertex){
        for (int i = 0; i < set.size(); i++){
            if (set.get(i) == vertex)
                set.remove(i);
        }
    }

    private void subtractSet(ArrayList<Integer> set1, ArrayList<Integer> set2)    {
        for (int i = 0; i < set1.size(); i++)
            for (int j = 0; j < set2.size(); j++)
                if (set1.size() != 0 && i < set1.size())
                    if (set1.get(i) == set2.get(j))
                        set1.remove(i);
    }

    public ArrayList<ArrayList<Integer>> findAllCliques(){
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        ArrayList<Integer> clique = new ArrayList<>(); // M
        ArrayList<Integer> vertex = new ArrayList<>(); // K
        ArrayList<Integer> usedVertex = new ArrayList<>(); //P
        ArrayList<Integer> GS = new ArrayList<>();
        int v;
        Stack<Integer> stackV = new Stack<>();
        Stack<ArrayList<Integer>> stackClique = new Stack<>();
        Stack<ArrayList<Integer>> stackVertex = new Stack<>();
        Stack<ArrayList<Integer>> stackUsed = new Stack<>();

        for(int i = 0; i < dimension; ++i)
            vertex.add(i);

        while (vertex.size() != 0 || clique.size() != 0){
            if (vertex.size() != 0){
                v = vertex.get(0);
                stackClique.push(new ArrayList<>(clique));
                stackVertex.push(new ArrayList<>(vertex));
                stackUsed.push(new ArrayList<>(usedVertex));
                stackV.push(v);
                clique.add(v);
                GS = listOfNOTEdges.get(v);
                subtractSet(vertex, GS);
                subtractSet(vertex, v);
                subtractSet(usedVertex, GS);
            }
            else
            {
                if (usedVertex.size() == 0) //клика найдена
                    res.add(new ArrayList<>(clique));
                clique = stackClique.pop();
                vertex = stackVertex.pop();
                usedVertex = stackUsed.pop();
                v = stackV.pop();
                subtractSet(vertex, v);
                usedVertex.add(v);
            }
        }
        return res;
    }
}
