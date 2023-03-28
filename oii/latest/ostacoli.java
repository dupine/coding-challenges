import java.util.*;
import java.io.*;
import java.lang.*;

public class ostacoli {

    int memo[][];
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

    public int solve(int N, int L, int D, int[] X, int[] P, int[] S) {
        ostacoli = new ArrayList<>();
        int i = 0;
        for (int index = 0; index < N; index++) {
            if (X[index] <= S[index]) {
                ostacoli.add(new ostacolo(i++, X[index], P[index], S[index]));
            }
        }

        memo = new int[ostacoli.size()][ostacoli.size()];
        int risposta = rec(null, ostacoli.get(0));

        return risposta;
    }

    private static int max3(int i1, int i2, int i3){
        if(i1>i2){
            return i1>i3 ? i1 : i3;
        } else {
            return i2>i3 ? i2 : i3;
        }
    }

    public int rec(ostacolo prec, ostacolo act) {


        if(prec!=null && Math.abs(prec.tempo-act.tempo) < Math.abs(prec.pos-act.pos) ){
            return 0;
        }
 
        if(act.id == ostacoli.size()-1){
            return act.punti;
        }

        int prendo = 0;
        int nonPrendo = 0;
        int salto = 0;

        if(memo[prec==null ? 0 : prec.id][act.id] !=0 )
            return memo[prec==null ? 0 : prec.id][act.id];

        // prendo
        prendo = act.punti + rec(act, ostacoli.get(act.id+1));

        // non prendo
        nonPrendo = rec(act, ostacoli.get(act.id+1));

        // salto
        //salto = act.punti + rec(prec, ostacoli.get(act.id+1));
        
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
            int risposta = solver.solve(N, L, D, X, P, S);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
    }
}
