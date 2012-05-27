package plp.mixin.memoria;

import java.util.ArrayList;
import java.util.List;

import plp.orientadaObjetos1.comando.Procedimento;
import plp.orientadaObjetos1.declaracao.procedimento.DecProcedimento;
import plp.orientadaObjetos1.excecao.declaracao.ProcedimentoNaoDeclaradoException;
import plp.orientadaObjetos1.expressao.leftExpression.Id;
import plp.orientadaObjetos1.util.Tipo;

/**
 * Uma defini�ao de categoria � uma declara�ao de vari�vel e uma declara��o de
 * procedimento. Ambos podem ser simples ou compostos.
 */
public class DefCategoria {

	/**
	 * Declara�ao do Procedimento
	 */
	protected DecProcedimento decProcedimento;
	
	protected DecProcedimento assinaturas;
	
	/**
	 * Declaracao de id
	 */
	protected Id idCategoria;

	public DefCategoria(Id idCategoria, DecProcedimento assinaturas,DecProcedimento decProcedimento) {
		this.idCategoria = idCategoria;
		this.assinaturas = assinaturas;
		this.decProcedimento = decProcedimento;
	}


	/**
	 * Retorna um m�todo da classe a partir de seu identificador.
	 * 
	 * @param idMetodo
	 *            Identificador do m�todo
	 * @return o m�todo desejado
	 * @throws ProcedimentoNaoDeclaradoException
	 */
	public Procedimento getMetodo(Id idMetodo)
			throws ProcedimentoNaoDeclaradoException {
		
		try {
			return decProcedimento.getProcedimento(idMetodo);
		} catch (Exception e) {
			return assinaturas.getProcedimento(idMetodo);
		}
	}

	public Id getIdCategoria() {
		return idCategoria;
	}
	
	public List<String> getListProcedimentoNomes() {		
		return decProcedimento.getListProcedimentoNomes();
	}
	
	public List<String> getListAssinaturaNomes() {		
		return assinaturas.getListAssinaturaNomes();
	}

}