package plp.mixin.declaracao.classe;

import java.util.List;

import plp.orientadaObjetos1.comando.Procedimento;
import plp.orientadaObjetos1.declaracao.procedimento.DecProcedimento;
import plp.orientadaObjetos1.excecao.declaracao.ClasseJaDeclaradaException;
import plp.orientadaObjetos1.excecao.declaracao.ClasseNaoDeclaradaException;
import plp.mixin.excecao.declaracao.CategoriaJaDeclaradaException;
import plp.mixin.excecao.declaracao.CategoriaNaoDeclaradaException;
import plp.mixin.memoria.AmbienteCompilacaoMixin;
import plp.mixin.memoria.AmbienteExecucaoMixin;
import plp.orientadaObjetos1.excecao.declaracao.ProcedimentoJaDeclaradoException;
import plp.orientadaObjetos1.excecao.declaracao.ProcedimentoNaoDeclaradoException;
import plp.orientadaObjetos1.expressao.leftExpression.Id;
import plp.mixin.memoria.DefCategoria;
import plp.orientadaObjetos1.declaracao.Declaracao;
import plp.orientadaObjetos1.memoria.AmbienteCompilacaoOO1;
import plp.orientadaObjetos1.memoria.AmbienteExecucaoOO1;
/**
 * Classe que representa a declaracao de uma unica classe.
 */
public class DecCategoriaSimples implements  Declaracao {

    protected Id nome;
    
    protected DecProcedimento metodos;
    
    protected DecProcedimento assinaturas;
    
    
    public  DecCategoriaSimples(Id nome, DecProcedimento assinaturas ,DecProcedimento metodos){
        this.nome = nome;
        this.assinaturas = assinaturas;
        this.metodos = metodos;
    }

    /**
     * Verifica se a declaracao esta bem tipada, ou seja, se a checagem dos tipos
     * dos metodos e atributos esta ok.
     * @param ambiente o ambiente que contem o mapeamento entre identificadores
     *  e seus tipos.
     * @return <code>true</code> se os tipos da declara vlidos;
     *          <code>false</code> caso contrario.
     */
    public boolean checaTipo(AmbienteCompilacaoOO1 amb)
       throws CategoriaJaDeclaradaException,CategoriaNaoDeclaradaException,
              ClasseJaDeclaradaException,ClasseNaoDeclaradaException,
              ProcedimentoNaoDeclaradoException, ProcedimentoJaDeclaradoException  {
        boolean resposta;
        AmbienteCompilacaoMixin ambiente = (AmbienteCompilacaoMixin)amb;
        ambiente.mapDefCategoria(nome, new DefCategoria(nome, metodos));
        ambiente.incrementa();
        resposta =  metodos.checaTipo(ambiente) && assinaturas.checaTipo(ambiente);
        ambiente.restaura();
        return resposta;
    }
    


    /**
     * Cria um mapeamento do identificador para a declarao desta classe.
     * @param ambiente o ambiente que contem o mapeamento entre identificadores
     *  e valores.
     * @return o ambiente modificado pela declarao da classe.
     */
    public AmbienteExecucaoOO1 elabora(AmbienteExecucaoOO1 amb)
       throws CategoriaJaDeclaradaException,CategoriaNaoDeclaradaException,
              ProcedimentoNaoDeclaradoException,ProcedimentoJaDeclaradoException {
    	AmbienteExecucaoMixin ambiente = (AmbienteExecucaoMixin)amb;

		ambiente.mapDefCategoria(nome, new DefCategoria(nome, metodos));
        return ambiente;
	}    
    
}
    
 