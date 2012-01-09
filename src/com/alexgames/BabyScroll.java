package com.alexgames;

import android.app.ListActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.*;
import android.widget.*;

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
                View textView = view.findViewById(R.id.txtItem);

                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.toast_layout,
                        (ViewGroup) findViewById(R.id.toast_layout_root));

                //TODO put in images
                //ImageView image = (ImageView) layout.findViewById(R.id.image);
                //image.setImageResource(R.drawable.dog);
                TextView text = (TextView) layout.findViewById(R.id.text);
                text.setText(((TextView) textView).getText());

                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layout);
                toast.show();

                Toast.makeText(getApplicationContext(), ((TextView) textView).getText(),
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
        View textView = (TextView)view.findViewById(R.id.txtItem);
        String viewText = ((TextView) textView).getText().toString();

        String say = viewText;

        if (viewText.equals("A")) {
            say = "AE Alex";
        } else if (viewText.equals("B")) {
            say = "B ball";
        } else if (viewText.equals("C")) {
            say = "C car";
        } else if (viewText.equals("D")) {
            say = "d  daddy";
        } else if (viewText.equals("E")) {
            say = "e  elephant";
        } else if (viewText.equals("F")) {
            say = "f  firetruck";
        } else if (viewText.equals("G")) {
            say = "g  grandpa";
        } else if (viewText.equals("M")) {
            say = "m mommy";
        } else if (viewText.equals("T")) {
            say = "t tiger";
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
