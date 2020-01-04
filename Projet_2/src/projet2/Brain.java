package dicewar;

public interface Brain {
    
    public static class Move {
        
        public double score;    // lower scores are better
    }

    public Brain.Move bestMove();
}
