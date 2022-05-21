import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
class Total_matches_class{
    void print_total_matches(ArrayList<String> total_matches){
        Set<String> single_year= new HashSet<>(total_matches);
        List<String> year_list=new ArrayList<String>(single_year);
        Collections.sort(year_list);
        for(String s: year_list){
            System.out.println("Total Matches Played in "+s+" : " +Collections.frequency(total_matches,s));
        }
    }
}
class Matches_won_class{
    void print_won_matches(ArrayList<String> won_matches){
        Set<String> team_name=new HashSet<>(won_matches);
        for(String s:team_name){
            System.out.println("No.Of Matches Won By "+s+" : "+ Collections.frequency(won_matches, s));
        }
    }
}
public class Ipl{
    public static void main(String[] args){
        String line="";
        try (BufferedReader br = new BufferedReader(new FileReader("/home/syam/Documents/Ipl Project/matches.csv"))) {
            ArrayList<String> total_matches =new ArrayList<String>();
            ArrayList<String> won_matches =new ArrayList<String>();
            while((line=br.readLine()) != null){    
                String[] values=  line.split(",");
                try {
                    Integer.parseInt(values[1]);
                    total_matches.add(values[1]);
                    won_matches.add(values[4]);
                    if(Integer.parseInt(values[1])==2016){
                        String match_won= values[10]+" Won Match By ";
                        if(Integer.parseInt(values[11])==0){
                                match_won=match_won+values[12]+" Wickets";
                        }else{
                            match_won=match_won+values[11]+" Runs";
                        }
                        System.out.println(match_won);
                    }
                   
                } catch (Exception e) {}
            }
            System.out.println("***************************");
            Total_matches_class tmc=new Total_matches_class();
            tmc.print_total_matches(total_matches);
            System.out.println("***************************");
            Matches_won_class mwc=new Matches_won_class();
            mwc.print_won_matches(won_matches);
            System.out.println("***************************");
            // System.out.println(extra_runs);

            
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
