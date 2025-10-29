interface GUIFactory {
    Button createButton();
    InputField createInputField();
}

// Конкретные фабрики
class WindowsFactory implements GUIFactory {
    public Button createButton() { return new WindowsButton(); }
    public InputField createInputField() { return new WindowsInputField(); }
}

class MacOSFactory implements GUIFactory {
    public Button createButton() { return new MacOSButton(); }
    public InputField createInputField() { return new MacOSInputField(); }
}

class LinuxFactory implements GUIFactory {
    public Button createButton() { return new LinuxButton(); }
    public InputField createInputField() { return new LinuxInputField(); }
}

// Фабрика для выбора платформы
class PlatformFactory {
    public static GUIFactory getFactory(String platform) {
        return switch(platform.toLowerCase()) {
            case "windows" -> new WindowsFactory();
            case "macos" -> new MacOSFactory();
            case "linux" -> new LinuxFactory();
            default -> throw new IllegalArgumentException("Unknown platform: " + platform);
        };
    }
}

// Application.java
// Клиентское приложение
class Application {
    private Button button;
    private InputField inputField;

    public Application(GUIFactory factory) {
        this.button = factory.createButton();
        this.inputField = factory.createInputField();
    }

    public void renderUI() {
        System.out.println(" Rendering UI ");
        button.render();
        inputField.render();
    }

    public void simulateInteraction() {
        System.out.println(" User Interaction ");
        button.onClick();
        inputField.onInput("Hello Abstract Factory!");
    }
}

