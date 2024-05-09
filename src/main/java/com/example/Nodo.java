package com.example;

public class Nodo {
    private Nodo hermano;
    private Nodo hijo;
    private Nodo padre;
    private int dato;

    public Nodo(int dato){
        this.dato = dato;
        this.hermano = null;
        this.hijo = null;
        this.padre = null;
    }

    public Nodo getHermano(){
        return this.hermano;
    }
    public void setHermano(Nodo nodo){
        this.hermano = nodo;
    }
    public Nodo getHijo(){
        return this.hijo;
    }
    public void setHijo(Nodo nodo){
        this.hijo = nodo;
    }
    public Nodo getPadre(){
        return this.padre;
    }
    public void setPadre(Nodo nodo){
        this.padre = nodo;
    }
    public int getDato(){
        return this.dato;
    }
    public void setDato(int dato){
        this.dato = dato;
    }
}
