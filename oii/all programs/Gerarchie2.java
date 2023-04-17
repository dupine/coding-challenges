
import java.util.*;
import java.io.*;
import java.lang.*;

public class Gerarchie2 {

    // ------------------ INIZIO implementazione dell'oggetto Grafo ---------
    static class Nodo{
        int id;
        int layer = -1;
        int peso = 0;
        Nodo padre = null;
        boolean retrocedibile = true;
        ArrayList<Arco> archi;

        public Nodo(int id) {
            this.id = id;
            archi = new ArrayList<>();
        }

        public String toString() {
            String vicini = "";
            for (Arco a : archi) {
                vicini += a;
            }
            return "{" + id + "r: " + retrocedibile + " ^" + (padre == null ? "-" : padre.id) + "} " + vicini;
        }
    }

    static class Arco {
        Nodo a;
        public Arco(int da, int a) {
            this.a = grafo[a];
            grafo[da].archi.add(this);
        }

        public String toString() {
            return "[→" + a.id + "]";
        }
    }

    static void dump() {
        for (Nodo n : grafo) {
            System.out.println(n);
        }
        System.out.println("----------------------");
    }

    static Nodo grafo[];
    static int aLayer = 0;
    public int solve(int N, int first) {
        grafo[first].layer = 0;
        toOrder(null, grafo[first], 0);

        int swaps = 0;
        aLayer = 0;
        int r = greater();
        //if(grafo[r].padre==null) aLayer++;

        while(r!=-1){
            //System.out.println(r);
            swaps += swap2(r);
            r = greater();
        }

        return swaps;
    }

    public int swap2(int idNodo){
        int fatherPeso, swaps = 0;
        boolean fatherRetrocedibile;

        if(grafo[idNodo].padre!=null && grafo[idNodo].padre.retrocedibile){
            grafo[idNodo].retrocedibile = false;

            while(grafo[idNodo].padre!=null && grafo[idNodo].padre.retrocedibile){
                fatherPeso = grafo[idNodo].padre.peso;
                fatherRetrocedibile = grafo[idNodo].padre.retrocedibile;
                
                //System.out.println("cambio: "+grafo[idNodo].id+", con: "+grafo[idNodo].padre.id);


                grafo[idNodo].padre.retrocedibile = grafo[idNodo].retrocedibile;
                grafo[idNodo].padre.peso = grafo[idNodo].peso;

                grafo[idNodo].retrocedibile = fatherRetrocedibile;
                grafo[idNodo].peso = fatherPeso;

                //if(!grafo[idNodo].padre.padre.retrocedibile) grafo[idNodo].padre.retrocedibile = false;                
                idNodo = grafo[idNodo].padre.id;
                swaps++;
            }
        } else grafo[idNodo].retrocedibile = false;
        return swaps;
    }

    public int greater(){
        int bigger = -1, id = -1;
        for (Nodo nodo : grafo) {
            if(nodo.retrocedibile && nodo.layer>=aLayer){
                if(nodo.peso>bigger){
                    bigger = nodo.peso;
                    id = nodo.id;
                }
            }
        }
        return id;
        
    }

    public void toOrder(Nodo prec, Nodo n, int layer){
        layer+=1;
        for (Arco a : n.archi) {
            if(prec!=null ? !(a.a==prec) : true){
                a.a.layer = layer;
                toOrder(n, a.a, layer);
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {

        InputStream fin;
        OutputStream fout;
        fin = new FileInputStream("input.txt");
        fout = new FileOutputStream("output.txt");

        Scanner scn = new Scanner(fin);
        PrintStream prnt = new PrintStream(fout);

        int T = scn.nextInt();
        for (int t = 1; t <= T; t++) {
            int N = scn.nextInt();
            grafo = new Nodo[N];

            for (int i = 0; i < N; i++)
                grafo[i] = new Nodo(i);

            int a, peso, first=0;
            for (int i = 0; i < N; i++) {
                a = scn.nextInt();
                peso = scn.nextInt();
                grafo[i].peso = peso;
                grafo[i].padre = a!=-1 ? grafo[a] : null;
                if(a!=-1){
                    new Arco(i, a);
                    new Arco(a, i);
                } else first=i;
            }

            //dump();

            Gerarchie2 solver = new Gerarchie2();
            int risposta = solver.solve(N, first);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
            System.out.println("==================");
        }
    }
}