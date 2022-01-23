package HomeSystem;

import java.util.stream.Stream;

public class HomeSystemFacade {
    Appliance tv;
    Appliance streamingApp;
    Appliance soundSystem;

    public HomeSystemFacade(String myTV, String myStreamingApp, String mySoundSystem) {

        tv = new TV(myTV);
        streamingApp = new StreamingApp(myStreamingApp);
        soundSystem = new SoundSystem(mySoundSystem);

    }

    // -------------- TV interfaces --------------
    public void turnOnTV() { tv.turnOn(); }
    public void turnOffTV() { tv.turnOff(); }

    // Delegate to TV methods

    public void mute() { ((TV) tv).mute(); }
    public void increaseTVVolume() { ((TV) tv).increaseTVVolume(); }
    public void decreaseTVVolume() { ((TV) tv).decreaseTVVolume(); }
    public void playCable(String cabelChannel) { ((TV) tv).playCable(cabelChannel); }

    // -------------- Streaming App interfaces --------------
    public void turnOnStreamingApp() { streamingApp.turnOn(); }
    public void turnOffStreamingApp() {
        streamingApp.turnOff();
    }

    // Delegate to Streaming App methods

    public void playNetflix() { ((StreamingApp) streamingApp).playNetflix(); }
    public void playHulu() { ((StreamingApp) streamingApp).playHulu(); }
    public void playHBOMax() { ((StreamingApp) streamingApp).playHBOMax(); }

    // -------------- Sound System interfaces --------------
    public void turnOnSoundSystem() { soundSystem.turnOn(); }
    public void turnOffSoundSystem() { soundSystem.turnOff(); }

    // Delegate to Sound System methods

    public void playStation(String station) { ((SoundSystem) soundSystem).playStation(station); }
    public void playWhiteNoise() { ((SoundSystem) soundSystem).playWhiteNoise(); }
    public void playNightSounds(NightSound nightSound) { ((SoundSystem) soundSystem).playNightSounds(nightSound); }


    // Method to turn off all three devices
    public void turnOffAll() { tv.turnOff(); streamingApp.turnOff(); soundSystem.turnOff(); }
}
