package mlptd;


import org.lwjgl.opengl.GL11;
import static org.lwjgl.util.glu.GLU.*;
import java.util.logging.Logger;
import java.util.logging.Level;
import org.newdawn.slick.util.ResourceLoader;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.opengl.Texture;
import java.io.IOException;
import java.util.Vector;
import org.lwjgl.util.Color;
import static org.lwjgl.opengl.GL11.*;

/**
 * Um desenho qualquer.
 * Serve como forma de manter interfaces iguais nas classes descendentes.
 * @author diogo
 */
public class Desenho extends Object{
    protected int tamanhoEmPorcentagem;
    protected float posX;
    protected float posY;
    protected float comprimento;
    protected float largura;
    protected float altura;
    protected Color cor;

    /**
     * Rotação em torno de um eixo.
     */
    protected float rotacaoX;
    protected float rotacaoY;
    protected float rotacaoZ;

    /**
     * Utilizada para encontrar este objeto em todosDesenhosCriados.
     */
    private long identificacaoUnica;

    /**
     * Array que contém todos os desenhos criados.
     * Utilizado para aplicação de eventos.
     */
    private static Vector<Desenho> todosDesenhosCriados;

   /**
    * Usado para garantir que identificacaoUnica é única.
    */
    private static long identificacaoUnicaLivre;

    /**
     * Filhos, que são sempre desenhados.
     * O filho é um desenho que estará sempre na mesma posição,
     * relativa à origem de seu desenho pai, não da tela.
     */
    protected Vector<Desenho> filhos;

    /**
     * A textura deste desenho, se houver.
     */
    protected Texture textura;

