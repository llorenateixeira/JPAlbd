package org.example.repositorio;

import org.example.modelos.Imovel;

import jakarta.persistence.*;

import java.util.List;

public class ImovelRepository {
    private final EntityManager manager;
    private DAOGenerico<Imovel> daoGenerico;

    public ImovelRepository(EntityManager manager) {
        this.manager = manager;
        this.daoGenerico = new DAOGenerico<>(manager, Imovel.class);
    }

    public Imovel buscaPor(Integer id) {
        return daoGenerico.buscaPorId(id);
    }

    public List<Imovel> buscaPorBairro(String bairro) {
        return this.manager.createQuery("from Imovel where upper(bairro) like :bairro", Imovel.class)
                .setParameter("bairro", bairro.toUpperCase() + "%")
                .getResultList();
    }

    public Imovel salvaOuAtualiza(Imovel imovel) {
        return daoGenerico.salvaOuAtualiza(imovel);
    }

    public void remove(Imovel imovel) {
        daoGenerico.remove(imovel);
    }
}