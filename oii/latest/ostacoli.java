import java.util.*;
import java.io.*;
import java.lang.*;

public class ostacoli {

    long memo[][];
    int tMax = 0;
    int dMax = 0;
    ArrayList<ostacolo> ostacoli;
    public class ostacolo {
        int id = 0;
        int pos = 0;
        int punti = 0;
        int tempo = 0;

        ostacolo(int id, int pos, int punti, int tempo) {
            this.id = id;
            this.pos = pos;
            this.punti = punti;
            this.tempo = tempo;
        }
    }

    public long solve(int N, int L, int D, int[] X, int[] P, int[] S) {
        ostacoli = new ArrayList<>();
        this.tMax = D;
        this.dMax = L;
        int i = 0;
        for (int index = 0; index < N; index++) {
            if (X[index] <= S[index]) {
                ostacoli.add(new ostacolo(i++, X[index], P[index], S[index]));
            }
        }

        memo = new long[ostacoli.size()][ostacoli.size()];
        long risposta = rec(null, ostacoli.get(0));

        return risposta;
    }

    private static long max3(long i1, long i2, long i3){
        if(i1>i2){
            return i1>i3 ? i1 : i3;
        } else {
            return i2>i3 ? i2 : i3;
        }
    }

    public long rec(ostacolo prec, ostacolo act) {

        if(prec!=null && Math.abs(prec.tempo-act.tempo) < Math.abs(prec.pos-act.pos) ){
            return 0;
        }
 
        if(act.id == ostacoli.size()-1) return act.punti;

        long prendo = 0;
        long nonPrendo = 0;
        long salto = 0;

        if(memo[prec==null ? 0 : prec.id][act.id] !=0 )
            return memo[prec==null ? 0 : prec.id][act.id];

        // prendo
        prendo = act.punti + rec(act, ostacoli.get(act.id+1));

        // non prendo
        nonPrendo = rec(prec, ostacoli.get(act.id+1));

        // salto
        //salto = rec(prec, ostacoli.get(act.id+1));
        
        memo[prec==null ? 0 : prec.id][act.id] = max3(prendo, nonPrendo, salto);
        return memo[prec==null ? 0 : prec.id][act.id];
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

            ostacoli solver = new ostacoli();
            long risposta = solver.solve(N, L, D, X, P, S);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
    }
}
