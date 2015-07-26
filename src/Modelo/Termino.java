/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Termino implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "s2")
    @SequenceGenerator(name = "s2", sequenceName = "s2",initialValue = 1,allocationSize = 1)
    private long id;
    private String termino;
    private String definicion;
    @ManyToMany(mappedBy = "terminos", fetch = FetchType.EAGER)
    private ArrayList< Titulo> titulos;

    @ManyToMany(mappedBy = "terminos", fetch = FetchType.EAGER)
    private ArrayList<Tema> temas;

    public Termino() {
        temas = new ArrayList<>();
        titulos = new ArrayList<>();
    }

    public Termino(String termino, String definicion) {
        this.termino = termino;
        this.definicion = definicion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTermino() {
        return termino;
    }

    public void setTermino(String termino) {
        this.termino = termino;
    }

    public String getDefinicion() {
        return definicion;
    }

    public void setDefinicion(String definicion) {
        this.definicion = definicion;
    }

    public ArrayList<Tema> getTemas() {
        return temas;
    }

    public void setTemas(ArrayList<Tema> temas) {
        this.temas = temas;
    }

    public ArrayList<Titulo> getTitulos() {
        return titulos;
    }

    public void setTitulos(ArrayList<Titulo> titulos) {
        this.titulos = titulos;
    }

    @Override
    public String toString() {
        return termino;
    }

}
