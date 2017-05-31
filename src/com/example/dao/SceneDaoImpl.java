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
     *��Ӿ���
     * @see com.example.dao.SceneDao#addScene(com.example.domain.Scene)
     */
    public void addScene(Scene scene) {
         //ע�⣺scene.getImgurls()��õ�������ͼ,�������ȡ���Ǵ�ͼ
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
     *��ѯ�����о���
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
     *���ݾ����Ų�ѯ����
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
