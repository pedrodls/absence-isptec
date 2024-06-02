package utils.enums;

import lib.isptec.listas.Listas;

public enum GenderTypeEnum {

    FEMININO("Casa"), MASCULINO("Emprego");

    private final String designacao; 

    private GenderTypeEnum(String designacao)
    {
        this.designacao = designacao;
    }

    public int toInteger()
    {
        return this.ordinal() + 1;
    }

    public static GenderTypeEnum fromInteger(int cod)
    {
        for (GenderTypeEnum estado : GenderTypeEnum.values())
        {
            if (estado.toInteger() == cod)
            {
                return estado;
            }
        }
        return null;
    }

    public static GenderTypeEnum fromDesignacao(String nome)
    {
        for (GenderTypeEnum estado : GenderTypeEnum.values())
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
        GenderTypeEnum tipos[] = GenderTypeEnum.values();
        int length = tipos.length;
        String designacoes[] = new String[length];
        
        for (int i = 0; i < length; i++)
        {
            designacoes[i] = tipos[i].toString();
        }
        return designacoes;
    }
    
    public static GenderTypeEnum enviarLerOpcaoEscolhida()
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
