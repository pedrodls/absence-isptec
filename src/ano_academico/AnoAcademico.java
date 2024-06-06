package ano_academico;

import java.io.IOException;
import java.io.RandomAccessFile;
import utils.CommonEntity;


public class AnoAcademico extends CommonEntity {

    public AnoAcademico() {

    }

    public AnoAcademico(int id, String descricao, Boolean ativo) {
        this.setId(id);
        this.setAtivo(ativo);
        this.setDescricaco(descricao);

    }

    public void write(RandomAccessFile raf) throws IOException {

        raf.writeInt(this.getId());
        raf.writeUTF(this.getDescricaco());
        raf.writeBoolean(this.getAtivo());

    }

    public static AnoAcademico read(RandomAccessFile raf) throws IOException {

        int id = raf.readInt();
        String descricacao = raf.readUTF();
        Boolean ativo = raf.readBoolean();

        return new AnoAcademico(id, descricacao, ativo);
    }

    @Override
    public String toString() {
        return "ID: " + this.getId() + ", Descrição: " + this.getDescricaco();
    }

}
