package plp.mixin.util;

import java.util.ArrayList;
import java.util.List;

import plp.expressions2.memory.VariavelJaDeclaradaException;
import plp.expressions2.memory.VariavelNaoDeclaradaException;
import plp.imperative1.util.Lista;
import plp.orientadaObjetos1.excecao.declaracao.ClasseNaoDeclaradaException;
import plp.orientadaObjetos1.expressao.leftExpression.Id;
import plp.orientadaObjetos1.expressao.valor.Valor;
import plp.orientadaObjetos1.memoria.AmbienteCompilacaoOO1;
import plp.orientadaObjetos1.memoria.AmbienteExecucaoOO1;

/**
 * Uma lista de ids.
 */
public class ListaID extends Lista<Id> {

	List<Valor> result = new ArrayList<Valor>();

	/**
	 * Construtores.
	 */
	public ListaID() {

	}

	public ListaID(Id id) {
		super(id, null);
	}

	public ListaID(Id id, ListaID listaId) {
		super(id, listaId);
	}

	public List<Valor> avaliar(AmbienteExecucaoOO1 ambiente)
			throws VariavelJaDeclaradaException, ClasseNaoDeclaradaException,
			VariavelNaoDeclaradaException {

		if (getHead() != null) {
			if (getTail() != null) {
				result = ((ListaID) getTail()).avaliar(ambiente);
			} else {
				result.add(getHead().avaliar(ambiente));
			}
		}

		return result;
	}

	public boolean checaTipo(AmbienteCompilacaoOO1 ambiente)
			throws VariavelNaoDeclaradaException {
		
		boolean resposta = true;
		
        if(getHead() != null) {
            if(getTail() != null) {
                resposta = getHead().checaTipo(ambiente) &&
				 			((ListaID)getTail()).checaTipo(ambiente);
            }
            else {
                resposta = getHead().checaTipo(ambiente);
            }
        }
        
        return resposta;
	}

}