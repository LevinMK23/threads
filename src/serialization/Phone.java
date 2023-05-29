package serialization;

import serialization.number.Number;

public class Phone {

    public Number number;
    public Model model;

    public Phone() {
    }

    public Phone(Number number, Model model) {
        this.number = number;
        this.model = model;
    }
}
