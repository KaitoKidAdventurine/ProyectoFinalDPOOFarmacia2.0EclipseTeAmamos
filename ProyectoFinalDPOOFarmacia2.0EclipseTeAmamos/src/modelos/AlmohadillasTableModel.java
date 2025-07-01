package modelos;

import Logica.AlmohadillasSanitarias;
import Logica.Farmacia;
import Logica.Venta;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class AlmohadillasTableModel extends ModeloPrincipalTableModel<Venta> {
    private static final long serialVersionUID = 1L;

    public AlmohadillasTableModel() {
        super(new Object[]{"N° Compra", "Producto", "Precio Unitario", "Cantidad", "Total", "Fecha de Compra", "Acción"});
    }

    @Override
    public void adicionar(Venta venta) {
        Object[] fila = new Object[7];
        fila[0] = getRowCount() + 1; // Número de compra
        fila[1] = obtenerNombreProducto(venta); // Nombre del producto ("Almohadillas Sanitarias")
        fila[2] = obtenerPrecioUnitario(venta); // Precio unitario
        fila[3] = obtenerCantidad(venta); // Cantidad (número de mujeres en el núcleo)
        fila[4] = calcularTotal(fila[2], fila[3]); // Total calculado
        fila[5] = venta.getFechaVenta(); // Fecha de la compra
        fila[6] = "Eliminar"; // Botón de acción
        addRow(fila);
    }

    public void actualizar(List<Venta> ventas) {
        super.actualizar(ventas, null); // Sin orden específico
    }

    public void actualizarOrdenado(List<Venta> ventas, Comparator<? super Venta> comparador) {
        super.actualizar(ventas, comparador); // Ordenar según el comparador
    }

    // Método auxiliar para obtener el nombre del producto desde la venta
    private String obtenerNombreProducto(Venta venta) {
        if (venta instanceof AlmohadillasSanitarias) {
            return "Almohadillas Sanitarias";
        }
        return "Desconocido";
    }

    // Método auxiliar para obtener el precio unitario desde la venta
    private double obtenerPrecioUnitario(Venta venta) {
        if (venta instanceof AlmohadillasSanitarias) {
            return ((AlmohadillasSanitarias) venta).getPrecioUnit();
        }
        return 0.0; // Valor por defecto si no es una venta de almohadillas
    }

    // Método auxiliar para obtener la cantidad desde la venta
    private int obtenerCantidad(Venta venta) {
        if (venta instanceof AlmohadillasSanitarias) {
            return ((AlmohadillasSanitarias) venta).getCant();
        }
        return 0; // Valor por defecto si no es una venta de almohadillas
    }

    // Método auxiliar para calcular el total
    private double calcularTotal(Object precioUnitario, Object cantidad) {
        double precio = ((Number) precioUnitario).doubleValue();
        int cant = ((Number) cantidad).intValue();
        return precio * cant;
    }

    // Método para eliminar una fila
    public void eliminarFila(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < getDataVector().size()) {
            getDataVector().remove(rowIndex); // Eliminar la fila de los datos
            fireTableRowsDeleted(rowIndex, rowIndex); // Notificar a la tabla
        }
    }
}