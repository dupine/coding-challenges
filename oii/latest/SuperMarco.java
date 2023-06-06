import java.util.*;
import java.io.*;
import java.lang.*;

public class muro {
    public static int solve(int N, int Q, int[] L) {
         
    }

    public static void dump(int [][]map, int N){
        for (int index = 0; index < N; index++) {
            System.out.println("colore:"+ index +", quantità: "+map[index][0]);
        }System.out.println("______");

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
            int Q = scn.nextInt();
            int[] L = new int[Q];

            for (int i = 0; i < Q; i++) {
                L[i] = scn.nextInt();
            }

            int ans = solve(N, Q, L);

            prnt.format("Case #%d: %d\n", t, ans);
            fout.flush();
        }
    }
}
