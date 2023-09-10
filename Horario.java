import java.util.Date;

public class Horario {
    private Date data;
    private String hora;
    private boolean disponivel;

    public Horario(Date data, String hora) {
        this.data = data;
        this.hora = hora;
        this.disponivel = true;
    }

    public Date getData() {
        return data;
    }

    public String getHora() {
        return hora;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void reservar() {
        disponivel = false;
    }

    public void liberar() {
        disponivel = true;
    }
}
