package datastructures.arvorebinaria;

import datastructures.no.NoBinario;

import java.util.Comparator;

public class ArvoreAVL<T> extends ArvoreBinaria<T> {

    public ArvoreAVL(Comparator<T> comparador) {
        super(comparador);
    }

    private NoBinario<T> rotacaoEsquerda(NoBinario<T> r) {
        NoBinario<T> f = r.getDir();
        r.setDir(f.getEsq());
        f.setEsq(r);
        return f;
    }

    private NoBinario<T> rotacaoDireita(NoBinario<T> r) {
        NoBinario<T> f = r.getEsq();
        r.setEsq(f.getDir());
        f.setDir(r);
        return f;
    }

    private NoBinario<T> rotacaoEsquerdaDireita(NoBinario<T> r) {
        // Faço uma rotação a esquerda no filho a esquerda
        r.setEsq(rotacaoEsquerda(r.getEsq()));
        // E depois uma rotação a direita na raiz
        return rotacaoDireita(r);
    }

    private NoBinario<T> rotacaoDireitaEsquerda(NoBinario<T> r) {
        // Faço uma rotação a Direita no filho a direita
        r.setDir(rotacaoDireita(r.getDir()));
        // E depois uma rotação a esquerda na raiz
        return rotacaoEsquerda(r);
    }

    private NoBinario<T> balancear(NoBinario<T> r) {
        if (r == null) {
            return null;
        }

        if (r.fatorBalanceamento() > 1) {
            if (r.getDir() != null && r.getDir().fatorBalanceamento() >= 0) {
                r = this.rotacaoEsquerda(r);
            } else {
                r = this.rotacaoDireitaEsquerda(r);
            }
        } else if (r.fatorBalanceamento() < -1) {
            if (r.getEsq() != null && r.getEsq().fatorBalanceamento() <= 0) {
                r = this.rotacaoDireita(r);
            } else {
                r = this.rotacaoEsquerdaDireita(r);
            }
        }
        return r;
    }

    @Override
    public boolean adicionar(T novoValor) {
        if (getRaiz() == null) {
            setRaiz(new NoBinario<T>(novoValor));
            quant++;
            return true;
        }

        setRaiz(adicionar(getRaiz(), novoValor));
        quant++;
        return true;
    }

    private NoBinario<T> adicionar(NoBinario<T> r, T novoValor) {
        if (r == null) {
            return new NoBinario<T>(novoValor);
        }

        int comp = comparador.compare(novoValor, r.getValor());

        if (comp <= 0) {
            r.setEsq(adicionar(r.getEsq(), novoValor));
        } else {
            r.setDir(adicionar(r.getDir(), novoValor));
        }

        return balancear(r);
    }

    private boolean removeu;

    @Override
    public boolean remover(T valor) {
        if (pesquisar(valor) == null) {
            return false;
        }

        removeu = false;
        setRaiz(remover(getRaiz(), valor));
        if (removeu) {
            quant--;
        }
        return removeu;
    }

    private NoBinario<T> remover(NoBinario<T> no, T valor) {
        if (no == null) {
            return null;
        }

        if (no.getValor().equals(valor)) {
            removeu = true;
            if (no.getEsq() == null) {
                return no.getDir();
            }
            if (no.getDir() == null) {
                return no.getEsq();
            }

            NoBinario<T> substituto = maiorElemento(no.getEsq());
            no.setValor(substituto.getValor());
            no.setEsq(remover(no.getEsq(), substituto.getValor()));
            return balancear(no);
        }

        int comp = comparador.compare(valor, no.getValor());

        if (comp <= 0) {
            no.setEsq(remover(no.getEsq(), valor));
        } else {
            no.setDir(remover(no.getDir(), valor));
        }

        return balancear(no);
    }

    private NoBinario<T> maiorElemento(NoBinario<T> no) {
        while (no.getDir() != null) {
            no = no.getDir();
        }
        return no;
    }
}
