package plp.mixin.memoria;

import java.util.ArrayList;
import java.util.HashMap;

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

public class ContextoExecucaoMixin extends ContextoExecucaoOO1 implements AmbienteExecucaoMixin {
	private ArrayList<SuperClasseMap> arraySuperClasse;

	public ContextoExecucaoMixin() {
		super();
		arraySuperClasse = new ArrayList <SuperClasseMap> ();
	}

	public ContextoExecucaoMixin(AmbienteExecucaoMixin ambiente) throws VariavelJaDeclaradaException {
		super(ambiente);
		arraySuperClasse = ((AmbienteExecucaoMixin) ambiente).getMapSuperClasse();
		HashMap<Id, Valor> aux = new HashMap<Id, Valor>();
		aux.put(new Id("super"), new ValorNull());
		getPilha().push(aux);
	}

	public ContextoExecucaoMixin(ListaValor entrada) throws VariavelJaDeclaradaException {
		super(entrada);
		arraySuperClasse = new ArrayList <SuperClasseMap> ();
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
        
        public DefClasse getDefCategoria(Id idArg) throws CategoriaNaoDeclaradaException {
            return null;
        }
        
	public void mapDefCategoria(Id idArg, DefClasse defClasse)
			throws CategoriaJaDeclaradaException {
            //
        }

}
