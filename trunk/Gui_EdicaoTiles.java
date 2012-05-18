/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mlptd;

import org.lwjgl.util.Color;

/**
 * Permite adição e remoção de torres.
 * @author diogo
 */
public class Gui_EdicaoTiles extends Gui{
    /**
     * Botões.
     */
    private Botao btAdicionarTorreMadeira;
    private Botao btAdicionarTorreCanhao;
    private Botao btRemoverTorre;
    
    /**
     * @param _comprimento, _largura Comprimento e largura da gui.
     */
    public Gui_EdicaoTiles(float _comprimento, float _largura){
        super(_comprimento, _largura);
        mudarCor(new Color(Color.WHITE));
        btAdicionarTorreMadeira = new Botao(100, 200);
        btAdicionarTorreMadeira.definirTextura(Texturas.TORRE);
        adicionarFilho(btAdicionarTorreMadeira, 200, 100);
        btAdicionarTorreMadeira.redimensionar(50, 50, 0);

        btAdicionarTorreCanhao = new Botao(480, 220);
        btAdicionarTorreCanhao.definirTextura(Texturas.CANHAO);
        adicionarFilho(btAdicionarTorreCanhao, 300, 100);
        btAdicionarTorreCanhao.redimensionar(50, 50, 0);
        
        btRemoverTorre = new Botao(200, 200);
        btRemoverTorre.definirTextura(Texturas.OURO);
        adicionarFilho(btRemoverTorre, 300, 150);
        btRemoverTorre.redimensionar(50, 50, 0);

        
        float comprimentoAtual = comprimento;
        float larguraAtual = largura;
        comprimento = 300;
        largura = 250;
        definirTextura(Texturas.MADEIRA);
        redimensionar(fatorEscalaX*comprimentoAtual, fatorEscalaY*larguraAtual, altura);

        btAdicionarTorreMadeira.redimensionar(50*(1/fatorEscalaX), 50*(1/fatorEscalaY), 0);
        btAdicionarTorreCanhao.redimensionar(50*(1/fatorEscalaX), 50*(1/fatorEscalaY), 0);
        btRemoverTorre.redimensionar(50*(1/fatorEscalaX), 50*(1/fatorEscalaY), 0);
    }
    
    /**
     * @return Botão que adiciona torres.
     */
    public Botao getBtAdicionarTorreMadeira(){
        return btAdicionarTorreMadeira;
    }
    public Botao getBtAdicionarTorreCanhao(){
        return btAdicionarTorreCanhao;
    }
    public Botao getBtRemoverTorre(){
        return btRemoverTorre;
    }

    
    
}



