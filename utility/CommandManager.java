package utility;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {
    private List<ICommand> commands = new ArrayList<>();
    private ICommand helpCommand;
    private ICommand infoCommand;
    private ICommand showCommand;
    private ICommand addElementCommand;
    private ICommand updateID;
    private ICommand removeByIdCommand;
    private ICommand clearCommand;
    private ICommand saveCommand;
    private ICommand executeScriptCommand;
    private ICommand exitCommand;
    private ICommand headCommand;
    private ICommand addIfMinCommand;
    private ICommand removeGreaterCommand;
    private ICommand averageOfDiscountCommand;
    private ICommand groupCountingCommand;
    private ICommand printUniqiePersonCommand;


        public CommandManager(ICommand helpCommand,
                              ICommand infoCommand,
                              ICommand showCommand,
                              ICommand addElementCommand,
                              ICommand updateID,
                              ICommand removeByIdCommand,
                              ICommand clearCommand,
                              ICommand saveCommand,
                              ICommand executeScriptCommand,
                              ICommand exitCommand,
                              ICommand headCommand,
                              ICommand addIfMinCommand,
                              ICommand removeGreaterCommand,
                              ICommand averageOfDiscountCommand,
                              ICommand groupCountingCommand,
                              ICommand printUniqiePersonCommand
                              ) {
        this.helpCommand = helpCommand;
        this.infoCommand = infoCommand;
        this.showCommand = showCommand;
        this.addElementCommand = addElementCommand;
        this.updateID = updateID;
        this.removeByIdCommand = removeByIdCommand;
        this.clearCommand = clearCommand;
        this.saveCommand = saveCommand;
        this.executeScriptCommand = executeScriptCommand;
        this.exitCommand = exitCommand;
        this.headCommand = headCommand;
        this.addIfMinCommand = addIfMinCommand;
        this.removeGreaterCommand = removeGreaterCommand;
        this.averageOfDiscountCommand = averageOfDiscountCommand;
        this.groupCountingCommand = groupCountingCommand;
        this.printUniqiePersonCommand = printUniqiePersonCommand;

        commands.add(helpCommand);
        commands.add(infoCommand);
        commands.add(showCommand);
        commands.add(addElementCommand);
        commands.add(updateID);
        commands.add(clearCommand);
        commands.add(removeByIdCommand);
        commands.add(saveCommand);
        commands.add(executeScriptCommand);
        commands.add(exitCommand);
        commands.add(headCommand);
        commands.add(addIfMinCommand);
        commands.add(removeGreaterCommand);
        commands.add(averageOfDiscountCommand);
        commands.add(groupCountingCommand);
        commands.add(printUniqiePersonCommand);
    }

    public boolean help(String argument) {
        if (helpCommand.execute(argument)) {
            for (ICommand command : commands) {
                Console.printtable(command.getName(), command.getDescripition());
            }
            return true;
        } else return false;
    }


    public boolean info(String argument){
        return infoCommand.execute(argument);
    }

    public boolean show(String argument){
        return showCommand.execute(argument);
    }

    public boolean addElement(String argument){
        return addElementCommand.execute(argument);
    }

    public boolean update(String argument){
        return updateID.execute(argument);
    }

    public boolean clear(String argument){
        return clearCommand.execute(argument);
    }

    public boolean remove(String argument){
        return removeByIdCommand.execute(argument);
    }

    public boolean save(String argument){
        return saveCommand.execute(argument);
    }

    public boolean executeScipt(String argument){
        return executeScriptCommand.execute(argument);
    }

    public boolean exit(String argument){
        return exitCommand.execute(argument);
    }

    public boolean head(String argument){
        return headCommand.execute(argument);
    }

    public boolean addmin(String argument){
        return addIfMinCommand.execute(argument);
    }

    public boolean removeGreater(String argument){
        return removeGreaterCommand.execute(argument);
    }

    public boolean averageDis(String argument){
        return averageOfDiscountCommand.execute(argument);
    }

    public boolean groupCount(String argument){
        return groupCountingCommand.execute(argument);
    }

    public boolean print(String argument){
        return printUniqiePersonCommand.execute(argument);
    }
    public List<ICommand> getCommands(){
        return commands;
    }

    public boolean noSuchCommand(String command){
        Console.println("Command '" + command + "' is not found. Print 'help' for list of commands");
        return false;
    }

    @Override
    public String toString() {
        return "CommandManager for working with commands";
    }
}

//всё перепроверить