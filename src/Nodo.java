import java.util.ArrayList;

public class Nodo<T> {
    private T valor;
    private ArrayList<Nodo<T>> arcos;
    private ArrayList<Nodo<T>> siguiente;

    public T getValor() {
        return valor;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }

    public ArrayList<Nodo<T>> getArcos() {
        return arcos;
    }

    public void setArcos(ArrayList<Nodo<T>> arcos) {
        this.arcos = arcos;
    }

    public ArrayList<Nodo<T>> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(ArrayList<Nodo<T>> siguiente) {
        this.siguiente = siguiente;
    }

    public Nodo(T pValor){
        this.valor = pValor;
    }

}
