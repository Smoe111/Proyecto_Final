package co.edu.uniquindio.poo.torneodeportivo;

public enum CaracterTorneo {
    INDIVIDUAL{
        public boolean esValido(Participante participante){
            return participante instanceof Jugador || participante instanceof Juez;
        }
    },GRUPAL{
        public boolean esValido(Participante participante){
            return participante instanceof Equipo;
        }
    };

    public abstract boolean esValido(Participante participante);
}
