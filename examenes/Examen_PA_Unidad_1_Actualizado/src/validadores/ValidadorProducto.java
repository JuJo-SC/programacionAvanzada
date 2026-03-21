package validadores;

public class ValidadorProducto {
    public void validarCamposObligatorios(String idTexto, String codigo, String nombre, String descripcion, String categoria, String precioCompraTexto, String precioVentaTexto, String stockTexto, String stockMinimoTexto) {
        if (idTexto.isBlank() || codigo.isBlank() || nombre.isBlank() || descripcion.isBlank() || categoria.isBlank() || precioCompraTexto.isBlank() || precioVentaTexto.isBlank() || stockTexto.isBlank() || stockMinimoTexto.isBlank()) {
            throw new IllegalArgumentException("Todos los campos son obligatorios.");
        }
    }
}
