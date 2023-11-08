package co.edu.uniquindio.poo.torneodeportivo;
import static co.edu.uniquindio.poo.util.AssertionUtil.ASSERTION;

public class Resultado {
    private int puntosEquipoLocal;
    private int puntosEquipoVisitante;

    public Resultado(int puntosEquipoLocal, int puntosEquipoVisitante) {

        ASSERTION.assertion( puntosEquipoLocal != 0 , "El nombre del lugar es requerido");
        ASSERTION.assertion( puntosEquipoVisitante != 0 , "El nombre del lugar es requerido");

        this.puntosEquipoLocal = puntosEquipoLocal;
        this.puntosEquipoVisitante = puntosEquipoVisitante;
    }

    public String calcularResultado() {
        if (puntosEquipoLocal > puntosEquipoVisitante) {
            return "victoria";
        } else if (puntosEquipoLocal == puntosEquipoVisitante) {
            return "empate";
        } else {
            return "derrota";
        }
    }
}
