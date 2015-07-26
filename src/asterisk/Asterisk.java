/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asterisk;

import Datos.DAO;
import Modelo.Relacion;
import Modelo.Tema;
import Modelo.Termino;
import Modelo.Titulo;
import java.io.Console;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Carlos
 */
public class Asterisk {

    /**
     * Parses a string with ANSI color codes based on the input
     *
     * @param input the input string
     * @return the parsed ANSI string
     */
    /**
     * @param args the command line arguments
     */
    private static Scanner scan = new Scanner(System.in);
    private static DAO dao = new DAO();
    private static Tema tema;
    private static Titulo titulo;

    public final static void clearConsole() {
        try {

            for (int i = 0; i < 80; i++) {
                System.out.println("");
            }
        } catch (final Exception e) {
            //  Handle any exceptions.
        }
    }

    public static String formatoCapital(String text) {
        String s = text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();;
        s = s.replace("  ", " ");
        return s;
    }

    public static Termino darTermino(String termino) {
        return dao.obtenerTermino(termino);
    }

    public static void crearTema() {
        System.out.println("Ingrese Nombre del Tema: ");
        String t = scan.nextLine();
        t = formatoCapital(t);
        Tema tema = new Tema(t);
        dao.create(tema);
    }

    public static void crearTitulo() {
        System.out.println("Ingrese Titulo:");
        String tit = formatoCapital(scan.nextLine());
        String desc = formatoCapital(scan.nextLine());
        Titulo titulo = new Titulo();
        titulo.setDescripcion(desc);
        titulo.setTitulo(tit);
        if (tema == null) {
            tema = seleccionarTema();
        }
        titulo.setTema(tema);
        Titulo padre = seleccionarTituloTema(tema);
        titulo.setPrincipal(padre);
        if (padre == null) {
            titulo.setNivel(0);
        } else {
            titulo.setNivel(padre.getNivel() + 1);
        }
        dao.create(titulo);
    }

    public static Titulo seleccionarTitulo() {
        ArrayList<Titulo> titulos = dao.obtenerListaTitulos();
        System.out.println("0 Sin titulo");
        for (int i = 0; i < titulos.size(); i++) {
            Titulo titulo = titulos.get(i);
            System.out.println((i + 1) + " " + titulo.toString());

        }
        int opt;
        System.out.println("Seleccione Titulo:");
        opt = new Integer(scan.nextLine());
        try {
            return titulos.get(opt - 1);
        } catch (Exception e) {
            return null;
        }
    }

    public static Titulo seleccionarTituloTema() {
        ArrayList<Titulo> titulos = dao.obtenerListaTitulosTema(tema);
        if (tema == null) {
            seleccionarTema();
        }
        System.out.println("0 Sin titulo");
        for (int i = 0; i < titulos.size(); i++) {
            Titulo titulo = titulos.get(i);
            System.out.println(titulo.toString());
        }
        long opt;
        System.out.println("Seleccione Titulo:");
        opt = new Long(scan.nextLine());
        try {
            return dao.obtenerTitulo(opt);
        } catch (Exception e) {
            return null;
        }
    }

    public static Titulo seleccionarTituloTema(Tema t) {
        ArrayList<Titulo> titulos = dao.obtenerListaTitulosTema(t);
        if (tema == null) {
            seleccionarTema();
        }
        System.out.println("0 Sin titulo");
        for (int i = 0; i < titulos.size(); i++) {
            Titulo titulo = titulos.get(i);
            System.out.println(titulo.toString());
        }
        long opt;
        System.out.println("Seleccione Titulo:");
        opt = new Long(scan.nextLine());
        try {
            return dao.obtenerTitulo(opt);
        } catch (Exception e) {
            return null;
        }
    }

    public static Tema seleccionarTema() {
        ArrayList<Tema> temas = dao.obtenerListaTemas();
        System.out.println("0 Sin Tema");
        for (int i = 0; i < temas.size(); i++) {
            Tema tema = temas.get(i);
            System.out.println((i + 1) + " " + tema.toString());
        }
        System.out.println("Seleccione el tema");
        int opt;
        opt = new Integer(scan.nextLine());
        try {
            return temas.get(opt - 1);
        } catch (Exception e) {
            return null;
        }
    }

