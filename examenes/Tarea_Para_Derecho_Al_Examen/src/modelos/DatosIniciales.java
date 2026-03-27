package modelos;

import java.util.ArrayList;

/**
 * Carga 69 productos de ejemplo para Surti-Tienda en 9 categorías
 */
public class DatosIniciales {

    public static ArrayList<Producto> cargarProductos() {
        ArrayList<Producto> productos = new ArrayList<>();
        
        // 1. DESPENSA BÁSICA - 8 productos
        productos.add(new ProductoUnitario(1, "DB-ARR-001", "Arroz Grano Largo 1kg", "Arroz grano largo premium", "Despensa Básica", 12.0, 54.17, 100, 10, true));
        productos.add(new ProductoUnitario(2, "DB-FRJ-001", "Frijol Negro 1kg", "Frijol negro de alta calidad", "Despensa Básica", 18.0, 38.89, 80, 10, true));
        productos.add(new ProductoUnitario(3, "DB-ACE-001", "Aceite Vegetal 1L", "Aceite vegetal para cocinar", "Despensa Básica", 22.0, 59.09, 60, 8, true));
        productos.add(new ProductoUnitario(4, "DB-PAS-001", "Pasta Spaghetti 200g", "Pasta de trigo durum", "Despensa Básica", 8.0, 50.0, 90, 10, true));
        productos.add(new ProductoUnitario(5, "DB-SAR-001", "Sardinas en Lata", "Sardinas en salsa de tomate", "Despensa Básica", 14.0, 57.14, 70, 8, true));
        productos.add(new ProductoUnitario(6, "DB-SAL-001", "Sal de Mesa 1kg", "Sal fina yodada", "Despensa Básica", 6.0, 66.67, 120, 15, true));
        productos.add(new ProductoUnitario(7, "DB-AZU-001", "Azúcar Blanca 1kg", "Azúcar refinada estándar", "Despensa Básica", 14.0, 57.14, 100, 12, true));
        productos.add(new ProductoUnitario(8, "DB-ATU-001", "Atún en Lata 140g", "Atún en agua", "Despensa Básica", 16.0, 50.0, 85, 10, true));
        
        // 2. LÁCTEOS Y HUEVO - 8 productos
        productos.add(new ProductoUnitario(9, "LH-LEC-001", "Leche Entera 1L", "Leche pasteurizada ultrapasteurizada", "Lácteos y Huevo", 14.0, 57.14, 100, 15, true));
        productos.add(new ProductoUnitario(10, "LH-HUE-001", "Huevo Blanco 18 pzas", "Huevo blanco fresco", "Lácteos y Huevo", 32.0, 56.25, 60, 8, true));
        productos.add(new ProductoUnitario(11, "LH-YOG-001", "Yogurt Natural 1L", "Yogurt sin azúcar añadida", "Lácteos y Huevo", 28.0, 50.0, 40, 5, true));
        productos.add(new ProductoUnitario(12, "LH-MAN-001", "Mantequilla 90g", "Mantequilla sin sal", "Lácteos y Huevo", 22.0, 59.09, 50, 6, true));
        productos.add(new ProductoUnitario(13, "LH-CRE-001", "Crema Ácida 200ml", "Crema para cocinar", "Lácteos y Huevo", 18.0, 55.56, 55, 7, true));
        productos.add(new ProductoUnitario(14, "LH-QUE-001", "Queso Panela 400g", "Queso panela fresco", "Lácteos y Huevo", 35.0, 48.57, 45, 6, true));
        productos.add(new ProductoUnitario(15, "LH-QUE-002", "Queso Oaxaca 400g", "Queso oaxaca hebra", "Lácteos y Huevo", 38.0, 52.63, 40, 5, true));
        productos.add(new ProductoUnitario(16, "LH-YOG-002", "Yogurt Griego 500g", "Yogurt griego con proteína", "Lácteos y Huevo", 32.0, 56.25, 35, 5, true));
        
        // 3. BEBIDAS Y LÍQUIDOS - 8 productos
        productos.add(new ProductoUnitario(17, "BL-AGU-001", "Agua Purificada 1.5L", "Agua purificada sin gas", "Bebidas y Líquidos", 6.0, 100.0, 150, 20, true));
        productos.add(new ProductoUnitario(18, "BL-REF-001", "Refresco Cola 2L", "Refresco carbonatado sabor cola", "Bebidas y Líquidos", 15.0, 66.67, 80, 10, true));
        productos.add(new ProductoUnitario(19, "BL-JUG-001", "Jugo de Naranja 1L", "Jugo de naranja sin azúcar añadida", "Bebidas y Líquidos", 18.0, 55.56, 60, 8, true));
        productos.add(new ProductoUnitario(20, "BL-CAF-001", "Café Soluble 200g", "Café instantáneo premium", "Bebidas y Líquidos", 35.0, 57.14, 40, 5, true));
        productos.add(new ProductoUnitario(21, "BL-TE-001", "Té Verde 25 sobres", "Infusión de té verde natural", "Bebidas y Líquidos", 22.0, 72.73, 50, 6, true));
        productos.add(new ProductoUnitario(22, "BL-CER-001", "Cerveza Lata 355ml", "Cerveza clara lager", "Bebidas y Líquidos", 14.0, 57.14, 200, 25, true));
        productos.add(new ProductoUnitario(23, "BL-ENE-001", "Bebida Energética 250ml", "Bebida energética con cafeína", "Bebidas y Líquidos", 18.0, 55.56, 90, 10, true));
        productos.add(new ProductoUnitario(24, "BL-AGU-002", "Agua Mineral 600ml", "Agua mineral con gas", "Bebidas y Líquidos", 10.0, 60.0, 100, 12, true));
        
        // 4. BOTANAS Y DULCES - 8 productos
        productos.add(new ProductoUnitario(25, "BD-PAP-001", "Papas Fritas Sal 45g", "Papas fritas sabor sal", "Botanas y Dulces", 10.0, 80.0, 90, 12, true));
        productos.add(new ProductoUnitario(26, "BD-GAL-001", "Galletas Marías 200g", "Galletas clásicas marías", "Botanas y Dulces", 12.0, 83.33, 70, 10, true));
        productos.add(new ProductoUnitario(27, "BD-CHO-001", "Chocolate Leche 100g", "Barra de chocolate con leche", "Botanas y Dulces", 16.0, 75.0, 60, 8, true));
        productos.add(new ProductoUnitario(28, "BD-DUL-001", "Dulces Surtidos 100g", "Dulces mexicanos variados", "Botanas y Dulces", 8.0, 87.5, 100, 15, true));
        productos.add(new ProductoUnitario(29, "BD-CAC-001", "Cacahuates Sal 50g", "Cacahuates tostados con sal", "Botanas y Dulces", 6.0, 100.0, 80, 10, true));
        productos.add(new ProductoUnitario(30, "BD-PAL-001", "Palomitas Microondas", "Palomitas con mantequilla", "Botanas y Dulces", 12.0, 66.67, 55, 7, true));
        productos.add(new ProductoUnitario(31, "BD-CHI-001", "Chicles Menta 12pzas", "Chicles sabor menta", "Botanas y Dulces", 7.0, 71.43, 120, 15, true));
        productos.add(new ProductoUnitario(32, "BD-GAL-002", "Galletas Chispas 150g", "Galletas con chispas de chocolate", "Botanas y Dulces", 14.0, 64.29, 65, 8, true));
        
        // 5. FRUTAS Y VERDURAS - 8 productos (Por Peso)
        productos.add(new ProductoPorPeso(33, "FV-MAN-001", "Manzana Roja", "Manzana roja fresca nacional", "Frutas y Verduras", 22.0, 59.09, 50, 5, true));
        productos.add(new ProductoPorPeso(34, "FV-PLA-001", "Plátano Tabasco", "Plátano tabasco maduro", "Frutas y Verduras", 10.0, 80.0, 60, 6, true));
        productos.add(new ProductoPorPeso(35, "FV-TOM-001", "Tomate Bola", "Tomate bola fresco", "Frutas y Verduras", 15.0, 66.67, 40, 4, true));
        productos.add(new ProductoPorPeso(36, "FV-ZAN-001", "Zanahoria", "Zanahoria fresca nacional", "Frutas y Verduras", 12.0, 66.67, 45, 5, true));
        productos.add(new ProductoPorPeso(37, "FV-PAP-001", "Papa Blanca", "Papa blanca criolla", "Frutas y Verduras", 14.0, 57.14, 80, 8, true));
        productos.add(new ProductoPorPeso(38, "FV-NAR-001", "Naranja Valenciana", "Naranja valenciana jugosa", "Frutas y Verduras", 16.0, 75.0, 70, 7, true));
        productos.add(new ProductoPorPeso(39, "FV-CHA-001", "Chayote", "Chayote verde fresco", "Frutas y Verduras", 8.0, 87.5, 35, 4, true));
        productos.add(new ProductoPorPeso(40, "FV-CEB-001", "Cebolla Blanca", "Cebolla blanca nacional", "Frutas y Verduras", 18.0, 55.56, 55, 6, true));
        
        // 6. CARNES Y SALCHICHONERÍA - 9 productos (Por Peso)
        productos.add(new ProductoPorPeso(41, "CS-POL-001", "Pechuga de Pollo", "Pechuga de pollo sin hueso", "Carnes y Salchichonería", 60.0, 50.0, 30, 3, true));
        productos.add(new ProductoPorPeso(42, "CS-RES-001", "Carne Molida de Res", "Carne molida de res fresca", "Carnes y Salchichonería", 80.0, 50.0, 25, 3, true));
        productos.add(new ProductoPorPeso(43, "CS-CER-001", "Costilla de Cerdo", "Costilla de cerdo ahumada", "Carnes y Salchichonería", 70.0, 57.14, 20, 2, true));
        productos.add(new ProductoPorPeso(44, "CS-TIL-001", "Filete de Tilapia", "Filete de tilapia sin espinas", "Carnes y Salchichonería", 50.0, 60.0, 15, 2, true));
        productos.add(new ProductoPorPeso(45, "CS-CAM-001", "Camarón Mediano", "Camarón fresco pelado mediano", "Carnes y Salchichonería", 120.0, 50.0, 10, 1, true));
        productos.add(new ProductoPorPeso(46, "CS-JAM-001", "Jamón de Pavo", "Jamón de pavo bajo en grasa", "Carnes y Salchichonería", 55.0, 54.55, 20, 2, true));
        productos.add(new ProductoPorPeso(47, "CS-SAL-001", "Salchicha Viena", "Salchicha de pavo tipo viena", "Carnes y Salchichonería", 48.0, 56.25, 25, 3, true));
        productos.add(new ProductoPorPeso(48, "CS-TOC-001", "Tocino Ahumado", "Tocino en rebanadas ahumado", "Carnes y Salchichonería", 85.0, 52.94, 15, 2, true));
        productos.add(new ProductoPorPeso(49, "CS-CHO-001", "Chorizo Rojo", "Chorizo rojo estilo español", "Carnes y Salchichonería", 78.0, 53.85, 18, 2, true));
        
        // 7. CUIDADO DEL HOGAR - 8 productos
        productos.add(new ProductoUnitario(50, "CH-DET-001", "Detergente Polvo 1kg", "Detergente en polvo para ropa", "Cuidado del Hogar", 35.0, 57.14, 40, 5, true));
        productos.add(new ProductoUnitario(51, "CH-SUA-001", "Suavizante 800ml", "Suavizante para ropa aroma lavanda", "Cuidado del Hogar", 30.0, 60.0, 35, 4, true));
        productos.add(new ProductoUnitario(52, "CH-DES-001", "Desinfectante 1L", "Desinfectante multiusos", "Cuidado del Hogar", 24.0, 58.33, 50, 6, true));
        productos.add(new ProductoUnitario(53, "CH-PAP-001", "Papel Higiénico 4 rollos", "Papel higiénico doble hoja", "Cuidado del Hogar", 28.0, 50.0, 80, 10, true));
        productos.add(new ProductoUnitario(54, "CH-ESP-001", "Esponja Trastes 3pzas", "Esponja con fibra verde", "Cuidado del Hogar", 10.0, 80.0, 100, 15, true));
        productos.add(new ProductoUnitario(55, "CH-BLA-001", "Blanqueador 1L", "Blanqueador con cloro", "Cuidado del Hogar", 16.0, 75.0, 60, 8, true));
        productos.add(new ProductoUnitario(56, "CH-JAB-001", "Jabón Barra Ropa 400g", "Jabón de barra para ropa", "Cuidado del Hogar", 12.0, 66.67, 70, 10, true));
        productos.add(new ProductoUnitario(57, "CH-LIM-001", "Limpiavidrios 500ml", "Limpiavidrios con atomizador", "Cuidado del Hogar", 20.0, 60.0, 45, 6, true));
        
        // 8. HIGIENE Y CUIDADO PERSONAL - 8 productos
        productos.add(new ProductoUnitario(58, "HP-SHA-001", "Shampoo Neutro 400ml", "Shampoo para todo tipo cabello", "Higiene y Cuidado Personal", 35.0, 57.14, 40, 5, true));
        productos.add(new ProductoUnitario(59, "HP-JAB-001", "Jabón Tocador 3pzas", "Jabón antibacterial pack 3", "Higiene y Cuidado Personal", 18.0, 66.67, 80, 10, true));
        productos.add(new ProductoUnitario(60, "HP-PAS-001", "Pasta Dental 150ml", "Pasta dental con flúor triple acción", "Higiene y Cuidado Personal", 20.0, 60.0, 60, 8, true));
        productos.add(new ProductoUnitario(61, "HP-DES-001", "Desodorante Roll-on", "Desodorante 24 horas protección", "Higiene y Cuidado Personal", 28.0, 60.71, 50, 6, true));
        productos.add(new ProductoUnitario(62, "HP-CRE-001", "Crema Corporal 250ml", "Crema hidratante corporal", "Higiene y Cuidado Personal", 40.0, 62.5, 30, 4, true));
        productos.add(new ProductoUnitario(63, "HP-TOA-001", "Toallas Femeninas 10pzas", "Toallas sanitarias con alas", "Higiene y Cuidado Personal", 22.0, 63.64, 55, 6, true));
        productos.add(new ProductoUnitario(64, "HP-CEP-001", "Cepillo Dental Adulto", "Cepillo dental suave", "Higiene y Cuidado Personal", 15.0, 66.67, 70, 8, true));
        productos.add(new ProductoUnitario(65, "HP-GEL-001", "Gel Antibacterial 250ml", "Gel antibacterial 70% alcohol", "Higiene y Cuidado Personal", 25.0, 60.0, 65, 8, true));
        
        // 9. ALIMENTOS PREPARADOS/ENLATADOS - 4 productos
        productos.add(new ProductoUnitario(66, "AP-SOP-001", "Sopa Instantánea Pollo", "Sopa instantánea sabor pollo", "Alimentos Preparados/Enlatados", 5.0, 80.0, 100, 12, true));
        productos.add(new ProductoUnitario(67, "AP-FRI-001", "Frijoles Refritos Lata 430g", "Frijoles refritos enlatados", "Alimentos Preparados/Enlatados", 12.0, 66.67, 80, 10, true));
        productos.add(new ProductoUnitario(68, "AP-CHI-001", "Chiles Jalapeños Lata 380g", "Chiles jalapeños en escabeche", "Alimentos Preparados/Enlatados", 15.0, 60.0, 70, 8, true));
        productos.add(new ProductoUnitario(69, "AP-ELO-001", "Elote Dulce Lata 410g", "Granos de elote dulce enlatado", "Alimentos Preparados/Enlatados", 14.0, 57.14, 75, 9, true));
        
        System.out.println("✓ Cargados 69 productos de Surti-Tienda en 9 categorías");
        System.out.println("  1. Despensa Básica (8 productos)");
        System.out.println("  2. Lácteos y Huevo (8 productos)");
        System.out.println("  3. Bebidas y Líquidos (8 productos)");
        System.out.println("  4. Botanas y Dulces (8 productos)");
        System.out.println("  5. Frutas y Verduras (8 productos)");
        System.out.println("  6. Carnes y Salchichonería (9 productos)");
        System.out.println("  7. Cuidado del Hogar (8 productos)");
        System.out.println("  8. Higiene y Cuidado Personal (8 productos)");
        System.out.println("  9. Alimentos Preparados/Enlatados (4 productos)");
        
        return productos;
    }
}
