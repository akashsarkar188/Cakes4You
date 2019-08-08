package gd.rf.savvy.www.cakes4you;

public class Forgetset {
    String name,price,type,flavour,weight,image;
    public Forgetset(){}
    public Forgetset(String name, String price, String type, String flavour, String weight, String image) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.flavour = flavour;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFlavour() {
        return flavour;
    }

    public void setFlavour(String flavour) {
        this.flavour = flavour;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
