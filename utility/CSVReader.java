package utility;

import models.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class CSVReader {



    String[] values;
    SimpleDateFormat parser = new SimpleDateFormat("HH:mm:ss");

    public void readCollection(Collection<Ticket> collection) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){



        /** try (
            BufferedReader reader = new BufferedReader(new FileReader("test.csv")))
            {
                String line;
            while ((line = reader.readLine()) != null) {
                int index = line.lastIndexOf(';');
                if (index == -1) {
                    System.err.println("File is empty");
                } else {
                    values = line.split(";");


                    int id = Integer.parseInt(values[0]);
                    String name = values[1];
                    Float x = Float.parseFloat(values[2]);
                    float y = Float.parseFloat(values[3]);
                    var coordinates = new Coordinates(x, y);
                    int price = Integer.parseInt(values[4]);
                    long discount = Long.parseLong(values[5]);
                    boolean refund = Boolean.getBoolean(values[6]);
                    TicketType type = TicketType.valueOf(values[7]);

                    Date birthday = parser.parse(values[8]);

                    Float height = Float.parseFloat(values[9]);
                    Float weight = Float.parseFloat(values[10]);
                    Country nationality = Country.valueOf(values[11]);

                    var person = new Person(birthday, height, weight, nationality);

                    LocalDate creationDate = LocalDate.parse(values[12]);
                    var ticket = new Ticket(id, name, coordinates, creationDate, price, discount, refund, type, person);
                }
            } */
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeCollection(Collection<Ticket> collection){
        final String NEW_LINE_SEPARATOR = "\n";

        List<Ticket> tickets = new ArrayList<Ticket>();
        BufferedWriter writer = null;
        //BufferedReader reader = null;
        CSVPrinter csvFilePrinter = null;

        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);

        try {
            writer = new BufferedWriter(new FileWriter("test.csv"));
            //reader = new BufferedReader(new InputStreamReader(System.in));

            csvFilePrinter = new CSVPrinter(writer, csvFileFormat);

            /** boolean text;
            while(!(text = reader.readLine().equals("ESC"))){
                writer.write(text + "\n");
                writer.flush();
            } */

            for (Ticket ticket : tickets) {
                List<Object> record = new ArrayList<>();

                record.add(ticket.getName());
                record.add(ticket.getId());
                record.add(ticket.getCreationDate());
                record.add(ticket.getCoordinates());
                record.add(ticket.getPrice());
                record.add(ticket.getDiscount());
                record.add(ticket.getRefundable());
                record.add(ticket.getType());
                record.add(ticket.getPerson());

                csvFilePrinter.printRecord(record);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                writer.flush();
                writer.close();
                //reader.close();
                csvFilePrinter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}



