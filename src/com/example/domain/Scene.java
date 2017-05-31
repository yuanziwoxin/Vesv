package com.example.domain;
/**
 * 1.дһ��get����ͨ��imgurl��ȡ����ͼ��imgurls����·��,ʹ�ÿ���ͨ��bean����������ͼ��·��
 * 2.����Ӧ��ͨ��id��hashֵ�����ж��Ƿ���ͬһ�����㣬��Ϊhashֵ���Ǿ���Ψһ�ģ���Scene.java��id����ȡhashֵ����
 */
import java.io.Serializable;

public class Scene implements Serializable{
    private String id;//������
    private String name;//��������
    private String imgurl;//ͼƬ��ַ
    private String description;//����
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
         * дһ��get����ͨ��imgurl��ȡ����ͼ��imgurls����·��,ʹ�ÿ���ͨ��bean����������ͼ��·��
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
     *����Ӧ��ͨ��id��hashֵ�����ж��Ƿ���ͬһ�����㣬��Ϊhashֵ���Ǿ���Ψһ�ģ���Scene.java��id����ȡhashֵ���� 
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
