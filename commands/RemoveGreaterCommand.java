package commands;

import utility.CollectionManager;
import utility.Console;
import utility.ICommand;
import utility.OrganizationAsker;
import exceptions.*;
import models.Ticket;

public class RemoveGreaterCommand extends AbstractCommand implements ICommand {
    CollectionManager collectionManager;
    OrganizationAsker organizationAsker;

    public RemoveGreaterCommand(CollectionManager collectionManager, OrganizationAsker organizationAsker){
        super("remove greater", "remove all items from the collection that exceed the specified");
        this.collectionManager = collectionManager;
        this.organizationAsker = organizationAsker;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new EmptyCollectionException();
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
            Ticket ticketColl = collectionManager.getByValue(ticket);
            if (ticketColl == null) throw new TicketNotFoundException();
            collectionManager.removeGreater(ticketColl);
            Console.println("Солдаты успешно удалены!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Использование: '" + getName() + "'");
        } catch (EmptyCollectionException exception) {
            Console.printerror("Коллекция пуста!");
        } catch (TicketNotFoundException exception) {
            Console.printerror("Солдата с такими характеристиками в коллекции нет!");
        } catch (IncorrectScriptException exception) {}
        return false;
    }
}
//перепроверить все выводы, исполнение команд и подключенные методы