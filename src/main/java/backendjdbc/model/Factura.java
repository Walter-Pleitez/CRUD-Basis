package backendjdbc.model;

import java.sql.Date;
import java.util.Objects;

public class Factura {
    private int idFactura;
    private Date fecha;
    private double total;
    private Cliente client;
    private Empleado employee;

    public Factura() {
    }

    public Factura(int idFactura, Date fecha, double total, Cliente client, Empleado employee) {
        this.idFactura = idFactura;
        this.fecha = fecha;
        this.total = total;
        this.client = client;
        this.employee = employee;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(java.sql.Date fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Cliente getClient() {
        return client;
    }

    public void setClient(Cliente client) {
        this.client = client;
    }

    public Empleado getEmployee() {
        return employee;
    }

    public void setEmployee(Empleado employee) {
        this.employee = employee;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Factura factura = (Factura) o;
        return idFactura == factura.idFactura && Double.compare(total, factura.total) == 0 && Objects.equals(fecha, factura.fecha) && Objects.equals(client, factura.client) && Objects.equals(employee, factura.employee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFactura, fecha, total, client, employee);
    }
}
