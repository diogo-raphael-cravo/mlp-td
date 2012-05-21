package mlptd;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 * Uma torre, que pode atirar projéteis em inimigos.
 * @author diogo
 */
public class Torre extends Desenho {
    /**
     * Tipos de torres.
     */
    public static enum TIPO_TORRE{MADEIRA, CANHAO};
    
    /**
     * O tipo da torre define atributos como dano,
     * alcance, textura, tamanho...
     */
    private TIPO_TORRE tipo;
    
    /**
     * Modelo de projétil desta torre.
     */
    private Projetil modeloProjetil;
    
    /**
     * @param _posX, _posY A posição do ponto superior esquerdo na tela.
     * @param _comprimento, _largura Comprimento e largura.
     * @param _tamanhoEmPorcentagem Usado para resize.
     * @param _tipo O tipo da torre, que define seus atributos como dano, alcance, textura, tamanho...
     */
    public Torre(float _posX, float _posY, float _comprimento, float _largura, TIPO_TORRE _tipo){
        super(_posX, _posY, _comprimento, _largura);
        setTipo(_tipo);
    }
    public Torre(Torre _torre) {
        super(_torre);
        setTipo(_torre.getTipo());
    }
    
    public TIPO_TORRE getTipo(){
        return tipo;
    }
    
    /**
     * Modifica os atributos da torre para que obedeçam ao seu tipo.
     * Note que cada textura necessita de um comprimento e largura ideais
     * para ser exibida corretamente.
     * @param _tipo O tipo que a torre deve ter.
     */
    private void setTipo(TIPO_TORRE _tipo){
        float comprimentoAtual = comprimento;
        float larguraAtual = largura;
        tipo = _tipo;
        switch(tipo){
            case MADEIRA: 
                    comprimento = 100;
                    largura = 200;
                    definirTextura(Texturas.TORRE);
                    modeloProjetil = new Projetil(Projetil.TIPO_PROJETIL.FLECHA);
                break;
            case CANHAO: 
                    comprimento = 480;
                    largura = 200;
                    definirTextura(Texturas.CANHAO);
                    modeloProjetil = new Projetil(Projetil.TIPO_PROJETIL.BALA_DE_CANHAO);
                break;
        }
        redimensionar(fatorEscalaX*comprimentoAtual, fatorEscalaY*larguraAtual, altura);
    }
    
    /**
     * @return Cópia do projétil desta torre.
     */
    public Projetil getProjetil(){
        return new Projetil(modeloProjetil);
    }
    
}
