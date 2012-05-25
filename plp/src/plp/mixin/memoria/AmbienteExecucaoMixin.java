package plp.mixin.memoria;

import java.util.ArrayList;
import java.util.List;

import plp.expressions2.expression.Id;
import plp.mixin.excecao.declaracao.CategoriaNaoDeclaradaException;
import plp.mixin.excecao.declaracao.CategoriaJaDeclaradaException;
import plp.mixin.util.ListaID;
import plp.orientadaObjetos1.excecao.declaracao.ClasseNaoDeclaradaException;
import plp.orientadaObjetos1.memoria.DefClasse;
import plp.orientadaObjetos2.memoria.AmbienteExecucaoOO2;

public interface AmbienteExecucaoMixin extends AmbienteExecucaoOO2 {

	public List<DefCategoria> getClasseCategoria(Id classe)
			throws ClasseNaoDeclaradaException;

	public void mapDefCategoria(Id nome, DefCategoria defCategoria)
			throws CategoriaJaDeclaradaException;

	public void mapClasseCategoria(Id nomeClasse, ListaID categorias)
			throws CategoriaNaoDeclaradaException;

}
