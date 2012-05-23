package mlptd;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



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
     * Controlador do terreno que esta gui possibilita editar.
     */
     private ControladorTerreno controladorTerrenoEditado;

    /**
     * @param _gui A gui controlada por este controlador.
     */
    public ControladorGuiBarraInferior(Gui_BarraInferior _gui){
        gui = _gui;
        controladorTerrenoEditado = null;
    }

    /**
     * @param _controladorTerreno Controlador do terreno que é editado por esta gui.
     */
    public void definirControladorTerreno(ControladorTerreno _controladorTerreno){
        controladorTerrenoEditado = _controladorTerreno;
    }

    public void houveMouseDown() {
        float mouseX = Mouse.getX();
        float mouseY = Mouse.getY();

        if(controladorTerrenoEditado != null){
            if(gui.contem(mouseX, mouseY)){
                if(gui.getGuiEdicaoTiles().getBtAdicionarTorreMadeira().contem(mouseX, mouseY)
                        && controladorTerrenoEditado.tileEdificavelSelecionadaEstahLivre()){
                    controladorTerrenoEditado.construirTorreNaTileSelecionada(Torre.TIPO_TORRE.MADEIRA);
                    gui.getGuiRetrato().exibir(controladorTerrenoEditado.getCopiaTileEdificavelSelecionada());
                    gui.getGuiEdicaoTiles().mostrar();
                } else if(gui.getGuiEdicaoTiles().getBtAdicionarTorreCanhao().contem(mouseX, mouseY)
                        && controladorTerrenoEditado.tileEdificavelSelecionadaEstahLivre()){
                    controladorTerrenoEditado.construirTorreNaTileSelecionada(Torre.TIPO_TORRE.CANHAO);
                    gui.getGuiRetrato().exibir(controladorTerrenoEditado.getCopiaTileEdificavelSelecionada());
                    gui.getGuiEdicaoTiles().mostrar();
                } else if(gui.getGuiEdicaoTiles().getBtRemoverTorre().contem(mouseX, mouseY)
                        && controladorTerrenoEditado.haTileEdificavelSelecionada()){
                    controladorTerrenoEditado.destruirTorreTileSelecionada();
                    gui.getGuiRetrato().exibir(controladorTerrenoEditado.getCopiaTileEdificavelSelecionada());
                    gui.getGuiEdicaoTiles().mostrar();
                } else {
                    controladorTerrenoEditado.desfazerSelecoes();
                }
            } else {
                controladorTerrenoEditado.desfazerSelecoes();
                controladorTerrenoEditado.tentarSelecaoDesenho();
            }
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
        if(gui.getGuiEdicaoTiles().getBtRemoverTorre().contem(mouseX, mouseY)){
            gui.getGuiEdicaoTiles().getBtRemoverTorre().pressionar();
        } else {
            gui.getGuiEdicaoTiles().getBtRemoverTorre().soltar();
        }        
    }
}
