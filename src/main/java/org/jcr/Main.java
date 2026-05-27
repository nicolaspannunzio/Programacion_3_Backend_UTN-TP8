package org.jcr;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.jcr.entidades.Categoria;
import org.jcr.entidades.Producto;
import org.jcr.enums.Rol;
import org.jcr.enums.Estado;
import org.jcr.enums.FormaPago;
import org.jcr.entidades.Usuario;
import org.jcr.entidades.Pedido;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidad");
        EntityManager em = emf.createEntityManager();

        try {
            // Abrimos la puerta de la base de datos
            em.getTransaction().begin();
            System.out.println("¡Conexión establecida! JPA está listo para operar.");

            /* =========================================================================
               PUNTO 4: INSTANCIAR Y PERSISTIR (COMENTADO PARA NO DUPLICAR)
               =========================================================================
            // --- 1. INSTANCIAR CATEGORÍAS ---
            Categoria catLacteos = Categoria.builder().nombre("Lácteos y Fermentos").descripcion("Insumos para producción láctea").eliminado(false).build();
            Categoria catSuplementos = Categoria.builder().nombre("Suplementos").descripcion("Proteínas y agregados deportivos").eliminado(false).build();
            Categoria catFrutos = Categoria.builder().nombre("Frutos Secos").descripcion("Ideales para activar y consumir").eliminado(false).build();

            em.persist(catLacteos); em.persist(catSuplementos); em.persist(catFrutos);

            // --- 2. INSTANCIAR 10 PRODUCTOS ---
            Producto p1 = Producto.builder().nombre("Leche Entera").precio(1200.0).stock(20).categoria(catLacteos).eliminado(false).build();
            Producto p2 = Producto.builder().nombre("Cultivo Láctico").precio(4500.0).stock(5).categoria(catLacteos).eliminado(false).build();
            Producto p3 = Producto.builder().nombre("Ricota Magra").precio(2100.0).stock(10).categoria(catLacteos).eliminado(false).build();
            Producto p4 = Producto.builder().nombre("Yogur Griego Base").precio(3000.0).stock(15).categoria(catLacteos).eliminado(false).build();
            Producto p5 = Producto.builder().nombre("Proteína de Arveja").precio(18000.0).stock(8).categoria(catSuplementos).eliminado(false).build();
            Producto p6 = Producto.builder().nombre("Creatina Monohidratada").precio(25000.0).stock(3).categoria(catSuplementos).eliminado(false).build();
            Producto p7 = Producto.builder().nombre("Nueces Mariposa").precio(8000.0).stock(12).categoria(catFrutos).eliminado(false).build();
            Producto p8 = Producto.builder().nombre("Almendras").precio(9500.0).stock(20).categoria(catFrutos).eliminado(false).build();
            Producto p9 = Producto.builder().nombre("Castañas de Cajú").precio(11000.0).stock(7).categoria(catFrutos).eliminado(false).build();
            Producto p10 = Producto.builder().nombre("Mix de Semillas").precio(3500.0).stock(30).categoria(catFrutos).eliminado(false).build();

            em.persist(p1); em.persist(p2); em.persist(p3); em.persist(p4); em.persist(p5);
            em.persist(p6); em.persist(p7); em.persist(p8); em.persist(p9); em.persist(p10);
            
            // --- 3. CREAR 2 USUARIOS ---
            Usuario u1 = Usuario.builder().nombre("Nicolás").apellido("Pannunzio").mail("nico@test.com").rol(Rol.ADMIN).eliminado(false).build();
            Usuario u2 = Usuario.builder().nombre("Marga").apellido("Rita").mail("marga@mar.com").rol(Rol.USUARIO).eliminado(false).build();
            
            // --- 4. CREAR PEDIDOS ---
            Pedido ped1 = Pedido.builder().fecha(LocalDate.now()).estado(Estado.PENDIENTE).formaPago(FormaPago.EFECTIVO).usuario(u1).eliminado(false).build();
            ped1.addDetallePedido(2, p1); ped1.addDetallePedido(1, p4); ped1.calcularTotal();

            Pedido ped2 = Pedido.builder().fecha(LocalDate.now()).estado(Estado.CONFIRMADO).formaPago(FormaPago.TRANSFERENCIA).usuario(u1).eliminado(false).build();
            ped2.addDetallePedido(1, p5); ped2.addDetallePedido(3, p7); ped2.calcularTotal();

            Pedido ped3 = Pedido.builder().fecha(LocalDate.now()).estado(Estado.TERMINADO).formaPago(FormaPago.TARJETA).usuario(u2).eliminado(false).build();
            ped3.addDetallePedido(5, p10); ped3.addDetallePedido(1, p6); ped3.calcularTotal();

            em.persist(u1); em.persist(u2);
            ========================================================================= */

            /* =========================================================================
               PUNTO 5: ACTUALIZAR PRODUCTOS (COMENTADO)
               =========================================================================
            Producto prod1 = em.find(Producto.class, 1L);
            Producto prod2 = em.find(Producto.class, 2L);
            if (prod1 != null && prod2 != null) {
                prod1.setPrecio(1500.0); prod1.setStock(50);
                prod2.setPrecio(5000.0);
                em.merge(prod1); em.merge(prod2);
            }
            ========================================================================= */

            /* =========================================================================
               PUNTO 6: BUSCAR USUARIO POR ID (COMENTADO)
               =========================================================================
            Usuario usuarioEncontrado = em.find(Usuario.class, 2L);
            if (usuarioEncontrado != null) {
                System.out.println("Usuario ID 2 encontrado: " + usuarioEncontrado.getNombre());
            }
            ========================================================================= */

            /* =========================================================================
               PUNTO 7: BUSCAR USUARIO POR MAIL (COMENTADO)
               =========================================================================
            String mailBuscado = "nico@test.com";
            Usuario usuarioPorMail = em.createQuery("SELECT u FROM Usuario u WHERE u.mail = :email", Usuario.class)
                                       .setParameter("email", mailBuscado)
                                       .getResultStream()
                                       .findFirst()
                                       .orElse(null);
            if (usuarioPorMail != null) {
                System.out.println("Usuario encontrado por mail: " + usuarioPorMail.getNombre());
            }
            ========================================================================= */

            // --- PUNTO 8: BORRAR 1 PRODUCTO ---
            System.out.println("Ejecutando Punto 8: Borrar un producto...");
            
            // 1. Buscamos el producto que queremos borrar (por ejemplo, el ID 3)
            Producto productoABorrar = em.find(Producto.class, 3L);

            // Verificamos que exista para que no rompa el programa al intentar borrar nulo
            if (productoABorrar != null) {
                System.out.println("Borrando el producto: " + productoABorrar.getNombre());
                
                // 2. Le pedimos a Hibernate que lo elimine
                em.remove(productoABorrar);
                
                System.out.println("¡Producto borrado con éxito de la Base de Datos!");
            } else {
                System.out.println("No se encontró ningún producto con ese ID para borrar.");
            }

            // Confirmamos y cerramos la transacción activa
            em.getTransaction().commit();
            
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
            // emf.close();
        }
    }
}