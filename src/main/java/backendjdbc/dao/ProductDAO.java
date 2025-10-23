package backendjdbc.dao;

import backendjdbc.model.Producto;
import backendjdbc.util.ConexionBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO extends CrudGenerico<Producto>{

    public ProductDAO() {
    }
    public ProductDAO(ConexionBD pool) {
        super(pool);
    }

    @Override
    public List<Producto> listar() {
        List<Producto> productos = new ArrayList<>();

        try(
                //stmt: Este abre la conexion a la BD
                Statement stmt = getConnection().createStatement();
                // rs: este toma la conexion abierta por stmt, por lo que stmt manda query a la bd
                ResultSet rs = stmt.executeQuery("SELECT * FROM productos")
        ){
            while(rs.next()){
                //caracteristica de Intelli J que permite crear un metodo para reusar el mismo codigo.
                //Ver crear producto al final de los metodos
                Producto p = crearProducto(rs);
                //productos: recibe los datos recolectados por p
                productos.add(p);
            }
        }
        catch(SQLException throwables){
            throwables.printStackTrace();
        }

        return productos;
    }

    @Override
    public Producto porId(int id) {
        //PreparedStatement es para sentencias preparadas como WHERE
        Producto producto = null;

        try(
                PreparedStatement pstmt = getConnection().
                        prepareStatement("SELECT * FROM productos WHERE id = ?");
        ){
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                //producto esta reutilizando codigo
                //Ver "Caracteriticas para reutilization de codigo de IntelliJ Idea" al final
                producto = crearProducto(rs);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }

        return producto;
    }

    @Override
    public void guardar(Producto producto) {
        String sql;
        Integer idReturned = producto.getId_producto();

        if(idReturned != null && idReturned>0){
            sql = "UPDATE producto SET nombre=?, categoria=?, color=?, aroma=?, presentacion=?, precio=?, costo=?" +
                    "WHERE id=?";
        }else{
            sql = "INSERT INTO producto(nombre, categoria, color, aroma, presentacion, precio, costo)" +
                    "VALUES (?,?,?,?,?,?,?)";
        }

        try(
                PreparedStatement pstmt = getConnection().prepareStatement(sql)
        )
        {
            pstmt.setString(1, producto.getNombre());
            pstmt.setString(2, producto.getCategoria());
            pstmt.setString(3, producto.getColor());
            pstmt.setString(4, producto.getAroma());
            pstmt.setString(5, producto.getPresentacion());
            pstmt.setDouble(6, producto.getPrecio());
            pstmt.setDouble(7, producto.getCosto());

            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        try(
                PreparedStatement pstmt = getConnection().prepareStatement("DELETE FROM producto" +
                        "WHERE id=?")
        ){
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        }
        catch(SQLException throwables){
            throwables.printStackTrace();
        }
    }

    //CARACTERISTICAS PARA REUTILIZACION DE CODIGO, DE IntelliJ Idea.
    private static Producto crearProducto(ResultSet rs) throws SQLException {
        //p: es una instancia que recolecta los datos desde sql
        Producto p = new Producto();

        //GetType es la logica, no se hace get del atributo en especifico sino del tipo
        p.setId_producto(rs.getInt("id_producto"));
        p.setNombre(rs.getString("nombre"));
        p.setCategoria(rs.getString("categoria"));
        p.setColor(rs.getString("color"));
        p.setAroma(rs.getString("aroma"));
        p.setPresentacion(rs.getString("presentacion"));
        p.setPrecio(rs.getFloat("precio"));
        p.setCosto(rs.getFloat("costo"));
        return p;
    }
}
