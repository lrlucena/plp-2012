package plp.mixin.memoria;

import java.util.List;

import plp.expressions2.expression.Id;
import plp.imperative1.util.Lista;
import plp.mixin.excecao.declaracao.CategoriaJaDeclaradaException;
import plp.mixin.excecao.declaracao.CategoriaNaoDeclaradaException;
import plp.mixin.util.ListaID;
import plp.orientadaObjetos2.memoria.AmbienteCompilacaoOO2;

public interface AmbienteCompilacaoMixin extends AmbienteCompilacaoOO2 {

	public void mapDefCategoria(Id idArg, DefCategoria defCategoria)
			throws CategoriaJaDeclaradaException;

	public DefCategoria getDefCategoria(Id idArg)
			throws CategoriaNaoDeclaradaException;

	public void mapClasseCategoria(Id classe, ListaID categorias)
			throws CategoriaNaoDeclaradaException;

	public List<DefCategoria> getClasseCategoria(Id classe)
			throws CategoriaNaoDeclaradaException;
}
