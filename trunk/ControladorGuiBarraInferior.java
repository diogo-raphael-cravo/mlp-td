/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mlptd;

import org.lwjgl.util.Color;
import java.util.Vector;
import org.lwjgl.input.Mouse;

/**
 * Controla eventos passados à gui_BarraInferior.
 * @author diogo
 */
public class ControladorGuiBarraInferior
    implements MouseListener{
    /**
     * Gui controlada por este controlador.
     */
    private Gui_BarraInferior gui;

    /**
     * O desenho que foi selecionado pelo mouse.
     */
     Desenho desenhoSelecionado;
     Filme filmeSelecionado;
     TileEdificavel tileEdificavelSelecionada;

    /**
     * @param _gui A gui controlada por este controlador.
     */
    public ControladorGuiBarraInferior(Gui_BarraInferior _gui){
        gui = _gui;
        desenhoSelecionado = null;
        filmeSelecionado = null;
        tileEdificavelSelecionada = null;
    }

    public void houveMouseDown() {
        float mouseX = Mouse.getX();
        float mouseY = Mouse.getY();
        
        if(gui.contem(mouseX, mouseY)){
            if(gui.getGuiEdicaoTiles().getBtAdicionarTorreMadeira().contem(mouseX, mouseY)
                    && tileEdificavelSelecionada != null
                    && !tileEdificavelSelecionada.ocupadaPorTorre()){
                Torre torreAdicionada = new Torre(0, 0, 100, 200, Torre.TIPO_TORRE.MADEIRA);
                torreAdicionada.redimensionar(50, 100, 0);
                tileEdificavelSelecionada.construirTorre(torreAdicionada);
                gui.getGuiRetrato().exibir(tileEdificavelSelecionada);
                gui.getGuiEdicaoTiles().mostrar();
            } else if(gui.getGuiEdicaoTiles().getBtAdicionarTorreCanhao().contem(mouseX, mouseY)
                    && tileEdificavelSelecionada != null
                    && !tileEdificavelSelecionada.ocupadaPorTorre()){
                Torre torreAdicionada = new Torre(0, 0, 100, 200, Torre.TIPO_TORRE.CANHAO);
                torreAdicionada.redimensionar(50, 100, 0);
                tileEdificavelSelecionada.construirTorre(torreAdicionada);
                gui.getGuiRetrato().exibir(tileEdificavelSelecionada);
                gui.getGuiEdicaoTiles().mostrar();
            } else if(gui.getGuiEdicaoTiles().getBtRemoverTorre().contem(mouseX, mouseY)
                    && tileEdificavelSelecionada != null){
                tileEdificavelSelecionada.destruirTorre();
                gui.getGuiRetrato().exibir(tileEdificavelSelecionada);
                gui.getGuiEdicaoTiles().mostrar();
            } else {
                desfazerSelecoes();
            }
        } else {
            desfazerSelecoes();
            tentarSelecaoDesenho();
        }

        
    }

    /**
     * Em um mouseDown, lida com a seleção de desenho.
     */
    private void tentarSelecaoDesenho(){
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
        //System.out.println("Global>"+mouseX+","+mouseY);
        float xMouseRotacionado = mouseX;//Camera.xGlobalParaCamera(mouseX, mouseY);
        float yMouseRotacionado = mouseY+200;//-constante*angulo//Camera.yGlobalParaCamera(mouseX, mouseY);
        //System.out.println("CameraGlobal>"+xMouseRotacionado+","+yMouseRotacionado);
        Vector<Desenho> todosDesenhosCriados = Desenho.getTodosDesenhosCriados();
        for(Desenho desenho : todosDesenhosCriados){
            //rotacionar no plano xy
            //xMouseRotacionado = (float) (hipotenusaMouseRotacionado*Math.cos(desenho.getRotacaoZ()));
            //yMouseRotacionado = (float) (hipotenusaMouseRotacionado*Math.cos(desenho.getRotacaoZ()));
            //rotacionar no plano xz
            //xMouseRotacionado = (float) (hipotenusaMouseRotacionado*Math.cos(desenho.getRotacaoY()));
            //rotacionar no plano yz
            //yMouseRotacionado = (float) (hipotenusaMouseRotacionado*Math.cos(desenho.getRotacaoX()));

            //System.out.println("("+xMouseRotacionado+","+yMouseRotacionado+")");
            //System.out.println("Comparando com ("+desenho.getGlobalX()+","+desenho.getGlobalY()+")");
            if(desenho.contem(xMouseRotacionado, yMouseRotacionado)){
                desenhoSelecionado = desenho;
                desenhoSelecionado.tornarTransparente();
                gui.getGuiRetrato().exibir(desenhoSelecionado);
                gui.getGuiEdicaoTiles().mostrar();
            }
        }
        Vector<Filme> todosFilmesCriados = Filme.getTodosFilmesCriados();
        for(Filme filme : todosFilmesCriados){
            if(filme.contem(mouseX, mouseY)){
                filmeSelecionado = filme;
                filmeSelecionado.tornarTransparente();
                gui.getGuiRetrato().exibir(filmeSelecionado);
                gui.getGuiEdicaoTiles().mostrar();
            }
        }
        Vector<TileEdificavel> todasTilesEdificaveisCriadas = TileEdificavel.getTodasTilesEdificaveisCriadas();
        for(TileEdificavel tileEdificavel : todasTilesEdificaveisCriadas){
            if(tileEdificavel.contem(mouseX, mouseY)){
                tileEdificavelSelecionada = tileEdificavel;
                tileEdificavelSelecionada.tornarTransparente();
                gui.getGuiRetrato().exibir(tileEdificavelSelecionada);
                gui.getGuiEdicaoTiles().mostrar();
            }
        }
    }

    /**
     * Desfaz a seleção de todos os objetos selecionados.
     */
    private void desfazerSelecoes(){
        if(desenhoSelecionado != null){
            desenhoSelecionado.restaurarTransparencia();
            desenhoSelecionado = null;
            gui.getGuiRetrato().retirarDesenhoExibido();
            gui.getGuiEdicaoTiles().esconder();
        }
        if(filmeSelecionado != null){
            filmeSelecionado.restaurarTransparencia();
            filmeSelecionado = null;
            gui.getGuiRetrato().retirarDesenhoExibido();
            gui.getGuiEdicaoTiles().esconder();
        }
        if(tileEdificavelSelecionada != null){
            tileEdificavelSelecionada.restaurarTransparencia();
            tileEdificavelSelecionada = null;
            gui.getGuiRetrato().retirarDesenhoExibido();
            gui.getGuiEdicaoTiles().esconder();
        }
    }

    @Override
    public void mouseMoveu() {
        float mouseX = Mouse.getX();
        float mouseY = Mouse.getY();
        
        if(gui.getGuiEdicaoTiles().getBtAdicionarTorreMadeira().contem(mouseX, mouseY)){
            gui.getGuiEdicaoTiles().getBtAdicionarTorreMadeira().pressionar();
        } else {
            gui.getGuiEdicaoTiles().getBtAdicionarTorreMadeira().soltar();
        }
        if(gui.getGuiEdicaoTiles().getBtAdicionarTorreCanhao().contem(mouseX, mouseY)){
            gui.getGuiEdicaoTiles().getBtAdicionarTorreCanhao().pressionar();
        } else {
            gui.getGuiEdicaoTiles().getBtAdicionarTorreCanhao().soltar();
        }
    }
}
