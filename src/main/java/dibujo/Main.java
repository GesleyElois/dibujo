package dibujo;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static dibujo.Options.CANVAS;

public class Main {

    private Canvas canvas;

    public void run(InputStream in, PrintStream out, PrintStream err) {
        try (Scanner scanner = new Scanner(in)) {
            out.print("\nenter command: ");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                try {
                    Options command = Options.getCommand(line);
                    canvas = command.draw(line, this.canvas);
                    canvas.draw(out);
                } catch (Exception ex) {
                    err.println(ex.getMessage()+"\n");
                    out.print("\nenter command: ");
                }
            }
        }
    }

    public static void main(String[] args) {
        new Main().run(System.in, System.out, System.err);
    }

}

