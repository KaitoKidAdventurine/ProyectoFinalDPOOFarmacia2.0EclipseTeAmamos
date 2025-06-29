package modelos;
import java.util.ArrayList;

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

    public void cargarPorcentajes(ArrayList<Porcentaje> porcentaje) {
        this.eliminarFilas();
        for (Porcentaje p : porcentaje) 
        {
            this.addRow(new Object[]{p.getNombre(), String.format("%.2f%%", p.getPorcentaje())});
        }
    }
}