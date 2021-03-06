package plp.mixin.declaracao.classe;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import plp.expressions2.memory.VariavelJaDeclaradaException;
import plp.expressions2.memory.VariavelNaoDeclaradaException;
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
import plp.mixin.excecao.declaracao.CategoriaJaMixadaException;
import plp.mixin.memoria.AmbienteCompilacaoMixin;
import plp.mixin.memoria.AmbienteExecucaoMixin;
import plp.mixin.memoria.DefCategoria;
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

	public DecClasseSimplesOO2(Id nomeClasse, Id nomeSuperClasse,
			DecVariavel atributos, DecConstrutor construtor,
			DecProcedimento metodos) {
		super(nomeClasse, atributos, metodos);

		this.construtor = construtor;
		this.nomeSuperClasse = nomeSuperClasse;
	}

	public DecClasseSimplesOO2(Id nomeClasse, Id nomeSuperClasse,
			ListaID categorias, DecVariavel atributos,
			DecConstrutor construtor, DecProcedimento metodos) {

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
			throws ClasseJaDeclaradaException, ClasseNaoDeclaradaException,
			ConstrutorNaoDeclaradoException {

		if (categorias != null) {
			ambiente.mapClasseCategoria(nomeClasse, categorias);
		}
		
		// Adiciona a classe no mapeameento de classes
		ambiente.mapDefClasse(nomeClasse, new DefClasseOO2(nomeClasse,
				nomeSuperClasse, ambiente.getClasseCategoria(nomeClasse), this.atributos, construtor,
				metodos));

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
	 * @throws CategoriaJaMixadaException 
	 * @throws ArrayIndexOutOfBoundsException 
	 */
	public boolean checaTipo(AmbienteCompilacaoMixin ambiente)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
			ClasseJaDeclaradaException, ClasseNaoDeclaradaException,
			ProcedimentoNaoDeclaradoException,
			ProcedimentoJaDeclaradoException, ConstrutorNaoDeclaradoException {

		// Verifica se a super classe j� foi declarada
		if (nomeSuperClasse != null) {
			ambiente.mapSuperClasse(nomeClasse, nomeSuperClasse);
		}
		// Verifica se as categorias foram declaradas
		if (categorias != null) {

			// verifica se existem categorias iguais
			Set<String> setCategorias = new HashSet<String>();

			// passa as categorias para um set
			for (int i = 0; i < categorias.length(); i++) { 
				if (setCategorias.add(categorias.get(i).getId().toString()) == false) { //duplicatas
					throw new CategoriaJaMixadaException(categorias.get(i).getId());
				}
			}

			ambiente.mapClasseCategoria(nomeClasse, categorias);
		}

		DefClasseOO2 defClasse = new DefClasseOO2(nomeClasse,
				nomeSuperClasse, ambiente.getClasseCategoria(nomeClasse), this.atributos, construtor,
				metodos);
		
		// Adiciona a classe no mapeameento de classes
		ambiente.mapDefClasse(nomeClasse, defClasse);

		boolean resposta = false;
		ambiente.incrementa();

		DecVariavel atr = (DecVariavel) this.atributos;
		if (atr.checaTipo(ambiente)) {
			resposta = true;
			
			ambiente.map(new Id("this"), new TipoClasse(nomeClasse));

			if (nomeSuperClasse != null) {
				this.checaTipoVariaveisClasseMae(ambiente,this.nomeSuperClasse);
			}
			
			if (categorias != null) {

				for (DefCategoria categoria : ambiente.getClasseCategoria(nomeClasse)) {
					List<String> list = categoria.getListAssinaturaNomes();

					for (int i = 0; i < list.size(); i++) {

						Id id = new Id(list.get(i));

						try {
							defClasse.getProcedimentoHierarquia(ambiente, id,false);
						} catch (ProcedimentoNaoDeclaradoException e) {
							resposta = false;
						}

					}
				}
			}
			
			resposta = metodos.checaTipo(ambiente) && resposta;
		}

		// Verifica se construtor est� declarado corretamente
		resposta = resposta && construtor.checaTipo(ambiente)
				&& verificaMetodosRepetidos(ambiente);

		ambiente.restaura();

		return resposta;
	}

	private boolean verificaMetodosRepetidos(AmbienteCompilacaoMixin ambiente)
			throws ProcedimentoNaoDeclaradoException,
			ArrayIndexOutOfBoundsException, ClasseNaoDeclaradaException {
		List<String> listaNomes = new ArrayList<String>();

		if (categorias == null) {
			return true; // verificacao esta ok. Porque a classe n�o mixa
			// nenhuma categoria.
		}
		// pega todos os metodos de todas as categorias
		for (int i = 0; i < categorias.length(); i++) {
			listaNomes.addAll(ambiente.getDefCategoria(categorias.get(i))
					.getListProcedimentoNomes());
		}

		List<String> listaMetodosClasse = ambiente
				.getDefClasse(this.nomeClasse).getListProcedimentoNomes();

		// ver se tem duplicatas de metodos
		for (int i = 0; i < listaNomes.size(); i++) {
			for (int j = i + 1; j < listaNomes.size(); j++) {
				String procNome = listaNomes.get(i);
				String proc = listaNomes.get(j);
				if (procNome.equals(proc)) {
					if (listaMetodosClasse.contains(procNome)) {
						return true;
					}
					return false;
				}
			}
		}
		return true;
	}

	private void checaTipoVariaveisClasseMae(AmbienteCompilacaoMixin ambiente,
			Id nomeSuperClasse) throws ClasseNaoDeclaradaException,
			VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
			ClasseJaDeclaradaException {
		if (nomeSuperClasse != null) {
			DefClasseOO2 defClasseMae = (DefClasseOO2) ambiente
					.getDefClasse(nomeSuperClasse);
			defClasseMae.getDecVariavel().checaTipo(ambiente);
			this.checaTipoVariaveisClasseMae(ambiente, defClasseMae
					.getNomeSuperClasse());
		}
	}
}
