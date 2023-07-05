import java.util.*; 
import java.io.*; 

public class buffered {

    private static long occulta(int N, int M) {
        
        return 42;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        InputStream fin = System.in;
        OutputStream fout = System.out;
        
        // Per leggere/scrivere da file
        fin = new FileInputStream("input.txt");
        fout = new FileOutputStream("output.txt");
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(fin));
        PrintWriter writer = new PrintWriter(fout);
        
        int T = Integer.parseInt(reader.readLine());
        
        for (int i = 0; i < T; i++) {
            String[] values = reader.readLine().split(" ");
            int N = Integer.parseInt(values[0]);
            int M = Integer.parseInt(values[1]);
            
            writer.print(solve(N, M) + " ");
        }
        
        writer.println();
        writer.close();
    }
}
