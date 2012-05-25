package plp.mixin.memoria;

import java.util.List;

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
	
	private List<DefCategoria> categorias;

	/**
	 * Consturtor
	 */
	private DecConstrutor construtor;

	public DefClasseOO2(Id idClasse, Id nomeSuperClasse,List<DefCategoria> categorias,DecVariavel decVariavel,
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

	public void setCategorias(List<DefCategoria> categorias) {
		this.categorias = categorias;
	}

	public List<DefCategoria> getCategorias() {
		return categorias;
	}
}
