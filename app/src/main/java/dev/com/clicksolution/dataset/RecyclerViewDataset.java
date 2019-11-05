package dev.com.clicksolution.dataset;

public class RecyclerViewDataset {
   private int image; //the return type will replace with String as image src later
    private String head_name, img_path, sub_name;


    public RecyclerViewDataset(int image, String head_name, String sub_name) {
        this.image = image;
        this.head_name = head_name;
        this.sub_name = sub_name;
    }

    public RecyclerViewDataset(String head_name, String img_path, String sub_name) {
        this.head_name = head_name;
        this.img_path = img_path;
        this.sub_name = sub_name;
    }

    public String getImg_path() {
        return img_path;
    }

    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }

    public String getSub_name() {
        return sub_name;
    }

    public void setSub_name(String sub_name) {
        this.sub_name = sub_name;
    }

    public RecyclerViewDataset() {
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return head_name;
    }

    public void setName(String name) {
        this.head_name = name;
    }
}
