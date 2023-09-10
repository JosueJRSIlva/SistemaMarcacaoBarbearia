public class Reserva {
    private Cliente cliente;
    private Funcionario funcionario;
    private Horario horario;

    public Reserva(Cliente cliente, Funcionario funcionario, Horario horario) {
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.horario = horario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public Horario getHorario() {
        return horario;
    }
}
