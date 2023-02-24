import java.util.*;
import java.io.*;
import java.lang.*;

public class ois_truffa {
    public int solve(int N, Integer[] C, int sum) {
        Arrays.sort(C);
        long sum2 = 0; 
        for (int i = 0; i < N; i++) {
            sum2 += (C[i]*-1)*2;
            if(sum+sum2>=0){
                return i+1;
            }
        }

        return 0;
    }

        public static void main(String[] args) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
    
        int N = Integer.parseInt(br.readLine());
        int sum = 0;
        Integer[] C = new Integer[N];
        String[] input = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            C[i] = Integer.parseInt(input[i]);
            sum += C[i];
        }
    
        ois_truffa solver = new ois_truffa();
        int risposta = solver.solve(N, C, sum);
    
        bw.write("" + risposta);
        bw.newLine();
        bw.flush();
        bw.close();
    }
}