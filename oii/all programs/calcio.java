import java.util.*;
import java.io.*;
import java.lang.*;

public class calcio {
    public int solve(int N, int M, int K, int A, int B, int[] X, int[] Y) {
        int min = Integer.MAX_VALUE;
        int a[][] = new int[N][M];
        for (int i = 0; i < K; i++) {
            a[X[i]][Y[i]] += 1; 
        }

        int psa[][] = new int[N][M];

        psa[0][0] = a[0][0];
        // riempo la prima riga e la prima colonna
        for (int i = 1; i < N; i++){ psa[i][0] = psa[i - 1][0] + a[i][0]; }
        for (int i = 1; i < M; i++){ psa[0][i] = psa[0][i - 1] + a[0][i]; }

        for (int i = 1; i < N; i++){
            for (int j = 1; j < M; j++)
                // elemento sopra +  elemento sinistra - elemento sopra a sinistra + elemento stesso
                psa[i][j] = ( psa[i - 1][j] + psa[i][j - 1] ) - psa[i - 1][j - 1] + a[i][j];
        }

        int dif = 0;
        for (int i = A-1; i < N; i++) {
            for (int j = B-1; j < M; j++) {

                dif = psa[i][j] - ((j-B<0) ? 0 : psa[i][j-B]) - ((i-A<0) ? 0 : psa[i-A][j]) + ((i-A<0 || j-B<0) ? 0 : psa[i-A][j-B]);
                if(dif<min){
                    min = dif;
                }
                
            } 
        }
        return min;
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
            int M = scn.nextInt();
            int K = scn.nextInt();
            int A = scn.nextInt();
            int B = scn.nextInt();

            int[] X = new int[K];
            int[] Y = new int[K];

            for (int i = 0; i < K; i++) {
                X[i] = scn.nextInt();
                Y[i] = scn.nextInt();
            }

            calcio solver = new calcio();
            int risposta = solver.solve(N, M, K, A, B, X, Y);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
    }
}