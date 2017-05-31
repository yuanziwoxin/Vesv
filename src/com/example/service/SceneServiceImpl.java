/**
 * Eigpay.com Inc.
 * Copyright (c) 2014-2017 All Rights Reserved.
 */
package com.example.service;

import java.util.List;
import java.util.UUID;
import com.example.dao.SceneDao;
import com.example.domain.Scene;
import com.example.factory.BasicFactory;

/**
 * 
 */
public class SceneServiceImpl implements SceneService{
   private SceneDao dao=BasicFactory.getFactory().getDao(SceneDao.class);
    /** 
     * @see com.example.service.SceneService#addScene(com.example.domain.Scene)
     */
    public void addScene(Scene scene) {
        scene.setId(UUID.randomUUID().toString());
        dao.addScene(scene);//添加景点
    }

    /** 
     * @see com.example.service.SceneService#findScene()
     */
    public List<Scene> findScene() {
        return dao.findScene();//查询景点
    }

    /** 
     * @see com.example.service.SceneService#findSceneById(java.lang.String)
     */
    public Scene findSceneById(String id) {
        return dao.findSceneById(id);//通过id查询景点
    }
    
}
