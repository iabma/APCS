import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class produces the Library object, which is the backbone of this project. The catalogue,
 * is a dynamic ArrayList containing Items. It can be accessed and modified by the various
 * methods included in the object.
 *
 * @author Ian Balaguera
 * @version 1.15.19
 */
public class Library {
    /**
     * A one-dimensional String array containing the names corresponding to the OPTIONS arrays.
     */
    public static final String[] PAGES_ARRAY = {"home", "search", "add item", "make hard copy",
            "search books", "search dvds", "add book", "add dvd"};
    /**
     * The ArrayList version of PAGES_ARRAY.
     */
    public static final ArrayList<String> PAGES = new ArrayList<>(Arrays.asList(PAGES_ARRAY));
    /**
     * This two-dimensional String array is the primary source of display information for the
     * Library, as it contains all the different selectable options on every Page of the Library.
     */
    public static final String[][] OPTIONS = {
            {"check out item", "return item", "search stock", "add item", "make hard copy", "quit"},
            {"books", "dvds", "back"},
            {"book", "dvd", "back"},
            {".txt file", "back"},
            {"title", "author", "number of pages", "description", "id", "back"},
            {"title", "origin", "duration", "description", "id", "back"},
            {"title", "author", "number of pages", "description"},
            {"title", "origin", "duration", "description"}};

    private ArrayList<Item> catalogue;
    private Page home, search, searchBooks, searchDVDs, add, addBooks, addDVDs, hardCopy, quit,
            currentPage;
    private String itemInProduction, currentPrompt, stringArg;
    private int currentStep, intArg;
    private boolean running;

    /* CONSTRUCTORS */

    /**
     * Initializes all the viewable Pages of the Library, as well as other instance variables
     * used in the program.
     */
    public Library() {
        running = true;
        home = new Page(PAGES.get(0), OPTIONS[0]);
        search = new Page(PAGES.get(1), OPTIONS[1]);
        add = new Page(PAGES.get(2), OPTIONS[2]);
        hardCopy = new Page(PAGES.get(3), OPTIONS[3]);
        searchBooks = new Page(PAGES.get(4), OPTIONS[4]);
        searchDVDs = new Page(PAGES.get(5), OPTIONS[5]);
        addBooks = new Page(PAGES.get(6), OPTIONS[6]);
        addDVDs = new Page(PAGES.get(7), OPTIONS[7]);
        quit = new Page("quitting", new String[0]);
        currentPage = home;
        currentPrompt = "none";
        stringArg = "";
        currentStep = 0;
        intArg = currentPage.getOptions().length - 1;
        itemInProduction = "";
        catalogue = new ArrayList<>();
    }

    /* PUBLIC METHODS */

