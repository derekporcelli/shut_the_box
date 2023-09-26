import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Game
    {
        private Board board;
        
        public Game (int numberOfTiles, int numberOfDice)
            {
                board = new Board(numberOfTiles, numberOfDice);
            }
        
        public Board getBoard ()
            {
                return board;
            }
        
        public boolean recursionIsStalemate (ArrayList<Tile> tiles, int value)
            {
                if(tiles.size() == 0 || value <= 0){
                    return true;
                }
                for (Tile tile : tiles)
                    {
                        if (tile.getValue() == value)
                            {
                                return false;
                            }
                    }
                for (Tile tile : tiles)
                    {
                        ArrayList<Tile> newTiles = new ArrayList<Tile>();
                        for (int i = 0; i < tiles.size(); i++)
                            {
                                if (tiles.get(i) != tile)
                                    {
                                        newTiles.add(tiles.get(i));
                                    }
                            }
                        if (!recursionIsStalemate(newTiles, value - tile.getValue()))
                            {
                                return false;
                            }
                    }
                return true;
            }
        
        public static void main (String[] args)
            {
                Game game = new Game(10, 2);
                Board board = game.getBoard();
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Welcome to Shut the Box!");
                
                boolean lastTurnWasValid = false;
                while (true)
                    {
                        if (lastTurnWasValid)
                            {
                                board.rollDie();
                            }
                        int value = board.getValue();
                        System.out.println("\nRolled a " + value);
    
                        for (int i = 0; i < board.getTiles().length; i++)
                            {
                                Tile tile = board.getTiles()[i];
                                int tileValue = tile.getValue();
                                if (tile.isOpen())
                                    {
                                        System.out.print(tileValue + " ");
                                    }
                                else
                                    {
                                        System.out.print("X ");
                                    }
                            }
                        System.out.println();
                        
                        ArrayList<Tile> newTiles = new ArrayList<Tile>();
                        for (int i = 0; i < board.getTiles().length; i++)
                            {
                                if(board.getTiles()[i].isOpen())
                                    {
                                        newTiles.add(board.getTiles()[i]);
                                    }
                            }
                        if(game.recursionIsStalemate(newTiles, board.getValue()))
                            {
                                System.out.println("Game over!");
                                break;
                            }
                        if(board.isShut()){
                            System.out.println("You shut the box!");
                            break;
                        }
                        
                        System.out.println("Which tiles do you want to shut?");
                        try
                            {
                                String input = reader.readLine();
                                String[] inputArray = input.split(" ");
                                int inputArrayValue = 0;
                                for (int i = 0; i < inputArray.length; i++)
                                    {
                                        inputArrayValue += Integer.parseInt(inputArray[i]);
                                    }
                                if (inputArrayValue == value)
                                    {
                                        lastTurnWasValid = true;
                                    }
                                else
                                    {
                                        throw new Exception();
                                    }
                                for(int i = 0; i < inputArray.length; i++){
                                    if(!board.getTiles()[Integer.parseInt(inputArray[i])-1].isOpen()){
                                        throw new Exception();
                                    }
                                }
                                for (int i = 0; i < inputArray.length; i++)
                                    {
                                        int tileNumber = Integer.parseInt(inputArray[i]);
                                        Tile tile = board.getTiles()[tileNumber - 1];
                                        tile.shut();
                                    }
                            }
                        catch (Exception e)
                            {
                                lastTurnWasValid = false;
                                System.out.println("Invalid input");
                            }
                    }
                System.out.println("Thanks for playing!");
            }
    }
