package commands;

import exceptions.WrongArgumentException;
import utility.CollectionManager;
import exceptions.EmptyCollectionException;
import exceptions.TicketNotFoundException;
import models.Ticket;

public class RemoveByIdCommand extends AbstractCommand{
    CollectionManager collectionManager;

    public RemoveByIdCommand(CollectionManager collectionManager){
        super("remove by id","delete an item from the collection by its id");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongArgumentException();
            if (collectionManager.collectionSize() == 0) throw new EmptyCollectionException();
            int id = Integer.parseInt(argument);
            Ticket ticketToremove = collectionManager.getById(id);
            if (ticketToremove == null) throw new TicketNotFoundException();
            collectionManager.removeFromCollection(ticketToremove);
            System.out.println("Ticket is deleted");

        } catch (WrongArgumentException exception) {
            System.out.println("Used: '" + getName() + "'");
        } catch (EmptyCollectionException exception) {
            System.out.println("Collection is empty");
        } catch (NumberFormatException exception) {
            System.out.println("ID has to be a number");
        } catch (TicketNotFoundException exception) {
            System.out.println("Ticket with this ID doesn't exist");
        }
        return false;
    }

}
//перепроверить корректность работы