    /**
     * This is the foremost method of the Library object, as it handles user interfacing with the
     * "catalogue" ArrayList. To begin with, it defaults to the home Page if the user input is null.
     * Then, it checks the "currentPrompt" variable, which contains relevant information about the
     * current state of the library. Different "currentPrompt" values are correlated to different
     * events. For instance, the first if statement checks if the user is currently attempting to
     * search for a book. If so, it grabs the search parameter from "currentPrompt" and checks if
     * it is "number of pages", in which case "intArg" is set to accommodate for any acceptable
     * integer. It then checks if the parameter is "id". If so, "intArg" is set to the maximum
     * possible ID value. Next it calls the private "searchBooks()" method, which returns an
     * ArrayList of matches. The appropriate output using this List is then returned.
     *
     * "intArg" is a value which is required in order to make proper error checks. It generally
     * either equals -1 if it is not in use or a number which specifies the maximum possible
     * integer which can be entered for a prompt.
     *
     * "stringArg" serves a similar purpose as "intArg", but for any Strings. In the current
     * implementation, it only serves the hard copy printing, as is acts as a blacklist for
     * characters that are not allowed in an output file name.
     *
     * If there are no current prompts, then the method defaults to page navigation. This is
     * handled by a collection of switch statements, which act as a sort of plug-and-play method
     * of connecting pages with each other. At the end of this else statement, the
     * "currentPage.toString()" is returned, which is the visualization of current page as
     * provided by the Page object.
     *
     * @param input the user input
     * @return either the current user interface or a hard copy
     */
    public String update(String input) {
        if (input == null) {
            return home.toString();
        }

        intArg = -1;
        if (currentPrompt.contains(searchBooks.getName())) {
            String param = currentPrompt.substring(searchBooks.getName().length());
            if (param.equals("number of pages"))
                intArg = Integer.MAX_VALUE;
            else if (param.equals("id"))
                intArg = catalogue.size() - 1;
            ArrayList<Book> matches = searchBooks(input, param);
            currentPage = searchBooks;
            currentPrompt = "none";
            if (matches.size() == 0) {
                return "\nno results.\n\n" + currentPage.toString();
            } else {
                String printedMatches = "\n";
                for (Book book : matches) {
                    printedMatches += "\n" + book.toString();
                }
                return printedMatches + "\n\n" + currentPage.toString();
            }
        } else if (currentPrompt.contains(searchDVDs.getName())) {
            String param = currentPrompt.substring(searchDVDs.getName().length());
            if (param.equals("duration"))
                intArg = Integer.MAX_VALUE;
            else if (param.equals("id"))
                intArg = catalogue.size() - 1;
            ArrayList<DVD> matches = searchDVDs(input, param);
            currentPage = searchBooks;
            currentPrompt = "none";
            if (matches.size() == 0) {
                return "\nno results.\n\n" + currentPage.toString();
            } else {
                String printedMatches = "\n";
                for (DVD dvd : matches) {
                    printedMatches += "\n" + dvd.toString();
                }
                return printedMatches + "\n\n" + currentPage.toString();
            }
        } else if (currentPrompt.contains(addBooks.getName())) {
            String[] options = addBooks.getOptions();
            if (currentStep == options.length - 1) {
                itemInProduction += "\n" + input;
                add(itemInProduction);
                itemInProduction = "";
                currentStep = 0;
                currentPrompt = "none";
                return "\nbook added to catalogue.\n\n" + currentPage.toString();
            } else {
                currentStep++;
                if (options[currentStep].equals("number of pages"))
                    intArg = Integer.MAX_VALUE;
                itemInProduction += "\n" + input;
                return options[currentStep];
            }
        } else if (currentPrompt.contains(addDVDs.getName())) {
            String[] options = addDVDs.getOptions();
            if (currentStep == options.length - 1) {
                itemInProduction += "\n" + input;
                add(itemInProduction);
                itemInProduction = "";
                currentStep = 0;
                currentPrompt = "none";
                return "\ndvd added to catalogue.\n\n" + currentPage.toString();
            } else {
                currentStep++;
                if (options[currentStep].equals("duration"))
                    intArg = Integer.MAX_VALUE;
                itemInProduction += "\n" + input;
                return options[currentStep];
            }
        } else if (currentPrompt.equals(hardCopy.getName())) {
            String output = ":::: LIBRARY BOOKS ::::";
            for (Item item : catalogue) {
                if (item.getClass().getName().equals("Book")) {
                    Book book = (Book) item;
                    output += "\n" + book.toString();
                }
            }
            currentPrompt = "none";
            stringArg = "";
            currentPage = home;
            return output;
        } else if (currentPrompt.equals("checkout")) {
            currentPrompt = "none";
            Item item = catalogue.get(Integer.parseInt(input));
            if (!item.isInStock())
                return "\nitem is not currently available.\n\n" + currentPage.toString();
            item.checkOut();
            intArg = -1;
            stringArg = "";
            return "\nitem checked out.\n\n" + currentPage.toString();
        } else if (currentPrompt.equals("return")) {
            currentPrompt = "none";
            Item item = catalogue.get(Integer.parseInt(input));
            if (item.isInStock())
                return "\nitem is already available.\n\n" + currentPage.toString();
            item.returnIt();
            intArg = -1;
            stringArg = "";
            return "\nitem returned.\n\n" + currentPage.toString();
        } else {
            int selection = Integer.parseInt(input) - 1;
            if (currentPage == home) {
                switch (selection) {
                    case 0:
                        if (catalogue.size() != 0) {
                            currentPrompt = "checkout";
                            intArg = catalogue.size() - 1;
                            stringArg = "id";
                            return "enter item id";
                        } else {
                            return "\ncatalogue is empty.\n\n" + currentPage.toString();
                        }
                    case 1:
                        if (catalogue.size() != 0) {
                            currentPrompt = "return";
                            intArg = catalogue.size() - 1;
                            stringArg = "id";
                            return "enter item id";
                        } else {
                            return "\ncatalogue is empty.\n\n" + currentPage.toString();
                        }
                    case 2:
                        currentPage = search; break;
                    case 3:
                        currentPage = add; break;
                    case 4:
                        currentPage = hardCopy; break;
                    case 5:
                        running = false;
                        currentPage = quit; break;
                }
            } else if (currentPage == search) {
                switch (selection) {
                    case 0:
                        currentPage = searchBooks; break;
                    case 1:
                        currentPage = searchDVDs; break;
                    case 2:
                        currentPage = home; break;
                }
            } else if (currentPage == add) {
                switch (selection) {
                    case 0:
                        currentPrompt = addBooks.getName();
                        itemInProduction = "book";
                        return addBooks.getOptions()[currentStep];
                    case 1:
                        currentPrompt = addDVDs.getName();
                        itemInProduction = "dvd";
                        return addDVDs.getOptions()[currentStep];
                    case 2:
                        currentPage = home; break;
                }
            } else if (currentPage == hardCopy) {
                switch (selection) {
                    case 0:
                        currentPrompt = hardCopy.getName();
                        stringArg = "/";
                        return "output file name";
                    case 1:
                        currentPage = home; break;
                }
            } else if (currentPage == searchBooks) {
                switch (selection) {
                    case 5:
                        currentPage = search; break;
                    default:
                        currentPrompt = searchBooks.getName() + searchBooks.getOptions()[selection];
                        return searchBooks.getOptions()[selection];
                }
            } else if (currentPage == searchDVDs) {
                switch (selection) {
                    case 5:
                        currentPage = search; break;
                    default:
                        currentPrompt = searchDVDs.getName() + searchDVDs.getOptions()[selection];
                        return searchDVDs.getOptions()[selection];
                }
            }
            if (running) {
                intArg = currentPage.getOptions().length - 1;
            }
            return currentPage.toString();
        }
    }

