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
            R.drawable.bs_dog, R.drawable.bs_zebra, R.drawable.bs_yellow, R.drawable.bs_xylophone, R.drawable.bs_dog, R.drawable.bs_dog, R.drawable.bs_dog, R.drawable.bs_dog, R.drawable.bs_dog, R.drawable.bs_dog, R.drawable.bs_dog,
            R.drawable.bs_dog, R.drawable.bs_dog, R.drawable.bs_dog, R.drawable.bs_dog, R.drawable.bs_dog, R.drawable.bs_dog, R.drawable.bs_dog, R.drawable.bs_dog, R.drawable.bs_dog, R.drawable.bs_dog, R.drawable.bs_dog,
            R.drawable.bs_dog, R.drawable.bs_dog, R.drawable.bs_dog, R.drawable.bs_dog
    };

}
