public class Tile
    {
        private boolean open;
        private int value;
        
        public Tile(int value)
        {
            open = true;
            this.value = value;
        }
        
        public boolean isOpen()
        {
            return open;
        }
        
        public void shut()
        {
            open = false;
        }
        
        public int getValue()
        {
            return value;
        }
        
        public String toString()
        {
            return "Tile " + value + " is " + (open ? "open" : "shut");
        }
    }
