package controladores;

import modelos.ArchivoProductos;
import modelos.GestorProductos;
import vistas.VistaInventarioInternal;
import vistas.VistaPrincipalMDI;
import vistas.VistaProductosInternal;
import vistas.VistaPuntoVentaInternal;

public class ControladorCatalogo {
    public ControladorCatalogo(VistaPrincipalMDI vistaPrincipal, VistaProductosInternal vistaProductos, VistaInventarioInternal vistaInventario, VistaPuntoVentaInternal vistaPuntoVenta, GestorProductos gestor, ArchivoProductos archivo) {
        ControladorContexto contexto = new ControladorContexto(vistaPrincipal, vistaProductos, vistaInventario, vistaPuntoVenta, gestor, archivo);
        ControladorProductos controladorProductos = new ControladorProductos(contexto);
        new ControladorInventario(contexto, controladorProductos);
        new ControladorPuntoVenta(contexto);
        new ControladorPrincipal(contexto);
        contexto.cargarDatosIniciales();
        contexto.mostrarVentanasIniciales();
        contexto.refrescarTodo();
        contexto.limpiarFormularioProducto();
    }
}