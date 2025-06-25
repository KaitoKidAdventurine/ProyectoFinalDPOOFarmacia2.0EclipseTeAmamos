package modelos;
import LogicaUtiles.Porcentaje;

public class ComparacionVentasTableModel extends ModeloPrincipalTableModel<Object[]> {

    private static final long serialVersionUID = 1L;

    public ComparacionVentasTableModel() {
        super(new String[] {"Categoría", "Porcentaje de Ventas"});
    }

    @Override
    public void adicionar(Object[] data) {
        // No se usa en este caso particular
    }

    public void cargarPorcentajes(Porcentaje porcentaje) {
        this.eliminarFilas();
        this.addRow(new Object[]{"Almohadillas Sanitarias", String.format("%.2f%%", porcentaje.getPorcentaje())});
        this.addRow(new Object[]{"Medicamentos con Prescripción", String.format("%.2f%%", porcentaje.getPorcentaje())});
        this.addRow(new Object[]{"Medicamentos Controlados", String.format("%.2f%%", porcentaje.getPorcentaje())});
        this.addRow(new Object[]{"Medicamentos de Venta Libre", String.format("%.2f%%", porcentaje.getPorcentaje())});
    }
}