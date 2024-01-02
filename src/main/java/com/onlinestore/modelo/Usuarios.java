package com.onlinestore.modelo;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuarios {

    @Id
    @Column(name = "nombre")
    private String usuario;
    @Column(name = "contrasenya")
    private String contrasenya;

    public Usuarios(String usuario, String contrasenya) {
        this.usuario = usuario;
        this.contrasenya = contrasenya;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    @Override
    public String toString() {
        return "usuarios{" +
                "usuario='" + usuario + '\'' +
                ", contrasenya='" + contrasenya + '\'' +
                '}';
    }
}
