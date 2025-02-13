package org.example.tests;

import org.example.modelos.Aluguel;
import org.example.modelos.Cliente;
import org.example.modelos.Imovel;
import org.example.modelos.Locacao;
import org.example.repositorio.AluguelRepository;
import org.example.repositorio.ClienteRepository;
import org.example.repositorio.ImovelRepository;
import org.example.repositorio.LocacaoRepository;
import org.example.servicos.AluguelService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class AluguelServiceTeste {

    public static void main(String[] args) {
        // Configuração do EntityManager
        EntityManager manager = Persistence.createEntityManagerFactory("imobiliaria").createEntityManager();
        EntityTransaction tx = manager.getTransaction();

        try {
            // Iniciando a transação
            tx.begin();

            // Criando instâncias dos repositórios e do serviço
            ClienteRepository clienteRepo = new ClienteRepository(manager);
            ImovelRepository imovelRepo = new ImovelRepository(manager);
            LocacaoRepository locacaoRepo = new LocacaoRepository(manager);
            AluguelRepository aluguelRepo = new AluguelRepository(manager);
            AluguelService aluguelService = new AluguelService(manager);

            // Criando um cliente
            Cliente cliente = new Cliente();
            cliente.setNome("Carlos Oliveira");
            cliente.setCpf("987.654.321-00");
            cliente.setTelefone("11999988888");
            cliente.setEmail("carlos@email.com");
            cliente.setDataNascimento(LocalDate.of(1980, 5, 15));
            clienteRepo.salvaOuAtualiza(cliente);
            System.out.println("✅ Cliente salvo com sucesso: " + cliente.getNome());

            // Criando um imóvel
            Imovel imovel = new Imovel();
            imovel.setProprietario(cliente);
            imovel.setLogradouro("Rua das Flores, 123");
            imovel.setBairro("Jardim das Rosas");
            imovel.setCep("98765-432");
            imovel.setMetragem(80);
            imovel.setDormitorios( 2);
            imovel.setBanheiros(1);
            imovel.setSuites(1);
            imovel.setVagasGaragem(1);
            imovel.setValorAluguelSugerido(1500.00);
            imovel.setStatus("DISPONIVEL");
            imovelRepo.salvaOuAtualiza(imovel);
            System.out.println("✅ Imóvel salvo com sucesso: " + imovel.getLogradouro());

            // Criando uma locação
            Locacao locacao = new Locacao();
            locacao.setImovel(imovel);
            locacao.setInquilino(cliente);
            locacao.setValorAluguel(1500.00);
            locacao.setDiaVencimento(10);
            locacao.setDataInicio(LocalDate.now());
            locacao.setDataFim(LocalDate.now().plusMonths(12));
            locacaoRepo.salvaOuAtualiza(locacao);
            System.out.println("✅ Locação registrada com sucesso!");

            // Criando o pagamento de aluguel
            Aluguel aluguel = new Aluguel();
            aluguel.setLocacao(locacao);
            aluguel.setDataVencimento(LocalDate.now().plusDays(10));  // Data de vencimento
            aluguel.setDataPagamento(LocalDate.now().plusDays(12));   // Pagamento com 2 dias de atraso
            aluguel.setValorPago(1500.00);

            // Registrando o pagamento
            aluguelService.registrarPagamento(aluguel);
            System.out.println("✅ Pagamento registrado com sucesso: Valor pago: " + aluguel.getValorPago());

            // Verificando se o pagamento foi registrado corretamente
            Aluguel aluguelEncontrado = aluguelRepo.buscaPor(aluguel.getId());
            if (aluguelEncontrado != null) {
                System.out.println("✔ Pagamento encontrado: " + aluguelEncontrado.getValorPago() + " - Obs: " + aluguelEncontrado.getObs());
            } else {
                System.out.println("Pagamento não encontrado.");
            }

            // Finalizando a transação
            tx.commit();
        } catch (Exception e) {
            // Se houver erro, faz o rollback
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            // Fechar o EntityManager
            manager.close();
        }
    }
}
