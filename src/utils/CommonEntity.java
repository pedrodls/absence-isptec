package utils;

public class CommonEntity {
    private int id;
    private String descricaco;
    private Boolean ativo;

    
    
    public CommonEntity(){
        
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricaco() {
        return descricaco;
    }

    public void setDescricaco(String descricaco) {
        this.descricaco = descricaco;
    }

}
