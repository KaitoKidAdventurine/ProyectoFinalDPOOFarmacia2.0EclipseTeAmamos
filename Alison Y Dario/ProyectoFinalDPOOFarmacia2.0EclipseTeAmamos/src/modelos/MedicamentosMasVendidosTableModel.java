package modelos;

import LogicaUtiles.VentaDeMedicamentos;

public class MedicamentosMasVendidosTableModel extends ModeloPrincipalTableModel<VentaDeMedicamentos> {

    private static final long serialVersionUID = 1L;

    public MedicamentosMasVendidosTableModel() {
        super(new String[] {
            "Posici�n",
            "Nombre del Medicamento", 
            "Cantidad Vendida"
        });
    }

    @Override
    public void adicionar(VentaDeMedicamentos venta) {
        Object[] fila = {
            getRowCount() + 1, // Posici�n autom�tica
            venta.getNombre(),
            venta.getCantidadVendida()
        };
        this.addRow(fila);
    }
}