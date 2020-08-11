package dibujo;

import java.util.regex.Pattern;

public enum Options {

    CANVAS("C") {
        public void validator(String line) {
            if(!Pattern.compile("^C (\\d+) (\\d+)$").matcher(line).find()) {
                throw new RuntimeException("Invalid parameters for the create new canvas command. Should be: C <width> <height>");
            }
        }
    },
    LINE("L") {
        public void validator(String line) {
            if(!Pattern.compile("^L (\\d+) (\\d+) (\\d+) (\\d+)$").matcher(line).find()) {
                throw new RuntimeException("Invalid parameters for the create new line command. Should be: L <starting x> <starting y> <ending x> <ending y>");
            }
        }
    },
    RECTANGLE("R") {
        public void validator(String line) {
            if(!Pattern.compile("^R (\\d+) (\\d+) (\\d+) (\\d+)$").matcher(line).find()) {
                throw new RuntimeException("Invalid parameters for the create new rectangle command. Should be: L <upper left corner x> <upper left corner y> <lower right corner x> <lower right corner y>");
            }
        }
    },
    BUCKET_FILL("B") {
        public void validator(String line) {
            if(!Pattern.compile("^B (\\d+) (\\d+) (\\w+)$").matcher(line).find()){
                throw new RuntimeException("Invalid parameters for the bucket fill command. Should be: B <starting x> <starting y> <color>");
            }
        }
    },
    EXIT("Q");

    private String command;

    Options(String command) {
        this.command = command;
    }
}
