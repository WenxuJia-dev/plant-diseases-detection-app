package domain;

import java.io.Serializable;
/*Serializable
*它表明这个类的实例可以被序列化为一个字节序列，这些字节序列可以被保存到文件中、
* 通过网络传输，或者通过其他方式被持久化。随后，这些字节序列也可以被反序列化回原来的对象。
* */
public class WheatDomain implements Serializable {
    private String type;
    private String diseasetype;
    private String description;
    private String guide;
    private String reason;
    private String presence;
    private String pic;
    public WheatDomain(String type, String diseasetype, String description, String reason , String presence,String guide, String pic) {
        this.type = type;
        this.diseasetype = diseasetype;
        this.description = description;
        this.reason = reason;
        this.presence = presence;
        this.guide = guide;
        this.pic = pic;
    }
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getPresence() {
        return presence;
    }

    public void setPresence(String presence) {
        this.presence = presence;
    }



    public String getType() {
        return type;
    }

    public void setTitle(String title) {
        this.type = title;
    }

    public String getDiseasetype() {
        return diseasetype;
    }

    public void setDiseasetype(String diseasetype) {
        this.diseasetype = diseasetype;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGuide() {
        return guide;
    }

    public void setGuide(String guide) {
        this.guide = guide;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }



}
