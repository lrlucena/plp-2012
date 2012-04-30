package plp.mixin.excecao.declaracao;

import plp.expressions2.expression.Id;
import plp.orientadaObjetos1.excecao.declaracao.ClasseNaoDeclaradaException;

/**
 * Exceção lançada quando uma classe que está sendo referenciada
 * não foi declarada anteriormente.
 */
public class CategoriaNaoDeclaradaException extends ClasseNaoDeclaradaException {
    /**
     * Construtor
     * @param id Identificador representando a classe.
     */
    public CategoriaNaoDeclaradaException(Id id) {
        super("Categoria " + id + " não declarada.");
    }
}