package plp.mixin.excecao.declaracao;

import plp.expressions2.expression.Id;
import plp.orientadaObjetos1.excecao.declaracao.ClasseJaDeclaradaException;

public class CategoriaJaDeclaradaException extends ClasseJaDeclaradaException {

    public CategoriaJaDeclaradaException(Id id) {
        super("Categoria " + id + " já declarada.");
    }
}
