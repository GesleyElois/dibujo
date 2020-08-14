package dibujo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public enum Options {

    CANVAS("C") {
         private Matcher isValid(String line) {
            Matcher matcher = Pattern.compile("^C (\\d+) (\\d+)$").matcher(line);

            if(!matcher.find()) {
                throw new RuntimeException("Invalid parameters for the create new canvas command. Should be: C <width> <height>");
            }

            return matcher;
        }

        public Canvas draw(String line, Canvas canvas) {
            Matcher matcher = isValid(line);

            int width = Integer.parseInt(matcher.group(1));
            int height = Integer.parseInt(matcher.group(2));

            return new Canvas(width, height);
        }
    },
    LINE("L") {
        private Matcher isValid(String line) {
            Matcher matcher = Pattern.compile("^L (\\d+) (\\d+) (\\d+) (\\d+)$").matcher(line);

            if(!matcher.find()) {
                throw new RuntimeException("Invalid parameters for the create new line command. Should be: L <starting x> <starting y> <ending x> <ending y>");
            }

            return matcher;
        }

        public Canvas draw(String line, Canvas canvas) {
            Matcher matcher = isValid(line);

            int startingX = Integer.parseInt(matcher.group(1));
            int startingY = Integer.parseInt(matcher.group(2));
            int endingX = Integer.parseInt(matcher.group(3));
            int endingY = Integer.parseInt(matcher.group(4));

            canvas.createNewLine(new Line(startingX, startingY, endingX, endingY));

            return canvas;
        }
    },
    RECTANGLE("R") {
        private Matcher isValid(String line) {
            Matcher matcher = Pattern.compile("^R (\\d+) (\\d+) (\\d+) (\\d+)$").matcher(line);

            if(!matcher.find()) {
                throw new RuntimeException("Invalid parameters for the create new rectangle command. Should be: L <upper left corner x> <upper left corner y> <lower right corner x> <lower right corner y>");
            }

            return matcher;
        }

        public Canvas draw(String line, Canvas canvas) {
            Matcher matcher = isValid(line);


            int upperLeftCornerX = Integer.parseInt(matcher.group(1));
            int upperLeftCornerY = Integer.parseInt(matcher.group(2));
            int lowerRightCornerX = Integer.parseInt(matcher.group(3));
            int lowerRightCornerY = Integer.parseInt(matcher.group(4));

            canvas.createNewRectangle(new Rectangle(upperLeftCornerX, upperLeftCornerY, lowerRightCornerX, lowerRightCornerY));

            return canvas;
        }
    },
    BUCKET_FILL("B") {
        private Matcher isValid(String line) {
            Matcher matcher = Pattern.compile("^B (\\d+) (\\d+) (\\w+)$").matcher(line);

            if(!matcher.find()){
                throw new RuntimeException("Invalid parameters for the bucket fill command. Should be: B <starting x> <starting y> <color>");
            }

            return matcher;
        }

        public Canvas draw(String line, Canvas canvas) {
            Matcher matcher = isValid(line);

            int startingX = Integer.parseInt(matcher.group(1));
            int startingY = Integer.parseInt(matcher.group(2));
            String colorCharacter = matcher.group(3);

            Position startingPositionToFill = new Position(startingX, startingY);

            canvas.fill(new BucketFill(startingPositionToFill, colorCharacter));

            return canvas;
        }
    },
    EXIT("Q") {
        public Canvas draw(String line, Canvas canvas) {
            canvas.quit();
            return canvas;
        }
    };

    private String command;

    Options(String command) {
        this.command = command;
    }

    private String getCommand() {
        return this.command;
    }

    public static Options getCommand(String line) {
        return Stream.of(values())
                .filter(option -> line.startsWith(option.getCommand()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Invalid command: " + line));
    }

    abstract Canvas draw(String line, Canvas canvas);
}
