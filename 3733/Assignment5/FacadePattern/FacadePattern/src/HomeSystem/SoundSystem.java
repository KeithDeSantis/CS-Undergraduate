package HomeSystem;

class SoundSystem extends Appliance {

    SoundSystem(String name) {
        super(name);
    }

    void playStation(String station) {
        turnOn();
        System.out.println(getName() + ": playing station " + station);
    }

    void playWhiteNoise() {
        turnOn();
        System.out.println(getName() + ": playing white noise");
    }

    void playNightSounds(NightSound nightSound ) {
        turnOn();
        System.out.println(getName() + ": playing " + nightSound.toString().toLowerCase() + " night sounds");
    }

}
