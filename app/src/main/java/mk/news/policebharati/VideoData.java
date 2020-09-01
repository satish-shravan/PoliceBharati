package mk.news.policebharati;

public class VideoData {
    String image_url;
    String link;



    //Default constucter is required as we are using parameterised constructer
    VideoData()
    {

    }

    public VideoData(String image_url, String link) {
        this.image_url = image_url;
        this.link = link;

    }



    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
