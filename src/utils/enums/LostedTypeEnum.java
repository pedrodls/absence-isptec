package utils.enums;

import lib.isptec.listas.Listas;

public enum LostedTypeEnum {

    EXAME("Casa"), PP_1("Emprego"), PP_2("Telemovel");

    private final String designacao; 

    private LostedTypeEnum(String designacao)
    {
        this.designacao = designacao;
    }

    public int toInteger()
    {
        return this.ordinal() + 1;
    }

    public static LostedTypeEnum fromInteger(int cod)
    {
        for (LostedTypeEnum estado : LostedTypeEnum.values())
        {
            if (estado.toInteger() == cod)
            {
                return estado;
            }
        }
        return null;
    }

    public static LostedTypeEnum fromDesignacao(String nome)
    {
        for (LostedTypeEnum estado : LostedTypeEnum.values())
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
        LostedTypeEnum tipos[] = LostedTypeEnum.values();
        int length = tipos.length;
        String designacoes[] = new String[length];
        
        for (int i = 0; i < length; i++)
        {
            designacoes[i] = tipos[i].toString();
        }
        return designacoes;
    }
    
    public static LostedTypeEnum enviarLerOpcaoEscolhida()
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
