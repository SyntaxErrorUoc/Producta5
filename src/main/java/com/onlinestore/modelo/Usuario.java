package com.onlinestore.modelo;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @Column(name = "nombre")
    private String usuario;
    @Column(name = "contrasenya")
    private String contrasenya;

    public Usuario(String usuario, String contrasenya) {
        super();
        this.usuario = usuario;
        this.contrasenya = contrasenya;
    }

    public Usuario() {

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
