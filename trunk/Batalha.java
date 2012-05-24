/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mlptd;

import java.util.Vector;

/**
 * Com um conjunto de torres, inimigos, projéteis e um terreno esta classe
 * é capaz de realizar o ataque de torres a inimigos, a movimentação 
 * de projéteis em direção a inimigos e a movimentação de inimigos em
 * seus caminhos.
 * @author diogo
 */
public class Batalha {
    /**
     * Inimigos que estão na batalha.
     */
     private Vector<Inimigo> inimigos;

    /**
     * Projéteis que já foram lançados e ainda não atingiram um alvo.
     * Projéteis não podem ser adicionados à batalha. A adição de projéteis
     * é responsabilidade da própria batalha, que o faz com base nas torres
     * que possui, sua configuração em relação aos inimigos e tempo de disparo
     * das torres.
     */
     private Vector<Projetil> projeteis;

    /**
     * Torres que podem atirar projéteis.
     */
     private Vector<Torre> torres;

    /**
     * O terreno em que dá-se a batalha.
     */
     private Terreno terreno;

     /**
      * Cria uma nova batalha, ainda sem inimigos, projéteis, torres
      * ou tiles.
      * @param _terreno O terreno em que acontece a batalha. Determinará
      *     as tiles onde ficarão os inimigos e seu caminho.
      */
     public Batalha(Terreno _terreno){
        inimigos = new Vector<Inimigo>();
        projeteis = new Vector<Projetil>();
        torres = new Vector<Torre>();
        terreno = _terreno;
     }

     /**
      * Adiciona um inimigo à batalha.
      * O inimigo irá seguir o caminho, dependendo da posição em que
      * ele se encontra e da sua velocidade. Projéteis podem ser atirados
      * contra ele de torres que estão nesta batalha.
      * @param _inimigo Inimigo que será adicionado. A cópia deve ser rasa.
      */
     public void adicionarInimigo(Inimigo _inimigo){
        inimigos.add(_inimigo);
     }

     /**
      * Adiciona uma torre à batalha.
      * Baseado na velocidade de disparo da torre, a batalha irá criar projé-
      * teis que saiam desta torre e destinem-se ao inimigo mais próximo den-
      * tro do alcance da torre.
      * @param _torre A torre que será adicionada. A cópia deve ser rasa.
      */
     public void adicionarTorre(Torre _torre){
        torres.add(_torre);
     }

     /**
      * Faz todas as atualizações necessárias para que a batalha
      * fique em dia com o tempo atual.
      */
     public void atualizar(){
         criarProjeteis();
         moverProjeteis();
         colidirProjeteisComInimigos();
         moverInimigos();
     }

     /**
      * Cria projéteis para esta batalha, com base nas velocidades
      * de disparo das torres e o tempo atual.
      */
     private void criarProjeteis(){
         for(Torre torreNaBatalha : torres){
            if(torreNaBatalha.estahProntaParaDisparar()){
                int inimigoAtual = 0;
                boolean encontrouAlvo = false;
                Inimigo inimigoAlvo = null;
                
                while(!encontrouAlvo && inimigoAtual<inimigos.size()){
                    if(torreNaBatalha.estahNoAlcance(inimigos.elementAt(inimigoAtual))){
                        encontrouAlvo = true;
                        inimigoAlvo = inimigos.elementAt(inimigoAtual);
                    }
                    inimigoAtual++;
                }

                if(encontrouAlvo){
                    Projetil projetilQueSerahDisparado = torreNaBatalha.disparar();
                    terreno.adicionarFilho(projetilQueSerahDisparado,
                            projetilQueSerahDisparado.getPosX(),
                            projetilQueSerahDisparado.getPosY());
                    projetilQueSerahDisparado.setPerseguido(inimigoAlvo);
                    projeteis.add(projetilQueSerahDisparado);
                }
            }
         }
     }

