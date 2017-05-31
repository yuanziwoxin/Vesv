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
     * ��Ӿ���
     * @param product ��װ�о�����Ϣ��bean����
     */
    
    void addScene(Scene scene);
    
    /**
     * ��ȡ���о�����Ϣ
     * @return ����һ����װ��һ����������Ϣ��bean�����list���ϣ���֮����null��
     */
    List<Scene> findScene();
    /**
     * ͨ��id��ȡ������Ϣ
     * @param id ��Ʒid
     * @return ����һ����װ�о�����Ϣ��bean����
     */
    Scene findSceneById(String id);
}
