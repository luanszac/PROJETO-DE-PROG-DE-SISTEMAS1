/*
 * Nome: Luan Souza Costa
 * RA: 10750029
 */

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Receita extends Transacao {
    private String origem;

    public Receita(String descricao, float valor, LocalDate data, String origem) {
        super(descricao, valor, data);
        this.origem = origem;
    }

    public String getOrigem() {
        return origem;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String valorFormatado = String.format(new Locale("pt", "BR"), "%.2f", valor);
        return descricao + "\nR$ " + valorFormatado + "\n" + data.format(formatter) + "\n" + origem;
    }
}