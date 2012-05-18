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
import java.util.Vector;
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
     public static Texture CORACAO;
     public static Texture RELOGIO;
     public static Texture[] SLIMEKING;

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
            CORACAO = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(Arquivos.ARQUIVO_TEXTURA_CORACAO));
         } catch (Exception ex) {}
         try {
            RELOGIO = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(Arquivos.ARQUIVO_TEXTURA_RELOGIO));
         } catch (Exception ex) {}

         SLIMEKING = new Texture[9];
         try {
            SLIMEKING[0] = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(Arquivos.LINK_BASE_TEXTURA_SLIMEKING+"1.png"));
         } catch (Exception ex) {}
         try {
            SLIMEKING[1] = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(Arquivos.LINK_BASE_TEXTURA_SLIMEKING+"2.png"));
         } catch (Exception ex) {}
         try {
            SLIMEKING[2] = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(Arquivos.LINK_BASE_TEXTURA_SLIMEKING+"3.png"));
         } catch (Exception ex) {}
         try {
            SLIMEKING[3] = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(Arquivos.LINK_BASE_TEXTURA_SLIMEKING+"4.png"));
         } catch (Exception ex) {}
         try {
            SLIMEKING[4] = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(Arquivos.LINK_BASE_TEXTURA_SLIMEKING+"5.png"));
         } catch (Exception ex) {}
         try {
            SLIMEKING[5] = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(Arquivos.LINK_BASE_TEXTURA_SLIMEKING+"6.png"));
         } catch (Exception ex) {}
         try {
            SLIMEKING[6] = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(Arquivos.LINK_BASE_TEXTURA_SLIMEKING+"7.png"));
         } catch (Exception ex) {}
         try {
            SLIMEKING[7] = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(Arquivos.LINK_BASE_TEXTURA_SLIMEKING+"8.png"));
         } catch (Exception ex) {}
         try {
            SLIMEKING[8] = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(Arquivos.LINK_BASE_TEXTURA_SLIMEKING+"9.png"));
         } catch (Exception ex) {}



     }


    
}
