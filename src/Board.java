public class Board
    {
        private Tile[] tiles;
        private Dice[] die;
        private int numberOfTiles;
        private int numberOfDice;
        private int value;
        
        public Board(int numberOfTiles, int numberOfDice)
        {
            this.numberOfTiles = numberOfTiles;
            this.numberOfDice = numberOfDice;
            
            this.tiles = new Tile[this.numberOfTiles];
            this.die = new Dice[this.numberOfDice];
            
            for (int i = 0; i < this.numberOfTiles; i++)
            {
                tiles[i] = new Tile(i + 1);
            }
            for (int i = 0; i < this.numberOfDice; i++)
            {
                Dice dice = new Dice();
                value += dice.getValue();
                die[i] = dice;
            }
        }
        
        public int getValue()
        {
            return value;
        }
        
        public Tile[] getTiles()
        {
            return tiles;
        }
        
        public void rollDie()
        {
            value = 0;
            for (int i = 0; i < numberOfDice; i++)
            {
                Dice dice = die[i];
                die[i].roll();
                value += dice.getValue();
            }
        }
        
        public boolean isShut()
        {
            for (int i = 0; i < numberOfTiles; i++)
            {
                if (tiles[i].isOpen())
                {
                    return false;
                }
            }
            return true;
        }
    }
