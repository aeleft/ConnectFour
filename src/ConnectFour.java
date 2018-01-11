import java.util.Scanner;

public class ConnectFour{
    
    public static int[][] board;
    
    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);
        
        //initialize board
        board = new int[6][7];
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 7; j++){
                board[i][j] = 0;
            }
        }
        
        int numMoves = 0;
        int winner = 0;
        int column;
        
        while(numMoves < 42){
            
            printBoard();
            
            if(numMoves % 2 == 0){
                System.out.println("It is Player 1's turn. Enter column number:");
                column = sc.nextInt();
                if(!playerMove(1, column)){
                    System.out.println("Not a valid move.");
                    continue;
                }
            }
            else{
                System.out.println("It is Player 2's turn. Enter column number:");
                column = sc.nextInt();
                if(!playerMove(-1, column)){
                    System.out.println("Not a valid move.");
                    continue;
                }
            }
            
            winner = checkWinner();
            if(winner != 0) break;
            
            numMoves++;
        }
        
        printBoard();
        
        if(winner == 1)
            System.out.println("Player 1 wins!");
        else if (winner == -1)
            System.out.println("Player 2 wins!");
        else
            System.out.println("It is a draw");
        
        sc.close();
    }
    
    
    public static void printBoard(){
        
        for(int i = 0; i < 6; i++){
            
            for(int j = 0; j < 7; j++){
                
                if(board[i][j] == 1)
                    System.out.print("X ");
                else if(board[i][j] == -1)
                    System.out.print("O ");
                else
                    System.out.print(". ");
                
            }
            System.out.println();
        }
        
    }
    
    
    public static int checkWinner(){
        
        int winner = 0;
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 7; j++){
                winner = checkWinner(i, j);
                if(winner != 0) return winner;
            }
        }
        return 0;
    }
    
    
    // checks if there are connected 4 pieces with the first piece
    // being at the location x, y
    public static int checkWinner(int x, int y){
        
        int count = 0;
        for(int i = 0; i < 4 && y+i < 7; i++)
            count += board[x][y+i];
        if(count == 4) return 1;
        if(count == -4) return -1;
        
        count = 0;
        for(int i = 0; i < 4 && x+i < 6; i++)
            count += board[x+i][y];
        if(count == 4) return 1;
        if(count == -4) return -1;
        
        count = 0;
        for(int i = 0; i < 4 && x+i < 6 && y+i < 7; i++)
            count += board[x+i][y+i];
        if(count == 4) return 1;
        if(count == -4) return -1;
        
        count = 0;
        for(int i = 0; i < 4 && x+i < 6 && y-i >= 0; i++)
            count += board[x+i][y-i];
        if(count == 4) return 1;
        if(count == -4) return -1;
        
        return 0;
    }
    
    
    // if the move is not valid, returns false.
    // otherwise updates the board and returns true.
    public static boolean playerMove(int player, int columnNum){
        
        if(columnNum < 0 || columnNum > 6) return false;
        if(board[0][columnNum] != 0) return false;
        
        for(int i = 5; i >= 0; i--){   
            if(board[i][columnNum] == 0){
                board[i][columnNum] = player;
                return true;
            }
        }
      
        return true;
        
    }
    
}