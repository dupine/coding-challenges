class dijkstra{
    // ---------- Dijkstra ----------

    // ricorsione per rivisitare tutti i nodi dell'albero
    static int tuttoCammino(Nodo x, int mS){
        if(x.padre == null){
            return mS;
        }
        return tuttoCammino(x.padre, (x.costoPercorso > mS) ? x.costoPercorso : mS);
    }

    // creazione albero
    static void camminiMinimi(Nodo x){
        // pulizia
        for(Nodo n: grafo){
            n.costoPercorso = Integer.MAX_VALUE;
            n.padre = null;
        }

        // costruzione partenza
        x.costoPercorso = 0;
        PriorityQueue<Nodo> s = new PriorityQueue<>();
        s.add(x);
        
        // costruzione della copertura
        while( !s.isEmpty() ){
            Nodo attuale = s.poll();
            for(Arco a: attuale.archi){
                Nodo vicino = a.a;
                int nDist = attuale.costoPercorso + a.peso;
                if( nDist < vicino.costoPercorso ){
                    vicino.costoPercorso = nDist;
                    vicino.padre = attuale;
                    s.add(vicino);
                }
            }
        }
    }
}