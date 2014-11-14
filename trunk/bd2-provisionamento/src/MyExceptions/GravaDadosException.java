package MyExceptions;

public class GravaDadosException extends DaoException {

    public GravaDadosException(String mensagem, Exception ex) {
        super(mensagem, ex);
    }

    public GravaDadosException(Exception ex) {
        super(ex);
    }
    public GravaDadosException(String mensage) {
        super(mensage);
    }
}
