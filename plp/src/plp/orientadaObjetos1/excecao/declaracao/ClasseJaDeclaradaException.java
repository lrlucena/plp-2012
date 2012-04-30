package plp.orientadaObjetos1.excecao.declaracao;

import plp.expressions2.expression.Id;


/**
 * Exce��o lan�ada quando a classe que est� sendo declarada, j� o foi
 * anteriormente.
 */
public class ClasseJaDeclaradaException extends Exception {
    public ClasseJaDeclaradaException(String str) {
        super(str);
    }

    /**
     * Construtor
     * @param id Identificador representando a classe.
     */
    public ClasseJaDeclaradaException(Id id) {
        super("Classe " + id + " j� declarada.");
    }
}
