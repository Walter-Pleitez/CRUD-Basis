package backendjdbc.model;

import java.util.Objects;

public class DetalleFactura {
    private int idDetalleFactura;
    private Factura factura;
    private Producto product;
    private int cantidad;
    private double precioUnit;

    public DetalleFactura() {
    }

    public DetalleFactura(int idDetalleFactura, Factura factura, Producto product, int cantidad, double precioUnit) {
        this.idDetalleFactura = idDetalleFactura;
        this.factura = factura;
        this.product = product;
        this.cantidad = cantidad;
        this.precioUnit = precioUnit;
    }

    public int getIdDetalleFactura() {
        return idDetalleFactura;
    }

    public void setIdDetalleFactura(int idDetalleFactura) {
        this.idDetalleFactura = idDetalleFactura;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Producto getProduct() {
        return product;
    }

    public void setProduct(Producto product) {
        this.product = product;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnit() {
        return precioUnit;
    }

    public void setPrecioUnit(double precioUnit) {
        this.precioUnit = precioUnit;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DetalleFactura that = (DetalleFactura) o;
        return idDetalleFactura == that.idDetalleFactura && cantidad == that.cantidad && Double.compare(precioUnit, that.precioUnit) == 0 && Objects.equals(factura, that.factura) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDetalleFactura, factura, product, cantidad, precioUnit);
    }
}
