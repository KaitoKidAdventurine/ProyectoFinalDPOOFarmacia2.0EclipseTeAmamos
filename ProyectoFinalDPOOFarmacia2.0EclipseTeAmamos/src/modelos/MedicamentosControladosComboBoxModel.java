package modelos;

import java.util.List;

public class MedicamentosControladosComboBoxModel extends ModeloPrincipalComboBoxModel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MedicamentosControladosComboBoxModel(List<String> listaMedicamentosControlados) {
        super(listaMedicamentosControlados); // Llama al constructor del padre
    }

    @Override
    protected void inicializar() {
        this.addElement("<Seleccione un medicamento>");
    }
}