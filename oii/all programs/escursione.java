import java.util.*;
import java.io.*;

class escursione {
    // ------------------ INIZIO implementazione dell'oggetto Grafo ---------
    static class Nodo implements Comparable<Nodo>{
        int id;
        int costoPercorso;
        Nodo padre = null;
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
        int peso;
        public Arco(int da, int a, int peso){
            this.a = grafo[a];
            this.peso = peso;
            grafo[da].archi.add(this);
        }
        public String toString(){
            return "[-> "+a.id+" $"+peso+"]";
        }
    }
    static Nodo grafo[];

    // ---------- Dijkstra ----------
    static int tuttoCammino(Nodo x, int mS){
        if(x.padre == null){
            return mS;
        }
        return tuttoCammino(x.padre, (x.costoPercorso > mS) ? x.costoPercorso : mS);
    }

    static void camminiMinimi(Nodo x){
        for(Nodo n: grafo){
            n.costoPercorso = Integer.MAX_VALUE;
            n.padre = null;
        }

        // costruzione partenza
        x.costoPercorso = 0;
        PriorityQueue<Nodo> s = new PriorityQueue<>();
        s.add(x);
        
        // costruzione della copertura
        while( !s.isEmpty() ){
            Nodo attuale = s.poll();
            for(Arco a: attuale.archi){
                
                Nodo vicino = a.a;
                
                int nDist = (attuale.costoPercorso < a.peso) ? a.peso : attuale.costoPercorso;
                if( nDist < vicino.costoPercorso){
                    vicino.costoPercorso = nDist;
                    vicino.padre = attuale;
                    s.add(vicino);
                }
            }
        }
    }

    public static void dump(int W){
        for (int i = 0; i < grafo.length; i++) {
            if(i%W==0){
                System.out.println("");
            }
            System.out.print(grafo[i].costoPercorso+" ");
        }
        System.out.println("");
        System.out.println("");
    }


    public static void main(String[] args) throws Exception{
        InputStream fin = new FileInputStream("input.txt");
        OutputStream fout = new FileOutputStream("output.txt");
        Scanner scn = new Scanner(fin);
        PrintStream prnt = new PrintStream(fout);
        int T = scn.nextInt();

        for (int t = 1; t <= T; t++) {
            
            int R = scn.nextInt();
            int C = scn.nextInt();
            int numeroNodi = R*C;
            grafo = new Nodo[numeroNodi];
            int altitudini[] = new int[numeroNodi];
            for(int i=0; i<numeroNodi; i++){
                grafo[i] = new Nodo(i);
                altitudini[i] = scn.nextInt();
            }

            for (int h = 0, i = 0; h < R; h++) {
                for (int w = 0; w < C; w++, i++) {
                    if(w!=0)  { new Arco( i, i-1, Math.abs( altitudini[i]-altitudini[i-1] ));} // LEFT
                    if(h!=0)  { new Arco( i, i-C, Math.abs( altitudini[i]-altitudini[i-C] ));} // TOP
                    if(w!=C-1){ new Arco( i, i+1, Math.abs( altitudini[i]-altitudini[i+1] ));} // RIGHT
                    if(h!=R-1){ new Arco( i, i+C, Math.abs( altitudini[i]-altitudini[i+C] ));} // BOTTOM
                }
            }
            camminiMinimi(grafo[0]);
            //dump(C);
            int rs = tuttoCammino(grafo[grafo.length-1], 0);
            prnt.format("Case #%d: %s\n", t, rs);
            fout.flush();
        }
        scn.close();
        fin.close();
        prnt.close();
    }
}