package mlptd;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * Um projétil, que pode ser atirado por uma torre.
 * @author diogo
 */
public class Projetil extends Desenho {
    /**
     * Tipo do projétil, que define dados como sua área de impacto (não
     * confundir com o alcance da torre), textura e dano base.
     */
    public static enum TIPO_PROJETIL{FLECHA, BALA_DE_CANHAO};
    private TIPO_PROJETIL tipo;

    /**
     * O inimigo que está sendo perseguido por este projétil, caso haja.
     */
    private Inimigo perseguido;

    /**
     * Velocidade do projétil em Tiles por segundo.
     * Uma tile é medida pelo seu comprimento.
     * Assim, este é o número de tiles que o projétil pode
     * cruzar por segundo em rota horizontal.
     * Caso a rota possua componente no eixo y, o número de
     * tiles realmente passadas por segundo será um pouco menor.
     */
    private float velocidadeTilesSegundo;

    /**
     * O comprimento e altura não são permitidos, pois todo projétil é sempre
     * criado como o comprimento e altura ideais para sua textura. Para mudar
     * suas dimensões, utilize Desenho.redimensionar. Para mudar sua posição,
     * utilize Desenho.adicionarFilho(coloque este projétil aqui).
     * @param _tipo O tipo de projétil, que definirá dados como sua área de 
     *  impacto, textura e dano base. Atenção: este tipo não é modificável!
     */
    public Projetil(TIPO_PROJETIL _tipo){
        super(0, 0, 0, 0); //Comprimento e largura serão definidos ainda no construtor.
        float comprimentoIdeal = 0;
        float larguraIdeal = 0;
        tipo = _tipo;
        switch(tipo){
            case FLECHA:
                    definirTextura(Texturas.FLECHA);
                    comprimentoIdeal = 1500;
                    larguraIdeal = 300;
                break;
            case BALA_DE_CANHAO:
                    definirTextura(Texturas.BALA_DE_CANHAO);
                    comprimentoIdeal = 130;
                    larguraIdeal = 130;
                break;
        }
        comprimento = comprimentoIdeal;
        largura = larguraIdeal;
        switch(tipo){
            case FLECHA: redimensionar(50, 30, 0);
                break;
            case BALA_DE_CANHAO: redimensionar(10, 10, 0);
                break;
        }
        perseguido = null;
        /*ATENÇÃO: precisa ser rápida o sufuciente para que o usuário não perceba
         * que o movimento do projétil faz curvas! O projétil deve conseguir alcan-
         * çar seu destino antes de fazer uma curva!
         */
        velocidadeTilesSegundo = 25;
    }
    public Projetil(Projetil _projetil){
        this(_projetil.tipo);
    }

    /**
     * @return Velocidade do projétil em Tiles por segundo.
     */
     public float getVelocidadeTilesPorSegundo(){
         return velocidadeTilesSegundo;
     }

    /**
     * Manda este projétil perseguir o inimigo dado.
     * ATENÇÃO: esta função só guarda o inimigo perse-
     * guido, mas não faz a perseguição!
     * @param _perseguido Inimigo que deve ser perse-
     *      guido.
     */
    public void setPerseguido(Inimigo _perseguido){
        perseguido = _perseguido;
    }

    /**
     * @return Booleano indicando se este projétil está
     * perseguindo algum inimigo.
     */
     public boolean possuiInimigoPerseguido(){
         if(perseguido != null){
             return true;
         } else {
             return false;
         }
     }

     /**
      * @return Caso haja, retorna o inimigo perseguido.
      *         Caso contrário, retorna null.
      */
     public Inimigo getPerseguido(){
         return perseguido;
     }

     /**
      * Todo projétil, em sua textura, deve ter sua ponta na horizontal
      * e no sentido da esquerda para a direita. Se isto for obedecido,
      * esta função sempre manterá o projétil virada para a direção em
      * que está indo.
      * @param _deslocamentoX, _deslocamentoY Vetor direção em que o pro-
      *         jétil deve olhar.
      */
      public void mudarDirecao(float _deslocamentoX, float _deslocamentoY){
          double hipotenusa = Math.hypot((double) _deslocamentoX, (double) _deslocamentoY);
          double seno=-20000;
          float angulo;

          if(0 < _deslocamentoX){
              seno = _deslocamentoY/hipotenusa;

              angulo = (float) Math.toDegrees(Math.asin(seno));
          } else if(_deslocamentoX < 0){
              seno = -_deslocamentoY/hipotenusa;
              angulo = (float) Math.toDegrees(Math.asin(seno));
              angulo += 180;
          } else if(0 <= _deslocamentoY){
              angulo = 90;
          } else {
              angulo = -90;
          }
          rotacionar(0, 0, angulo-getRotacaoZ());
      }
     
}
