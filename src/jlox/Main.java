package jlox;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        if (args.length > 1) {
          System.err.println("Usage of jlox: jlox [path/to/script.lox]");
          System.exit(1);
          return;
        }

        BufferedReader br;
        boolean isPrompt = args.length == 0;
        if (isPrompt) {
          br = new BufferedReader(new InputStreamReader(System.in));
        } else {
          File script = new File(args[0]);
          try {
            FileReader scanner = new FileReader(script);
            br = new BufferedReader(scanner);
          } catch (FileNotFoundException e) {
            System.err.printf("Script %s could not be found\n", args[0]);
            System.exit(1);
            return;
          }
        }

        Lox lox = new Lox();

        try {
          String line;
          LoxError err = null;

          if (isPrompt) System.out.print("> ");
          while (err == null && (line = br.readLine()) != null) {
            err = lox.run(line);
            if (isPrompt) System.out.print("> ");
          }

          if (err != null) {
            System.err.println(err);
            System.exit(1);
          } else {
            System.out.println("Lox shutting down.");
            System.exit(0);
          }
        } catch (IOException e) {
          e.printStackTrace();
        }
    }
}
