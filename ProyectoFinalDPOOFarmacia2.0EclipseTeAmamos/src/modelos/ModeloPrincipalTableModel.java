package modelos;

import javax.swing.table.DefaultTableModel;

import Logica.Medicamento;

import java.util.List;

public abstract class ModeloPrincipalTableModel<T> extends DefaultTableModel {
    private static final long serialVersionUID = 1L;

    public ModeloPrincipalTableModel(String[] columnas) {
        super(columnas, 0); // 0 filas iniciales
    }

    public abstract void adicionar(T elemento);

    public void cargar(List<Medicamento> listaMedicamentos) {
        this.setRowCount(0); // Limpiar tabla antes de llenar
        for (T elem : (List<T>) listaMedicamentos) 
        {
            adicionar(elem);
        }
    }
}