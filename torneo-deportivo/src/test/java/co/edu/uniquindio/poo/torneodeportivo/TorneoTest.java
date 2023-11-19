/**
 * Clase para probar el funcionamiento del Torneo
 * @author Área de programación UQ
 * @since 2023-08
 * 
 * Licencia GNU/GPL V3.0 (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE) 
 */
package co.edu.uniquindio.poo.torneodeportivo;

import static co.edu.uniquindio.poo.util.AssertionUtil.ASSERTION;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import co.edu.uniquindio.poo.util.AssertionUtil;

public class TorneoTest {
    /**
     * Instancia para el manejo de logs
     */
    private static final Logger LOG = Logger.getLogger(TorneoTest.class.getName());
    
    /**
     * Verificar que la clase Torneo almacene y recupere los datos 
     * 
     */
    @Test
    public void datosCompletos() {
        LOG.info("Inicio de prueba datos completos...");
        // Almacenar los datos de prueba Copa Mundo|2023-10-01|2023-08-01|2023-09-15|24|0|0|LOCAL|GRUPAL
        Torneo torneo = new Torneo("Copa Mundo", LocalDate.of(2023, 10, 1), LocalDate.of(2023, 8, 1), LocalDate.of(2023, 9, 15), (byte)24, (byte)0, 0,TipoTorneo.LOCAL,CaracterTorneo.GRUPAL , GeneroTorneo.MASCULINO);

        // Recuperación y verificación de datos
        assertEquals("Copa Mundo",torneo.getNombre());
        assertEquals(LocalDate.of(2023, 10, 1),torneo.getFechaInicio());
        assertEquals(LocalDate.of(2023, 8, 1),torneo.getFechaInicioInscripciones());
        assertEquals(LocalDate.of(2023, 9, 15),torneo.getFechaCierreInscripciones());
        assertEquals((byte)24,torneo.getNumeroParticipantes());
        assertEquals((byte)0,torneo.getLimiteEdad());
        assertEquals(0,torneo.getValorInscripcion());
        assertEquals(TipoTorneo.LOCAL,torneo.getTipoTorneo());
        assertEquals(CaracterTorneo.GRUPAL, torneo.getCaracter());
        assertEquals(GeneroTorneo.MASCULINO, torneo.getGenero());
        LOG.info("Fin de prueba datos completos...");
    }

    /**
     * Verificar que la clase Torneo valide que se ingrese los datos
     * 
     */
    @Test
    public void datosNulos() {
        LOG.info("Inicio de prueba datos nulos...");
        // Almacenar los datos de prueba null|null|null|null|24|0|0|null|LOCAL|GRUPAL
        assertThrows(Throwable.class, ()-> new Torneo(null, null, null, null, (byte)24, (byte)0, 0,TipoTorneo.LOCAL,CaracterTorneo.GRUPAL, GeneroTorneo.FEMENINO));
        
        
        LOG.info("Fin de prueba datos nulos...");
    }

    /**
     * Verificar que la clase Torneo valide que el ingreso de número de participantes negativo 
     * 
     */
    @Test
    public void NumeroParticipantesNegativos() {
        LOG.info("Inicio de prueba número de participantes negativo...");
        // Almacenar los datos de prueba Copa Mundo|2023-10-01|2023-08-01|2023-09-15|-24|0|0|LOCAL|GRUPAL
        assertThrows(Throwable.class, ()-> new Torneo("Copa Mundo", LocalDate.of(2023, 10, 1), LocalDate.of(2023, 8, 01), LocalDate.of(2023, 10, 15), (byte)-24, (byte)0, 0,TipoTorneo.LOCAL,CaracterTorneo.GRUPAL, GeneroTorneo.MASCULINO));
        
        LOG.info("Fin de prueba  número de participantes negativo...");
    }

    /**
     * Verificar que la clase Torneo valide que el ingreso de limites de edades negativo 
     * 
     */
    @Test
    public void limiteEdadesNegativo() {
        LOG.info("Inicio de prueba limites de edades negativo...");
        // Almacenar los datos de prueba Copa Mundo|2023-10-01|2023-08-01|2023-09-15|24|-1|0|LOCAL|GRUPAL
        assertThrows(Throwable.class, ()-> new Torneo("Copa Mundo", LocalDate.of(2023, 10, 1), LocalDate.of(2023, 8, 01), LocalDate.of(2023, 10, 15), (byte)24, (byte)-1, 0,TipoTorneo.LOCAL,CaracterTorneo.GRUPAL , GeneroTorneo.MIXTO));
        
        LOG.info("Fin de prueba  limites de edades negativo...");
    }

