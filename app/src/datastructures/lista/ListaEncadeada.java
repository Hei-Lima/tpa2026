package datastructures.lista;

import datastructures.no.No;

import java.util.Comparator;

public class ListaEncadeada<T> implements ILista<T> {
    private No<T> prim, ult;
    private Comparator<T> comparator;
    private boolean isOrdenada = false;
    private int quant;


    public ListaEncadeada() {
    }

    public ListaEncadeada(boolean isOrdenada, Comparator<T> comparator) {
        this.isOrdenada = isOrdenada;
        this.comparator = comparator;
    }


    @Override
    public boolean adicionar(T novoValor) {
        No<T> novoNo = new No<>(novoValor);

        if (prim == null) {
            prim = novoNo;
            ult = novoNo;
            quant++;
            return true;
        }

        if (this.isOrdenada) {
            return adicionarOrdenada(novoNo);
        }

        ult.setProx(novoNo);
        ult = novoNo;
        quant++;

        return true;
    }

    private boolean adicionarOrdenada(No<T> novoNo) {
        No<T> noAtual = prim;
        No<T> ultimoNo = null;

        while (noAtual != null) {
            if (comparator.compare(novoNo.getValor(), noAtual.getValor()) <= 0) {
                novoNo.setProx(noAtual);

                if (noAtual == prim) {
                    prim = novoNo;
                } else {
                    ultimoNo.setProx(novoNo);
                }

                quant++;
                return true;
            }

            ultimoNo = noAtual;
            noAtual = noAtual.getProx();
        }

        ultimoNo.setProx(novoNo);
        ult = novoNo;
        quant++;
        return true;
    }


    @Override
    public T pesquisar(T valor) {
        No<T> noAtual = prim;

        while (noAtual != null && !noAtual.getValor().equals(valor)) {
            noAtual = noAtual.getProx();
        }

        if (noAtual != null) return noAtual.getValor();
        return null;
    }

    @Override
    public boolean remover(T valor) {
        if (prim == null) {
            return false;
        }

        No<T> noAtual = prim;
        No<T> ultimoNo = null;

        while (noAtual != null && !noAtual.getValor().equals(valor)) {
            ultimoNo = noAtual;
            noAtual = noAtual.getProx();
        }

        if (noAtual == null) return false;

        if (noAtual == prim) {
            prim = prim.getProx();
            if (prim == null) {
                ult = null;
            }
        } else {
            ultimoNo.setProx(noAtual.getProx());
            if (noAtual == ult) {
                ult = ultimoNo;
            }
        }

        quant--;
        return true;
    }

    @Override
    public int quantidadeNos() {
        return quant;
    }

    @Override
    public String toString() {
        if (quant == 0) return "[]";

        String end = "]";
        String string = "[";
        No<T> noAtual = prim;

        for (int i = 0; i < quant - 1; i++) {
            string = string + noAtual.getValor().toString() + ", ";
            noAtual = noAtual.getProx();
        }

        string = string + noAtual.getValor().toString();

        return string + end;
    }
}
