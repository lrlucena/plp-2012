package plp.mixin.declaracao.classe;

import plp.orientadaObjetos1.declaracao.procedimento.DecProcedimento;
import plp.orientadaObjetos1.declaracao.variavel.DecVariavel;
import plp.orientadaObjetos1.excecao.declaracao.ClasseJaDeclaradaException;
import plp.orientadaObjetos1.excecao.declaracao.ClasseNaoDeclaradaException;
import plp.orientadaObjetos1.excecao.declaracao.ProcedimentoJaDeclaradoException;
import plp.orientadaObjetos1.excecao.declaracao.ProcedimentoNaoDeclaradoException;
import plp.orientadaObjetos1.expressao.leftExpression.Id;
import plp.orientadaObjetos1.memoria.AmbienteCompilacaoOO1;
import plp.orientadaObjetos1.memoria.AmbienteExecucaoOO1;
import plp.orientadaObjetos1.memoria.DefClasse;
import plp.orientadaObjetos1.util.TipoClasse;
/**
 * Classe que representa a declaracao de uma unica classe.
 */
public class DecCategoriaSimples implements DecCategoria {
	/**
	 * Identificador do nome da classe.
	 */
    protected Id nome;

	/**
	 * Metodos da classe.
	 */
    protected DecProcedimento metodos;


	/**
	 * Construtor.
	 * @param nomeClasse Nome da classe
	 * @param atributos Atributos da classe
	 * @param metodos Metodos da classe.
	 */
    public  DecCategoriaSimples(Id nome, DecProcedimento metodos){
        this.nome = nome;
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
    public boolean checaTipo(AmbienteCompilacaoOO1 ambiente)
       throws CategoriaJaDeclaradaException,CategoriaNaoDeclaradaException,
              ProcedimentoNaoDeclaradoException, ProcedimentoJaDeclaradoException  {

        // ambiente.mapDefClasse(nome, new DefCategoria(nome, metodos));
        // boolean resposta = false;
        // ambiente.incrementa();
        // if (atributos.checaTipo(ambiente)){
        //     ambiente.map(new Id("this"), new TipoClasse(nomeClasse));
        //     resposta =  metodos.checaTipo(ambiente);
        // }
        // ambiente.restaura();
        // return resposta;
    }

    /**
     * Cria um mapeamento do identificador para a declarao desta classe.
     * @param ambiente o ambiente que contem o mapeamento entre identificadores
     *  e valores.
     * @return o ambiente modificado pela declarao da classe.
     */
    public AmbienteExecucaoMixin elabora(AmbienteExecucaoMixin ambiente)
       throws CategoriaJaDeclaradaException,CategoriaNaoDeclaradaException,
              ProcedimentoNaoDeclaradoException,ProcedimentoJaDeclaradoException {
		ambiente.mapDefCategoria(nome, new DefCategoria(nome, metodos));
		return ambiente;
	}
}