package serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;

import serialization.number.Number;

public class Serializer {

    public void serialize(ObjectOutputStream oos, Object object) throws Exception {
        Class<?> cls = object.getClass();
        Field[] fields = cls.getDeclaredFields();
        try (oos) {
            for (Field field : fields) {
                Object o = field.get(object);
                if (o instanceof Serializable) {
                    oos.writeObject(o);
                    oos.writeUTF(";");
                } else {
                    serialize(oos, o);
                }
            }
        }
    }

    public <T> T deserialize(ObjectInputStream ois, Class<T> cls) throws Exception {
        Object result = cls.newInstance();
        try (ois) {
            Field[] fields = cls.getDeclaredFields();
            for (Field field : fields) {
                Object value = field.getType().newInstance();
                if (value instanceof Serializable) {
                    Object o = ois.readObject();
                    ois.readUTF();
                    field.set(result, o);
                } else {
                    Object des = deserialize(ois, value.getClass());
                    field.set(result, des);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return (T) result;
    }

    public static void main(String[] args) throws Exception {
//        new Serializer().serialize(
//                new ObjectOutputStream(new FileOutputStream("data.txt")),
//                new Person(
//                        new Name("Ivan"),
//                        new Phone(new Number(13), new Model("IPhone"))
//                )
//        );
        Person p = new Serializer().deserialize(
                new ObjectInputStream(new FileInputStream("data.txt")),
                Person.class
        );
        System.out.println(p);
    }
}
