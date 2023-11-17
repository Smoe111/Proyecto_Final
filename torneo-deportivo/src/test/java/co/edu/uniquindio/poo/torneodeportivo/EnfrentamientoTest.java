package co.edu.uniquindio.poo.torneodeportivo;

import static co.edu.uniquindio.poo.util.AssertionUtil.ASSERTION;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import co.edu.uniquindio.poo.util.AssertionUtil;



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

        Enfrentamiento enfrentamiento= new Enfrentamiento("Universidad del Quindío", "Carrera 49 #50-06", LocalDateTime.of(2023,12, 2,4, 30), jueces, equipoLocal, equipoVisitante, 0, 2);

        var estado= enfrentamiento.estadoEnfrentamiento(fechaHoraActual);

        assertEquals("PENDIENTE", estado);

        LOG.info("Final de prueba validar estado enfrentamiento pendiente");

    }

    @Test

    public void validarEstadoEnfrentamientoFinalizado(){

        LOG.info("Inicio prueba validar enfrentamiento finalizado");

        LocalDateTime fechaHoraActual= LocalDateTime.of(2023,11,2,5,30);
        Collection<Juez> jueces= new ArrayList<>();
        Persona representante1= new Persona("Sara","Acosta","sara@gmail.com","12345");
        var representante2= new Persona("Emilio","Echeverri","emilio@gmail.com","12345");
        Equipo equipoLocal= new Equipo("Equipo Local", representante1);
        var equipoVisitante= new Equipo("Equipo visitante", representante2);

        Enfrentamiento enfrentamiento= new Enfrentamiento("Universidad del Quindío", "Carrera 49 #50-06", LocalDateTime.of(2023,11, 2,4, 30), jueces, equipoLocal, equipoVisitante, 0, 2);
        var resultado= enfrentamiento.calcularResultado();
        var estado= enfrentamiento.estadoEnfrentamiento(fechaHoraActual);

        assertEquals("Victoria equipo visitante", resultado);
        assertEquals("FINALIZADO", estado);

        LOG.info("Final de prueba validar estado enfrentamiento finalizado");

    }

    @Test

    public void validarEstadoEnfrentamientoAplazado(){

        LOG.info("Inicia prueba validar estado enfrentamiento aplazado");

        LocalDateTime fechaHoraActual= LocalDateTime.of(2023,11,2,5,30);
        Collection<Juez> jueces= new ArrayList<>();
        Persona representante1= new Persona("Sara","Acosta","sara@gmail.com","12345");
        var representante2= new Persona("Emilio","Echeverri","emilio@gmail.com","12345");
        Equipo equipoLocal= new Equipo("Equipo Local", representante1);
        var equipoVisitante= new Equipo("Equipo visitante", representante2);

        Enfrentamiento enfrentamiento= new Enfrentamiento("Universidad del Quindío", "Carrera 49 #50-06", LocalDateTime.of(2023,11, 2,4, 30), jueces, equipoLocal, equipoVisitante);

 
        LOG.info("Final de prueba validar estado enfrentamiento aplazado");
    }

    @Test

    public void validarEstadoEnfrentamientoDerrota(){

        LocalDateTime fechaHoraActual= LocalDateTime.of(2023,11,2,5,30);
        Collection<Juez> jueces= new ArrayList<>();
        Persona representante1= new Persona("Sara","Acosta","sara@gmail.com","12345");
        var representante2= new Persona("Emilio","Echeverri","emilio@gmail.com","12345");
        Equipo equipoLocal= new Equipo("Equipo Local", representante1);
        var equipoVisitante= new Equipo("Equipo visitante", representante2);

        Enfrentamiento enfrentamiento= new Enfrentamiento("Universidad del Quindío", "Carrera 49 #50-06", LocalDateTime.of(2023,11, 2,4, 30), jueces, equipoLocal, equipoVisitante, 0, 2);
        var resultado= enfrentamiento.calcularResultado();
        var estado= enfrentamiento.estadoEnfrentamiento (fechaHoraActual);

        assertEquals("Derrota equippo local", resultado);
        assertEquals("FINALIZADO", estado);

    }


    @Test 

    public void validarCalcularResultado(){

        LOG.info("Inicio de prueba validar calcular resultado");

        Collection<Juez> jueces= new ArrayList<>();
        Persona representante1= new Persona("Sara","Acosta","sara@gmail.com","12345");
        var representante2= new Persona("Emilio","Echeverri","emilio@gmail.com","12345");
        Equipo equipoLocal= new Equipo("Equipo Local", representante1);
        var equipoVisitante= new Equipo("Equipo visitante", representante2);

        Enfrentamiento enfrentamiento= new Enfrentamiento("Universidad del Quindío", "Carrera 49 #50-06", LocalDateTime.of(2023,12, 2,4, 30), jueces, equipoLocal, equipoVisitante, 0, 2);


        assertEquals("Victoria equipo visitante", enfrentamiento.calcularResultado());

        LOG.info("Final de prueba validar calcular resultado");
    }



    
}