    /**
     * Verificar que la clase Torneo valide que el ingreso de valor de inscripción negativa
     * 
     */
    @Test
    public void inscripcionNegativa() {
        LOG.info("Inicio de prueba inscripción negativa...");
        // Almacenar los datos de prueba Copa Mundo|2023-10-01|2023-08-01|2023-09-15|24|0|-1|LOCAL|GRUPAL
        assertThrows(Throwable.class, ()-> new Torneo("Copa Mundo", LocalDate.of(2023, 10, 1), LocalDate.of(2023, 8, 01), LocalDate.of(2023, 10, 15), (byte)24, (byte)0, -1,TipoTorneo.LOCAL,CaracterTorneo.GRUPAL , GeneroTorneo.MASCULINO));
        
        LOG.info("Fin de prueba inscripción negativa...");
    }

    /**
     * Verificar que la clase Torneo valide que el ingreso de inscripciones posteriores a la 
     * fecha de inicio del torneo
     * 
     */
    @Test
    public void inscripcionTardia() {
        LOG.info("Inicio de prueba inscripción tardia...");
        // Almacenar los datos de prueba Copa Mundo|2023-10-01|2023-11-01|2023-11-15|24|0|0|LOCAL
        assertThrows(Throwable.class, ()-> new Torneo("Copa Mundo", LocalDate.of(2023, 10, 1), LocalDate.of(2023, 11, 01), LocalDate.of(2023, 11, 15), (byte)24, (byte)0, 0,TipoTorneo.LOCAL,CaracterTorneo.GRUPAL, GeneroTorneo.MASCULINO));
        
        LOG.info("Fin de prueba inscripción tardia...");
    }

    /**
     * Verificar que la clase Torneo valide que el ingreso de inicio inscripciones posteriores a 
     * la fecha de cierre de inscripciones
     * 
     */
    @Test
    public void cierreInscripcionAnteriorInicio() {
        LOG.info("Inicio de prueba Cierre inscripción anterior al inicio...");
        // Almacenar los datos de prueba Copa Mundo|2023-10-01|2023-11-01|2023-11-15|24|0|0|LOCAL|GRUPAL
        assertThrows(Throwable.class, ()-> new Torneo("Copa Mundo", LocalDate.of(2023, 10, 1), LocalDate.of(2023, 8, 15), LocalDate.of(2023, 8, 1), (byte)24, (byte)0, 0,TipoTorneo.LOCAL,CaracterTorneo.GRUPAL, GeneroTorneo.FEMENINO));
        
        LOG.info("Fin de prueba Cierre inscripción anterior al inicio...");
    }
   
    @Test
    public void obtenerEnfrentamientosDeEquipo(){
        LOG.info("Iniciando prueba para verificar la lista de equipos con enfretamientos");

        Torneo torneo = new Torneo("Copa Mundo", LocalDate.of(2024, 2, 1), LocalDate.of(2023, 11, 1), LocalDate.of(2024, 1, 5), (byte) 24, (byte) 0, 0, TipoTorneo.LOCAL, CaracterTorneo.GRUPAL, GeneroTorneo.FEMENINO);

        // Crear equipos y registrarlos en el torneo
        var representante1 = new Persona("William", "Pulgarin", "rpulgarin@email.com", "6067359300");
        var representante2 = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");

        Equipo equipo1 = new Equipo("Tigres", representante1);
        Equipo equipo2 = new Equipo("Panteras", representante2);

        torneo.registrarParticipante(equipo1);
        torneo.registrarParticipante(equipo2);

        // Realizar enfrentamientos (aquí deberías tener lógica específica de tu torneo para realizar enfrentamientos)
        Enfrentamiento enfrentamiento1 = new Enfrentamiento("Estadio Centro", "Centro Armenia", equipo1, equipo2, 5, 3);
        Enfrentamiento enfrentamiento2 = new Enfrentamiento("Parque del Cafe", "Montenegro Quindio", equipo1, equipo2, 3, 4);

        torneo.registrarEnfrentamiento(enfrentamiento1);
        torneo.registrarEnfrentamiento(enfrentamiento2);

        

    }
    
