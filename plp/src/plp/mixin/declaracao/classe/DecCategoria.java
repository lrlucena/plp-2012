package plp.mixin.declaracao.classe;

import plp.orientadaObjetos1.declaracao.Declaracao;
import plp.mixin.excecao.declaracao.CategoriaJaDeclaradaException;
import plp.mixin.excecao.declaracao.CategoriaNaoDeclaradaException;
import plp.orientadaObjetos1.excecao.declaracao.ProcedimentoJaDeclaradoException;
import plp.orientadaObjetos1.excecao.declaracao.ProcedimentoNaoDeclaradoException;
import plp.orientadaObjetos1.memoria.AmbienteCompilacaoOO1;
import plp.orientadaObjetos1.memoria.AmbienteExecucaoOO1;
import plp.mixin.memoria.AmbienteExecucaoMixin;

/**
 * Interface representando a declaração de uma classe.
 */
public interface DecCategoria extends Declaracao {

    /**
     * Cria um mapeamento do identificador para o valor da expressão
     * desta declaração no AmbienteExecucao
     * @param ambiente o ambiente que contem o mapeamento entre identificadores
     *  e valores.
     * @return o ambiente modificado pela inicialização da variável.
     */
    public AmbienteExecucaoMixin elabora(AmbienteExecucaoMixin ambiente)
       throws CategoriaJaDeclaradaException,CategoriaNaoDeclaradaException,
             ProcedimentoNaoDeclaradoException,ProcedimentoJaDeclaradoException;

    /**
     * Verifica se a declaração está bem tipada, ou seja, se a
     * expressão de inicialização está bem tipada.
     * @param ambiente o ambiente que contem o mapeamento entre identificadores
     *  e seus tipos.
     * @return <code>true</code> se os tipos da declaração são válidos;
     *          <code>false</code> caso contrario.
     */
    public boolean checaTipo(AmbienteCompilacaoOO1 ambiente)
       throws CategoriaJaDeclaradaException,CategoriaNaoDeclaradaException,
           ProcedimentoNaoDeclaradoException, ProcedimentoJaDeclaradoException ;
}
