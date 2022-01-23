import HomeSystem.HomeSystemFacade;
import HomeSystem.NightSound;

public class FacadePatternHW {
    public static void main(String[] args) {
        // Example Home System Facade code

        HomeSystemFacade homeSystemFacade = new HomeSystemFacade("Keith's TV", "Keith's Streaming App", "Keith's Sound System");

        // Turn everything on
        homeSystemFacade.turnOnTV();
        homeSystemFacade.turnOnStreamingApp();
        homeSystemFacade.turnOnSoundSystem();
        System.out.println("\n");

        //TV methods
        homeSystemFacade.mute();
        homeSystemFacade.increaseTVVolume();
        homeSystemFacade.decreaseTVVolume();
        homeSystemFacade.playCable("Cartoon Network");
        System.out.println("\n");

        //Streaming App methods
        homeSystemFacade.playNetflix();
        homeSystemFacade.playHulu();
        homeSystemFacade.playHBOMax();
        System.out.println("\n");

        //Sound System methods
        homeSystemFacade.playStation("180 Rock");
        homeSystemFacade.playWhiteNoise();
        homeSystemFacade.playNightSounds(NightSound.RAIN);
        homeSystemFacade.playNightSounds(NightSound.FAN);
        homeSystemFacade.playNightSounds(NightSound.HUM);
        homeSystemFacade.playNightSounds(NightSound.WAVES);
        System.out.println("\n");

        //Turn off all
        homeSystemFacade.turnOffAll();
        System.out.println("\n");

        //Turn back on in order to use individual turn off methods
        homeSystemFacade.turnOnTV();
        homeSystemFacade.turnOnStreamingApp();
        homeSystemFacade.turnOnSoundSystem();
        homeSystemFacade.turnOffTV();
        homeSystemFacade.turnOffStreamingApp();
        homeSystemFacade.turnOffSoundSystem();

    }
}
