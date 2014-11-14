package Sistema;

import provisionamento.model.Usuario;

public class Session {

    private Usuario usuario;
    private static Session instancia;

    private Session() {
    }

    public static Session getInstancia() {
        if (instancia == null) {
            instancia = new Session();
        }
        return instancia;
    }

    public Usuario getUsuarioLogado() {
        return usuario;
    }

    public void setUsuarioLogado(Usuario usuLog) {
        usuario = usuLog;
    }
}
