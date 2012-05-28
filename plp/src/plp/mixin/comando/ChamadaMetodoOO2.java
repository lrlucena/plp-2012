package plp.mixin.comando;

import java.util.List;

import plp.expressions2.memory.Ambiente;
import plp.expressions2.memory.VariavelJaDeclaradaException;
import plp.expressions2.memory.VariavelNaoDeclaradaException;
import plp.orientadaObjetos1.comando.ChamadaMetodo;
import plp.orientadaObjetos1.comando.ChamadaProcedimento;
import plp.orientadaObjetos1.comando.Procedimento;
import plp.orientadaObjetos1.excecao.declaracao.ClasseJaDeclaradaException;
import plp.orientadaObjetos1.excecao.declaracao.ClasseNaoDeclaradaException;
import plp.orientadaObjetos1.excecao.declaracao.ObjetoJaDeclaradoException;
import plp.orientadaObjetos1.excecao.declaracao.ObjetoNaoDeclaradoException;
import plp.orientadaObjetos1.excecao.declaracao.ProcedimentoJaDeclaradoException;
import plp.orientadaObjetos1.excecao.declaracao.ProcedimentoNaoDeclaradoException;
import plp.orientadaObjetos1.excecao.execucao.EntradaInvalidaException;
import plp.orientadaObjetos1.expressao.Expressao;
import plp.orientadaObjetos1.expressao.ListaExpressao;
import plp.orientadaObjetos1.expressao.leftExpression.Id;
import plp.orientadaObjetos1.expressao.valor.ValorRef;
import plp.orientadaObjetos1.memoria.AmbienteCompilacaoOO1;
import plp.orientadaObjetos1.memoria.AmbienteExecucaoOO1;
import plp.orientadaObjetos1.memoria.ContextoExecucaoOO1;
import plp.orientadaObjetos1.memoria.DefClasse;
import plp.orientadaObjetos1.memoria.Objeto;
import plp.orientadaObjetos1.memoria.colecao.ListaValor;
import plp.orientadaObjetos1.util.Tipo;
import plp.mixin.memoria.AmbienteCompilacaoMixin;
import plp.mixin.memoria.AmbienteExecucaoMixin;
import plp.mixin.memoria.DefCategoria;
import plp.mixin.memoria.DefClasseOO2;

public class ChamadaMetodoOO2 extends ChamadaMetodo {

	public ChamadaMetodoOO2(Expressao expressao, Id nomeMetodo, ListaExpressao parametrosReais) {
		super(expressao, nomeMetodo, parametrosReais);
	}

    public AmbienteExecucaoOO1 executar(AmbienteExecucaoOO1 ambiente) throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
    	ProcedimentoNaoDeclaradoException, ProcedimentoJaDeclaradoException, ObjetoJaDeclaradoException,
    	ObjetoNaoDeclaradoException, ClasseNaoDeclaradaException, ClasseJaDeclaradaException, EntradaInvalidaException {

	    ValorRef vr = (ValorRef) expressao.avaliar(ambiente);  // recupera o id do objeto
	    Objeto objeto =  ambiente.getObjeto(vr);               // recupera o objeto
	    Id idClasse = objeto.getClasse();                      // recupera o tipo do objeto
	    DefClasseOO2 defClasse = (DefClasseOO2) ambiente.getDefClasse((plp.expressions2.expression.Id)idClasse); // recupera a defini��o da classe
	    Procedimento metodo = defClasse.getProcedimentoHierarquia(ambiente, nomeMetodo, false); // recupera o procedimento
	    // cria um novo ambiente para a execucao, pois
	    // n�o deve levar em conta as vari�veis definidas na main
	    AmbienteExecucaoOO1 aux = new ContextoExecucaoOO1(ambiente);
	                                                           // � change pois no construtor do ambiente
	    aux.changeValor(new Id("this"),vr);                    // invocado na linha anterior ja � feito
	                                                           //  um mapeamento

	    ListaValor valoresDosParametros = parametrosReais.avaliar(ambiente);
	    new ChamadaProcedimento(metodo, parametrosReais, valoresDosParametros).executar(aux);
	    return ambiente;
    }

    public boolean checaTipo(AmbienteCompilacaoOO1 ambiente) throws VariavelNaoDeclaradaException,
    			VariavelJaDeclaradaException, ClasseNaoDeclaradaException {
        boolean resposta;
        //Antes de incrementar o ambiente, verifico se o m�todo
        //� v�lido para a definicao de classe obtida a partir de expressao.
        //Se n�o for v�lido, a exce��o ProcedimentoNaoDeclaradoException ser�
        //lan�ada e checaTipo retornar� false.
        
        AmbienteCompilacaoMixin amb = ((AmbienteCompilacaoMixin) ambiente);
        
        Tipo tipoClasse = expressao.getTipo(amb);
        DefClasseOO2 defClasse = null;
        DefCategoria defCategoria = null;
        
        try{
        	defClasse = (DefClasseOO2) amb.getDefClasse(tipoClasse.getTipo());
        } catch (ClasseNaoDeclaradaException e) {
			defCategoria = amb.getDefCategoria(tipoClasse.getTipo());
		}
        try {
        	Procedimento metodo = null;
        	
        	if(defClasse == null){
        		metodo = defCategoria.getMetodo(nomeMetodo);
        	} else {
        		metodo = defClasse.getProcedimentoHierarquia(amb, nomeMetodo, true);
        	}
        	
            ambiente.incrementa();
            ambiente.map(new Id("this"),tipoClasse);
            resposta = new ChamadaProcedimento(metodo, parametrosReais).checaTipo(ambiente);
            ambiente.restaura();
        } catch(ProcedimentoNaoDeclaradoException e){
            resposta = false;
        }
        return resposta;
    }
}
