package co.edu.uniquindio.poo.torneodeportivo;


public enum GeneroTorneo {
    FEMENINO{
        public boolean esValido(Participante participante){
            return ((Jugador)participante).getGeneroJugador() == FEMENINO;
        }
    },MASCULINO{
        public boolean esValido(Participante participante){
            return ((Jugador)participante).getGeneroJugador() == MASCULINO;
        }
    },MIXTO{
        public boolean esValido(Participante participante){
            return true;
        }
    };

    public abstract boolean esValido(Participante participante);
}


