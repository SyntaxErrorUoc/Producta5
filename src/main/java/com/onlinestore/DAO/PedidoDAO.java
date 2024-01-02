package com.onlinestore.DAO;

import com.onlinestore.modelo.Pedido;

import java.util.ArrayList;

public interface PedidoDAO extends DAO <Pedido,Integer>{
    ArrayList<Pedido> obtenerPorCriterio(Integer columna, String criterio);

    ArrayList<Pedido> obtenerPorCriterio(String columna, String criterio);
}
