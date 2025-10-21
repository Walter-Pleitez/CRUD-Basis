package backendjdbc.model;

public class Producto {
    private Long id_producto;
    private String nombre;
    private String categoria;
    private String color;
    private String aroma;
    private String presentacion;
    private float precio;
    private float costo;

    public Producto() {
    }

    public Producto(Long id_producto,
                    String nombre, String categoria, String color, String aroma, String presentacion,
                    float precio, float costo) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.categoria = categoria;
        this.color = color;
        this.aroma = aroma;
        this.presentacion = presentacion;
        this.precio = precio;
        this.costo = costo;
    }

    public Long getId_producto() {
        return id_producto;
    }

    public void setId_producto(Long id_producto) {
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

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
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
