package work;

import utility.*;
import commands.*;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static final String PS1 = "#";
    public static final String PS2 = "*";

    public static void main(String[] args) throws IOException {

        try (Scanner userScanner = new Scanner(System.in)) {

            var organizationAsker = new OrganizationAsker(userScanner);
            var csvReader = new CSVReader();
            var collectionManager = new CollectionManager(organizationAsker, csvReader);
            var commandManager = new CommandManager(
                    new HelpCommand(),
                    new InfoCommand(collectionManager),
                    new ShowCommand(collectionManager),
                    new AddElementCommand(collectionManager, organizationAsker),
                    new UpdateCommand(collectionManager, organizationAsker),
                    new RemoveByIdCommand(collectionManager),
                    new ClearCommand(collectionManager),
                    new SaveCommand(collectionManager),
                    new ExecuteScriptCommand(collectionManager),
                    new ExitCommand(collectionManager),
                    new HeadCommand(collectionManager),
                    new AddIfMinCommand(collectionManager, organizationAsker),
                    new RemoveByIdCommand(collectionManager),
                    new AverageOfDiscountCommand(collectionManager),
                    new GroupCountingCommand(collectionManager),
                    new PrintUniqiePersonCommand(collectionManager)

            );
            var console = new Console(commandManager, userScanner, organizationAsker);

            console.interactiveMode();
        }

    }
}
//дописать все кманды из менеджера команд