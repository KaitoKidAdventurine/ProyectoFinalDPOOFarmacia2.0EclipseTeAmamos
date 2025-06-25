package Logica;
import java.util.Date;

public class VentaControlada extends Venta 
{
    public VentaControlada(Date fechaVenta, double importeTotal) 
    {
        super((java.util.Date) fechaVenta, importeTotal);
    }
}

