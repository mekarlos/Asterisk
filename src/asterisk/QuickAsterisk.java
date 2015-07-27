/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asterisk;

import Modelo.Tema;
import java.util.Scanner;

/**
 *
 * @author Carlos
 */
public class QuickAsterisk {

    private static Scanner scan = new Scanner(System.in);

    public static void interprete(String line) {
        if (line.indexOf("ct ") == 0) {
            line = line.substring(3);
            Tema tema=new Tema();
            
        }
    }

    public static void main(String[] args) {

    }
}
