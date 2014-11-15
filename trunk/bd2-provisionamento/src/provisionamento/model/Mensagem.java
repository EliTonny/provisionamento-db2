package provisionamento.model;

public class Mensagem{
    private String mensagem;
    private GrupoUnitario grupo;
    
    public Mensagem(String mensagem, GrupoUnitario grupo){
        this.mensagem = mensagem;
        this.grupo = grupo;
    }
    
    public String getMensagem(){
        return this.mensagem;
    }

    public GrupoUnitario getGrupo() {
        return grupo;
    }
       
    @Override
    public String toString(){
        return this.mensagem;
    }
    
    @Override
    public boolean equals(Object obj){
        Mensagem msg; 
        if(obj instanceof Mensagem){
            msg = (Mensagem) obj;
            if(this.getMensagem().equals(msg.getMensagem()))
                return true;
        }
        return false;
    }
}
