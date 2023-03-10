package commands;

import exceptions.WrongArgumentException;
import utility.CollectionManager;
import utility.Console;

public class HeadCommand extends AbstractCommand {
    CollectionManager collectionManager;

    public HeadCommand(CollectionManager collectionManager) {
        super("head", " show first element of collection");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {
            if (!argument.isEmpty()) {
                Console.println("Use: '" + getName() + "'");
                return false;
            }

            if (collectionManager.getCollection().isEmpty()) {
                Console.println("Collection is empty");
            } else {
                Console.println(collectionManager.getFirst());
            }
            return true;
        }
}
//написать исполнение команды