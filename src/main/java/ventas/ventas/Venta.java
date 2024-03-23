package ventas.ventas;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Venta {
    private Long id;
    private LocalDate date;
    private List<Producto> productos;

    public Venta(Long id, LocalDate date) {
        this.id = id;
        this.date = date;
        this.productos = new ArrayList<>();
    }

    //getters
    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    //setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    //add products
    public void addProducto(Producto producto) {
        this.productos.add(producto);
    }
}
