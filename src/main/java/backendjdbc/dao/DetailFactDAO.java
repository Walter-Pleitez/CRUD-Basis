package backendjdbc.dao;

import backendjdbc.model.*;
import backendjdbc.util.ConexionBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DetailFactDAO extends CrudGenerico<DetalleFactura>{
    public DetailFactDAO() {
    }

    public DetailFactDAO(ConexionBD pool) {
        super(pool);
    }

    @Override
    public List listar() {
        List<DetalleFactura> detFacts = new ArrayList<>();

        try(
                //stmt: Este abre la conexion a la BD
                Statement stmt = getConnection().createStatement();
                // rs: este toma la conexion abierta por stmt, por lo que stmt manda query a la bd
                ResultSet rs = stmt.executeQuery("SELECT * FROM factura")
        ){
            while(rs.next()){
                //caracteristica de Intelli J que permite crear un metodo para reusar el mismo codigo.
                //Ver crear producto al final de los metodos
                DetalleFactura detailFact = crearDetalleFactura(rs);
                //productos: recibe los datos recolectados por p
                detFacts.add(detailFact);
            }
        }
        catch(SQLException throwables){
            throwables.printStackTrace();
        }

        return detFacts;
    }

    @Override
    public DetalleFactura porId(int id) {
        //PreparedStatement es para sentencias preparadas como WHERE
        DetalleFactura detFact = null;

        try(
                PreparedStatement pstmt = getConnection().
                        prepareStatement("SELECT * FROM productos WHERE id = ?");
        ){
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                //producto esta reutilizando codigo
                //Ver "Caracteriticas para reutilization de codigo de IntelliJ Idea" al final
                detFact = crearDetalleFactura(rs);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }

        return detFact;
    }

    @Override
    public void guardar(DetalleFactura detFact) {
        String sql;
        Integer idReturned = detFact.getIdDetalleFactura();

        if(idReturned != null && idReturned>0){
            sql = "UPDATE producto SET id_factura=?, id_producto=?, cantidad=?, precio_unit=?" +
                    "WHERE id=?";
        }else{
            sql = "INSERT INTO producto(id_factura, id_producto, cantidad, precio_unit)" +
                    "VALUES (?,?,?,?)";
        }

        try(
                PreparedStatement pstmt = getConnection().prepareStatement(sql)
        )
        {
            pstmt.setInt(1, detFact.getFactura().getIdFactura());
            pstmt.setInt(2, detFact.getProduct().getId_producto());
            pstmt.setInt(3, detFact.getCantidad());
            pstmt.setDouble(4, detFact.getPrecioUnit());


            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        try(
                PreparedStatement pstmt = getConnection().prepareStatement("DELETE FROM detalle_factura" +
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
    private static DetalleFactura crearDetalleFactura(ResultSet rs) throws SQLException {
        //p: es una instancia que recolecta los datos desde sql
        DetalleFactura detFact = new DetalleFactura();

        //Desde SQL solo se solicita el tipo del id no un objeto completo
        //por eso se declara un objeto y se manda solo el id del objeto por el ResultSet
        Factura f = new Factura();
        f.setIdFactura(rs.getInt("id_Factura"));
        Producto p = new Producto();
        p.setId_producto(rs.getInt("id_producto"));

        //GetType es la logica, no se hace get del atributo en especifico sino del tipo
        detFact.setCantidad(rs.getInt("cantidad"));
        detFact.setPrecioUnit(rs.getDouble("precio_unit"));

        return detFact;
    }

}
