package HomeSystem;

public class HomeSystemFacade {
    Appliance tv;
    Appliance streamingApp;
    Appliance soundSystem;

    public HomeSystemFacade(String myTV, String myStreamingApp, String mySoundSystem) {
        this.tv = new TV(myTV);
        this.streamingApp = new StreamingApp(myStreamingApp);
        this.soundSystem = new SoundSystem(mySoundSystem);
    }

    // -------------- TV interfaces --------------
    public void turnOnTV() {
        this.tv.turnOn();
    }

    public void turnOffTV() {
        this.tv.turnOff();
    }

    public void mute() {
        ((TV) tv).mute();
    }

    public void increaseTVVolume() {
        ((TV) tv).increaseTVVolume();
    }

    public void decreaseTVVolume() {
        ((TV) tv).decreaseTVVolume();
    }

    public void playCable(String cableChannel) {
        ((TV) tv).playCable(cableChannel);
    }

    // Delegate to TV methods

    // -------------- Streaming App interfaces --------------
    public void turnOnStreamingApp() {
        streamingApp.turnOn();
    }

    public void turnOffStreamingApp() {
        streamingApp.turnOff();
    }
    public void playNetflix(){
        ((StreamingApp) streamingApp).playNetflix();
    }
    public void playHulu(){
        ((StreamingApp) streamingApp).playHulu();
    }
    public void playHBOMax(){
        ((StreamingApp) streamingApp).playHBOMax();
    }

    // Delegate to Streaming App methods

    // -------------- Sound System interfaces --------------
    public void turnOnSoundSystem() {
        soundSystem.turnOn();
    }

    public void turnOffSoundSystem() {
        soundSystem.turnOff();
    }

    public void playStation(String station){
        ((SoundSystem) soundSystem).playStation(station);
    }

    public void playWhiteNoise(){
        ((SoundSystem) soundSystem).playWhiteNoise();
    }

    public void playNightSounds(NightSound nightSound){
        ((SoundSystem) soundSystem).playNightSounds(nightSound);

    }

    // Delegate to Sound System methods

    // Method to turn off all three devices
    public void turnOffAll() {
        tv.turnOff();
        soundSystem.turnOff();
        streamingApp.turnOff();
    }
}
