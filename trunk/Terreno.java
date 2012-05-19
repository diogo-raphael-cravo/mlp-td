/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mlp.td;

import java.util.Vector;
import org.lwjgl.util.Color;

/**
 * Um terreno contém um conjunto de tiles, um caminho e um conjunto de inimigos.
 * @author diogo
 */
public class Terreno extends Desenho{
    /**
     * Array de tiles do terreno.
     * A posição da tile no array deve refletir sua posição no terreno.
     * Assim, tiles[0][0] é a tile mais acima e à esquerda.
     */
    Tile[][] tiles;

    /**
     * Caminho composto por índices de tiles em tiles[][].
     * O caminho mostra um lugar por onde os inimigos podem movimentar-se.
     * Apesar de caminhos isolados possuirem somente um início e um fim, sem bifurcações,
     * o terreno pode ter vários caminhos, o que cria bifurcações no terreno, mas não
     * nos caminhos isoladamente.
     */
    Caminho caminho;

    /**
     * O tamanho que cada tile deve ter.
     */
    float comprimentoCadaTile;
    float larguraCadaTile;

    /**
     * Dimensões do terreno em tiles.
     */
    int tilesPorLinha;
    int tilesPorColuna;

    /**
     * Os inimigos que estão no terreno.
     */
    Vector<Inimigo> inimigosNoTerreno;
    Vector<Inimigo> inimigosQueSairam;

    /**
     * Angulo de rotacao dos inimigos neste terreno.
     */
    private float xRotacaoInimigos;
    private float yRotacaoInimigos;
    private float zRotacaoInimigos;

    /**
     * Ângulo de rotação das torres nas tiles edificáveis deste terreno.
     */
    private float yRotacaoTorres;

    /**
     * O terreno é criado com um número especificado de tiles de forma que ocupe as dimensões que lhe são especificadas.
     * @param _posicao Posição do terreno.
     * @param _dimensoesTela Comprimento e largura do terreno em unidades de tela.
     * @param _dimensoesTiles Comprimento e largura do terreno em tiles.
     */
     public Terreno(float _posX, float _posY, float _comprimentoTela, float _larguraTela,
                    int _comprimentoEmTiles, int _larguraEmTiles){
        super(_posX, _posY, _comprimentoTela, _larguraTela, 100);
         
        comprimentoCadaTile = _comprimentoTela/_comprimentoEmTiles;
        larguraCadaTile = _larguraTela/_larguraEmTiles;
        
        tilesPorLinha = _comprimentoEmTiles;
        tilesPorColuna = _larguraEmTiles;
		
        tiles = new Tile[tilesPorLinha][tilesPorColuna];

        criarMatrizTilesEdificaveis();

        //caminho = Caminho.criarCaminhoDiagonalDecrescente(tilesPorColuna, tilesPorLinha, tilesPorColuna);
        caminho = Caminho.criarCaminhoLinhasAlternadas(tilesPorLinha, tilesPorColuna);
        adicionarTilesPassadouroSegundoCaminho(caminho);

        inimigosNoTerreno = new Vector<Inimigo>();
        inimigosQueSairam = new Vector<Inimigo>();

        xRotacaoInimigos = 90;
        yRotacaoInimigos = 0;
        zRotacaoInimigos = 0;

        yRotacaoTorres = 0;

        /**
         * Criação do plano de fundo.
         */
        Desenho fundo = new Desenho(0, 0, comprimentoCadaTile, larguraCadaTile);
        fundo.redimensionar(comprimento, largura, altura);
        fundo.definirTextura(Texturas.GRAMA);
        adicionarFilho(new Desenho(fundo), posX-comprimento/2, posY-largura/2);
        adicionarFilho(new Desenho(fundo), posX+comprimento/2, posY-largura/2);
        adicionarFilho(new Desenho(fundo), posX+3*comprimento/2, posY-largura/2);
        adicionarFilho(new Desenho(fundo), posX-comprimento/2, posY+largura/2);
        adicionarFilho(new Desenho(fundo), posX-comprimento/2, posY+3*largura/2);
        adicionarFilho(new Desenho(fundo), posX+comprimento/2, posY+3*largura/2);
        adicionarFilho(new Desenho(fundo), posX+3*comprimento/2, posY+3*largura/2);
        adicionarFilho(new Desenho(fundo), posX+3*comprimento/2, posY+largura/2);
     }

