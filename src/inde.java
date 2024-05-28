/*********************************************************************/
/** Centro Universitario Senac **/
/** TADS - 1o semestre de 2024 **/
/** <Fernando> **/
/** **/
/** Projeto SEMESTRAL I **/
/** Arquivo: <nome do arquivo> **/
/** **/
/** <Italo Ribeiro de Alcântara> **/
/** <Nome completo do aluno 2> **/
/** <Nome completo do aluno 3> **/
/** **/
/** <data da entrega> **/
/*********************************************************************/

import java.util.Random;
import java.util.Scanner;
public class inde {
    
    static Scanner ler = new Scanner(System.in);
    static int[] contadorPoints = {0,0}; //Única variável global usada para armazenar os pontos dos jogadores/máquinas
    public static void main(String[] args){
        int modoJogo = imprimeMenuPrincipal();
        char[][] tabuleiro = inicializarTabuleiro();
        
        //Modos de jogo selecionado pelo user
        switch (modoJogo) {
            case 1:
                modoJogador(tabuleiro);
            break;
            case 2:
                modoFacil(tabuleiro);
            break;
            case 3:
                    modoDificil(tabuleiro);     
            break;
            default:
                System.err.println("Error )=");
                break;
        }    
    }
    
    public static int imprimeMenuPrincipal(){
     System.out.println("Olá! Seja bem-vindo(a) ao jogo da velha ( =");
     System.out.println("1 - Player vs Player");
     System.out.println("2 - Player vs Máquina(Fácil)");
     System.out.println("3 - Player vs Máquina(Difícil)");
     System.out.println("Informe o modo de jogo: ");
     int modoJogo = ler.nextInt(); // Lê o modo de jogo selecionado pelo user
     
     //Tratamento de opção inválida
     while (modoJogo != 1 && modoJogo != 2 && modoJogo != 3) {
        System.out.println("Opção inválida!");
        System.out.println("1 - Player vs Player");
        System.out.println("2 - Player vs Máquina(Fácil)");
        System.out.println("3 - Player vs Máquina(Difícil)");
        System.out.println("Informe o modo de jogo: ");
        modoJogo = ler.nextInt();
     }

     return modoJogo;
    }

    //player vs player
    public static void modoJogador(char[][] tabuleiro){
        System.out.println("Modo seleiconado: Player vs Player");

        //Chama os players de acordo com os valores do contador(par/ímpar)
        for(int i = 0; i < 9; i ++){
            if(i % 2 == 0){
                player1(tabuleiro);
            }
            else{
                player2(tabuleiro);
            }
        }
    }

    //player vs máquina(fácil)
    public static void modoFacil(char[][] tabuleiro){
        System.out.println("Modo Fácil seleiconado: Player vs Maquina\n");
        
        //Chama os players ou maquina de acordo com os valores do contador(par/ímpar)
        for(int i = 0; i < 9; i ++){
            if(i % 2 == 0){
                player1(tabuleiro);
            }
            else{
                MachineEasy(tabuleiro);
            }
        }
    }

    //player vs máquina(Difícil)
    public static void modoDificil(char[][] tabuleiro){
        System.out.println("\n_Modo_Difícil_\n");
        imprimirTabuleiro(tabuleiro);
    }

    //Preenche o tabluleiro
    public static char[][] inicializarTabuleiro(){
        char[][] tabuleiro = new char[3][3];
        
        for(int i = 0; i < 3; i ++){
            for(int j = 0; j < 3; j ++){
                tabuleiro[i][j] = '_';
            }
        }
        return tabuleiro;
    }

    //Imprime o tabuleiro
    public static void imprimirTabuleiro(char tabuleiro[][]){
        System.out.println(" -----------");
        for(int i = 0; i < 3; i ++){
            System.out.print("| ");
            for(int j = 0; j < 3; j ++){
                System.out.print(tabuleiro[i][j] + " | ");
            }
            System.out.println();
        }
        System.out.println(" -----------");
    }

