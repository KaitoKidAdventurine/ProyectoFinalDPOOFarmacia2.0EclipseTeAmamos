package modelos;

import java.util.List;

import Logica.Farmacia;

public class PacientesComboBoxModel extends ModeloPrincipalComboBoxModel{

	private static final long serialVersionUID = 1L;
	
	
	public PacientesComboBoxModel() {
		super(null);
		// TODO Auto-generated constructor stub
	}



	// Método para cargar datos
    public void cargarDatos(List<String> pacientes) {
        this.removeAllElements(); // Limpiar elementos existentes
        this.addElement("<Seleccione un paciente>"); 
        for (String paciente : pacientes) {
            this.addElement(paciente); // Agregar cada paciente
        }
    }

	@Override
	protected void inicializar() {
		// TODO Auto-generated method stub
		
	}

}
