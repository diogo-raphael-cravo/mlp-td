/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mlptd;

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

     public static Texture TIMES_NEW_ROMAN_0;
     public static Texture TIMES_NEW_ROMAN_1;
     public static Texture TIMES_NEW_ROMAN_2;
     public static Texture TIMES_NEW_ROMAN_3;
     public static Texture TIMES_NEW_ROMAN_4;
     public static Texture TIMES_NEW_ROMAN_5;
     public static Texture TIMES_NEW_ROMAN_6;
     public static Texture TIMES_NEW_ROMAN_7;
     public static Texture TIMES_NEW_ROMAN_8;
     public static Texture TIMES_NEW_ROMAN_9;

     public static Texture PIXEL_0;
     public static Texture PIXEL_1;
     public static Texture PIXEL_2;
     public static Texture PIXEL_3;
     public static Texture PIXEL_4;
     public static Texture PIXEL_5;
     public static Texture PIXEL_6;
     public static Texture PIXEL_7;
     public static Texture PIXEL_8;
     public static Texture PIXEL_9;

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

         try {
            TIMES_NEW_ROMAN_0 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(Arquivos.LINK_TIMES_NEW_ROMAN+"0.PNG"));
         } catch (Exception ex) { ex.printStackTrace(); }
         try {
            TIMES_NEW_ROMAN_1 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(Arquivos.LINK_TIMES_NEW_ROMAN+"1.PNG"));
         } catch (Exception ex) {}
         try {
            TIMES_NEW_ROMAN_2 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(Arquivos.LINK_TIMES_NEW_ROMAN+"2.PNG"));
         } catch (Exception ex) {}
         try {
            TIMES_NEW_ROMAN_3 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(Arquivos.LINK_TIMES_NEW_ROMAN+"3.PNG"));
         } catch (Exception ex) {}
         try {
            TIMES_NEW_ROMAN_4 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(Arquivos.LINK_TIMES_NEW_ROMAN+"4.PNG"));
         } catch (Exception ex) {}
         try {
            TIMES_NEW_ROMAN_5 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(Arquivos.LINK_TIMES_NEW_ROMAN+"5.PNG"));
         } catch (Exception ex) {}
         try {
            TIMES_NEW_ROMAN_6 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(Arquivos.LINK_TIMES_NEW_ROMAN+"6.PNG"));
         } catch (Exception ex) {}
         try {
            TIMES_NEW_ROMAN_7 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(Arquivos.LINK_TIMES_NEW_ROMAN+"7.PNG"));
         } catch (Exception ex) {}
         try {
            TIMES_NEW_ROMAN_8 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(Arquivos.LINK_TIMES_NEW_ROMAN+"8.PNG"));
         } catch (Exception ex) {}
         try {
            TIMES_NEW_ROMAN_9 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(Arquivos.LINK_TIMES_NEW_ROMAN+"9.PNG"));
         } catch (Exception ex) {}

         try {
            PIXEL_0 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(Arquivos.LINK_PIXEL+"0.png"));
         } catch (Exception ex) { ex.printStackTrace(); }
         try {
            PIXEL_1 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(Arquivos.LINK_PIXEL+"1.png"));
         } catch (Exception ex) {}
         try {
            PIXEL_2 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(Arquivos.LINK_PIXEL+"2.png"));
         } catch (Exception ex) {}
         try {
            PIXEL_3 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(Arquivos.LINK_PIXEL+"3.png"));
         } catch (Exception ex) {}
         try {
            PIXEL_4 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(Arquivos.LINK_PIXEL+"4.png"));
         } catch (Exception ex) {}
         try {
            PIXEL_5 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(Arquivos.LINK_PIXEL+"5.png"));
         } catch (Exception ex) {}
         try {
            PIXEL_6 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(Arquivos.LINK_PIXEL+"6.png"));
         } catch (Exception ex) {}
         try {
            PIXEL_7 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(Arquivos.LINK_PIXEL+"7.png"));
         } catch (Exception ex) {}
         try {
            PIXEL_8 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(Arquivos.LINK_PIXEL+"8.png"));
         } catch (Exception ex) {}
         try {
            PIXEL_9 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(Arquivos.LINK_PIXEL+"9.png"));
         } catch (Exception ex) {}
     }

    
}
