package MyExceptions;

public class DaoException extends Exception {

    public DaoException(String mensagem, Exception ex) {
        super(mensagem, ex);
    }

    public DaoException(Exception ex) {
        super(ex);
    }
    
    public DaoException(String mensage) {
        super(mensage);
    }
}
