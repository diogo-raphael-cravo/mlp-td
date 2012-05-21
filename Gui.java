package mlptd;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Vector;

/**
 * Elemento da interface gráfica.
 * @author diogo
 */
public class Gui extends Desenho{
    /**
     * Verdadeiras dimensões da gui.
     * Utilizadas quando reaparece.
     */
    private float comprimentoVerdadeiro;
    private float larguraVerdadeira;
    
    /**
     * Elementos de interface que aninha.
     */
    protected Vector<Gui> guisFilhas;
    
    /**
     * @param _comprimento, _largura Comprimento e largura da gui.
     */
    public Gui(float _comprimento, float _largura){
        super(0, 0, _comprimento, _largura);
        
        comprimentoVerdadeiro = comprimento;
        larguraVerdadeira = largura;
        
        guisFilhas = new Vector<Gui>();
    }
    
    
    /**
     * Adiciona um desenho filho a este desenho.
     * Ele é sempre desenhado em sua posição.
     * @param _desenho Desenho do filho a ser adicionado.
     * @param _posX, _posY Posição em que o filho é adicionado em relação à posição deste desenho.
     *                     Assim, se _posX=0 e _posY=0, o desenho é adicionado na posição deste desenho.
     * Atenção: a diferença entre um frame e um filho é que o filho permanece sempre,
     * enquanto o frame alterna-se com os outros.
     */
     public void adicionarFilho(Gui _desenhoFilho, float _posX, float _posY){
         super.adicionarFilho(_desenhoFilho, _posX, _posY);
         guisFilhas.add(_desenhoFilho);
     }
    
    public void mostrar() {
        comprimento = comprimentoVerdadeiro;
        largura = larguraVerdadeira;
        for(Gui guiFilha : guisFilhas){
            guiFilha.mostrar();
        }
    }

    public void esconder() {
        comprimentoVerdadeiro = comprimento;
        larguraVerdadeira = largura;
        comprimento = 0.1f;
        largura = 0.1f;
        for(Gui guiFilha : guisFilhas){
            guiFilha.esconder();
        }
    }
    
    
}
