package img.imaginary.presentation;

public class MenuBuilder {

    private MenuContainer baseMenu;
    private MenuContainer previousMenu = null;
    
    public MenuBuilder(MenuContainer baseMenu) {
        this.baseMenu = baseMenu;
    }

    public MenuBuilder addItem(Menu component) {
        baseMenu.addItem(component);
        return this;
    }

    public MenuBuilder subMenu(MenuContainer subMenu) {
        this.addItem(subMenu);
        previousMenu = baseMenu;
        subMenu.setPrevious(baseMenu);
        baseMenu = subMenu;
        return this;
    }

    public MenuBuilder endMenu() {
        baseMenu = previousMenu;
        return this;
    }

    public Menu build() {
        return baseMenu;
    }
}