    /**
     * Returns whether or not the Library is currently running.
     * @return running boolean
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Returns the integer argument corresponding to the Library's current state.
     * @return intArg
     */
    public int getIntArg() {
        return intArg;
    }

    /**
     * Returns the String argument corresponding to the Library's current state.
     * @return stringArg
     */
    public String getStringArg() {
        return stringArg;
    }

    /**
     * Returns a formatted version of "stock".
     * @return formatted version of "stock"
     */
    @Override
    public String toString() {
        StringBuilder formatted = new StringBuilder();
        for (Item item : catalogue) {
            formatted.append(item);
        }
        return formatted.toString();
    }

    /* PRIVATE METHODS */

    private ArrayList<Book> searchBooks(String input, String param) {
        ArrayList<Book> bookStock = new ArrayList<>();
        for (Item item : catalogue) {
            if (item.getClass().getName().equals("Book")) bookStock.add((Book) item);
        }

        ArrayList<Book> returnList = new ArrayList<>();
        for (Book book : bookStock) {
            switch (param) {
                case "id":
                    if (book.getID() == Integer.parseInt(input)) returnList.add(book); break;
                case "description":
                    if (book.getDescription().toLowerCase().contains(input.toLowerCase()))
                        returnList.add(book); break;
                case "title":
                    if (book.getTitle().toLowerCase().contains(input.toLowerCase()))
                        returnList.add(book); break;
                case "author":
                    if (book.getAuthor().toLowerCase().contains(input.toLowerCase()))
                        returnList.add(book); break;
                case "number of pages":
                    if (book.getNumPages() == Integer.parseInt(input)) returnList.add(book); break;
            }
        }
        return returnList;
    }

    private ArrayList<DVD> searchDVDs(String input, String param) {
        ArrayList<DVD> dvdStock = new ArrayList<>();
        for (Item item : catalogue) {
            if (item.getClass().getName().equals("DVD")) dvdStock.add((DVD) item);
        }

        ArrayList<DVD> returnList = new ArrayList<>();
        for (DVD dvd : dvdStock) {
            switch (param) {
                case "id":
                    if (dvd.getID() == Integer.parseInt(input)) returnList.add(dvd); break;
                case "description":
                    if (dvd.getDescription().toLowerCase().contains(input.toLowerCase()))
                        returnList.add(dvd); break;
                case "title":
                    if (dvd.getTitle().toLowerCase().contains(input.toLowerCase()))
                        returnList.add(dvd); break;
                case "origin":
                    if (dvd.getOrigin().toLowerCase().contains(input.toLowerCase()))
                        returnList.add(dvd); break;
                case "duration":
                    if (dvd.getLength() == Integer.parseInt(input)) returnList.add(dvd); break;
            }
        }
        return returnList;
    }

    private void add(String newItemInfo) {
        String[] info = newItemInfo.split("\n");
        if (info[0].equalsIgnoreCase("book")) {
            Book newBook = new Book(info[1], info[2], Integer.parseInt(info[3]), info[4]);
            catalogue.add(newBook);
        } else if (info[0].equalsIgnoreCase("dvd")) {
            DVD newDVD = new DVD(info[1], info[2], Integer.parseInt(info[3]), info[4]);
            catalogue.add(newDVD);
        }
    }
}