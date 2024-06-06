package justificacao;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;

import utils.LongToDate;
import utils.enums.TipoDespachoEnum;
import utils.enums.TipoProvaEnum;

public class Justificacao {

    private int id;
    private int idEstudante;
    private int idMotivoFalta;
    private int idCoordenacao;
    private int idAnoAcademico;

    private Date dataDespacho;
    private Date dataCriacao;
    private Date periodoInicialFalta;
    private Date periodoFinalFalta;

    private String assunto;
    private TipoDespachoEnum tipoDespacho;

    private boolean provaPerdida;
    private TipoProvaEnum tipoProva;

    public Justificacao() {
        
    }

    public Justificacao(int id, int idEstudante, int idMotivoFalta, int idCoordenacao,
            int idAnoAcademico, Date dataDespacho, Date dataCriacao,
            Date periodoInicialFalta, Date periodoFinalFalta,String assunto, TipoDespachoEnum tipoDespacho, boolean provaPerdida, TipoProvaEnum tipoProva) {

        this.id = id;
        this.idEstudante = idEstudante;
        this.idMotivoFalta = idMotivoFalta;
        this.idCoordenacao = idCoordenacao;
        this.idAnoAcademico = idAnoAcademico;
        this.dataDespacho = dataDespacho;
        this.dataCriacao = dataCriacao;
        this.periodoInicialFalta = periodoInicialFalta;
        this.periodoFinalFalta = periodoFinalFalta;
        this.assunto = assunto;
        this.tipoDespacho = tipoDespacho;
        this.provaPerdida = provaPerdida;
        this.tipoProva = tipoProva;
    }

    public Justificacao(Justificacao justificacao) {
        this.id = justificacao.id;
        this.idEstudante = justificacao.idEstudante;
        this.idMotivoFalta = justificacao.idMotivoFalta;
        this.idCoordenacao = justificacao.idCoordenacao;
        this.idAnoAcademico = justificacao.idAnoAcademico;
        this.dataDespacho = justificacao.dataDespacho;
        this.dataCriacao = justificacao.dataCriacao;
        this.periodoInicialFalta = justificacao.periodoInicialFalta;
        this.periodoFinalFalta = justificacao.periodoFinalFalta;
        this.assunto = justificacao.assunto;
        this.tipoDespacho = justificacao.tipoDespacho;
        this.provaPerdida = justificacao.provaPerdida;
        this.tipoProva = justificacao.tipoProva;
    }

    public void write(RandomAccessFile raf) throws IOException {

        raf.writeInt(this.id);
        raf.writeInt(this.idEstudante);
        raf.writeInt(this.idMotivoFalta);
        raf.writeInt(this.idCoordenacao);
        raf.writeInt(this.idAnoAcademico);
        raf.writeLong(this.dataDespacho.getTime());
        raf.writeLong(this.dataCriacao.getTime());
        raf.writeLong(this.periodoInicialFalta.getTime());
        raf.writeLong(this.periodoFinalFalta.getTime());
        raf.writeUTF(this.assunto);
        raf.writeInt(this.tipoDespacho.toInteger());
        raf.writeBoolean(this.provaPerdida);
        raf.writeInt(this.tipoProva.toInteger());

    }

    public static Justificacao read(RandomAccessFile raf) throws IOException {

        int id = raf.readInt();
        int idEstudante = raf.readInt();
        int idMotivoFalta = raf.readInt();
        int idCoordenacao = raf.readInt();
        int idAnoAcademico = raf.readInt();
        Date dataDespacho = LongToDate.convert(raf.readLong());
        Date dataCriacao = LongToDate.convert(raf.readLong());
        Date periodoInicialFalta = LongToDate.convert(raf.readLong());
        Date periodoFinalFalta = LongToDate.convert(raf.readLong());
        String assunto = raf.readUTF();
        TipoDespachoEnum tipoDespacho = TipoDespachoEnum.fromInteger(raf.readInt());
        Boolean provaPerdida = raf.readBoolean();
        TipoProvaEnum tipoProva = TipoProvaEnum.fromInteger(raf.readInt());

 
        return new Justificacao(id, idEstudante, idMotivoFalta, idCoordenacao, idAnoAcademico, dataDespacho, dataCriacao, periodoInicialFalta, periodoFinalFalta,assunto, tipoDespacho, provaPerdida, tipoProva);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEstudante() {
        return idEstudante;
    }

    public void setIdEstudante(int idEstudante) {
        this.idEstudante = idEstudante;
    }

    public int getIdMotivoFalta() {
        return idMotivoFalta;
    }

    public void setIdMotivoFalta(int idMotivoFalta) {
        this.idMotivoFalta = idMotivoFalta;
    }

    public int getIdCoordenacao() {
        return idCoordenacao;
    }

    public void setIdCoordenacao(int idCoordenacao) {
        this.idCoordenacao = idCoordenacao;
    }

    public int getIdAnoAcademico() {
        return idAnoAcademico;
    }

    public void setIdAnoAcademico(int idAnoAcademico) {
        this.idAnoAcademico = idAnoAcademico;
    }

    public Date getDataDespacho() {
        return dataDespacho;
    }

    public void setDataDespacho(Date dataDespacho) {
        this.dataDespacho = dataDespacho;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getPeriodoInicialFalta() {
        return periodoInicialFalta;
    }

    public void setPeriodoInicialFalta(Date periodoInicialFalta) {
        this.periodoInicialFalta = periodoInicialFalta;
    }

    public Date getPeriodoFinalFalta() {
        return periodoFinalFalta;
    }

    public void setPeriodoFinalFalta(Date periodoFinalFalta) {
        this.periodoFinalFalta = periodoFinalFalta;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public TipoDespachoEnum getTipoDespacho() {
        return tipoDespacho;
    }

    public void setTipoDespacho(TipoDespachoEnum tipoDespacho) {
        this.tipoDespacho = tipoDespacho;
    }

    public boolean isProvaPerdida() {
        return provaPerdida;
    }

    public void setProvaPerdida(boolean provaPerdida) {
        this.provaPerdida = provaPerdida;
    }

    public TipoProvaEnum getTipoProva() {
        return tipoProva;
    }

    public void setTipoProva(TipoProvaEnum tipoProva) {
        this.tipoProva = tipoProva;
    }

}
