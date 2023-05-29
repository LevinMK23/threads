package serialization;

import java.io.Serializable;

public class Name implements Serializable {

    public String value;

    public Name() {
    }

    public Name(String value) {
        this.value = value;
    }
}
