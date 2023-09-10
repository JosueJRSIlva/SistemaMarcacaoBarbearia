import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class UIMarcacaoBarbearia {
    private static ArrayList<Horario> horariosDisponiveis = new ArrayList<>();
    private static ArrayList<Reserva> reservas = new ArrayList<>();

    public static void main(String[] args) {
        // Inicialize alguns horários disponíveis para teste
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            horariosDisponiveis.add(new Horario(dateFormat.parse("2023-08-30"), "10:00"));
            horariosDisponiveis.add(new Horario(dateFormat.parse("2023-08-30"), "11:00"));
            horariosDisponiveis.add(new Horario(dateFormat.parse("2023-08-30"), "14:00"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);
        int escolha;

        do {
            System.out.println("===== Sistema de Marcação de Horários para Barbearia =====");
            System.out.println("1. Ver Horários Disponíveis");
            System.out.println("2. Fazer Reserva");
            System.out.println("3. Ver Reservas");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            escolha = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (escolha) {
                case 1:
                    listarHorariosDisponiveis();
                    break;
                case 2:
                    fazerReserva(scanner);
                    break;
                case 3:
                    verReservas();
                    break;
                case 4:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (escolha != 4);

        scanner.close();
    }

    private static void listarHorariosDisponiveis() {
        System.out.println("Horários Disponíveis:");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        for (int i = 0; i < horariosDisponiveis.size(); i++) {
            Horario horario = horariosDisponiveis.get(i);
            if (horario.isDisponivel()) {
                System.out.println(i + ". " + dateFormat.format(horario.getData()) + " " + horario.getHora());
            }
        }
    }

    private static void fazerReserva(Scanner scanner) {
        listarHorariosDisponiveis();
        System.out.print("Digite o número do horário que deseja reservar: ");
        int indiceReserva = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer

        if (indiceReserva >= 0 && indiceReserva < horariosDisponiveis.size()) {
            Horario horarioSelecionado = horariosDisponiveis.get(indiceReserva);
            if (horarioSelecionado.isDisponivel()) {
                System.out.print("Digite o nome do cliente: ");
                String nomeCliente = scanner.nextLine();
                System.out.print("Digite o telefone do cliente: ");
                String telefoneCliente = scanner.nextLine();

                Cliente cliente = new Cliente(nomeCliente, telefoneCliente);
                Reserva reserva = new Reserva(cliente, null, horarioSelecionado);
                reservas.add(reserva);
                horarioSelecionado.reservar();

                System.out.println("Reserva realizada com sucesso!");
            } else {
                System.out.println("Este horário já está reservado.");
            }
        } else {
            System.out.println("Número de horário inválido!");
        }
    }

    private static void verReservas() {
        System.out.println("Reservas:");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        for (Reserva reserva : reservas) {
            Horario horario = reserva.getHorario();
            Cliente cliente = reserva.getCliente();
            System.out.println("Data: " + dateFormat.format(horario.getData()) + " Hora: " + horario.getHora() +
                    " Cliente: " + cliente.getNome() + " Telefone: " + cliente.getTelefone());
        }
    }
}
