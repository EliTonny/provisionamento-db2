package DaoArquivo;

import MyExceptions.CarregaDadosException;
import MyExceptions.DaoException;
import MyExceptions.GravaDadosException;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;

public class Dados<T extends ModeloBase> implements Serializable{
    
    private HashMap<Integer, T> dados;
    private int proximoId;

    public int getProximoId() {
        return proximoId;
    }

    public void setProximoId(int proximoId) {
        this.proximoId = proximoId;
    }
    
    public Dados()
    {
        dados = new HashMap<>();
    }
    
    public T insere(T object)
    {
        return this.dados.put(object.getId(), object);
    }
    
    public T remove(T object)
    {
        return this.dados.remove(object.getId());
    }
    
    public T busca(int id)
    {
        return this.dados.get(id);
    }
    
    public Collection<T> buscaTodos()
    {
        return this.dados.values();
    }
    
    public void Persiste(File arquivo) throws DaoException {
        try {
            try (FileOutputStream fileOutput = new FileOutputStream(arquivo)) {
                BufferedOutputStream bufferedOutput = new BufferedOutputStream(fileOutput);
                ObjectOutputStream objectOutput = new ObjectOutputStream(bufferedOutput);
                objectOutput.writeObject(this);
                objectOutput.flush();
            }
        } catch (Exception ex) {
            throw new GravaDadosException(ex);
        }
    }
    
    public void Carrega(File arquivo) throws CarregaDadosException {
        try {
            if (arquivo.exists()) {
                try (FileInputStream fileInputStream = new FileInputStream(arquivo)) {
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream); //abre
                    ObjectInputStream objetos = new ObjectInputStream(bufferedInputStream);
                    Dados d = (Dados<T>) objetos.readObject();
                    this.dados = d.dados;
                    this.proximoId = d.proximoId;
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            throw new CarregaDadosException(ex);
        }
    }

}
