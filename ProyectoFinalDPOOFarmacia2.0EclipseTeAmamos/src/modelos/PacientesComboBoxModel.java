package modelos;

import java.util.List;

public class PacientesComboBoxModel extends ModeloPrincipalComboBoxModel{
	private static final long serialVersionUID = 1L;

	protected PacientesComboBoxModel(List<String> pacientes) {
		super(pacientes);
	}

	@Override
	protected void inicializar() {
		this.addElement("<Pacientes>");
	}

}
