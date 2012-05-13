/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mlptd;

/**
 * Uma torre, que pode atirar projéteis em inimigos.
 * @author diogo
 */
public class Torre extends Desenho {
    /**
     * @param _posX, _posY A posição do ponto superior esquerdo na tela.
     * @param _comprimento, _largura Comprimento e largura.
     * @param _tamanhoEmPorcentagem Usado para resize.
     */
    public Torre(float _posX, float _posY, float _comprimento, float _largura){
        super(_posX, _posY, _comprimento, _largura);
        adicionarTextura(Texturas.TORRE);
    }
    public Torre(Torre _torre) {
        super(_torre);
        adicionarTextura(Texturas.TORRE);
    }
}
