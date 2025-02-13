package org.example.repositorio;

import org.example.modelos.ServicoImovel;
import jakarta.persistence.EntityManager;
import java.util.List;

public class ServicoImovelRepository {
    private final EntityManager manager;
    private final DAOGenerico<ServicoImovel> daoGenerico;

    public ServicoImovelRepository(EntityManager manager) {
        this.manager = manager;
        this.daoGenerico = new DAOGenerico<>(manager, ServicoImovel.class);
    }

    /**
     * Busca um serviço de imóvel pelo ID.
     * @param id ID do serviço.
     * @return Serviço encontrado ou null se não existir.
     */
    public ServicoImovel buscaPor(Integer id) {
        return daoGenerico.buscaPorId(id);
    }

    /**
     * Busca serviços de um imóvel específico.
     * @param idImovel ID do imóvel.
     * @return Lista de serviços do imóvel.
     */
    public List<ServicoImovel> buscaPorImovel(Integer idImovel) {
        return this.manager.createQuery("FROM ServicoImovel WHERE id_imovel = :idImovel", ServicoImovel.class)
                .setParameter("idImovel", idImovel)
                .getResultList();
    }

    /**
     * Salva ou atualiza um serviço de imóvel no banco de dados.
     * @param servico Serviço de imóvel a ser salvo ou atualizado.
     * @return Serviço persistido.
     */
    public ServicoImovel salvaOuAtualiza(ServicoImovel servico) {
        return daoGenerico.salvaOuAtualiza(servico);
    }

    /**
     * Remove um serviço de imóvel do banco de dados.
     * @param servico Serviço a ser removido.
     */
    public void remove(ServicoImovel servico) {
        daoGenerico.remove(servico);
    }
}