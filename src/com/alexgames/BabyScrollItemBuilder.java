package com.alexgames;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Jason
 * Date: 1/8/12
 * Time: 9:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class BabyScrollItemBuilder
{
    private ArrayList<BabyScrollItem> BabyScrollItems;

    public ArrayList<BabyScrollItem> GetAlphabetItems() {

        ArrayList<BabyScrollItem> result = new ArrayList<BabyScrollItem>();

        for(String item : ABCs ){
            BabyScrollItem scrollItem = new BabyScrollItem();
            scrollItem.setItemText(item);
            result.add(scrollItem);
        }

        return result;
    }

    static final String[] ABCs = new String[]{
            "A", "Z", "Y", "X", "W", "V", "U", "T", "S", "R", "Q",
            "P", "O", "N", "M", "L", "K", "J", "I", "H", "G", "F",
            "E", "D", "C", "B"
    };

}
