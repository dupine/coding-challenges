import java.util.*;
import java.io.*;
import java.lang.*;

public class collezione {
    public int solve(int N, int K, int[] C) {

        Arrays.sort(C);
        differenze[] a = new differenze[N-1];

        for (int i = 0; i < N-1; i++) {
            a[i] = new differenze(C[i+1] - C[i]);
        }

        Arrays.sort(a, (g,c) -> g.dif.compareTo(c.dif));
        for (int i = 0; i < K-1; i++) {
            a[N-2-i].setRemoved(true);
        }
        int risposta = 0;
        for (int i = 0; i < N-1; i++) {
            if(a[i].getRemoved()==false){
                risposta += a[i].getDif();
            }
        }
        return risposta;
    }

    public class differenze{
        Integer dif = 0;
        boolean removed = false;
        differenze(int j){
            dif = j; 
        }
        public int getDif() {
            return dif;
        }
        public boolean getRemoved() {
            return removed;
        }

        public void setDif(int dif) {
            this.dif = dif;
        }
        public void setRemoved(boolean removed) {
            this.removed = removed;
        }
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
            int K = scn.nextInt();

            int[] C = new int[N];
            for (int i = 0; i < N; i++) {
                C[i] = scn.nextInt();
            }

            collezione solver = new collezione();
            int risposta = solver.solve(N, K, C);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
    }
}