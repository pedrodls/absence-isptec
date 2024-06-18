/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isptec.utils;

import isptec.listas.Listas;
import utils.MainMenu;

import java.text.DecimalFormat;
import java.util.Objects;
import java.util.Scanner;

/**
 *
 * @author aires
 */
public class Utils {

    public static boolean validarAnoLetivo(String anoLetivo) {

        Integer ano_1 = Integer.parseInt(anoLetivo.split("/")[0]);
        Integer ano_2 = Integer.parseInt(anoLetivo.split("/")[1]);

        return ano_1 == ano_2 - 1;
    }

    public static boolean concorda() {
        String opcoes[] = {
                "Concorda", "Nao Concorda"
        };
        return Listas.enviarLerOpcaoEscolhida(opcoes) == 1;
    }

    public static boolean concorda(String label1, String label2) {
        String opcoes[] = {
                label1, label2
        };
        return Listas.enviarLerOpcaoEscolhida(opcoes) == 1;
    }

    public static boolean continua() {
        System.out.print("Continua ? [s]/[S]: ");
        String resposta = next();
        if (resposta.equalsIgnoreCase(""))
            resposta = "s";
        return resposta.equalsIgnoreCase("s")
                || resposta.equalsIgnoreCase("S");
    }

    public static boolean continua(String msg) {
        System.out.println(msg);
        return continua();
    }

    public static boolean continua(String msg, String label1, String label2) {
        System.out.println(msg);

        return concorda(label1, label2);
    }

    public static boolean editarCampo(String field, String old) {
        System.out.println("Editar " + field + "(" + old + ")?");

        return concorda("Sim", "Não");
    }

    public static boolean pergunta(String msg) {
        // Scanner scanner = new Scanner(System.in);
        System.out.print(msg + " ? [s]/[S]: ");
        String resposta = next();
        if (resposta.equalsIgnoreCase(""))
            resposta = "s";
        return resposta.equalsIgnoreCase("s")
                || resposta.equalsIgnoreCase("S");
    }

    public static String sameLine(String word) {
        word = (word.isBlank() || word.isEmpty())
                ? ""
                : word.trim();
        System.out.print(word + " ? ");
        Scanner scanner = new Scanner(System.in);
        String resposta = null;
        try {
            resposta = scanner.nextLine();
        } catch (Exception ex) {
            resposta = "";
        }
        resposta = (resposta == null) ? "" : resposta.trim();
        return (resposta.isBlank() || resposta.isEmpty())
                ? word.trim()
                : resposta;
    }

    public static String same(String word) {
        // System.out.println("\n0: Utils.same(String)\tword: " + word);
        if (word == null) {
            return "";
        }
        // System.out.println("\n1: Utils.same(String)\tword: " + word);
        word = (word.isBlank() || word.isEmpty())
                ? ""
                : word.trim();
        // System.out.println("\n2: Utils.same(String)\tword: " + word);
        System.out.print(word + " ? ");
        // Scanner scanner = new Scanner(System.in);
        String resposta = null;
        try {
            resposta = next();
            // System.out.println("\n3: Utils.same(String)\tword: " + word);
        } catch (Exception ex) {
            resposta = "";
            // System.out.println("\n4.3: Utils.same(String)\tword: " + word);
        }
        // System.out.println("\n5: Utils.same(String)");
        resposta = (resposta == null) ? "" : resposta;
        // System.out.println("\n6: Utils.same(String)");
        return (resposta.isBlank() || resposta.isEmpty())
                ? word
                : resposta;
    }

    public static String next() {
        Scanner scanner = new Scanner(System.in);
        String resposta = null;
        try {
            resposta = scanner.nextLine();
        } catch (Exception ex) {
            return "";
        }
        if (resposta == null)
            return "";
        resposta = resposta.trim();
        return (resposta.isBlank() || resposta.isEmpty())
                ? ""
                : resposta.split(" ")[0];
    }

    public static void exit(String msg) {
        System.err.println(msg);
        System.exit(0);
    }

    public static void exit(String msg, int codigo) {
        System.err.println(msg);
        System.exit(codigo);
    }

    public static int compare(Integer n1, Integer n2) {
        return n1 > n2 ? 1 : (Objects.equals(n1, n2) ? 0 : -1);
    }

    public static double converterDoubleDoisDecimais(double precoDouble) {
        DecimalFormat fmt = new DecimalFormat("0.00");
        String string = fmt.format(precoDouble);
        return Double.parseDouble(string);
    }

}
