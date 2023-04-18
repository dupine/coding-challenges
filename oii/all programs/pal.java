import java.util.*;
import java.io.*;

public class pal {
    static final int INF = 1_000_000_000;

    static class Node {
        int u, v;
        Node(int _u, int _v) {
            u = _u;
            v = _v;
        }
    }

    static class Graph {
        int n;
        List<List<List<Node>>> adj;
        List<List<Integer>> dist;
        Graph(int _n) {
            n = _n;
            adj = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                List<List<Node>> row = new ArrayList<>();
                for (int j = 0; j < n; j++) {
                    row.add(new ArrayList<>());
                }
                adj.add(row);
            }
            dist = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                List<Integer> row = new ArrayList<>();
                for (int j = 0; j < n; j++) {
                    row.add(INF);
                }
                dist.add(row);
            }
        }
        void add_edge(Node x, Node y) {
            adj.get(x.u).get(x.v).add(y);
            adj.get(y.u).get(y.v).add(x);
        }
        List<Node> adj_list(Node x) {
            return adj.get(x.u).get(x.v);
        }
        int get_dist(Node x) {
            return dist.get(x.u).get(x.v);
        }
        void set_dist(Node x, int d) {
            dist.get(x.u).set(x.v, d);
        }
    }

    static void bfs(Node source, Graph graph) {
        Queue<Node> Q = new LinkedList<>();
        graph.set_dist(source, 0);
        Q.add(source);

        while (!Q.isEmpty()) {
            Node x = Q.poll();
            for (Node y : graph.adj_list(x)) {
                if (graph.get_dist(y) != INF) continue;
                graph.set_dist(y, graph.get_dist(x) + 1);
                Q.add(y);
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input.txt"));
        PrintWriter pw = new PrintWriter(new File("output.txt"));
    
        int T = sc.nextInt();
        for (int t = 1; t <= T; t++) {
            int N = sc.nextInt();
            int M = sc.nextInt();
            int X = sc.nextInt();
            int Y = sc.nextInt();
    
            int[] A = new int[M];
            int[] B = new int[M];
            char[] L = new char[M];
            for (int i = 0; i < M; i++) {
                A[i] = sc.nextInt();
                B[i] = sc.nextInt();
                L[i] = sc.next().charAt(0);
            }
    
            Graph graph = new Graph(N);
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < M; j++) {
                    if (L[i] != L[j]) continue;
                    graph.add_edge(new Node(A[i], A[j]), new Node(B[i], B[j]));
                    graph.add_edge(new Node(A[i], B[j]), new Node(B[i], A[j]));
                }
            }
    
            bfs(new Node(X, Y), graph);
    
            int ans = INF;
            for (int i = 0; i < N; i++)
                ans = Math.min(ans, 2 * graph.get_dist(new Node(i, i)));
            for (int i = 0; i < M; i++) {
                ans = Math.min(ans, 2 * graph.get_dist(new Node(A[i], B[i])) + 1);
                ans = Math.min(ans, 2 * graph.get_dist(new Node(B[i], A[i])) + 1);
            }
    
            pw.println("Case #" + t + ": " + (ans == INF ? -1 : ans));
        }
    
        sc.close();
        pw.close();
    }
}