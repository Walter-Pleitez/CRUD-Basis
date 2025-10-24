package backendjdbc.dao;

import backendjdbc.model.Cliente;
import backendjdbc.model.Empleado;
import backendjdbc.model.Factura;
import backendjdbc.util.ConexionBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FacturaDAO extends CrudGenerico<Factura>{
    public FacturaDAO() {
    }

    public FacturaDAO(ConexionBD pool) {
        super(pool);
    }

    @Override
    public List listar() {
        List<Factura> facturas = new ArrayList<>();

        try(
                //stmt: Este abre la conexion a la BD
                Statement stmt = getConnection().createStatement();
                // rs: este toma la conexion abierta por stmt, por lo que stmt manda query a la bd
                ResultSet rs = stmt.executeQuery("SELECT * FROM factura")
        ){
            while(rs.next()){
                //caracteristica de Intelli J que permite crear un metodo para reusar el mismo codigo.
                //Ver crear producto al final de los metodos
                Factura fact = crearFactura(rs);
                //productos: recibe los datos recolectados por p
                facturas.add(fact);
            }
        }
        catch(SQLException throwables){
            throwables.printStackTrace();
        }

        return facturas;
    }

    @Override
    public Factura porId(int id) {
        //PreparedStatement es para sentencias preparadas como WHERE
        Factura fact = null;

        try(
                PreparedStatement pstmt = getConnection().
                        prepareStatement("SELECT * FROM productos WHERE id = ?");
        ){
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                //producto esta reutilizando codigo
                //Ver "Caracteriticas para reutilization de codigo de IntelliJ Idea" al final
                fact = crearFactura(rs);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }

        return fact;
    }

    @Override
    public void guardar(Factura fact) {
        String sql;
        Integer idReturned = fact.getIdFactura();

        if(idReturned != null && idReturned>0){
            sql = "UPDATE producto SET fecha=?, total=?, cliente=?, empleado=?" +
                    "WHERE id=?";
        }else{
            sql = "INSERT INTO producto(fecha, total, cliente, empleado)" +
                    "VALUES (?,?,?,?)";
        }

        try(
                PreparedStatement pstmt = getConnection().prepareStatement(sql)
        )
        {
            pstmt.setDate(1, fact.getFecha());
            pstmt.setDouble(2, fact.getTotal());
            pstmt.setInt(3, fact.getClient().getClienteId());
            pstmt.setInt(4, fact.getEmployee().getEmpleadoId());


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
    private static Factura crearFactura(ResultSet rs) throws SQLException {
        //p: es una instancia que recolecta los datos desde sql
        Factura fact = new Factura();

        //GetType es la logica, no se hace get del atributo en especifico sino del tipo
        fact.setFecha(rs.getDate("fecha"));
        fact.setTotal(rs.getDouble("total"));

        //Desde SQL solo se solicita el tipo del id no un objeto completo
        //por eso se declara un objeto y se manda solo el id del objeto por el ResultSet
        Cliente c = new Cliente();
        c.setClienteId(rs.getInt("id_cliente"));
        Empleado e = new Empleado();
        e.setEmpleadoId(rs.getInt("id_empleado"));

        return fact;
    }

}
