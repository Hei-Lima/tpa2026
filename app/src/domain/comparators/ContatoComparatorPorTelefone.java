package domain.comparators;

import domain.Contato;

import java.util.Comparator;

public class ContatoComparatorPorTelefone implements Comparator<Contato> {

    @Override
    public int compare(Contato o1, Contato o2) {
        return o1.getTelefone().compareTo(o2.getTelefone());
    }
}
