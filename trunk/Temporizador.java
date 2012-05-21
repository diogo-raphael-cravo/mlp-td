package mlptd;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 * Marca o tempo, gerando dados de frame rate e tempo desde a última marcação.
 * @author diogo
 */
public class Temporizador {

    /**
     * Temporizador principal.
     * Este elemento não pode ser acessível de fora desta classe, não deve haver getter para ele.
     * O motivo é que todas as suas propriedades são públicas.
     */
    private static Temporizador temporizador;

    /**
     * Guarda os dados das 5 últimas marcações.
     */
    public long marcacoes[];
    
    public static void inicializar(){
        temporizador = new Temporizador();
    }

    public Temporizador(){
        marcacoes = new long[5];
        marcacoes[0] = 1000/24;
        marcacoes[1] = 1000/24;
        marcacoes[2] = 1000/24;
        marcacoes[3] = 1000/24;
        marcacoes[4] = 1000/24;
    }

    /**
     * Realiza uma marcação no temporizador principal, salvando a hora atual.
     */
     public static void marcarAgoraPrincipal(){
          temporizador.marcarAgora();
     }

     /**
      * Realiza uma marcação no temporizador, salvando a hora atual.
      */
     public void marcarAgora(){
         marcacoes[4] = marcacoes[3];
         marcacoes[3] = marcacoes[2];
         marcacoes[2] = marcacoes[1];
         marcacoes[1] = marcacoes[0];
         marcacoes[0] = System.nanoTime()/1000000;
     }

    /**
     * @return O tempo em milissegundos que se passou entre as duas últimas marcações no temporizador principal.
     */
     public static long diferencaUltimasDuasMarcacoesPrincipal(){
         return temporizador.diferencaUltimasDuasMarcacoes();
     }

     /**
      * @return O tempo em milissegundos que se passou entre as duas últimas marcações no temporizador
      */
     public long diferencaUltimasDuasMarcacoes(){
         long diferenca = marcacoes[0] - marcacoes[1];
         if(100 < diferenca){
             diferenca = 100;
         }
         return diferenca;
     }

     /**
      * @return O tempo em milissegundos que se passou desde a última marcação feita.
      */
     public long tempoDesdeUltimaMarcacao(){
         long diferenca = System.nanoTime()/1000000 - marcacoes[0];
         return diferenca;
     }

     /**
      * @return Frame rate aproximado, com base nas 5 últimas marcações.
      */
     public static long frameRate(){
         long mediaMarcacoes = 0;
         mediaMarcacoes += temporizador.marcacoes[0];
         mediaMarcacoes += temporizador.marcacoes[1];
         mediaMarcacoes += temporizador.marcacoes[2];
         mediaMarcacoes += temporizador.marcacoes[3];
         mediaMarcacoes += temporizador.marcacoes[4];
         mediaMarcacoes = mediaMarcacoes/5;
         int frameRateCalculado = (int) ((int) 1000 / mediaMarcacoes);
         if(frameRateCalculado == 0){
             frameRateCalculado = Integer.MAX_VALUE;
         }
         return frameRateCalculado;
     }
}
