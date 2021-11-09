package img.imaginary.presentation;

import img.imaginary.exception.MenuBuilderException;

public class MenuBuilder {

    private final MenuContainer baseMenu;

    public MenuBuilder(String name) {
        this.baseMenu = new MenuContainer(name);
    }

    public MenuContainer getBaseMenu() {
        return baseMenu;
    }

    public MenuBuilder addItem(Menu component) {
        baseMenu.addItem(component);
        return this;
    }

    public SubMenuBuilder subMenu(String name) {
        return new SubMenuBuilder(name, this);    
    }

    MenuBuilder endMenu() {
        throw new MenuBuilderException("A submenu must be created before calling endMenu() method");
      }

    public Menu build() {
        return baseMenu;
    }
}
