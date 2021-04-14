interface StylMebliFactory {
    public void wybierzNogi();
    public void wybierzKolor();
}

class StylNowoczesnyFactory implements StylMebliFactory {

    @Override
    public void wybierzNogi() {
        System.out.println("Proste nogi");
    };


    @Override
    public void wybierzKolor() {
        System.out.println("Kolor morski");
    }
}

class StylWiktorianskiFactory implements StylMebliFactory {

    @Override
    public void wybierzNogi() {
        System.out.println("Krzywe nogi");
    };


    @Override
    public void wybierzKolor() {
        System.out.println("Kolor biały");
    }
}

abstract class Mebel {
    String name;

    void prepare() {
        System.out.println("Przygotuj mebel");
    }

    void setName(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }
}

class Fotel extends Mebel {
    StylMebliFactory stylMebliFactory;

    public Fotel(StylMebliFactory stylMebliFactory) {
        this.stylMebliFactory = stylMebliFactory;
        prepare();
    }

    void prepare() {
        stylMebliFactory.wybierzNogi();
        stylMebliFactory.wybierzKolor();
    }
}

class Sofa extends Mebel {
    StylMebliFactory stylMebliFactory;

    public Sofa(StylMebliFactory stylMebliFactory) {
        this.stylMebliFactory = stylMebliFactory;
        prepare();
    }

    void prepare() {
        stylMebliFactory.wybierzNogi();
        stylMebliFactory.wybierzKolor();
    }
}
class StolikKawowy extends Mebel {
    StylMebliFactory stylMebliFactory;

    public StolikKawowy(StylMebliFactory stylMebliFactory) {
        this.stylMebliFactory = stylMebliFactory;
        prepare();
    }

    void prepare() {
        stylMebliFactory.wybierzNogi();
        stylMebliFactory.wybierzKolor();
    }
}

abstract class StylMebli {

    protected abstract Mebel stworzMebel(String rodzaj);

    public Mebel przygotujMebel(String rodzaj) {
        Mebel mebel = stworzMebel(rodzaj);
        System.out.println("--- Making a " + mebel.getName() + " ---");
        mebel.prepare();
        return mebel;
    }
}

class StylWiktorianski extends StylMebli {

    @Override
    protected Mebel stworzMebel(String rodzaj) {
        Mebel mebel = null;
        StylMebliFactory stylMebliFactory = new StylWiktorianskiFactory();
        if (rodzaj.equals("fotel")) {
            mebel = new Fotel(stylMebliFactory);
            mebel.setName("Stworzono fotel w stylu wiktorianskim");

        } else if (rodzaj.equals("sofa")) {
            mebel = new Sofa(stylMebliFactory);
            mebel.setName("Stworzono sofę w stylu wiktorianskim");

        } else if (rodzaj.equals("stolik")) {
            mebel = new StolikKawowy(stylMebliFactory);
            mebel.setName("Stworzono stolik kawowy w stylu wiktorianskim");

        }
        return mebel;
    }
}

class StylNowoczesny extends StylMebli {

    @Override
    protected Mebel stworzMebel(String rodzaj) {
        Mebel mebel = null;
        StylMebliFactory stylMebliFactory = new StylNowoczesnyFactory();
        if (rodzaj.equals("fotel")) {
            mebel = new Fotel(stylMebliFactory);
            mebel.setName("Stworzono fotel w stylu nowoczesnym");

        } else if (rodzaj.equals("sofa")) {

            mebel = new Sofa(stylMebliFactory);
            mebel.setName("Stworzono sofę w stylu nowoczesnym");

        } else if (rodzaj.equals("stolik")) {

            mebel = new StolikKawowy(stylMebliFactory);
            mebel.setName("Stworzono stolik kawowy w stylu nowoczesnym");

        }
        return mebel;
    }
}

public class SklepMeblowy {
    public static void main(String[] args) {
        StylMebli wiktorianski = new StylWiktorianski();
        StylMebli nowoczesny = new StylNowoczesny();
        Mebel nowaSofa = wiktorianski.stworzMebel("sofa");
        System.out.println(nowaSofa.getName());
        Mebel nowyFotel = nowoczesny.stworzMebel("fotel");
        System.out.println(nowyFotel.getName());
    }
}
