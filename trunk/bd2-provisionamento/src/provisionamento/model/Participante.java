package provisionamento.model;

import DaoMongoDB.ModeloBase;

public class Participante extends ModeloBase {
    private Usuario usuario;
    private boolean pago;

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    /*@Override
    public boolean equals(Object o){
        Participante participante;
        if(o instanceof Participante){
            participante = (Participante) o;
            if(this.getUsuario().getId() == participante.getUsuario().getId())
                return true;
        }
        return false;
    }*/
    
    @Override
    public String toString(){
        return this.getUsuario().toString();
    }
}
