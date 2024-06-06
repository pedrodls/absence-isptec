package utils.enums;

import isptec.listas.Listas;

public enum TipoDespachoEnum {

    DEFERIDO("Deferido"), INDEFERIDO("Indeferido"), FALTA_COMPROVATIVO("Falta de comprovativo");

    private final String designacao; 

    private TipoDespachoEnum(String designacao)
    {
        this.designacao = designacao;
    }

    public int toInteger()
    {
        return this.ordinal() + 1;
    }

    public static TipoDespachoEnum fromInteger(int cod)
    {
        for (TipoDespachoEnum estado : TipoDespachoEnum.values())
        {
            if (estado.toInteger() == cod)
            {
                return estado;
            }
        }
        return null;
    }

    public static TipoDespachoEnum fromDesignacao(String nome)
    {
        for (TipoDespachoEnum estado : TipoDespachoEnum.values())
        {
            if (estado.designacao.equals(nome))
            {
                return estado;
            }
        }
        return null;
    }

    public static String[] getDesignacoes()
    {
        TipoDespachoEnum tipos[] = TipoDespachoEnum.values();
        int length = tipos.length;
        String designacoes[] = new String[length];
        
        for (int i = 0; i < length; i++)
        {
            designacoes[i] = tipos[i].toString();
        }
        return designacoes;
    }
    
    public static TipoDespachoEnum enviarLerOpcaoEscolhida()
    {
        System.out.println("Selecione um dos tipos de despacho: ");
        int opcao = Listas.enviarLerOpcaoEscolhida(getDesignacoes());
        return fromInteger(opcao);
    }
    
    // Getters

    public String getDesignacao()
    {
        return designacao;
    }
}
