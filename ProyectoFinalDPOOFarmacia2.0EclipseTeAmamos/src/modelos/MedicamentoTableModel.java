package modelos;

import Logica.Medicamento;


public class MedicamentoTableModel extends ModeloPrincipalTableModel<Medicamento> 
{

	private static final long serialVersionUID = 1L;

	public MedicamentoTableModel() 
	{
		super(new String[] 
		{
				"Nombre Común", 
				"Nombre Científico", 
				"Presentación",
				"Precio", 
				"Tipo", 
				"Fortaleza", 
				"Temperatura Almacén",
				"Cantidad Existente", 
				"Fecha Producción", 
				"Fecha Vencimiento"
		});
	}

	public void adicionar(Medicamento med) 
	{
		Object[] fila = 
			{
				med.getNomComun(),
				med.getNomCientifico(),
				med.getPresentacion(),
				String.format("%.2f", med.getPrecio()),
				med.getTipo(),
				med.getFortalezaDelMed(),
				String.format("%.1f°C", med.getTempDeAlmac()),
				med.getCantExis(),
				med.getFechaDeProd(),
				med.getFechaDeVenc()
			};
		this.addRow(fila);
	}


}