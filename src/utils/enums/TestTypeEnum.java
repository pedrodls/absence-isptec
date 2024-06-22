package utils.enums;

import isptec.listas.Listas;

public enum TestTypeEnum {

    EXAME("Exame"), PP_1("PP_1"), PP_2("PP_2"), NENHUMA("Nenhuma");

    private final String designacao; 

    private TestTypeEnum(String designacao)
    {
        this.designacao = designacao;
    }

    public int toInteger()
    {
        return this.ordinal() + 1;
    }

    public static TestTypeEnum fromInteger(int cod)
    {
        for (TestTypeEnum estado : TestTypeEnum.values())
        {
            if (estado.toInteger() == cod)
            {
                return estado;
            }
        }
        return null;
    }

    public static TestTypeEnum fromDesignacao(String nome)
    {
        for (TestTypeEnum estado : TestTypeEnum.values())
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
        TestTypeEnum tipos[] = TestTypeEnum.values();
        int length = tipos.length;
        String designacoes[] = new String[length];
        
        for (int i = 0; i < length; i++)
        {
            designacoes[i] = tipos[i].toString();
        }
        return designacoes;
    }
    
    public static TestTypeEnum enviarLerOpcaoEscolhida()
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
