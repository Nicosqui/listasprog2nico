/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.listase.modelo;

/**
 *
 * @author nicolas
 */
public class NodoMotogp {
    private Piloto dato;
    private NodoMotogp siguiente;
    private NodoMotogp anterior;
    
    public NodoMotogp(Piloto dato) {
        this.dato = dato;
    }


    public Piloto getDato() {
        return dato;
    }

    public void setDato(Piloto dato) {
        this.dato = dato;
    }

    public NodoMotogp getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoMotogp siguiente) {
        this.siguiente = siguiente;
    }

    public NodoMotogp getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoMotogp anterior) {
        this.anterior = anterior;
    }

    
    
    
}
