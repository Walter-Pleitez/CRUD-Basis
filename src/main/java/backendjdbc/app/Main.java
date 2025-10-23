package backendjdbc.app;

import backendjdbc.dao.ICrud;
import backendjdbc.dao.ProductDAO;
import backendjdbc.model.Producto;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int continuar = 1, opcion=0;
        Scanner inputAddProduct = new Scanner(System.in);

        System.out.println("SISTEMA DE VENTAS");

        do{
            System.out.println("Ingrese la opcion de su preferencia");
            System.out.println("1)Sistema de Ventas 2)BD Productos 3)BD Clientes 4)BD Empleados 5)Facturas");
            opcion = input.nextInt();

            switch(opcion){
                case 1->{
                    //Sistema de ventas
                }
                case 2->{
                    System.out.println("PRODUCTOS");
                    System.out.println("1)Mostrar 2)Buscar por ID 3)Insertar/Actualizar 4)Eliminar");
                    opcionFunc();

                    }
                }
            System.out.println("1)Continuar 0)Salir");
            continuar = inputAddProduct.nextInt();
        }while(continuar == 1);

    }
//It needs to be generic
public static void opcionFunc() {
    Scanner input = new Scanner(System.in);
    int opcion = input.nextInt();
    switch (opcion) {
        case 1 -> {
            ICrud productoCRUD = new ProductDAO();
            productoCRUD.listar();

            Producto producto = new Producto();
            producto.toString();
        }
        case 2 -> {
            int productoId1;
            ICrud productoCRUD1 = new ProductDAO();
            System.out.println("Ingrese el ID del producto a buscar: ");
            productoId1 = input.nextInt();

            System.out.println("El resultado de busqueda es: " + productoCRUD1.porId(productoId1).toString());
        }
        case 3 -> {
            int continuar = 1;
           Scanner inputAddProduct = new Scanner(System.in);
            System.out.println("Inserte o actualize datos");

                ICrud productoCRUD2 = new ProductDAO();
                Producto p1 = addProduct();
                productoCRUD2.guardar(p1);

                System.out.println("Producto guardado");
                System.out.println("1)Otro producto 0)Salir");
               continuar = inputAddProduct.nextInt();

        }
        case 4 -> {
            int productoId2 = 0;
            System.out.println("Eliminar Producto");
            ICrud productoCRUD3 = new ProductDAO();
            System.out.println("Inserte el ID del producto a eliminar: ");
            productoId2 = input.nextInt();

            productoCRUD3.eliminar(productoId2);

            System.out.println("Producto eliminado");
        }
    }
}
//It needs to be generic
public static Producto addProduct(){
        Scanner input = new Scanner(System.in);
    String nombre, categoria, color, aroma, presentacion;
    double precio, costo;
    int continuar = 1;

        System.out.println("Ingrese nombre: ");
        nombre = input.nextLine();
        System.out.println("Ingrese categoria: ");
        categoria = input.nextLine();
        System.out.println("Ingrese color: ");
        color = input.nextLine();
        System.out.println("Ingrese aroma: ");
        aroma = input.nextLine();
        System.out.println("Ingrese presentacion: ");
        presentacion = input.nextLine();
        System.out.println("Ingrese precio: ");
        precio = input.nextDouble();
        System.out.println("Ingrese Costo: ");
        costo = input.nextDouble();

    Producto productoInsertado = new Producto(nombre,categoria,color,aroma,presentacion,precio,costo);

        return productoInsertado;
}


}
