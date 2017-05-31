/**
 * Eigpay.com Inc.
 * Copyright (c) 2014-2017 All Rights Reserved.
 */
package com.example.service;

import java.util.List;
import com.example.domain.Scene;

/**
 * 
 *
 */
public interface SceneService extends Service{
    /**
     * 添加景点
     * @param product 封装有景点信息的bean对象
     */
    
    void addScene(Scene scene);
    
    /**
     * 获取所有景点信息
     * @return 返回一个封装有一条条景点信息的bean对象的list集合，反之返回null；
     */
    List<Scene> findScene();
    /**
     * 通过id获取景点信息
     * @param id 商品id
     * @return 返回一个封装有景点信息的bean对象
     */
    Scene findSceneById(String id);
}
