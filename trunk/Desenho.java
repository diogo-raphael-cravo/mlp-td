package mlptd;

import org.lwjgl.util.Color;

/**
 * Um desenho qualquer.
 * Serve como forma de manter interfaces iguais nas classes descendentes.
 * @author diogo
 */
public class Desenho {
    protected int tamanhoEmPorcentagem;
    protected float posX;
    protected float posY;
    protected float comprimento;
    protected float largura;
    protected float altura;
    protected Color cor;

    /**
     * @param _posX, _posY A posi��o do ponto superior esquerdo na tela.
     * @param _comprimento, _largura, _altura Comprimento, largura e altura.
     * @param _tamanhoEmPorcentagem Usado para resize.
     */
    public Desenho(float _posX, float _posY, float _comprimento, float _largura, int _tamanhoEmPorcentagem){
        cor = new Color(Color.GREEN);
        posX = _posX;
        posY = _posY;
        comprimento = _comprimento;
        largura = _largura;tamanhoEmPorcentagem = _tamanhoEmPorcentagem;
        altura = 1;
        redimensionar(1);
    }
    public Desenho(float _posX, float _posY, float _comprimento, float _largura, int _tamanhoEmPorcentagem, float _altura){
        cor = new Color(Color.GREEN);
        posX = _posX;
        posY = _posY;
        comprimento = _comprimento;
        largura = _largura;tamanhoEmPorcentagem = _tamanhoEmPorcentagem;
        altura = _altura;
        redimensionar(1);
    }


    public float getPosX(){
        return posX;
    }
    public float getPosY(){
        return posY;
    }
    public float getComprimento(){
        return comprimento;
    }
    public float getLargura(){
        return largura;
    }
    public int getTamanhoEmPorcentagem(){
        return tamanhoEmPorcentagem;
    }
    public Color getCor(){
        return cor;
    }
	
    /**
     * Modifica a cor deste desenho.
     * @param _cor Cor do desenho.
     */
    public void mudarCor(Color _cor){
        cor = new Color(_cor);
    }

    /**
     * @param _destino Ponto para onde o desenho deve ir.
     */
    public void mover(float _posX, float _posY){
        posX = _posX;
        posY = _posY;
    }

    /**
     * @param _tamanhoEmPorcentagem O tamanho em rela��o ao tamanho inicial (de cria��o) do desenho.
     */
    public void redimensionar(int _tamanhoEmPorcentagem){
        tamanhoEmPorcentagem = _tamanhoEmPorcentagem;
    }
	
    /**
     * Desenha na tela com base em seu tamanho e posi��o.
     * Deve ser implementada em casa classe descendente.
     */
    public void desenhar(){}
}