package kr.edcan.sunrinton.models;

import android.graphics.drawable.Drawable;

/**
 * Created by Junseok Oh on 2017-07-24.
 */

public class Settings {
    private String title;
    private Drawable icon;
    private String content;

    public Settings(String title, Drawable icon, String content) {
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
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
