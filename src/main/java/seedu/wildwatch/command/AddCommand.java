package seedu.wildwatch.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.wildwatch.entry.Entry;
import seedu.wildwatch.entry.EntryList;
import seedu.wildwatch.exception.IncorrectInputException;
import seedu.wildwatch.operation.Ui;

/**
 * Command class for adding entry to EntryList
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final Pattern ADD_ENTRY_COMMAND_FORMAT =
            Pattern.compile("add"
                    + " D/(?<date>[^/]+)"
                    + " S/(?<species>[^/]+)"
                    + " N/(?<name>[^/]+)"
                    + "(?: R/(?<remark>[^/]+))?");

    //TODO[PARSER]: TEMPORARY. REMOVE LATER.
    private String inputBuffer;

    public AddCommand(String inputBuffer) {
        this.inputBuffer = inputBuffer;
    }

    public void execute() throws IncorrectInputException {

        final Matcher matcher = ADD_ENTRY_COMMAND_FORMAT.matcher(inputBuffer);

        if (!matcher.matches()) {
            throw new IncorrectInputException();
        }

        final String date = matcher.group("date").trim();
        final String species = matcher.group("species").trim();
        final String name = matcher.group("name").trim();
        final String remark = matcher.group("remark");

        EntryList.addEntry(new Entry(date, species, name, remark));

        Ui.entryAddedMessagePrinter();
        Ui.printEntry(EntryList.getArraySize() - 1);
        Ui.entryCountPrinter();
    }
}

