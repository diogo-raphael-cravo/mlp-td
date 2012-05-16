/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mlptd;

import java.util.Map;
import org.newdawn.slick.opengl.Texture;

/**
 * Uma fonte de texto.
 * @author diogo
 */
public class Fonte {
    /**
     * As texturas da fonte.
     * Mapeamento em que cada caractere, em forma de string, é associado a uma textura.
     */
    private Map<String, Texture> fonte;

    /**
     * As dimensões associam-se com a melhor visualização da textura em um desenho.
     * Não são as dimensões exatas que os desenhos terão, servem apenas para carregamento de texturas.
     */
    private float comprimento;
    private float largura;

    /**
     * Espaçamento em pixels entre letras. Não confundir com o espaço da barra de espaço.
     */
    private float espacamento;

    /**
     * @param _fonte O mapeamento de caracteres em texturas da fonte.
     * @param _comprimento O comprimento ideal para a fonte. A dimensão associa-se com a melhor visualização da textura em um desenho.
     * @param _largura A largura ideal para a fonte. A dimensão associa-se com a melhor visualização da textura em um desenho.
     * @param _espacamento Espaçamento em pixels entre letras. Não confundir com o espaço da barra de espaço.
     */
    public Fonte(Map<String, Texture> _fonte, float _comprimento, float _largura, float _espacamento){
        fonte = _fonte;
        comprimento = _comprimento;
        largura = _largura;
        espacamento = _espacamento;
    }

    public float getComprimento(){
        return comprimento;
    }
    public float getLargura(){
        return largura;
    }
    public float getEspacamento(){
        return espacamento;
    }

    /**
     * @param _caractere Caractere que será procurado na fonte.
     * @return Textura do caractere ou null, se não existir.
     */
    public Texture getTextura(String _caractere){
        return fonte.get(_caractere);
    }

}
