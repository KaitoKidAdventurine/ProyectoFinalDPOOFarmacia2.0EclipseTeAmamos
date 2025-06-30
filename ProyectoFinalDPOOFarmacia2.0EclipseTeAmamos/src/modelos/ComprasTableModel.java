package modelos;

import Logica.Farmacia;
import Logica.Venta;
import Logica.VentaLibre;

import java.util.Comparator;
import java.util.List;

public class ComprasTableModel extends ModeloPrincipalTableModel<Venta> {
    private static final long serialVersionUID = 1L;
    public int cantidad ;

    public ComprasTableModel() {
        super(new Object[]{"N° Compra", "Medicamento", "Precio","Cantidad", "Fecha de Compra", "Acción"});
    }

    @Override
    public void adicionar(Venta venta) {
        Object[] fila = new Object[6];
        fila[0] = getRowCount() + 1; // Número de compra
        fila[1] = obtenerNombreMedicamento(venta); // Nombre del medicamento
        fila[2] = obtenerPrecioMedicamento(fila[1].toString()); // Precio del medicamento
        fila[3] = cantidad;
        fila[4] = venta.getFechaVenta(); // Fecha de la compra
        fila[5] = "Eliminar"; // Botón de acción
        addRow(fila);
    }

    public void actualizar(List<Venta> ventas) {
        super.actualizar(ventas, null); // Sin orden específico
    }

    public void actualizarOrdenado(List<Venta> ventas, Comparator<? super Venta> comparador) {
        super.actualizar(ventas, comparador); // Ordenar según el comparador
    }

    // Método auxiliar para obtener el nombre del medicamento desde la venta
    private String obtenerNombreMedicamento(Venta venta) {
        if (venta instanceof VentaLibre) {
            return ((VentaLibre) venta).getNombreDelMed();
        }
        return "Desconocido";
    }

 // Método auxiliar para obtener el precio del medicamento desde la clase Farmacia
    private double obtenerPrecioMedicamento(String nombreMedicamento) {
        double precio = 0.0; // Valor por defecto si no se encuentra el medicamento

        try {
            // Intentar obtener el precio del medicamento desde la instancia de Farmacia
            precio = Farmacia.obtenerInstancia().obtenerPrecioMedicamento(nombreMedicamento);
        } catch (Exception e) {
            // Manejar la excepción: dejar el precio en 0.0 o registrar un mensaje de error
            System.err.println("Error al obtener el precio del medicamento: " + nombreMedicamento);
            e.printStackTrace();
        }

        return precio; // Devolver el precio obtenido o el valor por defecto
    }
    
    public void eliminarFila(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < getDataVector().size()) {
            getDataVector().remove(rowIndex); // Eliminar la fila de los datos
            fireTableRowsDeleted(rowIndex, rowIndex); // Notificar a la tabla
        }
    }
}