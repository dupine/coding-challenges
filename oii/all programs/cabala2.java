import java.util.*; 
import java.io.*; 

public class cabala2 {

    public static long solve(int N, int M) {
        long x = 0;
        long max = 0;
        for (int i = N; i >= 1; i--) {
            long n3 = combina(M, i, 1, 3) % M; 
            long n6 = combina(M, i, 1, 6) % M;
            long n9 = combina(M, i, 1, 9) % M;

            x = n3 > n6 ? n3 > n9 ? n3 :n9 : n6 > n9 ? n6 :n9;

            if(x>max) {
                max = x;
            }
        }
        return max;
    }

    private static long combina(int M, int N, int step, long n){
        if(step==N){
            return n; 
        } else {
            long ultima = n%10;
            long n1=0, n2=0;
            if(ultima==3){
                n1 = combina(M, N, step+1, n*10+6);
                n2 = combina(M, N, step+1, n*10+9);
            }
            if(ultima==6){
                n1 = combina(M, N, step+1, n*10+3);
                n2 = combina(M, N, step+1, n*10+9);
            }
            if(ultima==9){
                n1 = combina(M, N, step+1, n*10+3);
                n2 = combina(M, N, step+1, n*10+6);
            }
            return n1%M > n2%M ? n1 : n2;
        }
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
