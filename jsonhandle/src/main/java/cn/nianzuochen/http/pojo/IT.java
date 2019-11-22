package cn.nianzuochen.http.pojo;

public class IT extends User {
    private String identify;
    private String company;
    private String direction;

    public String getIdentify() {
        return identify;
    }

    public void setIdentify(String identify) {
        this.identify = identify;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "IT{" +
                "identify='" + identify + '\'' +
                ", company='" + company + '\'' +
                ", direction='" + direction + '\'' +
                '}';
    }
}
