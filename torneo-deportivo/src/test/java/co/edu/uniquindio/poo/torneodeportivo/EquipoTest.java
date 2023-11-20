/**
 * Clase para probar el registro de los equipos
 * @author Área de programación UQ
 * @since 2023-08
 * 
 * Licencia GNU/GPL V3.0 (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE) 
 */
package co.edu.uniquindio.poo.torneodeportivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EquipoTest {
    /**
     * Instancia para el manejo de logs
     */
    private static final Logger LOG = Logger.getLogger(EquipoTest.class.getName());
    
    /**
     * Verificar que sea posible registrar un equipo en el torneo 
     * 
     */
    @Test
    public void registrarEquipo() {
        LOG.info("Inicio de prueba registrarEquipo...");
        // Almacenar los datos de prueba Torneo{Copa Mundo\|fechaActual+ 1mes\| fechaActual - 15 días\|fechaActual+15 días\|24\|0\|0\|LOCAL|GRUPAL}  Equipo{Uniquindio} Representante{Robinson,Pulgarin,rpulgarin@email.com,6067359300}

        
        Torneo torneo = new Torneo("Copa Mundo", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(15), LocalDate.now().plusDays(15), (byte)24, (byte)0, 0,TipoTorneo.LOCAL,CaracterTorneo.GRUPAL, GeneroTorneo.MIXTO);

        var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");

        var equipo = new Equipo("Uniquindio", representante);

        torneo.registrarParticipante(equipo);

        // Recuperación y verificación de datos
        assertTrue(torneo.getParticipantes().contains(equipo));
        assertEquals(1, torneo.getParticipantes().size());
        LOG.info("Fin de prueba registrarEquipo...");
    }

    /**
     * Verificar que la clase Torneo valide que no se ingresen equipos con nombre repetido
     * 
     */
    @Test
    public void nombreEquipoRepetido() {
        LOG.info("Inicio de prueba nombreEquipoRepetido...");
        // Almacenar los datos de prueba Torneo{Copa Mundo\|fechaActual+ 1mes\| fechaActual - 15 días\|fechaActual+15 días\|24\|0\|0\|LOCAL|GRUPAL}  Equipo{Uniquindio} Representante{Robinson,Pulgarin,rpulgarin@email.com,6067359300}

        
        Torneo torneo = new Torneo("Copa Mundo", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(15), LocalDate.now().plusDays(15), (byte)24, (byte)0, 0,TipoTorneo.LOCAL,CaracterTorneo.GRUPAL, GeneroTorneo.MIXTO);

        var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");

        var equipo = new Equipo("Uniquindio", representante);
        var equipo2 = new Equipo("Uniquindio", representante);
        torneo.registrarParticipante(equipo);

        assertThrows(Throwable.class, ()-> torneo.registrarParticipante(equipo2));
        
        LOG.info("Fin de prueba nombreEquipoRepetido...");
    }

    /**
     * Verificar que la clase Torneo valide que no se ingresen equipos cuando las inscripciones ya cerraron
     * 
     */
    @Test
    public void inscripcionCerrada() {
        LOG.info("Inicio de prueba inscripcionCerrada...");
        // Almacenar los datos de prueba Torneo{Copa Mundo\|fechaActual+ 1mes\| fechaActual - 15 días\|fechaActual-1 días\|24\|0\|0\|LOCAL|GRUPAL}  Equipo{Uniquindio} Representante{Robinson,Pulgarin,rpulgarin@email.com,6067359300}
        
        Torneo torneo = new Torneo("Copa Mundo", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(15), LocalDate.now().minusDays(1), (byte)24, (byte)0, 0,TipoTorneo.LOCAL,CaracterTorneo.GRUPAL, GeneroTorneo.MIXTO);

        var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");

        var equipo = new Equipo("Uniquindio", representante);

        assertThrows(Throwable.class, ()-> torneo.registrarParticipante(equipo));
        
        LOG.info("Fin de prueba inscripcionCerrada...");
    }

    @Test
    public void testRegistrarResultadoEquipo() {

        LOG.info("Inicio de prueba registrar resultado equipo");

        var representante1 = new Persona("William", "Pulgarin", "rpulgarin@email.com", "6067359300");
        var representante2 = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");

        Equipo equipo1 = new Equipo("Tigres", representante1);
        Equipo equipo2 = new Equipo("Panteras", representante2);

        Juez juez = new Juez("Sara", "Acosta", "SvAb@gmail.com", "3145290574", "123456");
        Enfrentamiento enfrentamiento = new Enfrentamiento("estadio central", "centro armenia",
                LocalDateTime.of(2024, 5, 28, 18, 0), List.of(juez), equipo1, equipo2, 3, 2);

        
        equipo1.registrarResultadoEquipo(enfrentamiento, equipo1);

        Assertions.assertEquals(1, equipo1.getVictorias());
        Assertions.assertEquals(0, equipo1.getEmpates());
        Assertions.assertEquals(0, equipo1.getDerrotas());

        Collection<Enfrentamiento> enfrentamientosEquipo = equipo1.getEnfrentamientos();
        Assertions.assertEquals(1, enfrentamientosEquipo.size());

        LOG.info("Fin de prueba registrar resultado equipo");
       
    }

    

}
