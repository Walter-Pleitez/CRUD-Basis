package backendjdbc.dao;

import backendjdbc.model.Cliente;
import backendjdbc.util.ConexionBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO extends CrudGenerico<Cliente>{
    public ClientDAO() {
    }

    public ClientDAO(ConexionBD pool) {
        super(pool);
    }

    @Override
    public List listar() {
        List<Cliente> clientes = new ArrayList<>();

        try(
                //stmt: Este abre la conexion a la BD
                Statement stmt = getConnection().createStatement();
                // rs: este toma la conexion abierta por stmt, por lo que stmt manda query a la bd
                ResultSet rs = stmt.executeQuery("SELECT * FROM clientes")
        ){
            while(rs.next()){
                //caracteristica de Intelli J que permite crear un metodo para reusar el mismo codigo.
                //Ver crear producto al final de los metodos
                Cliente client = crearCliente(rs);
                //productos: recibe los datos recolectados por p
                clientes.add(client);
            }
        }
        catch(SQLException throwables){
            throwables.printStackTrace();
        }

        return clientes;
    }

    @Override
    public Cliente porId(int id) {
        //PreparedStatement es para sentencias preparadas como WHERE
        Cliente cliente = null;

        try(
                PreparedStatement pstmt = getConnection().
                        prepareStatement("SELECT * FROM productos WHERE id = ?");
        ){
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                //producto esta reutilizando codigo
                //Ver "Caracteriticas para reutilization de codigo de IntelliJ Idea" al final
                cliente = crearCliente(rs);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }

        return cliente;
    }

    @Override
    public void guardar(Cliente cliente) {
        String sql;
        Integer idReturned = cliente.getClienteId();

        if(idReturned != null && idReturned>0){
            sql = "UPDATE producto SET nombre=?, apellido=?, telefono=?, correo=?" +
                    "WHERE id=?";
        }else{
            sql = "INSERT INTO producto(nombre, apellido, telefono, correo)" +
                    "VALUES (?,?,?,?)";
        }

        try(
                PreparedStatement pstmt = getConnection().prepareStatement(sql)
        )
        {
            pstmt.setString(1, cliente.getNombre());
            pstmt.setString(2, cliente.getApellido());
            pstmt.setInt(3, cliente.getTelefono());
            pstmt.setString(4, cliente.getCorreo());
            pstmt.setString(5, cliente.getTipo());

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
    private static Cliente crearCliente(ResultSet rs) throws SQLException {
        //p: es una instancia que recolecta los datos desde sql
        Cliente cli = new Cliente();

        //GetType es la logica, no se hace get del atributo en especifico sino del tipo
        cli.setClienteId(rs.getInt("id_cliente"));
        cli.setNombre(rs.getString("nombre"));
        cli.setApellido(rs.getString("apellido"));
        cli.setTelefono(rs.getInt("telefono"));
        cli.setCorreo(rs.getString("correo"));
        cli.setTipo(rs.getString("tipo"));

        return cli;
    }

}
