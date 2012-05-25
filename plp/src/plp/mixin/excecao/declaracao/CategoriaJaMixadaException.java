package plp.mixin.excecao.declaracao;

import plp.expressions2.expression.Id;
import plp.orientadaObjetos1.excecao.declaracao.ClasseJaDeclaradaException;

public class CategoriaJaMixadaException extends ClasseJaDeclaradaException {

	public CategoriaJaMixadaException(String str) {
        super(str);
    }

    /**
     * Construtor
     * @param id Identificador representando a classe.
     */
    public CategoriaJaMixadaException(Id id) {
        super("Categoria " + id + " já mixada.");
    }
}
