package org.example.repositorio;

import org.example.modelos.Profissional;

import jakarta.persistence.*;
import java.util.List;

public class ProfissionalRepository {
    private final EntityManager manager;
    private DAOGenerico<Profissional> daoGenerico;

    public ProfissionalRepository(EntityManager manager) {
        this.manager = manager;
        this.daoGenerico = new DAOGenerico<>(manager, Profissional.class);
    }

    public Profissional buscaPor(Integer id) {
        return daoGenerico.buscaPorId(id);
    }

    public List<Profissional> buscaPorProfissao(String profissao) {
        return this.manager.createQuery("from Profissional where upper(profissao) like :profissao", Profissional.class)
                .setParameter("profissao", profissao.toUpperCase() + "%")
                .getResultList();
    }

    public Profissional salvaOuAtualiza(Profissional profissional) {
        return daoGenerico.salvaOuAtualiza(profissional);
    }

    public void remove(Profissional profissional) {
        daoGenerico.remove(profissional);
    }
}
