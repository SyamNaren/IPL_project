import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class Ipl{  
    public static void main(String[] args){
        String line="";
        try (BufferedReader br = new BufferedReader(new FileReader("/media/syam/Others/matches.csv"))) {
            HashMap<String,Integer> total_matches =new HashMap<String,Integer>();
            int team_count=1;
            while((line=br.readLine()) != null){    
                String[] values=  line.split(",");
                if(total_matches.get(values[1])==null){
                    team_count=1;
                    total_matches.put(values[1], team_count);
                }else{
                    team_count=team_count+1;
                    total_matches.put(values[1], team_count);
                }
            }
            System.out.println(total_matches);
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
