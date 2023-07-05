import java.util.*; 
import java.io.*; 

public class catalogo {
    static Hashtable<String, Integer> hashtable = new Hashtable<>();

    private static void aggiungi(long id) {
        if(hashtable.get(""+id)!=null) hashtable.put(""+id, hashtable.get(""+id)+1);
        else hashtable.put(""+id, 1);
    }

    private static void togli(long id) {
        hashtable.put(""+id, hashtable.get(""+id)-1);        
    }

    private static int conta(long id) {
        return hashtable.get(""+id)==null ? 0 : hashtable.get(""+id);
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
        Character method;
        long id;

        for (int i=0; i<T; i++) {
            method = scn.next().charAt(0);
            id = scn.nextLong();

            if(method=='a') aggiungi(id);
            if(method=='t') togli(id);
            if(method=='c') prnt.print(conta(id)+ "\n");
        }
        prnt.println();
        prnt.close();
    }
}
