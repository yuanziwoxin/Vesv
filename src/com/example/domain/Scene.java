package com.example.domain;
/**
 * 1.写一个get方法通过imgurl获取缩略图（imgurls）的路径,使得可以通过bean对象获得缩略图的路径
 * 2.这里应该通过id的hash值进行判断是否是同一个景点，因为hash值才是绝对唯一的（在Scene.java对id进行取hash值）；
 */
import java.io.Serializable;

public class Scene implements Serializable{
    private String id;//景点编号
    private String name;//景点名称
    private String imgurl;//图片地址
    private String description;//描述
    public Scene()
    {
        
    }
  
  public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getImgurl() {
        return imgurl;
    }
    public String getImgurls() {
        /*
         * 写一个get方法通过imgurl获取缩略图（imgurls）的路径,使得可以通过bean对象获得缩略图的路径
         */
        String imgurls=imgurl.substring(0, imgurl.lastIndexOf("."))
                    +"_s"+imgurl.substring(imgurl.lastIndexOf("."));
        return imgurls;
    }
    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    /*
     *这里应该通过id的hash值进行判断是否是同一个景点，因为hash值才是绝对唯一的（在Scene.java对id进行取hash值）； 
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Scene other = (Scene) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
