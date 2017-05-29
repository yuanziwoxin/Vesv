package com.example.web;
/**
 * 在线支付（接入易宝在线支付平台）
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
		     //获取merchantInfo配置文件(存储着商户编号和商户密钥及响应地址)
		     ResourceBundle bundle=ResourceBundle.getBundle("merchantInfo");
             //1.获得支付基本数据
		     String p0_Cmd="Buy";//业务类型
		     String p1_MerId=bundle.getString("p1_MerId");//商户编号
		     String p2_Order=request.getParameter("orderId");//订单编号
		     
		     OrderService service=BasicFactory.getFactory().getService(OrderService.class);
		     Orders orders=service.findOrderById(p2_Order);
		    // String p3_Amt=orders.getMoney()+"";//支付金额
		     String p3_Amt="0.01";//这里模拟支付金额
		     String p4_Cur="CNY";//交易币种
		     String p5_Pid="";//商品名称
		     String p6_Pcat="";//商品类型
		     String p7_Pdesc="";//商品描述
		     String p8_Url="http://localhost:8088/Estore/Callback";//商户接收支付成功数据的地址
		     String p9_SAF="0";//送货地址
		     String pa_MP="";//商户扩展信息
		     String pd_FrpId=request.getParameter("pd_FrpId");//支付通道编码
		     String pr_NeedResponse="1";//应答机制
		     //签名数据（ 加密hmac 需要密钥keyValue）
		     String hmac=PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse,bundle.getString("keyValue"));
		     //2.存到request域中，并请求转发到支付确认页面（config.jsp）
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
                 //跳转到支付确认页面
				request.getRequestDispatcher("/confirm.jsp").forward(request, response);
	}  

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
            doGet(request,response);
	}

}
