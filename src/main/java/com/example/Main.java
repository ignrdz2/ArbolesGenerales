package com.example;

public class Main {
    public static void main(String[] args) {
        Arbol arbol = new Arbol();
        arbol.setRaiz(new Nodo(14));

        arbol.insertarHijo(14, new Nodo(3));
        arbol.insertarHermano(3, new Nodo(27));
        arbol.insertarHermano(27, new Nodo(19));

        arbol.insertarHijo(3, new Nodo(7));
        arbol.insertarHermano(7, new Nodo(12));
        arbol.insertarHijo(12, new Nodo(5));
        arbol.insertarHermano(5, new Nodo(17));

        arbol.insertarHijo(27, new Nodo(9));
        arbol.insertarHermano(9, new Nodo(16));
        arbol.insertarHermano(16, new Nodo(4));
        arbol.insertarHijo(16, new Nodo(6));

        System.out.println("Recorrido por nivel:");
        arbol.recorridoPorNivel();
        System.out.println("\nPreorden:");
        arbol.preOrden();
        System.out.println("\nInorden:");
        arbol.inOrden();
        System.out.println("\nPostorden:");
        arbol.postOrden();

        System.out.println(" ");

        int aBuscar = 6;

        Nodo buscado = arbol.buscar(aBuscar);
        if (buscado != null) System.out.println("\nNodo " + aBuscar + " encontrado -> "  + buscado.getDato());
        else System.out.println("\nEl nodo " + aBuscar + " no esta en el arbol.");

        Nodo padre = arbol.buscarPadre(aBuscar);
        if (padre != null) System.out.println("El padre del nodo " + aBuscar + " es: " + padre.getDato());
        else System.out.println("El nodo " + aBuscar +" no tiene padre.");

        System.out.println("Altura del nodo " + aBuscar + ": " + arbol.altura(aBuscar));
        System.out.println("Nivel del nodo " + aBuscar + ": " + arbol.nivel(aBuscar));

        System.out.println("Cantidad de hojas del arbol: " + arbol.contarHojas());
        System.out.println("Tamaño del arbol: " + arbol.tamano());

        int nivel = 2;
        System.out.println("Cantidad de nodos en el nivel: " + nivel + " -> " + arbol.nodosEnNivel(nivel));
        System.out.println("Nodos en el nivel " + nivel + ": ");
        arbol.mostrarNodosEnNivel(nivel);

        System.out.println("\nTodas las hojas y su respectivo nivel:");
        arbol.mostrarHojasConNivel();

        System.out.println("Nodo mas grande: " + arbol.buscarMax().getDato());
        System.out.println("Nodo mas pequeño: " + arbol.buscarMin().getDato());

        //Mostrar graficamente el arbol en un archivo .dot
        //arbol.eliminarNodo(27);
        arbol.insertarHermano(4, new Nodo(15));
        arbol.insertarHijo(15, new Nodo(1));
        arbol.insertarHermano(1, new Nodo(55));
        arbol.generarDot();
    }
}