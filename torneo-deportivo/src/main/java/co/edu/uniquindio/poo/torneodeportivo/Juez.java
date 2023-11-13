package co.edu.uniquindio.poo.torneodeportivo;

public class Juez extends Persona implements Participante{
    //atributo
    private String licencia;

    public Juez(String nombre, String apellido, String email, String celular, String licencia){
        super(nombre, apellido, email, celular);
        this.licencia = licencia;  
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


}