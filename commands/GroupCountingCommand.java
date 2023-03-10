package commands;

import exceptions.WrongArgumentException;
import utility.CollectionManager;

public class GroupCountingCommand extends AbstractCommand{
    CollectionManager collectionManager;

    public GroupCountingCommand(CollectionManager collectionManager){
        super("count","group the elements of the collection by the value of the CreationDate field, output the number of elements in each group");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {
    try {
        if (!argument.isEmpty()) {
            throw new WrongArgumentException();
        }
    } catch (WrongArgumentException e) {
        System.out.println("Use: '" + getName() + "'");
    } catch (Exception e) {
        System.out.println("Mistake. Try again");
    }
        return false;
    }
}
//написать исполнение команды