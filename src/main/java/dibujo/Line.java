package dibujo;

public class Line {

    private int startingX;
    private int startingY;
    private int endingX;
    private int endingY;

    public Line(int startingX, int startingY, int endingX, int endingY) {
        if (startingX <= 0 || startingY <= 0 || endingX <= 0 || endingY <= 0) {
            throw new RuntimeException("Invalid parameters: the starting and ending coordinates should be greater than zero. Given parameters: " + this);
        }

        if (startingX != endingX && startingY != endingY ) {
            throw new RuntimeException("Invalid parameters: currently only horizontal or vertical lines are supported. Given parameters: " + this);
        }

        this.startingX = startingX;
        this.startingY = startingY;
        this.endingX = endingX;
        this.endingY = endingY;
    }

    public int getStartingX() {
        return startingX;
    }

    public int getStartingY() {
        return startingY;
    }

    public int getEndingX() {
        return endingX;
    }

    public int getEndingY() {
        return endingY;
    }
}
