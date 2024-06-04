package persistence.model;

import java.io.*;
import java.util.*;

import entities.justification.JustificationUI;
import entities.lostedTest.LostedTest;

public class JustificationModel {

    private static final String FILE_NAME = "./src/persistence/data/justifications.txt";

    public static void create(JustificationUI jt) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {

            String finalStr = "";

            finalStr += jt.getStudent().getId() + "," + jt.getStudent().getName() + "," +
                    jt.getStudent().getCourse() + "," + jt.getStudent().getTelephone() + "," +
                    jt.getStudent().getEmail() + ";";

            finalStr += jt.getCreatedAt()+","+jt.getAbsenceStart() + "," + jt.getAbsenceEnd() + "," + jt.getDescription() + ";";

            finalStr += jt.getLostedTestType() + ";";

            if (jt.getLostedTests().size() > 0) {

                for (LostedTest lt : jt.getLostedTests()) {
                    finalStr += lt.getTeacher()+","+lt.getSubject()+","+lt.getDate()+"_";
                }
                
            }

            writer.write(finalStr);

            writer.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<JustificationUI> read() {
        List<JustificationUI> justifications = new ArrayList<JustificationUI>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                justifications.add(JustificationUI.fromString(linha));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return justifications;
    }

    /*
     * public void update(int id, Contato novoContato) {
     * List<Contato> contatos = read();
     * try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
     * for (Contato contato : contatos) {
     * if (contato.getId() == id) {
     * writer.write(novoContato.toString());
     * } else {
     * writer.write(contato.toString());
     * }
     * writer.newLine();
     * }
     * } catch (IOException e) {
     * e.printStackTrace();
     * }
     * }
     * 
     * public void delete(int id) {
     * List<Contato> contatos = read();
     * try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
     * for (Contato contato : contatos) {
     * if (contato.getId() != id) {
     * writer.write(contato.toString());
     * writer.newLine();
     * }
     * }
     * } catch (IOException e) {
     * e.printStackTrace();
     * }
     * }
     */
}
