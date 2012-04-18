package plp.mixin.memoria;

import plp.orientadaObjetos1.comando.Procedimento;
import plp.orientadaObjetos1.declaracao.procedimento.DecProcedimento;
import plp.orientadaObjetos1.excecao.declaracao.ProcedimentoNaoDeclaradoException;
import plp.orientadaObjetos1.expressao.leftExpression.Id;
import plp.orientadaObjetos1.util.Tipo;

/**
 * Uma definiçao de classe é uma declaraçao de variável e uma declaração de
 * procedimento. Ambos podem ser simples ou compostos.
 */
public class DefCategoria {

	/**
	 * Declaraçao do Procedimento
	 */
	protected DecProcedimento decProcedimento;

	/**
	 * Declaracao de id
	 */
	protected Id idCategoria;

	public DefCategoria(Id idCategoria, DecProcedimento decProcedimento) {
		this.idCategoria = idCategoria;
		this.decProcedimento = decProcedimento;
	}


	/**
	 * Retorna um método da classe a partir de seu identificador.
	 * 
	 * @param idMetodo
	 *            Identificador do método
	 * @return o método desejado
	 * @throws ProcedimentoNaoDeclaradoException
	 */
	public Procedimento getMetodo(Id idMetodo)
			throws ProcedimentoNaoDeclaradoException {
		return decProcedimento.getProcedimento(idMetodo);
	}

	public Id getIdCategoria() {
		return idCategoria;
	}
}