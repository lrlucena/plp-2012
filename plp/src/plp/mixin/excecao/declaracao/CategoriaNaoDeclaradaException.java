package plp.mixin.excecao.declaracao;

import plp.expressions2.expression.Id;


/**
 * Exce��o lan�ada quando uma classe que est� sendo referenciada
 * n�o foi declarada anteriormente.
 */
public class CategoriaNaoDeclaradaException extends Exception {
    /**
     * Construtor
     * @param id Identificador representando a classe.
     */
    public CategoriaNaoDeclaradaException(Id id) {
        super("Categoria " + id + " n�o declarada.");
    }
}