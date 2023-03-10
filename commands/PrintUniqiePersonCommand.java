package commands;

import exceptions.WrongArgumentException;
import models.Person;
import models.Ticket;
import utility.CollectionManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class PrintUniqiePersonCommand extends AbstractCommand {
    CollectionManager collectionManager;

    public PrintUniqiePersonCommand(CollectionManager collectionManager) {
        super("print", "output the unique values of the person field of all items in the collection");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {

        return false;
    }
}
//что=то сделать с правильным исполнением команды