     /**
      * Movimenta os inimigos.
      * Os inimigos seguem o caminho em direção à tile considerada em
      * que ele está. A tile que é considerada como a tile em que o
      * inimigo está é sempre aquela que está mais próxima dele.
      * Quando um inimigo atinge o centro de uma tile, ele passa a
      * ir em direção à tile vizinha daquela no caminho que está
      * gravado no terreno da batalha.
      * A quantidade de movimento a cada chamada depende de quanto tempo
      * passou-se desde a última chamada à função e da velocidade do
      * inimigo.
      * Quando um inimigo atinge o fim de um terreno, ele sai da batalha.
      * Decide qual o melhor caminho a utilizar para cada inimigo.
      * Na prática, o melhor é o menor caminho que o inimigo pode usar.
      */
     private void moverInimigos(){
         Tile tileInimigo;
         Tile tileVizinhaTileInimigo;
         Vector<Inimigo> inimigosQueSairamDoTerreno = new Vector<Inimigo>();
         Vector<Inimigo> inimigosNoTerreno = terreno.getInimigos();
         Vector<TilePassadouro> caminhoDeTiles = terreno.getTilesCaminho();

         for(Inimigo inimigoNoTerreno : inimigosNoTerreno){
            mover(inimigoNoTerreno);

            tileInimigo = terreno.getTileComPosicao(inimigoNoTerreno.getGlobalX(), inimigoNoTerreno.getGlobalY());
            tileVizinhaTileInimigo = terreno.getTileVizinha(tileInimigo, terreno.getCaminho());
            if(tileVizinhaTileInimigo == null){
                inimigosQueSairamDoTerreno.add(inimigoNoTerreno);
            }
         }

         for(Inimigo inimigoQueSaiuDoTerreno : inimigosQueSairamDoTerreno){
             terreno.removerFilho(inimigoQueSaiuDoTerreno);
         }
     }

     /**
      * Move o inimigo sobre este terreno.
      * Note que ele não precisa pertencer ao terreno, apesar de isto ser altamente recomendado.
      * O algoritmo é o seguinte:
      *   1) Identificar tile em que o inimigo está, baseado somente em sua posição
      *   2) Identificar tile vizinha (no caminho) da encontrada.
      *   3) Calcular distância em x e em y entre a posição do inimigo e a posição da tile vizinha encontrada.
      *   4) Escalar a variação pelo tempo passado entre o último movimento e este, multiplicado
      *      pela velocidade em pixels/segundo do inimigo. Se o movimento for em mais de uma direção,
      *      calcular de tal forma que a norma do vetor movimento seja a velocidade em pixels/segundo
      *      do inimigo.
      *   5) Movimentar o inimigo.
      *   6) Se o inimigo já atingiu a próxima tile, adicioná-lo a ela.
      * @param _inimigo Inimigo que será movido sobre o terreno.
      */
     private void mover(Inimigo _inimigo){
        Inimigo inimigoNaoMovido = _inimigo;
        TilePassadouro tileInimigo = (TilePassadouro) terreno.getTileComPosicao(inimigoNaoMovido.getGlobalX(), inimigoNaoMovido.getGlobalY());
        TilePassadouro tileVizinhaTileInimigo = null;
        float tempoPassadoDesdeUltimoMovimentoEmSegundos = Temporizador.diferencaUltimasDuasMarcacoesPrincipal()/((float) 1000.0);
        float velocidadeInimigoEmPixelsPorSegundo = terreno.getComprimentoCadaTile()*
                                                    inimigoNaoMovido.getVelocidadeTilesPorSegundo();
        //Vetor direção final, que o inimigo deseja obter após passado 1 segundo.
        float xDestinoInimigo = 0;
        float yDestinoInimigo = 0;

        //Vetor variação da posição do inimigo, a ponderação do destino (anterior) pelo tempo passado.
        float xVariacaoInimigo = 0;
        float yVariacaoInimigo = 0;

        //Vetor direção do movimento que será feito.
        int direcaoX = 1;
        int direcaoY = 1;

        if(tileInimigo != null){
            tileVizinhaTileInimigo = (TilePassadouro) terreno.getTileVizinha(tileInimigo, _inimigo.getCaminhoQueSegue());
        }

        if(tileVizinhaTileInimigo != null){
            xDestinoInimigo = tileVizinhaTileInimigo.xGlobalCentroParaDesenho(inimigoNaoMovido);
            yDestinoInimigo = tileVizinhaTileInimigo.yGlobalCentroParaDesenho(inimigoNaoMovido);

            if(xDestinoInimigo <= inimigoNaoMovido.getGlobalX()){
                direcaoX = -1;
            } else {
                direcaoX = 1;
            }

            if(yDestinoInimigo <= inimigoNaoMovido.getGlobalY()){
                direcaoY = 1;
            } else {
                direcaoY = -1;
            }

            if(inimigoNaoMovido.getGlobalX() == xDestinoInimigo){
                xVariacaoInimigo = 0;
                if(inimigoNaoMovido.getGlobalY() != yDestinoInimigo){
                    yVariacaoInimigo = - velocidadeInimigoEmPixelsPorSegundo;
                }
            } else if(inimigoNaoMovido.getGlobalY() == yDestinoInimigo){
                xVariacaoInimigo = velocidadeInimigoEmPixelsPorSegundo;
                yVariacaoInimigo = 0;
            } else {
                xVariacaoInimigo = (float) Math.sqrt(Math.pow((double) velocidadeInimigoEmPixelsPorSegundo, 2))/2;
                yVariacaoInimigo = - (float) Math.sqrt(Math.pow((double) velocidadeInimigoEmPixelsPorSegundo, 2))/2;
            }
            xVariacaoInimigo *= tempoPassadoDesdeUltimoMovimentoEmSegundos;
            yVariacaoInimigo *= tempoPassadoDesdeUltimoMovimentoEmSegundos;
            xVariacaoInimigo *= direcaoX;
            yVariacaoInimigo *= direcaoY;
            inimigoNaoMovido.deslocar(xVariacaoInimigo, yVariacaoInimigo);
            if(tileVizinhaTileInimigo.contem(inimigoNaoMovido)){
                tileVizinhaTileInimigo.adicionarInimigo(inimigoNaoMovido);
            }
         }
     }

