package org.example.repositorio;

import jakarta.persistence.EntityManager;
import java.util.List;
import org.example.modelos.Locacao;

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
        return this.manager.createQuery("from Locacao where inquilino.id = :idInquilino", Locacao.class)
                .setParameter("idInquilino", idInquilino)
                .getResultList();
    }

    public Locacao salvaOuAtualiza(Locacao locacao) {
        return daoGenerico.salvaOuAtualiza(locacao);
    }

    public void remove(Locacao locacao) {
        daoGenerico.remove(locacao);
    }

    /**
     * Método para buscar todas as locações cadastradas.
     */
    public List<Locacao> buscaTodas() {
        return this.manager.createQuery("from Locacao", Locacao.class).getResultList();
    }
}
