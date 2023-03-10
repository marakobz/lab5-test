package commands;

import models.Ticket;
import utility.CollectionManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Hashtable;

public class InfoCommand extends AbstractCommand{
    private LocalDate creationDate;
    private CollectionManager collectionManager;

        public InfoCommand(CollectionManager collectionManager) {
        super("info", "shows information about the commands");
        this.collectionManager = collectionManager;
        creationDate = LocalDate.now();
    }

    public boolean execute(String argument){
        System.out.println(
                "Информация о коллекции:"
                        + "  Тип: Priority Queue <Ticket>\n"
                        + "  Дата создания:" + creationDate + " \n"
                        + "  Количество элементов:" + collectionManager.collectionSize()
        );
        return false;
    }

    @Override
    public String toString() {
        return "info";
    }
}

//всё перепроверить