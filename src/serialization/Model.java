package serialization;

import java.io.Serializable;

public class Model implements Serializable {
    public String value;

    public Model() {
    }

    public Model(String value) {
        this.value = value;
    }
}
