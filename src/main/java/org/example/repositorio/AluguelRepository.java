package org.example.repositorio;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.example.modelos.Aluguel;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class AluguelRepository {
    private final EntityManager manager;
    private DAOGenerico<Aluguel> daoGenerico;

    public AluguelRepository(EntityManager manager) {
        this.manager = manager;
        this.daoGenerico = new DAOGenerico<>(manager, Aluguel.class);
    }

    public Aluguel buscaPor(Integer id) {
        return daoGenerico.buscaPorId(id);
    }

    public List<Aluguel> buscaPorVencimento(LocalDate dataVencimento) {
        return this.manager.createQuery("from Aluguel where dataVencimento = :dataVencimento", Aluguel.class)
                .setParameter("dataVencimento", dataVencimento)
                .getResultList();
    }

    public Aluguel salvaOuAtualiza(Aluguel aluguel) {
        return daoGenerico.salvaOuAtualiza(aluguel);
    }

    public void remove(Aluguel aluguel) {
        daoGenerico.remove(aluguel);
    }

    /**
     * Retorna todos os registros de aluguel cadastrados no sistema.
     * @return Lista de todos os aluguéis.
     */
    public List<Aluguel> buscaTodos() {
        return this.manager.createQuery("from Aluguel", Aluguel.class).getResultList();
    }

    /**
     * Registra o pagamento do aluguel e calcula multa se houver atraso.
     * @param idAluguel ID do aluguel.
     * @param dataPagamento Data real do pagamento.
     * @return Valor final pago, incluindo multa.
     */
    public double registrarPagamento(Integer idAluguel, LocalDate dataPagamento) {
        EntityTransaction tx = manager.getTransaction();
        tx.begin();

        Aluguel aluguel = buscaPor(idAluguel);
        if (aluguel == null) {
            throw new IllegalStateException("Aluguel não encontrado.");
        }

        // Obtendo o valor original do aluguel
        double valorAluguel = aluguel.getLocacao().getValorAluguel();

        double valorFinal = calcularValorComMulta(valorAluguel, aluguel.getDataVencimento(), dataPagamento);

        aluguel.setDataPagamento(dataPagamento);
        aluguel.setValorPago(valorFinal);
        salvaOuAtualiza(aluguel);

        tx.commit();
        return valorFinal;
    }

    /**
     * Calcula a multa por atraso no pagamento.
     * @param valorAluguel Valor base do aluguel.
     * @param dataVencimento Data de vencimento do aluguel.
     * @param dataPagamento Data real do pagamento.
     * @return Valor final com multa aplicada.
     */
    private double calcularValorComMulta(double valorAluguel, LocalDate dataVencimento, LocalDate dataPagamento) {
        long diasAtraso = ChronoUnit.DAYS.between(dataVencimento, dataPagamento);

        if (diasAtraso > 0) {
            double multaDiaria = 0.0033 * diasAtraso * valorAluguel;
            double multaMaxima = 0.2 * valorAluguel;
            double multaFinal = Math.min(multaDiaria, multaMaxima);
            return valorAluguel + multaFinal;
        }

        return valorAluguel;
    }
}
