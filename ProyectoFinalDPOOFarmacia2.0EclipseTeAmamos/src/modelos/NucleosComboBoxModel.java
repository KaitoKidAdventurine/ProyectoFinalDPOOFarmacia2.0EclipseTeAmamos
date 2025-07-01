package modelos;

import java.util.List;

import Logica.Farmacia;
import Logica.NucleoFamiliar;

public class NucleosComboBoxModel extends ModeloPrincipalComboBoxModel{
	private static final long serialVersionUID = 1L;
	
	
	public NucleosComboBoxModel() {
		super(null);
		// TODO Auto-generated constructor stub
	}

	// Método para cargar datos
    public void cargarDatos() {
        this.removeAllElements(); // Limpiar elementos existentes
        this.addElement("<Seleccione un núcleo>"); 
        // Obtener la lista de núcleos familiares desde la instancia de Farmacia
        for (NucleoFamiliar nucleo : Farmacia.obtenerInstancia().getNucleos()) {
            // Formatear el texto que aparecerá en el combo
            String item = nucleo.getId() + " - " + nucleo.getDireccion();
            addElement(item);
        }
    }

	@Override
	protected void inicializar() {
		// TODO Auto-generated method stub
		
	}

}
