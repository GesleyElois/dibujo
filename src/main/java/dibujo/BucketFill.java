package dibujo;

public class BucketFill {

    private Position positionFill;
    private String fillColor;

    public BucketFill(Position positionToFill, String fillColor) {
        this.positionFill = positionToFill;
        this.fillColor = fillColor;
    }

    public Position getPositionFill() {
        return positionFill;
    }

    public String getFillColor() {
        return fillColor;
    }
}
