package plp.mixin.memoria;

import plp.expressions2.expression.Id;
import plp.mixin.excecao.declaracao.CategoriaJaDeclaradaException;
import plp.orientadaObjetos2.memoria.AmbienteCompilacaoOO2;

public interface AmbienteCompilacaoMixin extends AmbienteCompilacaoOO2 {

    public void mapDefCategoria(Id idArg, DefCategoria defCategoria) throws CategoriaJaDeclaradaException;
}
