package view;

import domain.Contato;
import repository.ContatoRepository;
import services.ContatoService;

import java.util.Scanner;

public class ContatoView {

    Scanner scanner = new Scanner(System.in);
    ContatoService contatoService;

    public void iniciar() {
        splashScreen();
        setup();
        mainMenu();
    }

    private void mainMenu() {
        boolean executando = true;

        while (executando) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Carregar dados de arquivo (padrão: dados/arquivo1000.txt)");
            System.out.println("2. Adicionar contato");
            System.out.println("3. Pesquisar contato por nome");
            System.out.println("4. Pesquisar contato por telefone");
            System.out.println("5. Remover contato por telefone");
            System.out.println("6. Alterar dados de contato");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opcao: ");

            String opcao = scanner.nextLine().trim();

            switch (opcao) {
                case "1":
                    adicionarArquivo();
                    break;
                case "2":
                    adicionarContato();
                    break;
                case "3":
                    pesquisarPorNome();
                    break;
                case "4":
                    pesquisarPorTelefone();
                    break;
                case "5":
                    removerPorTelefone();
                    break;
                case "6":
                    alterarContato();
                    break;
                case "0":
                    System.out.println("Total de contatos: " + contatoService.quantidadeDeContatos());
                    executando = false;
                    break;
                default:
                    System.out.println("Opcao invalida!");
                    break;
            }
        }
    }

    private void removerPorTelefone() {
        System.out.println("== REMOVER CONTATO POR TELEFONE ==");

        System.out.print("Digite telefone do contato que deseja remover: ");
        String telefone = scanner.nextLine();

        long inicio = System.nanoTime();
        boolean res = contatoService.removerPorTelefone(telefone);
        long fim = System.nanoTime();

        double tempoMs = (fim - inicio) / 1_000_000.0;

        if (res == false) {
            exibirMensagemErroERetornar("ERRO: Contato com telefone informado não existe. \nTempo (ms): " + tempoMs);
            return;
        }

        exibirSucessoERetornar("Contato removido com sucesso. \nTempo (ms): " + tempoMs);
    }

    private void pesquisarPorTelefone() {
        System.out.println("== PESQUISAR CONTATO POR TELEFONE ==");

        System.out.print("Digite telefone do contato que deseja buscar: ");
        String telefone = scanner.nextLine();

        long inicio = System.nanoTime();
        Contato contato = contatoService.buscarPorTelefone(telefone);
        long fim = System.nanoTime();

        double tempoMs = (fim - inicio) / 1_000_000.0;

        if (contato == null) {
            exibirMensagemErroERetornar("ERRO: Contato com telefone informado não existe. \nTempo Gasto (ms): " + tempoMs);
            return;
        }

        exibirSucessoERetornar("Nome: " + contato.getNome() + "\nTelefone: " + contato.getTelefone() + "\nTempo Gasto (ms): " + tempoMs);
    }

    private void pesquisarPorNome() {
        System.out.println("== PESQUISAR CONTATO POR NOME ==");

        System.out.print("Digite nome do contato que deseja buscar: ");
        String nome = scanner.nextLine();

        long inicio = System.nanoTime();
        Contato contato = contatoService.buscarPorNome(nome);
        long fim = System.nanoTime();

        double tempoMs = (fim - inicio) / 1_000_000.0;

        if (contato == null) {
            exibirMensagemErroERetornar("ERRO: Contato com nome informado não existe. \nTempo Gasto (ms): " + tempoMs);
            return;
        }

        exibirSucessoERetornar("Nome: " + contato.getNome() + "\nTelefone: " + contato.getTelefone() + "\nTempo Gasto (ms): " + tempoMs);
    }

    private void adicionarContato() {
        System.out.println("== ADICIONAR CONTATO ==");

        System.out.print("Digite nome do contato que deseja inserir: ");
        String nome = scanner.nextLine();

        System.out.print("Digite telefone do contato que deseja inserir: ");
        String telefone = scanner.nextLine();

        boolean res = contatoService.adicionarContato(nome, telefone);

        if (!res) {
            exibirMensagemErroERetornar("ERRO: Telefones repetidos não podem ser colocados.");
        }

        exibirSucessoERetornar("Contato inserido com sucesso.");
    }

    private void adicionarArquivo() {
        System.out.println("== CARREGAR DADOS DE ARQUIVO ==");
        System.out.print("Digite o caminho do arquivo (Enter para padrão 'dados/arquivo1000.txt'): ");
        String caminho = scanner.nextLine().trim();

        if (caminho.isEmpty()) {
            caminho = "dados/arquivo1000.txt";
        }

        if (!new java.io.File(caminho).exists()) {
            if (new java.io.File("dados/" + caminho).exists()) {
                caminho = "dados/" + caminho;
            } else if (new java.io.File("app/dados/" + caminho).exists()) {
                caminho = "app/dados/" + caminho;
            }
        }

        long inicio = System.nanoTime();
        boolean res = contatoService.adicionarContatos(caminho);
        long fim = System.nanoTime();

        double tempoMs = (fim - inicio) / 1_000_000.0;

        if (!res) {
            exibirMensagemErroERetornar("ERRO: Alguma coisa não saiu como planejada. \nTempo decorrido (ms): " + tempoMs);
            return;
        }

        exibirSucessoERetornar("Arquivo lido e inserido com sucesso. Tempo decorrido (ms): " + tempoMs);
    }

    private void alterarContato() {
        limparTela();
        System.out.println("== ALTERAR CONTATO ==");

        System.out.print("Digite nome do contato que deseja alterar: ");
        String nome = scanner.nextLine();

        Contato contato = contatoService.buscarPorNome(nome);
        if (contato == null) {
            exibirMensagemErroERetornar("ERRO: A alteração não foi finalizada com sucesso pois o Nome não foi encontrado");
            return;
        }

        String telefoneAntigo = contato.getTelefone();

        System.out.println("Antigo Telefone: " + telefoneAntigo);

        System.out.print("Digite novo nome do contato: ");
        String novoNome = scanner.nextLine();

        System.out.print("Digite novo telefone do contato: ");
        String novoTelefone = scanner.nextLine();

        boolean sucesso = contatoService.alterarDadosContato(nome, novoTelefone, novoNome);

        if (!sucesso) {
            exibirMensagemErroERetornar("ERRO: A alteração não foi finalizada com sucesso pois o Nome não foi encontrado");
            return;
        }

        exibirSucessoERetornar("Contato alterado com sucesso!");
    }


    private void setup() {
        System.out.println("Você deseja usar uma lista ORDENADA? (S/N)");
        boolean ordenada = scanner.nextLine().trim().equalsIgnoreCase("S");

        // ODEIO ter que iniciar o repository aqui... mas enfim...
        contatoService = new ContatoService(new ContatoRepository(ordenada));
    }

    private void exibirMensagemErroERetornar(String mensagem) {
        System.out.println("\n" + mensagem);
        System.out.println("Pressione qualquer tecla (Enter) para voltar ao menu principal...");
        scanner.nextLine();
        limparTela();
    }

    private void exibirSucessoERetornar(String mensagem) {
        System.out.println("\n" + mensagem);
        System.out.println("Pressione Enter para voltar ao menu principal...");
        scanner.nextLine();
        limparTela();
    }

    // isso também foi a IA
    private void splashScreen() {
        System.out.println("==========================================================================");
        System.out.println(" __  __ _   _ ____       _    ____  ____  _____ ____  _____ _   _ _____ _    __  __ ");
        System.out.println("|  \\/  | | | |  _ \\     / \\  |  _ \\|  _ \\| ____/ ___|| ____| \\ | |_   _/ \\  |  \\/  |");
        System.out.println("| |\\/| | |_| | |_) |   / _ \\ | |_) | |_) |  _| \\___ \\|  _| |  \\| | | |/ _ \\ | |\\/| |");
        System.out.println("| |  | |  _  |  _ <   / ___ \\|  __/|  _ <| |___ ___) | |___| |\\  | | / ___ \\| |  | |");
        System.out.println("|_|  |_|_| |_|_| \\_\\ /_/   \\_\\_|   |_| \\_\\_____|____/|_____|_| \\_| |_/_/   \\_\\_|  |_|");
        System.out.println("==========================================================================");
        System.out.println("                     SISTEMA DE GESTAO DE CONTATOS                        ");
        System.out.println("                              Carregando...                               ");
        System.out.println("==========================================================================");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
