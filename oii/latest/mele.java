import java.util.*;
import java.io.*;
import java.lang.*;

public class mele {

        // ------------------ INIZIO implementazione dell'oggetto Grafo ---------
        static class Nodo implements Comparable<Nodo>{
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
            @Override
            public int compareTo(Nodo o) {
                return this.costoPercorso - o.costoPercorso;
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

    public int solve(int N, int M) {

        int max = 0;
        grafo[0].costoPercorso = 0;
        PriorityQueue<Nodo> s = new PriorityQueue<>();
        s.add(grafo[0]);

        while( !s.isEmpty() ){
            Nodo attuale = s.poll();
            for(Arco a: attuale.archi){
                Nodo vicino = a.a;
                for(Arco aa: vicino.archi){
                    Nodo vicinoDelVicino = aa.a;
                    for(Arco aaa: vicino.archi){
                        Nodo vicinoDelVicinoDelVicino = aaa.a;
                        if(){

                        }
                        
                        
                    }
                }
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
            int M = scn.nextInt();

            for (int i = 0; i < N; i++) {
                grafo[i] = new Nodo(i);
            }
            for (int i = 0; i < 6; i++) {
                new Arco(scn.nextInt(), scn.nextInt());
            }

            /*
            int[] A = new int[M];
            int[] B = new int[M];
            for (int i = 0; i < M; i++) {
                A[i] = scn.nextInt();
                B[i] = scn.nextInt();
            }*/

            mele solver = new mele();
            int risposta = solver.solve(N, M);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
    }
}

/*

        int max = 0;
        for (int i = 0; i < grafo.length; i++) {
            if(grafo[i].archi.size()==2){
                for (Arco a : grafo[i].archi) {
                    if (a.a.id != i) {
                    }
                }
                    max+=1;
            }
        }

 */