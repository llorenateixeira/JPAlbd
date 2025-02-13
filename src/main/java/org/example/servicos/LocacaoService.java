package org.example.servicos;

import jakarta.persistence.EntityManager;
import org.example.modelos.Imovel;
import org.example.modelos.Locacao;
import org.example.repositorio.ImovelRepository;
import org.example.repositorio.LocacaoRepository;

public class LocacaoService {
    private final EntityManager manager;
    private final LocacaoRepository locacaoRepo;
    private final ImovelRepository imovelRepo;

    public LocacaoService(EntityManager manager) {
        this.manager = manager;
        this.locacaoRepo = new LocacaoRepository(manager);
        this.imovelRepo = new ImovelRepository(manager);
    }

    public Locacao registrarLocacao(Locacao locacao) {
        // A transação já está em andamento na classe Main
        // Não inicie outra transação aqui

        // Buscar o imóvel pelo ID
        Imovel imovel = imovelRepo.buscaPor(locacao.getImovel().getId());

        if (imovel == null) {
            throw new IllegalStateException("Imóvel não encontrado.");
        }

        if (!"DISPONIVEL".equalsIgnoreCase(imovel.getStatus())) {
            throw new IllegalStateException("O imóvel já está alugado.");
        }

        imovel.setStatus("ALUGADO");
        imovelRepo.salvaOuAtualiza(imovel);

        locacao = locacaoRepo.salvaOuAtualiza(locacao);

        return locacao;
    }

    public void finalizarLocacao(Integer idLocacao) {
        // A transação já está em andamento na classe Main
        // Não inicie outra transação aqui

        Locacao locacao = locacaoRepo.buscaPor(idLocacao);
        if (locacao == null) {
            throw new IllegalStateException("Locação não encontrada.");
        }

        Imovel imovel = locacao.getImovel();
        imovel.setStatus("DISPONIVEL");
        imovelRepo.salvaOuAtualiza(imovel);

        locacaoRepo.remove(locacao);
    }
}
