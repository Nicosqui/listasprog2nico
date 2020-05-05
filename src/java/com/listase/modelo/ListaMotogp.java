/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.listase.modelo;

import com.listase.excepciones.PilotoExcepcion;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author nicolas
 */
public class ListaMotogp implements Serializable{
    private NodoMotogp cabeza;
    
    public ListaMotogp() {
    }

    public NodoMotogp getCabeza() {
        return cabeza;
    }

    public void setCabeza(NodoMotogp cabeza) {
        this.cabeza = cabeza;
    }

    public void adicionarNodo(Piloto piloto)
    {
        if(cabeza ==null)
        {
            cabeza = new NodoMotogp(piloto);
        }
        else
        {
            //Lamo a mi ayudante
            NodoMotogp temp= cabeza;
            while(temp.getSiguiente()!=null) //Mientras que en siguiente exista algo
            {
                temp= temp.getSiguiente();
            }
            //temp va estar ubicado en el ultimo nodo
            temp.setSiguiente(new NodoMotogp(piloto));
            temp.getSiguiente().setAnterior(temp);
            
        }        
    }
    
    
    public void adicionarNodoAlInicio(Piloto piloto)
    {
        if(cabeza ==null)
        {
            cabeza = new NodoMotogp(piloto);
        }
        else
        {
            NodoMotogp temp= new NodoMotogp(piloto);
            temp.setSiguiente(cabeza);
            cabeza.setAnterior(temp);
            cabeza= temp;
        }
    }
    
    public short contarNodos()
    {
        if(cabeza ==null)
        {
            return 0;
        }
        else
        {
            //llamar a mi ayudante
            NodoMotogp temp= cabeza;
            short cont=1;
            while(temp.getSiguiente()!=null)
            {
                temp=temp.getSiguiente();
                cont++;
            }
            return cont;
        }
    }
    
    
    public String obtenerListadoPilotos()
    {
        
        //Un método recursivo que recoora mis infantes y que sacando la
        // info la adicione een el string
        
        return listarPilotos("");
    }
    
    public String listarPilotos(String listado)
    {
        if(cabeza !=null)
        {
            NodoMotogp temp= cabeza;            
            while(temp!=null)
            {
                listado += temp.getDato()+"\n";
                temp=temp.getSiguiente();
                
            }
            return listado;
        }
        return "No hay pilotos";
    }
    
    public List obtenerListaPilotos()
    {
        List<Piloto> listado = new ArrayList<>();
        //Un método recursivo que recoora mis infantes y que sacando la
        // info la adicione een el string
        listarPilotos(listado);
        return listado;
    }
    
    public void listarPilotos(List listado)
    {
        if(cabeza !=null)
        {
            NodoMotogp temp= cabeza;            
            while(temp!=null)
            {
                //listado += temp.getDato()+"\n";
                listado.add(temp.getDato());
                temp=temp.getSiguiente();                
            }            
        }
        
    }
    
    public void eliminarPiloto(short codigo ) throws PilotoExcepcion
    {
        if(cabeza !=null)
        {
            if(cabeza.getDato().getCodigo()==codigo)
            {
                cabeza=cabeza.getSiguiente();
                cabeza.setAnterior(null);
                return;
            }
            else
            {
                NodoMotogp temp=cabeza;
                while(temp.getSiguiente()!=null)
                {
                    if(temp.getSiguiente().getDato().getCodigo()== codigo)
                    {
                        //el que sigue es el que hay que eliminar
                        temp.setSiguiente(temp.getSiguiente().getSiguiente());
                        if(temp.getSiguiente()!=null)
                            temp.getSiguiente().setAnterior(temp);
                        return;
                    }
                    temp = temp.getSiguiente();
                }
                
                throw new PilotoExcepcion("El código "+codigo +" no existe en la lista");
            }
        }
        throw new PilotoExcepcion("La lista de pilotos está vacía");
    }
    
    public Piloto obtenerPiloto(short codigo ) throws PilotoExcepcion
    {
        if(cabeza !=null)
        {
            if(cabeza.getDato().getCodigo()==codigo)
            {                
                return cabeza.getDato();
            }
            else
            {
                NodoMotogp temp=cabeza;
                while(temp!=null)
                {
                    if(temp.getDato().getCodigo()== codigo)
                    {                                                
                        return temp.getDato();
                    }
                    temp = temp.getSiguiente();
                }
                
                throw new PilotoExcepcion("El código "+codigo +" no existe en la lista");
            }
        }
        throw new PilotoExcepcion("La lista de pilotos está vacía");
    }
}
