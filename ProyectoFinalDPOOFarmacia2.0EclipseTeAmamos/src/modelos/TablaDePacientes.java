package modelos;

import javax.swing.table.DefaultTableModel;

import Logica.Paciente;

public class TablaDePacientes extends ModeloPrincipalTableModel
{
	private static final long serialVersionUID = 1L;

	public TablaDePacientes() 
	{
		super(new String[] 
				{
					"Nombre", 
					"Carnet", 
					"Dirección",
					"Fecha de Nacimiento", 
					"Género", 
					"Núcleo Familiar",
					"Tarjetones", 
					"Es Controlado"
				});
	}

	public void adicionarPaciente(Paciente paciente) 
	{
		Object[] fila = 
			{
				paciente.getNombre(),
				paciente.getCi(),
				paciente.getDireccion(),
				paciente.getFechaNacimiento(),
				String.valueOf(paciente.getGenero()),
				(paciente.getNucleo() != null ? paciente.getNucleo().getId() : "Sin núcleo"),
				(paciente.obtenerTarjetones() != null ? paciente.obtenerTarjetones().size() : 0),
				paciente.esControlado() ? "Sí" : "No"
			};
		this.addRow(fila);
	}

	@Override
	public void adicionar(Object t) 
	{
		// no se usa aqui
		
	}
}
