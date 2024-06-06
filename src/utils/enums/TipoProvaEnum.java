package utils.enums;

import isptec.listas.Listas;

public enum TipoProvaEnum {

    EXAME("Exame"), PP_1("PP_1"), PP_2("PP_2");

    private final String designacao; 

    private TipoProvaEnum(String designacao)
    {
        this.designacao = designacao;
    }

    public int toInteger()
    {
        return this.ordinal() + 1;
    }

    public static TipoProvaEnum fromInteger(int cod)
    {
        for (TipoProvaEnum estado : TipoProvaEnum.values())
        {
            if (estado.toInteger() == cod)
            {
                return estado;
            }
        }
        return null;
    }

    public static TipoProvaEnum fromDesignacao(String nome)
    {
        for (TipoProvaEnum estado : TipoProvaEnum.values())
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
        TipoProvaEnum tipos[] = TipoProvaEnum.values();
        int length = tipos.length;
        String designacoes[] = new String[length];
        
        for (int i = 0; i < length; i++)
        {
            designacoes[i] = tipos[i].toString();
        }
        return designacoes;
    }
    
    public static TipoProvaEnum enviarLerOpcaoEscolhida()
    {
        System.out.println("Selecione um dos tipos de provas perdidas: ");
        int opcao = Listas.enviarLerOpcaoEscolhida(getDesignacoes());
        return fromInteger(opcao);
    }
    
    // Getters

    public String getDesignacao()
    {
        return designacao;
    }
}
