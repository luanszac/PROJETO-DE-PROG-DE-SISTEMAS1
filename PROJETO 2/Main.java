/*
 * Nome: Luan Souza Costa
 * RA: 10750029
 */

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Financas financas = new Financas();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/uuuu")
                .withResolverStyle(ResolverStyle.STRICT);

        int opcao = -1;

        while (opcao != 0) {
            exibirMenu();
            String entradaOpcao = scanner.nextLine().trim();

            try {
                opcao = Integer.parseInt(entradaOpcao);
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida! Digite um número entre 0 e 8.\n");
                continue;
            }

            switch (opcao) {
                case 1:
                    cadastrarReceita(scanner, financas, dateFormatter);
                    break;
                case 2:
                    cadastrarDespesa(scanner, financas, dateFormatter);
                    break;
                case 3:
                    exibirRelatorioMensal(scanner, financas, 0);
                    break;
                case 4:
                    exibirRelatorioMensal(scanner, financas, 1);
                    break;
                case 5:
                    exibirRelatorioMensal(scanner, financas, 2);
                    break;
                case 6:
                    exibirRelatorioAnual(scanner, financas, 0);
                    break;
                case 7:
                    exibirRelatorioAnual(scanner, financas, 1);
                    break;
                case 8:
                    exibirRelatorioAnual(scanner, financas, 2);
                    break;
                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opção inválida! Digite um número entre 0 e 8.\n");
            }
        }

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("===== MINHAS FINANÇAS =====");
        System.out.println("[1] CADASTRAR RECEITA");
        System.out.println("[2] CADASTRAR DESPESA");
        System.out.println("[3] GERAR RELATÓRIO MENSAL");
        System.out.println("[4] GERAR RELATÓRIO MENSAL (SOMENTE RECEITAS)");
        System.out.println("[5] GERAR RELATÓRIO MENSAL (SOMENTE DESPESAS)");
        System.out.println("[6] GERAR RELATÓRIO ANUAL");
        System.out.println("[7] GERAR RELATÓRIO ANUAL (SOMENTE RECEITAS)");
        System.out.println("[8] GERAR RELATÓRIO ANUAL (SOMENTE DESPESAS)");
        System.out.println("[0] SAIR");
        System.out.print("Selecione uma opção: ");
    }

    private static void cadastrarReceita(Scanner scanner, Financas financas, DateTimeFormatter formatter) {
        String descricao = lerTextoObrigatorio(scanner, "Descrição: ");
        float valor = lerValorObrigatorio(scanner, "Valor: ");
        LocalDate data = lerDataObrigatoria(scanner, "Data (DD/MM/AAAA): ", formatter);
        String origem = lerTextoObrigatorio(scanner, "Origem: ");

        Receita receita = new Receita(descricao, valor, data, origem);
        financas.cadastrar(receita);
        System.out.println("Receita cadastrada com sucesso!\n");
    }

    private static void cadastrarDespesa(Scanner scanner, Financas financas, DateTimeFormatter formatter) {
        String descricao = lerTextoObrigatorio(scanner, "Descrição: ");
        float valor = lerValorObrigatorio(scanner, "Valor: ");
        LocalDate data = lerDataObrigatoria(scanner, "Data (DD/MM/AAAA): ", formatter);
        String destino = lerTextoObrigatorio(scanner, "Destino: ");

        Despesa despesa = new Despesa(descricao, valor, data, destino);
        financas.cadastrar(despesa);
        System.out.println("Despesa cadastrada com sucesso!\n");
    }

    private static void exibirRelatorioMensal(Scanner scanner, Financas financas, int tipo) {
        int mes = lerInteiroIntervalo(scanner, "Informe o mês (1 a 12): ", 1, 12);
        int ano = lerInteiroPositivo(scanner, "Informe o ano: ");

        String relatorio = financas.gerarRelatorioMensal(tipo, mes, ano);
        if (relatorio.isEmpty()) {
            System.out.println("Nenhuma transação encontrada para o período informado.\n");
        } else {
            System.out.println("\n" + relatorio + "\n");
        }
    }

    private static void exibirRelatorioAnual(Scanner scanner, Financas financas, int tipo) {
        int ano = lerInteiroPositivo(scanner, "Informe o ano: ");

        String relatorio = financas.gerarRelatorioAnual(tipo, ano);
        if (relatorio.isEmpty()) {
            System.out.println("Nenhuma transação encontrada para o ano informado.\n");
        } else {
            System.out.println("\n" + relatorio + "\n");
        }
    }

    private static String lerTextoObrigatorio(Scanner scanner, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine().trim();
            if (!entrada.isEmpty()) {
                return entrada;
            }
            System.out.println("Erro: Este campo é obrigatório.");
        }
    }

    private static float lerValorObrigatorio(Scanner scanner, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine().trim().replace(",", ".");
            try {
                float valor = Float.parseFloat(entrada);
                if (valor > 0) {
                    return valor;
                }
                System.out.println("Erro: O valor deve ser maior que zero.");
            } catch (NumberFormatException e) {
                System.out.println("Erro: Valor numérico inválido.");
            }
        }
    }

    private static LocalDate lerDataObrigatoria(Scanner scanner, String mensagem, DateTimeFormatter formatter) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine().trim();
            try {
                return LocalDate.parse(entrada, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Erro: Data inválida. Utilize o formato DD/MM/AAAA.");
            }
        }
    }

    private static int lerInteiroIntervalo(Scanner scanner, String mensagem, int min, int max) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine().trim();
            try {
                int valor = Integer.parseInt(entrada);
                if (valor >= min && valor <= max) {
                    return valor;
                }
                System.out.println("Erro: Informe um valor entre " + min + " e " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("Erro: Número inválido.");
            }
        }
    }

    private static int lerInteiroPositivo(Scanner scanner, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine().trim();
            try {
                int valor = Integer.parseInt(entrada);
                if (valor > 0) {
                    return valor;
                }
                System.out.println("Erro: O ano deve ser maior que zero.");
            } catch (NumberFormatException e) {
                System.out.println("Erro: Ano inválido.");
            }
        }
    }
}