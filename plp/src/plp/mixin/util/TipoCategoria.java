package plp.mixin.util;

import plp.mixin.excecao.declaracao.CategoriaNaoDeclaradaException;
import plp.mixin.memoria.AmbienteCompilacaoMixin;
import plp.orientadaObjetos1.expressao.leftExpression.Id;
import plp.orientadaObjetos1.memoria.AmbienteCompilacaoOO1;
import plp.orientadaObjetos1.util.Tipo;

/**
 * Classe que representa os possiveis tipos de uma expressao.
 */
public class TipoCategoria implements Tipo {

    /**
     * Indica que a expressao associada &eacute; uma categoria.
     */
    private Id tipoCategoria;

    /**
     * Indica que a expressao associada &eacute; nula.
     */
    public static final Id NULL = new Id("NULL");

    /**
     * Constante de tipo nulo.
     */
    public static final Tipo TIPO_NULL = new TipoCategoria(NULL);

     /**
     * Construtor da categoria.
     *
     * @param tipo o tipo da expressao associada.
     */

     public TipoCategoria(Id tipoCategoria) {
        this.tipoCategoria = tipoCategoria;
     }


    /**
     * Retorna o tipo da expressao associada.
     *
     * @return o tipo da expressao associada.
     */
    public Id getTipo() {
        return tipoCategoria;
    }


    /**
     * Indica se esta classe &eacute; um tipo válido, ou seja, se já tiver sido declarada.
     *
     * @return <code>true</code> se esta classe for um tipo válido (já declarada);
     *          <code>false</code> caso contrario.
     */

    public boolean eValido(AmbienteCompilacaoOO1 ambiente)
         throws CategoriaNaoDeclaradaException {
        boolean resposta = false;
        try{
             resposta =  (tipoCategoria == NULL) || (((AmbienteCompilacaoMixin) ambiente).getDefCategoria(tipoCategoria) != null);
         }
         catch(CategoriaNaoDeclaradaException c){
              resposta = false;
         }
         return resposta;
    }

    /**
     * Compara este tipo com o tipo dado.
     *
     * @return <code>true</code> se se tratarem do mesmo tipo;
     *          <code>false</code> caso contrario.
     */
    public boolean equals(Object obj) {
        return (obj instanceof TipoCategoria) &&
               ((TipoCategoria)obj).tipoCategoria.equals(this.tipoCategoria);
    }
    /**
     * Retorna  a descrição textual do tipo.
     * @return  a descrição textual do tipo.
     */
    public String toString() {
        return tipoCategoria.toString();
    }
}
