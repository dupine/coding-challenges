import java.util.*; 
import java.io.*; 

public class cabala {

    private static long occulta(int N, int M) {
        
        return 42;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        InputStream fin = System.in;
        OutputStream fout = System.out;
        // per leggere/scrivere da file 
        fin = new FileInputStream("input.txt");
        fout = new FileOutputStream("output.txt");
        Scanner scn = new Scanner(fin); 
        PrintStream prnt = new PrintStream(fout);
        
        int T = scn.nextInt(); 
        for (int i=0; i<T; i++) {
            int N = scn.nextInt();
            int M = scn.nextInt();
            prnt.print(occulta(N, M)+ " ");
        }
        prnt.println();
        prnt.close();
    }
}
