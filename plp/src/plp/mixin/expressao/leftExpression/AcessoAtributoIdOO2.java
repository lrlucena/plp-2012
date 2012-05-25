package plp.mixin.expressao.leftExpression;

import plp.expressions2.memory.VariavelNaoDeclaradaException;
import plp.orientadaObjetos1.excecao.declaracao.ClasseNaoDeclaradaException;
import plp.orientadaObjetos1.expressao.leftExpression.AcessoAtributoId;
import plp.orientadaObjetos1.expressao.leftExpression.Id;
import plp.orientadaObjetos1.expressao.leftExpression.LeftExpression;
import plp.orientadaObjetos1.memoria.AmbienteCompilacaoOO1;
import plp.orientadaObjetos1.util.Tipo;
import plp.mixin.memoria.AmbienteCompilacaoMixin;
import plp.mixin.memoria.DefCategoria;
import plp.mixin.memoria.DefClasseOO2;
import plp.mixin.util.ListaID;

public class AcessoAtributoIdOO2 extends AcessoAtributoId{

	public AcessoAtributoIdOO2(LeftExpression av, Id id) {
		super(av, id);
	}

	private boolean checaTipoClasseMae (AmbienteCompilacaoOO1 ambiente, Id idClasseMae) throws ClasseNaoDeclaradaException {
		boolean retorno = false;
		DefClasseOO2 defSuperClasse = (DefClasseOO2) ambiente.getDefClasse(idClasseMae);

		try {
			defSuperClasse.getTipoAtributo(super.getId());
			retorno = true;
		} catch (VariavelNaoDeclaradaException atrib) {
			if (defSuperClasse.getNomeSuperClasse() != null) {
				retorno = this.checaTipoClasseMae(ambiente, defSuperClasse.getNomeSuperClasse());
			}
   	 	}

		return retorno;
	}
	
//	private boolean checaTipoCategorias(AmbienteCompilacaoMixin ambiente, ListaID idCategorias) throws ClasseNaoDeclaradaException {
//		boolean retorno = false;
//		
//		for (int i = 0; i < idCategorias.length(); i++) {
//			DefCategoria defCategoria = ambiente.getDefCategoria(idCategorias.get(i));
//			
//			defCategoria.
//			
//		} 
//		
//
//		try {
//			defSuperClasse.getTipoAtributo(super.getId());
//			retorno = true;
//		} catch (VariavelNaoDeclaradaException atrib) {
//			if (defSuperClasse.getNomeSuperClasse() != null) {
//				retorno = this.checaTipoClasseMae(ambiente, defSuperClasse.getNomeSuperClasse());
//			}
//   	 	}
//
//		return retorno;
//	}

	@Override
	public boolean checaTipo(AmbienteCompilacaoOO1 ambiente) throws VariavelNaoDeclaradaException, ClasseNaoDeclaradaException {
        boolean resposta = false;
        if(av.checaTipo(ambiente)) {
            try{
                Tipo tipo = av.getTipo(ambiente);
                DefClasseOO2 defClasse = (DefClasseOO2) ambiente.getDefClasse(tipo.getTipo());

                if (defClasse.getNomeSuperClasse() != null) {
                	resposta = this.checaTipoClasseMae(ambiente, defClasse.getNomeSuperClasse());
                }

                // Se a verificacao pelo atributo nas classes maes nao encontrou nada
                if (!resposta) {
                    defClasse.getTipoAtributo(super.getId());
                    resposta = true;
                }
            }
            catch(VariavelNaoDeclaradaException atrib){
                resposta = false;
            }
            catch(ClasseNaoDeclaradaException clas){
                resposta = false;
            }

        }
        return resposta;
	}

}
