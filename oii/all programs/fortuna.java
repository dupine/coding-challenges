import java.util.*;
import java.io.*;
import java.lang.*;

public class fortuna {
    public int solve(int N, int[] V, int[] G) {

        int minSum = Integer.MAX_VALUE;
        int sum = 0;
        int newIndex = 0;

        for (int i = 0; i < N; i++) {
            sum = 0;
            newIndex = i;
            for (int j = 0; j < N; j++) {
                if(newIndex==N){
                    newIndex=0;
                }
                sum += V[newIndex]*G[j];
                newIndex++;
            }
            if(sum<minSum){
                minSum = sum;
            }
        }

        return minSum;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // se preferisci leggere e scrivere da file
        // ti basta modificare la seguente variabile
        boolean input_from_file = true;

        InputStream fin;
        OutputStream fout;
        if(input_from_file) {
            fin = new FileInputStream("input.txt");
            fout = new FileOutputStream("output.txt");
        } else {
            fin = System.in;
            fout = System.out;
        }

        Scanner scn = new Scanner(fin);
        PrintStream prnt = new PrintStream(fout);

        int T = scn.nextInt();
        for(int t = 1; t <= T; t++) {
            int N = scn.nextInt();

            int[] V = new int[N];
            int[] G = new int[N];

            for (int i = 0; i < N; i++) {
                V[i] = scn.nextInt();
            }

            for (int i = 0; i < N; i++) {
                G[i] = scn.nextInt();
            }

            fortuna solver = new fortuna();
            int risposta = solver.solve(N, V, G);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
    }
}