     /**
      * Movimenta os projéteis na batalha. 
      * Só são movimentados os projéteis que já estão na batalha.
      * Um projétil é sempre movimentado em direção ao inimigo que persegue.
      * Todos projéteis possuem a mesma velocidade, que é bem maior do que
      * a dos inimigos. Desta forma, é garantido que TODO projétil lançado
      * atingirá seu inimigo alvo, à exceção de raros casos em que o inimigo
      * consegue sair do terreno antes de ser atingido.
      * Os projéteis sempre se movem seguindo os inimigos, não em linha reta.
      */
     private void moverProjeteis(){
         for(Projetil projetil : projeteis){
            mover(projetil);
         }
     }

     /**
      * Move um projétil em direção ao inimigo que persegue.
      * O algoritmo é o seguinte:
      *   1) Calcular distância em x e em y entre a posição do projétil e a posição do inimigo que ele persegue.
      *   2) Escalar a variação pelo tempo passado entre o último movimento e este, multiplicado
      *      pela velocidade em pixels/segundo do projétil. Se o movimento for em mais de uma direção,
      *      calcular de tal forma que a norma do vetor movimento seja a velocidade em pixels/segundo
      *      do projétil.
      *   3) Movimentar o projétil.
      * @param _projetil Projétil que será movido em direção a um inimigo (especificado no próprio projétil).
      */
     private void mover(Projetil _projetil){
         float tempoPassadoDesdeUltimoMovimentoEmSegundos = Temporizador.diferencaUltimasDuasMarcacoesPrincipal()/((float) 1000.0);
         float velocidadeEmPixelsPorSegundo = terreno.getComprimentoCadaTile()*
                                                    _projetil.getVelocidadeTilesPorSegundo();

         //Vetor direção final, que deseja-se obter após passado 1 segundo.
         float xDestino = _projetil.getPerseguido().getGlobalX();
         float yDestino = _projetil.getPerseguido().getGlobalY();

         //Vetor variação da posição, a ponderação do destino (anterior) pelo tempo passado.
         float xVariacao = 0;
         float yVariacao = 0;

         //Vetor direção do movimento que será feito.
         int direcaoX = 1;
         int direcaoY = 1;

         if(xDestino <= _projetil.getGlobalX()){
             direcaoX = -1;
         } else {
             direcaoX = 1;
         }

         if(yDestino <= _projetil.getGlobalY()){
             direcaoY = 1;
         } else {
             direcaoY = -1;
         }

         if(_projetil.getGlobalX() == xDestino){
             xVariacao = 0;
             if(_projetil.getGlobalY() != yDestino){
                yVariacao = - velocidadeEmPixelsPorSegundo;
             }
         } else if(_projetil.getGlobalY() == yDestino){
             xVariacao = velocidadeEmPixelsPorSegundo;
             yVariacao = 0;
         } else {
             xVariacao = (float) Math.sqrt(Math.pow((double) velocidadeEmPixelsPorSegundo, 2))/2;
             yVariacao = - (float) Math.sqrt(Math.pow((double) velocidadeEmPixelsPorSegundo, 2))/2;
         }
         xVariacao *= tempoPassadoDesdeUltimoMovimentoEmSegundos;
         yVariacao *= tempoPassadoDesdeUltimoMovimentoEmSegundos;
         xVariacao *= direcaoX;
         yVariacao *= direcaoY;
         _projetil.mudarDirecao(_projetil.getPerseguido().getPosX()-_projetil.getPosX(),
                                _projetil.getPerseguido().getPosY()-_projetil.getPosY());
         _projetil.deslocar(xVariacao, yVariacao);
     }

     /**
      * Dada a configuração de inimigos e projéteis, define se algum projétil
      * já atingiu algum inimigo.
      * Quando um projétil atinge um inimigo, o projétil é destruído e o inimigo
      * é ferido.
      */
     private void colidirProjeteisComInimigos(){
         
     }


     
         
}
