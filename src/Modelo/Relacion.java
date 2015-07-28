/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author mekar_000
 */
@Entity
public class Relacion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "s4")
    @SequenceGenerator(name = "s4", sequenceName = "s4",initialValue = 1,allocationSize = 1)
    private long id;
    private String conexion;
    @OneToOne(fetch = FetchType.EAGER)
    private Termino termino1;
    @OneToOne(fetch = FetchType.EAGER)
    private Termino termino2;

    public Relacion() {
    }

    public Relacion(String conexion, Termino termino1, Termino termino2) {
        this.conexion = conexion;
        this.termino1 = termino1;
        this.termino2 = termino2;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getConexion() {
        return conexion;
    }

    public void setConexion(String conexion) {
        this.conexion = conexion;
    }

    public Termino getTermino1() {
        return termino1;
    }

    public void setTermino1(Termino termino1) {
        this.termino1 = termino1;
    }

    public Termino getTermino2() {
        return termino2;
    }

    public void setTermino2(Termino termino2) {
        this.termino2 = termino2;
    }

    
  
}
