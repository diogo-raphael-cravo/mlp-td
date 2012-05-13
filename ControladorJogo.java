package mlptd;

import java.util.Vector;
import org.lwjgl.input.Mouse;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Controla eventos que pertençam a um jogo.
 * @author drcravo
 */
public class ControladorJogo
    implements MouseListener {

    /**
     * O jogo do controlador.
     */
     Jogo jogo;

    /**
     * O desenho que foi selecionado pelo mouse.
     */
     Desenho desenhoSelecionado;
     Filme filmeSelecionado;

    public ControladorJogo(Jogo _jogo){
        jogo = _jogo;
        desenhoSelecionado = null;
    }

    public void houveMouseDown() {
        boolean selecionouDesenho = false;
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
        System.out.println("Global>"+mouseX+","+mouseY);
        float xMouseRotacionado = mouseX;//Camera.xGlobalParaCamera(mouseX, mouseY);
        float yMouseRotacionado = mouseY+200;//-constante*angulo//Camera.yGlobalParaCamera(mouseX, mouseY);
        System.out.println("CameraGlobal>"+xMouseRotacionado+","+yMouseRotacionado);
        //System.out.println("("+mouseX+","+mouseY+")");
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
            System.out.println("Comparando com ("+desenho.getGlobalX()+","+desenho.getGlobalY()+")");
            if(desenho.contem(xMouseRotacionado, yMouseRotacionado)){
                if(desenhoSelecionado != null){
                    desenhoSelecionado.restaurarTransparencia();
                } else if(filmeSelecionado != null){
                    filmeSelecionado.restaurarTransparencia();
                    filmeSelecionado = null;
                }
                desenhoSelecionado = desenho;
                desenhoSelecionado.tornarTransparente();
                selecionouDesenho = true;
                if(desenho instanceof Filme){
                    Tela.getGuiBarraInferior().getGuiRetrato().exibir(desenhoSelecionado);
                } else {
                    Tela.getGuiBarraInferior().getGuiRetrato().exibir(desenhoSelecionado);
                }
            }
        }
        Vector<Filme> todosFilmesCriados = Filme.getTodosFilmesCriados();
        for(Filme filme : todosFilmesCriados){
            if(filme.contem(mouseX, mouseY)){
                if(filmeSelecionado != null){
                    filmeSelecionado.restaurarTransparencia();
                } else if(desenhoSelecionado != null){
                    desenhoSelecionado.restaurarTransparencia();
                    desenhoSelecionado = null;
                }
                filmeSelecionado = filme;
                filmeSelecionado.tornarTransparente();
                selecionouDesenho = true;
                Tela.getGuiBarraInferior().getGuiRetrato().exibir(filmeSelecionado);
            }
        }
        if(!selecionouDesenho && desenhoSelecionado != null){
            desenhoSelecionado.restaurarTransparencia();
            desenhoSelecionado = null;
            Tela.getTela().getGuiBarraInferior().getGuiRetrato().retirarDesenhoExibido();
        }
        if(!selecionouDesenho && filmeSelecionado != null){
            filmeSelecionado.restaurarTransparencia();
            filmeSelecionado = null;
            Tela.getTela().getGuiBarraInferior().getGuiRetrato().retirarDesenhoExibido();
        }
    }
}
