interface SoundCoddingInterface {
    void decode();
}

class DolbyProLogic implements SoundCoddingInterface {
    @Override
    public void decode() {
        System.out.println("Umcyk Umcyk Umcyk!");
    }
}

class DolbyDigital implements SoundCoddingInterface {
    @Override
    public void decode() {
        System.out.println("Bumcyk Bumcyk Bumcyk!");
    }
}

class MusicPlayer {

    SoundCoddingInterface soundCoddingInterface = new DolbyDigital();

    public MusicPlayer() {
    }

    public void show() {
        System.out.println("Odtwarzacz Muzyki");
    };

    public void playMusic() {
        soundCoddingInterface.decode();
    }

    public void setSoundCodding (SoundCoddingInterface sci) {
        soundCoddingInterface = sci;
    }
}

public class Winamp2020 {
    public static void main(String[] args) {
        MusicPlayer musicPlayer = new MusicPlayer();
        musicPlayer.show();
        musicPlayer.playMusic();
        musicPlayer.setSoundCodding(new DolbyProLogic());
        musicPlayer.playMusic();
    }

}