    public static Termino seleccionarTermino() {
        System.out.println("Ingrese termino buscado: ");
        String term = scan.nextLine().toLowerCase();
        ArrayList<Termino> terminos = dao.obtenerListaTerminos(term, tema);
        for (int i = 0; i < terminos.size(); i++) {
            Termino t = terminos.get(i);
            System.out.println((i + 1) + " " + t.toString());
        }
        System.out.println("Seleccione termino: ");
        int opt = new Integer(scan.nextLine());
        return terminos.get(opt);
    }

    public static void crearRelacion() {
        Termino origen, destino;
        String conexion;

        Relacion relacion = new Relacion();
        origen = seleccionarTermino();
        destino = seleccionarTermino();
        System.out.println("Ingrese descripcion conexion:");
        conexion = scan.nextLine();
        relacion.setTermino1(origen);
        relacion.setTermino1(destino);
        relacion.setConexion(conexion);
        dao.create(relacion);
    }

    public static void crearTermino() {
        if (tema == null) {
            tema = seleccionarTema();
        }
        if (titulo == null) {
            titulo = seleccionarTituloTema();
        }
        String term, def;
        System.out.println("Ingrese Termino:");
        term = scan.nextLine();
        term = formatoCapital(term);
        System.out.println("Ingrese Definicion:");
        def = scan.nextLine();
        def = formatoCapital(def);
        Termino termino = new Termino(term, def);
        dao.create(termino);

    }

    public static void verTemas() {
        ArrayList<Tema> temas = new ArrayList<Tema>();
        for (int i = 0; i < temas.size(); i++) {
            Tema t = temas.get(i);
            System.out.println(t.toString());
        }
    }

    public static void verTemaTitulos() {
        if (tema == null) {
            tema = seleccionarTema();
        }
        ArrayList<Titulo> titulos = dao.obtenerListaTitulosTema(tema);

        for (int i = 0; i < titulos.size(); i++) {
            Titulo tit = titulos.get(i);
            tit.imprimeTitulos();
        }

    }

    public static void verTemaTituloTerminos() {
        if (tema == null) {
            tema = seleccionarTema();
        }
        ArrayList<Titulo> titulos = dao.obtenerListaTitulosTema(tema);

        for (int i = 0; i < titulos.size(); i++) {
            Titulo tit = titulos.get(i);
            tit.imprimeTituloTerminos();
        }

    }

    public static void verTemaTerminos() {
        if (tema == null) {
            tema = seleccionarTema();
        }
        for (int i = 0; i < tema.getTerminos().size(); i++) {
            Termino tt = tema.getTerminos().get(i);
            System.out.println(tt.toString());
        }
    }

    public static void mostrarMenu() {

        System.out.println("\t\tMenu");
        System.out.println("1.Crear Tema");
        System.out.println("2.Crear Titulo");
        System.out.println("3.Crear Termino");
        System.out.println("4.Crear Relacion");
        System.out.println("5.Ver Temas");
        System.out.println("6.Ver Tema Titulos ");
        System.out.println("7.Ver Tema Terminos");
        System.out.println("8.Ver Tema Titulos Termino");
        System.out.println("9.Seleccionar Tema");
        System.out.println("10.Seleccionar Titulo");
        System.out.println("11.Seleccionar Termino");
        System.out.println("12.Salir");

    }

    public static void ejecutar() {
        int opt;
        System.out.println("Ingrese una opción: ");
//        scan.nextLine();
        opt = new Integer(scan.nextLine());
        switch (opt) {
            case 1:
                crearTema();
                break;
            case 2:
                crearTitulo();
                break;
            case 3:
                crearTermino();
                break;
            case 4:
                crearRelacion();
                break;
            case 5:
                verTemas();
                break;
            case 6:
                verTemaTitulos();
                break;
            case 7:
                verTemaTerminos();
                break;
            case 8:
                verTemaTituloTerminos();
                break;
            case 9:
                seleccionarTema();
                break;
            case 10:
                seleccionarTituloTema();
                break;
            case 11:
                seleccionarTermino();
                break;
            case 12:
                seleccionarTituloTema();
                break;
            case 13:
                System.exit(0);
                break;

        }
    }

    public static void main(String[] args) {
        while (true) {
            mostrarMenu();
            ejecutar();

        }
    }

}
