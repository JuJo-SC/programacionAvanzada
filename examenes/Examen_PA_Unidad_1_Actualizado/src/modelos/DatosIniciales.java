package modelos;

import java.io.*;
import java.util.ArrayList;

/**
 * Carga 69 productos de ejemplo en 11 categorías
 */
public class DatosIniciales {

    public static ArrayList<Producto> cargarProductos() {
        ArrayList<Producto> productos = new ArrayList<>();
        
        // 1. ABARROTES (Despensa) - 7 productos
        productos.add(new ProductoUnitario(1, "ABR001", "Arroz 1kg", "Arroz grano largo", "Abarrotes (Despensa)", 12.0, 18.5, 100, 10, true));
        productos.add(new ProductoUnitario(2, "ABR002", "Frijol Negro 1kg", "Frijol negro de alta calidad", "Abarrotes (Despensa)", 18.0, 25.0, 80, 10, true));
        productos.add(new ProductoUnitario(3, "ABR003", "Aceite Vegetal 1L", "Aceite para cocinar", "Abarrotes (Despensa)", 22.0, 35.0, 60, 8, true));
        productos.add(new ProductoUnitario(4, "ABR004", "Pasta Spaghetti 200g", "Pasta de trigo", "Abarrotes (Despensa)", 8.0, 12.0, 90, 10, true));
        productos.add(new ProductoUnitario(5, "ABR005", "Sardinas en Lata", "Sardinas en salsa de tomate", "Abarrotes (Despensa)", 14.0, 22.0, 70, 8, true));
        productos.add(new ProductoUnitario(6, "ABR006", "Sal de Mesa 1kg", "Sal fina yodada", "Abarrotes (Despensa)", 6.0, 10.0, 120, 15, true));
        productos.add(new ProductoUnitario(7, "ABR007", "Azucar Blanca 1kg", "Azucar refinada", "Abarrotes (Despensa)", 14.0, 22.0, 100, 12, true));
        
        // 2. BEBIDAS - 7 productos
        productos.add(new ProductoUnitario(8, "BEB001", "Agua Purificada 1.5L", "Agua sin gas", "Bebidas", 6.0, 12.0, 150, 20, true));
        productos.add(new ProductoUnitario(9, "BEB002", "Refresco Cola 2L", "Refresco carbonatado", "Bebidas", 15.0, 25.0, 80, 10, true));
        productos.add(new ProductoUnitario(10, "BEB003", "Jugo de Naranja 1L", "Jugo natural sin azucar anadida", "Bebidas", 18.0, 28.0, 60, 8, true));
        productos.add(new ProductoUnitario(11, "BEB004", "Cafe Soluble 200g", "Cafe instantaneo", "Bebidas", 35.0, 55.0, 40, 5, true));
        productos.add(new ProductoUnitario(12, "BEB005", "Te Verde (25 sobres)", "Infusion de te verde", "Bebidas", 22.0, 38.0, 50, 6, true));
        productos.add(new ProductoUnitario(13, "BEB006", "Cerveza Lata 355ml", "Cerveza clara", "Bebidas", 14.0, 22.0, 200, 25, true));
        productos.add(new ProductoUnitario(14, "BEB007", "Bebida Energetica 250ml", "Bebida con cafeina", "Bebidas", 18.0, 28.0, 90, 10, true));
        
        // 3. LACTEOS Y HUEVO - 6 productos
        productos.add(new ProductoUnitario(15, "LAC001", "Leche Entera 1L", "Leche pasteurizada", "Lacteos y Huevo", 14.0, 22.0, 100, 15, true));
        productos.add(new ProductoUnitario(16, "LAC002", "Huevo 30 piezas", "Huevo blanco fresco", "Lacteos y Huevo", 55.0, 85.0, 60, 8, true));
        productos.add(new ProductoUnitario(17, "LAC003", "Yogurt Natural 1kg", "Yogurt sin azucar", "Lacteos y Huevo", 28.0, 42.0, 40, 5, true));
        productos.add(new ProductoUnitario(18, "LAC004", "Mantequilla 90g", "Mantequilla sin sal", "Lacteos y Huevo", 22.0, 35.0, 50, 6, true));
        productos.add(new ProductoUnitario(19, "LAC005", "Crema Acida 200g", "Crema para cocinar", "Lacteos y Huevo", 18.0, 28.0, 55, 7, true));
        productos.add(new ProductoUnitario(20, "LAC006", "Gelatina Familiar", "Postre de gelatina", "Lacteos y Huevo", 10.0, 18.0, 70, 10, true));
        
        // 4. FRUTAS Y VERDURAS - 7 productos (Por Peso)
        productos.add(new ProductoPorPeso(21, "FRV001", "Manzana", "Manzana roja fresca", "Frutas y Verduras", 22.0, 35.0, 50, 5, true));
        productos.add(new ProductoPorPeso(22, "FRV002", "Platano", "Platano tabasco", "Frutas y Verduras", 10.0, 18.0, 60, 6, true));
        productos.add(new ProductoPorPeso(23, "FRV003", "Tomate", "Tomate bola fresco", "Frutas y Verduras", 15.0, 25.0, 40, 4, true));
        productos.add(new ProductoPorPeso(24, "FRV004", "Zanahoria", "Zanahoria fresca", "Frutas y Verduras", 12.0, 20.0, 45, 5, true));
        productos.add(new ProductoPorPeso(25, "FRV005", "Papa", "Papa blanca", "Frutas y Verduras", 14.0, 22.0, 80, 8, true));
        productos.add(new ProductoPorPeso(26, "FRV006", "Naranja", "Naranja valenciana", "Frutas y Verduras", 16.0, 28.0, 70, 7, true));
        productos.add(new ProductoPorPeso(27, "FRV007", "Chayote", "Chayote verde fresco", "Frutas y Verduras", 8.0, 15.0, 35, 4, true));
        
        // 5. CARNES Y PESCADOS - 6 productos (Por Peso)
        productos.add(new ProductoPorPeso(28, "CAR001", "Pechuga de Pollo", "Pechuga sin hueso", "Carnes y Pescados", 60.0, 90.0, 30, 3, true));
        productos.add(new ProductoPorPeso(29, "CAR002", "Carne Molida de Res", "Carne molida fresca", "Carnes y Pescados", 80.0, 120.0, 25, 3, true));
        productos.add(new ProductoPorPeso(30, "CAR003", "Costilla de Cerdo", "Costilla ahumada", "Carnes y Pescados", 70.0, 110.0, 20, 2, true));
        productos.add(new ProductoPorPeso(31, "CAR004", "Filete de Tilapia", "Tilapia sin espinas", "Carnes y Pescados", 50.0, 80.0, 15, 2, true));
        productos.add(new ProductoPorPeso(32, "CAR005", "Camaron Mediano", "Camaron fresco pelado", "Carnes y Pescados", 120.0, 180.0, 10, 1, true));
        productos.add(new ProductoPorPeso(33, "CAR006", "Salmon en Rebanada", "Salmon atlantico", "Carnes y Pescados", 150.0, 220.0, 8, 1, true));
        
        // 6. SALCHICHONERIA - 6 productos (Por Peso)
        productos.add(new ProductoPorPeso(34, "SAL001", "Jamon de Pavo", "Jamon bajo en grasa", "Salchichoneria", 55.0, 85.0, 20, 2, true));
        productos.add(new ProductoPorPeso(35, "SAL002", "Salchicha Viena", "Salchicha de pavo", "Salchichoneria", 48.0, 75.0, 25, 3, true));
        productos.add(new ProductoPorPeso(36, "SAL003", "Tocino Ahumado", "Tocino en rebanadas", "Salchichoneria", 85.0, 130.0, 15, 2, true));
        productos.add(new ProductoPorPeso(37, "SAL004", "Queso Manchego", "Queso manchego rebanado", "Salchichoneria", 120.0, 180.0, 12, 2, true));
        productos.add(new ProductoPorPeso(38, "SAL005", "Chorizo Rojo", "Chorizo estilo espanol", "Salchichoneria", 78.0, 120.0, 18, 2, true));
        productos.add(new ProductoPorPeso(39, "SAL006", "Mortadela", "Mortadela con aceitunas", "Salchichoneria", 42.0, 65.0, 22, 3, true));
        
        // 7. PANADERIA Y TORTILLERIA - 6 productos
        productos.add(new ProductoUnitario(40, "PAN001", "Pan de Caja Blanco", "Pan de caja grande", "Panaderia y Tortilleria", 24.0, 38.0, 30, 4, true));
        productos.add(new ProductoUnitario(41, "PAN002", "Tortilla de Maiz 1kg", "Tortilla fresca de maiz", "Panaderia y Tortilleria", 14.0, 22.0, 60, 8, true));
        productos.add(new ProductoUnitario(42, "PAN003", "Bolillo (pieza)", "Pan frances fresco", "Panaderia y Tortilleria", 2.0, 4.0, 100, 20, true));
        productos.add(new ProductoUnitario(43, "PAN004", "Concha de Pan Dulce", "Pan dulce tipo concha", "Panaderia y Tortilleria", 3.0, 6.0, 80, 15, true));
        productos.add(new ProductoUnitario(44, "PAN005", "Pan Integral 680g", "Pan integral de avena", "Panaderia y Tortilleria", 28.0, 42.0, 25, 4, true));
        productos.add(new ProductoUnitario(45, "PAN006", "Tortilla de Harina", "Tortilla de harina grande", "Panaderia y Tortilleria", 18.0, 28.0, 45, 6, true));
        
        // 8. LIMPIEZA DEL HOGAR - 6 productos
        productos.add(new ProductoUnitario(46, "LIM001", "Detergente en Polvo 1kg", "Detergente para ropa", "Limpieza del Hogar", 35.0, 55.0, 40, 5, true));
        productos.add(new ProductoUnitario(47, "LIM002", "Suavizante Ropa 800ml", "Suavizante lavanda", "Limpieza del Hogar", 30.0, 48.0, 35, 4, true));
        productos.add(new ProductoUnitario(48, "LIM003", "Desinfectante 1L", "Desinfectante multiusos", "Limpieza del Hogar", 24.0, 38.0, 50, 6, true));
        productos.add(new ProductoUnitario(49, "LIM004", "Papel Higienico 4 rollos", "Papel doble hoja", "Limpieza del Hogar", 28.0, 42.0, 80, 10, true));
        productos.add(new ProductoUnitario(50, "LIM005", "Esponja para Trastes", "Esponja con fibra", "Limpieza del Hogar", 10.0, 18.0, 100, 15, true));
        productos.add(new ProductoUnitario(51, "LIM006", "Blanqueador 1L", "Blanqueador con cloro", "Limpieza del Hogar", 16.0, 28.0, 60, 8, true));
        
        // 9. CUIDADO PERSONAL - 6 productos
        productos.add(new ProductoUnitario(52, "CUI001", "Shampoo Neutro 400ml", "Shampoo para todo tipo de cabello", "Cuidado Personal", 35.0, 55.0, 40, 5, true));
        productos.add(new ProductoUnitario(53, "CUI002", "Jabon de Tocador", "Jabon antibacterial", "Cuidado Personal", 10.0, 18.0, 80, 10, true));
        productos.add(new ProductoUnitario(54, "CUI003", "Pasta Dental 75ml", "Pasta con fluor", "Cuidado Personal", 20.0, 32.0, 60, 8, true));
        productos.add(new ProductoUnitario(55, "CUI004", "Desodorante Roll-on", "Desodorante 24 horas", "Cuidado Personal", 28.0, 45.0, 50, 6, true));
        productos.add(new ProductoUnitario(56, "CUI005", "Crema Corporal 250g", "Crema hidratante", "Cuidado Personal", 40.0, 65.0, 30, 4, true));
        productos.add(new ProductoUnitario(57, "CUI006", "Papel Facial 100pzs", "Kleenex suave", "Cuidado Personal", 20.0, 32.0, 45, 5, true));
        
        // 10. SNACKS Y DULCERIA - 6 productos
        productos.add(new ProductoUnitario(58, "SNA001", "Papas Sabor Limon", "Papas fritas limon", "Snacks y Dulceria", 10.0, 18.0, 90, 12, true));
        productos.add(new ProductoUnitario(59, "SNA002", "Galletas Marias", "Galletas clasicas", "Snacks y Dulceria", 12.0, 22.0, 70, 10, true));
        productos.add(new ProductoUnitario(60, "SNA003", "Chocolate de Leche", "Barra de chocolate", "Snacks y Dulceria", 16.0, 28.0, 60, 8, true));
        productos.add(new ProductoUnitario(61, "SNA004", "Dulces Surtidos 100g", "Dulces mexicanos", "Snacks y Dulceria", 8.0, 15.0, 100, 15, true));
        productos.add(new ProductoUnitario(62, "SNA005", "Cacahuates Saladitos", "Cacahuates con sal", "Snacks y Dulceria", 6.0, 12.0, 80, 10, true));
        productos.add(new ProductoUnitario(63, "SNA006", "Palomitas Microondas", "Palomitas con mantequilla", "Snacks y Dulceria", 12.0, 20.0, 55, 7, true));
        
        // 11. MASCOTAS - 6 productos
        productos.add(new ProductoPorPeso(64, "MAS001", "Alimento Perro Premium 1kg", "Croquetas adulto raza mediana", "Mascotas", 28.0, 45.0, 20, 3, true));
        productos.add(new ProductoPorPeso(65, "MAS002", "Alimento Gato Adulto 1kg", "Croquetas para gato adulto", "Mascotas", 30.0, 48.0, 18, 2, true));
        productos.add(new ProductoPorPeso(66, "MAS003", "Croquetas Cachorro 1kg", "Alimento cachorro raza pequeña", "Mascotas", 32.0, 52.0, 15, 2, true));
        productos.add(new ProductoUnitario(67, "MAS004", "Arena para Gatos 5kg", "Arena aglomerante", "Mascotas", 40.0, 65.0, 25, 3, true));
        productos.add(new ProductoUnitario(68, "MAS005", "Hueso de Nylon Mediano", "Juguete para perros", "Mascotas", 22.0, 35.0, 30, 4, true));
        productos.add(new ProductoUnitario(69, "MAS006", "Snack Dental Perro", "Premio dental para perros", "Mascotas", 18.0, 28.0, 40, 5, true));
        
        // Agregar rutas de imágenes a todos los productos
        productos.get(0).setImagenRuta("imagenes/productos/arroz.jpg");
        productos.get(1).setImagenRuta("imagenes/productos/frijol.jpg");
        productos.get(2).setImagenRuta("imagenes/productos/aceite.jpg");
        productos.get(3).setImagenRuta("imagenes/productos/pasta.jpg");
        productos.get(4).setImagenRuta("imagenes/productos/sardinas.jpg");
        productos.get(5).setImagenRuta("imagenes/productos/sal.jpg");
        productos.get(6).setImagenRuta("imagenes/productos/azucar.jpg");
        productos.get(7).setImagenRuta("imagenes/productos/agua.jpg");
        productos.get(8).setImagenRuta("imagenes/productos/refresco.jpg");
        productos.get(9).setImagenRuta("imagenes/productos/jugo.jpg");
        productos.get(10).setImagenRuta("imagenes/productos/cafe.jpg");
        productos.get(11).setImagenRuta("imagenes/productos/te.jpg");
        productos.get(12).setImagenRuta("imagenes/productos/cerveza.jpg");
        productos.get(13).setImagenRuta("imagenes/productos/energetica.jpg");
        productos.get(14).setImagenRuta("imagenes/productos/leche.jpg");
        productos.get(15).setImagenRuta("imagenes/productos/huevo.jpg");
        productos.get(16).setImagenRuta("imagenes/productos/yogurt.jpg");
        productos.get(17).setImagenRuta("imagenes/productos/mantequilla.jpg");
        productos.get(18).setImagenRuta("imagenes/productos/crema.jpg");
        productos.get(19).setImagenRuta("imagenes/productos/gelatina.jpg");
        productos.get(20).setImagenRuta("imagenes/productos/manzana.jpg");
        productos.get(21).setImagenRuta("imagenes/productos/platano.jpg");
        productos.get(22).setImagenRuta("imagenes/productos/tomate.jpg");
        productos.get(23).setImagenRuta("imagenes/productos/zanahoria.jpg");
        productos.get(24).setImagenRuta("imagenes/productos/papa.jpg");
        productos.get(25).setImagenRuta("imagenes/productos/naranja.jpg");
        productos.get(26).setImagenRuta("imagenes/productos/chayote.jpg");
        productos.get(27).setImagenRuta("imagenes/productos/pollo.jpg");
        productos.get(28).setImagenRuta("imagenes/productos/carne_res.jpg");
        productos.get(29).setImagenRuta("imagenes/productos/cerdo.jpg");
        productos.get(30).setImagenRuta("imagenes/productos/tilapia.jpg");
        productos.get(31).setImagenRuta("imagenes/productos/camaron.jpg");
        productos.get(32).setImagenRuta("imagenes/productos/salmon.jpg");
        productos.get(33).setImagenRuta("imagenes/productos/jamon.jpg");
        productos.get(34).setImagenRuta("imagenes/productos/salchicha.jpg");
        productos.get(35).setImagenRuta("imagenes/productos/tocino.jpg");
        productos.get(36).setImagenRuta("imagenes/productos/queso.jpg");
        productos.get(37).setImagenRuta("imagenes/productos/chorizo.jpg");
        productos.get(38).setImagenRuta("imagenes/productos/mortadela.jpg");
        productos.get(39).setImagenRuta("imagenes/productos/pan_caja.jpg");
        productos.get(40).setImagenRuta("imagenes/productos/tortilla.jpg");
        productos.get(41).setImagenRuta("imagenes/productos/bolillo.jpg");
        productos.get(42).setImagenRuta("imagenes/productos/concha.jpg");
        productos.get(43).setImagenRuta("imagenes/productos/pan_integral.jpg");
        productos.get(44).setImagenRuta("imagenes/productos/tortilla_harina.jpg");
        productos.get(45).setImagenRuta("imagenes/productos/detergente.jpg");
        productos.get(46).setImagenRuta("imagenes/productos/suavizante.jpg");
        productos.get(47).setImagenRuta("imagenes/productos/desinfectante.jpg");
        productos.get(48).setImagenRuta("imagenes/productos/papel_higienico.jpg");
        productos.get(49).setImagenRuta("imagenes/productos/esponja.jpg");
        productos.get(50).setImagenRuta("imagenes/productos/blanqueador.jpg");
        productos.get(51).setImagenRuta("imagenes/productos/shampoo.jpg");
        productos.get(52).setImagenRuta("imagenes/productos/jabon.jpg");
        productos.get(53).setImagenRuta("imagenes/productos/pasta_dental.jpg");
        productos.get(54).setImagenRuta("imagenes/productos/desodorante.jpg");
        productos.get(55).setImagenRuta("imagenes/productos/crema_corporal.jpg");
        productos.get(56).setImagenRuta("imagenes/productos/papel_facial.jpg");
        productos.get(57).setImagenRuta("imagenes/productos/papas.jpg");
        productos.get(58).setImagenRuta("imagenes/productos/galletas.jpg");
        productos.get(59).setImagenRuta("imagenes/productos/chocolate.jpg");
        productos.get(60).setImagenRuta("imagenes/productos/dulces.jpg");
        productos.get(61).setImagenRuta("imagenes/productos/cacahuates.jpg");
        productos.get(62).setImagenRuta("imagenes/productos/palomitas.jpg");
        productos.get(63).setImagenRuta("imagenes/productos/alimento_perro.jpg");
        productos.get(64).setImagenRuta("imagenes/productos/alimento_gato.jpg");
        productos.get(65).setImagenRuta("imagenes/productos/alimento_cachorro.jpg");
        productos.get(66).setImagenRuta("imagenes/productos/arena_gatos.jpg");
        productos.get(67).setImagenRuta("imagenes/productos/juguete_perro.jpg");
        productos.get(68).setImagenRuta("imagenes/productos/snack_perro.jpg");
        
        System.out.println("✓ Cargados 69 productos en 11 categorías");
        return productos;
    }
}
