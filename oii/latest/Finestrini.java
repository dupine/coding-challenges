import java.util.*;
import java.io.*;
import java.lang.*;

public class Finestrini {
    int max = 0;
    int[] L, R;
    int mem[][][];
    public int solve(int N, int[] L, int[] R) {
        this.max = N;
        this.L = L;
        this.R = R;
        mem = new int[N][2][3];
        for(int i=0; i<N; i++){
            for(int k=0; k<N; k++){
                for(int r=0; r<N; r++){
                    mem[i][k][r] = -1;
                }
            }
        }


        int resR = rec(0, true, 0);
        int resL = rec(0, false, 0);

        return Math.min(resR, resL);
    }

    public int rec(int posto, boolean right, int quantiGiaMessi){
        int costo = right? R[posto] : L[posto];
        quantiGiaMessi++;
    
        if(mem[posto][right ? 0 : 1][quantiGiaMessi] != 0)
            return mem[posto][right ? 0 : 1][quantiGiaMessi];

        if(posto != R.length-1){
            if(quantiGiaMessi==2){
                costo += rec(posto+1, !right, 0);
            } else {
                int costoAltro = rec(posto+1, !right, 0);
                int costoStesso = rec(posto+1, right, quantiGiaMessi);
                costo += Math.min(costoAltro, costoStesso);
            }
        }

        return mem[posto][right ? 0 : 1][quantiGiaMessi] = costo;
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
            int N = scn.nextInt();

            int[] L = new int[N];
            int[] R = new int[N];
            for (int i = 0; i < N; i++) {
                L[i] = scn.nextInt();
                R[i] = scn.nextInt();
            }

            Finestrini solver = new Finestrini();
            int risposta = solver.solve(N, L, R);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
    }
}