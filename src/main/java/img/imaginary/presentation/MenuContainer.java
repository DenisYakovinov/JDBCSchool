package img.imaginary.presentation;

import java.util.ArrayList;
import java.util.List;

public class MenuContainer extends MenuReader implements Menu {

    private final String description;
    private final List<Menu> components = new ArrayList<>();
    private Menu previousMenu;
    private String exit = "";

    public MenuContainer(String description) {
        this.description = description;
    }

    public void addItem(Menu item) {
        components.add(item);
    }

    public void setPrevious(Menu previousMenu) {
        this.previousMenu = previousMenu;
    }

    public void setExit(String exit) {
        this.exit = exit;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void execute() {
        while (!exit.equals("q")) {
            out.println(description);
            out.println("for exit input 'q'");
            components.forEach(menu -> out.println((components.indexOf(menu) + 1) + ". " + menu.getDescription()));
            String partMessage = "";
            if (previousMenu != null) {
                partMessage = "for previous menu point input 0\n";
            }
            String message = String.format("%sinput number", partMessage);
            String input = readString(message);
            if (input.equals("q")) {
                out.println("bye bye");
                exit = "q";
                break;
            }
            int number = -1;
            try {
                number = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                out.println("\nThe input should be a nubmer or 'q' (for exit) try again:");
            }
            if (number > 0 && number <= components.size()) {
                components.get(number - 1).execute();

            } else if (number == 0 && previousMenu != null) {
                break;
            } else {
                out.println("\nThe input should be a positive nubmer and present in the menu");
            }
        }
        if (exit.equals("q") && previousMenu instanceof MenuContainer) {
            MenuContainer previous = (MenuContainer) previousMenu;
            previous.setExit("q");
        }
    }
}

