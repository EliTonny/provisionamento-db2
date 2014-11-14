package MyExceptions;

public class DeletaDadosException extends DaoException {

    public DeletaDadosException(String mensagem, Exception ex) {
        super(mensagem, ex);
    }

    public DeletaDadosException(Exception ex) {
        super(ex);
    }
    public DeletaDadosException(String mensage) {
        super(mensage);
    }
}
