public class muraglia {
    int size;
    int N, realSize;
    int nodi[];

    void chiedi(int x, int[] nodiartEnd) {
        nodiartEnd[0] = nodi[1] == nodi[size+x] ? 0   : left(x+1, nodi[size+x], 1, 1, size)-1;
        nodiartEnd[1] = nodi[1] == nodi[size+x] ? N-1 : right(x, nodi[size+x], 1, 1, size)-1;
    }

    int left(int x, int h, int i, int l, int r){
        
        if(l == r) return r;

        if(nodi[2*i+1] > h && x >= (l+r)/2+1) 
            return left(x, h, 2 * i + 1, (l+r)/2+1, r);
        else if(nodi[2*i] > h) 
            return left(x, h, 2 * i, l, (l+r)/2);

        return 1;
    }

    int right(int x, int h, int i, int l, int r){
        
        if(l == r) return r;

        if(nodi[2*i] > h && x <= r)
            return right(x, h, 2 * i, l, (l+r)/2);
        else if(nodi[2*i+1] > h)
            return right(x, h, 2 * i + 1, (l+r)/2+1, r);
            

        return N;
    }

    void cambia(int x, int h) {
        int u = size + x;
        nodi[u] = h;

        while(u > 1){
            u /= 2;
            nodi[u] = Math.max(nodi[2*u], nodi[2*u+1]);
        }
    }

    void inizializza(int N, int[] H) {
        this.N = N;
        size = 2 << ((int)Math.ceil(Math.log(N) / Math.log(2)) - 1);
        nodi = new int[size*2];

        for (int i = 0; i < N; i++) {
            nodi[size+i] = H[i];
        }

        int i = (size + N)/2;
        do {
            nodi[i] = Math.max(nodi[2*i], nodi[2*i+1]);
            i--;
        } while (i > 0);
    }
}