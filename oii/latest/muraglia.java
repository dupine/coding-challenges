public class muraglia {
    static int MIN = Integer.MIN_VALUE;
    static int MAX = Integer.MAX_VALUE;

    public class Nodo{
        int val = MIN;

        public void setVal(int val){
            this.val = val;
        }
    }

    int size;
    int N, realSize;
    Nodo nodi[];

    void chiedi(int x, int[] nodiartEnd) {
        nodiartEnd[0] = nodi[1].val == nodi[size+x].val ? 0   : left(x, nodi[size+x].val, 1, 1, size)-1;
        nodiartEnd[1] = nodi[1].val == nodi[size+x].val ? N-1 : right(x, nodi[size+x].val, 1, 1, size)-1;
    }

    int left(int x, int h, int i, int l, int r){
        if(i >= nodi.length) return 1;
        if(r == l) return l;

        int ll = 1, rr = 1;
        if(nodi[2*i+1].val > h && x > (l+r)/2+1) rr = left(x, h, 2 * i + 1, (l+r)/2+1, r);
        if(nodi[2*i].val > h) ll = left(x, h, 2 * i, l, (l+r)/2);

        return Math.max(ll, rr);
    }

    int right(int x, int h, int i, int l, int r){
        if(i >= nodi.length) return N;
        if(l == r) return r;

        int ll = N, rr = N;
        if(nodi[2*i].val>h && x < (l+r)/2) ll = right(x, h, 2 * i, l, (l+r)/2);
        if(nodi[2*i+1].val>h) rr = right(x, h, 2 * i+1, (l+r)/2+1, r);

        return Math.min(ll, rr);
    }

    void cambia(int x, int h) {
        int u = size + x;
        nodi[u].val = h;

        while(u > 1){
            u /= 2;
            nodi[u].setVal(Math.max(nodi[2*u].val, nodi[2*u+1].val));
        }
    }

    void inizializza(int N, int[] H) {
        this.N = N;
        size = 2 << ((int)Math.ceil(Math.log(N) / Math.log(2)) - 1);
        nodi = new Nodo[size*2];
        
        for (int i = 0; i < size*2; i++) {
            nodi[i] = new Nodo();
        }

        for (int i = 0; i < N; i++) {
            nodi[size+i].setVal(H[i]);
        }

        int i = (size + N)/2;
        do {
            nodi[i].setVal(Math.max(nodi[2*i].val, nodi[2*i+1].val));
            i--;
        } while (i > 0);
    }
}

