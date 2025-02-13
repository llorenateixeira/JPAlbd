package org.example.repositorio;

import org.example.modelos.Cliente;
import jakarta.persistence.*;
import java.util.List;

public class ClienteRepository {
    private final EntityManager manager;
    private final DAOGenerico<Cliente> daoGenerico;

    public ClienteRepository(EntityManager manager) {
        this.manager = manager;
        this.daoGenerico = new DAOGenerico<>(manager, Cliente.class);
    }

    /**
     * Busca um cliente pelo ID.
     * @param id ID do cliente.
     * @return Cliente encontrado ou null se não existir.
     */
    public Cliente buscaPor(Integer id) {
        return daoGenerico.buscaPorId(id);
    }

    /**
     * Busca clientes pelo nome.
     * @param nome Nome do cliente (busca aproximada).
     * @return Lista de clientes encontrados.
     */
    public List<Cliente> buscaPorNome(String nome) {
        return this.manager.createQuery("FROM Cliente WHERE UPPER(nome) LIKE :nome", Cliente.class)
                .setParameter("nome", nome.toUpperCase() + "%")
                .getResultList();
    }

    /**
     * Salva ou atualiza um cliente no banco de dados.
     * @param cliente Cliente a ser salvo ou atualizado.
     * @return Cliente persistido.
     */
    public Cliente salvaOuAtualiza(Cliente cliente) {
        return daoGenerico.salvaOuAtualiza(cliente);
    }

    /**
     * Remove um cliente do banco de dados.
     * @param cliente Cliente a ser removido.
     */
    public void remove(Cliente cliente) {
        daoGenerico.remove(cliente);
    }
}
