package backendjdbc.dao;

import java.util.List;

public interface ICrud <T>{
    List<T> listar();
    T porId(long id);
    void guardar(T t);
    void eliminar(long id);
}
