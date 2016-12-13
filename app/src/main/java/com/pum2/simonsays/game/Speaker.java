package com.pum2.simonsays.game;

import android.content.Context;
import android.content.res.Resources;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import com.pum2.simonsays.R;
import com.pum2.simonsays.gesture.Gesture;

import java.util.List;

/**
 * Created by Grego on 11.12.2016.
 */

public class Speaker {

    private TextToSpeech tts;

    private Context context;

    public Speaker (Context context)
    {
        this.context = context;
    }

    public void setTts(TextToSpeech tts) {
        this.tts = tts;
    }

    public TextToSpeech getTts() {
        return tts;
    }

    public void initialMessage(Integer levelSize)
    {
        String msg = context.getResources().getString(R.string.game_new_game_started)+". "
                + levelSize.toString() +
                " "
                + context.getResources().getString(R.string.game_levels_count);
        log(msg);
        speech(msg);
    }

    public void newLevel(Integer levelNo)
    {
        String msg = context.getResources().getString(R.string.game_level) + ": " + levelNo;
        log(msg);
        speech(msg);
    }

    public void movesList(List<Gesture> listToRepeat)
    {
        String msg;
        msg = context.getResources().getString(R.string.game_repeat) + ": ";
        log(msg);
        speech(msg);
        for(Gesture toRepeat : listToRepeat)
        {
            msg = context.getResources().getString(toRepeat.getType().getResourceId());
            log(msg);
            speech(msg);
        }
    }

    public void correctMove()
    {
        String msg = context.getResources().getString(R.string.game_correct);
        log(msg);
        speech(msg);
    }

    public void endOfTheGame(boolean won)
    {
        Resources rsrc = context.getResources();
        String msg = won ? rsrc.getString(R.string.game_won) : rsrc.getString(R.string.game_fail);
        log(msg);
        speech(msg);
    }

    public void gesturesToRepeat(Integer currentLevel, List<Gesture> listToRepeat)
    {
        this.newLevel(currentLevel);
        this.movesList(listToRepeat);
    }

    private void speech(String msg)
    {
        if (tts == null)
        {
            return;
        }
        tts.speak(msg, TextToSpeech.QUEUE_ADD, null);
    }

    private void log(String msg)
    {
        Log.d(Game.LOG_TAG, msg);
    }
}
