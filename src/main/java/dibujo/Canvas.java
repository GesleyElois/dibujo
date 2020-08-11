package dibujo;

import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;

public class Canvas {

    private int width;
    private int height;

    private Position[][] positions;

    public Canvas(int width, int height) {
        this.width = width;
        this.height = height;

        if (width <= 0 || height <= 0) {
            throw new RuntimeException("Invalid parameters: the width and height of the canvas should be greater than zero. Given parameters: " + this);
        }

        this.positions = new Position[this.height][this.width];

        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                this.positions[y][x] = new Position(x+1, y+1);
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Canvas [width=" + width + ", height=" + height + "]";
    }

    public Position getPosition(int x, int y) {
        return positions[y-1][x-1];
    }

    public void draw(PrintStream out) {
        out.println();

        for (int i = 0; i <= this.getWidth(); i++) {
            out.print("-");
        }
        out.print("-");

        out.println();

        int x = 1, y = 1;
        while (x >= 1 && x <= this.width && y >= 1 && y <= this.height) {
            Position position = this.getPosition(x, y);
            if (position.getX() == 1) {
                if (position.getY() > 1) {
                    out.println("|");
                }
                out.print("|");
            }

            if (position.getColor() != null) {
                out.print(position.getColor());
            } else if (position.isFilled()) {
                out.print("x");
            } else {
                out.print(" ");
            }
            x++;
            if (x >= this.width + 1) {
                x = 1;
                y++;
            }
        }

        out.println("|");
        out.print("-");

        for (int i = 0; i < this.width; i++) {
            out.print("-");
        }
        out.print("-");

        out.println();

        out.print("\nenter command: ");
    }

    public void createNewLine(Line line) {
        int startingX = line.getStartingX();
        int startingY = line.getStartingY();
        int endingX = line.getEndingX();
        int endingY = line.getEndingY();

        if (startingX > width ||  endingX > width || startingY > height || endingY > height) {
            throw new RuntimeException("Invalid parameters: the line coordinates should not be off limits. Given parameters: " + this + " starting(X=" + startingX + ", Y=" + startingY + ") ending(X="+endingX+ ", Y=" + endingY + ")");
        }

        for (int y = startingY-1; y <= endingY-1; y++) {
            for (int x = startingX-1; x <= endingX-1; x++) {
                this.positions[y][x].fill();
            }
        }
    }

    public void createNewRectangle(Rectangle rectangle) {
        int upperLeftCornerY = rectangle.getUpperLeftCornerY();
        int upperLeftCornerX = rectangle.getUpperLeftCornerX();
        int lowerRightCornerX = rectangle.getLowerRightCornerX();
        int lowerRightCornerY = rectangle.getLowerRightCornerY();

        if (upperLeftCornerX > width || lowerRightCornerX > width || upperLeftCornerY > height || lowerRightCornerY > height) {
            throw new RuntimeException("Invalid parameters: the rectangle coordinates should not be off limits. Given parameters: " + this + " upperLeftCorner(X=" + upperLeftCornerX + ", Y=" + upperLeftCornerY + " lowerRightCorner(X=" + lowerRightCornerX + ",Y=" + lowerRightCornerY + ")");
        }

        for (int y = upperLeftCornerY-1; y <= lowerRightCornerY-1; y++) {
            for (int x = upperLeftCornerX-1; x <= lowerRightCornerX-1; x++) {
                int otherX = x+1;
                int otherY = y+1;

                if ((otherY == upperLeftCornerY&& (otherX >= upperLeftCornerX && otherX <= lowerRightCornerX)) ||
                        (otherY == lowerRightCornerY && (otherX >= upperLeftCornerX && otherX <= lowerRightCornerX)) ||
                        (otherX == upperLeftCornerX && (otherY >= upperLeftCornerY && otherY <= lowerRightCornerY)) ||
                        (otherX == lowerRightCornerX && (otherY >= upperLeftCornerY && otherY <= lowerRightCornerY))) {
                    this.positions[y][x].fill();
                }
            }
        }
    }

    public void fill(Position positionToFill, String fillColor) {
        fill(positionToFill, fillColor, new HashSet<>());
    }

    private void fill(Position positionToFill, String fillColor, Set<Position> visited) {
        if (visited.contains(positionToFill) || positionToFill.getX() <= 0 || positionToFill.getX() > this.width || positionToFill.getY() <= 0 || positionToFill.getY() > this.height) {
            return;
        }

        Position canvasPosition = positions[positionToFill.getY()-1][positionToFill.getX()-1];
        if (canvasPosition.isFilled()) {
            return;
        }
        canvasPosition.changeColor(fillColor);
        visited.add(positionToFill);

        fill(positionToFill.leftUp(), fillColor, visited);
        fill(positionToFill.centerUp(), fillColor, visited);
        fill(positionToFill.rightUp(), fillColor, visited);

        fill(positionToFill.right(), fillColor, visited);
        fill(positionToFill.rightDown(), fillColor, visited);

        fill(positionToFill.centerDown(), fillColor, visited);

        fill(positionToFill.leftDown(), fillColor, visited);
        fill(positionToFill.left(), fillColor, visited);
    }

}
