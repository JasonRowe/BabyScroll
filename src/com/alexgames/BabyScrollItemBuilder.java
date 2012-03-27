package com.alexgames;

import java.util.ArrayList;

public class BabyScrollItemBuilder
{
    public ArrayList<BabyScrollItem> GetAlphabetItems() {

        ArrayList<BabyScrollItem> result = new ArrayList<BabyScrollItem>();

        int count = 0;

        for(String item : ABCs ){
            BabyScrollItem scrollItem = new BabyScrollItem();
            scrollItem.setItemText(item);
            scrollItem.setImageResource(ImageResources[count]);
            result.add(scrollItem);

            count ++;
        }

        return result;
    }

    static final String[] ABCs = new String[]{
        "A", "Z", "Y", "X", "W", "V", "U", "T", "S", "R", "Q",
        "P", "O", "N", "M", "L", "K", "J", "I", "H", "G", "F",
        "E", "D", "C", "B"
};

    static final int[] ImageResources = new int[]{
            R.drawable.bs_apple,
            R.drawable.bs_zebra,
            R.drawable.bs_yellow,
            R.drawable.bs_xylophone,
            R.drawable.bs_walrus,
            R.drawable.bs_van,
            R.drawable.bs_umbrella,
            R.drawable.bs_tiger,
            R.drawable.bs_snake,
            R.drawable.bs_rocket,
            R.drawable.bs_questionmark,
            R.drawable.bs_penguin,
            R.drawable.bs_octopus,
            R.drawable.bs_noodle,
            R.drawable.bs_mermaid,
            R.drawable.bs_love,
            R.drawable.bs_koala,
            R.drawable.bs_jungle,
            R.drawable.bs_icecream,
            R.drawable.bs_hamster,
            R.drawable.bs_grapes,
            R.drawable.bs_fire_truck,
            R.drawable.bs_elephant,
            R.drawable.bs_dog,
            R.drawable.bs_car,
            R.drawable.bs_ball
    };

}
