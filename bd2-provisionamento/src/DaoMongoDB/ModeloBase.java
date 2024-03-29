package DaoMongoDB;

import java.io.Serializable;
import org.bson.types.ObjectId;

public abstract class ModeloBase implements Serializable {

    private ObjectId id;

    public ModeloBase() {
        this.id = null;
    }

    public ObjectId getId() {
        return this.id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ModeloBase) {
            if (this.getId() != null) {
                return this.getId().equals(((ModeloBase) o).getId());
            }
        }
        return false;
    }
}
