import java.util.*;
import java.io.*;
//import java.lang.*;

public class fiori {
   
    static class Nodo{
        int id;
        Nodo padre;
        int peso = 0;
        boolean visitato = false;
        boolean prescelto = false;
        boolean roccia = true;

        ArrayList<Arco> archi;
        public Nodo( int id ){
            this.id = id;
            archi = new ArrayList<>();
        }
    }
    static class Arco{
        Nodo a;
        public Arco(int da, int a){
            this.a = grafo[a];
            grafo[da].archi.add(this);
        }
    }

    static boolean first = true;
    public static int BFS(Nodo start){
        Queue<Nodo> queue = new LinkedList<>();
        queue.add(start);
       
        while(!queue.isEmpty()){
            Nodo current = queue.poll();       
            for(Arco a: current.archi){
                Nodo neighbor = a.a;
                if(!neighbor.visitato && !neighbor.prescelto){
                    neighbor.visitato = true;
                    if(!first && neighbor.peso <= current.peso){
                        break;
                    }
                    neighbor.peso = current.peso+1;
                    queue.add(neighbor);
                }
            }
        }
        first = false;
        int id = 0;
        for (int i = 0, max = 0, c = 0; i < grafo.length; i++) {
            c = grafo[i].peso;
            if(c > max && !grafo[i].roccia){
                max = c;
                id = i;
            }
        }

        return id;
    }

    public void reset(int W){
        for (Nodo a: grafo) { a.visitato = false; }
    }

    public void dump(int W){
        for (int i = 0; i < grafo.length; i++) {
            grafo[i].visitato = false;
            if(i%W==0){
                System.out.println("");
            }
            //System.out.print(grafo[i].peso+" ");
            System.out.print(((grafo[i].prescelto) ? "O" : (grafo[i].roccia) ? "." : " ")+" ");
        }
        System.out.println("");
        System.out.println("");
    }

    // find the shortest path
    public int minPath(int W, int H, int nFiori){
        int[][] position = new int[nFiori][2];

        for (int i = 0, p = 0, x = 0, y = 0; i < grafo.length; i++) {
            if(grafo[i].prescelto){
                position[p][0] = x;
                position[p++][1] = y;
            }  
            if(++x==W){ x = 0; y++; }
        }

        int min = Integer.MAX_VALUE;
        int dis = 0;
        for (int i = 0; i < position.length; i++) {
            for (int j = i+1; j < position.length-1; j++) {
                dis = Math.abs(position[i][0]-position[j][0])+Math.abs(position[i][1]-position[j][1]);
                if(dis < min){
                    min = dis;
                }  
            }
        }

        return min;
    }

    // RESOLUTION
    public int solve(int W, int H, int nFiori) {
       int max = 0;
       int attuale = 0;
       int idPresceltoAttuale = 0;
        for (int i = 0; i < grafo.length; i++) {
            if(!grafo[i].roccia){
                // clean
                first = true;
                for (Nodo a: grafo) { a.prescelto = false; a.peso = 0; a.visitato = false;}
                idPresceltoAttuale = i;
                grafo[idPresceltoAttuale].peso = 0;
                grafo[idPresceltoAttuale].prescelto = true;
                
                for (int index = 0; index < nFiori-1; index++) {
                    idPresceltoAttuale = BFS(grafo[idPresceltoAttuale]);
                    grafo[idPresceltoAttuale].prescelto = true;
                    grafo[idPresceltoAttuale].peso = 0;
                    reset(W);
                }
                
                attuale = minPath(W, H, nFiori+1);
                if(attuale>max){
                    max = attuale;
                }
            }
        }
        
        return max;
    }


    static Nodo grafo[];
    public static void main(String[] args) throws FileNotFoundException, IOException {
        InputStream fin = new FileInputStream("input.txt");
        OutputStream fout = new FileOutputStream("output.txt");
        Scanner scn = new Scanner(fin);
        PrintStream prnt = new PrintStream(fout);

        int T = scn.nextInt();
        for(int t = 1; t <= T; t++) {
            int W = scn.nextInt();
            int H = scn.nextInt();
            int nFiori = scn.nextInt();
            int nLiberi = scn.nextInt();

            grafo = new Nodo[H*W];
            for(int i=0;i<H*W;i++){
                grafo[i] = new Nodo(i);
                grafo[i].id = i;
            }
            
            for (int i = 0; i < nLiberi; i++) {
                int X = scn.nextInt();
                int Y = scn.nextInt();
                grafo[Y*W+X].roccia = false;
            }

            for (int h = 0, i = 0; h < H; h++) {
                for (int w = 0; w < W; w++, i++) {
                    if(w!=0){   new Arco( i, i-1); } // LEFT
                    if(h!=0){   new Arco( i, i-W); } // TOP
                    if(w!=W-1){ new Arco( i, i+1); } // RIGHT
                    if(h!=H-1){ new Arco( i, i+W); } // BOTTOM
                }
            }

            fiori solver = new fiori();
            int risposta = solver.solve(W, H, nFiori);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
        prnt.close();
        scn.close();
    }
}