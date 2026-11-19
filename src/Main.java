import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int emprestimosAtivosMembro = 2;
        boolean cadastroMembroValido = true;

        int totalExemplaresLivro = 2;
        int exemplaresDisponiveis = 1;
        boolean exemplarEmBomEstado = true;

        final int PRAZO_PADRAO_DIAS = 14;
        final double VALOR_MULTA_POR_DIA = 2.50;

        int opcao = -1;

        while (opcao != 0) {
            System.out.println("1. Consultar Disponibilidade do Livro");
            System.out.println("2. Simular Empréstimo de Exemplar");
            System.out.println("3. Simular Devolução e Cálculo de Multa");
            System.out.println("4. Simular Solicitação de Reserva");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("\n[Consulta] Total de exemplares: " + totalExemplaresLivro);
                    System.out.println("[Consulta] Disponíveis agora: " + exemplaresDisponiveis);
                    System.out.println("[Consulta] Empréstimos ativos do membro: " + emprestimosAtivosMembro + "/3");
                    break;

                case 2:
                    if (!cadastroMembroValido) {
                        System.out.println("BLOQUEADO: Membro não possui cadastro válido.");
                    } else if (emprestimosAtivosMembro >= 3) {
                        System.out.println("BLOQUEADO: Limite máximo de 3 empréstimos simultâneos atingido.");
                    } else if (exemplaresDisponiveis <= 0 || !exemplarEmBomEstado) {
                        System.out.println("BLOQUEADO: Exemplar indisponível ou danificado.");
                    } else {
                        emprestimosAtivosMembro++;
                        exemplaresDisponiveis--;
                        System.out.println("SUCESSO: Empréstimo realizado! Prazo de devolução: " + PRAZO_PADRAO_DIAS + " dias corridos.");
                    }
                    break;

                case 3:
                    if (exemplaresDisponiveis >= totalExemplaresLivro) {
                        System.out.println("AVISO: Não há exemplares emprestados para devolver.");
                    } else {
                        System.out.print("Informe quantos dias corridos se passaram desde a retirada: ");
                        int diasDecorridos = scanner.nextInt();

                        emprestimosAtivosMembro--;
                        exemplaresDisponiveis++;

                        if (diasDecorridos > PRAZO_PADRAO_DIAS) {
                            int diasAtraso = diasDecorridos - PRAZO_PADRAO_DIAS;
                            double valorMulta = diasAtraso * VALOR_MULTA_POR_DIA;
                            System.out.printf("DEVOLVIDO COM ATRASO: %d dia(s) de atraso. Multa gerada: R$ %.2f%n", diasAtraso, valorMulta);
                        } else {
                            System.out.println("SUCESSO: Devolução realizada dentro do prazo. Sem multas.");
                        }
                    }
                    break;

                case 4:
                    if (!cadastroMembroValido) {
                        System.out.println("BLOQUEADO: Cadastro de membro inválido.");
                    } else if (exemplaresDisponiveis > 0) {
                        System.out.println("BLOQUEADO: Existem " + exemplaresDisponiveis + " exemplar(es) disponível(is). Reserva não permitida.");
                    } else {
                        System.out.println("SUCESSO: Todos os exemplares estão emprestados. Reserva registrada para o livro!");
                    }
                    break;

                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }
}