package kr.edcan.sunrinton.models;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;

import kr.edcan.sunrinton.utils.AppController;

/**
 * Created by Junseok Oh on 2017-07-24.
 */

public class Settings {
    private String title;
    private int icon;
    private String content;

    public Settings(String title, @DrawableRes int icon, String content) {
        this.title = title;
        this.icon = icon;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Drawable getIcon() {
        return (icon == 0) ? null : ContextCompat.getDrawable(AppController.getContext(), icon);
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
