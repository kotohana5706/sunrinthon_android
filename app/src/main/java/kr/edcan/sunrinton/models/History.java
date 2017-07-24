package kr.edcan.sunrinton.models;

/**
 * Created by Junseok Oh on 2017-07-24.
 */

public class History {
    private String title;
    private String content;
    private int price;

    public History(String title, String content, int price) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPrice() {
        return price + "";
    }
}
