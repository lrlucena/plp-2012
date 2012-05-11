package plp.mixin.memoria;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import plp.expressions2.expression.Id;
import plp.imperative1.util.Lista;
import plp.orientadaObjetos1.excecao.declaracao.ClasseNaoDeclaradaException;
import plp.mixin.excecao.declaracao.CategoriaJaDeclaradaException;
import plp.mixin.excecao.declaracao.CategoriaNaoDeclaradaException;
import plp.mixin.util.ListaID;
import plp.orientadaObjetos1.memoria.ContextoCompilacaoOO1;
import plp.orientadaObjetos1.memoria.DefClasse;
import plp.orientadaObjetos1.memoria.colecao.ListaValor;
import plp.orientadaObjetos2.util.SuperClasseMap;

public class ContextoCompilacaoMixin extends ContextoCompilacaoOO1 implements
		AmbienteCompilacaoMixin {

	private ArrayList<SuperClasseMap> arraySuperClasse;

	private HashMap<Id, DefCategoria> mapDefCategoria;

	private HashMap<Id, List<DefCategoria>> mapMixinCategoria;

	public ContextoCompilacaoMixin(ListaValor entrada) {
		super(entrada);
		arraySuperClasse = new ArrayList<SuperClasseMap>();
		mapMixinCategoria = new HashMap<Id, List<DefCategoria>>();
		mapDefCategoria = new HashMap<Id, DefCategoria>(); // cria mapeamento
		// ids def categoria
	}

	public void mapDefCategoria(Id idArg, DefCategoria defCategoria)
			throws CategoriaJaDeclaradaException {

		// if(!mapDefCategoria.containsKey(idArg)){
		// mapDefCategoria.put(idArg, defCategoria);
		// } else {
		// throw new CategoriaJaDeclaradaException(idArg);
		// }

		if (mapDefCategoria.put(idArg, defCategoria) != null) {
			throw new CategoriaJaDeclaradaException(idArg);
		}
	}

	/**
	 * Mapeia o id da sub-classe em uma super-classe.
	 */
	public void mapSuperClasse(Id classe, Id superClasse)
			throws ClasseNaoDeclaradaException {

		DefClasse defClasse = getDefClasse(superClasse);

		if (defClasse != null) {
			arraySuperClasse.add(new SuperClasseMap(classe, superClasse));
		}
	}

	/**
	 * Dado o id de uma classe, recupera a definicao da super-classe.
	 */
	public SuperClasseMap getSuperClasse(Id classe)
			throws ClasseNaoDeclaradaException {
		for (int i = 0; i < arraySuperClasse.size(); i++) {
			String nomeClasse = arraySuperClasse.get(i).getClasse().toString();

			if (nomeClasse.equalsIgnoreCase(classe.toString())) {
				return arraySuperClasse.get(i);
			}
		}
		return null;
	}

	public List<DefCategoria> getClasseCategoria(Id classe)
			throws CategoriaNaoDeclaradaException {

		return mapMixinCategoria.get(classe);
	}

	public void mapClasseCategoria(Id classe, ListaID categorias)
			throws CategoriaNaoDeclaradaException {

		List<DefCategoria> listaCategorias = new ArrayList<DefCategoria>();

		try {
			for (int i = 0; i < categorias.length(); i++) {
				listaCategorias.add(getDefCategoria(categorias.get(i)));
			}
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}

		if (!listaCategorias.isEmpty()) {
			mapMixinCategoria.put(classe, listaCategorias);
		}

	}

	public DefCategoria getDefCategoria(Id idArg)
			throws CategoriaNaoDeclaradaException {
		DefCategoria result = null;
		result = this.mapDefCategoria.get(idArg);
		if (result == null) {
			throw new CategoriaNaoDeclaradaException(idArg);
		} else {
			return result;
		}
	}
}