    //Lê a linha e verifica se ela existe no tabuleiro
    public static int leiaCoordenadaLinha(){
        System.out.println("Informe a linha: ");
        int linha = ler.nextInt();
        
        while(linha > 3 || linha < 1){
            System.out.println("Linha inválida!");
            linha = leiaCoordenadaLinha();
        }

        return linha;   
    }
    //Lê a coluna e verifica se ela existe no tabuleiro
    public static int leiaCoordenadaColuna(){
        System.out.println("Informe a coluna: ");
        int coluna = ler.nextInt();

        while (coluna > 3 || coluna < 1) {
            System.out.println("Coluna inválida!");
            coluna = leiaCoordenadaColuna();
        }

        return coluna;
    }

    //Verifica se a posição da matriz está livre
    public static boolean posicaoValida(int linha, int coluna, char[][] tabuleiro, char iconePlayer){
        boolean validation = false;
        
            for(int i = 0; i < 3; i ++){
                for(int j = 0; j < 3; j ++){
                    if((linha - 1) == i && (coluna - 1) == j){
                        if(tabuleiro[i][j] == '_'){
                            validation = true;
                        }
                        else{
                            validation = false;
                            System.err.println("Posição já preenchida )=");
                        }
                    }
                }
            }

        return validation;
    }

    public static boolean posicaoValidaMaquina(int linha, int coluna, char[][] tabuleiro, char iconePlayer){
        boolean validation = false;
        
            for(int i = 0; i < 3; i ++){
                for(int j = 0; j < 3; j ++){
                    if((linha - 1) == i && (coluna - 1) == j){
                        if(tabuleiro[i][j] == '_'){
                            validation = true;
                        }
                        else{
                            validation = false;
                        }
                    }
                }
            }

        return validation;
    }

    //Após a validação da função "posicaoValida", guarda os valores de linha e coluna.
    public static void jogadaUsuario(boolean posicaoValida, int linha, int coluna, char[][] tabuleiro, char iconePlayer){
        
            int[] linhaAndColuna = {linha,coluna};
            jogar(linhaAndColuna, tabuleiro, iconePlayer);//Envia os valores de linha e coluna para está função

    }
    
    public static void jogar(int[] linhaAndColuna, char[][] tabuleiro, char iconePlayer){
        //linhaAndColuna[0] = linha
        //linhaAndColuna[1] = coluna

        //Preenche a posição do tabuleiro com o ícone do player 
        for(int i = 0; i < 3; i ++){
            for(int j = 0; j < 3; j ++){
                if(linhaAndColuna[0] == (i + 1) && linhaAndColuna[1] == (j + 1)){
                    tabuleiro[i][j] = iconePlayer;
                }
            }
        }
        //Mostra o tabuleiro após ser preenchido
        imprimirTabuleiro(tabuleiro);
        //Função para verificar se houve vendedor
        verificaVencedor(tabuleiro,iconePlayer);
    }
    
    //Função do player1
    public static void player1(char[][] tabuleiro){
    
        System.out.println("\n _Player_1");
        char iconePlayer1 = 'X'; //Ícone referente ao player 1

        imprimirTabuleiro(tabuleiro);
        int linha = leiaCoordenadaLinha();//Lê a linha do player1
        int coluna = leiaCoordenadaColuna();//Lê a coluna do player1
        boolean posicaoValida = posicaoValida(linha, coluna, tabuleiro, iconePlayer1);//Verifica se as posições são válidas

        while (posicaoValida == false) {//Enqunto for false, chama a função linha e coluna.Após isso, chama a posicaoValida para verificar se a posição é válida
            linha = leiaCoordenadaLinha();
            coluna = leiaCoordenadaColuna();
            posicaoValida = posicaoValida(linha, coluna, tabuleiro, iconePlayer1);
        }
        jogadaUsuario(posicaoValida,linha,coluna, tabuleiro, iconePlayer1);
    }

