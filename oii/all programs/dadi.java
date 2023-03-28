import java.util.*;
import java.io.*;
import java.lang.*;

public class dadi {
    public int solve(int K, int A, int B, int C, int D) {
        int somma = 0;
        while(K>0){
            for (int i = 0; i < 4; i++) {
                K--;
                somma+=4;
                if(A!=0){
                    A--;
                    break;
                }
                if(B!=0){
                    B--;
                    break;
                }
                if(C!=0){
                    C--;
                    break;
                }
                if(D!=0){
                    D--;
                    break;
                }
            }
        }

        return somma+A+(B*2)+(C*3)+(D*4);
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // se preferisci leggere e scrivere da file
        // ti basta modificare la seguente variabile
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
            int K = scn.nextInt();
            int A = scn.nextInt();
            int B = scn.nextInt();
            int C = scn.nextInt();
            int D = scn.nextInt();
            
            dadi solver = new dadi();
            int risposta = solver.solve(K, A, B, C, D);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
    }
}