package services;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import domain.Contato;
import repository.IContatoRepository;

public class ContatoService {

    private final IContatoRepository repository;

    public ContatoService(IContatoRepository repository) {
        this.repository = repository;
    }

    public Contato buscarPorNome(String nome) {
        return repository.buscarPorNome(nome);
    }

    public Contato buscarPorTelefone(String telefone) {
        return repository.buscarPorTelefone(telefone);
    }

    public boolean removerPorTelefone(String telefone) {
        Contato contato = repository.buscarPorTelefone(telefone);

        if (contato == null) {
            return false;
        }

        return repository.remover(contato);
    }

    // usei DIRETAMENTE IA nessa, por motivos de sem saco para fazer na mão. - heitor
    public boolean adicionarContatos(String path) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String linha;

            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");

                boolean result = adicionarContato(dados[0], dados[1]);

                if (!result) {
                    return false;
                }
            }

            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean adicionarContato(String nome, String telefone) {
        Contato novoContato = new Contato(nome, telefone);

        if (repository.buscarPorTelefone(novoContato.getTelefone()) != null) {
            return false;
        }

        repository.inserir(novoContato);
        return true;
    }

    public boolean alterarDadosContato(String nome, String novoTelefone, String novoNome) {
        Contato contatoBuscado = repository.buscarPorNome(nome);

        if (contatoBuscado == null) {
            return false;
        }

        repository.remover(contatoBuscado);
        repository.inserir(new Contato(novoNome, novoTelefone));

        return true;
    }

    public int quantidadeDeContatos() {
        return repository.getQuantidade();
    }
}
