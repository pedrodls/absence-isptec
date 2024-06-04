package persistence.model;

import java.io.*;
import java.util.*;

import entities.justification.JustificationUI;

public class JustificationModel {

    private static final String FILE_NAME = "justifications.txt";

    public void create(JustificationUI jt) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(jt.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<JustificationUI> read() {
        List<JustificationUI> justifications = new ArrayList<>();
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

    /* public void update(int id, Contato novoContato) {
        List<Contato> contatos = read();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Contato contato : contatos) {
                if (contato.getId() == id) {
                    writer.write(novoContato.toString());
                } else {
                    writer.write(contato.toString());
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        List<Contato> contatos = read();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Contato contato : contatos) {
                if (contato.getId() != id) {
                    writer.write(contato.toString());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    } */
}
