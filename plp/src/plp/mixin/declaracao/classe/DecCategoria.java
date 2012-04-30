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
 * Interface representando a declara��o de uma classe.
 */
public interface DecCategoria extends Declaracao {

    /**
     * Cria um mapeamento do identificador para o valor da express�o
     * desta declara��o no AmbienteExecucao
     * @param ambiente o ambiente que contem o mapeamento entre identificadores
     *  e valores.
     * @return o ambiente modificado pela inicializa��o da vari�vel.
     */
    public AmbienteExecucaoMixin elabora(AmbienteExecucaoMixin ambiente)
       throws CategoriaJaDeclaradaException,CategoriaNaoDeclaradaException,
             ProcedimentoNaoDeclaradoException,ProcedimentoJaDeclaradoException;

    /**
     * Verifica se a declara��o est� bem tipada, ou seja, se a
     * express�o de inicializa��o est� bem tipada.
     * @param ambiente o ambiente que contem o mapeamento entre identificadores
     *  e seus tipos.
     * @return <code>true</code> se os tipos da declara��o s�o v�lidos;
     *          <code>false</code> caso contrario.
     */
    public boolean checaTipo(AmbienteCompilacaoOO1 ambiente)
       throws CategoriaJaDeclaradaException,CategoriaNaoDeclaradaException,
           ProcedimentoNaoDeclaradoException, ProcedimentoJaDeclaradoException ;
}
