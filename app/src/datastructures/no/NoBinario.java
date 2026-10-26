package datastructures.no;

public class NoBinario<T> implements INo<T>{
    private T valor;
    private NoBinario<T> esq;
    private NoBinario<T> dir;

    private int altura;

    public NoBinario(T valor) {
        this.valor = valor;
        this.altura = 0;
    }

    @Override
    public T getValor() {
        return valor;
    }

    public NoBinario<T> getEsq() {
        return esq;
    }

    public NoBinario<T> getDir() {
        return dir;
    }

    @Override
    public void setValor(T valor) {
        this.valor = valor;
    }

    public void setEsq(NoBinario no) {
        this.esq = no;
    }

    public void setDir(NoBinario no) {
        this.dir = no;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public void atualizarAltura() {
        int hd = (dir == null) ? -1 : dir.altura;
        int he = (esq == null) ? -1 : esq.altura;
        this.altura = 1 + (hd > he ? hd : he);
    }

    public int obterAltura() {
        return this.altura;
    }

    public int fatorBalanceamento() {
        int hd = (dir == null) ? -1 : dir.altura;
        int he = (esq == null) ? -1 : esq.altura;
        return hd - he;
    }
}
