/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mlptd;

import java.util.Vector;

/**
 * Um desenho que possui quadros, executados de forma circular em um determinado tempo.
 * @author drcravo
 */
public class Filme extends Desenho {
    /**
     * Indica o frame que foi exibido na última chamada a desenhar.
     * Inicia em 0.
     */
     private int quadroExibido;

    /**
     * Quadros, que são desenhados de forma alternada.
     */
    private Vector<Desenho> quadros;

    /**
     * @param _posX, _posY A posi��o do ponto superior esquerdo na tela.
     * @param _comprimento, _largura, _altura Comprimento, largura e altura.
     * @param _tamanhoEmPorcentagem Usado para resize.
     */
    public Filme(float _posX, float _posY, float _comprimento, float _largura, int _tamanhoEmPorcentagem){
        super(_posX, _posY, _comprimento, _largura, _tamanhoEmPorcentagem);
        quadros = new Vector<Desenho>();
        quadroExibido = 0;
    }
    public Filme(Filme _filme){
        this(_filme.getPosX(), _filme.getPosY(),
             _filme.getComprimento(), _filme.getLargura(),
             _filme.getTamanhoEmPorcentagem());
        for(Desenho desenho : _filme.getQuadros()){
            adicionarQuadro(desenho);
        }
    }

    public Vector<Desenho> getQuadros(){
        Vector<Desenho> quadrosRetorno = new Vector<Desenho>();
        for(Desenho desenho : quadros){
            quadrosRetorno.add(desenho);
        }
        return quadrosRetorno;
    }

    /**
     * O movimento é feito de uma só vez.
     * Movimenta todos os quadros para a posição dada.
     * @param _destino Ponto para onde o desenho deve ir.
     */
    public void mover(float _posX, float _posY){
        super.mover(_posX, _posY);
        for(Desenho desenho : quadros){
            desenho.mover(_posX, _posY);
        }
    }

    /**
     * O movimento é feito de uma só vez.
     * Movimenta todos os quadros para a posição dada.
     * @param _destino Ponto para onde o desenho deve ir.
     */
    public void deslocar(float _deslocamentoX, float _deslocamentoY){
        super.deslocar(_deslocamentoX, _deslocamentoY);
        for(Desenho desenho : quadros){
            desenho.deslocar(_deslocamentoX, _deslocamentoY);
        }
    }

    /**
     * Adiciona um quadro a este desenho.
     * Quando o filme é desenhado, alterna-se entre seus quadros.
     * @param _desenho Desenho do quadro a ser adicionado.
     */
    public void adicionarQuadro(Desenho _desenhoFrame){
        quadros.add(new Desenho(_desenhoFrame));
    }

    /**
     * Desenha na tela com base em seu tamanho e posicao.
     * Desenha um frame por vez e cada chamada pode alternar o frame atual,
     * baseada no tempo total de animação e número de frames que possui.
     */
    public void desenhar(){
        if(0 < quadros.size()){
            quadroExibido++;
            if(quadroExibido == quadros.size()){
                quadroExibido = 0;
            }
            quadros.get(quadroExibido).desenhar();
        }
    }
}
