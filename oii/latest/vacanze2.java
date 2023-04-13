import java.util.*;
import java.io.*;
import java.lang.*;

public class vacanze2 {

        // ------------------ INIZIO implementazione dell'oggetto Grafo ---------
        static class Nodo{
            int id;
            Nodo padre = null;
            ArrayList<Arco> archi;
            public Nodo( int id ){
                this.id = id;
                archi = new ArrayList<>();
            }
            public String toString(){
                String vicini = "";
                for(Arco a: archi){
                    vicini+=a;
                }
                return "{"+id+" ^"+(padre==null?"-":padre.id)+"} "+vicini;
            }
        }

        static class Arco{
            Nodo a;
            public Arco(int da, int a){
                this.a = grafo[a];
                grafo[da].archi.add(this);
            }
            public String toString(){
                return "[→"+a.id+"]";
            }
        }

    static Nodo grafo[];
    static ArrayList<int[]> comb;

    public int solve(int N, int M) {

        comb = new ArrayList<>();

        for (int i = 0; i < grafo.length; i++) {
            int[] vec = {grafo[i].id, 0, 0, 0};
            rec(grafo[i], grafo[i], null,  0, null, vec);
        }
        return counter();
    }

    public int rec(Nodo princ, Nodo act, Nodo prec, int checked, Nodo b, int[] vec){
        //System.out.println(s+princ.id+", "+( b!=null ? b.id : "b")+", "+( c!=null ? c.id : "c"));
        //System.out.println(s+""+princ.id+" - " + act.id + " :"+checked);
        int combos = 0;
        if(checked==2) if(search(act, princ)) return 0;
        if(checked==3) if(checked!=0) if(searchFinal(act, b, princ)){/*for (int x : vec) System.out.print(x); System.out.println();*/  comb.add(vec); return 1;} else return 0;
        for(Arco a: act.archi)
            if(prec!=null ? a.a!=prec : true){  
                vec[checked+1]=a.a.id;
                combos = rec(princ, a.a, act, checked+1, (checked==1 ? act : b), vec);  
            }
        return combos;
    }

    public boolean search(Nodo x, Nodo y){
        for(Arco a: x.archi)
            if(a.a==y) return true;
        return false;
    }

    public boolean searchFinal(Nodo x, Nodo y, Nodo z){
        boolean fine = false;

        for(Arco a: x.archi)
            if(a.a==y) return false;
            else if(a.a==z) fine = true;
        
        if(fine) return true;
        else return false;
    }

    public int counter(){
        Set<String> uniqueArrays = new HashSet<>();
        for (int[] array : comb) {
            Arrays.sort(array);
            if(array[0]!=array[1] && array[1]!=array[2] && array[2]!=array[3]) 
                uniqueArrays.add(Arrays.toString(array));
        }

        for (String string : uniqueArrays) {
            System.out.println(string);
        }System.out.println("_______________");
        
        return uniqueArrays.size();
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {

        InputStream fin;
        OutputStream fout;
        fin = new FileInputStream("input.txt");
        fout = new FileOutputStream("output.txt");

        Scanner scn = new Scanner(fin);
        PrintStream prnt = new PrintStream(fout);

        int T = scn.nextInt();
        for(int t = 1; t <= T; t++) {
            int N = scn.nextInt();
            int M = scn.nextInt();
            grafo = new Nodo[N];

            for (int i = 0; i < N; i++) 
                grafo[i] = new Nodo(i);

            int a, b;
            for (int i = 0; i < M; i++){
                a = scn.nextInt();
                b = scn.nextInt();
                new Arco(a, b);
                new Arco(b, a);
            }

            //dump();

            vacanze2 solver = new vacanze2();
            int risposta = solver.solve(N, M);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
    }
}