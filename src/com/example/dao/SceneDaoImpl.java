/**
 * Eigpay.com Inc.
 * Copyright (c) 2014-2017 All Rights Reserved.
 */
package com.example.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.example.domain.Scene;
import com.example.util.TransactionManager;

/**
 * 
 */
public class SceneDaoImpl implements SceneDao{

    /** 
     *添加景点
     * @see com.example.dao.SceneDao#addScene(com.example.domain.Scene)
     */
    public void addScene(Scene scene) {
         //注意：scene.getImgurls()获得的是缩略图,而这里获取的是大图
        try {
             String sql="insert into scenes values(?,?,?,?)";
            QueryRunner runner=new QueryRunner(TransactionManager.getSource());
             runner.update(sql, scene.getId(),scene.getName(),scene.getImgurl(),scene.getDescription());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /** 
     *查询出所有景点
     * @see com.example.dao.SceneDao#findScene()
     */
    public List<Scene> findScene() {
        
        try {
            String sql="select * from scenes";
            QueryRunner runner=new QueryRunner(TransactionManager.getSource());
            return runner.query(sql, new BeanListHandler<Scene>(Scene.class));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        
    }

    /** 
     *根据景点编号查询景点
     * @see com.example.dao.SceneDao#findSceneById(java.lang.String)
     */
    public Scene findSceneById(String id) {
      
        try {
            String sql="select * from scenes where id=?";
            QueryRunner runner=new QueryRunner(TransactionManager.getSource());
            return runner.query(sql, new BeanHandler<Scene>(Scene.class),id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
