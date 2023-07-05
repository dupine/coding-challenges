import java.util.*;
import java.io.*;
import java.lang.*;

public class ois_scrigni {
    public static double solve(long N) {
        return (double) (((N-1) * (N)) / 2) / 2;
        //return (double)rec(N-1)/2;
    }

    public static int rec(int n){
        if(n==1){
            return 1;
        }
        return n + rec(n-1);
    }

    public static void dump(int [][]map, int N){
        for (int index = 0; index < N; index++) {
            System.out.println("colore:"+ index +", quantità: "+map[index][0]);
        }System.out.println("______");

    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
    
        String[] input = br.readLine().split(" ");
        long N = Long.parseLong(input[0]);
    
        ois_scrigni solver = new ois_scrigni();
        double risposta = solver.solve(N);
    
        bw.write("" + risposta);
        bw.newLine();
        bw.flush();
        bw.close();
    }
}
