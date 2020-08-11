package dibujo;

public class Rectangle {
    private int upperLeftCornerY;
    private int upperLeftCornerX;
    private int lowerRightCornerX;
    private int lowerRightCornerY;

    public Rectangle(int upperLeftCornerX, int upperLeftCornerY, int lowerRightCornerX, int lowerRightCornerY) {
        if (upperLeftCornerX <= 0 || upperLeftCornerY <= 0 || lowerRightCornerX <= 0 || lowerRightCornerY <= 0) {
            throw new RuntimeException("Invalid parameters: the upper left corner and lower right corner coordinates should be greater than zero. Given parameters: " + this);
        }

        this.upperLeftCornerX = upperLeftCornerX;
        this.upperLeftCornerY = upperLeftCornerY;
        this.lowerRightCornerX = lowerRightCornerX;
        this.lowerRightCornerY = lowerRightCornerY;
    }

    public int getUpperLeftCornerY() {
        return upperLeftCornerY;
    }

    public int getUpperLeftCornerX() {
        return upperLeftCornerX;
    }

    public int getLowerRightCornerX() {
        return lowerRightCornerX;
    }

    public int getLowerRightCornerY() {
        return lowerRightCornerY;
    }
}
