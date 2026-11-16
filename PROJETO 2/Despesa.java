/*
 * Nome: Luan Souza Costa
 * RA: 10750029
 */

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Despesa extends Transacao {
    private String destino;

    public Despesa(String descricao, float valor, LocalDate data, String destino) {
        super(descricao, valor, data);
        this.destino = destino;
    }

    public String getDestino() {
        return destino;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String valorFormatado = String.format(new Locale("pt", "BR"), "%.2f", valor);
        return descricao + "\nR$ " + valorFormatado + "\n" + data.format(formatter) + "\n" + destino;
    }
}