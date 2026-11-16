/*
 * Nome: Luan Souza Costa
 * RA: 10750029
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Financas {
    private List<Transacao> transacoes;

    public Financas() {
        this.transacoes = new ArrayList<>();
    }

    public void cadastrar(Receita receita) {
        if (receita != null) {
            transacoes.add(receita);
        }
    }

    public void cadastrar(Despesa despesa) {
        if (despesa != null) {
            transacoes.add(despesa);
        }
    }

    public String gerarRelatorioMensal(int tipo, int mes, int ano) {
        List<Transacao> filtradas = new ArrayList<>();
        for (Transacao t : transacoes) {
            if (t.getData().getMonthValue() == mes && t.getData().getYear() == ano) {
                if (tipo == 0) {
                    filtradas.add(t);
                } else if (tipo == 1 && t instanceof Receita) {
                    filtradas.add(t);
                } else if (tipo == 2 && t instanceof Despesa) {
                    filtradas.add(t);
                }
            }
        }
        return formatarRelatorio(filtradas);
    }

    public String gerarRelatorioAnual(int tipo, int ano) {
        List<Transacao> filtradas = new ArrayList<>();
        for (Transacao t : transacoes) {
            if (t.getData().getYear() == ano) {
                if (tipo == 0) {
                    filtradas.add(t);
                } else if (tipo == 1 && t instanceof Receita) {
                    filtradas.add(t);
                } else if (tipo == 2 && t instanceof Despesa) {
                    filtradas.add(t);
                }
            }
        }
        return formatarRelatorio(filtradas);
    }

    private String formatarRelatorio(List<Transacao> lista) {
        if (lista.isEmpty()) {
            return "";
        }
        lista.sort(Comparator.comparing(Transacao::getData));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lista.size(); i++) {
            sb.append(lista.get(i).toString());
            if (i < lista.size() - 1) {
                sb.append("\n\n");
            }
        }
        return sb.toString();
    }
}