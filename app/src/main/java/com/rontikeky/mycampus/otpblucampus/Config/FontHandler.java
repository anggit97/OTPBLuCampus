package com.rontikeky.mycampus.otpblucampus.Config;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by Anggit on 03/10/2017.
 */

public class FontHandler {
    private String FONT_NAME = "Gotham/GothamRounded-Medium.otf";
    private String FONT_NAME_BOLD = "Gotham/GothamRounded-Bold.otf";
    private String FONT_NAME_ITALIC = "Gotham/GothamRounded-MediumItalic.otf";
    Typeface typeface;
    Context context;

    public FontHandler(Context context) {
        this.context = context;
        typeface =  Typeface.createFromAsset(context.getAssets(), FONT_NAME);
    }

    public void setFont(String customFont){
        FONT_NAME = customFont;
    }

    public Typeface getFont(){
        return typeface;
    }

    public Typeface getFontBold(){
        return typeface = Typeface.createFromAsset(context.getAssets(),FONT_NAME_BOLD);
    }
}
