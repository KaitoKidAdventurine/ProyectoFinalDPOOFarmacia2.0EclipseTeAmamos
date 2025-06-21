package Interfaces_Enum;

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
}
