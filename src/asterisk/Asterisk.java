/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asterisk;

import DAO.DAO;
import Model.Tema;
import Model.Termino;
import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.gui.dialog.DialogResult;
import com.googlecode.lanterna.gui.dialog.MessageBox;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.Console;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.fusesource.jansi.Ansi;
import static org.fusesource.jansi.Ansi.ansi;
import org.fusesource.jansi.AnsiConsole;
import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

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
    public final static void clearConsole() {
        try {

            for (int i = 0; i < 80; i++) {
                System.out.println("");
            }
        } catch (final Exception e) {
            //  Handle any exceptions.
        }
    }

    public static void main(String[] args) {

        Terminal terminal = TerminalFacade.createTerminal(System.in, System.out, Charset.forName("UTF8"));

        terminal.putCharacter('H');
        terminal.putCharacter('e');
        terminal.putCharacter('l');
        terminal.putCharacter('l');
        terminal.putCharacter('o');
        terminal.putCharacter('!');
        terminal.moveCursor(0, 0);

        GUIScreen guiScreen = TerminalFacade.createGUIScreen();
        guiScreen.getScreen().startScreen();
        DialogResult result = MessageBox.showMessageBox(guiScreen, "Hello 1989!", "Ready to configure your SoundBlaster IRQs?");
        /*      Scanner scan = new Scanner(System.in);
         AnsiConsole.systemInstall();

         System.out.println(ansi().fg(Color.YELLOW).a("Hello World").reset());
         System.out.println(ansi().fg(Color.BLUE).a("Hello World").reset());

         System.out.println(ansi().fg(Color.GREEN).a("Hello World").reset());
         */
// System.out.println(ansi().eraseScreen().render("@|red Hello|@ @|green World|@"));
        //System.err.println(ansi().eraseScreen());
        //  AnsiConsole.systemUninstall();
        //scan.next();
        //System.out.println("");
//        DAO dao = new DAO();
//        String termino;
//        int temaId;
//        int tituloId;
//        int option;
//
//        ArrayList<Tema> temas = dao.ListaTemas();
//        clearConsole();
//        System.out.println("Lista Temas: ");
//        for (Tema tema : temas) {
//            System.out.println(tema.toString());
//        }
//        
//        
//
//        System.out.println("----------------------------");
//        System.out.println("Tema ID:");
//        temaId = new Integer(scan.nextLine());
//        while (true) {
//            System.out.println("1. Ingresar\n2. Leer\n3. Temas\n4. Terminos Tema");
//            option = new Integer(scan.nextLine());
//
//            switch (option) {
//                case 1:
//                    while (true) {
//                        Termino t = new Termino();
//                        Tema tema = new Tema();
//                        System.out.println("Termino: ");
//                        termino = scan.nextLine();
//                        if (termino.equals("")) {
//                            break;
//                        }
//                        t.setTermino(termino);
//                        tema.setId(temaId);
//                        dao.create(t);
//                        clearConsole();
//                    }
//                    break;
//                case 2:
//                    while (true) {
//                        Termino tt = dao.darTermino();
//                        System.out.println(tt.toString());
//                        String k = scan.nextLine();
//                        if (!k.equals("")) {
//                            break;
//                        }
//                    }
//                    break;
//                case 3:
//                    System.out.println("Lista Temas: ");
//                    for (Tema tema : temas) {
//                        System.out.println(tema.toString());
//                    }
//                    break;
//
//                case 4:
//                    System.out.println("Lista Temas: ");
//                    for (Tema tema : temas) {
//                        System.out.println(tema.toString());
//                    }
//                    temaId = new Integer(scan.nextLine());
//                    ArrayList<Termino> teminos = dao.ListaTerminosTema(temaId);
//                    clearConsole();
//                    int i = 1;
//                    for (Termino t : teminos) {
//                        System.out.println(" -" + (i < 10 ? "  " : " ") + i + ". " + t.toString());
//                        i++;
//                    }
//
//                    break;
//            }
//
//        }
    }

}
