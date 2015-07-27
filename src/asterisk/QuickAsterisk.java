/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asterisk;

import Datos.DAO;
import Modelo.Tema;
import Modelo.Titulo;
import static asterisk.Asterisk.imprimirSeparador;
import static asterisk.Asterisk.seleccionarTema;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Carlos
 */
public class QuickAsterisk {

    private static Scanner scan = new Scanner(System.in);
    private static DAO dao = new DAO();
    private static Tema tema;
    private static Titulo titulo;
    private static Tema auxTema;
    private static Titulo auxTitulo;
    private static long id;

    public static void verTemaTitulos(String line) {
        line = line.replace("pt ", "");
        if (line.length() > 0) {
            id = new Long(line);
            imprimirSeparador();
            tema = dao.obtenerTema(id);
        }
        ArrayList<Titulo> titulos = dao.obtenerListaTitulosTema(tema);
        if (tema != null) {

            System.out.println(tema.toString().toUpperCase());
        }
        for (int i = 0; i < titulos.size(); i++) {
            Titulo tit = titulos.get(i);
            tit.imprimeTitulos();
        }
        imprimirSeparador();
    }

    public static void verTemas() {

        if (tema != null) {
            System.out.println(tema.toString().toUpperCase());
        }
        ArrayList<Tema> temas = dao.obtenerListaTemas();
        imprimirSeparador();
        System.out.print("\n");
        for (int i = 0; i < temas.size(); i++) {
            Tema t = temas.get(i);
            System.out.print(t.toText());
        }
        System.out.print("\n");
        imprimirSeparador();
    }

    public static void crearTema(String line) {
        line = line.substring(3);
        auxTema = new Tema();
        auxTema.setTema(line);
        dao.create(auxTema);
        System.out.println("Tema Creado.");
    }

    public static void crearTitulo(String line) {
        line = line.substring(3);
        if (line.indexOf(".") < 0) {
            System.out.println("Bad Command");
            return;
        }
        auxTitulo = new Titulo();
        String tit, desc, tem, princ;
        tit = line.substring(0, line.indexOf(":"));
        desc = line.substring(line.indexOf(":") + 1, line.indexOf("."));
        if (line.indexOf("*") > 0) {
            tem = line.substring(line.indexOf("*") + 1, line.indexOf(" ", line.indexOf("*") + 1));
            id = new Long(tem);
            auxTema = dao.obtenerTema(id);
            auxTitulo.setTema(auxTema);
        } else {
            auxTitulo.setTema(tema);
        }
        if (line.indexOf("+") > 0) {
            princ = line.substring(line.indexOf("+") + 1);
            id = new Long(princ);
            auxTitulo.setPrincipal(dao.obtenerTitulo(id));
        } else {
            auxTitulo.setPrincipal(titulo);
        }
        if (auxTitulo.getPrincipal() == null) {
            auxTitulo.setNivel(0);
        } else {
            auxTitulo.setNivel(auxTitulo.getNivel() + 1);
        }
        dao.create(auxTitulo);
        System.out.println("Titulo Creado.");
    }

    public static void seleccionarTema(String line) {
        id = new Long(line.replace("sa ", ""));
        tema = dao.obtenerTema(id);
        System.out.println("Tema:" + tema);
    }

    public static void seleccionarTitulo(String line) {
        id = new Long(line.replace("st ", ""));
        titulo = dao.obtenerTitulo(id);
        System.out.println("Titulo:" + titulo);

    }

    public static void crearTermino(String line) {

    }

    public static void crearRelacion(String line) {

    }

    public static void interprete(String line) {

        if (line.indexOf("ca ") == 0) {
            crearTema(line);
        } else if (line.indexOf("ct ") == 0) {
            crearTitulo(line);
        } else if (line.indexOf("sa") == 0) {
            seleccionarTema(line);
        } else if (line.indexOf("st") == 0) {
            seleccionarTitulo(line);
        } else if (line.equals("pa ")) {
            verTemas();
        } else if (line.equals("pt ")) {
            verTemaTitulos(line);
        } else if (line.indexOf("cp ") == 0) {
            crearTermino(line);
        } else if (line.indexOf("cr ") == 0) {
            crearRelacion(line);
        } else {
            System.out.println("Bad Input");
        }
    }

    public static void main(String[] args) {
        String s;

        while (true) {
            s = scan.nextLine();
            interprete(s);
        }
    }
}
