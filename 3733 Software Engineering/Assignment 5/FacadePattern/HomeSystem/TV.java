package HomeSystem;

class TV extends Appliance {

    public TV(String name) {
        super(name);
    }

    static int HIGHEST_VOLUME = 20;
    static int LOWEST_VOLUME = 0;
    int volume = 5; // Default volume when HomeSystem.TV is first created
    String cableChannel;

    void mute() {
        turnOn();
        volume = 0;
        System.out.println("HomeSystem.TV: Volume has been muted");
    }

    void increaseTVVolume() {
        turnOn();
        if (volume < HIGHEST_VOLUME) {
            volume++;
            System.out.println("HomeSystem.TV: Volume is now at " + volume);
        } else {
            System.out.println("HomeSystem.TV: Volume is already at the maximum volume, 20");
        }
    }
    void decreaseTVVolume() {
        turnOn();
        if (volume > LOWEST_VOLUME) {
            volume--;
            System.out.println("HomeSystem.TV: Volume is now at " + volume);
        } else {
            System.out.println("HomeSystem.TV: Volume has been muted");
        }
    }

    void playCable(String cableChannel) {
        turnOn();
        System.out.println(getName() + ": Playing cable channel " + cableChannel);
    }

}
