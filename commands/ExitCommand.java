package commands;

import utility.CollectionManager;
import exceptions.WrongAmountOfElementsException;
import utility.Console;

public class ExitCommand extends AbstractCommand{

    public ExitCommand(CollectionManager collectionManager){
        super("exit", "end the program (without saving to a file)");
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Использование: '" + getName() + "'");
        }
        return false;
    }
}
