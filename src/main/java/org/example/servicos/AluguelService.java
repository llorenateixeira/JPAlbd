package org.example.servicos;

import org.example.modelos.Aluguel;
import org.example.modelos.Locacao;
import org.example.repositorio.AluguelRepository;
import org.example.repositorio.LocacaoRepository;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class AluguelService {
    private final EntityManager manager;
    private final AluguelRepository aluguelRepo;
    private final LocacaoRepository locacaoRepo;

    public AluguelService(EntityManager manager) {
        this.manager = manager;
        this.aluguelRepo = new AluguelRepository(manager);
        this.locacaoRepo = new LocacaoRepository(manager);
    }

    /**
     * Registra o pagamento de aluguel e calcula multas se houver atraso.
     * @param aluguel Aluguel com as informações de pagamento.
     * @return Aluguel atualizado com o valor pago e a data do pagamento.
     */
    public Aluguel registrarPagamento(Aluguel aluguel) {
        // Busca a locação associada ao aluguel
        Locacao locacao = locacaoRepo.buscaPor(aluguel.getLocacao().getId());
        if (locacao == null) {
            throw new IllegalStateException("Locação não encontrada.");
        }

        // Calcula a multa, se houver
        double valorComMulta = calcularValorComMulta(locacao.getValorAluguel(), aluguel.getDataVencimento(), aluguel.getDataPagamento());
        aluguel.setValorPago(valorComMulta);

        // Define a observação
        if (aluguel.getDataPagamento().isAfter(aluguel.getDataVencimento())) {
            aluguel.setObs("Pagamento com multa.");
        } else {
            aluguel.setObs("Pagamento sem multa.");
        }

        // Salva o pagamento de aluguel
        Aluguel salvo = aluguelRepo.salvaOuAtualiza(aluguel);
        return salvo;
    }

    /**
     * Calcula o valor do aluguel com multa se houver atraso.
     * @param valorAluguel Valor base do aluguel.
     * @param dataVencimento Data de vencimento do aluguel.
     * @param dataPagamento Data real do pagamento.
     * @return Valor final com multa.
     */
    private double calcularValorComMulta(double valorAluguel, LocalDate dataVencimento, LocalDate dataPagamento) {
        long diasAtraso = ChronoUnit.DAYS.between(dataVencimento, dataPagamento);

        if (diasAtraso > 0) {
            double multaDiaria = 0.0033 * diasAtraso * valorAluguel;  // 0.33% por dia
            double multaMaxima = 0.2 * valorAluguel;  // Limitação de 20% do valor do aluguel
            double multaFinal = Math.min(multaDiaria, multaMaxima);  // Aplica a multa mínima entre a diária e a máxima
            return valorAluguel + multaFinal;
        }

        return valorAluguel;  // Sem multa se estiver dentro do prazo
    }
}
