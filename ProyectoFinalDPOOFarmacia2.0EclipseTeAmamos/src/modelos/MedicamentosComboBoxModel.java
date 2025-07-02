package modelos;

import javax.swing.DefaultComboBoxModel;

import Logica.Medicamento;

import java.util.List;

public class MedicamentosComboBoxModel extends DefaultComboBoxModel<String> {
   
	private static final long serialVersionUID = 1L;

	public MedicamentosComboBoxModel() {
        
    }

    // Método para cargar datos
	 public void cargarDatos(List<Medicamento> medicamentos) {
	        this.removeAllElements(); // Limpiar elementos existentes
	        this.addElement("<Seleccione un medicamento>"); 
	        for (Medicamento medicamento : medicamentos) {
	            this.addElement(medicamento.getNomComun()); // Agregar el nombre común del medicamento
	        }
	    }
}