/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mlp.td;

/**
 * Indica dados de um nível.
 * Cada nível possui apenas um tipo de inimigo, mas muitos deles.
 * @author diogo
 */
public class Nivel {
    /**
     * O número de inimigos que aparecem no nível.
     */
    private int numeroInimigos;

    /**
     * Inimigo que serve de modelo para criação dos inimigos deste nível.
     */
    private Inimigo modeloInimigo;

    /**
     * O número de inimigos deste nível que já nasceram.
     */
    private int inimigosNascidos;

    public Nivel(Inimigo _modeloInimigo, int _numeroInimigos){
        modeloInimigo = new Inimigo(_modeloInimigo);
        numeroInimigos = _numeroInimigos;
        inimigosNascidos = 0;
    }

    public Inimigo getModeloInimigo(){
        return new Inimigo(modeloInimigo);
    }
    public int getNumeroInimigos(){
        return numeroInimigos;
    }
    public int getNumeroInimigosNascidos(){
        return inimigosNascidos;
    }

    /**
     * Faz com que nasça o próximo inimigo deste nível no terreno dado.
     * @param _terreno O terreno em que o inimigo nascerá.
     */
    public void criarInimigo(Terreno _terreno){
        if(inimigosNascidos < numeroInimigos){
            _terreno.adicionarInimigo(modeloInimigo);
            inimigosNascidos++;
        }
    }
    
}
