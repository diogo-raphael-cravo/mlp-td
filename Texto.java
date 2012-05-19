/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mlp.td;

import java.awt.Font;
import java.util.Map;
import java.util.Vector;
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
    * Inicializa os desenhos.
    */
    public static void inicializar(){
        foi_inicializada = true;
    }

    /**
     * Quebra a string em linhas e a retorna. Uma nova é feita toda vez
     * que encontra-se um '\n' na string.
     * @param _string A string que será quebrada.
     * @return Vetor de linhas da string.
     */
    public static Vector<String> quebrarEmLinhas(String _string){
        if(!foi_inicializada){
            inicializar();
        }

        Vector<String> linhas = new Vector<String>();
        String caracteres = _string.replaceAll("\n", "");
        int quantidadeLinhas = 0;
        int dimensoesLinhas[][] = new int[_string.length()][2];
        int caracteresNaMaiorLinha = 0;

        dimensoesLinhas[0][0] = 0;
        quantidadeLinhas = 1;
        for(int charAtual=0; charAtual<_string.length(); charAtual++){
            if(_string.charAt(charAtual) == '\n'){
                dimensoesLinhas[quantidadeLinhas][0] = charAtual -quantidadeLinhas +1;
                quantidadeLinhas++;
            }
        }

        for(int linhaAtual=0; linhaAtual<quantidadeLinhas; linhaAtual++){
            if(linhaAtual == 0){
                dimensoesLinhas[linhaAtual][1] = dimensoesLinhas[linhaAtual+1][0];
            } else if(linhaAtual < quantidadeLinhas - 1) {
                dimensoesLinhas[linhaAtual][1] = dimensoesLinhas[linhaAtual+1][0];
            } else {
                dimensoesLinhas[linhaAtual][1] = _string.length() -quantidadeLinhas +1;
            }

            if(caracteresNaMaiorLinha < dimensoesLinhas[linhaAtual][1] - dimensoesLinhas[linhaAtual][0]){
                caracteresNaMaiorLinha = dimensoesLinhas[linhaAtual][1] - dimensoesLinhas[linhaAtual][0];
            }
        }

        if(1 < quantidadeLinhas){
            for(int linhaAtual=0; linhaAtual<quantidadeLinhas; linhaAtual++){
                linhas.add(caracteres.substring(dimensoesLinhas[linhaAtual][0],
                                                dimensoesLinhas[linhaAtual][1]));
            }
        } else {
            linhas.add(caracteres);
        }

        return linhas;
    }
    
}
