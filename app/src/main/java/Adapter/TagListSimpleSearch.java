package Adapter;

public class TagListSimpleSearch {

    private String TagId;
    private String TagText;
    private boolean selected;

    public String getTagId() {
        return TagId;
    }

    public void setTagId(String TagId) {
        this.TagId = TagId;
    }

    public String getTagText() {
        return TagText;
    }

    public void setTagText(String tagText) {
        TagText = tagText;
    }

    public boolean isSelected()
    {
        return selected;
    }

    public void setSelected(boolean selected)
    {
        this.selected = selected;
    }
}