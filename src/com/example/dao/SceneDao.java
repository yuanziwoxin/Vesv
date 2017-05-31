/**
 * Eigpay.com Inc.
 * Copyright (c) 2014-2017 All Rights Reserved.
 */
package com.example.dao;

import java.sql.SQLException;
import java.util.List;
import com.example.domain.Scene;

/**
 * 
 */
public interface SceneDao extends Dao{
    /**
     * �����ݿ�����Ӿ�����Ϣ��¼
     * @param scene ��װ�о�����Ϣ��bean����
     */
     void addScene(Scene scene);

     /**
      * ��ѯ�����о�����Ϣ
      * @return ����һ����װ��һ����������Ϣ��bean�����list����
      */
     List<Scene> findScene();
      /**
       * ͨ��id��ѯ������Ϣ
       * @param id ����id
       * @return  ����һ����װ�о�����Ϣ��bean����
       */
     Scene findSceneById(String id);
      

}
