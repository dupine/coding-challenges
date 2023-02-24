import java.util.*;
import java.io.*;


public class Solution {

    public String solve(int R, int C) {
        String res = "";
        String top = "";
        String mid = "";
        String bot = "";
        
        if(R!=0 && C!=0){
            res+="\n.";
            for(int i=0; i<C; i++){
                res += (i==0) ? ".+" : "-+";
                top += (i==0) ? "..|" : ".|";
                mid += (i==0) ? "|.|" : ".|";
                bot += (i==0) ? "+-+" : "-+";
            }
        } else {
            return "";
        }
        res+="\n"+top+"\n"+bot;
        for(int i=0; i<R-1; i++){
            res += "\n"+mid+"\n"+bot;
        }
        
        return res;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {  
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        int T = in.nextInt();
        for(int t = 1; t <= T; t++) {
            int R = in.nextInt();
            int C = in.nextInt();
            
            Solution solver = new Solution();
            String risposta = solver.solve(R, C);
            System.out.println("Case #" + t + ": "+risposta);
        }
    }
}