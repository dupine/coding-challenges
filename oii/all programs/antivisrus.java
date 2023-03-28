import java.util.*;
import java.io.*;
import java.util.ArrayList;
import java.lang.*;

public class antivisrus {
    public String solve(int N[], int LunghezzaVirus, String F[]) {

        String res[] = new String[4];
        String ress = "";
        for (int i=0; i < N[0]-LunghezzaVirus+1; i++) {
            ress = rec(F[0].substring(i, i+LunghezzaVirus), LunghezzaVirus, N, F, 1, "");
            if(ress.length()>=3){
                res = (i+ress).split(" ");
                break;
            }
            
        }

        return res[0]+" "+res[1]+" "+res[2]+" "+res[3];        
    }

    public String rec(String v, int LV, int N[], String F[], int n, String result){

        if(n>3){
            return result;
        }
        for (int c=0; c < N[n]-LV+1; c++) {
            if(v.equals(F[n].substring(c, c+LV))){
                return rec(v, LV, N, F, n+1, result+" "+c);
            }
        }
        return "";
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
        for (int t = 1; t <= T; t++) {
            int N[] = new int[4];
            N[0] = scn.nextInt();
            N[1] = scn.nextInt();
            N[2] = scn.nextInt();
            N[3] = scn.nextInt();
            int LV = scn.nextInt();
            String f[] = new String[4];
            for (int i = 0; i < 4; i++) {
                f[i] = scn.next();
            }

            antivisrus solver = new antivisrus();
            String risposta = solver.solve(N, LV, f);

            prnt.format("Case #%d: %s\n", t, risposta);
            fout.flush();
        }
    }
}