     /**
      * Adiciona um inimigo a este terreno, que correrá imediatamente
      * à partir da primeira tile do caminho.
      * @param _inimigo Modelo de inimigo a ser adicionado.
      */
     public void adicionarInimigo(Inimigo _inimigoModelo){
          TilePassadouro primeiraTileCaminho = (TilePassadouro) tiles[caminho.getColunaTile(0)][caminho.getLinhaTile(0)];
          Inimigo inimigoNovo = new Inimigo(_inimigoModelo);
          inimigoNovo.mover(getPosX(), getPosY());
          primeiraTileCaminho.adicionarInimigo(inimigoNovo);
          inimigosNoTerreno.add(inimigoNovo);
          inimigoNovo.inicializarEventos();
          inimigoNovo.rotacionar(xRotacaoInimigos, -yRotacaoInimigos, zRotacaoInimigos);
          adicionarFilho(inimigoNovo, primeiraTileCaminho.getPosX(), primeiraTileCaminho.getPosY());
     }

     /**
      * @return Booleano indicando se todos os inimigos já morreram ou já passaram pelo terreno.
      */
     public boolean todosInimigosMorreramOuSairam(){
         if(inimigosQueSairam.size() == inimigosNoTerreno.size()){
             return true;
         } else {
             return false;
         }
     }

