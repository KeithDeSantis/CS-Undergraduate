import HomeSystem.HomeSystemFacade;
import HomeSystem.NightSound;

public class FacadePatternHW {
    public static void main(String[] args) {
        HomeSystemFacade homeSystem = new HomeSystemFacade("myTV",
                "myStreamingApp",
                "mySoundSystem");
        homeSystem.turnOnTV();
        homeSystem.playCable("CNN");
        homeSystem.increaseTVVolume();
        homeSystem.turnOnStreamingApp();
        homeSystem.playHBOMax();
        homeSystem.turnOnSoundSystem();
        homeSystem.mute();
        homeSystem.playStation("101.3");
        homeSystem.playWhiteNoise();
        homeSystem.playNightSounds(NightSound.RAIN);
        homeSystem.turnOffAll();

    }
}
