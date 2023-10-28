package seedu.wildwatch.command;

import seedu.wildwatch.entry.Entry;
import seedu.wildwatch.entry.EntryList;
import seedu.wildwatch.operation.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Handles the "find" command to search for tasks that contain a specific keyword.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    //TODO[PARSER]: SHOULD BE REMOVED AFTER IMPLEMENTING PARSER
    private String inputBuffer;

    public FindCommand(String inputBuffer) {
        this.inputBuffer = inputBuffer;
    }

    /**
     * Searches for tasks that contain the specified keyword and prints them.
     */
    public void execute() {
        boolean hasMatch = false;
        String matchingWord = inputBuffer.substring(inputBuffer.indexOf("find") + 5).trim();
        ArrayList<Entry> entries = EntryList.getArray();
        ArrayList<Integer> matchingEntries = new ArrayList<>();

        LocalDate inputDate = null;
        try {
            inputDate = LocalDate.parse(matchingWord, DateTimeFormatter.ofPattern("dd-MM-yy"));
        } catch (DateTimeParseException exception) {
            // matchingWord is not a date
        }

        for (Entry entry: entries) {
            boolean isInputADate = (inputDate != null && entry.getDate().equals(inputDate));


            if (entry.getSpecies().contains(matchingWord)
                    || entry.getSpecies().toLowerCase().contains(matchingWord)
                    || entry.getName().contains(matchingWord)
                    || entry.getName().toLowerCase().contains(matchingWord)
                    || entry.getRemark().contains(matchingWord)
                    || entry.getRemark().toLowerCase().contains(matchingWord)
                    || isInputADate) {
                hasMatch = true;
                matchingEntries.add(entries.indexOf(entry));
            }
        }
        Ui.findTaskMessagePrinter(hasMatch, matchingEntries);
    }
}
