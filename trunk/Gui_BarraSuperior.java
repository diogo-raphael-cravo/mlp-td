package mlptd;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import org.lwjgl.util.Color;

/**
 * Elemento da gui que fica na parte superior da tela.
 * Fornece dados de vida do jogador e dinheiro.
 * @author drcravo
 */
public class Gui_BarraSuperior extends Desenho {
    /**
     * Desenho que indica ouro, ao lado do qual é impressa a quantidade de ouro.
     */
     private Desenho iconeOuro;
     private CaixaDeTexto ctOuro;
     private Integer quantidadeOuro;

    /**
     * Desenho que indica vidas, ao lado do qual é impressa a quantidade de vidas.
     */
     private Desenho iconeVidas;
     private CaixaDeTexto ctVidas;
     private Integer quantidadeVidas;

    /**
     * Desenho que indica o nível atual e o total de níveis.
     */
     private Desenho iconeNiveis;
     private CaixaDeTexto ctNiveis;
     private Integer nivelAtual;
     private Integer totalNiveis;

    /**
     * Desenho que indica unidades de tempo.
     */
     private Desenho iconeRelogio;
     private CaixaDeTexto ctTempo;
     private Integer segundos;
     private Integer minutos;

    public Gui_BarraSuperior(){
        super(0, Tela.HEIGHT-30, Tela.WIDTH, 30);
        mudarCor(new Color(Color.WHITE));
        definirTextura(Texturas.MURO);

        quantidadeOuro = 0;
        nivelAtual = 0;
        totalNiveis = 0;
        quantidadeVidas = 0;
        segundos = 0;
        minutos = 0;

        iconeOuro = new Desenho(0, 0, 200, 200);
        iconeOuro.definirTextura(Texturas.OURO);
        iconeOuro.redimensionar(15, 15, 0);
        adicionarFilho(iconeOuro, 7.5f, 7.5f);

        iconeNiveis = new Desenho(0, 0, 35, 40);
        iconeNiveis.definirTextura(Texturas.CAVEIRA);
        iconeNiveis.redimensionar(15, 15, 0);
        adicionarFilho(iconeNiveis, 7.5f+comprimento/4, 7.5f);

        iconeVidas = new Desenho(0, 0, 250, 250);
        iconeVidas.definirTextura(Texturas.CORACAO);
        iconeVidas.redimensionar(15, 15, 0);
        adicionarFilho(iconeVidas, 7.5f+comprimento/2, 7.5f);

        iconeRelogio = new Desenho(0, 0, 470, 470);
        iconeRelogio.definirTextura(Texturas.RELOGIO);
        iconeRelogio.redimensionar(15, 15, 0);
        adicionarFilho(iconeRelogio, 7.5f+3*comprimento/4, 7.5f);
        
        /**
         * As caixas de texto devem ser adicionados DEPOIS dos ícones.
         * Assim, elas não interferem no desenhar() dos ícones.
         */
        ctOuro = new CaixaDeTexto(50, 0);
        ctOuro.setTexto(quantidadeOuro.toString());
        adicionarFilho(ctOuro, 7.5f+20, 7.5f);
        
        ctNiveis = new CaixaDeTexto(50, 0);
        ctNiveis.setTexto(nivelAtual.toString()+"/"+totalNiveis.toString());
        adicionarFilho(ctNiveis, 7.5f+comprimento/4+20, 7.5f);

        ctVidas = new CaixaDeTexto(50, 0);
        ctVidas.setTexto(quantidadeVidas.toString());
        adicionarFilho(ctVidas, 7.5f+comprimento/2+20, 7.5f);

        ctTempo = new CaixaDeTexto(50, 0);
        ctTempo.setTexto(minutos.toString()+":"+segundos.toString());
        adicionarFilho(ctTempo, 7.5f+3*comprimento/4+20, 7.5f);
    }

    public void setQuantidadeOuro(int _ouro){
        quantidadeOuro = _ouro;
        ctOuro.setTexto(quantidadeOuro.toString());
    }
    public void setNivelAtual(int _nivelAtual){
        nivelAtual = _nivelAtual;
        ctNiveis.setTexto(nivelAtual.toString()+"/"+totalNiveis.toString());
    }
    public void setTotalNiveis(int _totalNiveis){
        totalNiveis = _totalNiveis;
        ctNiveis.setTexto(nivelAtual.toString()+"/"+totalNiveis.toString());
    }
    public void setQuantidadeVidas(int _vidas){
        quantidadeVidas = _vidas;
        ctVidas.setTexto(quantidadeVidas.toString());
    }
    public void setTempo(int _segundos){
        minutos = _segundos/60;
        segundos = _segundos%60;
        
        ctTempo.setTexto(minutos.toString()+":"+segundos.toString());
    }
    
}
