import java.util.*;
import java.io.*;
import java.lang.*;

public class biglietti {
    public int solve(int N, int M, int A, int B) {

        int money = 0;
        while(N>0){
            if(N*A<B && N*A<A*M){
                return money+=N*A; 
            } else {
                if(M*A<B){
                    money += M*A;
                    N-=M;
                } else {
                    money += B;
                    N-=M;
                }                 
            }
        }

        return money;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
    
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        int A = Integer.parseInt(input[2]);
        int B = Integer.parseInt(input[3]);
    
        biglietti solver = new biglietti();
        int risposta = solver.solve(N, M, A, B);
    
        bw.write("" + risposta);
        bw.newLine();
        bw.flush();
        bw.close();
    }

}