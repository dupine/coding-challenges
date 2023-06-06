import java.util.*;
import java.io.*;
import java.lang.*;

public class rettangolo {
    public int[] solve(int x1, int y1, int x2, int y2, int x3, int y3) {

        // aggiungi codice...
        int x4 = 42;
        int y4 = 42;

        if(x1==x2)
            x4 = x3;
        else if(x1!=x3) x4 = x1;
        else x4 = x2;
        
        if(y1==y2)
            y4 = y3;
        else if(y1!=y3) y4 = y1;
        else y4 = y2;

        return new int[] {x4, y4};
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        InputStream fin = System.in;
        OutputStream fout = System.out;

        // se preferisci leggere e scrivere da file
        // ti basta decommentare le seguenti due righe:

        fin = new FileInputStream("input.txt");
        fout = new FileOutputStream("output.txt");

        Scanner scn = new Scanner(fin);
        PrintStream prnt = new PrintStream(fout);

        int T = scn.nextInt();
        for(int t = 1; t <= T; t++) {
            int x1 = scn.nextInt();
            int y1 = scn.nextInt();
            int x2 = scn.nextInt();
            int y2 = scn.nextInt();
            int x3 = scn.nextInt();
            int y3 = scn.nextInt();

            rettangolo solver = new rettangolo();
            int[] risposta = solver.solve(x1, y1, x2, y2, x3, y3);

            prnt.format("Case #%d: %d %d\n", t, risposta[0], risposta[1]);
            fout.flush();
        }

        scn.close();
        prnt.close();
    }
}
