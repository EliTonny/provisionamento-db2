package MyExceptions;

public class CarregaDadosException extends DaoException {

    public CarregaDadosException(String mensagem, Exception ex) {
        super(mensagem, ex);
    }

    public CarregaDadosException(Exception ex) {
        super(ex);
    }
}
