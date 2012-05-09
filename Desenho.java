package mlptd;

import org.lwjgl.util.Color;
import static org.lwjgl.opengl.GL11.*;

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
        cor = new Color(Color.WHITE);
        posX = _posX;
        posY = _posY;
        comprimento = _comprimento;
        largura = _largura;
        tamanhoEmPorcentagem = _tamanhoEmPorcentagem;
        altura = 1;
        redimensionar(1);
    }
    public Desenho(float _posX, float _posY, float _comprimento, float _largura, int _tamanhoEmPorcentagem, float _altura){
        this(_posX, _posY, _comprimento, _largura, _tamanhoEmPorcentagem);
        altura = _altura;
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
     * O movimento é feito de uma só vez.
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
     * Desenha na tela com base em seu tamanho e posicao.
     * O objeto desenhado é um retângulo.
     */
    public void desenhar(){
        glPushMatrix();
        glTranslatef(posX,posY,0.0f);
        glRotatef(0,0.0f,0.0f,1.0f);
        glTranslatef(-(100 >> 1),-(100 >> 1),0.0f);
        glColor3f((float) (cor.getRed()/255.0),
                  (float) (cor.getGreen()/255.0),
                  (float) (cor.getBlue()/255.0));
        glBegin(GL_QUADS);
            glTexCoord2f(0.0f,0.0f); glVertex2f(0.0f,0.0f);
            glTexCoord2f(1.0f,0.0f); glVertex2f(tamanhoEmPorcentagem*comprimento, 0.0f);
            glTexCoord2f(1.0f,1.0f); glVertex2f(tamanhoEmPorcentagem*comprimento, tamanhoEmPorcentagem*largura);
            glTexCoord2f(0.0f,1.0f); glVertex2f(0.0f, tamanhoEmPorcentagem*largura);
        glEnd();
        glPopMatrix();
    }
}