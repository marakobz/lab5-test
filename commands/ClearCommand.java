package commands;

import exceptions.WrongAmountOfElementsException;
import models.Ticket;
import utility.CollectionManager;
import utility.Console;

import java.util.Hashtable;

public class ClearCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    public ClearCommand(CollectionManager collectionManager) {
     super("clear", "clear collection");
     this.collectionManager = collectionManager;
     }
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            collectionManager.clearCollection();
            Console.println("Коллекция очищена!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Использование: '" + getName() + "'");
        }
        return false;
    }

}