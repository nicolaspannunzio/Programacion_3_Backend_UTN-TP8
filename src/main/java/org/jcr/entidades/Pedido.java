package org.jcr.entidades;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.jcr.enums.Estado;
import org.jcr.enums.FormaPago;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Pedido extends Base implements Calculable {

    private LocalDate fecha;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    private Double total;

    @Enumerated(EnumType.STRING)
    private FormaPago formaPago;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<DetallePedido> detalles = new ArrayList<>();

    public void addDetallePedido(int cantidad, Producto producto) {
        DetallePedido detalle = DetallePedido.builder()
                .eliminado(false)
                .createdAt(LocalDateTime.now())
                .cantidad(cantidad)
                .subtotal(producto.getPrecio() * cantidad)
                .producto(producto)
                .pedido(this) 
                .build();
        detalles.add(detalle);
    }

    public DetallePedido findeDetallePedidoByProducto(Producto producto) {
        return detalles.stream()
                .filter(d -> d.getProducto().equals(producto))
                .findFirst()
                .orElse(null);
    }

    public void deleteDetallePedidoByProducto(Producto producto) {
        detalles.removeIf(d -> d.getProducto().equals(producto));
    }

    @Override
    public void calcularTotal() {
        this.total = detalles.stream()
                .mapToDouble(DetallePedido::getSubtotal)
                .sum();
    }
}