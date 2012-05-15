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
     }

    
}
