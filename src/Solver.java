import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Nikita on 15.09.2016.
 */
public class Solver {
    private ArrayList<String> words;
    private int M;

    public Solver(){
        M = 0;
        words = new ArrayList<>();
    }

    public int getM(){
        return M;
    }

    private ArrayList<String> buildListOfWords(String filePath){
        ArrayList<String> res = new ArrayList<>();
        try(FileReader reader = new FileReader(filePath))
        {
            int c;
            StringBuffer newWord = new StringBuffer();
            while((c=reader.read())!=-1){
                if((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')){
                    newWord.append((char)c);
                }
                else{
                    if(newWord.length() > 0) res.add(newWord.toString());
                    newWord = new StringBuffer();
                }
            }
            if(newWord.length() > 0) res.add(newWord.toString());
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        return res;
    }

    public ArrayList<ArrayList<String>> solve(String filePath){
        words = buildListOfWords(filePath);

        Graph graph = new Graph(words);
        M = graph.getM();
        ArrayList<ArrayList<Integer>> localRes = graph.findAllCliques();
        ArrayList<ArrayList<String>> res = new ArrayList<>();
        for(int i = 0; i < localRes.size(); ++i){
            res.add(new ArrayList<>());
            int cur = res.size() - 1;
            for(int j = 0; j < localRes.get(i).size(); ++j){
                res.get(cur).add(words.get(localRes.get(i).get(j)));
            }
        }
        return res;
    }
}
