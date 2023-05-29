package serialization.number;

import java.io.Serializable;

public class Number implements Serializable {

    public int value;

    public Number() {
    }

    public Number(int value) {
        this.value = value;
    }
}
