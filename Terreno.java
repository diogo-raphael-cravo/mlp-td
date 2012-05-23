package mlptd;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



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
     * Controlador de eventos deste terreno.
     */
     private ControladorTerreno controlador;

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

        xRotacaoInimigos = 90;
        yRotacaoInimigos = 0;
        zRotacaoInimigos = 0;

        yRotacaoTorres = 0;

        controlador = null;

        /**
         * Criação do plano de fundo.
         */
        Desenho fundo = new Desenho(0, 0, comprimentoCadaTile, larguraCadaTile);
        fundo.redimensionar(comprimento, largura, altura);
        fundo.definirTextura(Texturas.GRAMA);
        adicionarFilho(new Desenho(fundo), getPosX()-comprimento/2, posY-largura/2);
        adicionarFilho(new Desenho(fundo), getPosX()+comprimento/2, posY-largura/2);
        adicionarFilho(new Desenho(fundo), getPosX()+3*comprimento/2, posY-largura/2);
        adicionarFilho(new Desenho(fundo), getPosX()-comprimento/2, posY+largura/2);
        adicionarFilho(new Desenho(fundo), getPosX()-comprimento/2, posY+3*largura/2);
        adicionarFilho(new Desenho(fundo), getPosX()+comprimento/2, posY+3*largura/2);
        adicionarFilho(new Desenho(fundo), getPosX()+3*comprimento/2, posY+3*largura/2);
        adicionarFilho(new Desenho(fundo), getPosX()+3*comprimento/2, posY+largura/2);
     }

     public float getComprimentoCadaTile(){
         return comprimentoCadaTile;
     }
     public float getLarguraCadaTile(){
         return larguraCadaTile;
     }
     public ControladorTerreno getControlador(){
         return controlador;
     }

     /**
      * @param Controlador de eventos para este terreno.
      */
     public void definirControlador(ControladorTerreno _controlador){
         controlador = _controlador;
     }

     /**
      * @return Cópia rasa do vector de inimigos que estão no terreno.
      */
     public Vector<Inimigo> getInimigos(){
         return inimigosNoTerreno;
     }

     /**
      * @return Vector com seqüência de tiles no caminho deste terreno.
      */
     public Vector<TilePassadouro> getTilesCaminho(){
        Vector<TilePassadouro> tilesCaminho = new Vector<TilePassadouro>();
        for(int posicao=0; posicao<caminho.getComprimento(); posicao++){
            tilesCaminho.add((TilePassadouro) tiles[caminho.getColunaTile(posicao)][caminho.getLinhaTile(posicao)]);
        }
        return tilesCaminho;
     }
     public Caminho getCaminho(){
         return caminho;
     }

     /**
      * Adiciona um inimigo a este terreno, que correrá imediatamente
      * à partir da primeira tile do caminho.
      * @param _inimigo Modelo de inimigo a ser adicionado.
      */
     public void adicionarInimigo(Inimigo _inimigoModelo){
          TilePassadouro primeiraTileCaminho = (TilePassadouro) tiles[caminho.getColunaTile(0)][caminho.getLinhaTile(0)];
          _inimigoModelo.mover(getPosX(), getPosY());
          _inimigoModelo.definirCaminhoQueSeguira(caminho);
          primeiraTileCaminho.adicionarInimigo(_inimigoModelo);
          inimigosNoTerreno.add(_inimigoModelo);
          _inimigoModelo.inicializarEventos();
          _inimigoModelo.rotacionar(xRotacaoInimigos, -yRotacaoInimigos, zRotacaoInimigos);
          adicionarFilho(_inimigoModelo, primeiraTileCaminho.getPosX(), primeiraTileCaminho.getPosY());
     }

     

     /**
      * @param _posX, _posY A posição a ser testada.
      * @return A tile deste terreno que contenha a posição de parâmetro.
      *         Se não houver, retornará null.
      */
     public Tile getTileComPosicao(float _posX, float _posY){
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
     public Tile getTileVizinha(Tile _tile, Caminho _caminho){
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

     /**
      * @return Vetor com todas as tiles edificáveis do terreno, e somente elas.
      */
     public Vector<TileEdificavel> getTilesEdificaveis(){
         Vector<TileEdificavel> tilesEdificaveis = new Vector<TileEdificavel>();
         for(int coluna=0; coluna<tilesPorLinha; coluna++){
             for(int linha=0; linha<tilesPorColuna; linha++){
                 if(!caminho.contem(coluna, linha)){
                    tilesEdificaveis.add((TileEdificavel) tiles[coluna][linha]);
                 }
             }
         }
         return tilesEdificaveis;
     }

}
