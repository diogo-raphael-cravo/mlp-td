package mlptd;


import org.lwjgl.opengl.GL11;
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
     */
    protected Vector<Desenho> filhos;

    /**
     * A textura deste desenho, se houver.
     */
    private Texture textura;

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
        identificacaoUnica = identificacaoUnicaLivre;
        identificacaoUnicaLivre++;
    }
    public Desenho(float _posX, float _posY, float _comprimento, float _largura, int _tamanhoEmPorcentagem, float _altura){
        this(_posX, _posY, _comprimento, _largura, _tamanhoEmPorcentagem);
        altura = _altura;
    }
    public Desenho(Desenho _desenho){
        this(_desenho.getPosX(), _desenho.getPosY(), _desenho.getComprimento(), _desenho.getLargura(), _desenho.getTamanhoEmPorcentagem());
        cor = new Color(_desenho.getCor());
        for(Desenho desenho : _desenho.getFilhos()){
            adicionarFilho(desenho, desenho.getPosX()-posX, desenho.getPosY()-posY);
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
         filhos.add(_desenhoFilho);
         _desenhoFilho.mover(posX+_posX, posY+_posY);
     }

    /**
     * Compara este desenho com o desenho de parâmetro e retorna a posição em que
     * o parâmetro deve ficar para estar no centro deste.
     * @param _desenho O desenho que deseja-se que fique no centro deste.
     * @return A posição x em que o parâmetro deve ficar para estar no centro deste.
     */
    public float xCentroParaDesenho(Desenho _desenho){
        float xCentro = getPosX() + getComprimento()/2 - _desenho.getComprimento()/2;
        return xCentro;
    }
    
    /**
     * Compara este desenho com o desenho de parâmetro e retorna a posição em que
     * o parâmetro deve ficar para estar no centro deste.
     * @param _desenho O desenho que deseja-se que fique no centro deste.
     * @return A posição y em que o parâmetro deve ficar para estar no centro deste.
     */
    public float yCentroParaDesenho(Desenho _desenho){
        float yCentro = getPosY() + getLargura()/2 - _desenho.getLargura()/2;
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
        for(Desenho desenhoFilho : filhos){
            desenhoFilho.mover(_posX, _posY);
        }
    }

    /**
     * O movimento é feito de uma só vez.
     * @param _destino Ponto para onde o desenho deve ir.
     */
    public void deslocar(float _deslocamentoX, float _deslocamentoY){
        posX += _deslocamentoX;
        posY += _deslocamentoY;
        for(Desenho desenhoFilho : filhos){
            desenhoFilho.deslocar(_deslocamentoX, _deslocamentoY);
        }
    }
    
    /**
     * @param _tamanhoEmPorcentagem O tamanho em rela��o ao tamanho inicial (de cria��o) do desenho.
     */
    public void redimensionar(int _tamanhoEmPorcentagem){
        tamanhoEmPorcentagem = _tamanhoEmPorcentagem;
    }

    /**
     * Indica se o ponto pertence a este desenho.
     * @param _posX, _posY Ponto a ser testado
     * @return Boleano indicando se o ponto pertence ao desenho.
     */
    public boolean contem(float _posX, float _posY){
        boolean contem=false;
        if(posX <= _posX && _posX <= posX+comprimento){
            if(posY <= _posY && _posY <= posY+largura){
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

        contemExtremoNoroeste = contem(_desenho.getPosX(), _desenho.getPosY());
        contemExtremoNordeste = contem(_desenho.getPosX()+_desenho.getComprimento(), _desenho.getPosY());
        contemExtremoSudoeste = contem(_desenho.getPosX(), _desenho.getPosY()+_desenho.getLargura());
        contemExtremoSudeste = contem(_desenho.getPosX()+_desenho.getComprimento(), _desenho.getPosY()+_desenho.getLargura());

        contem = contemExtremoNoroeste
              || contemExtremoNordeste
              || contemExtremoSudoeste
              || contemExtremoSudeste;
        return contem;
    }

    /**
     * Adiciona textura a este desenho.
     * @param _caminho O caminho absoluto, no sistema de arquivos, do arquivo da textura.
     * @param _tipo O tipo, pode ser "GIF", "JPG" ou "PNG".
     */
    public void adicionarTextura(String _caminho, String _tipo){
        try {
            textura = TextureLoader.getTexture(_tipo, ResourceLoader.getResourceAsStream(_caminho));
        } catch (IOException ex) {
            Logger.getLogger(Desenho.class.getName()).log(Level.SEVERE, null, ex);
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
        glTranslatef(posX,posY,0.0f);
        glRotatef(0,0.0f,0.0f,1.0f);
        glTranslatef(-(100 >> 1),-(100 >> 1),0.0f);
        glColor4f((float) (cor.getRed()/255.0),
                  (float) (cor.getGreen()/255.0),
                  (float) (cor.getBlue()/255.0),
                  (float) (cor.getAlpha()/255.0));
        glBegin(GL_QUADS);
            glTexCoord2f(0.0f,0.0f); glVertex2f(0.0f,0.0f);
            glTexCoord2f(1.0f,0.0f); glVertex2f(tamanhoEmPorcentagem*comprimento, 0.0f);
            glTexCoord2f(1.0f,1.0f); glVertex2f(tamanhoEmPorcentagem*comprimento, tamanhoEmPorcentagem*largura);
            glTexCoord2f(0.0f,1.0f); glVertex2f(0.0f, tamanhoEmPorcentagem*largura);
        glEnd();
        glPopMatrix();
        for(Desenho desenhoFilho : filhos){
            desenhoFilho.desenhar();
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
        for(Desenho desenhoCriado : todosDesenhosCriados){
            if(desenhoCriado.getIdentificacaoUnica() == identificacaoUnica){
                indexDesteDesenho = index;
            }
            index++;
        }
        todosDesenhosCriados.remove(indexDesteDesenho);
    }
}