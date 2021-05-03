package HomeSystem;

class StreamingApp extends Appliance {
    StreamingApp(String name) {
        super(name);
    }

    void playNetflix() {
        turnOn();
        System.out.println(getName() + ": Playing Netflix");
    }

    void playHulu() {
        turnOn();
        System.out.println(getName() + ": Playing Hulu");
    }

    void playHBOMax() {
        turnOn();
        System.out.println(getName() + ": Playing HBOMax");
    }

}
