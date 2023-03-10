package commands;

import utility.CollectionManager;
import utility.Console;
import utility.OrganizationAsker;
import exceptions.IncorrectScriptException;
import exceptions.WrongAmountOfElementsException;
import models.Ticket;

public class AddIfMinCommand extends AbstractCommand {
    CollectionManager collectionManager;
    OrganizationAsker organizationAsker;

    public AddIfMinCommand(CollectionManager collectionManager, OrganizationAsker organizationAsker) {
        super("add min element", "add a new item to the collection if its value is less than that of the smallest item in this collection");
        this.organizationAsker = organizationAsker;
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            var ticket = new Ticket(
                    collectionManager.generateNextId(),
                    organizationAsker.askName(),
                    organizationAsker.askCoordinates(),
                    organizationAsker.askPrice(),
                    organizationAsker.askDiscount(),
                    organizationAsker.askRefund(),
                    organizationAsker.askTicketType(),
                    organizationAsker.askPerson()
            );
            if (collectionManager.collectionSize() == 0 || ticket.compareTo(collectionManager.getFirst()) < 0) {
                collectionManager.addToCollection(ticket);
                Console.println("Ticket is added successfully");
                return true;
            } else
                Console.printerror("The value of the ticket is greater than the value of the smallest of the tickets");
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Used: '" + getName() + "'");
        } catch (IncorrectScriptException exception) {
        }
        return false;
    }
}
