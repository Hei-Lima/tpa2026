package datastructures.arvorebinaria;
import datastructures.no.NoBinario;

import java.util.Comparator;

public class ArvoreBinaria<T> extends ArvoreBinariaBase<T>{
    private NoBinario<T> raiz;

    public NoBinario<T> getRaiz() {
        return raiz;
    }

    public void setRaiz(NoBinario<T> raiz) {
        this.raiz = raiz;
    }

    public ArvoreBinaria(Comparator<T> comparador) {
        super(comparador);
    }

    @Override
    public int altura() {
        if (raiz == null) {
            return 0;
        }
        return altura(this.raiz);
    }

    private int altura(NoBinario<T> no) {
        if (no == null) {
            return -1;
        }
        return 1 + Math.max(altura(no.getEsq()), altura(no.getDir()));
    }

    @Override
    public String caminharEmNivel() {
        if (raiz == null) {
            return "[]";
        }

        String string = "[\n";
        java.util.Queue<NoBinario<T>> fila = new java.util.LinkedList<>();
        fila.add(raiz);

        while (!fila.isEmpty()) {
            int nivelTamanho = fila.size();
            for (int i = 0; i < nivelTamanho; i++) {
                NoBinario<T> atual = fila.poll();
                string = string + atual.getValor().toString();
                if (i < nivelTamanho - 1) {
                    string = string + " ";
                }
                if (atual.getEsq() != null) {
                    fila.add(atual.getEsq());
                }
                if (atual.getDir() != null) {
                    fila.add(atual.getDir());
                }
            }
            string = string + "\n";
        }
        string = string + "]";
        return string;
    }

    @Override
    public String caminharEmOrdem() {
        if (raiz == null) {
            return "[]";
        }
        return "[" + caminharEmOrdem(this.raiz) + "]";
    }

    private String caminharEmOrdem(NoBinario<T> no) {
        if (no == null) {
            return "";
        }

        String esq = caminharEmOrdem(no.getEsq());
        String dir = caminharEmOrdem(no.getDir());

        String res = "";
        if (!esq.isEmpty()) {
            res = res + esq + ", ";
        }
        res = res + no.getValor().toString();
        if (!dir.isEmpty()) {
            res = res + ", " + dir;
        }

        return res;
    }

    protected int quant;

    public boolean adicionar(T novoValor) {
        if (raiz == null) {
            setRaiz(new NoBinario<T>(novoValor));
            quant++;
            return true;
        }

        boolean ok = adicionar(novoValor, getRaiz());
        if (ok) quant++;
        return ok;
    }

    private boolean adicionar(T novoValor, NoBinario<T> no) {
        int comp = comparador.compare(novoValor, no.getValor());

        if (comp <= 0) {
            if (no.getEsq() == null) {
                no.setEsq(new NoBinario<>(novoValor));
                return true;
            }
            return adicionar(novoValor, no.getEsq());
        } else {
            if (no.getDir() == null) {
                no.setDir(new NoBinario<>(novoValor));
                return true;
            }
            return adicionar(novoValor, no.getDir());
        }
    }

    @Override
    public T pesquisar(T valor) {
        return pesquisar(valor, this.raiz);
    }

    public T pesquisar(T valor, NoBinario<T> no) {
        if (no == null) {
            return null;
        }

        if (no.getValor().equals(valor)) {
            return no.getValor();
        }

        int comp = comparador.compare(valor, no.getValor());

        if (comp <= 0) {
            return pesquisar(valor, no.getEsq());
        } else {
            return pesquisar(valor, no.getDir());
        }

    }

    @Override
    public boolean remover(T valor) {
        if (pesquisar(valor) == null) {
            return false;
        }

        this.raiz = remover(valor, this.raiz);
        quant--;
        return true;
    }

    private NoBinario<T> remover(T valor, NoBinario<T> no) {
        if (no == null) {
            return null;
        }

        if (no.getValor().equals(valor)) {
            if (no.getEsq() == null) {
                return no.getDir();
            }
            if (no.getDir() == null) {
                return no.getEsq();
            }

            NoBinario<T> substituto = maiorElemento(no.getEsq());
            no.setValor(substituto.getValor());
            no.setEsq(remover(substituto.getValor(), no.getEsq()));
            return no;
        }

        int comp = comparador.compare(valor, no.getValor());

        if (comp <= 0) {
            no.setEsq(remover(valor, no.getEsq()));
        } else {
            no.setDir(remover(valor, no.getDir()));
        }

        return no;
    }

    private NoBinario<T> maiorElemento(NoBinario<T> no) {
        while (no.getDir() != null) {
            no = no.getDir();
        }
        return no;
    }

    @Override
    public int quantidadeNos() {
        return quant;
    }
}
