package backendjdbc.dao;

import backendjdbc.model.concretClasses.Producto;
import backendjdbc.util.ConexionBD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProductoDAO implements ICrud<Producto> {

    private Connection getConnection() throws SQLException {
        return ConexionBD.getConnection();
    }

    @Override
    public List<Producto> listar() {
        return List.of();
    }

    @Override
    public Producto porId(long id) {
        return null;
    }

    @Override
    public void guardar(Producto producto) {

    }

    @Override
    public void eliminar(long id) {

    }
}
