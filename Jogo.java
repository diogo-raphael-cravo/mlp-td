package mlptd;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 * Representa um jogo de mlptd.
 * Todo jogo é composto por muitos níveis, todos em um mesmo terreno.
 * @author diogo
 */
public class Jogo {
    /**
     * Tempo em milissegundos entre o nascimento de dois inimigos.
     * Como cada tile passadouro só pode ter um inimigo, este tempo precisa
     * ser suficientemente grande para que dois inimigos nunca se encontrem em
     * uma mesma tile, o que daria erro.
     */
    private static float INTERVALO_NASCIMENTO_INIMIGOS_MILISSEGUNDOS = 1500;

    /**
     * O único terreno utilizado neste jogo.
     */
    private Terreno terreno;

    /**
     * A batalha que está acontecendo neste jogo.
     */
    private Batalha batalha;

    /**
     * Array com todos níveis deste jogo.
     * Os níveis começam no índice 0.
     * Desta forma, niveis[0] contém os dados do primeiro nível.
     */
    private Nivel[] niveis;

    /**
     * Indica o índice em niveis[] do nível que está acontecendo.
     */
    private int nivelAtual;

    /**
     * Marca os nascimentos.
     */
    private Temporizador temporizadorNascimentos;

    public Jogo(Terreno _terreno, Nivel[] _niveis){
        batalha = new Batalha(_terreno);
        terreno = _terreno;
        terreno.definirControlador(new ControladorTerreno(terreno, batalha));
        niveis = _niveis;
        nivelAtual = -1;
        temporizadorNascimentos = new Temporizador();
        iniciarProximoNivel();
    }

    /**
     * Inicia o próximo nível, mandando os inimigos andarem.
     */
    public void iniciarProximoNivel(){
        nivelAtual++;
        boolean alcancouFimNiveis = (nivelAtual == niveis.length);
        if(!alcancouFimNiveis){
            temporizadorNascimentos = new Temporizador();
            niveis[nivelAtual].criarInimigo(terreno, batalha);
            temporizadorNascimentos.marcarAgora();
        }
    }

    /**
     * Atualiza o jogo, adicionando inimigos quando necessário.
     */
    public void atualizar(){
        if(INTERVALO_NASCIMENTO_INIMIGOS_MILISSEGUNDOS
                <= temporizadorNascimentos.tempoDesdeUltimaMarcacao()
           && niveis[nivelAtual].getNumeroInimigosNascidos() < niveis[nivelAtual].getNumeroInimigos()){
            niveis[nivelAtual].criarInimigo(terreno, batalha);
            temporizadorNascimentos.marcarAgora();
        }
        Tela.getGuiBarraSuperior().setQuantidadeOuro(nivelAtual);
        Tela.getGuiBarraSuperior().setNivelAtual(nivelAtual+1);
        Tela.getGuiBarraSuperior().setTotalNiveis(niveis.length);
        Tela.getGuiBarraSuperior().setQuantidadeVidas(nivelAtual);
        Tela.getGuiBarraSuperior().setTempo((int) (niveis[nivelAtual].getTempoDesdeInicioMilissegundos()/1000));
        batalha.atualizar();
    }

    /**
     * Desenha o jogo.
     */
    public void desenhar(){
        terreno.desenhar();
    }

    /**
     * @return O terreno utilizado no jogo.
     */
    public Terreno getTerreno(){
        return terreno;
    }
}
