import java.util.*;
import java.io.*;
import java.lang.*;

public class soldatini {
    public int solve(int N, int[] S) {

        int max = 0;
        boolean zero = false;
        int left = 0;
        int right = 0;

        for (int i = 0; i < N; i++) {

            if(S[i]==0){
                zero = true;
                left = right;
                right = 0;
            } else {
                right++;
            }

            if(left+right+((zero) ? 1 : 0)>max){
                max = left+right+((zero) ? 1 : 0);
            }
        }

        return max;
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
            int[] S = new int[N];
            
            for (int i = 0; i < N; i++) {
                S[i] = scn.nextInt();
            }

            soldatini solver = new soldatini();
            int risposta = solver.solve(N, S);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
    }
}

/*

 Case #9: 67
Case #10: 48

 */