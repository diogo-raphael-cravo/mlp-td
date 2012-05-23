package mlptd;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



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

    /**
     * Guarda o momento, em milissegundos, do início do nível (criação do primeiro inimigo).
     */
    private long tempoInicioMilissegundos;

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
    public long getTempoInicioMilissegundos(){
        return tempoInicioMilissegundos;
    }
    public long getTempoDesdeInicioMilissegundos(){
        return System.nanoTime()/1000000 - tempoInicioMilissegundos;
    }

    /**
     * Faz com que nasça o próximo inimigo deste nível no terreno dado.
     * A razão de existência desta função é que esta classe contabiliza o número
     * de inimigos deste nível que já nasceram, garantindo que nunca nasçam mais
     * inimigos do que o nível suporta.
     * @param _terreno O terreno em que o inimigo nascerá.
     * @param _batalha A batalha em que o inimigo estará.
     */
    public void criarInimigo(Terreno _terreno, Batalha _batalha){
        if(inimigosNascidos == 0){
            tempoInicioMilissegundos = System.nanoTime()/1000000;
        }

        if(inimigosNascidos < numeroInimigos){
            Inimigo inimigoNovo = new Inimigo(modeloInimigo);
            _terreno.adicionarInimigo(inimigoNovo);
            _batalha.adicionarInimigo(inimigoNovo);
            inimigosNascidos++;
        }
    }
    
}
