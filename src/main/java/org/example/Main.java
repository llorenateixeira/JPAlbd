package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.modelos.Cliente;
import org.example.repositorio.ClienteRepository;

import java.sql.Date;

public class Main {
    public static void main(String[] args) {
        // Criando EntityManagerFactory para gerenciar conexões
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("imobiliaria");
        EntityManager manager = factory.createEntityManager();

        // Criando repositório com o EntityManager
        ClienteRepository repo = new ClienteRepository(manager);

        // Criando um cliente
        Cliente cliente = new Cliente();
        cliente.setNome("João da Silva");
        cliente.setCpf("123.456.789-00");
        cliente.setTelefone("11999999999");
        cliente.setEmail("joao@email.com");
        cliente.setDataNascimento(Date.valueOf("1995-07-20"));

        // Iniciando transação
        manager.getTransaction().begin();
        repo.salvaOuAtualiza(cliente);
        manager.getTransaction().commit();
        System.out.println("✅ Cliente salvo com sucesso!");

        // Buscar cliente pelo ID
        Cliente encontrado = repo.buscaPor(cliente.getId());
        System.out.println("🔍 Cliente encontrado: " + encontrado.getNome());

        // Remover cliente
        manager.getTransaction().begin();
        repo.remove(cliente);
        manager.getTransaction().commit();
        System.out.println("🗑️ Cliente removido com sucesso!");

        // Fechando o EntityManager e Factory
        manager.close();
        factory.close();
    }
}