    @Test
public void testListadoEstadisticasEquipos() {
    LOG.info("Iniciando prueba para verificar la lista de equipos con estadísticas en orden descendente");

    // Crear un torneo para la prueba
    Torneo torneo = new Torneo("Copa Mundo", LocalDate.of(2024, 2, 1), LocalDate.of(2023, 11, 1), LocalDate.of(2024, 1, 5), (byte) 24, (byte) 0, 0, TipoTorneo.LOCAL, CaracterTorneo.GRUPAL, GeneroTorneo.FEMENINO);

    // Crear equipos y registrarlos en el torneo
    var representante1 = new Persona("William", "Pulgarin", "rpulgarin@email.com", "6067359300");
    var representante2 = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");

    Equipo equipo1 = new Equipo("Tigres", representante1);
    Equipo equipo2 = new Equipo("Panteras", representante2);

    torneo.registrarParticipante(equipo1);
    torneo.registrarParticipante(equipo2);

    // Realizar enfrentamientos (aquí deberías tener lógica específica de tu torneo para realizar enfrentamientos)
    Enfrentamiento enfrentamiento1 = new Enfrentamiento("Estadio Centro", "Centro Armenia", equipo1, equipo2, 5, 3);
    Enfrentamiento enfrentamiento2 = new Enfrentamiento("Parque del Cafe", "Montenegro Quindio", equipo1, equipo2, 3, 4);

    torneo.registrarEnfrentamiento(enfrentamiento1);
    torneo.registrarEnfrentamiento(enfrentamiento2);

    // Realizar registros de resultados (esto también depende de la lógica específica de tu torneo)
    equipo1.registrarResultadoEquipo(enfrentamiento1, equipo1);
    equipo2.registrarResultadoEquipo(enfrentamiento2, equipo2);

    // Obtener el listado de estadísticas
    Map<String, String> estadisticas = torneo.listadoEstadisticasEquipos();

    // Realizar aserciones según las expectativas (ajusta según las estadísticas reales esperadas)
    assertEquals("Victorias: 1", estadisticas.get("Panteras - Victorias"));
    assertEquals("Derrotas: 1", estadisticas.get("Panteras - Derrotas"));
    assertEquals("Empates: 0", estadisticas.get("Panteras - Empates"));
    assertEquals("Victorias: 1", estadisticas.get("Tigres - Victorias"));
    assertEquals("Derrotas: 1", estadisticas.get("Tigres - Derrotas"));
    assertEquals("Empates: 0", estadisticas.get("Tigres - Empates"));
}


@Test
    public void testListadoEstadisticasEquipos1() {
        // Crear un objeto Torneo para la prueba
        Torneo torneo = new Torneo("Copa Mundo", LocalDate.of(2024, 2, 1), LocalDate.of(2023, 11, 1), LocalDate.of(2024, 1, 5), (byte) 24, (byte) 0, 0, TipoTorneo.LOCAL, CaracterTorneo.GRUPAL, GeneroTorneo.FEMENINO);

        // Crear equipos y registrarlos en el torneo
        var representante1 = new Persona("William", "Pulgarin", "rpulgarin@email.com", "6067359300");
        var representante2 = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");

        Equipo equipo1 = new Equipo("Tigres", representante1);
        Equipo equipo2 = new Equipo("Panteras", representante2);

        torneo.registrarParticipante(equipo1);
        torneo.registrarParticipante(equipo2);

        // Crear un enfrentamiento ficticio y registrarlo en el torneo
        Enfrentamiento enfrentamiento1 = new Enfrentamiento("Estadio Centro", "Centro Armenia", equipo1, equipo2, 5, 3);

      
        equipo1.registrarResultadoEquipo(enfrentamiento1, equipo1);

        // Llamar al método que queremos probar
        Map<String, String> estadisticasEquipos = torneo.listadoEstadisticasEquipos();

        // Verificar que las estadísticas generadas coinciden con las esperadas
        assertEquals("1", estadisticasEquipos.get("EquipoA - Victorias"));
        assertEquals("0", estadisticasEquipos.get("EquipoA - Derrotas"));
        assertEquals("0", estadisticasEquipos.get("EquipoA - Empates"));
        assertEquals("1", estadisticasEquipos.get("EquipoA - Total Enfrentamientos"));

        assertEquals("0", estadisticasEquipos.get("EquipoB - Victorias"));
        assertEquals("1", estadisticasEquipos.get("EquipoB - Derrotas"));
        assertEquals("0", estadisticasEquipos.get("EquipoB - Empates"));
        assertEquals("1", estadisticasEquipos.get("EquipoB - Total Enfrentamientos"));
    }
}
