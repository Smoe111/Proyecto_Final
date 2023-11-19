package co.edu.uniquindio.poo.torneodeportivo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Juez extends Persona implements Participante{
    //atributo
    private String licencia;
    private List<Enfrentamiento> enfrentamientos;

    public Juez(String nombre, String apellido, String email, String celular, String licencia){
        super(nombre, apellido, email, celular);
        this.licencia = licencia; 
        this.enfrentamientos = new ArrayList<>();
    }

    @Override
    public String getNombreCompleto() {
        return getNombre()+ " "+getApellido();
    } 

    public String getLicencia(){
        return licencia;
    }

    public void setLicencia(String licencia){
        this.licencia = licencia;
    }
    /**
     * Permite obtener la lista de enfrentamientos en los que participará el juez.
     * @return List<Enfrentamiento> con los enfrentamientos del juez.
     */
    public Collection<Enfrentamiento> getEnfrentamientos() {
        return enfrentamientos;
    }
    /**
     * Permite agregar un enfrentamiento a la lista de enfrentamientos del juez.
     * @param enfrentamiento Enfrentamiento a ser agregado.
     */
    public void agregarEnfrentamiento(Enfrentamiento enfrentamiento) {
        enfrentamientos.add(enfrentamiento);
    }
    
    


}