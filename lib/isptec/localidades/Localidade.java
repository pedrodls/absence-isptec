package lib.isptec.localidades;





/**
 *
 * @author Aires Veloso
 */

public class Localidade //extends ObjectSerializableGeneric
{
    private int codigo, localidadePai;
    private String nome;

    public Localidade()
    {
        this.codigo = 0;
        this.nome = "";
        this.localidadePai = 0;
    }
    public Localidade(int codigo, String nome, int localidadePai)
    {
        this.codigo = codigo;
        this.nome = nome;
        this.localidadePai = localidadePai;
    }
    
    public String toStringPaiFilho()
    {
        String pai = Localidades.getLocalidade(this.localidadePai).getNome();
        return pai + ", " + nome;
    }

    @Override
    public String toString()
    {
        return "{" + codigo + ", " + nome + ", " + localidadePai + '}';
    }

    // Business Methods


    // Getters and Setters

    public int getCodigo()
    {
        return codigo;
    }

    public void setCodigo(int codigo)
    {
        this.codigo = codigo;
    }

    public int getLocalidadePai()
    {
        return localidadePai;
    }

    public void setLocalidadePai(int localidadePai)
    {
        this.localidadePai = localidadePai;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

}
