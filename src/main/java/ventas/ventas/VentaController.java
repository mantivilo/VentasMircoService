package ventas.ventas;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//controlador
@RestController
@RequestMapping("/ventas") //http://localhost:8080/ventas
public class VentaController {
    private List<Venta> ventas = new ArrayList<>();

    // Constructor para agregar data de ejemplo
    public VentaController() {
        // Crear una venta
        Venta venta0 = new Venta(1L, LocalDate.of(2024, 3, 23));
        //Agregar productos a la venta
        venta0.addProducto(new Producto(1, "Collar para perro M", 7.999));
        venta0.addProducto(new Producto(2, "Correa para perro 3M", 14.999));
        venta0.addProducto(new Producto(3, "Saco 10KLS comida perro pug importado", 30.999));
        ventas.add(venta0);

        Venta venta1 = new Venta(2L, LocalDate.of(2023, 12, 23));
        venta1.addProducto(new Producto(1, "Juguete para gato", 4.999));
        venta1.addProducto(new Producto(2, "Vitamina B12 para gatos", 9.999));
        venta1.addProducto(new Producto(3, "Shampoo en seco para gato 100ML", 10.999));
        venta1.addProducto(new Producto(4, "Cepillo para gato", 3.999));
        ventas.add(venta1);

        Venta venta2 = new Venta(3L, LocalDate.of(2023, 11, 12));
        venta2.addProducto(new Producto(1, "Piscina para perro L", 30.990));
        venta2.addProducto(new Producto(2, "Alimento para Hamster", 1.990));
        venta2.addProducto(new Producto(3, "Vitaminas para reptiles 50ML", 17.999));
        venta2.addProducto(new Producto(4, "Rueda para Hamster", 6.999));
        venta2.addProducto(new Producto(5, "Bandana Kawai para perro", 4.499));
        ventas.add(venta2);
    }

    //metodo para retornar todas las ventas
    @GetMapping
    public List<Venta> getAllSales() {
        return ventas;
    }

    //metodo para traer una venta
    //http://localhost:8080/ventas/1
    @GetMapping("/{id}")
    public Venta getSaleById(@PathVariable Long id) {
        return ventas.stream()
                .filter(v -> v.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    //metodo para traer los productos de una venta
    //http://localhost:8080/ventas/0/productos
    @GetMapping("/{id}/productos")
    public List<Producto> getProductsOfSale(@PathVariable Long id) {
    Venta venta = ventas.stream()
            .filter(v -> v.getId().equals(id))
            .findFirst()
            .orElse(null);
        if (venta != null) {
            System.out.println("Productos de venta " + id + " traidos con exito.");
            return venta.getProductos();
        } else {
            System.out.println("Venta con ID " + id + " no encontrada, devolviendo lista vacia.");
            return new ArrayList<>(); // Retorna una lista vacia si la venta no fue encontrada
        }
    }

    //metodo para traer el total de una venta
    //http://localhost:8080/ventas/3/MontoTotalVenta
    @GetMapping("/{id}/MontoTotalVenta")
    public double getMontoTotalVenta(@PathVariable Long id) {
    Venta venta = ventas.stream()
            .filter(v -> v.getId().equals(id))
            .findFirst()
            .orElse(null);
        if (venta != null) {
            double totalAmount = venta.getProductos().stream()
                    .mapToDouble(Producto::getPrecio)
                    .sum();
            System.out.println("Total amount of sale " + id + ": " + totalAmount);        
            return totalAmount;
        } else {
            return 0.0; // Devuelve 0 si la venta no se encontro 
        }
    }

    //metodo para traer la venta mayor
    //http://localhost:8080/ventas/ventaMayor
    @GetMapping("/ventaMayor")
    public Venta getVentaMayor() {
        Venta ventaMayor = null;
        double montoMasAlto = Double.MIN_VALUE; // Inializa con el valor más bajo posible

        //recorremos las ventas y guardamos la mas alta
        for (Venta venta : ventas) {
            double montoTotal = venta.getProductos().stream()
                    .mapToDouble(Producto::getPrecio)
                    .sum();
            if (montoTotal > montoMasAlto) {
                montoMasAlto = montoTotal;
                ventaMayor = venta;
            }
        }

        if (ventaMayor != null) {
            System.out.println("La venta mas cara es la venta con ID " + ventaMayor.getId() + " con un monto total de: " + montoMasAlto);
        } else {
            System.out.println("No se encontraron ventas");
        }

        return ventaMayor;
    }

}
