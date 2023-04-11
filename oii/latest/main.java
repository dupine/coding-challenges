import java.util.*;
import java.io.*;
import java.lang.*;

public class main {
    static int N, B;
    static int[] NC, PC;
    int mem[][];
    static int max;

    public int solve() {
        mem = new int[max+1][N+1];
        for(int i=0; i<max+1; i++){
            for(int j=0; j<N+1; j++){
                mem[i][j] = -1;
            }
        }

        int res = rec(0, 0, max);
        return res;
    }

    public int rec(int posto, int price, int cpu){

        if (cpu == 0) return 0;
        if (posto == N) return 0;

        int prendo = 0;
        int nonPrendo = 0;

        if(mem[cpu][posto] != -1)
            return mem[cpu][posto];
        
        // prendo
        if(price+PC[posto] <= B)
            prendo = NC[posto] + rec(posto+1, price+PC[posto], cpu-NC[posto]);

        // non prendo
        nonPrendo = rec(posto+1, price, cpu);
        
        return mem[cpu][posto] = Math.max(prendo, nonPrendo);
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
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
            N = scn.nextInt();
            B = scn.nextInt();

            NC = new int[N];
            max = 0;
            PC = new int[N];
            for (int i = 0; i < N; i++) {
                NC[i] = scn.nextInt();
                max += NC[i];
                PC[i] = scn.nextInt();
            }

            main solver = new main();
            int risposta = solver.solve();

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
    }
}