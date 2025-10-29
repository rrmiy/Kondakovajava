interface Button {
    void render();
    void onClick();
}

interface InputField {
    void render();
    void onInput(String text);
}

// Windows реализации
class WindowsButton implements Button {
    public void render() {
        System.out.println(" Rendering Windows button with gradients");
    }
    public void onClick() {
        System.out.println(" Windows button clicked with animation");
    }
}

class WindowsInputField implements InputField {
    public void render() {
        System.out.println(" Rendering Windows input field (Aero style)");
    }
    public void onInput(String text) {
        System.out.println(" Windows input with ClearType: " + text);
    }
}

// MacOS реализации
class MacOSButton implements Button {
    public void render() {
        System.out.println(" Rendering MacOS button (rounded corners)");
    }
    public void onClick() {
        System.out.println(" MacOS button clicked with haptic feedback");
    }
}

class MacOSInputField implements InputField {
    public void render() {
        System.out.println(" Rendering MacOS input field (minimalist)");
    }
    public void onInput(String text) {
        System.out.println(" MacOS input with gestures: " + text);
    }
}

// Linux реализации
class LinuxButton implements Button {
    public void render() {
        System.out.println(" Rendering Linux button (GTK style)");
    }
    public void onClick() {
        System.out.println(" Linux button clicked - instant response");
    }
}

class LinuxInputField implements InputField {
    public void render() {
        System.out.println(" Rendering Linux input field (practical)");
    }
    public void onInput(String text) {
        System.out.println(" Linux input keyboard optimized: " + text);
    }
}
