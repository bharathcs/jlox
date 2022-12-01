package jlox;

public class LoxError {
  public final int type;
  public final String message;
  public final int line;
  public final String description;

  public LoxError(int type, String message, int line) {
    this(type, message, line, null);
  }

  public LoxError(int type, String message, int line, String description) {
    this.type = type;
    this.message = message;
    this.line = line;
    this.description = description;
  }

  @Override
  public String toString() {
    return  String.format("line %5d", line) + type +
        ": " + message + '\n' + description;
  }
}
