package day3.part2;

public class Slope {

    private int moveDown;
    private int moveRight;

    public Slope(int moveDown, int moveRight) {
        this.moveDown = moveDown;
        this.moveRight = moveRight;
    }

    public int getMoveDown() {
        return moveDown;
    }

    public int getMoveRight() {
        return moveRight;
    }
}
