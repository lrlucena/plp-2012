package plp.mixin.memoria;

import java.util.ArrayList;

import plp.expressions2.expression.Id;
import plp.mixin.excecao.declaracao.CategoriaNaoDeclaradaException;
import plp.mixin.excecao.declaracao.CategoriaJaDeclaradaException;
import plp.orientadaObjetos1.memoria.DefClasse;
import plp.orientadaObjetos2.memoria.AmbienteExecucaoOO2;

public interface AmbienteExecucaoMixin extends AmbienteExecucaoOO2{

	public void mapDefCategoria(Id idArg, DefClasse defClasse)
			throws CategoriaJaDeclaradaException;

	public DefClasse getDefCategoria(Id idArg) throws CategoriaNaoDeclaradaException;

}
