package com.alexgames;

import java.util.ArrayList;

public class BabyScrollItemBuilder
{
    public ArrayList<BabyScrollItem> GetAlphabetItems() {

        ArrayList<BabyScrollItem> result = new ArrayList<BabyScrollItem>();

        for(String item : ABCs ){
            BabyScrollItem scrollItem = new BabyScrollItem();
            scrollItem.setItemText(item);
            result.add(scrollItem);
        }

        //TODO add image names

        return result;
    }

    static final String[] ABCs = new String[]{
            "A", "Z", "Y", "X", "W", "V", "U", "T", "S", "R", "Q",
            "P", "O", "N", "M", "L", "K", "J", "I", "H", "G", "F",
            "E", "D", "C", "B"
    };

}
