package commands;

import utility.CollectionManager;
import utility.OrganizationAsker;
import exceptions.*;
import models.Coordinates;
import models.Person;
import models.Ticket;
import models.TicketType;

import java.time.LocalDate;

public class UpdateCommand extends AbstractCommand{
    CollectionManager collectionManager;
    OrganizationAsker organizationAsker;

    public UpdateCommand(CollectionManager collectionManager, OrganizationAsker organizationAsker){
        super("update", " ID - reload the value of the collection item");
        this.collectionManager = collectionManager;
        this.organizationAsker = organizationAsker;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new EmptyCollectionException();

            var id = Integer.parseInt(argument);
            var oldTicket = collectionManager.getById(id);
            if (oldTicket == null) throw new TicketNotFoundException();

            String name = oldTicket.getName();
            Coordinates coordinates = oldTicket.getCoordinates();
            LocalDate creationDate = oldTicket.getCreationDate();
            int price = Integer.parseInt(argument);
            oldTicket.getPrice();
            long discount = Long.parseLong(argument);
            oldTicket.getDiscount();
            Boolean refundable = Boolean.getBoolean(argument);
            oldTicket.getRefundable();
            TicketType type = oldTicket.getType();
            Person person = oldTicket.getPerson();

            collectionManager.removeFromCollection(oldTicket);

            collectionManager.addToCollection(new Ticket(
                    id,
                    name,
                    coordinates,
                    creationDate,
                    price,
                    discount,
                    refundable,
                    type,
                    person
            ));
            System.out.println("Ticket is changed");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            System.out.println("Used: '" + getName() + "'");
        } catch (EmptyCollectionException exception) {
            System.out.println("Collection is empty");
        } catch (NumberFormatException exception) {
            System.out.println("ID has to be a number");
        } catch (TicketNotFoundException exception) {
            System.out.println("Ticket with this ID doesn't exist");
            return false;
        }
        return false;
    }

}
//всё перепроверить