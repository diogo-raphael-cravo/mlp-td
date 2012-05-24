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
     * Intervalo de tempo entre dois disparos de projéteis desta torre.
     */
    private long intervaloDisparados;

    /**
     * Hora, em milissegundos, em que foi feito o último disparo.
     */
    private long horaUltimoDisparo;

    /**
     * O alcance de toda torre é um círculo em torno de seu ponto (0,0).
     * Este alcance é o raio do alcance da torre.
     */
    private float raioAlcance;
    
    /**
     * @param _posX, _posY A posição do ponto superior esquerdo na tela.
     * @param _comprimento, _largura Comprimento e largura.
     * @param _tamanhoEmPorcentagem Usado para resize.
     * @param _tipo O tipo da torre, que define seus atributos como dano, alcance, textura, tamanho...
     */
    public Torre(float _posX, float _posY, float _comprimento, float _largura, TIPO_TORRE _tipo){
        super(_posX, _posY, _comprimento, _largura);
        setTipo(_tipo);
        horaUltimoDisparo = 0;
    }
    public Torre(Torre _torre) {
        super(_torre);
        setTipo(_torre.getTipo());
        horaUltimoDisparo = 0;
    }
    
    public TIPO_TORRE getTipo(){
        return tipo;
    }
    public long getIntervaloDisparos(){
        return intervaloDisparados;
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
                    intervaloDisparados = 1000;
                    definirTextura(Texturas.TORRE);
                    modeloProjetil = new Projetil(Projetil.TIPO_PROJETIL.FLECHA);
                    if(getPai() != null){ //está sempre entrando no else.
                        raioAlcance = getPai().getComprimento() * 2;
                    } else {
                        raioAlcance = 150;
                    }
                break;
            case CANHAO: 
                    comprimento = 480;
                    largura = 200;
                    intervaloDisparados = 10000;
                    definirTextura(Texturas.CANHAO);
                    modeloProjetil = new Projetil(Projetil.TIPO_PROJETIL.BALA_DE_CANHAO);
                    if(getPai() != null){ //está sempre entrando no else.
                        raioAlcance = getPai().getComprimento() * 10;
                    } else {
                        raioAlcance = 1000;
                    }
                break;
        }
        redimensionar(fatorEscalaX*comprimentoAtual, fatorEscalaY*larguraAtual, altura);
    }
    
    /**
     * Na prática, apenas marca a hora em que foi feito o último disparo.
     * Habilita o uso de estahProntaParaDisparar().
     * @return Cópia do projétil desta torre.
     */
    public Projetil disparar(){
        Projetil projetilDisparado = new Projetil(modeloProjetil);
        horaUltimoDisparo = Temporizador.getHoraAtualMilissegundos();
        projetilDisparado.mover(getPai().getPosX()+getPosX(), getPai().getPosY()+getPosY());
        return projetilDisparado;
    }

    /**
     * @param _inimigo Inimigo cuja posição será testada.
     * @return Booleano indicando se _inimigos está na região de alcance desta
     *      torre, isto é, se ele pode ser atingido por um projétil.
     */
    public boolean estahNoAlcance(Inimigo _inimigo){
        boolean estah = false;
        float xCentroCirculo = getPai().getPosX() + getPosX();
        float yCentroCirculo = getPai().getPosY() + getPosY();
        float xSudoesteInimigo = _inimigo.getPosX();
        float ySudoesteInimigo = _inimigo.getPosY();
        float xSudesteInimigo = _inimigo.getPosX() + _inimigo.getComprimento();
        float ySudesteInimigo = _inimigo.getPosY();
        float xNoroesteInimigo = _inimigo.getPosX();
        float yNoroesteInimigo = _inimigo.getPosY() + _inimigo.getLargura();
        float xNordesteInimigo = _inimigo.getPosX() + _inimigo.getComprimento();
        float yNordesteInimigo = _inimigo.getPosY() + _inimigo.getLargura();

        float distanciaNoroesteDoInimigoAoCentroDoAlcance = 
                (float) Math.hypot(xNoroesteInimigo-xCentroCirculo, yNoroesteInimigo-yCentroCirculo);
        float distanciaNordesteDoInimigoAoCentroDoAlcance = 
                (float) Math.hypot(xNordesteInimigo-xCentroCirculo, yNordesteInimigo-yCentroCirculo);
        float distanciaSudoesteDoInimigoAoCentroDoAlcance = 
                (float) Math.hypot(xSudoesteInimigo-xCentroCirculo, ySudoesteInimigo-yCentroCirculo);
        float distanciaSudesteDoInimigoAoCentroDoAlcance = 
                (float) Math.hypot(xSudesteInimigo-xCentroCirculo, ySudesteInimigo-yCentroCirculo);

        if(distanciaNoroesteDoInimigoAoCentroDoAlcance <= raioAlcance
                || distanciaNordesteDoInimigoAoCentroDoAlcance <= raioAlcance
                || distanciaSudoesteDoInimigoAoCentroDoAlcance <= raioAlcance
                || distanciaSudesteDoInimigoAoCentroDoAlcance <= raioAlcance){
            estah = true;
        }

        return estah;
    }

    /**
     * @return Booleano indicando se esta torre já está pronta para um novo disparo.
     */
    public boolean estahProntaParaDisparar(){
        long horaAtual = Temporizador.getHoraAtualMilissegundos();
        long tempoDesdeUltimoDisparo = horaAtual - horaUltimoDisparo;
        if(horaUltimoDisparo == 0
                || intervaloDisparados <= tempoDesdeUltimoDisparo){
            return true;
        } else {
            return false;
        }
    }
    
}

