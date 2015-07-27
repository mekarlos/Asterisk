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

    public static void verTemas() {
        imprimirSeparador();
        if (tema != null) {
            System.out.println(tema.toString().toUpperCase());
        }
        ArrayList<Tema> temas = dao.obtenerListaTemas();
        for (int i = 0; i < temas.size(); i++) {
            Tema t = temas.get(i);
            System.out.print(t.toText());
        }
        imprimirSeparador();
    }

    public static void interprete(String line) {
        long id;
        if (line.indexOf("ca ") == 0) {
            line = line.substring(3);
            auxTema = new Tema();
            auxTema.setTema(line);
            dao.create(auxTema);
            System.out.println("Tema Creado.");
        } else if (line.indexOf("ct ") == 0) {
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
        } else if (line.indexOf("sa") == 0) {
            id = new Long(line.replace("sa ", ""));
            tema = dao.obtenerTema(id);
            System.out.println("Tema:" + tema);
        } else if (line.indexOf("st") == 0) {
            id = new Long(line.replace("st ", ""));
            titulo = dao.obtenerTitulo(id);
            System.out.println("Titulo:" + titulo);

        } else {

        }
    }

    public static void main(String[] args) {
        String s;
        verTemas();
        while (true) {
            s = scan.nextLine();
            interprete(s);
        }
    }
}
