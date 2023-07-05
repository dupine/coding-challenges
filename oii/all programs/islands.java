import java.util.*;
import java.io.*;

class islands {
    
    public static boolean DFS(int r, int c, int[][] copia, boolean bordo){
        copia[r][c] = 0;
        if(!bordo &&  (c==0 || r==0 || c==C-1 || r==R-1)) bordo = true;

        if(c!=C-1 && copia[r][c+1] == 1){
            bordo = DFS(r, c+1, copia, bordo);
        }
        if(c!=0 && copia[r][c-1] == 1){
            bordo = DFS(r, c-1, copia, bordo);
        }
        if(r!=R-1 && copia[r+1][c] == 1){
            bordo = DFS(r+1, c, copia, bordo);
        }
        if(r!=0 && copia[r-1][c] == 1){
            bordo = DFS(r-1, c, copia, bordo);
        }

        return bordo;
    }

    public static int islandsFinder(int y, int x){
        int isole = 0;
        
        R = y;
        C = x;
        int[][] copia = new int[R][C];
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                copia[r][c] = grafo[r][c];
            }
        }

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if(copia[r][c]==1) {
                    if(!DFS(r, c, copia, false)) isole++;
                }
            }
        }

        return isole;
    }

    static int grafo[][];
    static int C = 0, R = 0;
    public static void main(String[] args) throws IOException {
        InputStream fin = System.in;
        OutputStream fout = System.out;
        
        // Per leggere/scrivere da file
        fin = new FileInputStream("input.txt");
        fout = new FileOutputStream("output.txt");
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(fin));
        PrintWriter writer = new PrintWriter(fout);        
        String fl[] = reader.readLine().split(" ");

        int R = Integer.parseInt(fl[0]);
        int C = Integer.parseInt(fl[1]);
        grafo = new int[R][C];

        for(int r=0; r<R; r++){
            fl = reader.readLine().split(" ");
            for(int c=0; c<C; c++){
                grafo[r][c] = Integer.parseInt(fl[c]);
            }
        }
        writer.write(Integer.toString(islandsFinder(R, C)));
        writer.flush();
        reader.close();
        writer.close();
    }
}