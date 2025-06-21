package Interfaces_Enum;
import java.sql.Date;
import java.util.ArrayList;

import LogicaUtiles.VentaDeMedicamentos;
import Logica.Tarjeton;
import LogicaUtiles.Porcentaje;

public interface Reportes 
{
	public ArrayList<VentaDeMedicamentos> medicamentosMasVendidos();
	public long cantDeAlmohadillasNecesarias();
	public Porcentaje comparacionDeVentasMensuales();
	public ArrayList<Tarjeton> registroDeIncumplimiento();

}
