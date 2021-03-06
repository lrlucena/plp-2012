package plp.mixin;

import plp.expressions2.memory.VariavelJaDeclaradaException;
import plp.expressions2.memory.VariavelNaoDeclaradaException;
import plp.orientadaObjetos1.comando.Comando;
import plp.orientadaObjetos1.excecao.declaracao.ClasseJaDeclaradaException;
import plp.orientadaObjetos1.excecao.declaracao.ClasseNaoDeclaradaException;
import plp.orientadaObjetos1.excecao.declaracao.ObjetoJaDeclaradoException;
import plp.orientadaObjetos1.excecao.declaracao.ObjetoNaoDeclaradoException;
import plp.orientadaObjetos1.excecao.declaracao.ProcedimentoJaDeclaradoException;
import plp.orientadaObjetos1.excecao.declaracao.ProcedimentoNaoDeclaradoException;
import plp.orientadaObjetos1.excecao.execucao.EntradaInvalidaException;
import plp.orientadaObjetos1.excecao.execucao.EntradaNaoFornecidaException;
import plp.orientadaObjetos1.memoria.colecao.ListaValor;
import plp.mixin.declaracao.ConstrutorNaoDeclaradoException;
import plp.mixin.declaracao.ListaDeclaracaoOO;
import plp.mixin.memoria.AmbienteCompilacaoMixin;
import plp.mixin.memoria.AmbienteExecucaoMixin;

/**
 * Classe que representa um programa na linguagem OO.
 */
public class Programa {
    /**
     * lista de declaracoes OO
     */
    private ListaDeclaracaoOO declaracoesOO;
    /**
     * Comando executado pos a declaracao de classes
     */
    private Comando comando;

    /**
     * Construtor.
     * @param decClasse A declaraçao de classe(s)
     * @param comando O comando executado após a declaraçao.
     */
    public Programa(ListaDeclaracaoOO dec, Comando comando){
        this.declaracoesOO = dec;
        this.comando = comando;
    }

     /**
     * Executa o programa.
     *
     * @param ambiente o ambiente de execução.
     *
     * @return o ambiente depois de modificado pela execução
     * do programa.
     *
     * @exception EntradaNaoFornecidaException se não for fornecida
     *  a tail de valores de entrada do programa.
     * @throws ConstrutorNaoDeclaradoException
     *
     */
    public ListaValor executar(AmbienteExecucaoMixin ambiente)
        throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException,
               ObjetoNaoDeclaradoException, ObjetoJaDeclaradoException,
               ProcedimentoJaDeclaradoException,ProcedimentoNaoDeclaradoException,
               ClasseJaDeclaradaException, ClasseNaoDeclaradaException,
               EntradaNaoFornecidaException, EntradaInvalidaException, ConstrutorNaoDeclaradoException {
        if(ambiente == null)
            throw new EntradaNaoFornecidaException();
        ambiente.incrementa();
        comando.executar(declaracoesOO.elabora(ambiente)).restaura();
        return ambiente.getSaida();
    }

    /**
     * Realiza a verificacao de tipos do programa
     *
     * @param ambiente o ambiente de compilação.
     * @return <code>true</code> se o programa está bem tipado;
     *          <code>false</code> caso contrario.
     *
     * @exception EntradaNaoFornecidaException se não for fornecida
     *  a tail de valores de entrada do programa.
     * @throws ConstrutorNaoDeclaradoException
     *
     */
    public boolean checaTipo(AmbienteCompilacaoMixin ambiente)
        throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException,
               ProcedimentoNaoDeclaradoException, ProcedimentoJaDeclaradoException,
               ClasseJaDeclaradaException, ClasseNaoDeclaradaException,
               EntradaNaoFornecidaException, ConstrutorNaoDeclaradoException{
        boolean resposta;
        if(ambiente == null) {
            throw new EntradaNaoFornecidaException();
        }
        ambiente.incrementa();
        resposta = declaracoesOO.checaTipo(ambiente) && comando.checaTipo(ambiente);
        ambiente.restaura();
        return resposta;
    }
}