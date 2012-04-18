package plp.mixin.memoria;

import java.util.ArrayList;

import plp.expressions2.expression.Id;
import plp.mixin.excecao.declaracao.CategoriaNaoDeclaradaException;
import plp.mixin.excecao.declaracao.CategoriaJaDeclaradaException;
import plp.orientadaObjetos2.memoria.AmbienteExecucaoOO2;

public interface AmbienteExecucaoMixin extends AmbienteExecucaoOO2{

	public void mapDefCategoria(Id idArg, DefCategoria defClasse)
			throws CategoriaJaDeclaradaException;

	public DefCategoria getDefCategoria(Id idArg) throws CategoriaNaoDeclaradaException;

}
