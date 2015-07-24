/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author Carlos
 */
@Entity
public class Titulo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "s3")
    @SequenceGenerator(name = "s3", sequenceName = "ms3")
    private int id;
    private String titulo;
    private int nivel;
    private String descripcion;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER, mappedBy = "principal")
    private ArrayList<Titulo> subtitulos;
    @ManyToOne(fetch = FetchType.LAZY)
    private Titulo principal;
    @ManyToOne(fetch = FetchType.LAZY)
    private Tema tema;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER, mappedBy = "titulo")
    private Collection<Termino> terminos;

    public Titulo() {
    }

    public Titulo(int id, String titulo, int nivel) {
        this.id = id;
        this.titulo = titulo;
        this.nivel = nivel;
    }

    public ArrayList<Titulo> getSubtitulos() {
        return subtitulos;
    }

    public void setSubtitulos(ArrayList<Titulo> subtitulos) {
        this.subtitulos = subtitulos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Titulo getPrincipal() {
        return principal;
    }

    public void setPrincipal(Titulo principal) {
        this.principal = principal;
    }

    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }

    public Collection<Termino> getTerminos() {
        return terminos;
    }

    public void setTerminos(Collection<Termino> terminos) {
        this.terminos = terminos;
    }

    @Override
    public String toString() {
        return "-" + id + " " + titulo + ((nivel == 1) ? "" : "");
    }

}
