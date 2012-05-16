/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mlptd;

import java.util.Map;
import org.lwjgl.util.Color;
import org.newdawn.slick.opengl.Texture;

/**
 * Classe para criação de desenhos que contenham textos.
 * Oferece apenas métodos estáticos, não possui construtor.
 * @author drcravo
 */
public class Texto {
    /**
     * Define se a classe já foi inicializada.
     */
    private static boolean foi_inicializada = false;

    /**
     * Fontes oferecidas.
     */
    public static Fonte FONTE_PIXEL;
    public static Fonte FONTE_TIMES_NEW_ROMAN;

   /**
    * Inicializa os desenhos.
    */
    public static void inicializar(){
        foi_inicializada = true;
        FONTE_PIXEL = new Fonte(Texturas.FONTE_PIXEL, 2.5f, 3.5f, 1.0f);
        FONTE_TIMES_NEW_ROMAN = new Fonte(Texturas.FONTE_TIMES_NEW_ROMAN, 7.5f, 11f, 0.0f);
    }

    /**
     * Cria um desenho com a string passada e o retorna.
     * @param _string A string que irá aparecer no desenho.
     * @return Desenho da string.
     */
    public static Desenho converterParaDesenho(String _string, Fonte _fonte){
        if(!foi_inicializada){
            inicializar();
        }

        Desenho desenhoDaString = new Desenho(0f, 0f, _string.length()*(_fonte.getComprimento()+_fonte.getEspacamento()), 5f);
        desenhoDaString.esconderSohEste();
        String charDaString;

        //Vector<Desenho> linhas = new Vector<Desenho>();
        for(int charAtual=0; charAtual<_string.length(); charAtual++){
            charDaString = _string.substring(charAtual, charAtual+1);
            desenhoDaString.adicionarFilho(converterCaractereParaDesenho(charDaString, _fonte),
                    charAtual*(_fonte.getComprimento()+_fonte.getEspacamento()), 0);
        }
        
        return desenhoDaString;
    }

    /**
     * À partir de um caractere, cria um desenho e o retorna.
     * Se o caractere não existir na fonte, retornará um desenho vazio.
     * @param _caractere Caractere que deve aparecer no desenho.
     * @param _fonte A fonte, uma daquelas fornecidas por Texturas.
     * @return Desenho do caractere.
     */
     private static Desenho converterCaractereParaDesenho(String _caractere, Fonte _fonte){
         Desenho desenhoDoChar = new Desenho(0f, 0f, _fonte.getComprimento(), _fonte.getLargura());
         if(_fonte.getTextura(_caractere) != null){
             desenhoDoChar.definirTextura(_fonte.getTextura(_caractere));
         }
         return desenhoDoChar;
     }
    
}
