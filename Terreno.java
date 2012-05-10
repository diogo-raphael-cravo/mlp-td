/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mlptd;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    /**
     * O terreno é criado com um número especificado de tiles de forma que ocupe as dimensões que lhe são especificadas.
     * @param _posicao Posição do terreno.
     * @param _dimensoesTela Comprimento e largura do terreno em unidades de tela.
     * @param _dimensoesTiles Comprimento e largura do terreno em tiles.
     */
     public Terreno(float _posX, float _posY, float _comprimentoTela, float _larguraTela,
                    int _comprimentoEmTiles, int _larguraEmTiles){
        super(_posX, _posY+_larguraTela, _comprimentoTela, _larguraTela, 100);
         
        comprimentoCadaTile = _comprimentoTela/_comprimentoEmTiles;
        larguraCadaTile = _larguraTela/_larguraEmTiles;
        
        tilesPorLinha = _comprimentoEmTiles;
        tilesPorColuna = _larguraEmTiles;
		
        tiles = new Tile[tilesPorLinha][tilesPorColuna];

        //Pequeno teste. Notar que a tile (0,0) é sinalizada com verde, e não laranja.
        transformarEmTabuleiro(new Color(Color.ORANGE));
        //transformarEmTabuleiro(new Color(Color.GREEN));

        //caminho = Caminho.criarCaminhoDiagonalDecrescente(tilesPorColuna, tilesPorLinha, tilesPorColuna);
        caminho = Caminho.criarCaminhoLinhasAlternadas(tilesPorLinha, tilesPorColuna);
        aplicar(caminho);

        inimigosNoTerreno = new Vector<Inimigo>();
        adicionarInimigo(new Inimigo());
     }

     /**
      * Adiciona um inimigo a este terreno, que correrá imediatamente
      * à partir da primeira tile do caminho.
      * @param _inimigo Modelo de inimigo a ser adicionado.
      */
     public void adicionarInimigo(Inimigo _inimigoModelo){
          TilePassadouro primeiraTileCaminho = (TilePassadouro) tiles[caminho.getColunaTile(0)][caminho.getLinhaTile(0)];
          Inimigo inimigoNovo = new Inimigo(_inimigoModelo);
          inimigoNovo.mover(primeiraTileCaminho.getPosX(), primeiraTileCaminho.getPosY());
          primeiraTileCaminho.adicionarInimigo(inimigoNovo);
          inimigosNoTerreno.add(inimigoNovo);
     }

     /**
      * Move os inimigos que estão neste terreno.
      * Os inimigos seguem a rota indicada pelo caminho.
      */
     public void moverInimigos() throws InterruptedException{
        Inimigo inimigoNaoMovido = inimigosNoTerreno.get(0);
        Tile exemploTileTerreno = tiles[0][0];
        TilePassadouro tileInimigo = (TilePassadouro) getTileComPosicao(inimigoNaoMovido.getPosX(), inimigoNaoMovido.getPosY());
        TilePassadouro tileVizinhaTileInimigo = null;
        float tempoPassadoDesdeUltimoMovimentoEmSegundos = Temporizador.diferencaUltimasDuasMarcacoes()/((float) 1000.0);
        float velocidadeInimigoEmPixelsPorSegundo = exemploTileTerreno.getComprimento()*
                                                    inimigoNaoMovido.getVelocidadeTilesPorSegundo();
        float xDestinoInimigo = 0;
        float yDestinoInimigo = 0;
        float xVariacaoInimigo = 0;
        float yVariacaoInimigo = 0;

        int direcaoX = 1;
        int direcaoY = 1;
        
        if(tileInimigo != null){
            tileVizinhaTileInimigo = (TilePassadouro) getTileVizinha(tileInimigo, caminho);
        }

        if(tileVizinhaTileInimigo != null){
            xDestinoInimigo = tileVizinhaTileInimigo.xCentroParaDesenho(inimigoNaoMovido);
            yDestinoInimigo = tileVizinhaTileInimigo.yCentroParaDesenho(inimigoNaoMovido);

            if(xDestinoInimigo <= inimigoNaoMovido.getPosX()){
                direcaoX = -1;
            } else {
                direcaoX = 1;
            }

            if(yDestinoInimigo <= inimigoNaoMovido.getPosY()){
                direcaoY = 1;
            } else {
                direcaoY = -1;
            }

            if(inimigoNaoMovido.getPosX() == xDestinoInimigo){
                xVariacaoInimigo = 0;
                if(inimigoNaoMovido.getPosY() != yDestinoInimigo){
                    yVariacaoInimigo = - velocidadeInimigoEmPixelsPorSegundo;
                }
            } else if(inimigoNaoMovido.getPosY() == yDestinoInimigo){
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
            System.out.print("("+xVariacaoInimigo+","+yVariacaoInimigo+")\n");
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
            encontrada_posicaoTileParametroNoCaminho = tileTestada.contem(_tile.getPosX()+1, _tile.getPosY()+1);
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
      * @param _caminho O caminho a ser aplicado.
      */
     private void aplicar(Caminho _caminho){
        int posicaoAtual;
        int linha;
        int coluna;
        Tile tileSubstituida;
        for(posicaoAtual = 0; posicaoAtual<_caminho.getComprimento(); posicaoAtual++){
            coluna = _caminho.getColunaTile(posicaoAtual);
            linha = _caminho.getLinhaTile(posicaoAtual);
            tileSubstituida = tiles[coluna][linha];
            tiles[coluna][linha] = new TilePassadouro(tileSubstituida);
        }
     }

     /**
      * Transforma este terreno em um tabuleiro.
      * A casa de índice (0,0) é sinalizada com a cor de parâmetro.
      */
     private void transformarEmTabuleiro(Color _cor){
        int linha;
        int coluna;
        Color corCasaAtual;
        for(coluna=0; coluna<tilesPorLinha; coluna++){
            for(linha=0; linha<tilesPorColuna; linha++){
                if(linha==0 && coluna == 0){
                    corCasaAtual = new Color(_cor);
                } else if((coluna%2==0 && linha%2==1)
                   || (coluna%2==1 && linha%2==0)){
                    corCasaAtual = new Color(Color.BLUE);
                } else {
                    corCasaAtual = new Color(Color.RED);
                }
                adicionarTile(coluna, linha, corCasaAtual);
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
     private void adicionarTile(int _colunaTile, int _linhaTile, Color _cor){
         float xTile;
         float yTile;
         Tile tileQueDeveSerAdicionada;
         boolean tileEstahForaDosLimites = (tilesPorLinha <= _colunaTile || tilesPorColuna <= _linhaTile);
         if(!tileEstahForaDosLimites){
            xTile = posX + _colunaTile*comprimentoCadaTile;
            yTile = posY - _linhaTile*larguraCadaTile;
            
            tileQueDeveSerAdicionada = new Tile(xTile, yTile, comprimentoCadaTile, larguraCadaTile);
            tileQueDeveSerAdicionada.mudarCor(_cor);
            tiles[_colunaTile][_linhaTile] = tileQueDeveSerAdicionada;
         }
         //Apenas as tiles que não são passadouro têm eventos de mouse.
         tiles[_colunaTile][_linhaTile].inicializarEventos();
     }
     
     /**
      * Desenha todas as tiles do terreno.
      */
    @Override
     public void desenhar(){
         for(int linha=0; linha<tilesPorColuna; linha++){
            for(int coluna=0; coluna<tilesPorLinha; coluna++){
                tiles[coluna][linha].desenhar();
            }
         }
     }

     /**
      * Move o terreno.
      * @param _destino Posição para onde o terreno deve ir.
      */
    @Override
     public void mover(float _posX, float _posY){
        posX = _posX;
        posY = _posY;

        float xTile;
        float yTile;
        for(int linha=0; linha<tilesPorColuna; linha++){
            for(int coluna=0; coluna<tilesPorLinha; coluna++){
                xTile = posX + coluna*comprimentoCadaTile;
                yTile = posY - linha*larguraCadaTile;
                tiles[coluna][linha].mover(xTile, yTile);
            }
        }
     }

}
