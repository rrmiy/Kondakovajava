public class Main {
    public static void main(String[] args) {
        System.out.println(" ABSTRACT FACTORY GUI DEMO \n");

        // Демонстрация для разных платформ
        demonstratePlatform("Windows");
        demonstratePlatform("MacOS");
        demonstratePlatform("Linux");
    }

    private static void demonstratePlatform(String platform) {
        System.out.println(" " + platform + " Platform ");
        GUIFactory factory = PlatformFactory.getFactory(platform);
        Application app = new Application(factory);
        app.renderUI();
        app.simulateInteraction();
        System.out.println();
    }
}
