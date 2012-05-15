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
     * Desenhos de caracteres.
     */
    private static Desenho ZERO;
    private static Desenho UM;

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
        return new Desenho(0f,0f,0f,0f);
    }

    /**
     * À partir de um caractere, cria um desenho e o retorna.
     * @param _caractere Caractere que deve aparecer no desenho.
     * @return Desenho do caractere.
     */
     private static Desenho converterParaDesenho(char _char){
         return new Desenho(0f, 0f, 0f, 0f);
     }
    
}