     /**
      * Move todos os inimigos que estão neste terreno.
      * Decide qual o melhor caminho a utilizar para cada inimigo.
      * Na prática, o melhor é o menor caminho que o inimigo pode usar.
      */
     public void moverInimigos(){
         Tile tileInimigo;
         Tile tileVizinhaTileInimigo;
         Vector<Long> apagados = new Vector<Long>();

         for(Inimigo inimigoNoTerreno : inimigosNoTerreno){
            moverInimigo(inimigoNoTerreno, caminho);

            tileInimigo = getTileComPosicao(inimigoNoTerreno.getGlobalX(), inimigoNoTerreno.getGlobalY());
            tileVizinhaTileInimigo = getTileVizinha(tileInimigo, caminho);
            if(tileVizinhaTileInimigo == null){
                apagados.add(inimigoNoTerreno.getIdentificacaoUnica());
            }
         }

         for(Long identificacaoApagado : apagados){
             for(int i=0; i<inimigosNoTerreno.size(); i++){
                if(inimigosNoTerreno.get(i).getIdentificacaoUnica() == identificacaoApagado){
                    inimigosQueSairam.add(inimigosNoTerreno.get(i));
                }
             }
             for(int i=0; i<filhos.size(); i++){
                if(filhos.get(i).getIdentificacaoUnica() == identificacaoApagado){
                    filhos.remove(i);
                }
             }
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
      * @param _caminho Caminho a ser utilizado para encontrar a vizinha.
      */
     private void moverInimigo(Inimigo _inimigo, Caminho _caminho){
        Inimigo inimigoNaoMovido = _inimigo;
        Tile exemploTileTerreno = tiles[0][0];
        TilePassadouro tileInimigo = (TilePassadouro) getTileComPosicao(inimigoNaoMovido.getGlobalX(), inimigoNaoMovido.getGlobalY());
        TilePassadouro tileVizinhaTileInimigo = null;
        float tempoPassadoDesdeUltimoMovimentoEmSegundos = Temporizador.diferencaUltimasDuasMarcacoesPrincipal()/((float) 1000.0);
        float velocidadeInimigoEmPixelsPorSegundo = exemploTileTerreno.getComprimento()*
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
            tileVizinhaTileInimigo = (TilePassadouro) getTileVizinha(tileInimigo, _caminho);
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
      * @param _posX, _posY A posição a ser testada.
      * @return A tile deste terreno que contenha a posição de parâmetro.
      *         Se não houver, retornará null.
      */
     private Tile getTileComPosicao(float _posX, float _posY){
         Tile tileQueContemPosicao = null;
         boolean encontrouTile = false;
         int linha=0;
         int coluna=0;
         while(!encontrouTile && linha<tilesPorColuna){
            coluna = 0;
            while(!encontrouTile && coluna<tilesPorLinha){
                if(tiles[coluna][linha].contem(_posX, _posY)){
                    tileQueContemPosicao = tiles[coluna][linha];
                    encontrouTile = true;
                }
                coluna++;
            }
            linha++;
         }
         return tileQueContemPosicao;
     }

     /**
      * @param _tile Tile a ser procurada.
      * @param _caminho Caminho que indica as tiles a serem testadas.
      * @return A posição no caminho de parâmetro em que está a tile de parâmetro.
      *         Se a tile não estiver no caminho, retorna -1.
      */
     private int getPosicaoTileCaminho(Tile _tile, Caminho _caminho){
         Tile tileTestada;
         boolean encontrada_posicaoTileParametroNoCaminho = false;
         int posicaoTestada = 0;
         int posicaoTileCaminho = -1;
         int colunaTileCaminho;
         int linhaTileCaminho;
         while(!encontrada_posicaoTileParametroNoCaminho && posicaoTestada < caminho.getComprimento()){
            colunaTileCaminho = caminho.getColunaTile(posicaoTestada);
            linhaTileCaminho = caminho.getLinhaTile(posicaoTestada);
            tileTestada = tiles[colunaTileCaminho][linhaTileCaminho];
            encontrada_posicaoTileParametroNoCaminho = tileTestada.contem(_tile.getGlobalX()+1, _tile.getGlobalY()+1);
            if(encontrada_posicaoTileParametroNoCaminho && posicaoTestada < caminho.getComprimento()-1){
                encontrada_posicaoTileParametroNoCaminho = true;
                posicaoTileCaminho = posicaoTestada;
            }
            posicaoTestada++;
         }
         return posicaoTileCaminho;
     }

     /**
      * Dada uma tile e um caminho, retorna a tile que segue
      * a tile dada no caminho dado.
      * @param _tile A tile cuja vizinha será procurada.
      * @param _caminho O caminho que definirá quem é a vizinha.
      * @return A tile vizinha ou null, caso não haja tile vizinha.
      */
     private Tile getTileVizinha(Tile _tile, Caminho _caminho){
         Tile tileVizinha = null;
         Tile tileTestada;
         int posicaoTileVizinha = getPosicaoTileCaminho(_tile, _caminho) + 1;
         int coluna;
         int linha;
         if(posicaoTileVizinha < caminho.getComprimento() && posicaoTileVizinha != 0){
             coluna = caminho.getColunaTile(posicaoTileVizinha);
             linha = caminho.getLinhaTile(posicaoTileVizinha);
             tileVizinha = tiles[coluna][linha];
         }
         return tileVizinha;
     }

     /**
      * Aplica o caminho a este terreno, isto é, transforma as tiles que estão no caminho em TilePassadouro.
      * Sobrescreve as tiles passadouro que estiverem no caminho.
      * @param _caminho O caminho a ser aplicado.
      */
     private void adicionarTilesPassadouroSegundoCaminho(Caminho _caminho){
        int posicaoAtual;
        int linha;
        int coluna;
        Tile tileSubstituida;
        for(posicaoAtual = 0; posicaoAtual<_caminho.getComprimento(); posicaoAtual++){
            coluna = _caminho.getColunaTile(posicaoAtual);
            linha = _caminho.getLinhaTile(posicaoAtual);
            tileSubstituida = tiles[coluna][linha];
            tiles[coluna][linha] = new TilePassadouro(tileSubstituida);
            adicionarFilho(tiles[coluna][linha], tiles[coluna][linha].getPosX(), tiles[coluna][linha].getPosY());
            removerFilho(tileSubstituida);
            tileSubstituida.destruir();
        }
        atualizarTilesEdificaveis(); //Para que sejam desenhadas corretamente.
     }

     /**
      * Cria as tiles de forma que o terreno tenha seu tamanho certo.
      * Todas as tiles criadas são tiles edificáveis.
      */
     private void criarMatrizTilesEdificaveis(){
        int linha;
        int coluna;
        Color corCasaAtual = new Color(Color.WHITE);
        for(coluna=0; coluna<tilesPorLinha; coluna++){
            for(linha=0; linha<tilesPorColuna; linha++){
                adicionarTileEdificavel(coluna, linha, corCasaAtual);
            }
        }
     }
     
     /**
      * Adiciona uma tile ao terreno. Notar que as tiles têm seu tamanho limitado dentro do terreno,
      * isto é, todas possuem o mesmo tamanho que é definido na construção do terreno.
      * Caso a posição da tile esteja fora dos limites, não fará nada.
      * @param _posicao Posição da tile na matriz de tiles (e no terreno).
      * @param _cor Cor da tile.
      */
     private void adicionarTileEdificavel(int _colunaTile, int _linhaTile, Color _cor){
         float xTile;
         float yTile;
         TileEdificavel tileQueDeveSerAdicionada;
         boolean tileEstahForaDosLimites = (tilesPorLinha <= _colunaTile || tilesPorColuna <= _linhaTile);
         if(!tileEstahForaDosLimites){
            xTile = _colunaTile*comprimentoCadaTile;
            yTile = _linhaTile*larguraCadaTile;
            
            tileQueDeveSerAdicionada = new TileEdificavel(0.0f, 0.0f, comprimentoCadaTile, larguraCadaTile);
            tileQueDeveSerAdicionada.mudarCor(_cor);
            tiles[_colunaTile][_linhaTile] = tileQueDeveSerAdicionada;
            adicionarFilho(tiles[_colunaTile][_linhaTile], xTile, yTile);
         }
         //Apenas as tiles que não são passadouro têm eventos de mouse.
         tiles[_colunaTile][_linhaTile].inicializarEventos();
     }

     /**
      * Rotaciona todos os inimigos deste terreno e somente eles.
      * Só é possível rotacioná-los em torno do eixo y.
      * @param _rotacaoY Valor da rotação.
      */
     public void rotacionarInimigosEmY(float _rotacaoY){
         yRotacaoInimigos += _rotacaoY;
         for(Inimigo inimigoNoTerreno : inimigosNoTerreno){
             inimigoNoTerreno.rotacionar(0, -_rotacaoY, 0);
         }
     }

     /**
      * Rotaciona todas as torres deste terreno e somente elas.
      * Só é possível rotacioná-las em torno do eixo y.
      * @param _rotacaoY Valor da rotação.
      */
     public void rotacionarTorresEmY(float _rotacaoY){
         Vector<Torre> torresTerreno = getTorres();
         float yRotacaoDestaTorre;
         for(Torre torreNoTerreno : torresTerreno){
             if(torreNoTerreno.getRotacaoY() == -yRotacaoTorres){
                 yRotacaoDestaTorre = -_rotacaoY;
             } else {
                yRotacaoDestaTorre = -yRotacaoTorres - _rotacaoY;
             }
             torreNoTerreno.rotacionar(0, yRotacaoDestaTorre, 0);
         }
         yRotacaoTorres += _rotacaoY;
     }

     /**
      * @return Todas as torres que estão no terreno, isto é, torres
      *         que pertencem a tiles edificáveis do terreno.
      */
     public Vector<Torre> getTorres(){
         Vector<Torre> torres = new Vector<Torre>();
         TileEdificavel tileAtual;
         int coluna;
         int linha;
         for(coluna=0; coluna<tilesPorLinha; coluna++){
            for(linha=0; linha<tilesPorColuna; linha++){
                if(!caminho.contem(coluna, linha)){
                    tileAtual = (TileEdificavel) tiles[coluna][linha];
                    if(tileAtual.ocupadaPorTorre()){
                        torres.add(tileAtual.getTorre());
                    }
                }
            }
        }
        return torres;
     }

     /**
      * Os filhos de um desenho são desenhados pela ordem em que são adicionados.
      * Para fazer com que as torres sejam desenhadas corretamente, precisa-se garantir
      * que as tiles edificáveis do terreno sejam desenhadas depois das tiles passadouro.
      * Desta forma, é necessário atualizar as tiles depois de aplicar um caminho.
      */
     private void atualizarTilesEdificaveis(){
         TileEdificavel tileParaAtualizar;
         int coluna;
         int linha;
         for(coluna=0; coluna<tilesPorLinha; coluna++){
            for(linha=0; linha<tilesPorColuna; linha++){
                if(!caminho.contem(coluna, linha)){
                    tileParaAtualizar = (TileEdificavel) tiles[coluna][linha];
                    removerFilho(tileParaAtualizar);
                    adicionarFilho(tileParaAtualizar, 
                            tileParaAtualizar.getPosX(),
                            tileParaAtualizar.getPosY());
                }
            }
        }
     }

}
