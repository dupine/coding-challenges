import java.util.*;
import java.io.*;
import java.lang.*;

public class Gerarchie {

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
            return "{" + id + " ^" + (padre == null ? "-" : padre.id) + "} " + vicini;
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
    int maxLayer = 0;
    public int solve(int N, int first) {
        
        grafo[first].layer = 0;
        int maxLayer = 0;

        toOrder(null, grafo[first], 0);
        //Arrays.sort(grafoPesi, (Nodo a, Nodo b)-> b.peso-a.peso );
        Arrays.sort(grafo, (Nodo a, Nodo b)-> a.id-b.id );
        for (Nodo n : grafo) if(n.layer>maxLayer) maxLayer = n.layer;

        int swaps = 0;
        int r = 0;
        for (int i = 0; i < maxLayer; i++) {
            r = greater();
            grafo[r].retrocedibile=false;
            System.out.println(r);
            swaps += swap2(r, i);
            Arrays.sort(grafo, (Nodo a, Nodo b)-> a.id-b.id );
            //dump();
        }

        return swaps;
    }

    public int swap2(int idNodo, int layerMax){
        Nodo father;
        int swaps = 0;
        int x = 0;
        while(grafo[idNodo].layer>layerMax){

            father = grafo[idNodo].padre;
            x = father.id;
            //System.out.println("id: "+x+", cambio: "+grafo[idNodo].id+", padre: "+grafo[idNodo].padre.id);
            
            // cambio il padre
            grafo[idNodo].padre.peso = grafo[idNodo].peso;
            grafo[idNodo].padre.id = grafo[idNodo].id;

            // cambio quello attuale
            grafo[idNodo].id = father.id;
            grafo[idNodo].peso = father.peso;

            // cambio archi
            archChanger(grafo[idNodo], x);

            idNodo = x;

            swaps++;
        }

        return swaps;
    }

    public int greater(){
        int bigger = -1;
        int id = -1;
        for (Nodo nodo : grafo) {
            if(nodo.retrocedibile){
                System.out.println(nodo.id);
                if(nodo.peso>bigger){
                    bigger = nodo.peso;
                    id = nodo.id;
                }
            }
        }
        //System.out.println(id);
        return id;
        
    }

    public void archChanger(Nodo act, int idPadre){
        for (Arco a : act.archi) {
            if(a.a.id==idPadre) a.a = grafo[idPadre];
        }
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

            Gerarchie solver = new Gerarchie();
            int risposta = solver.solve(N, first);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
            System.out.println("==================0");
        }
    }
}