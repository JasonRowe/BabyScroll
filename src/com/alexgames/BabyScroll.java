package com.alexgames;

import android.app.ListActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.*;

public class BabyScroll extends ListActivity implements TextToSpeech.OnInitListener {
    TextToSpeech talker;
    MediaPlayer player;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GoFullScreen();

        talker = new TextToSpeech(this, this);

        CircularArrayAdapter<String> adapter = new CircularArrayAdapter<String>(this, R.layout.list_item, ABCs);
        setListAdapter(adapter);

        ListView lv = getListView();
        lv.setTextFilterEnabled(true);

        lv.setSelectionFromTop(adapter.MIDDLE, 0);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked do text to speech
                TalkItem(view);
                // When clicked, show a toast with the TextView text
                Toast.makeText(getApplicationContext(), ((TextView) view).getText(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        InitializeMediaPlayer();
    }

    private void GoFullScreen() {
        final Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    static final String[] ABCs = new String[]{
            "A", "Z", "Y", "X", "W", "V", "U", "T", "S", "R", "Q",
            "P", "O", "N", "M", "L", "K", "J", "I", "H", "G", "F",
            "E", "D", "C", "B"
    };

    public void onResume() {
        super.onResume();

        if (player == null) {
            InitializeMediaPlayer();
        } else {
            player.start();
        }
    }

    private void InitializeMediaPlayer() {
        player = MediaPlayer.create(this, R.raw.kidsmusic);
        player.setLooping(true);
        player.setVolume(0.2f, 0.2f);
        player.start();
    }

    private void ShutDownPlayer() {
        if (player != null) {
            if (player.isPlaying())
                player.stop();

            player.reset();
            player.release();
            player = null;
        }
    }

    public void onPause() {
        super.onPause();
        ShutDownPlayer();
    }

    private void TalkItem(View view) {
        String viewText = ((TextView) view).getText().toString();

        String say = viewText;

        if (viewText.equals("A")) {
            say = "AE is for Alex";
        } else if (viewText.equals("B")) {
            say = "B is for ball";
        } else if (viewText.equals("C")) {
            say = "C is for car";
        } else if (viewText.equals("D")) {
            say = "d is for daddy";
        } else if (viewText.equals("M")) {
            say = "m is for mommy";
        } else if (viewText.equals("Z")) {
            say = "z is for zoo";
        }

        talker.speak(say, TextToSpeech.QUEUE_FLUSH, null);
    }

    public void onInit(int status) {
    }

    @Override
    public void onDestroy() {
        if (talker != null) {
            talker.stop();
            talker.shutdown();
        }
        ShutDownPlayer();

        super.onDestroy();
    }
}
