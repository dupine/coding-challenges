int quadri(int N, long long M, int V[]) {
    int* qSum = new int[N+1];
    qSum[0] = 0;
        for (int i = 1; i < N+1; i++) {
    if(V[i-1]>M){
        return 0;
    }
    qSum[i] = qSum[i-1]+V[i-1];
}

    int l = N+1/2;
    int last = 0;
    while(true){
        if(tryThis(l, N, M, qSum)){
            if(l+1==last){break;}
            last = l++;
        } else {
            if(l-1==last){break;}
            last = l--;
        }
    }

    return l;
}


bool tryThis(int l, int N, int M, int qSum[]){
    for (int i = l; i < N+1; i++) {
        if(qSum[i]-qSum[i-l]>M){
            return false;
        }
    }
    return true;
}
