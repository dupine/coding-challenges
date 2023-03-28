// visito il grafo in profondita'

class DFS{
    public static void DFS(Nodo start){
        start.visitato = true;

        for(Arco a: start.archi){
            Nodo neighbor = a.a;
            if(!neighbor.visitato){            
                DFS(neighbor);
            }
        }
    }
}