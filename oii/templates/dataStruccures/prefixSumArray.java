class prefixSumArray{

    // lista
    private static void psaList(int N, int a[]){
        int psa[] = new int[N + 1];
        for (int i = 1; i < N; i++) {
            psa[i] = a[i]+psa[i-1];
        }
    }


    // matrice
    private static void psaMatrix(int N, int M, int a[][]){
        int psa[][] = new int[N][M];

        psa[0][0] = a[0][0];
        // riempo la prima riga e la prima colonna
        for (int i = 1; i < N; i++){ psa[i][0] = psa[i - 1][0] + a[i][0]; }
        for (int i = 1; i < M; i++){ psa[0][i] = psa[0][i - 1] + a[0][i]; }

        for (int i = 1; i < N; i++){
            for (int j = 1; j < M; j++)
                // elemento sopra +  elemento sinistra - elemento sopra a sinistra + elemento stesso
                psa[i][j] = ( psa[i - 1][j] + psa[i][j - 1] ) - psa[i - 1][j - 1] + a[i][j];
        }
    }
}