    /**
     * Desenho pai deste desenho, quando houver.
     * Quando não houver, contém null.
     */
    private Desenho pai;

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
        textura = null;
        redimensionar(1);
        if(todosDesenhosCriados == null){
            todosDesenhosCriados = new Vector<Desenho>();
            identificacaoUnicaLivre=0;
        }
        filhos = new Vector<Desenho>();
        pai = null;
        identificacaoUnica = identificacaoUnicaLivre;
        identificacaoUnicaLivre++;
        rotacaoX = 0;
        rotacaoY = 0;
        rotacaoZ = 0;
    }
    public Desenho(float _posX, float _posY, float _comprimento, float _largura, int _tamanhoEmPorcentagem, float _altura){
        this(_posX, _posY, _comprimento, _largura, _tamanhoEmPorcentagem);
        altura = _altura;
    }
    public Desenho(Desenho _desenho){
        this(_desenho.getPosX(), _desenho.getPosY(), _desenho.getComprimento(), _desenho.getLargura(), _desenho.getTamanhoEmPorcentagem());
        cor = new Color(_desenho.getCor());
        textura = _desenho.getTextura();
        for(Desenho desenho : _desenho.getFilhos()){
            adicionarFilho(desenho, desenho.getPosX(), desenho.getPosY());
        }
    }

    /**
     * Registra os eventos de mouse.
     * Um desenho só obedecerá a eventos quando esta função for invocada nele.
     */
    public void inicializarEventos(){
        todosDesenhosCriados.add(this);
    }
    
    /**
     * @return Array com todos os desenhos já criados. Útil para aplicação de eventos.
     */
    public static Vector<Desenho> getTodosDesenhosCriados(){
        return todosDesenhosCriados;
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
    public long getIdentificacaoUnica(){
        return identificacaoUnica;
    }
    public Vector<Desenho> getFilhos(){
        Vector<Desenho> filhosRetorno = new Vector<Desenho>();
        for(Desenho desenho : filhos){
            filhosRetorno.add(desenho);
        }
        return filhosRetorno;
    }
    public Desenho getPai(){
        return pai;
    }
    public float getGlobalX(){
        float xGlobal = posX;
        Desenho ancestral = getPai();
        while(ancestral != null){
            xGlobal += ancestral.getPosX();
            ancestral = ancestral.getPai();
        }
        return xGlobal;
    }
    public float getGlobalY(){
        float yGlobal = posY;
        Desenho ancestral = getPai();
        while(ancestral != null){
            yGlobal += ancestral.getPosY();
            ancestral = ancestral.getPai();
        }
        return yGlobal;
    }
    public Texture getTextura(){
        return textura;
    }


    /**
     * Modifica o pai deste desenho.
     * @param _pai O pai do desenho.
     * IMPORTANTE: esta função SÓ pode ser usada quando o desenho é adicionado
     * como filho de outro! Se souber outra forma de modelar este comportamento,
     * faça-a, por favor!
     */
    protected void setPai(Desenho _pai){
        pai = _pai;
    }

    /**
     * Rotaciona o desenho.
     * @param _rotacaoX, _rotacaoY, _rotacaoZ Valor da rotação em [0,360).
     *        Valores maiores são truncados.
     */
    public void rotacionar(float _rotacaoX, float _rotacaoY, float _rotacaoZ){
        if(360 <= _rotacaoX){
            rotacaoX = _rotacaoX%360;
        } else if(_rotacaoX <= -360){
            rotacaoX =  -(((-_rotacaoX)%360) - 360);
        } else if(_rotacaoX < 0){
            rotacaoX = 360 - _rotacaoX;
        } else {
            rotacaoX = _rotacaoX;
        }
        if(360 <= _rotacaoY){
            rotacaoY = _rotacaoY%360;
        } else if(_rotacaoY <= -360){
            rotacaoY =  -(((-_rotacaoY)%360) - 360);
        } else if(_rotacaoY < 0){
            rotacaoY = 360 - _rotacaoY;
        } else {
            rotacaoY = _rotacaoY;
        }
        if(360 <= _rotacaoZ){
            rotacaoZ = _rotacaoZ%360;
        } else if(_rotacaoZ <= -360){
            rotacaoZ =  -(((-_rotacaoZ)%360) - 360);
        } else if(_rotacaoZ < 0){
            rotacaoZ = 360 - _rotacaoZ;
        } else {
            rotacaoZ = _rotacaoZ;
        }

        for(Desenho filho : filhos){
            filho.rotacionar(_rotacaoX, _rotacaoY, _rotacaoZ);
        }
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
     public void adicionarFilho(Desenho _desenhoFilho, float _posX, float _posY){
         _desenhoFilho.mover(_posX, _posY);
         filhos.add(_desenhoFilho);
         _desenhoFilho.setPai(this);
     }

     /**
      * Se o filho passado é um dos filhos deste desenho, retirá-o da lista de filhos.
      * @param _desenho Desenho filho a ser procurado e retirado.
      */
     public void removerFilho(Desenho _desenhoFilho){
        int index=0;
        int indexFilho=0;
        boolean filhoEncontrado = false;
        for(Desenho desenhoCriado : filhos){
            if(desenhoCriado.getIdentificacaoUnica() == _desenhoFilho.getIdentificacaoUnica()){
                indexFilho = index;
                filhoEncontrado = true;
            }
            index++;
        }
        if(filhoEncontrado){
            filhos.remove(indexFilho);
        }
     }

    /**
     * Compara este desenho com o desenho de parâmetro e retorna a posição em que
     * o parâmetro deve ficar para estar no centro deste.
     * @param _desenho O desenho que deseja-se que fique no centro deste.
     * @return A posição x em que o parâmetro deve ficar para estar no centro deste.
     */
    public float xGlobalCentroParaDesenho(Desenho _desenho){
        float xCentro = getGlobalX() + getComprimento()/2 - _desenho.getComprimento()/2;
        return xCentro;
    }
    
    /**
     * Compara este desenho com o desenho de parâmetro e retorna a posição em que
     * o parâmetro deve ficar para estar no centro deste.
     * @param _desenho O desenho que deseja-se que fique no centro deste.
     * @return A posição y em que o parâmetro deve ficar para estar no centro deste.
     */
    public float yGlobalCentroParaDesenho(Desenho _desenho){
        float yCentro = getGlobalY() + getLargura()/2 - _desenho.getLargura()/2;
        return yCentro;
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
     * O movimento é feito de uma só vez.
     * @param _destino Ponto para onde o desenho deve ir.
     */
    public void deslocar(float _deslocamentoX, float _deslocamentoY){
        posX += _deslocamentoX;
        posY += _deslocamentoY;
    }
    
    /**
     * @param _tamanhoEmPorcentagem O tamanho em rela��o ao tamanho inicial (de cria��o) do desenho.
     */
    public void redimensionar(int _tamanhoEmPorcentagem){
        tamanhoEmPorcentagem = _tamanhoEmPorcentagem;
    }

    /**
     * Indica se o ponto (em coordenadas globais) pertence a este desenho.
     * @param _posX, _posY Ponto a ser testado
     * @return Boleano indicando se o ponto pertence ao desenho.
     */
    public boolean contem(float _posX, float _posY){
        boolean contem=false;
        float xTestado = getGlobalX();
        float yTestado = getGlobalY();
        if(xTestado <= _posX && _posX <= xTestado+comprimento){
            if(yTestado <= _posY && _posY <= yTestado+largura){
               contem = true;
            }
        }
        return contem;
    }
    public boolean contem(Desenho _desenho){
        boolean contem=false;
        boolean contemExtremoNoroeste = false;
        boolean contemExtremoNordeste = false;
        boolean contemExtremoSudoeste = false;
        boolean contemExtremoSudeste = false;

        contemExtremoNoroeste = contem(_desenho.getGlobalX(), _desenho.getGlobalY());
        contemExtremoNordeste = contem(_desenho.getGlobalX()+_desenho.getComprimento(), _desenho.getGlobalY());
        contemExtremoSudoeste = contem(_desenho.getGlobalX(), _desenho.getGlobalY()+_desenho.getLargura());
        contemExtremoSudeste = contem(_desenho.getGlobalX()+_desenho.getComprimento(), _desenho.getGlobalY()+_desenho.getLargura());

        contem = contemExtremoNoroeste
              || contemExtremoNordeste
              || contemExtremoSudoeste
              || contemExtremoSudeste;
        return contem;
    }

    /**
     * Adiciona textura a este desenho.
     * Não é necessário especificar o tipo da imagem, pois ele é "adivinhado" pela função.
     * @param _caminho O caminho absoluto, no sistema de arquivos, do arquivo da textura.
     */
    public void adicionarTextura(String _caminho){
        try {
            textura = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream(_caminho));
        } catch (IOException ex1) {
            try {
                textura = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(_caminho));
            } catch (IOException ex2) {
                try {
                    textura = TextureLoader.getTexture("GIF", ResourceLoader.getResourceAsStream(_caminho));
                } catch (IOException ex3) {
                    Logger.getLogger(Desenho.class.getName()).log(Level.SEVERE, null, ex3);
                }
            }
        }
    }

    /**
     * Desenha na tela com base em seu tamanho e posicao.
     * O objeto desenhado é um retângulo.
     */
    public void desenhar(){
        if(textura != null){
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            textura.bind();
        } else {
            GL11.glDisable(GL11.GL_TEXTURE_2D);
        }
        glPushMatrix();
        
        glRotatef(rotacaoX,1.0f,0.0f,0.0f);
        glRotatef(rotacaoY,0.0f,1.0f,0.0f);
        glRotatef(rotacaoZ,0.0f,0.0f,1.0f);
        glColor4f((float) (cor.getRed()/255.0),
                  (float) (cor.getGreen()/255.0),
                  (float) (cor.getBlue()/255.0),
                  (float) (cor.getAlpha()/255.0));
        glBegin(GL_QUADS);
        if(textura != null){
            glTexCoord3f(0.0f, largura/textura.getImageHeight(), 0.0f);
                glVertex3f(posX, posY, 0.0f);
            glTexCoord3f(comprimento/textura.getImageWidth(), largura/textura.getImageHeight(), 0.0f);
                glVertex3f(posX+tamanhoEmPorcentagem*comprimento, posY, 0.0f);
            glTexCoord3f(comprimento/textura.getImageWidth(),0.0f, 0.0f);
                glVertex3f(posX+tamanhoEmPorcentagem*comprimento, posY+tamanhoEmPorcentagem*largura, 0.0f);
            glTexCoord3f(0.0f,0.0f, 0.0f);
                glVertex3f(posX, posY+tamanhoEmPorcentagem*largura, 0.0f);
        } else {
            glTexCoord3f(0.0f,1.0f, 0.0f);
                glVertex3f(posX, posY, 0.0f);
            glTexCoord3f(1.0f,1.0f, 0.0f);
                glVertex3f(posX+tamanhoEmPorcentagem*comprimento, posY, 0.0f);
            glTexCoord3f(1.0f,0.0f, 0.0f);
                glVertex3f(posX+tamanhoEmPorcentagem*comprimento, posY+tamanhoEmPorcentagem*largura, 0.0f);
            glTexCoord3f(0.0f,0.0f, 0.0f);
                glVertex3f(posX, posY+tamanhoEmPorcentagem*largura, 0.0f);
        }
        glEnd();
        glPopMatrix();
        for(Desenho desenhoFilho : filhos){
            desenhoFilho.deslocar(posX, posY);
            desenhoFilho.desenhar();
            desenhoFilho.deslocar(-posX, -posY);
        }
    }

    /**
     * Torna este desenho semi-transparente. Utilizado para seleção.
     * Chamadas sucessivas não possuem efeito, apenas a primeira.
     */
    public void tornarTransparente() {
        cor.setAlpha(127);
    }
    /**
     * Restaura a transparência para como era antes de tornarTransparente.
     */
    public void restaurarTransparencia() {
        cor.setAlpha(255);
    }

    /**
     * @return String com os dados deste desenho.
     */
    @Override
    public String toString(){
        StringBuffer string = new StringBuffer("Desenho {\n");
        string.append("\t tamanhoEmPorcentagem = "+tamanhoEmPorcentagem+"\n");
        string.append("\t posX = "+posX+"\n");
        string.append("\t posY = "+posY+"\n");
        string.append("\t comprimento = "+comprimento+"\n");
        string.append("\t largura = "+largura+"\n");
        string.append("\t altura = "+altura+"\n");
        string.append("\t cor = "+cor.toString()+"\n");
        string.append("\t filhos = "+filhos.toString()+"\n");
        string.append("}");
        return string.toString();
    }

    /**
     * Destrói este desenho, retirando-o da lista de desenhos existentes.
     */
    public void destruir() {
        int index=0;
        int indexDesteDesenho=0;
        boolean desenhoEncontrado = false;
        for(Desenho desenhoCriado : todosDesenhosCriados){
            if(desenhoCriado.getIdentificacaoUnica() == identificacaoUnica){
                indexDesteDesenho = index;
                desenhoEncontrado = true;
            }
            index++;
        }
        if(desenhoEncontrado){
            todosDesenhosCriados.remove(indexDesteDesenho);
        }
    }
}