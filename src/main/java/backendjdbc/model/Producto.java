package backendjdbc.model;

public class Producto {
    private int id_producto;
    private String nombre;
    private String categoria;
    private String color;
    private String aroma;
    private String presentacion;
    private double precio;
    private double costo;


    public Producto() {
    }

    public Producto(String nombre, String categoria, String color, String aroma, String presentacion,
                    double precio, double costo) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.color = color;
        this.aroma = aroma;
        this.presentacion = presentacion;
        this.precio = precio;
        this.costo = costo;
    }

    public Producto(int id_producto, String nombre, String categoria, String color, String aroma, String presentacion,
                    double precio, double costo) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.categoria = categoria;
        this.color = color;
        this.aroma = aroma;
        this.presentacion = presentacion;
        this.precio = precio;
        this.costo = costo;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getAroma() {
        return aroma;
    }

    public void setAroma(String aroma) {
        this.aroma = aroma;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }
    @Override
    public String toString() {
        return id_producto +
                " | " + nombre + '\'' +
                " | " + categoria + '\'' +
                " | " + color + '\'' +
                " | " + aroma + '\'' +
                " | " + presentacion + '\'' +
                " | precio = " + precio +
                " | costo = " + costo;
    }

}
