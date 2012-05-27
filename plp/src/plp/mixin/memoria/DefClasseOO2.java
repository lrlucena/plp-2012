package plp.mixin.memoria;

import java.util.List;

import plp.orientadaObjetos1.comando.Procedimento;
import plp.orientadaObjetos1.declaracao.procedimento.DecProcedimento;
import plp.orientadaObjetos1.declaracao.variavel.DecVariavel;
import plp.orientadaObjetos1.excecao.declaracao.ClasseNaoDeclaradaException;
import plp.orientadaObjetos1.excecao.declaracao.ProcedimentoNaoDeclaradoException;
import plp.orientadaObjetos1.expressao.leftExpression.Id;
import plp.orientadaObjetos1.memoria.AmbienteCompilacaoOO1;
import plp.orientadaObjetos1.memoria.AmbienteExecucaoOO1;
import plp.orientadaObjetos1.memoria.DefClasse;
import plp.expressions2.memory.Ambiente;
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
	
	public Procedimento getProcedimentoHierarquia (Ambiente ambiente, Id nomeMetodo, boolean verificarAssinaturas) throws ClasseNaoDeclaradaException, ProcedimentoNaoDeclaradoException {
		Procedimento metodo = null;

		try {
			metodo = this.getMetodo(nomeMetodo);
		} catch (ProcedimentoNaoDeclaradoException e) {
			
			List<DefCategoria> defCategorias;
			
			if(this.getCategorias() != null){
				defCategorias = this.getCategorias();
				for (int i = 0; i < defCategorias.size(); i++) {
					try {
						metodo = defCategorias.get(i).getMetodo(nomeMetodo);
						
						if(verificarAssinaturas == false && metodo.isAssinatura()){
							metodo = null;
						}
						
					} catch (ProcedimentoNaoDeclaradoException e2) {
						// TODO: handle exception
					}
				}
				
			}
			
			if(metodo == null) {
			
				if (this.getNomeSuperClasse() != null) {
					if (ambiente instanceof AmbienteCompilacaoOO1) {
						AmbienteCompilacaoOO1 ambienteCompilacao = (AmbienteCompilacaoOO1) ambiente;
						DefClasseOO2 defClasseMae = (DefClasseOO2) ambienteCompilacao
								.getDefClasse(this.getNomeSuperClasse());
						metodo = defClasseMae.getProcedimentoHierarquia(ambiente,
								nomeMetodo, verificarAssinaturas);
					} else if (ambiente instanceof AmbienteExecucaoOO1) {
						AmbienteExecucaoOO1 ambienteExecucao = (AmbienteExecucaoOO1) ambiente;
						DefClasseOO2 defClasseMae = (DefClasseOO2) ambienteExecucao
								.getDefClasse(this.getNomeSuperClasse());
						metodo = defClasseMae.getProcedimentoHierarquia(ambiente,
								nomeMetodo, verificarAssinaturas);
					}
				}
			}
		}
		if (metodo == null) {
			throw new ProcedimentoNaoDeclaradoException(nomeMetodo);
		}
		return metodo;
	}
}
