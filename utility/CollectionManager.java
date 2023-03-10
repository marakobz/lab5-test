package utility;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeSet;

import models.Ticket;

public class CollectionManager {
    private Queue<Ticket> collection = new PriorityQueue<Ticket>();
    private LocalDateTime creationDate;
    private OrganizationAsker organizationAsker;
    private CSVReader csvReader;


    public CollectionManager(OrganizationAsker organizationAsker, CSVReader csvReader) throws IOException {
        this.creationDate = null;
        this.csvReader = csvReader;
        this.organizationAsker = organizationAsker;

        loadCollection();
    }

    public Queue<Ticket> getCollection(){
        return collection;
    }

    public LocalDateTime getTime(){
        return creationDate;
    }

    public String collectionType(){
        return collection.getClass().getName();
    }

    public int collectionSize(){
        return collection.size();
    }

   public Ticket getFirst(){
        if (collection.isEmpty()) return null;
        return collection.peek();
    }

    public Ticket getLast(){
        if(collection.isEmpty()) return null;
        return collection.poll();
    }

    public Ticket getById(int id) {
        for (Ticket element : collection) {
            if (element.getId() == id) return element;
        }
        return null;
    }

    public Ticket getByValue(Ticket ticket){
        for (Ticket tickett : collection){
            if (tickett.equals(ticket)) return tickett;
        }
        return null;
    }
    public void removeGreater(Ticket ticketToCompare) {
        collection.removeIf(marine -> marine.compareTo(ticketToCompare) > 0);
    }
    public void addToCollection(Ticket element) {
        collection.add(element);
    }

    public void removeFromCollection(Ticket element) {
        collection.remove(element);
    }


    public int generateNextId() {
        if (collection.isEmpty()) return 1;
        return collection.poll().getId() + 1;
    }

    public void saveCollection(){
        csvReader.writeCollection(collection);
    }

    public void loadCollection() throws IOException {
        csvReader.readCollection(collection);
    }


    public void clearCollection() {
        collection.clear();
    }

    @Override
    public String toString() {
        if (collection.isEmpty()) return "There is nothing in collection";
        var last = getLast();

       String info = "";
       for (Ticket ticket : collection){
           info+= ticket;
           if (ticket!= collection.poll()) info += "\n\n";
       }
        return info;
    }
}
//доразобраться с записью коллекций