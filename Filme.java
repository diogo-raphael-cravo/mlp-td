/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mlp.td;

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
     * Array que contém todos os filmes criados.
     * Utilizado para aplicação de eventos.
     */
    private static Vector<Filme> todosFilmesCriados;

    /**
     * @param _posX, _posY A posi��o do ponto superior esquerdo na tela.
     * @param _comprimento, _largura, _altura Comprimento, largura e altura.
     * @param _tamanhoEmPorcentagem Usado para resize.
     */
    public Filme(float _posX, float _posY, float _comprimento, float _largura){
        super(_posX, _posY, _comprimento, _largura);
        quadros = new Vector<Desenho>();
        quadroExibido = 0;
        if(todosFilmesCriados == null){
            todosFilmesCriados = new Vector<Filme>();
        }
    }
    public Filme(Filme _filme){
        this(_filme.getPosX(), _filme.getPosY(),
             _filme.getComprimento(), _filme.getLargura());
        for(Desenho desenho : _filme.getQuadros()){
            adicionarQuadro(desenho);
        }
    }

    /**
     * Registra os eventos de mouse.
     * Um filme só obedecerá a eventos quando esta função for invocada nele.
     */
    public void inicializarEventos(){
        todosFilmesCriados.add(this);
    }

    /**
     * @return Array com todos os filmes já criados. Útil para aplicação de eventos.
     */
    public static Vector<Filme> getTodosFilmesCriados(){
        return todosFilmesCriados;
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
     * Rotaciona o desenho.
     * @param _rotacaoX, _rotacaoY, _rotacaoZ Valor da rotação em [0,360).
     *        Valores maiores são truncados.
     */
    public void rotacionar(float _rotacaoX, float _rotacaoY, float _rotacaoZ){
        super.rotacionar(_rotacaoX, _rotacaoY, _rotacaoZ);
        for(Desenho desenho : quadros){
            desenho.rotacionar(_rotacaoX, _rotacaoY, _rotacaoZ);
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
        desenharFilhos();
    }

    /**
     * Destrói este filme, retirando-o da lista de filme existentes.
     */
    public void destruir() {
        int index=0;
        int indexDesteDesenho=0;
        boolean desenhoEncontrado = false;
        for(Desenho desenhoCriado : todosFilmesCriados){
            if(desenhoCriado.getIdentificacaoUnica() == getIdentificacaoUnica()){
                indexDesteDesenho = index;
                desenhoEncontrado = true;
            }
            index++;
        }
        if(desenhoEncontrado){
            todosFilmesCriados.remove(indexDesteDesenho);
        }
    }
}
