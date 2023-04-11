import java.util.*;
import java.io.*;
import java.lang.*;

public class vacanze {

        // ------------------ INIZIO implementazione dell'oggetto Grafo ---------
        static class Nodo{
            int id;
            int costoPercorso;
            boolean visitato = false;
            Nodo padre;
            ArrayList<Arco> archi;
            public Nodo( int id ){
                this.id = id;
                archi = new ArrayList<>();
            }
            public String toString(){
                String vicini = "";
                for(Arco a: archi){
                    vicini+=a;
                }
                return "{"+id+" $:"+costoPercorso+" ^"+(padre==null?"-":padre.id)+"} "+vicini;
            }
        }

        static class Arco{
            Nodo a;
            public Arco(int da, int a){
                this.a = grafo[a];
                grafo[da].archi.add(this);
            }
        }

    static Nodo grafo[];
    static ArrayList<Integer> comb;

    public int solve(int N, int M) {

        comb = new ArrayList<>();

        int x = 0;
        for (int i = 0; i < grafo.length; i++) {
            x = rec(grafo[i], grafo[i], 0, 0, null, null);
            if(x!=0){ comb.add(x);}
        }

        return comb.size();
    }

    public int rec(Nodo princ, Nodo act, int checked, int combos, Nodo b, Nodo c){
        if(checked==4 && act!=princ) return 0;

        for(Arco a: act.archi)
            if(a.a!=b && a.a!=c && a.a!=act)
                combos += a.a.id + rec(princ, a.a, checked+1, combos+a.a.id, ( b==null ? act : b), (c==null ? act : (b==null ? null : act) ) );
            else
                return 0;

        return exists(combos) ? 0 : combos;
    }

    public boolean exists(int x){
        for (int i = 0; i < comb.size(); i++)
            if(comb.get(i)==x)
                return true;
        return false;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {

        InputStream fin;
        OutputStream fout;
        fin = new FileInputStream("input.txt");
        fout = new FileOutputStream("output.txt");

        Scanner scn = new Scanner(fin);
        PrintStream prnt = new PrintStream(fout);

        int T = scn.nextInt();
        for(int t = 1; t <= T; t++) {
            int N = scn.nextInt();
            int M = scn.nextInt();
            grafo = new Nodo[N];

            for (int i = 0; i < N; i++) 
                grafo[i] = new Nodo(i);

            for (int i = 0; i < M; i++)
                new Arco(scn.nextInt(), scn.nextInt());

            /* 
            int[] A = new int[M];
            int[] B = new int[M];
            for (int i = 0; i < M; i++) {
                A[i] = scn.nextInt();
                B[i] = scn.nextInt();
            }
            */
            
            vacanze solver = new vacanze();
            int risposta = solver.solve(N, M);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
    }
}