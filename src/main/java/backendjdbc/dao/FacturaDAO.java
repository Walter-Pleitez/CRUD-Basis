package backendjdbc.dao;

import backendjdbc.util.ConexionBD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class FacturaDAO implements ICrud{

    private Connection getConnection() throws SQLException {
        return ConexionBD.getConnection();
    }

    @Override
    public List listar() {
        return List.of();
    }

    @Override
    public Object porId(long id) {
        return null;
    }

    @Override
    public void guardar(Object o) {

    }

    @Override
    public void eliminar(long id) {

    }

}
