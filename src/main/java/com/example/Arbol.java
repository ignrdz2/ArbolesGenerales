package com.example;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Arbol {
    Nodo raiz;

    public Arbol(){
        this.raiz = null;
    }
    public Nodo getRaiz(){
        return this.raiz;
    }
    public void setRaiz(Nodo nodo){
        this.raiz = nodo;
    }

    public Nodo buscarPadre(int busc){
        if(this.raiz.getDato() == busc) return this.raiz.getPadre();
        else return buscarPadre(this.raiz, busc);
    }
    private Nodo buscarPadre(Nodo actual, int busc){
        Nodo padreEnc = null;
        if(actual.getDato() == busc){
            padreEnc = actual.getPadre();
        } else{
            if(actual.getHijo() != null){
                Nodo a = buscarPadre(actual.getHijo(), busc);
                if(a != null) padreEnc = a;
            }
            if(actual.getHermano() != null){
                Nodo a = buscarPadre(actual.getHermano(), busc);
                if(a != null) padreEnc = a;
            }
        }
        return padreEnc;
    }

    public Nodo buscar(int busc){
        return buscar(this.raiz, busc);
    }
    private Nodo buscar(Nodo nodo, int dato){
        if(nodo == null) return null;
        if(nodo.getDato() == dato) return nodo;
        else{
            Nodo encontradoEnHijo = null;
            if(nodo.getHijo() != null){
                encontradoEnHijo = buscar(nodo.getHijo(), dato);
            }
            if(encontradoEnHijo != null) return encontradoEnHijo;
    
            Nodo encontradoEnHermano = null;
            if(nodo.getHermano() != null){
                encontradoEnHermano = buscar(nodo.getHermano(), dato);
            }
            return encontradoEnHermano;
        }
    }

    public int altura(int dato){
        Nodo nodo = buscar(dato);
        if(nodo == null) return -1;
        else return altura(nodo);
    }
    private int altura(Nodo nodo){
        if(nodo == null) return -1;
        int alturaMax = -1;
        Nodo actual = nodo.getHijo();

        while(actual != null){
            int alturaHijo = altura(actual);
            if(alturaHijo > alturaMax) alturaMax = alturaHijo;
            actual = actual.getHermano();
        }
        
        return alturaMax + 1;
    }

    public int contarHojas(){
        return contarHojas(this.raiz);
    }
    private int contarHojas(Nodo nodo){
        if(nodo == null) return 0;
        if(nodo.getHijo() == null) return 1;
        else{
            int hojas = 0;
            Nodo actual = nodo.getHijo();
            while(actual != null){
                hojas += contarHojas(actual);
                actual = actual.getHermano();
            }
            return hojas;
        }
    }

    public int nivel(int dato){
        return nivel(this.raiz, dato, 0);
    }
    private int nivel(Nodo nodo, int dato, int nivel){
        if(nodo == null) return -1;
        if(nodo.getDato() == dato) return nivel;
        else{
            int nivelHijo = nivel(nodo.getHijo(), dato, nivel + 1);
            if(nivelHijo != -1) return nivelHijo;
            else return nivel(nodo.getHermano(), dato, nivel);
        }
    }

    public int tamano(){
        return tamano(this.raiz);
    }
    private int tamano(Nodo nodo){
        if(nodo == null) return 0;
        else{
            int tam = 1;
            Nodo actual = nodo.getHijo();
            while(actual != null){
                tam += tamano(actual);
                actual = actual.getHermano();
            }
            return tam;
        }
    }

    public int nodosEnNivel(int nivel){
        return nodosEnNivel(this.raiz, nivel, 0);
    }
    private int nodosEnNivel(Nodo nodo, int nivel, int nivelActual){
        if(nodo == null) return 0;
        if(nivelActual == nivel) return 1;
        else{
            int nodos = 0;
            Nodo actual = nodo.getHijo();
            while(actual != null){
                nodos += nodosEnNivel(actual, nivel, nivelActual + 1);
                actual = actual.getHermano();
            }
            return nodos;
        }
    }

    public int mostrarNodosEnNivel(int nivel){
        return mostrarNodosEnNivel(this.raiz, nivel, 0);
    }
    private int mostrarNodosEnNivel(Nodo nodo, int nivel, int nivelActual){
        if(nodo == null) return 0;
        if(nivelActual == nivel){
            System.out.print(nodo.getDato() + " ");
            return 1;
        } else{
            int nodos = 0;
            Nodo actual = nodo.getHijo();
            while(actual != null){
                nodos += mostrarNodosEnNivel(actual, nivel, nivelActual + 1);
                actual = actual.getHermano();
            }
            return nodos;
        }
    }

    public void mostrarHojasConNivel(){
        mostrarHojasConNivel(this.raiz, 0);
    }
    private void mostrarHojasConNivel(Nodo nodo, int nivel){
        if(nodo == null) return;
        if(nodo.getHijo() == null){
            System.out.println("Nodo: " + nodo.getDato() + " -> Nivel: " + nivel);
        } else{
            Nodo actual = nodo.getHijo();
            while(actual != null){
                mostrarHojasConNivel(actual, nivel + 1);
                actual = actual.getHermano();
            }
        }
    }

    public Nodo buscarMax(){
        return buscarMax(this.raiz);
    }
    private Nodo buscarMax(Nodo nodo){
        if(nodo == null) return null;
        Nodo max = nodo;
        Nodo actual = nodo.getHijo();
        while(actual != null){
            Nodo maxHijo = buscarMax(actual);
            if(maxHijo.getDato() > max.getDato()) max = maxHijo;
            actual = actual.getHermano();
        }
        return max;
    }

    public Nodo buscarMin(){
        return buscarMin(this.raiz);
    }
    private Nodo buscarMin(Nodo nodo){
        if(nodo == null) return null;
        Nodo min = nodo;
        Nodo actual = nodo.getHijo();
        while(actual != null){
            Nodo minHijo = buscarMin(actual);
            if(minHijo.getDato() < min.getDato()) min = minHijo;
            actual = actual.getHermano();
        }
        return min;
    }

    public void insertarHijo(int destino, Nodo nodo){
        // Destino indica donde sera agregado el nodo hijo
        // Nodo es el nodo a agregar

        if(this.raiz == null){
            this.raiz = nodo;
            this.raiz.setPadre(this.raiz);
        } else{
            insertarHijo(this.raiz, destino, nodo);
        }
    }
    private void insertarHijo(Nodo actual, int destino, Nodo nodo){
        if(actual.getDato() == destino){
            // Si se encuentra el nodo destino, se le agrega un hijo
            actual.setHijo(nodo);
            actual.getHijo().setPadre(actual);
        } else{
            if(actual.getHijo() != null){
                insertarHijo(actual.getHijo(), destino, nodo);
            }
            if(actual.getHermano() != null){
                insertarHijo(actual.getHermano(), destino, nodo);
            }
        }
    }

    public void insertarHermano(int destino, Nodo nodo){
        if(this.raiz == null){
            this.raiz = nodo;
            this.raiz.setPadre(this.raiz);
        } else{
            insertarHermano(this.raiz, destino, nodo);
        }
    }
    private void insertarHermano(Nodo actual, int destino, Nodo nodo){
        if(actual.getDato() == destino){
            actual.setHermano(nodo);
            if(actual.getPadre() != null){
                actual.getHermano().setPadre(actual.getPadre());
            }
        } else{
            if(actual.getHijo() != null){
                insertarHermano(actual.getHijo(), destino, nodo);
            }
            if(actual.getHermano() != null){
                insertarHermano(actual.getHermano(), destino, nodo);
            }
        }
    }


    public void recorridoPorNivel(){
        if(raiz == null) System.out.println("El arbol esta vacio.");
        else{
            Nodo aux = raiz; //Crea una copia del arbol
            Nodo aux2 = null;
            Cola cola = new Cola();
            cola.agregar(aux); //Se inserta la raiz en la cola

            while(!cola.estaVacia()){ //Si la cola no esta vacia sigue iterando
                aux = cola.remover();
                System.out.print(aux.getDato() + " ");

                //Si el nodo evaluado tiene un hijo lo inserta en la cola y lo guarda en aux2 para
                //luego verificar si tiene hermanos
                if(aux.getHijo() != null){
                    aux2 = aux.getHijo();
                    cola.agregar(aux2);
                }

                //Inserta a todos los hermanos del hijo del nodo evaluado
                while(aux2.getHermano() != null){
                    aux2 = aux2.getHermano();
                    cola.agregar(aux2);
                }
            }   
        }
    }

    public void preOrden(){
        preOrdenRec(raiz);
    }
    public void preOrdenRec(Nodo nodo){
        if(nodo != null){
            System.out.print(nodo.getDato() + " "); // Visita el nodo actual
            preOrdenRec(nodo.getHijo()); // Luego visita su hijo
            preOrdenRec(nodo.getHermano()); // Finalmente visita su hermano
        }
    }

    public void inOrden(){
        inOrdenRec(raiz);
    }
    public void inOrdenRec(Nodo nodo){
        if(nodo != null){
            inOrdenRec(nodo.getHijo()); //izquierda
            System.out.print(nodo.getDato() + " ");
            inOrdenRec(nodo.getHermano()); //derecha
        }
    }

    public void postOrden(){
        postOrdenRec(raiz);
    }
    public void postOrdenRec(Nodo nodo){
        if(nodo != null){
            postOrdenRec(nodo.getHijo()); //izquierda
            postOrdenRec(nodo.getHermano()); //derecha
            System.out.print(nodo.getDato() + " ");
        }
    }

    public void eliminarNodo(int dato){
        Nodo nodoAEliminar = buscar(dato);
        if(nodoAEliminar == null){
            System.out.println("El nodo con el dato " + dato + " no se encuentra en el árbol.");
        } else{
            Nodo padre = nodoAEliminar.getPadre();
            Nodo hijo = nodoAEliminar.getHijo();
            Nodo hermano = nodoAEliminar.getHermano();
            if(padre != null){
                if(padre.getHijo() == nodoAEliminar){
                    if(hijo != null){
                        padre.setHijo(hijo);
                        Nodo ultimoHijo = getUltimoHermano(hijo);
                        ultimoHijo.setHermano(hermano);
                    } else {
                        padre.setHijo(hermano);
                    }
                } else{
                    Nodo hermanoActual = padre.getHijo();
                    while(hermanoActual != null && hermanoActual.getHermano() != nodoAEliminar){
                        hermanoActual = hermanoActual.getHermano();
                    }
                    if(hermanoActual != null){
                        if(hijo != null){
                            hermanoActual.setHermano(hijo);
                            Nodo ultimoHijo = getUltimoHermano(hijo);
                            ultimoHijo.setHermano(hermano);
                        } else {
                            hermanoActual.setHermano(hermano);
                        }
                    }
                }
            }
            System.out.println("El nodo con el dato " + dato + " ha sido eliminado.");
        }
    }
    
    private Nodo getUltimoHermano(Nodo nodo){
        while(nodo != null && nodo.getHermano() != null){
            nodo = nodo.getHermano();
        }
        return nodo;
    }

    public void generarDot() {
        try (PrintWriter out = new PrintWriter(new FileWriter("arbol.dot"))) {
            out.println("digraph G {");
            generarDot(raiz, out);
            out.println("}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void generarDot(Nodo nodo, PrintWriter out) {
        if (nodo != null) {
            Nodo hijo = nodo.getHijo();
            while (hijo != null) {
                out.println("\"" + nodo.getDato() + "\" -> \"" + hijo.getDato() + "\";");
                generarDot(hijo, out);
                hijo = hijo.getHermano();
            }
        }
    }
}
