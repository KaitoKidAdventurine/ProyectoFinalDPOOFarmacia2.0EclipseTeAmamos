package Interfaces_Enum;

<<<<<<< HEAD
public enum Presentacion {
	TABLETAS("Tabletas"),
    CAPSULAS("Cápsulas"),
    JARABE("Jarabe"),
    CREMA("Crema"),
    POMADA("Pomada"),
    GEL("Gel"),
    INYECTABLE("Inyectable"),
    SUSPENSION("Suspensión"),
    POLVO("Polvo"),
    GRAGEA("Gragea"),
    SUPOSITORIO("Supositorio"),
    SOLUCION("Solución"),
    UNGUENTO("Ungüento"),
    AEROSOL("Aerosol"),
    PARCHE("Parche"),
    GOTAS("Gotas"),
    COLIRIO("Colirio"),
    SHAMPOO("Shampoo medicinal"),
    LOCOION("Loción"),
    INHALADOR("Inhalador");

    private final String descripcion;

    Presentacion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }
=======
public enum Presentacion 
{
	
>>>>>>> 49593027f33470da99ba69df5e782da64f41882e
}
