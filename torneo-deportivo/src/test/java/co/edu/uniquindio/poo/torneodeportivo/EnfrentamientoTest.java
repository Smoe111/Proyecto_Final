package co.edu.uniquindio.poo.torneodeportivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;



public class EnfrentamientoTest{

    private static final Logger LOG = Logger.getLogger(TorneoTest.class.getName());

    @Test
    public void validarEstadoEnfrentamientoPendiente(){

        LOG.info("Inicio de prueba validar estado enfrentamiento pendiente");

        LocalDateTime fechaHoraActual= LocalDateTime.of(2023,12,2,3,0);
        Collection<Juez> jueces= new ArrayList<>();
        Persona representante1= new Persona("Sara","Acosta","sara@gmail.com","12345");
        var representante2= new Persona("Emilio","Echeverri","emilio@gmail.com","12345");
        Equipo equipoLocal= new Equipo("Equipo Local", representante1);
        var equipoVisitante= new Equipo("Equipo visitante", representante2);

        Enfrentamiento enfrentamiento= new Enfrentamiento("Universidad del Quindio", "Carrera 49 #50-06", LocalDateTime.of(2023,12, 2,4, 30), jueces, equipoLocal, equipoVisitante, 0, 2);

        var estado= enfrentamiento.estadoEnfrentamiento(fechaHoraActual);

        assertEquals("PENDIENTE", estado);

        LOG.info("Final de prueba validar estado enfrentamiento pendiente");

    }

    @Test

    public void validarEstadoEnfrentamientoFinalizado(){

        LOG.info("Inicio prueba validar enfrentamiento finalizado");

        LocalDateTime fechaHoraActual= LocalDateTime.of(2023,11,2,10,30);
        Collection<Juez> jueces= new ArrayList<>();
        Persona representante1= new Persona("Sara","Acosta","sara@gmail.com","12345");
        var representante2= new Persona("Emilio","Echeverri","emilio@gmail.com","12345");
        Equipo equipoLocal= new Equipo("Equipo Local", representante1);
        var equipoVisitante= new Equipo("Equipo visitante", representante2);

        Enfrentamiento enfrentamiento= new Enfrentamiento("Universidad del Quindio", "Carrera 49 #50-06", LocalDateTime.of(2023,11, 2,5, 30), jueces, equipoLocal, equipoVisitante, 0, 2);
        var resultado= enfrentamiento.calcularResultado();
        var estado= enfrentamiento.estadoEnfrentamiento(fechaHoraActual);

        assertEquals("Victoria", resultado.get(equipoVisitante));
        assertEquals("Derrota", resultado.get(equipoLocal));
        assertEquals("FINALIZADO", estado);

        LOG.info("Final de prueba validar estado enfrentamiento finalizado");

    }

    @Test

    public void validarEstadoEnfrentamientoAplazado(){

        LOG.info("Inicio prueba validar enfrentamiento Aplazado");

        LocalDateTime fechaHoraActual= LocalDateTime.of(2023,11,2,7,30);
        Collection<Juez> jueces= new ArrayList<>();
        Persona representante1= new Persona("Sara","Acosta","sara@gmail.com","12345");
        var representante2= new Persona("Emilio","Echeverri","emilio@gmail.com","12345");
        Equipo equipoLocal= new Equipo("Equipo Local", representante1);
        var equipoVisitante= new Equipo("Equipo visitante", representante2);

        Enfrentamiento enfrentamiento= new Enfrentamiento("Universidad del Quindio", "Carrera 49 #50-06", LocalDateTime.of(2023,11, 2,4, 30), jueces, equipoLocal, equipoVisitante, 0, 0);
        var resultado= enfrentamiento.calcularResultado();
        var estado = enfrentamiento.definirEstadoEnfretamientonAplazado();

        assertEquals("APLAZADO", estado);

        LOG.info("Final de prueba validar estado enfrentamiento aplazado");

    }

    

    @Test
    public void testEstadoEnfrentamientoEnJuego() {
        LOG.info("Inicio prueba para verificar estado de enfrentamiento en juego ");
        LocalDateTime fechaHoraActual = LocalDateTime.of(2023, 11, 2, 14, 0);

        // Crear un enfrentamiento para la prueba con fecha y hora igual al actual
        Enfrentamiento enfrentamiento = new Enfrentamiento("Estadio", "Ubicacion", LocalDateTime.of(2023, 11, 2, 14, 0),
                Collections.emptyList(), new Equipo("Local", new Persona("Representante", "Local", "local@email.com", "123")),
                new Equipo("Visitante", new Persona("Representante", "Visitante", "visitante@email.com", "456")), 0, 0);

        // Ejecutar el método que se va a probar
        var estado = enfrentamiento.estadoEnfrentamiento(fechaHoraActual);
        assertEquals("EN_JUEGO", estado);
        LOG.info("Fin prueba para verificar estado de enfrentamiento en juego ");
    }

    @Test

    public void validarCalcularResultadoDerrotaEquipoLocal(){

        LOG.info("Inicio prueba para verificar calcular resultado enfrentamiento derrota equipo local");
        Collection<Juez> jueces= new ArrayList<>();
        Persona representante1= new Persona("Sara","Acosta","sara@gmail.com","12345");
        var representante2= new Persona("Emilio","Echeverri","emilio@gmail.com","12345");
        Equipo equipoLocal= new Equipo("Equipo Local", representante1);
        var equipoVisitante= new Equipo("Equipo visitante", representante2);

        Enfrentamiento enfrentamiento= new Enfrentamiento("Universidad del Quindio", "Carrera 49 #50-06", LocalDateTime.of(2023,11, 2,4, 30), jueces, equipoLocal, equipoVisitante, 0, 2);
        var resultado= enfrentamiento.calcularResultado();
        

        assertEquals("Victoria", resultado.get(equipoVisitante));
        assertEquals("Derrota", resultado.get(equipoLocal));
        LOG.info("Fin prueba para verificar calcular resultado enfrentamiento derrota equipo local ");
    }


    @Test 

    public void validarCalcularResultadoVictoriaEquipoLocal(){

        LOG.info("Inicio de prueba validar calcular resultado victoria equipo local");
        LocalDateTime fechaHoraActual= LocalDateTime.of(2023,11,2,5,30);
        Collection<Juez> jueces= new ArrayList<>();
        Persona representante1= new Persona("Sara","Acosta","sara@gmail.com","12345");
        var representante2= new Persona("Emilio","Echeverri","emilio@gmail.com","12345");
        Equipo equipoLocal= new Equipo("Equipo Local", representante1);
        var equipoVisitante= new Equipo("Equipo visitante", representante2);

        Enfrentamiento enfrentamiento= new Enfrentamiento("Universidad del Quindío", "Carrera 49 #50-06", fechaHoraActual, jueces, equipoLocal, equipoVisitante, 3, 2);

        var resultado = enfrentamiento.calcularResultado();

        assertEquals("Derrota", resultado.get(equipoVisitante));
        assertEquals("Victoria", resultado.get(equipoLocal));

        LOG.info("Final de prueba validar calcular resultado victoria equipo local");
    }
 
}

