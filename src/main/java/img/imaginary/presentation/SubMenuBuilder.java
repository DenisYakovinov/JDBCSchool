package img.imaginary.presentation;

public class SubMenuBuilder extends MenuBuilder {

    private final MenuBuilder outer;

    public SubMenuBuilder(String name, MenuBuilder outer) {
        super(name);
        this.outer = outer;
    }

    @Override
    MenuBuilder endMenu() {
        Menu subMenu = build();
        if (subMenu instanceof MenuContainer) {
            ((MenuContainer) subMenu).setPrevious(outer.getBaseMenu());
        }
        outer.addItem(subMenu);
        return outer;
    }
}
