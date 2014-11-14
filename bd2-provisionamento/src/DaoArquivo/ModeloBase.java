package DaoArquivo;

import java.io.Serializable;

public abstract class ModeloBase implements Serializable {

    protected int id;

    public ModeloBase()
    {
        this.id = -1;
    }
    
    public int getId() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        return (((ModeloBase) o).getId() == this.getId());
    }
}
