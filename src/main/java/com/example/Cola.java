package com.example;

import java.util.LinkedList;

public class Cola {
    private LinkedList<Nodo> cola;

    public Cola(){
        this.cola = new LinkedList<>();
    }
    public boolean estaVacia(){
        return cola.isEmpty();
    }
    public void agregar(Nodo nodo){
        cola.addLast(nodo);
    }
    public Nodo remover(){
        if(!estaVacia()) return cola.removeFirst();
        return null;
    }
    public Nodo verPrimero(){
        if(!estaVacia()) return cola.getFirst();
        return null;
    }
}
