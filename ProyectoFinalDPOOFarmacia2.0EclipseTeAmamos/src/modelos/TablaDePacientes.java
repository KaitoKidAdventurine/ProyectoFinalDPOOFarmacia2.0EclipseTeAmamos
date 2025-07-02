package modelos;

import java.sql.Date;

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
	    if (t == null) 
	    {
	        throw new IllegalArgumentException("El objeto no puede ser nulo");
	    }

	    if (!(t instanceof Paciente)) 
	    {
	        throw new IllegalArgumentException("Solo se pueden agregar objetos de tipo Paciente");
	    }

	    Paciente paciente = (Paciente) t;

	    Object[] fila = 
	    {
	        paciente.getNombre(),
	        paciente.getCi(),
	        paciente.getDireccion(),
	        formatDate(paciente.getFechaNacimiento()), // Usa formato bonito
	        String.valueOf(paciente.getGenero()),
	        (paciente.getNucleo() != null ? paciente.getNucleo().getId() : "Sin núcleo"),
	        paciente.obtenerTarjetones().size(),
	        paciente.esControlado() ? "Sí" : "No"
	    };

	    this.addRow(fila);
	}

	// Método auxiliar para formatear fechas
	private String formatDate(java.util.Date date) 
	{
	    if (date == null) return "N/A";
	    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy/MM/dd");
	    return sdf.format(date);
	}
}
