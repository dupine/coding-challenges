import java.util.*;
import java.io.*;
import java.lang.*;

public class muro {
    public static int solve(int N, int Q, int[] L) {

        // aggiungi codice...
        int risposta = 42;

        int muro[] = new int[N];

        int iStart = 0;
        for (int i = Q-1; i >= 0; i--) {
            if(L[i]==N) {iStart = i; break;}
        }

        int map[][] = new int[Q+1][2];
        map[0][0] = N;

        for (int index = iStart; index < Q; index++) {
            map[index][0] = L[index];
        }

        int pos = 0;
        int inizio = 0;

        //dump(map, Q+1);

        //per ogni colore
        for (int index = iStart; index < Q; index++) {

            //decido da dove dipingere
            if(map[muro[pos]][0]<=L[index]){
                int x = 0;
                int max = 0;
                for (int i = 0; i < map.length; i++) {
                    if(map[i][0]>max){
                        x = map[i][1];
                        max = map[i][0];
                    }
                }
                pos = map[x][1];
            }

            inizio = pos;
            // dipingo
            for (int i = 0; i < L[index]; i++) {
                if(i >= N) pos=0;

                if(pos<N){
                    // se cancello un colore
                    map[muro[pos]][0]--;

                    muro[pos++] = index+1;
                }
            }
            //dump(map, Q+1);
            if(pos >= N) pos=0;
            map[index][1] = inizio;
        }

        int x = 0;
        for (int i = 0; i < map.length; i++) {
            if(map[i][0]!=0) x++;
        }
        return x;
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
