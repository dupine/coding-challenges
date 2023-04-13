import java.util.*;
import java.io.*;
import java.lang.*;

public class vacanze2 {

        // ------------------ INIZIO implementazione dell'oggetto Grafo ---------
        static class Nodo{
            int id;
            int costoPercorso;
            boolean visitato = false;
            Nodo padre;
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
                return "{"+id+" $:"+costoPercorso+" ^"+(padre==null?"-":padre.id)+"} "+vicini;
            }
        }

        static class Arco{
            Nodo a;
            public Arco(int da, int a){
                this.a = grafo[a];
                grafo[da].archi.add(this);
            }
        }

    static Nodo grafo[];
    static ArrayList<Integer> comb;

    public int solve(int N, int M) {

        comb = new ArrayList<>();

        int x = 0;
        for (int i = 0; i < grafo.length; i++) {
            x += rec(grafo[i], grafo[i], null,  0, null, null, "", grafo[i].id);
            //if(x!=0){ comb.add(x);}
        }

        /*for (int i = 0; i < comb.size(); i++) {
            System.out.println(comb.get(i));
        }System.out.println(".................");
        */
        return x;
    }

    public int rec(Nodo princ, Nodo act, Nodo prec, int checked, Nodo b, Nodo c, String s, int idSum){
        //System.out.println(s+princ.id+", "+( b!=null ? b.id : "b")+", "+( c!=null ? c.id : "c"));
        System.out.println(s+""+princ.id+" - " + act.id + " :"+checked);

        int combos = 0;
        for(Arco a: act.archi)
            if(prec!=null ? a.a!=prec : true){

                if(checked==2) if(search(act, princ)) combos += rec(princ, a.a, act, checked+1, (checked==1 ? act : b), (checked==2 ? act : c), s+" ", idSum+a.a.id);
                if(checked==3)
                    searchFinal(act, )
                if(checked==3 && a.a==princ) return 1;

                combos += rec(princ, a.a, act, checked+1, (checked==1 ? act : b), (checked==2 ? act : c), s+" ", idSum+a.a.id);            
            }    

        return combos;
    }

    public boolean search(Nodo x, Nodo y){
        for(Arco a: x.archi)
            if(a.a==y) return false;
        return true;
    }

    public boolean searchFinal(Nodo x, Nodo y, Nodo z){
        boolean fine = false;

        for(Arco a: x.archi)
            if(a.a==y) return false;
            else if(a.a==z) fine = true;
        
        if(fine) return true;
        else return false;
    }

    public boolean exists(int x){
        for (int i = 0; i < comb.size(); i++)
            if(comb.get(i)==x)
                return true;
        return false;
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

            vacanze2 solver = new vacanze2();
            int risposta = solver.solve(N, M);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
    }
}