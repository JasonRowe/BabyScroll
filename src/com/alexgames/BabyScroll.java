package com.alexgames;

import android.app.ListActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class BabyScroll extends ListActivity implements TextToSpeech.OnInitListener {
    TextToSpeech talker;
    MediaPlayer player;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GoFullScreen();

        talker = new TextToSpeech(this, this);

        BabyScrollItemBuilder itemBuilder = new BabyScrollItemBuilder();
        
        ArrayList<BabyScrollItem> items = itemBuilder.GetAlphabetItems();

        CircularArrayAdapter<BabyScrollItem> adapter = new CircularArrayAdapter<BabyScrollItem>(this, R.layout.list_item, items);
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
            say = "AE Alex";
        } else if (viewText.equals("B")) {
            say = "B ball";
        } else if (viewText.equals("C")) {
            say = "C car";
        } else if (viewText.equals("D")) {
            say = "d  daddy";
        } else if (viewText.equals("M")) {
            say = "m mommy";
        } else if (viewText.equals("Z")) {
            say = "z zoo";
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
