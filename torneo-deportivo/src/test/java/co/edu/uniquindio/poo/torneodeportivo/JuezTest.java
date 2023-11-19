package co.edu.uniquindio.poo.torneodeportivo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class JuezTest {
    
    private static final Logger LOG = Logger.getLogger(EquipoTest.class.getName());

    @Test
    public void verificarAgregarEnfretamientoAjuez (){

        var representante1 = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300"); 
        var representante2 = new Persona("William", "Pulgarin", "rpulgarin@email.com", "6067359300"); 

        Equipo equipo1 = new Equipo("Tigres", representante1);
        Equipo equipo2 = new Equipo("Panteras", representante2);
        Juez juez = new Juez("Sara", "Acosta", "SvAb@gmail.com", "3145290574", "123456");
        
        Enfrentamiento enfrentamiento = new Enfrentamiento("estadio central", "centro armenia",  LocalDateTime.of(2024, 5, 28, 18, 0),
        List.of(juez), equipo1,equipo2);

        juez.agregarEnfrentamiento(enfrentamiento);
        Collection<Enfrentamiento> enfrentamientos = juez.getEnfrentamientos();

        assertTrue(enfrentamientos.contains(enfrentamiento));
    }

    @Test
    public void testObtenerEnfrentamientosPorLicenciaJuez() {
    
        // Crear un torneo y registrar el juez
        Torneo torneo = new Torneo("Copa Mundo", LocalDate.of(2024, 2, 1), LocalDate.of(2023, 11, 1), LocalDate.of(2024, 1, 5), (byte)24, (byte)0, 0, TipoTorneo.LOCAL, CaracterTorneo.GRUPAL, GeneroTorneo.FEMENINO);

        var representante1 = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");
        var representante2 = new Persona("William", "Pulgarin", "rpulgarin@email.com", "6067359300");

        Equipo equipo1 = new Equipo("Tigres", representante1);
        Equipo equipo2 = new Equipo("Panteras", representante2);
        Juez juez = new Juez("Sara", "Acosta", "SvAb@gmail.com", "3145290574", "123456");

        // Crear un enfrentamiento para el juez
        Enfrentamiento enfrentamiento = new Enfrentamiento("estadio central", "centro armenia",
                LocalDateTime.of(2024, 5, 28, 18, 0), List.of(juez), equipo1, equipo2);

        torneo.registrarEnfrentamiento(enfrentamiento);

        // método que se va a probar
        List<Enfrentamiento> enfrentamientosJuez = torneo.obtenerEnfrentamientosPorLicenciaJuez(juez.getLicencia());

        // Verificar que se obtuvieron los enfrentamientos correctamente
        Assertions.assertEquals(1, enfrentamientosJuez.size()); // Verifica que haya un enfrentamiento registrado
    }

}
