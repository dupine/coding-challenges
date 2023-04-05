import java.util.*;
import java.io.*;
import java.lang.*;

public class Ostacoli1 {
    
    public static int solve(int N, int L, int D, int[] X, int[] P, int[] S) {
        final int INF = 1000000000;
        int[] dp = new int[N];
        for (int i = 0; i < N; i++) {
            dp[i] = -INF;
            if (S[i] >= X[i]) dp[i] = Math.max(dp[i], P[i]);
            for (int j = 0; j < i; j++)
                if (S[i] - S[j] >= Math.abs(X[i] - X[j]))
                    dp[i] = Math.max(dp[i], dp[j] + P[i]);
        }

        int ans = 0;
        for (int i = 0; i < N; i++)
            ans = Math.max(ans, dp[i]);

        return ans;
    }
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // se preferisci leggere e scrivere da file
        // ti basta modificare la seguente variabile
        boolean input_from_file = true;

        InputStream fin;
        OutputStream fout;
        if (input_from_file) {
            fin = new FileInputStream("latest/input.txt");
            fout = new FileOutputStream("latest/output.txt");
        } else {
            fin = System.in;
            fout = System.out;
        }

        Scanner scn = new Scanner(fin);
        PrintStream prnt = new PrintStream(fout);

        int T = scn.nextInt();
        for (int t = 1; t <= T; t++) {
            int N = scn.nextInt();
            int L = scn.nextInt();
            int D = scn.nextInt();

            int[] X = new int[N];
            int[] P = new int[N];
            int[] S = new int[N];
            for (int i = 0; i < N; i++) {
                X[i] = scn.nextInt();
                P[i] = scn.nextInt();
                S[i] = scn.nextInt();
            }

            Ostacoli1 solver = new Ostacoli1();
            int risposta = solver.solve(N, L, D, X, P, S);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
    }
}