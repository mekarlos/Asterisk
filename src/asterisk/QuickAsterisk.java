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
import static asterisk.Asterisk.imprimirSeparador;
import static asterisk.Asterisk.seleccionarTema;
import java.io.FileNotFoundException;
import java.io.PrintStream;
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
    private static Termino auxTermino;
    private static Relacion auxRelacion;
    private static long id;

    public static void verTemaTituloTerminos(String line) {
        line = line.replace("pp ", "");
        line = line.replace("pp", "");
        if (line.length() > 0) {
            id = new Long(line);

            tema = dao.obtenerTema(id);
        }
        imprimirSeparador();
        if (tema != null) {
            System.out.println(tema.toString().toUpperCase());
        }

        ArrayList<Titulo> titulos = dao.obtenerListaTitulosTema(tema);

        for (int i = 0; i < titulos.size(); i++) {
            Titulo tit = titulos.get(i);
            tit.imprimeTituloTerminos();
        }
        imprimirSeparador();
    }

    public static void verTemaTitulos(String line) {
        line = line.replace("pt ", "");
        line = line.replace("pt", "");
        if (line.length() > 0) {
            id = new Long(line);

            tema = dao.obtenerTema(id);
        }
        ArrayList<Titulo> titulos = dao.obtenerListaTitulosTema(tema);
        imprimirSeparador();
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
        line = formatoCapital(line);
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
        tit = formatoCapital(tit);
        auxTitulo.setTitulo(tit);
        desc = line.substring(line.indexOf(":") + 1, line.indexOf("."));
        auxTitulo.setDescripcion(desc);
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
    }

    public static void seleccionarTitulo(String line) {
        id = new Long(line.replace("st ", ""));
        titulo = dao.obtenerTitulo(id);

    }

    public static String formatoCapital(String text) {
        String s = text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();;
        s = s.replace("  ", " ");
        return s;
    }

    public static void crearTermino(String line) {

        String term, def, tem, tit;
        auxTermino = new Termino();
        line = line.substring(3);
        if (line.indexOf(".") < 0) {
            System.out.println("Bad Command");
            return;
        }
        term = line.substring(0, line.indexOf(":"));
        ArrayList<Termino> terminos = dao.obtenerListaTerminos(term);
        if (terminos.size() > 0) {
            auxTermino = terminos.get(0);
        }
        term = formatoCapital(term);

        auxTermino.setTermino(term);
        def = line.substring(line.indexOf(":") + 1, line.indexOf("."));
        auxTermino.setDefinicion(def);
        if (line.indexOf("*") > 0) {
            tem = line.substring(line.indexOf("*") + 1, line.indexOf(" ", line.indexOf("*") + 1));
            id = new Long(tem);
            auxTema = dao.obtenerTema(id);
            auxTermino.getTemas().add(auxTema);
        } else {
            auxTermino.getTemas().add(tema);
        }
        if (line.indexOf("+") > 0) {
            tit = line.substring(line.indexOf("+") + 1);
            id = new Long(tit);
            auxTitulo = dao.obtenerTitulo(id);
            auxTermino.getTitulos().add(auxTitulo);
        } else {
            auxTermino.getTitulos().add(titulo);
        }

        dao.create(auxTermino);
        auxTema = auxTermino.getTemas().get(auxTermino.getTemas().size() - 1);
        auxTema = dao.obtenerTema(auxTema.getId());
        auxTitulo = auxTermino.getTitulos().get(auxTermino.getTitulos().size() - 1);
        auxTitulo = dao.obtenerTitulo(auxTitulo.getId());
        auxTema.getTerminos().add(auxTermino);
        auxTitulo.getTerminos().add(auxTermino);
        dao.update(auxTema);
        dao.update(auxTitulo);
        System.out.println("Termino Creado.");
    }

    public static void crearRelacion(String line) {
        line = line.substring(3);
        String origen = line.substring(0, line.indexOf(" "));
        line = line.substring(line.indexOf(" ") + 1);
        String destino = line.substring(0, line.indexOf(" "));
        line = line.substring(line.indexOf(" ") + 1);
        String def = line;
        long idOrigen, idDestino;
        idOrigen = new Long(origen);
        idDestino = new Long(destino);
        auxRelacion = new Relacion(origen, auxTermino, auxTermino);
        dao.create(auxRelacion);
        System.out.println("Relacion creada");
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
        } else if (line.equals("pa")) {
            verTemas();
        } else if (line.indexOf("pt") == 0) {
            verTemaTitulos(line);
        } else if (line.indexOf("pp") == 0) {
            verTemaTituloTerminos(line);
        } else if (line.indexOf("cp ") == 0) {
            crearTermino(line);
        } else if (line.indexOf("cr ") == 0) {
            crearRelacion(line);
        } else {
            System.out.println("Bad Input");
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        String s;
        System.setErr(new PrintStream("out.txt"));
        while (true) {
            try {
                System.out.println("Tema: [" + tema.getId() + "] " + tema);
                System.out.println("Titulo: " + titulo);
            } catch (Exception e) {

            }
            try {
                s = scan.nextLine();
                interprete(s);
            } catch (Exception e) {
                System.out.println("Bad Command");
            }
        }
    }
}
