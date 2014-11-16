package provisionamento.model;

import DaoMongoDB.ModeloBase;

public class Categoria extends ModeloBase {
    
    private String descricao;
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    @Override 
    public String toString() {
        return this.getDescricao();
    }
}
