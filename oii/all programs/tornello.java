import java.util.*;
import java.io.*;
import java.util.ArrayList;
import java.lang.*;

public class tornello {
    public int solve(int N, int[] s) {

        int pInStanza = 0;
        int pFuoriStanza = 0;
        int pp = 0;
        for (int i = 0; i < N; i++) {
            if(s[i]==-1 && pInStanza==0){
                pFuoriStanza++;
            } else if(s[i]==-1){
                pInStanza--;
                pFuoriStanza++;
            }

            if(s[i]==1 && pFuoriStanza==0){
                pInStanza++;
            } else if(s[i]==1){
                pFuoriStanza--;
                pInStanza++;
            }
        }
        return pFuoriStanza+pInStanza;
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
        for (int t = 1; t <= T; t++) {
            int N = scn.nextInt();
            int s[] = new int[N];
            for (int h = 0; h < N; h++) {
                s[h] = scn.nextInt();
            }

            tornello solver = new tornello();
            int risposta = solver.solve(N, s);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
    }
}