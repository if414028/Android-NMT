package com.example.voldarex.neuralmachinetranslation.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

import com.example.voldarex.neuralmachinetranslation.R;

public class CustomFontEditTextView extends EditText implements View.OnFocusChangeListener {

    public static final String ANDROID_SCHEMA = "http://schemas.android.com/apk/res/android";

    public CustomFontEditTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        super.setOnFocusChangeListener(this);

        applyCustomFont(context, attrs);
    }

    public CustomFontEditTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        applyCustomFont(context, attrs);
    }

    private void applyCustomFont(Context context, AttributeSet attrs) {
        TypedArray attributeArray = context.obtainStyledAttributes(
                attrs,
                R.styleable.CustomFontTextView);
        String fontName = attributeArray.getString(R.styleable.CustomFontTextView_fontName);
        int textStyle = attrs.getAttributeIntValue(ANDROID_SCHEMA, "textStyle", Typeface.NORMAL);

        Typeface customFont = selectTypeface(context, fontName, textStyle);
        setTypeface(customFont);

        attributeArray.recycle();
    }

    private Typeface selectTypeface(Context context, String fontName, int textStyle) {
        if (fontName.contentEquals(context.getString(R.string.font_icon))) {
            return FontCache.getTypeface(context, "icomoon.ttf");
            /*
             * information about the TextView textStyle:
             * http://developer.android.com/reference/android/R.styleable.html#TextView_textStyle
             */
        } else if (fontName.contentEquals(context.getString(R.string.font_stoke))) {
            return FontCache.getTypeface(context, "Stoke.ttf");
        } else if (fontName.contentEquals(context.getString(R.string.font_roboto))) {
              /*
              information about the TextView textStyle:
              http://developer.android.com/reference/android/R.styleable.html#TextView_textStyle
              */
            switch (textStyle) {
                case Typeface.BOLD: // bold
                    return FontCache.getTypeface(context, "Montserrat-Bold.otf");

                case Typeface.ITALIC: // italic
                    return FontCache.getTypeface(context, "Montserrat-Italic.otf");

                case Typeface.NORMAL: // regular
                default:
                    return FontCache.getTypeface(context, "Montserrat-Regular.otf");
            }
        } else {
            // no matching font found
            // return null so Android just uses the standard font (Roboto)
            return null;
        }

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            setAlpha(1);
        } else {
            setAlpha((float) 0.5);
        }

    }

}
