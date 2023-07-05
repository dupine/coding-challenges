
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

public class muraglia3 {
    int N = 0;
    static class torre implements Comparable<torre>{
        int id;
        int h = 0;
        int pos = 0;

        torre(int id, int h){
            this.id = id;
            this.h = h;
        }

        void setH(int h) { this.h = h; }

        @Override
        public int compareTo(torre t) {
            return this.h - t.h;
        }
    }
    ArrayList<torre> mura = new ArrayList<>();
    int[] sorted;
    int[] originalPos;

    void chiedi(int x, int[] startEnd) {
        int s = 0, d = N-1;
        int idX = 0;
        for (int i = 0; i < N; i++) {
            if(mura.get(i).id == x){
                idX = i;
                break;
            }
        }
        torre tor;
        int hX = mura.get(idX).h;
        for (int i = idX+1; i < N; i++) {
            tor = mura.get(i);
            if(tor.h != hX){
                if(tor.id > x && tor.id < d){
                    d = tor.id;
                }
                if(tor.id < x && tor.id > s){
                    s = tor.id;
                }
            }
        }

        startEnd[0] = s; // start
        startEnd[1] = d; // end
    }

    void cambia(int x, int hNew) {
        for (int i = 0; i < N; i++) {
            if(mura.get(i).id == x){
                mura.get(i).h = hNew;
                break;
            }
        }
        Collections.sort(mura);
    }

    void inizializza(int N, int[] H) {
        for (int i = 0; i < N; i++) {
            mura.add(new torre(i, H[i]));
        }
        this.N = N;
        Collections.sort(mura);
    }
}

