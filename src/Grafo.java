import java.util.ArrayList;

public class Grafo<T extends Comparable<T>>{
    private ArrayList<Nodo> listadoVertices;

    public ArrayList<Nodo> getListadoVertices() {
        return listadoVertices;
    }


    public Grafo(){
        listadoVertices = new ArrayList<Nodo>();

        int i = 0;
    }
}
