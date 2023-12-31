package com.onlinestore.DAO;

import java.util.ArrayList;

public interface DAO<T,k> {

    String insertar(T a) ;
    void modificar (T a);
    void eliminar(k a) ;
    ArrayList<T> obtenerTodos() ;
    T obtenerUno(k id);
    ArrayList<T> obtenerPorCriterio(k columna,k criterio);
}