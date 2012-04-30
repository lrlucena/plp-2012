package plp.mixin.memoria;

import java.util.ArrayList;
import java.util.HashMap;

import plp.expressions2.expression.Id;
import plp.orientadaObjetos1.excecao.declaracao.ClasseNaoDeclaradaException;
import plp.mixin.excecao.declaracao.CategoriaJaDeclaradaException;
import plp.orientadaObjetos1.memoria.ContextoCompilacaoOO1;
import plp.orientadaObjetos1.memoria.DefClasse;
import plp.orientadaObjetos1.memoria.colecao.ListaValor;
import plp.orientadaObjetos2.util.SuperClasseMap;

public class ContextoCompilacaoMixin extends ContextoCompilacaoOO1 implements AmbienteCompilacaoMixin{

	private ArrayList<SuperClasseMap> arraySuperClasse;

        private HashMap<Id, DefCategoria> mapDefCategoria;

	public ContextoCompilacaoMixin(ListaValor entrada) {
		super(entrada);
		arraySuperClasse = new ArrayList <SuperClasseMap> ();
                mapDefCategoria = new HashMap<Id, DefCategoria>();  //cria mapeamento ids def categoria
	}

        public void mapDefCategoria(Id idArg, DefCategoria defCategoria) throws CategoriaJaDeclaradaException {
          if (mapDefCategoria.put(idArg, defCategoria) != null) {
             throw new CategoriaJaDeclaradaException(idArg);
          }
        }

	/**
	 * Mapeia o id da sub-classe em uma super-classe.
	 */
	public void mapSuperClasse(Id classe, Id superClasse) throws ClasseNaoDeclaradaException {
		DefClasse defClasse = getDefClasse(superClasse);
		if (defClasse != null) {
			arraySuperClasse.add(new SuperClasseMap( classe, superClasse ));
		}
	}

	/**
	 * Dado o id de uma classe,
	 * recupera a definicao da super-classe.
	 */
	public SuperClasseMap getSuperClasse(Id classe) throws ClasseNaoDeclaradaException {
		for(int i=0; i < arraySuperClasse.size(); i++){
			String nomeClasse = arraySuperClasse.get(i).getClasse().toString();

			if(nomeClasse.equalsIgnoreCase( classe.toString() )) {
				return arraySuperClasse.get(i);
			}
		}
		return null;
	}

}
