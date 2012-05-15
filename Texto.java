/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mlptd;

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
     * Cria um desenho com a string passada e o retorna.
     * @param _string A string que irá aparecer no desenho.
     * @return Desenho da string.
     */
    public static Desenho converterParaDesenho(String _string){
        if(!foi_inicializada){
            inicializar();
        }

        Desenho desenhoDaString = new Desenho(0f, 0f, _string.length()*15f, 20f);
        char charDaString;
        
        for(int charAtual=0; charAtual<_string.length(); charAtual++){
            charDaString = _string.charAt(charAtual);
            desenhoDaString.adicionarFilho(converterParaDesenho(charDaString), 
                    charAtual*15f, 0);
        }
        
        return desenhoDaString;
    }

    /**
     * À partir de um caractere, cria um desenho e o retorna.
     * @param _caractere Caractere que deve aparecer no desenho.
     * @return Desenho do caractere.
     */
     public static Desenho converterParaDesenho(char _char){
         Desenho desenhoDoChar = new Desenho(0f, 0f, 7.5f, 15f);
         switch(_char){
             case '0': desenhoDoChar.definirTextura(Texturas.TIMES_NEW_ROMAN_0);
                break;
             case '1': desenhoDoChar.definirTextura(Texturas.TIMES_NEW_ROMAN_1);
                break;
             case '2': desenhoDoChar.definirTextura(Texturas.TIMES_NEW_ROMAN_2);
                break;
             case '3': desenhoDoChar.definirTextura(Texturas.TIMES_NEW_ROMAN_3);
                break;
             case '4': desenhoDoChar.definirTextura(Texturas.TIMES_NEW_ROMAN_4);
                break;
             case '5': desenhoDoChar.definirTextura(Texturas.TIMES_NEW_ROMAN_5);
                break;
             case '6': desenhoDoChar.definirTextura(Texturas.TIMES_NEW_ROMAN_6);
                break;
             case '7': desenhoDoChar.definirTextura(Texturas.TIMES_NEW_ROMAN_7);
                break;
             case '8': desenhoDoChar.definirTextura(Texturas.TIMES_NEW_ROMAN_8);
                break;
             case '9': desenhoDoChar.definirTextura(Texturas.TIMES_NEW_ROMAN_9);
                break;
         }
         desenhoDoChar.redimensionar(15, 20, 0);
         return desenhoDoChar;
     }
    
}