    public static void player2(char[][] tabuleiro){
        
        System.out.println("\n _Player_2");
        char iconePlayer2 = 'O';

        imprimirTabuleiro(tabuleiro);
        int linha = leiaCoordenadaLinha();
        int coluna = leiaCoordenadaColuna();
        boolean posicaoValida = posicaoValida(linha, coluna, tabuleiro, iconePlayer2);
        
        while (posicaoValida == false) { //Caso seja false, chama a função linha e coluna.Após isso, chama a posicaoValida para verificar novamente
            linha = leiaCoordenadaLinha();
            coluna = leiaCoordenadaColuna();
            posicaoValida = posicaoValida(linha, coluna, tabuleiro, iconePlayer2);
        }

         jogadaUsuario(posicaoValida,linha,coluna, tabuleiro, iconePlayer2);
    }

    public static void MachineEasy(char[][] tabuleiro) {
        Random random = new Random();
        System.out.println("\n_Maquina_amigavel");
        char iconMachine = 'O';

        boolean valido = false;
        int linha,coluna;
        imprimirTabuleiro(tabuleiro);
       
        while (valido == false) { 
            linha = random.nextInt(10);
            coluna = random.nextInt(10);
            if(posicaoValidaMaquina(linha, coluna, tabuleiro, iconMachine) == true){
                valido = true;
                jogadaUsuario(valido, linha, coluna, tabuleiro, iconMachine);
            }
        }
        System.err.println("A maquina jogou");
        imprimirTabuleiro(tabuleiro);
    }

    public static boolean verificaVencedor(char[][] tabuleiro, char iconePlayer){
        //player1 = X
        //player2 = O
        
        int permitionAddPoint1 = 0;
        int permitionAddPoint2 = 0;
        boolean reiniciaPartida = false;

        if(tabuleiro[0][0] == iconePlayer && tabuleiro[0][1] == iconePlayer && tabuleiro[0][2] == iconePlayer || tabuleiro[1][0] == iconePlayer && tabuleiro[1][1] == iconePlayer && tabuleiro[1][2] == iconePlayer || tabuleiro[2][0] == iconePlayer && tabuleiro[2][1] == iconePlayer && tabuleiro[2][2] == iconePlayer || tabuleiro[0][0] == iconePlayer && tabuleiro[1][1] == iconePlayer && tabuleiro[2][2] == iconePlayer){
            if(iconePlayer == 'X'){
                System.out.println("Player 1 +1 Point!");
                permitionAddPoint1++;

                imprimePontuacao(permitionAddPoint1, permitionAddPoint2);
                reiniciaPartida = true;
                reiniciaPartida(reiniciaPartida,tabuleiro);    
                //reiniciar o jogo
            }else if(iconePlayer == 'O'){
                System.out.println("Player 2 +1 Point!");
                permitionAddPoint2++;

                imprimePontuacao(permitionAddPoint1,permitionAddPoint2);
                reiniciaPartida = true;
                reiniciaPartida(reiniciaPartida, tabuleiro);
                //reiniciar o jogo
            }else{
                System.err.println("Error )=");
            }
        }

        return reiniciaPartida;
    }

    public static void imprimePontuacao(int permitionAddPoint1, int permitionAddPoint2){
        System.out.println("\n_Pontuação_");

        if(permitionAddPoint1 == 1){
             contadorPoints[0] = contadorPoints[0] + 1;
        }else if(permitionAddPoint2 == 1){
            contadorPoints[1] = contadorPoints[1] + 1;
            ;
        }else{
            System.err.println("Erro )=");
        }

        System.out.println("Player 1: " + contadorPoints[0]);
        System.out.println("Player 2: " + contadorPoints[1]);
    }

    //Reinicia a partida quando algum dos jogadores fizerem ponto 
    public static void reiniciaPartida(boolean reiniciaPartida, char[][] tabuleiro){
        System.out.println("\n_Partida_reiniciada_");

         if(reiniciaPartida == true){
            for(int i = 0; i < 3; i ++){
                for(int j = 0; j < 3; j++){
                    tabuleiro[i][j] = '_';
                }
            }
            modoJogador(tabuleiro);
         }
    }
}