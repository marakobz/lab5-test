package utility;

public interface ICommand {
    String getDescripition();

    String getName();

    boolean execute(String argument);
}