package backendjdbc.dao;

import backendjdbc.util.ConexionBD;
import java.sql.*;
import java.util.List;

public abstract class CrudGenerico <T> implements ICrud<T>{
    protected ConexionBD pool;

    public CrudGenerico() {
    }
    //La clase abstract madre hara una sola conexion con la BD para todo el CRUD
    //ConexionBD es la clase
    public CrudGenerico(ConexionBD pool) {
        this.pool = pool;
    }

    //Conexion Generica a base de datos usando pool de conexiones
    //Si no fuese generica Connection fuese un metodo dentro de la clase ConexionBD
    protected Connection getConnection() throws SQLException{
        return pool.getConnection();
    }

    protected void cerrarRecursos(Connection conn, PreparedStatement ps, ResultSet rs){
        try{
            if(rs != null) rs.close();
            if(ps != null) ps.close();
            if(conn != null) conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    //ABSTRACCION DEL CRUD
    public abstract List<T> listar();
    public abstract T porId(int id);
    public abstract void guardar(T t);
    public abstract void eliminar(int id);

}
