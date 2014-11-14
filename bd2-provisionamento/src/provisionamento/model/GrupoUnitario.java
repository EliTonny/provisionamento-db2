package provisionamento.model;

import DaoArquivo.ModeloBase;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GrupoUnitario  extends ModeloBase{
    private Categoria categoria;
    private String descricao;
    private Date prazoValidade;
    private int quantidade;
    private int QrdDiasNotificacao;
    private Usuario criador;
    private Date dataCriacao;
    private boolean finalizado;

    private double valorCompra;

    public double getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(double valorCompra) {
        this.valorCompra = valorCompra;
    }

     public GrupoUnitario() {
        this.id = -1;
        this.dataCriacao = new Date();
    }

    public Usuario getCriador() {
        return criador;
    }   

    public void setCriador(Usuario criador) {
        this.criador = criador;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getPrazoValidade() {
        return prazoValidade;
    }

    public void setPrazoValidade(Date prazoValidade) {
        this.prazoValidade = prazoValidade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getQrdDiasNotificacao() {
        return QrdDiasNotificacao;
    }

    public void setQrdDiasNotificacao(int QrdDiasNotificacao) {
        this.QrdDiasNotificacao = QrdDiasNotificacao;
    }
    
    @Override
    public String toString()
    {
        SimpleDateFormat sdf1= new SimpleDateFormat("dd/MM/yyyy");
        Date y = this.dataCriacao;
        return this.getCategoria().getDescricao() + " " + sdf1.format(y);

    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

}
