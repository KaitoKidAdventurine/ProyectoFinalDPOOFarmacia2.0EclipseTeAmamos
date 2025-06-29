package modelos;

import javax.swing.table.DefaultTableModel;

public class AlmohadillasNecesariasTableModel extends DefaultTableModel {

    private static final long serialVersionUID = 1L;
    private long cantidadNecesaria;
    private long stockActual;

    
    public AlmohadillasNecesariasTableModel(long cantidadNecesaria, long stockActual) 
    {
    	
    	super(new Object[][] 
    	{
            {"Cantidad Necesaria", cantidadNecesaria},
            {"Cantidad Actual: ", stockActual}
        }, 
        new String[] {"Concepto", "Cantidad"});
        
        this.cantidadNecesaria = cantidadNecesaria;
        this.stockActual = stockActual;
    }

    @Override
    public boolean isCellEditable(int row, int column) 
    {
        return false;
    }
}
