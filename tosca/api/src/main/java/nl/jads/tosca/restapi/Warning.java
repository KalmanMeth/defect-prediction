package nl.jads.tosca.restapi;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Warning {
    private String type;
    private Info info;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }
}
