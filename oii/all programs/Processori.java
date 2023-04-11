import java.util.*;
import java.io.*;
import java.lang.*;

public class Processori {
    static int N, B;
    static int[] NC, PC;
    int mem[][];
    static int max;

    public int solve() {
        mem = new int[N][max+1];
        for(int i=0; i<N; i++){
            for(int j=0; j<max+1; j++){
                mem[i][j] = -1;
            }
        }

        int res = rec(0, 0, 0);
        return res;
    }

    public int rec(int posto, int price, int cpu){

        if(posto>=N || price>=B){
            return 0;
        }

        int prendo = 0;
        int nonPrendo = 0;

        if(mem[posto][cpu] != -1)
            return mem[posto][cpu];
        
        // prendo
        if(price+PC[posto] <= B)
            prendo = NC[posto] + rec(posto+1, price+PC[posto], NC[posto]+cpu);

        // non prendo
        nonPrendo = rec(posto+1, price, cpu);
        
        return mem[posto][cpu] = Math.max(prendo, nonPrendo);
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

            Processori solver = new Processori();
            int risposta = solver.solve();

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
    }
}