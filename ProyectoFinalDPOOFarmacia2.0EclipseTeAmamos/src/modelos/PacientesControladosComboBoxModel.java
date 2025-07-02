package modelos;

import java.util.List;

public class PacientesControladosComboBoxModel extends ModeloPrincipalComboBoxModel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PacientesControladosComboBoxModel(List<String> listaPacientesControlados) {
        super(listaPacientesControlados);
    }

    @Override
    protected void inicializar() {
        this.addElement("<Seleccione un paciente>");
    }
}