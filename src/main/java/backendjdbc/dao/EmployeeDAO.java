package backendjdbc.dao;

import backendjdbc.model.Cliente;
import backendjdbc.model.Empleado;
import backendjdbc.util.ConexionBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO extends CrudGenerico<Empleado>{

    public EmployeeDAO() {
    }

    public EmployeeDAO(ConexionBD pool) {
        super(pool);
    }

    @Override
    public List listar() {
        List<Empleado> empleados = new ArrayList<>();

        try(
                //stmt: Este abre la conexion a la BD
                Statement stmt = getConnection().createStatement();
                // rs: este toma la conexion abierta por stmt, por lo que stmt manda query a la bd
                ResultSet rs = stmt.executeQuery("SELECT * FROM empleado")
        ){
            while(rs.next()){
                //caracteristica de Intelli J que permite crear un metodo para reusar el mismo codigo.
                //Ver crear producto al final de los metodos
                Empleado emp = crearEmpleado(rs);
                //productos: recibe los datos recolectados por p
                empleados.add(emp);
            }
        }
        catch(SQLException throwables){
            throwables.printStackTrace();
        }

        return empleados;
    }

    public Empleado porId(int id) {
        //PreparedStatement es para sentencias preparadas como WHERE
        Empleado emp = null;

        try(
                PreparedStatement pstmt = getConnection().
                        prepareStatement("SELECT * FROM empleado WHERE id = ?");
        ){
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                //producto esta reutilizando codigo
                //Ver "Caracteriticas para reutilization de codigo de IntelliJ Idea" al final
                emp = crearEmpleado(rs);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }

        return emp;
    }

    public void guardar(Empleado employee) {
        String sql;
        Integer idReturned = employee.getEmpleadoId();

        if(idReturned != null && idReturned>0){
            sql = "UPDATE producto SET nombre=?, apellido=?, telefono=?, correo=?, posicion=?" +
                    "WHERE id=?";
        }else{
            sql = "INSERT INTO producto(nombre, apellido, telefono, correo, posicion)" +
                    "VALUES (?,?,?,?,?)";
        }

        try(
                PreparedStatement pstmt = getConnection().prepareStatement(sql)
        )
        {
            pstmt.setString(1, employee.getNombre());
            pstmt.setString(2, employee.getApellido());
            pstmt.setInt(3, employee.getTelefono());
            pstmt.setString(4, employee.getCorreo());
            pstmt.setString(5, employee.getPosicion());

            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void eliminar(int id) {
        try(
                PreparedStatement pstmt = getConnection().prepareStatement("DELETE FROM empleado" +
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

    private static Empleado crearEmpleado(ResultSet rs) throws SQLException {
        //cli: es una instancia que recolecta los datos desde sql
        Empleado emp = new Empleado();

        //GetType es la logica, no se hace get del atributo en especifico sino del tipo
        emp.setEmpleadoId(rs.getInt("id_empleado"));
        emp.setNombre(rs.getString("nombre"));
        emp.setApellido(rs.getString("apellido"));
        emp.setTelefono(rs.getInt("telefono"));
        emp.setCorreo(rs.getString("correo"));
        emp.setPosicion(rs.getString("posicion"));

        return emp;
    }
}
