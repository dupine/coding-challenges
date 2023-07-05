    import java.util.*;
    import java.io.*;
    import java.lang.*;

    public class nonna2 {

        int mem[];
        public int solve(int N, int grammi, int piatti[]) {
            mem = new int[5000];



            int x = rec(0, 0, piatti, grammi, N);
            return x;
        }

        public int rec(int i, int g, int[] piatti, int grammi, int N){
            if (g >= grammi) return g;
            if (i==N) return Integer.MAX_VALUE;
            if(mem[g]!=0)
                return mem[g];

            return mem[g] = Math.min(rec(i+1, g+piatti[i], piatti, grammi, N), rec(i+1, g, piatti, grammi, N));
        }

        
    public static void main(String[] args) throws FileNotFoundException, IOException {
            BufferedReader br = new BufferedReader(new FileReader("input.txt"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
            String[] input = br.readLine().split(" ");

            int N = Integer.parseInt(input[0]);
            int g = Integer.parseInt(input[1]);

            int[] C = new int[N];
            input = br.readLine().split(" ");
            for (int i = 0; i < N; i++) {
                C[i] = Integer.parseInt(input[i]);
            }
        
            nonna2 solver = new nonna2();
            int risposta = solver.solve(N, g, C);
        
            bw.write("" + risposta);
            bw.newLine();
            bw.flush();
            bw.close();
        }

    }