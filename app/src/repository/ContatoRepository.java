package repository;

import datastructures.colecao.IColecao;
import datastructures.lista.ListaEncadeada;
import domain.Contato;
import domain.comparators.ContatoComparatorPorNome;
import domain.comparators.ContatoComparatorPorTelefone;

import java.util.Comparator;

public class ContatoRepository implements IContatoRepository {

    private final IColecao<Contato> listaPorNome;
    private final IColecao<Contato> listaPorTelefone;

    public ContatoRepository(boolean ordenada) {
        this.listaPorNome = new ListaEncadeada<Contato>(ordenada, new ContatoComparatorPorNome());
        this.listaPorTelefone = new ListaEncadeada<Contato>(ordenada, new ContatoComparatorPorTelefone());
    }

    @Override
    public void inserir(Contato contato) {
        listaPorNome.adicionar(contato);
        listaPorTelefone.adicionar(contato);
    }

    @Override
    public boolean remover(Contato contato) {
        listaPorNome.remover(contato);
        listaPorTelefone.remover(contato);
        return true;
    }

    @Override
    public Contato buscarPorNome(String nome) {
        Contato contato = new Contato(nome, null);
        return listaPorNome.pesquisar(contato);
    }

    @Override
    public Contato buscarPorTelefone(String telefone) {
        Contato contato = new Contato(null, telefone);
        return listaPorTelefone.pesquisar(contato);
    }

    @Override
    public int getQuantidade() {
        return listaPorNome.quantidadeNos();
    }

    @Override
    public String exibirLista() {
        return listaPorTelefone.toString();
    }
}
