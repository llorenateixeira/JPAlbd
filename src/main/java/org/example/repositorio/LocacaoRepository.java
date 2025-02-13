package org.example.repositorio;

import org.example.modelos.Locacao;

import jakarta.persistence.*;

import java.util.List;

public class LocacaoRepository {
    private final EntityManager manager;
    private DAOGenerico<Locacao> daoGenerico;

    public LocacaoRepository(EntityManager manager) {
        this.manager = manager;
        this.daoGenerico = new DAOGenerico<>(manager, Locacao.class);
    }

    public Locacao buscaPor(Integer id) {
        return daoGenerico.buscaPorId(id);
    }

    public List<Locacao> buscaPorInquilino(Integer idInquilino) {
        return this.manager.createQuery("from Locacao where id_inquilino = :idInquilino", Locacao.class)
                .setParameter("idInquilino", idInquilino)
                .getResultList();
    }

    public Locacao salvaOuAtualiza(Locacao locacao) {
        return daoGenerico.salvaOuAtualiza(locacao);
    }

    public void remove(Locacao locacao) {
        daoGenerico.remove(locacao);
    }
}
