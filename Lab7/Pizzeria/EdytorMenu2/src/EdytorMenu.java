
import java.util.*;
import java.util.Iterator;
import java.util.ArrayList;


abstract class MenuComponent {

    public void add(MenuComponent menuComponent) {
        throw new UnsupportedOperationException();
    }
    public void remove(MenuComponent menuComponent) {
        throw new UnsupportedOperationException();
    }
    public MenuComponent getChild(int i) {
        throw new UnsupportedOperationException();
    }

    public String getName() {
        throw new UnsupportedOperationException();
    }
    public String getDescription() {
        throw new UnsupportedOperationException();
    }

    public abstract Iterator createIterator();

    public void print() {
        throw new UnsupportedOperationException();
    }
}


class MenuItem extends MenuComponent {

    String name;
    String description;


    public MenuItem(String name, String description)
    {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Iterator createIterator() {
        return new NullIterator();
    }

    public void print() {
        System.out.print("  " + getName());
        System.out.println("     -- " + getDescription());
    }

}

class NullIterator implements Iterator {

    public Object next() {
        return null;
    }

    public boolean hasNext() {
        return false;
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}

class DokumentTextowy {
    MenuComponent pozycjaMenu;

    public DokumentTextowy(MenuComponent pozycjaMenu) {
        this.pozycjaMenu = pozycjaMenu;
    }

    public void printMenu() {
        pozycjaMenu.print();
    }
}

class CompositeIterator implements Iterator {
    Stack stack = new Stack();

    public CompositeIterator(Iterator iterator) {
        stack.push(iterator);
    }

    public Object next() {
        if (hasNext()) {
            Iterator iterator = (Iterator) stack.peek();
            MenuComponent component = (MenuComponent) iterator.next();
            if (component instanceof Menu) {
                stack.push(component.createIterator());
            }
            return component;
        } else {
            return null;
        }
    }

    public boolean hasNext() {
        if (stack.empty()) {
            return false;
        } else {
            Iterator iterator = (Iterator) stack.peek();
            if (!iterator.hasNext()) {
                stack.pop();
                return hasNext();
            } else {
                return true;
            }
        }
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}

class Menu extends MenuComponent {

    ArrayList menuComponents = new ArrayList();
    String name;
    String description;

    public Menu(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void add(MenuComponent menuComponent) {
        menuComponents.add(menuComponent);
    }

    public void remove(MenuComponent menuComponent) {
        menuComponents.remove(menuComponent);
    }

    public MenuComponent getChild(int i) {
        return (MenuComponent)menuComponents.get(i);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


    public Iterator createIterator() {
        return new CompositeIterator(menuComponents.iterator());
    }


    public void print() {
        System.out.print("\n" + getName());
        System.out.println(", " + getDescription());
        System.out.println("---------------------");

        Iterator iterator = menuComponents.iterator();
        while (iterator.hasNext()) {
            MenuComponent menuComponent =
                    (MenuComponent)iterator.next();
            menuComponent.print();
        }
    }
}

public class EdytorMenu {
    public static void main(String args[]) {

        // „oddzielnego liścia i korzenia”

        MenuComponent plik =
                new Menu("Plik", "Opcje dla pliku");
        MenuComponent pobierz =
                new Menu("Pobierz plik jako..", "Opcje pobierania pliku");
        MenuComponent widok =
                new Menu("Widok", "Opcje widoku");
        MenuComponent trybWidoku =
                new Menu("Tryb Widoku", "Zmiana możliwości trybu widoku");




        plik.add(new MenuItem("Nowy", "Tworzy nowy plik"));
        plik.add(new MenuItem("Pobierz plik", "Pobierz plik"));
        plik.add(new MenuItem("Zamknij", "Zamyka plik bez zapisu"));
        plik.add(new MenuItem("Zapisz", "Zapisuje plik"));


        pobierz.add(new MenuItem("PDF", "Pobierz plik w formacie pdf"));
        pobierz.add(new MenuItem("DOC", "Pobierz plik w formacie doc"));
        plik.add(pobierz);

        widok.add(new MenuItem("Pelen ekran", "Wyswietla dokument na pełnym ekranie"));
        widok.add(new MenuItem("Zapisz", "Zapisuje plik"));

        trybWidoku.add(new MenuItem("Edytowanie", "Pozwala edytować plik"));
        trybWidoku.add(new MenuItem("Komentarz", "Pozwala komentować plik bez edycji tekstu"));
        widok.add(trybWidoku);

        DokumentTextowy dokumentTextowy = new DokumentTextowy(plik);

        dokumentTextowy.printMenu();

        DokumentTextowy dokumentTextowy2 = new DokumentTextowy(widok);
        dokumentTextowy2.printMenu();

    }
}
