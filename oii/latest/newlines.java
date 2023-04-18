import java.util.*;
import java.io.*;
import java.lang.*;

public class newlines {
    public int[] solve(int N, int[] W) {

        int min = Integer.MAX_VALUE, max = 0;

        int sum = 0;
        int sumMax = 0;
        for (int i = 0; i < N; i++) {
            if(W[i]==-1){
                sumMax = N!=N-2 ? sum+W[i+1] : max;
                if(sumMax<min){
                    min = sumMax;
                }

                if(sum>max){
                    max = sum;
                }
                sum = 0;
            } else if(i==N-1){
                sum+=W[i];
                if(sum>max){
                    max = sum;
                }
                sum = 0;
            } else if(W[i+1]==-1){sum+=W[i];}
            else sum+=W[i]+1;
        }
        

        for (int i = 0; i < N; i++) {
            
        }

        int[] risposta = {max, min};

        return risposta;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        InputStream fin = System.in;
        OutputStream fout = System.out;

        // se preferisci leggere e scrivere da file
        // ti basta decommentare le seguenti due righe:

         fin = new FileInputStream("input.txt");
         fout = new FileOutputStream("output.txt");

        Scanner scn = new Scanner(fin);
        PrintStream prnt = new PrintStream(fout);

        int T = scn.nextInt();
        for (int t = 1; t <= T; t++) {
            int N = scn.nextInt();
            int[] W = new int[N];

            for (int i = 0; i < N; i++) {
                W[i] = scn.nextInt();
            }

            newlines solver = new newlines();
            int[] risposta = solver.solve(N, W);

            prnt.format("Case #%d: %d %d\n", t, risposta[0], risposta[1]);
            fout.flush();
        }

        scn.close();
        prnt.close();
    }
}
