package ro.ibm.myapplication;

public class Destination {
    private String name;
    private String descriptoin;
    private String url;

    public Destination(String name, String descriptoin, String url){
        this.name=name;
        this.descriptoin=descriptoin;
        this.url=url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptoin() {
        return descriptoin;
    }

    public void setDescriptoin(String descriptoin) {
        this.descriptoin = descriptoin;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
