package modelos;

import Logica.Medicamento;

import Logica.Farmacia;

import javax.swing.table.DefaultTableModel;

import java.text.SimpleDateFormat;
import java.util.List;




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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

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
				sdf.format(med.getFechaDeProd()),
				sdf.format(med.getFechaDeVenc())
			};
		this.addRow(fila);
	}

	public void limpiarTabla() 
	{
		int rowCount = getRowCount();
		for (int i = rowCount - 1; i >= 0; i--) 
		{
			removeRow(i);
		}
	}

}