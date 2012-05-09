/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mlptd;

/**
 * Classe que contém todos os elementos de interface, os quais precisam manter-se na tela o tempo todo.
 * Esta classe não é instanciável e possui somente funções estáticas.
 * @author drcravo
 */
public class Tela extends Desenho {
    public static int WIDTH = 640;
    public static int HEIGHT = 480;

    /**
     * A única tela existente, que contém todos elementos de interface.
     */
    private static Tela tela;

    /**
     * Barra de informações que fica na base da tela.
     */
    private Gui_BarraInferior barraInformacoesInferior;


    /**
     * Faz as inicializações necessárias para a tela.
     */
    public static void inicializar(){
        tela = new Tela();
        tela.inicializarInstancia();
    }

    /**
     * Inicializações privadas de cada elemento da classe.
     */
    private void inicializarInstancia(){
       barraInformacoesInferior = new Gui_BarraInferior();
    }

    private Tela(){
        super(0, 0, 0, 0, 100);
    }

    /**
     * @return A única tela existente. Retorna-se a própria, não uma cópia.
     */
    public static Tela getTela(){
        return tela;
    }

    /**
     * @param _xTela Uma coordenada X em coordenadas de tela.
     * @return Coordenada X passada em coordenada globais.
     */
     public static float xTelaParaGlobal(float _xTela){
         return tela.getPosX() + _xTela;
     }

    /**
     * @param _yTela Uma coordenada Y em coordenadas de tela.
     * @return Coordenada Y passada em coordenada globais.
     */
     public static float yTelaParaGlobal(float _yTela){
         return tela.getPosY() + _yTela;
     }

    /**
     * O movimento é feito de uma só vez.
     * @param _destino Ponto para onde o desenho deve ir.
     */
    @Override
     public void deslocar(float _deslocamentoX, float _deslocamentoY){
        super.deslocar(_deslocamentoX, _deslocamentoY);
        barraInformacoesInferior.deslocar(_deslocamentoX, _deslocamentoY);
     }

     /**
     * Desenha a todos os elementos de interface gráfica da tela.
     */
     @Override
     public void desenhar(){
        super.desenhar();
        barraInformacoesInferior.desenhar();
     }
}
