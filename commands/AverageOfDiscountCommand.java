package commands;

import models.Ticket;
import utility.CollectionManager;

import java.util.Iterator;

public class AverageOfDiscountCommand extends AbstractCommand {
    CollectionManager collectionManager;

    public AverageOfDiscountCommand(CollectionManager collectionManager){
        super("average of discount", "print the average value of the discount field for all items in the collection");
        this.collectionManager = collectionManager;
    }
    @Override
    public boolean execute(String argument){
        Iterator<Ticket> disc = collectionManager.getCollection().iterator();
        int sum = 0;
        int count = 0;
        while (disc.hasNext()){
            Ticket ticket = disc.next();
            sum += ticket.getDiscount();
            count += 1;
        }
        System.out.println("The average \"discount\" is: " + sum/count);
        return false;
    }
}
