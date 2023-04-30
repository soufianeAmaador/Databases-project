package nl.hva.ict.data;

import java.util.List;

public interface Data<T> {

    List<T> getAll();
    T get(String id);
    void add(T object);
    void update (T object);
    void remove(T object);
}
