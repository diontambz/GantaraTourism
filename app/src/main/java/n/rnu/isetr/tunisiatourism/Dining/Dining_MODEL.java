package n.rnu.isetr.tunisiatourism.Dining;

public class Dining_MODEL {
    int image;
    String placeName,guide;

    public String getGuide() {
        return guide;
    }

    public void setGuide(String guide) {
        this.guide = guide;
    }

    public Dining_MODEL(int image, String placeName, String guide) {
        this.image = image;
        this.placeName = placeName;
        this.guide=guide;
     }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeNames) {
        this.placeName = placeName;
    }

}

