package org.example.repositorio;

import org.example.modelos.Aluguel;

import jakarta.persistence.*;


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

    public List<Aluguel> buscaPorVencimento(String dataVencimento) {
        return this.manager.createQuery("from Aluguel where data_vencimento = :dataVencimento", Aluguel.class)
                .setParameter("dataVencimento", dataVencimento)
                .getResultList();
    }

    public Aluguel salvaOuAtualiza(Aluguel aluguel) {
        return daoGenerico.salvaOuAtualiza(aluguel);
    }

    public void remove(Aluguel aluguel) {
        daoGenerico.remove(aluguel);
    }
}
