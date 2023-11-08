package co.edu.uniquindio.poo.torneodeportivo;
import java.time.LocalDateTime;
import static co.edu.uniquindio.poo.util.AssertionUtil.ASSERTION;


public class Enfrentamiento {

    private String nombreLugar;
    private String ubicacionLugar;
    private LocalDateTime fechayHoraEnfrentamiento;
    private Equipo equipo;
    private Juez juez;
    private Resultado obtenerResultado;

    public Enfrentamiento(String nombreLugar, String ubicacionLugar, LocalDateTime fechayHoraEnfrentamiento,
            Equipo equipo, Juez juez, Resultado obtenerResultado) {

            ASSERTION.assertion( nombreLugar != null && !nombreLugar.isBlank() , "El nombre del lugar es requerido");
            ASSERTION.assertion( ubicacionLugar!= null && !ubicacionLugar.isBlank() , "El nombre es requerido");
            ASSERTION.assertion( fechayHoraEnfrentamiento != null , "El nombre es requerido");
            ASSERTION.assertion( equipo != null );
            ASSERTION.assertion( juez != null );
        

            this.nombreLugar= nombreLugar;     
            this.ubicacionLugar= ubicacionLugar;
            this.fechayHoraEnfrentamiento= fechayHoraEnfrentamiento;
            this.equipo= equipo;
            this.juez= juez;

                
    }

    public String estadoEnfrentamiento(){
        LocalDateTime fechaHoraActualesEnfrentamiento = LocalDateTime.now();
        String estado;
        if (fechaHoraActualesEnfrentamiento.isBefore(fechayHoraEnfrentamiento)){
            estado = "Torneo pendiente";

        if (fechaHoraActualesEnfrentamiento == fechayHoraEnfrentamiento){
            estado= "Torneo en juego";
        }
        else (fecha)
            
        return estado;
    }

    

    

}
 }
