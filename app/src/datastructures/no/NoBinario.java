package datastructures.no;

public class NoBinario<T> implements INo<T>{
    private T valor;
    private NoBinario<T> esq;
    private NoBinario<T> dir;

    public NoBinario(T valor) {
        this.valor = valor;
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
}
