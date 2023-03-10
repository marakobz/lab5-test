package utility;

import exceptions.IncorrectScriptException;
import exceptions.NoFileAccessException;
import exceptions.ScriptRecursionException;
import work.Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Console{

    private CommandManager commandManager;
    private Scanner userScanner;
    private OrganizationAsker organizationAsker;
    private List<String> scriptStack = new ArrayList<>();

    public Console(CommandManager commandManager, Scanner userScanner, OrganizationAsker organizationAsker) {
        this.commandManager = commandManager;
        this.userScanner = userScanner;
        this.organizationAsker = organizationAsker;
    }

    public void interactiveMode() {
        String[] userCommand = {"", ""};
        int commandStatus;
        try {
            do {
                Console.print(Main.PS1);
                userCommand = (userScanner.nextLine().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                commandStatus = launchCommand(userCommand);
            } while (commandStatus != 2);
        } catch (NoSuchElementException exception) {
            Console.printerror("Пользовательский ввод не обнаружен!");
        } catch (IllegalStateException exception) {
            Console.printerror("Непредвиденная ошибка!");
        }
    }

    public int scriptMode(String argument) {
        String[] userCommand = {"", ""};
        int commandStatus;
        scriptStack.add(argument);
        try (Scanner scriptScanner = new Scanner(new File(argument))) {
            if (!scriptScanner.hasNext()) throw new NoSuchElementException();
            Scanner tmpScanner = organizationAsker.getUserScanner();
            organizationAsker.setUserScanner(scriptScanner);
            organizationAsker.setFileMode();
            do {
                userCommand = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                while (scriptScanner.hasNextLine() && userCommand[0].isEmpty()) {
                    userCommand = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                    userCommand[1] = userCommand[1].trim();
                }
                Console.println(Main.PS1 + String.join(" ", userCommand));
                if (userCommand[0].equals("execute_script")) {
                    for (String script : scriptStack) {
                        if (userCommand[1].equals(script)) throw new ScriptRecursionException();
                    }
                }
                commandStatus = launchCommand(userCommand);
            } while (commandStatus == 0 && scriptScanner.hasNextLine());
            organizationAsker.setUserScanner(tmpScanner);
            organizationAsker.setUserScanner(userScanner);
            if (commandStatus == 1 && !(userCommand[0].equals("execute_script") && !userCommand[1].isEmpty()))
                Console.println("Проверьте скрипт на корректность введенных данных!");
            return commandStatus;
        } catch (FileNotFoundException exception) {
            Console.printerror("Файл со скриптом не найден!");
        } catch (NoSuchElementException exception) {
            Console.printerror("Файл со скриптом пуст!");
        } catch (ScriptRecursionException exception) {
            Console.printerror("Скрипты не могут вызываться рекурсивно!");
        } catch (IllegalStateException exception) {
            Console.printerror("Непредвиденная ошибка!");
            System.exit(0);
        } finally {
            scriptStack.remove(scriptStack.size()-1);
        }
        return 1;
    }

    private int launchCommand(String[] userCommand) {
        switch (userCommand[0]) {
            case "":
                break;
            case "help":
                if (!commandManager.help(userCommand[1])) return 1;
                break;
            case "info":
                if (!commandManager.info(userCommand[1])) return 1;
                break;
            case "show":
                if (!commandManager.show(userCommand[1])) return 1;
                break;
            case "add":
                if (!commandManager.addElement(userCommand[1])) return 1;
                break;
            case "update":
                if (!commandManager.update(userCommand[1])) return 1;
                break;
            case "clear":
                if (!commandManager.clear(userCommand[1])) return 1;
                break;
            case "remove_by_id":
                if (!commandManager.remove(userCommand[1])) return 1;
                break;
            case "save":
                if (!commandManager.save(userCommand[1])) return 1;
                break;
            case "execute_script":
                if (!commandManager.executeScipt(userCommand[1])) return 1;
                else return scriptMode(userCommand[1]);
            case "exit":
                if (!commandManager.exit(userCommand[1])) return 1;
                break;
            case "head":
                if (!commandManager.head(userCommand[1])) return 1;
                break;
            case "add_if_min":
                if (!commandManager.addmin(userCommand[1])) return 1;
                break;
            case "remove_greater":
                if (!commandManager.removeGreater(userCommand[1])) return 1;
                break;
            case "average_of_discount":
                if (commandManager.averageDis(userCommand[1])) return 1;
                break;
            case "group_counting":
                if (!commandManager.groupCount(userCommand[1])) return 1;
                break;
            case "print_unique_person":
                if (!commandManager.print(userCommand[1])) return 1;
                else return 2;
            default:
                if (!commandManager.noSuchCommand(userCommand[0])) return 1;
        }
        return 0;
    }

    public static void print(Object toOut) {
        System.out.print(toOut);
    }

    public static void println(Object toOut) {
        System.out.println(toOut);
    }

    public static void printerror(Object toOut) {
        System.out.println("error: " + toOut);
    }

    public static void printtable(Object element1, Object element2) {
        System.out.printf(element1.toString(), element2);
    }

    @Override
    public String toString() {
        return "Console (класс для обработки ввода команд)";
    }
    // разобраться с тем как нормально строится консоль
// дописать команды для консоли
    // проверить работоспособность всего

}
