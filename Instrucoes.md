# "Como compilar e utilizar este projeto?" #

## Requisitos ##

### LWJGL ###
Você vai necessitar da biblioteca lwjgl, de preferência na última versão (2.8.3). Instruções de como utilizá-la no NetBeans podem ser encontradas em:
http://www.lwjgl.org/wiki/index.php?title=Setting_Up_LWJGL_with_NetBeans

### slick-util ###
Você também vai precisar da biblioteca slick-util, mas esta já está incluída neste repositório. Para instalar a biblioteca, basta adicioná-la às bibliotecas do NetBeans (como feito para a lwjgl) e adicionar às bibliotecas do projeto (na guia de projetos do NetBeans). Não é necessário alterar as propriedades do projeto.

### Imagens ###
Entre as classes, há um arquivo chamado Arquivos.java. É importante certificar-se de que todos os endereços contidos nele existem mesmo. Senão, será gerada exceção sem tratamento e o projeto não compilará.

## No Netbeans ##

Tendo a biblioteca instalada (segundo instruções da seção anterior), basta criar um novo projeto que contenha um pacote chamado "mlptd" e colar diretamente todos os arquivos deste projeto dentro do pacote. Lembre de adicionar a biblioteca e modificar as propriedades do projeto, como também é especificado no link da seção anterior.
Caso haja erro "Uncompilable code" ao compilar, tente deletar todos os arquivos e colá-los novamente.