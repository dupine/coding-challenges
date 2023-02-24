import java.util.*;
import java.io.*;

public class lampadine {
    class Lampadina{
        int id;
        boolean singolo=false;
        int intr = 0;
        ArrayList<Lampadina> adiacenti = new ArrayList<>();
        public Lampadina(int i){
            id = i;
        }
        @Override
        public String toString() {
            return id+" "+intr;
        }
    }

    public String solve(int N, int A, int B, int[] S, int[] X, int[] Y) {

        Lampadina grafo[] = new Lampadina[N];
        for(int i=0; i<N ; i++){
            grafo[i] = new Lampadina(i);
        }
        for(int i=0; i<A ; i++){
            grafo[ S[i] ].singolo = true;
            grafo[ S[i] ].intr = 1;
        }
        for(int i=0; i<B ; i++){
            grafo[ X[i] ].adiacenti.add( grafo[ Y[i] ] );
            grafo[ Y[i] ].adiacenti.add( grafo[ X[i] ] );
        }

        int intrBase = 1;
        ArrayList<Lampadina> l = new ArrayList<>(Arrays.asList(grafo));
        while(l.size()>1){
          for (int i = 0; i < l.size() && l.size()>1; i++) {
            if(l.get(i).singolo==true && l.get(i).intr==intrBase){
              for (Lampadina lamp : l.get(i).adiacenti) { if(lamp.singolo!=true){lamp.singolo=true; lamp.intr = l.get(i).intr+1;}}
              l.remove(i--);
            }
          }
          intrBase++;
        }
        return l.get(0).toString();
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // se preferisci leggere e scrivere da file
        // ti basta modificare la seguente variabile
        boolean input_from_file = true;

        InputStream fin;
        OutputStream fout;
        if(input_from_file) {
            fin = new FileInputStream("input.txt");
            // fin = new FileInputStream("abc_quadri.input33.txt");
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
            int A = scn.nextInt();
            int B = scn.nextInt();

            int[] S = new int[A];
            int[] X = new int[B];
            int[] Y = new int[B];
            for (int i = 0; i < A; i++) {
                S[i] = scn.nextInt();
            }
            for (int i = 0; i < B; i++) {
                X[i] = scn.nextInt();
                Y[i] = scn.nextInt();
            }

            lampadine solver = new lampadine();
            String risposta = solver.solve(N, A, B, S, X, Y);


            prnt.format("Case #%d: %s\n", t, risposta);
            fout.flush();
        }

        prnt.close();
        scn.close();
    }
}