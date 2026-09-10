package repository;

import domain.Contato;
import java.util.List;

public interface IContatoRepository {

    void inserir(Contato contato);

    boolean remover(Contato contato);

    Contato buscarPorNome(String nome);

    Contato buscarPorTelefone(String telefone);

    int getQuantidade();

    String exibirLista();
}