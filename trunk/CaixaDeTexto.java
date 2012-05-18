/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mlptd;

import org.lwjgl.util.Color;
import java.awt.Font;
import java.util.Vector;
import org.newdawn.slick.TrueTypeFont;
import static org.lwjgl.opengl.GL11.*;

/**
 * Uma caixa de texto.
 * Filhos de caixas de texto nunca são desenhados.
 * @author drcravo
 */
public class CaixaDeTexto extends Gui {
    /**
     * A fonte da caixa de texto. Default é dada no construtor.
     */
    private Font fonte;

    /**
     * Recurso utilizado para imprimir o texto na tela.
     */
    private TrueTypeFont fonteParaImpressao;

    /**
     * Conteúdo da caixa de texto.
     */
     private Vector<String> linhasDoConteudo;

    /**
     * @param _comprimento, _largura Dimensões máximas da caixa. Notes que as letras
     *          obedecerão às dimensões de suas fontes, mas nunca ultrapassarão as
     *          dimensões da caixa à qual pertencem.
     */
    public CaixaDeTexto(float _comprimento, float _largura){
        super(_comprimento, _largura);
        mudarCor(new Color(Color.WHITE));
        linhasDoConteudo = new Vector<String>();
        fonte = new Font("Lucida Console", Font.BOLD, 15);
        fonteParaImpressao = new TrueTypeFont(fonte, false);
    }

    /**
     * Modifica o conteúdo da caixa, o texto exibido.
     * @param _texto Texto que será exibido pela caixa de texto.
     */
    public void setTexto(String _texto){
        if(1 < _texto.length()){
            linhasDoConteudo = Texto.quebrarEmLinhas(_texto);
        } else {
            linhasDoConteudo.add(_texto);
        }
    }

    /**
     * Muda a fonte utilizada.
     * @param _fonte Nova fonte a ser utilizada pela caixa de texto.
     */
    public void setFonte(Font _fonte){
        fonte = _fonte;
        fonteParaImpressao = new TrueTypeFont(fonte, false);
    }

    /**
     * Desenha na tela com base em seu tamanho e posicao.
     * O objeto desenhado é um retângulo.
     */
    @Override
    public void desenhar(){
        int linhaAtual=1;

        Camera.CAMERA cameraUsada = Camera.cameraAtiva();
        Camera.setCamera(Camera.CAMERA.ORTOGRAFICA_ESTATICA_INVERTIDA_Y);

        
        float yEspelhadoEmY = Camera.ortografica_estatica.getY()+Tela.HEIGHT - posY; //2 vezes pois imprime de cima para baixo.
        float xDesenhado = posX;
        float yDesenhado;
        for(String texto : linhasDoConteudo){
            // -  pois está imprimindo de cima para baixo
            yDesenhado = yEspelhadoEmY - (linhasDoConteudo.size()*largura)/linhaAtual -fonte.getSize();
            fonteParaImpressao.drawString(xDesenhado, yDesenhado, texto);
            linhaAtual++;
        }

        Camera.setCamera(cameraUsada);
    }

    
}
