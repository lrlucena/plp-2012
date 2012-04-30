package plp.mixin.declaracao;

import plp.expressions2.memory.VariavelJaDeclaradaException;
import plp.expressions2.memory.VariavelNaoDeclaradaException;
import plp.imperative1.util.Lista;
import plp.orientadaObjetos1.declaracao.Declaracao;
import plp.orientadaObjetos1.declaracao.classe.DecClasse;
import plp.mixin.declaracao.classe.DecCategoria;
import plp.orientadaObjetos1.excecao.declaracao.ClasseJaDeclaradaException;
import plp.orientadaObjetos1.excecao.declaracao.ClasseNaoDeclaradaException;
import plp.orientadaObjetos1.excecao.declaracao.ProcedimentoJaDeclaradoException;
import plp.orientadaObjetos1.excecao.declaracao.ProcedimentoNaoDeclaradoException;
import plp.mixin.declaracao.classe.DecClasseSimplesOO2;
import plp.mixin.memoria.AmbienteCompilacaoOO2;
import plp.mixin.memoria.AmbienteExecucaoOO2;


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

	public ListaDeclaracaoOO(DecCategoria decOO) {
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

	public ListaDeclaracaoOO(DecCategoria decOO, ListaDeclaracaoOO lista) {
		super(decOO, lista);
	}

	public AmbienteExecucaoOO2 elabora(AmbienteExecucaoOO2 ambiente)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
			ClasseJaDeclaradaException, ClasseNaoDeclaradaException,
			ProcedimentoNaoDeclaradoException, ProcedimentoJaDeclaradoException, ConstrutorNaoDeclaradoException {

		if (length() == 1) {
			DecClasseSimplesOO2 classe = (DecClasseSimplesOO2) getHead();
			classe.elabora(ambiente);
		} else {
			DecClasseSimplesOO2 classe = (DecClasseSimplesOO2) getHead();
			classe.elabora(ambiente);
			((ListaDeclaracaoOO)getTail()).elabora(ambiente);
		}

		return ambiente;
	}

	public boolean checaTipo(AmbienteCompilacaoOO2 ambiente)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
			ClasseJaDeclaradaException, ClasseNaoDeclaradaException,
			ProcedimentoNaoDeclaradoException, ProcedimentoJaDeclaradoException, ConstrutorNaoDeclaradoException {

		boolean ret = false;
		if (length() == 1) {
			DecClasseSimplesOO2 classe = (DecClasseSimplesOO2) getHead();
			ret = classe.checaTipo(ambiente);
		} else {
			DecClasseSimplesOO2 classe = (DecClasseSimplesOO2) getHead();
			ret = classe.checaTipo(ambiente);
			if (ret)
				ret = ((ListaDeclaracaoOO)getTail()).checaTipo(ambiente);
		}

		return ret;
	}
}
