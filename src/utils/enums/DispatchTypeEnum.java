package utils.enums;

import isptec.listas.Listas;

public enum DispatchTypeEnum {

    DEFERIDO("Deferido"), 
    INDEFERIDO("Indeferido"), 
    AGUARDANDO("Em Espera"),
    FALTA_COMPROVATIVO("Falta de comprovativo");

    private final String designacao;

    private DispatchTypeEnum(String designacao) {
        this.designacao = designacao;
    }

    public int toInteger() {
        return this.ordinal() + 1;
    }

    public static DispatchTypeEnum fromInteger(int cod) {
        for (DispatchTypeEnum estado : DispatchTypeEnum.values()) {
            if (estado.toInteger() == cod) {
                return estado;
            }
        }
        return null;
    }

    public static DispatchTypeEnum fromDesignacao(String nome) {
        for (DispatchTypeEnum estado : DispatchTypeEnum.values()) {
            if (estado.designacao.equals(nome)) {
                return estado;
            }
        }
        return null;
    }

    public static String[] getDesignacoes() {
        DispatchTypeEnum tipos[] = DispatchTypeEnum.values();
        int length = tipos.length;
        String designacoes[] = new String[length];

        for (int i = 0; i < length; i++) {
            designacoes[i] = tipos[i].toString();
        }
        return designacoes;
    }

    public static DispatchTypeEnum enviarLerOpcaoEscolhida() {
        System.out.println("Selecione um dos tipos de despacho: ");
        int opcao = Listas.enviarLerOpcaoEscolhida(getDesignacoes());
        return fromInteger(opcao);
    }

    // Getters

    public String getDesignacao() {
        return designacao;
    }
}
