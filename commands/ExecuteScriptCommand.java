package commands;

import utility.CollectionManager;
import exceptions.WrongAmountOfElementsException;

public class ExecuteScriptCommand extends AbstractCommand{
    public ExecuteScriptCommand(CollectionManager collectionManager){
        super("execute script", "read and execute the script from the specified file");
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongAmountOfElementsException();
            System.out.println("Do the script '" + argument + "'...");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            System.out.println("Used: '" + getName() + "'");
        }
        return false;
    }
}
