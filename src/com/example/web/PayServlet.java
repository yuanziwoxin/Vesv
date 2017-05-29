package com.example.web;
/**
 * ����֧���������ױ�����֧��ƽ̨��
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.domain.Orders;
import com.example.factory.BasicFactory;
import com.example.service.OrderService;
import com.example.util.PaymentUtil;

public class PayServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		     //��ȡmerchantInfo�����ļ�(�洢���̻���ź��̻���Կ����Ӧ��ַ)
		     ResourceBundle bundle=ResourceBundle.getBundle("merchantInfo");
             //1.���֧����������
		     String p0_Cmd="Buy";//ҵ������
		     String p1_MerId=bundle.getString("p1_MerId");//�̻����
		     String p2_Order=request.getParameter("orderId");//�������
		     
		     OrderService service=BasicFactory.getFactory().getService(OrderService.class);
		     Orders orders=service.findOrderById(p2_Order);
		    // String p3_Amt=orders.getMoney()+"";//֧�����
		     String p3_Amt="0.01";//����ģ��֧�����
		     String p4_Cur="CNY";//���ױ���
		     String p5_Pid="";//��Ʒ����
		     String p6_Pcat="";//��Ʒ����
		     String p7_Pdesc="";//��Ʒ����
		     String p8_Url="http://localhost:8088/Estore/Callback";//�̻�����֧���ɹ����ݵĵ�ַ
		     String p9_SAF="0";//�ͻ���ַ
		     String pa_MP="";//�̻���չ��Ϣ
		     String pd_FrpId=request.getParameter("pd_FrpId");//֧��ͨ������
		     String pr_NeedResponse="1";//Ӧ�����
		     //ǩ�����ݣ� ����hmac ��Ҫ��ԿkeyValue��
		     String hmac=PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse,bundle.getString("keyValue"));
		     //2.�浽request���У�������ת����֧��ȷ��ҳ�棨config.jsp��
		        request.setAttribute("pd_FrpId", pd_FrpId);
				request.setAttribute("p0_Cmd", p0_Cmd);
				request.setAttribute("p1_MerId", p1_MerId);
				request.setAttribute("p2_Order", p2_Order);
				request.setAttribute("p3_Amt", p3_Amt);
				request.setAttribute("p4_Cur", p4_Cur);
				request.setAttribute("p5_Pid", p5_Pid);
				request.setAttribute("p6_Pcat", p6_Pcat);
				request.setAttribute("p7_Pdesc", p7_Pdesc);
				request.setAttribute("p8_Url", p8_Url);
				request.setAttribute("p9_SAF", p9_SAF);
				request.setAttribute("pa_MP", pa_MP);
				request.setAttribute("pr_NeedResponse", pr_NeedResponse);
				request.setAttribute("hmac", hmac);
                 //��ת��֧��ȷ��ҳ��
				request.getRequestDispatcher("/confirm.jsp").forward(request, response);
	}  

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
            doGet(request,response);
	}

}
