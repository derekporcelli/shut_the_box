import java.util.Random;

public class Dice
    {
        private Random random;
        private int value;
        
        public Dice()
        {
            random = new Random();
            value = roll();
        }
        
        public int roll()
        {
            value = random.nextInt(6) + 1;
            return value;
        }
        
        public int getValue()
        {
            return value;
        }
    }
