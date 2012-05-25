package plp.mixin.declaracao;

import plp.expressions2.memory.VariavelJaDeclaradaException;
import plp.expressions2.memory.VariavelNaoDeclaradaException;
import plp.imperative1.util.Lista;
import plp.mixin.declaracao.classe.DecCategoriaSimples;
import plp.orientadaObjetos1.declaracao.classe.DecClasse;
import plp.orientadaObjetos1.excecao.declaracao.ClasseJaDeclaradaException;
import plp.orientadaObjetos1.excecao.declaracao.ClasseNaoDeclaradaException;
import plp.orientadaObjetos1.excecao.declaracao.ProcedimentoJaDeclaradoException;
import plp.orientadaObjetos1.excecao.declaracao.ProcedimentoNaoDeclaradoException;
import plp.mixin.declaracao.classe.DecClasseSimplesOO2;
import plp.mixin.memoria.AmbienteCompilacaoMixin;
import plp.mixin.memoria.AmbienteExecucaoMixin;
import plp.orientadaObjetos1.declaracao.Declaracao;
import plp.orientadaObjetos1.declaracao.classe.DecClasseSimples;

public class ListaDeclaracaoOO extends Lista<Declaracao> {
	/**
	 * Construtor.
	 */
	public ListaDeclaracaoOO() {
	}

	/**
	 * Construtor.
	 * 
	 * @param DeclaracaoOO
	 *            DeclaracaoOO que compoe a tail.
	 */
	public ListaDeclaracaoOO(DecClasse decOO) {
		super(decOO, new ListaDeclaracaoOO());
	}

	public ListaDeclaracaoOO(DecCategoriaSimples decOO) {
		super(decOO, new ListaDeclaracaoOO());
	}

	/**
	 * Construtor.
	 * 
	 * @param declaracaoOO
	 *            Primeira declaracao da tail.
	 * @param listaDeclaracaoOO
	 *            Restante da tail de declaracoes.
	 */
	public ListaDeclaracaoOO(DecClasse decOO, ListaDeclaracaoOO lista) {
		super(decOO, lista);
	}

	public ListaDeclaracaoOO(DecCategoriaSimples decOO, ListaDeclaracaoOO lista) {
		super(decOO, lista);
	}

	public AmbienteExecucaoMixin elabora(AmbienteExecucaoMixin ambiente)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
			ClasseJaDeclaradaException, ClasseNaoDeclaradaException,
			ProcedimentoNaoDeclaradoException,
			ProcedimentoJaDeclaradoException, ConstrutorNaoDeclaradoException {

		if (length() == 1) {
			Declaracao d = getHead();
			if (d instanceof DecCategoriaSimples) {
				((DecCategoriaSimples) d).elabora(ambiente);
			} else {
				((DecClasseSimplesOO2) d).elabora(ambiente);
			}
			
			// DecClasseSimplesOO2 classe = (DecClasseSimplesOO2) getHead();
			// classe.elabora(ambiente);
		} else {
			Declaracao d = getHead();
			if (d instanceof DecCategoriaSimples) {
				((DecCategoriaSimples) d).elabora(ambiente);
			} else {
				((DecClasseSimplesOO2) d).elabora(ambiente);
			}
			((ListaDeclaracaoOO) getTail()).elabora(ambiente);
			
//			DecClasseSimplesOO2 classe = (DecClasseSimplesOO2) getHead();
//			classe.elabora(ambiente);
//			((ListaDeclaracaoOO) getTail()).elabora(ambiente);
		}

		return ambiente;
	}

	public boolean checaTipo(AmbienteCompilacaoMixin ambiente)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
			ClasseJaDeclaradaException, ClasseNaoDeclaradaException,
			ProcedimentoNaoDeclaradoException,
			ProcedimentoJaDeclaradoException, ConstrutorNaoDeclaradoException{

		boolean ret = false;
		if (length() == 1) {
			Declaracao d = getHead();
			if (d instanceof DecCategoriaSimples) {
				ret = ((DecCategoriaSimples) d).checaTipo(ambiente);
			} else {
				ret = ((DecClasseSimplesOO2) d).checaTipo(ambiente);
			}
		} else {
			Declaracao d = getHead();
			if (d instanceof DecCategoriaSimples) {
				ret = ((DecCategoriaSimples) d).checaTipo(ambiente);
			} else {
				ret = ((DecClasseSimplesOO2) d).checaTipo(ambiente);
			}
			if (ret)
				ret = ((ListaDeclaracaoOO) getTail()).checaTipo(ambiente);
		}

		return ret;
	}
}
