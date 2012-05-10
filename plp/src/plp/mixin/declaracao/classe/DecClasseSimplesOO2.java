package plp.mixin.declaracao.classe;

import plp.expressions2.memory.VariavelJaDeclaradaException;
import plp.expressions2.memory.VariavelNaoDeclaradaException;
import plp.imperative1.util.Lista;
import plp.orientadaObjetos1.declaracao.classe.DecClasseSimples;
import plp.orientadaObjetos1.declaracao.procedimento.DecProcedimento;
import plp.orientadaObjetos1.declaracao.variavel.DecVariavel;
import plp.orientadaObjetos1.excecao.declaracao.ClasseJaDeclaradaException;
import plp.orientadaObjetos1.excecao.declaracao.ClasseNaoDeclaradaException;
import plp.orientadaObjetos1.excecao.declaracao.ProcedimentoJaDeclaradoException;
import plp.orientadaObjetos1.excecao.declaracao.ProcedimentoNaoDeclaradoException;
import plp.orientadaObjetos1.expressao.leftExpression.Id;
import plp.orientadaObjetos1.util.TipoClasse;
import plp.mixin.declaracao.ConstrutorNaoDeclaradoException;
import plp.mixin.declaracao.DecConstrutor;
import plp.mixin.memoria.AmbienteCompilacaoMixin;
import plp.mixin.memoria.AmbienteExecucaoMixin;
import plp.mixin.memoria.DefClasseOO2;
import plp.mixin.util.ListaID;

public class DecClasseSimplesOO2 extends DecClasseSimples {

	/**
	 * Identificador da super classe
	 */
	private Id nomeSuperClasse;

	/**
	 * Declarassa do construtor
	 */
	private DecConstrutor construtor;
	
	/**
	 * Identificador da categoria
	 */
	private ListaID categorias;

    public DecClasseSimplesOO2(Id nomeClasse, Id nomeSuperClasse, DecVariavel atributos,
			DecConstrutor construtor, DecProcedimento metodos) {
		super(nomeClasse, atributos, metodos);

		this.construtor = construtor;
		this.nomeSuperClasse = nomeSuperClasse;
	}
    
    public DecClasseSimplesOO2(Id nomeClasse, Id nomeSuperClasse, ListaID categorias,
			DecVariavel atributos, DecConstrutor construtor,
			DecProcedimento metodos) {
		
    	super(nomeClasse, atributos, metodos);
    	this.construtor = construtor;
		this.nomeSuperClasse = nomeSuperClasse;
		this.categorias = categorias;
	}

	/**
	 * Cria um mapeamento do identificador para a declara��o desta classe.
	 *
	 * @param ambiente
	 *            o ambiente que contem o mapeamento entre identificadores e
	 *            valores.
	 * @return o ambiente modificado pela declara��o da classe.
	 * @throws ConstrutorNaoDeclaradoException
	 */
	public AmbienteExecucaoMixin elabora(AmbienteExecucaoMixin ambiente)
			throws ClasseJaDeclaradaException, ClasseNaoDeclaradaException, ConstrutorNaoDeclaradoException {

		// Adiciona a classe no mapeameento de classes
		ambiente.mapDefClasse(nomeClasse, new DefClasseOO2(nomeClasse, nomeSuperClasse, categorias,this.atributos, construtor, metodos));

		// Verifica se a super classe j� foi declarada
		if (nomeSuperClasse != null) {
			ambiente.mapSuperClasse(nomeClasse, nomeSuperClasse);
		}

		return ambiente;
	}

	/**
	 * Verifica se a declaracao esta bem tipada, ou seja, se a checagem dos
	 * tipos dos metodos e atributos esta ok.
	 *
	 * @param ambiente
	 *            o ambiente que contem o mapeamento entre identificadores e
	 *            seus tipos.
	 * @return <code>true</code> se os tipos da declaracao sao validos;
	 *         <code>false</code> caso contrario.
	 * @throws ConstrutorNaoDeclaradoException
	 */
	public boolean checaTipo (AmbienteCompilacaoMixin ambiente)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
			ClasseJaDeclaradaException, ClasseNaoDeclaradaException,
			ProcedimentoNaoDeclaradoException,
			ProcedimentoJaDeclaradoException, ConstrutorNaoDeclaradoException {

		// Verifica se a super classe j� foi declarada
		if (nomeSuperClasse != null) {
			ambiente.mapSuperClasse(nomeClasse, nomeSuperClasse);
		}
		
		if (categorias != null) {
			ambiente.mapClasseCategoria(nomeClasse, categorias);
		}

		// Adiciona a classe no mapeameento de classes
		ambiente.mapDefClasse(nomeClasse, new DefClasseOO2(nomeClasse, nomeSuperClasse, categorias, this.atributos, construtor, metodos));

		boolean resposta = false;
		ambiente.incrementa();

		DecVariavel atr = (DecVariavel) this.atributos;
		if (atr.checaTipo(ambiente)){
			ambiente.map(new Id("this"), new TipoClasse(nomeClasse));

			if (nomeSuperClasse != null) {
				this.checaTipoVariaveisClasseMae(ambiente, this.nomeSuperClasse);
			}
			resposta =  metodos.checaTipo(ambiente);
		}

		//Verifica se construtor est� declarado corretamente
		resposta = resposta && construtor.checaTipo(ambiente);

		ambiente.restaura();

		return resposta;
	}

	private void checaTipoVariaveisClasseMae(AmbienteCompilacaoMixin ambiente, Id nomeSuperClasse) throws ClasseNaoDeclaradaException, VariavelJaDeclaradaException, VariavelNaoDeclaradaException, ClasseJaDeclaradaException {
		if (nomeSuperClasse != null) {
			DefClasseOO2 defClasseMae = (DefClasseOO2) ambiente.getDefClasse(nomeSuperClasse);
			defClasseMae.getDecVariavel().checaTipo(ambiente);
			this.checaTipoVariaveisClasseMae(ambiente, defClasseMae.getNomeSuperClasse());
		}
	}
}
