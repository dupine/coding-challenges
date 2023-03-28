// visito il grafo a ventaglio o ampiezza

class BFS{
    public static void BFS(Nodo start){
        Queue<Nodo> queue = new LinkedList<>();
        queue.add(start);
       
        while(!queue.isEmpty()){
            Nodo current = queue.poll();       
            for(Arco a: current.archi){
                Nodo neighbor = a.a;
                if(!neighbor.visitato){            
                    neighbor.visitato = true;
                    queue.add(neighbor);
                }
            }
        }
    }
}