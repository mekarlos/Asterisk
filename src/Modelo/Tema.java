/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author Carlos
 */
@Entity
public class Tema implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "s1")
    @SequenceGenerator(name = "s1", sequenceName = "ms1")
    private long id;
    private String tema;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "TemaTitulo",
            joinColumns = {
                @JoinColumn(name = "temaid", referencedColumnName = "ID")},
            inverseJoinColumns = {
                @JoinColumn(name = "tituloid", referencedColumnName = "ID")})
    private ArrayList<Titulo> titulos;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "TemaTermino",
            joinColumns = {
                @JoinColumn(name = "temaid", referencedColumnName = "ID")},
            inverseJoinColumns = {
                @JoinColumn(name = "terminoid", referencedColumnName = "ID")})
    private ArrayList<Termino> terminos;

    public Tema() {
         titulos = new ArrayList<>();
           terminos = new ArrayList<Termino>();

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public ArrayList<Termino> getTerminos() {
        return terminos;
    }

    public void setTerminos(ArrayList<Termino> terminos) {
        this.terminos = terminos;
    }

    public ArrayList<Titulo> getTitulos() {
        return titulos;
    }

    public void setTitulos(ArrayList<Titulo> titulos) {
        this.titulos = titulos;
    }
    

}
