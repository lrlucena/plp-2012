package plp.mixin.excecao.declaracao;

import plp.expressions2.expression.Id;

public class CategoriaJaDeclaradaException extends Exception {

    public CategoriaJaDeclaradaException(Id id) {
        super("Categoria " + id + " já declarada.");
    }
}
