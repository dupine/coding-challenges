class recPercorso{
    static int tuttoCammino(Nodo x, int mS){
        if(x.padre == null){
            return mS;
        }
        return tuttoCammino(x.padre, (x.costoPercorso > mS) ? x.costoPercorso : mS);
    }
}