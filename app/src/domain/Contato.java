package domain;

public class Contato {
    private String nome;
    private String telefone;

    public Contato(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return this.nome + " - " + this.telefone;
    }

    // USEI IA no override do equals.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contato contato = (Contato) o;

        if (this.telefone != null && contato.telefone != null && this.telefone.equals(contato.telefone)) {
            return true;
        }

        if (this.nome != null && contato.nome != null && this.nome.equalsIgnoreCase(contato.nome)) {
            return true;
        }

        return false;
    }
}
