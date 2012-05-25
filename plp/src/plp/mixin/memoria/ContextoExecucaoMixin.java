package plp.mixin.memoria;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import plp.expressions2.expression.Id;
import plp.expressions2.memory.VariavelJaDeclaradaException;
import plp.orientadaObjetos1.excecao.declaracao.ClasseNaoDeclaradaException;
import plp.orientadaObjetos1.expressao.valor.Valor;
import plp.orientadaObjetos1.expressao.valor.ValorNull;
import plp.orientadaObjetos1.memoria.ContextoExecucaoOO1;
import plp.orientadaObjetos1.memoria.DefClasse;
import plp.orientadaObjetos1.memoria.colecao.ListaValor;
import plp.orientadaObjetos2.util.SuperClasseMap;
import plp.mixin.excecao.declaracao.CategoriaNaoDeclaradaException;
import plp.mixin.excecao.declaracao.CategoriaJaDeclaradaException;
import plp.mixin.util.ListaID;

public class ContextoExecucaoMixin extends ContextoExecucaoOO1 implements AmbienteExecucaoMixin {
	private ArrayList<SuperClasseMap> arraySuperClasse;
	private HashMap<Id, DefCategoria> mapDefCategoria;
	private HashMap<Id, List<DefCategoria>> mapMixinCategoria;


	public ContextoExecucaoMixin() {
		super();
		arraySuperClasse = new ArrayList <SuperClasseMap> ();
		mapDefCategoria = new HashMap<Id, DefCategoria>(); // cria mapeamento
		mapMixinCategoria = new HashMap<Id, List<DefCategoria>>();

	}

	public ContextoExecucaoMixin(AmbienteExecucaoMixin ambiente) throws VariavelJaDeclaradaException {
		super(ambiente);
		arraySuperClasse = ((AmbienteExecucaoMixin) ambiente).getMapSuperClasse();
		mapDefCategoria = new HashMap<Id, DefCategoria>(); // cria mapeamento
		mapMixinCategoria = new HashMap<Id, List<DefCategoria>>();

		HashMap<Id, Valor> aux = new HashMap<Id, Valor>();
		aux.put(new Id("super"), new ValorNull());
		getPilha().push(aux);
	}

	public ContextoExecucaoMixin(ListaValor entrada) throws VariavelJaDeclaradaException {
		super(entrada);
		arraySuperClasse = new ArrayList <SuperClasseMap> ();
		mapDefCategoria = new HashMap<Id, DefCategoria>(); // cria mapeamento
		mapMixinCategoria = new HashMap<Id, List<DefCategoria>>();

		HashMap<Id, Valor> aux = new HashMap<Id, Valor>();
		aux.put(new Id("super"), new ValorNull());
		getPilha().push(aux);
	}

	@Override
	public ContextoExecucaoMixin getContextoIdValor() throws VariavelJaDeclaradaException {
		ContextoExecucaoMixin ambiente = new ContextoExecucaoMixin(this.getEntrada());
		ambiente.setPilha( getPilha() );
		ambiente.setSaida( getSaida() );
		return ambiente;
	}

	public void mapSuperClasse(Id classe, Id superClasse) throws ClasseNaoDeclaradaException {
		DefClasse defClasse = getDefClasse(superClasse);
		if (defClasse != null) {
			arraySuperClasse.add(new SuperClasseMap( classe, superClasse ));
		}
	}

	public SuperClasseMap getSuperClasse(Id classe) throws ClasseNaoDeclaradaException {
		for(int i = 0; i < arraySuperClasse.size(); i++){
			String nomeClasse = arraySuperClasse.get(i).getClasse().toString();

			if (nomeClasse.equalsIgnoreCase( classe.toString() )) {
				return arraySuperClasse.get(i);
			}
		}
		return null;
	}

	public ArrayList<SuperClasseMap> getMapSuperClasse() {
		return arraySuperClasse;
	}


	public void mapDefCategoria(Id nome, DefCategoria defCategoria)
			throws CategoriaJaDeclaradaException {
		if (mapDefCategoria.put(nome, defCategoria) != null) {
			throw new CategoriaJaDeclaradaException(nome);
		}
	}

	public List<DefCategoria> getClasseCategoria(Id classe)
			throws ClasseNaoDeclaradaException {
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
