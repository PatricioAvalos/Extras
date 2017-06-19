/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller4app;
import java.util.Iterator;
import java.util.LinkedList;
/**
 *
 * @author D4C
 */
public class ListaAsignaturas {
    private LinkedList listaAsignaturas = new LinkedList();
    
    public void add(Asignatura asig){
        listaAsignaturas.add(asig);
    }
    
    public Iterator getIterator(){
    Iterator iter = listaAsignaturas.iterator();
    return iter;}
   
}


