/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mlptd;

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

        Vector<Desenho> linhas = new Vector<Desenho>();
        Desenho desenhoDaString;
        String caracteres = _string.replaceAll("\n", "");
        int quantidadeLinhas = 0;
        int dimensoesLinhas[][] = new int[_string.length()][2];
        int caracteresUltimaLinha = 0;
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
                linhas.add(converterParaDesenho(caracteres, _fonte,
                        dimensoesLinhas[linhaAtual][0], dimensoesLinhas[linhaAtual][1]));
            }

            desenhoDaString = new Desenho(0f, 0f,
                    caracteresNaMaiorLinha*(_fonte.getComprimento()+_fonte.getEspacamento()),
                    quantidadeLinhas*_fonte.getLargura());

            for(int linhaAtual=0; linhaAtual<quantidadeLinhas; linhaAtual++){
                desenhoDaString.adicionarFilho(linhas.elementAt(linhaAtual), 0.0f,
                        quantidadeLinhas*_fonte.getLargura() - linhaAtual*_fonte.getLargura());
            }
        } else {
            desenhoDaString = converterParaDesenho(caracteres, _fonte, 0, caracteres.length());
        }

        desenhoDaString.esconderSohEste();
        return desenhoDaString;
    }

    /**
     * Lê a string passada e converte seus caracteres em desenhos um a um.
     * Cada desenho criado é colocado como filho do desenho que será retornado.
     * A string não é lida por inteiro. Apenas na janela fornecida.
     * @param _string String que será lida.
     * @param _fonte Fonte para a qual deve-se traduzir.
     * @param _indiceInicio Indice do primeiro caractere que será lido.
     * @param _indiceFim Indice do fim, cujo caractere não será lido.
     * @return Desenho com os caracteres lidos.
     */
    private static Desenho converterParaDesenho(String _string, Fonte _fonte, int _indiceInicio, int _indiceFim){
        if(!foi_inicializada){
            inicializar();
        }

        String intervaloLido = _string.substring(_indiceInicio, _indiceFim);
        String charDaString;
        Desenho desenhoDaString = new Desenho(0f, 0f, intervaloLido.length()*(_fonte.getComprimento()+_fonte.getEspacamento()),
                _fonte.getLargura());

        for(int charAtual=0; charAtual<intervaloLido.length(); charAtual++){
            charDaString = intervaloLido.substring(charAtual, charAtual+1);
            desenhoDaString.adicionarFilho(converterCaractereParaDesenho(charDaString, _fonte),
                    charAtual*(_fonte.getComprimento()+_fonte.getEspacamento()), 0);
        }

        desenhoDaString.esconderSohEste();
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
