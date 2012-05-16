/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mlptd;

import org.lwjgl.util.Color;

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
    * Inicializa os desenhos.
    */
    private static void inicializar(){
        foi_inicializada = true;
    }

    /**
     * Dimensões de letras.
     */
    private static float COMPRIMENTO_LETRAS = 3f;
    private static float ESPACO_LETRAS = 1f; //Não confundir com o espaço da barra de espaço.

    /**
     * Cria um desenho com a string passada e o retorna.
     * @param _string A string que irá aparecer no desenho.
     * @return Desenho da string.
     */
    public static Desenho converterParaDesenho(String _string){
        if(!foi_inicializada){
            inicializar();
        }

        Desenho desenhoDaString = new Desenho(0f, 0f, _string.length()*(COMPRIMENTO_LETRAS+ESPACO_LETRAS), 5f);
        desenhoDaString.esconderSohEste();
        char charDaString;
        
        for(int charAtual=0; charAtual<_string.length(); charAtual++){
            charDaString = _string.charAt(charAtual);
            desenhoDaString.adicionarFilho(converterParaDesenho(charDaString), 
                    charAtual*(COMPRIMENTO_LETRAS+ESPACO_LETRAS), 0);
        }
        
        return desenhoDaString;
    }

    /**
     * À partir de um caractere, cria um desenho e o retorna.
     * @param _caractere Caractere que deve aparecer no desenho.
     * @return Desenho do caractere.
     */
     public static Desenho converterParaDesenho(char _char){
         Desenho desenhoDoChar = new Desenho(0f, 0f, 2.5f, 3.5f);
         switch(_char){
             case '0': desenhoDoChar.definirTextura(Texturas.PIXEL_0);
                break;
             case '1': desenhoDoChar.definirTextura(Texturas.PIXEL_1);
                break;
             case '2': desenhoDoChar.definirTextura(Texturas.PIXEL_2);
                break;
             case '3': desenhoDoChar.definirTextura(Texturas.PIXEL_3);
                break;
             case '4': desenhoDoChar.definirTextura(Texturas.PIXEL_4);
                break;
             case '5': desenhoDoChar.definirTextura(Texturas.PIXEL_5);
                break;
             case '6': desenhoDoChar.definirTextura(Texturas.PIXEL_6);
                break;
             case '7': desenhoDoChar.definirTextura(Texturas.PIXEL_7);
                break;
             case '8': desenhoDoChar.definirTextura(Texturas.PIXEL_8);
                break;
             case '9': desenhoDoChar.definirTextura(Texturas.PIXEL_9);
                break;
         }
         return desenhoDoChar;
     }
    
}
