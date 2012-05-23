/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mlptd;

import java.util.Vector;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.Color;

/**
 * Controla eventos do terreno.
 * @author diogo
 */
public class ControladorTerreno
    implements MouseListener {
    /**
     * Terreno controlado.
     */
    private Terreno terreno;

    /**
     * Batalha que será informada sempre que torres forem adicionadas ao terreno.
     */
    private Batalha batalhaTerreno;

    /**
     * Objetos que podem ser selecionados.
     */
    private Inimigo inimigoSelecionado;
    private TileEdificavel tileEdificavelSelecionada;

    /**
     * Objeto que está selecionado.
     */
    private Desenho objetoSelecionado;

    /**
     * Controlador da gui que edita o terreno controlado por este objeto.
     */
    private ControladorGuiBarraInferior controladorGuiEdicaoTerreno;

    /**
     * @param _terreno Terreno que será controlado.
     * @param _batalha Batalha que será informada sempre que alguma torre
     *      for adicionada ao terreno.
     */
    public ControladorTerreno(Terreno _terreno, Batalha _batalha){
        terreno = _terreno;
        objetoSelecionado = null;
        inimigoSelecionado = null;
        tileEdificavelSelecionada = null;
        controladorGuiEdicaoTerreno = null;
        batalhaTerreno = _batalha;
    }

    /**
     * @param _controladorGuiEdicaoTerreno Controlador da gui que edita o terreno controlado por este objeto.
     */
    public void definirControladorGuiEdicaoTerreno(ControladorGuiBarraInferior _controladorGuiEdicaoTerreno){
        controladorGuiEdicaoTerreno = _controladorGuiEdicaoTerreno;
    }
        

    public void mouseMoveu() {
        float cameraMouseX = Camera.xTelaParaGlobal(Mouse.getX());
        float cameraMouseY = Camera.yTelaParaGlobal(Mouse.getY());
        Vector<TileEdificavel> tilesEdificaveisDoTerreno = terreno.getTilesEdificaveis();
        for(TileEdificavel tileEdificavel : tilesEdificaveisDoTerreno){
            if(!haTileEdificavelSelecionada()
                    || tileEdificavelSelecionada.getIdentificacaoUnica() != tileEdificavel.getIdentificacaoUnica()){
                if(tileEdificavel.contem(cameraMouseX, cameraMouseY)){
                    tileEdificavel.mudarCor(new Color(Color.GREY));
                } else {
                    tileEdificavel.mudarCor(new Color(Color.WHITE));
                }
            }
        }
    }

    public void houveMouseDown() {
        
    }

    /**
     * @return Booleano indicando se a tile edificável que está selecionada pode ser ocupada
     *      por uma torre. Se não houver tile edificável selecionada, retorna false.
     */
    public boolean tileEdificavelSelecionadaEstahLivre(){
        if(haTileEdificavelSelecionada() && !tileEdificavelSelecionada.ocupadaPorTorre()){
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return Booleano indicando se há algum objeto selecionado, qualquer
     *      que seja seu tipo.
     */
    public boolean haObjetoSelecionado(){
        if(objetoSelecionado != null){
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return Booleano indicando se há tile edificável selecionada.
     */
    public boolean haTileEdificavelSelecionada(){
        if(haObjetoSelecionado() && tileEdificavelSelecionada != null){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Diz ao controlador que deve ser construída uma torre na tile que estiver selecionada,
     * caso haja.
     * @param _tipoTorre O tipo de torre que deve ser construído.
     */
    public void construirTorreNaTileSelecionada(Torre.TIPO_TORRE _tipo){
        if(haTileEdificavelSelecionada()){
            Torre torreQueSerahAdicionada = new Torre(0, 0, 100, 200, Torre.TIPO_TORRE.MADEIRA);
            switch(_tipo){
                case MADEIRA: torreQueSerahAdicionada = new Torre(0, 0, 100, 200, Torre.TIPO_TORRE.MADEIRA);
                    break;
                case CANHAO: torreQueSerahAdicionada = new Torre(0, 0, 100, 200, Torre.TIPO_TORRE.CANHAO);
                    break;
            }
            torreQueSerahAdicionada.redimensionar(50, 100, 0);
            tileEdificavelSelecionada.construirTorre(torreQueSerahAdicionada);
            batalhaTerreno.adicionarTorre(tileEdificavelSelecionada.getTorre());
        }
    }

    /**
     * Destrói a torre da tile que estiver selecionada.
     */
    public void destruirTorreTileSelecionada(){
        if(haTileEdificavelSelecionada()){
            tileEdificavelSelecionada.destruirTorre();
        }
    }

    /**
     * @return Cópia do desenho que estiver selecionado, não importando
     *      o seu tipo. Retornará null se não houver desenho selecionado.
     */
    public Desenho getCopiaObjetoSelecionado(){
        if(haObjetoSelecionado()){
           return new Desenho(objetoSelecionado);
        } else {
            return null;
        }
    }
    public TileEdificavel getCopiaTileEdificavelSelecionada(){
        if(haTileEdificavelSelecionada()){
            return new TileEdificavel(tileEdificavelSelecionada);
        } else {
            return null;
        }
    }

     /**
     * Em um mouseDown, lida com a seleção de desenho.
     */
    public void tentarSelecaoDesenho(){
        /**
         * ATENÇÃO: elementos que foram rotacionados devem ser testados
         *      em coordenadas rotacionadas!
         */
        /**
         * Mouse.getX() e Mouse.getY() sempre retornam valores
         * entre (0,0) e (Tela.WIDTH,Tela.HEIGHT).
         */
        float mouseX = Camera.xTelaParaGlobal(Mouse.getX());
        float mouseY = Camera.yTelaParaGlobal(Mouse.getY());
        Vector<TileEdificavel> tilesEdificaveisDoTerreno = terreno.getTilesEdificaveis();
        for(TileEdificavel tileEdificavel : tilesEdificaveisDoTerreno){
            if(tileEdificavel.contem(mouseX, mouseY)){
                objetoSelecionado = tileEdificavel;
                tileEdificavelSelecionada = tileEdificavel;
                tileEdificavelSelecionada.mudarCor(new Color(Color.WHITE));
                tileEdificavelSelecionada.tornarTransparente();
                Tela.getGuiBarraInferior().getGuiRetrato().exibir(tileEdificavelSelecionada);
                Tela.getGuiBarraInferior().getGuiEdicaoTiles().mostrar();
            }
        }
    }

    /**
     * Desfaz a seleção de todos os objetos selecionados.
     */
    public void desfazerSelecoes(){
        objetoSelecionado = null;
        if(tileEdificavelSelecionada != null){
            tileEdificavelSelecionada.restaurarTransparencia();
            tileEdificavelSelecionada = null;
            Tela.getGuiBarraInferior().getGuiRetrato().retirarDesenhoExibido();
            Tela.getGuiBarraInferior().getGuiEdicaoTiles().esconder();
        }
    }

}
