package modelos;

import LogicaUtiles.VentaDeMedicamentos;

public class MedicamentosMasVendidosTableModel extends ModeloPrincipalTableModel<VentaDeMedicamentos> {

    private static final long serialVersionUID = 1L;

    public MedicamentosMasVendidosTableModel() {
        super(new String[] {
            "Posición",
            "Nombre del Medicamento", 
            "Cantidad Vendida"
        });
    }

    @Override
    public void adicionar(VentaDeMedicamentos venta) {
        Object[] fila = {
            getRowCount() + 1, // Posición automática
            venta.getNombre(),
            venta.getCantidadVendida()
        };
        this.addRow(fila);
    }
}