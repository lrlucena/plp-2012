package plp.mixin.memoria;

import plp.orientadaObjetos1.declaracao.procedimento.DecProcedimento;
import plp.orientadaObjetos1.declaracao.variavel.DecVariavel;
import plp.orientadaObjetos1.expressao.leftExpression.Id;
import plp.orientadaObjetos1.memoria.DefClasse;
import plp.mixin.declaracao.DecConstrutor;
import plp.mixin.util.ListaID;

public class DefClasseOO2 extends DefClasse {

	/**
	 * Nome super classe
	 */
	private Id nomeSuperClasse;
	
	private ListaID categorias;

	/**
	 * Consturtor
	 */
	private DecConstrutor construtor;

	public DefClasseOO2(Id idClasse, Id nomeSuperClasse,ListaID categorias,DecVariavel decVariavel,
			DecConstrutor construtor, DecProcedimento decProcedimento) {
		super(idClasse, decVariavel, decProcedimento);
		this.nomeSuperClasse = nomeSuperClasse;
		this.construtor = construtor;
		this.setCategorias(categorias);
	}

	public DecConstrutor getConstrutor() {
		return construtor;
	}

	public void setConstrutor(DecConstrutor construtor) {
		this.construtor = construtor;
	}

	/**
	 * @return the nomeSuperClasse
	 */
	public Id getNomeSuperClasse() {
		return nomeSuperClasse;
	}

	/**
	 * @param nomeSuperClasse the nomeSuperClasse to set
	 */
	public void setNomeSuperClasse(Id nomeSuperClasse) {
		this.nomeSuperClasse = nomeSuperClasse;
	}

	public void setCategorias(ListaID categorias) {
		this.categorias = categorias;
	}

	public ListaID getCategorias() {
		return categorias;
	}
}
