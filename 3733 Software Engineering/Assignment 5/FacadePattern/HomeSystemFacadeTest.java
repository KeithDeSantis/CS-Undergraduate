import HomeSystem.HomeSystemFacade;
import HomeSystem.NightSound;
import org.junit.Test;

public class HomeSystemFacadeTest {


    @Test
    public void testTV() {
        HomeSystemFacade homeSystemFacade = new HomeSystemFacade("TV", "no", "no");
        homeSystemFacade.turnOnTV();
        for(int i = 0; i < 6; i ++) homeSystemFacade.decreaseTVVolume();
        for(int i = 0; i < 21; i ++) homeSystemFacade.increaseTVVolume();
        homeSystemFacade.playCable("CABLE");
        homeSystemFacade.turnOffTV();
    }

    @Test
    public void testStreamingApp() {
        HomeSystemFacade homeSystemFacade = new HomeSystemFacade("no", "StreamingApp", "no");
        homeSystemFacade.turnOnStreamingApp();
        homeSystemFacade.playNetflix();
        homeSystemFacade.playHulu();
        homeSystemFacade.playHBOMax();
        homeSystemFacade.turnOffStreamingApp();
    }

    @Test
    public void testSoundSystem() {
        HomeSystemFacade homeSystemFacade = new HomeSystemFacade("no", "no", "SoundSystem");
        homeSystemFacade.turnOnSoundSystem();
        homeSystemFacade.playStation("STATION");
        homeSystemFacade.playWhiteNoise();
        homeSystemFacade.playNightSounds(NightSound.FAN);
        homeSystemFacade.playNightSounds(NightSound.WAVES);
        homeSystemFacade.playNightSounds(NightSound.HUM);
        homeSystemFacade.playNightSounds(NightSound.RAIN);
        homeSystemFacade.turnOffSoundSystem();
    }

    @Test
    public void testAll() {
        HomeSystemFacade homeSystemFacade = new HomeSystemFacade("TV", "StreamingApp", "SoundSystem");
        homeSystemFacade.turnOnTV();
        homeSystemFacade.turnOnStreamingApp();
        homeSystemFacade.turnOnSoundSystem();
        homeSystemFacade.turnOffAll();
    }

}
