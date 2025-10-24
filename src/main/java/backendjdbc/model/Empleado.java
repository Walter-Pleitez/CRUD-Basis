package backendjdbc.model;

import java.util.Objects;

public class Empleado {
    private int empleadoId;
    private String nombre;
    private String apellido;
    private int telefono;
    private String correo;
    private String posicion;

    public Empleado() {
    }

    public Empleado(int empleadoId, String nombre, String apellido, int telefono, String correo, String posicion) {
        this.empleadoId = empleadoId;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correo = correo;
        this.posicion = posicion;
    }

    public int getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Empleado empleado = (Empleado) o;
        return empleadoId == empleado.empleadoId && telefono == empleado.telefono && Objects.equals(nombre, empleado.nombre) && Objects.equals(apellido, empleado.apellido) && Objects.equals(correo, empleado.correo) && Objects.equals(posicion, empleado.posicion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empleadoId, nombre, apellido, telefono, correo, posicion);
    }
}
