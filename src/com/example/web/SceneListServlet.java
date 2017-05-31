package com.example.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.domain.Product;
import com.example.domain.Scene;
import com.example.factory.BasicFactory;
import com.example.service.ProdService;
import com.example.service.SceneService;

/**
 * Servlet implementation class SceneListServlet
 */
public class SceneListServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           SceneService service=BasicFactory.getFactory().getService(SceneService.class);
            //1.调用service方法获取所有景点信息
            List<Scene> list=service.findScene();
           //2.将景点信息放到request域中，然后请求转发到index.jsp页面进行展示
            request.setAttribute("list",list);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           doGet(request,response);
    }

}
