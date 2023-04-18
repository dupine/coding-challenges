import java.util.*;
import java.io.*;
import java.lang.*;

public class quadrante {
    public int solve(int N, int[] X, int[] Y) {

        // aggiungi codice...
        int risposta = 0;

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
        for(int t = 1; t <= T; t++) {
            int N = scn.nextInt();
            int[] X = new int[N];
            int[] Y = new int[N];
            for(int i = 0; i < N; i++){
                X[i] = scn.nextInt();
                Y[i] = scn.nextInt();
            }
            quadrante solver = new quadrante();
            int risposta = solver.solve(N, X, Y);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }

        scn.close();
        prnt.close();
    }
}
