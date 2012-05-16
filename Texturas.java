/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mlptd;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.util.ResourceLoader;

/**
 * Classe criada só por desempenho.
 * Carregar todas as imagens, uma por uma e repetindo-as, fazia o início do
 * programa ser lentíssimo.
 * @author diogo
 */
public class Texturas {
    /**
     * Texturas oferecidas.
     */
     public static Texture CAVEIRA;
     public static Texture CAVEIRA2;
     public static Texture LADRILHO;
     public static Texture GRAMA;
     public static Texture MURO;
     public static Texture TORRE;
     public static Texture CANHAO;
     public static Texture MADEIRA;
     public static Texture X;
     public static Texture OURO;

     /**
      * Texturas de uma fonte.
      * As texturas são organizadas segundo o caractere que representam.
      */
     public static Map<String, Texture> FONTE_PIXEL;
     public static Map<String, Texture> FONTE_TIMES_NEW_ROMAN;

     public static void inicializar(){
         try {
            CAVEIRA = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(Arquivos.ARQUIVO_TEXTURA_CAVEIRA));
         } catch (Exception ex) {}
         try {
            CAVEIRA2 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(Arquivos.ARQUIVO_TEXTURA_CAVEIRA_INVERTIDA));
         } catch (Exception ex) {}
         try {
            LADRILHO = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream(Arquivos.ARQUIVO_TEXTURA_LADRILHO));
         } catch (Exception ex) {}
         try {
            GRAMA = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream(Arquivos.ARQUIVO_TEXTURA_GRAMA));
         } catch (Exception ex) {}
         try {
            MURO = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(Arquivos.ARQUIVO_TEXTURA_MURO));
         } catch (Exception ex) {}
         try {
            TORRE = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(Arquivos.ARQUIVO_TEXTURA_TORRE));
         } catch (Exception ex) {}
         try {
            CANHAO = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(Arquivos.ARQUIVO_TEXTURA_CANHAO));
         } catch (Exception ex) {}
         try {
            MADEIRA = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream(Arquivos.ARQUIVO_TEXTURA_MADEIRA));
         } catch (Exception ex) {}
         try {
            X = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(Arquivos.ARQUIVO_TEXTURA_X));
         } catch (Exception ex) {}
         try {
            OURO = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(Arquivos.ARQUIVO_TEXTURA_OURO));
         } catch (Exception ex) {}

         FONTE_PIXEL = criarFonte(Arquivos.LINK_PIXEL);
         FONTE_TIMES_NEW_ROMAN = criarFonte(Arquivos.LINK_TIMES_NEW_ROMAN);
     }


     /**
      * Cria um map com uma fonte, baseado em um endereço.
      * @param _endereco O lugar onde ficam os arquivos de imagem dos caracteres.
      * @return Map que associa caracteres a suas imagens.
      */
     private static Map<String, Texture> criarFonte(String _endereco){
         Map<String, Texture> fonte  = new HashMap<String, Texture>();
         try {
            fonte.put("0", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(_endereco+"0.png")));
         } catch (Exception ex) {}
         try {
            fonte.put("1", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(_endereco+"1.png")));
         } catch (Exception ex) {}
         try {
            fonte.put("2", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(_endereco+"2.png")));
         } catch (Exception ex) {}
         try {
            fonte.put("3", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(_endereco+"3.png")));
         } catch (Exception ex) {}
         try {
            fonte.put("4", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(_endereco+"4.png")));
         } catch (Exception ex) {}
         try {
            fonte.put("5", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(_endereco+"5.png")));
         } catch (Exception ex) {}
         try {
            fonte.put("6", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(_endereco+"6.png")));
         } catch (Exception ex) {}
         try {
            fonte.put("7", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(_endereco+"7.png")));
         } catch (Exception ex) {}
         try {
            fonte.put("8", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(_endereco+"8.png")));
         } catch (Exception ex) {}
         try {
            fonte.put("9", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(_endereco+"9.png")));
         } catch (Exception ex) {}
         try {
            fonte.put("A", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(_endereco+"A.png")));
         } catch (Exception ex) {}
         try {
            fonte.put("B", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(_endereco+"B.png")));
         } catch (Exception ex) {}
         try {
            fonte.put("C", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(_endereco+"C.png")));
         } catch (Exception ex) {}
         try {
            fonte.put("D", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(_endereco+"D.png")));
         } catch (Exception ex) {}
         try {
            fonte.put("E", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(_endereco+"E.png")));
         } catch (Exception ex) {}
         try {
            fonte.put("F", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(_endereco+"F.png")));
         } catch (Exception ex) {}
         try {
            fonte.put("G", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(_endereco+"G.png")));
         } catch (Exception ex) {}
         try {
            fonte.put("H", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(_endereco+"H.png")));
         } catch (Exception ex) {}
         try {
            fonte.put("I", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(_endereco+"I.png")));
         } catch (Exception ex) {}
         try {
            fonte.put("J", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(_endereco+"J.png")));
         } catch (Exception ex) {}
         try {
            fonte.put("K", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(_endereco+"K.png")));
         } catch (Exception ex) {}
         try {
            fonte.put("L", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(_endereco+"L.png")));
         } catch (Exception ex) {}
         try {
            fonte.put("M", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(_endereco+"M.png")));
         } catch (Exception ex) {}
         try {
            fonte.put("N", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(_endereco+"N.png")));
         } catch (Exception ex) {}
         try {
            fonte.put("O", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(_endereco+"O.png")));
         } catch (Exception ex) {}
         try {
            fonte.put("P", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(_endereco+"P.png")));
         } catch (Exception ex) {}
         try {
            fonte.put("Q", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(_endereco+"Q.png")));
         } catch (Exception ex) {}
         try {
            fonte.put("R", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(_endereco+"R.png")));
         } catch (Exception ex) {}
         try {
            fonte.put("S", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(_endereco+"S.png")));
         } catch (Exception ex) {}
         try {
            fonte.put("T", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(_endereco+"T.png")));
         } catch (Exception ex) {}
         try {
            fonte.put("U", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(_endereco+"U.png")));
         } catch (Exception ex) {}
         try {
            fonte.put("V", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(_endereco+"V.png")));
         } catch (Exception ex) {}
         try {
            fonte.put("W", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(_endereco+"W.png")));
         } catch (Exception ex) {}
         try {
            fonte.put("X", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(_endereco+"X.png")));
         } catch (Exception ex) {}
         try {
            fonte.put("Y", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(_endereco+"Y.png")));
         } catch (Exception ex) {}
         try {
            fonte.put("Z", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(_endereco+"Z.png")));
         } catch (Exception ex) {}
         return fonte;
     }
    
}
