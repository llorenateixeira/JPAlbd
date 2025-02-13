package org.example.tests;

import org.example.modelos.Cliente;
import org.example.modelos.Imovel;
import org.example.modelos.Locacao;
import org.example.repositorio.ClienteRepository;
import org.example.repositorio.ImovelRepository;
import org.example.repositorio.LocacaoRepository;
import org.example.servicos.LocacaoService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class LocacaoServiceTeste {

    public static void main(String[] args) {
        // Configuração do EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("imobiliaria");
        EntityManager manager = emf.createEntityManager();

        // Instanciando os repositórios e o serviço
        ClienteRepository clientesRepo = new ClienteRepository(manager);
        ImovelRepository imoveisRepo = new ImovelRepository(manager);
        LocacaoRepository locacaoRepo = new LocacaoRepository(manager);
        LocacaoService locacaoService = new LocacaoService(manager);

        // Iniciando transação
        manager.getTransaction().begin();

        try {
            // Criando um novo cliente
            Cliente cliente = new Cliente();
            cliente.setNome("Carlos Silva");
            cliente.setCpf("987.654.321-00");
            cliente.setTelefone("11999998888");
            cliente.setEmail("carlos@email.com");
            cliente.setDataNascimento(LocalDate.of(1980, 12, 1));

            // Salvando o cliente
            clientesRepo.salvaOuAtualiza(cliente);
            System.out.println("✅ Cliente salvo com sucesso: " + cliente.getNome());

            // Criando um novo imóvel
            Imovel imovel = new Imovel();
            imovel.setProprietario(cliente);
            imovel.setLogradouro("Rua das Flores, 456");
            imovel.setBairro("Jardim das Rosas");
            imovel.setCep("67890-123");
            imovel.setMetragem(100);
            imovel.setDormitorios(3);
            imovel.setBanheiros(2);
            imovel.setSuites(1);
            imovel.setVagasGaragem(2);
            imovel.setValorAluguelSugerido(3500.0);
            imovel.setStatus("DISPONIVEL");

            // Salvando o imóvel
            imoveisRepo.salvaOuAtualiza(imovel);
            System.out.println("✅ Imóvel salvo com sucesso: " + imovel.getLogradouro());

            // Criando uma nova locação
            Locacao locacao = new Locacao();
            locacao.setImovel(imovel);
            locacao.setInquilino(cliente);
            locacao.setValorAluguel(3500.0);
            locacao.setDiaVencimento(5);
            locacao.setDataInicio(LocalDate.now());
            locacao.setDataFim(LocalDate.now().plusMonths(12));

            // Registrando a locação
            locacaoService.registrarLocacao(locacao);
            System.out.println("✅ Locação registrada com sucesso!");

            // Verificando a locação
            Locacao locacaoEncontrada = locacaoRepo.buscaPor(locacao.getId());
            if (locacaoEncontrada != null) {
                System.out.println("✔ Locação encontrada: " + locacaoEncontrada.getImovel().getLogradouro());
            } else {
                System.out.println("Locação não encontrada.");
            }

            // Commit da transação
            manager.getTransaction().commit();

        } catch (Exception e) {
            // Em caso de erro, rollback da transação
            if (manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            // Fechar o EntityManager
            manager.close();
            emf.close();
        }
    }
}
