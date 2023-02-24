import java.util.*;
import java.io.*;


/*TODO:

rifai tutta la parte bassa ma senza usare un array per gli archi, ma invece usa l'oggetto stesso

*/



public class main {

    // uso un oggeto dato che il grafico è semplice(un nomo massimo ha 4 archi)
    // rispetto ad una matrice che sì fa risparmiare tempo ma è non avendo nodi con moltissimi archi
    // mi bastano gli oggetti che sono più intuitivi e semplici da gestire
    // ============== classi nodo e arco ==============
    static class Node{
        int id;
        ArrayList<Edge> edges = new ArrayList<>();

        public Node(int i){
            id = i;
        }
    }

    static class Edge{
        Node a, b;
        int weight = 0;
        public Edge(Node a, Node b, int weight){
            this.a = a;
            this.b = b;
            this.weight = weight;
        }
    }

    // ============== metodo risoluzione ==============
    public int solve(int H, int W, Node[] nodes, Edge[] edges) {

        graph(H, W, nodes);
        return 0;
    }
    
    public void graph(int H, int W, Node[] nodes){
        for (int i = 0; i < H*W; i++) {
            System.out.printf(" %d    %d    ", i, nodes[i].ed weight);
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
            int H = scn.nextInt();
            int W = scn.nextInt();

            Node nodes[] = new Node[H*W];
            Edge edges[] = new Edge[(H-1)*W+(W-1)*H];
            int id = 0;

            int[][] S = new int[H][W];
            for (int h = 0; h < H; h++) {
                for (int w = 0; w < W; w++) {
                    S[h][w] = scn.nextInt();
                    nodes[id] = new Node(id++);
                }
            }

            id = 0;
            int idNodo = 0;
            int jump = 0;
            for (int h = 0; h < H; h++) {
                for (int w = 0; w < W; w++) {
                    if(w!=W-1){
                        edges[id++] = (new Edge(nodes[idNodo], 
                        nodes[idNodo+1],
                        Math.abs(S[h][w]-S[h][w+1]))); 
                    } // RIGHT
                    if(h!=H-1){ 
                        edges[id++] = (new Edge(nodes[idNodo],
                        nodes[idNodo+jump], 
                        Math.abs(S[h][w]-S[h+1][w])));
                    } // BOTTOM
                }
                jump+=W;
            }
            main solver = new main();
            int risposta = solver.solve(H, W, nodes, edges);


            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();

            System.out.println("======================");
        }

        prnt.close();
        scn.close();
    }
}


/*

The choice of representation for a graph depends on the problem at hand and the operations that need to be performed on the graph.

Using an object-oriented representation, as I did in the first request, can be more flexible and easier to work with when dealing with complex graphs with variable node degrees and edge weights. This approach can also be more intuitive, as each node and edge is represented as an object with its own properties and methods.

On the other hand, using a matrix representation, as in the second request, can be more efficient for certain types of algorithms that require fast lookup of edge weights. It can also be more space-efficient for dense graphs, where most nodes are connected to each other.

In general, if the graph is small and dense, a matrix representation may be more appropriate. If the graph is large and sparse, an object-oriented representation may be more suitable. However, it is always a good idea to consider the specifics of the problem at hand and choose the representation that best fits the requirements of the algorithm or application.

*/