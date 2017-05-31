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
     * 向数据库中添加景点信息记录
     * @param scene 封装有景点信息的bean对象
     */
     void addScene(Scene scene);

     /**
      * 查询出所有景点信息
      * @return 返回一个封装有一条条景点信息的bean对像的list集合
      */
     List<Scene> findScene();
      /**
       * 通过id查询景点信息
       * @param id 景点id
       * @return  返回一个封装有景点信息的bean对象
       */
     Scene findSceneById(String id);
      

}
