package com.example.web;
/**
 * 在线支付确认应答机制
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.factory.BasicFactory;
import com.example.service.OrderService;
import com.example.util.PaymentUtil;

public class Callback extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	// 获得回调所有数据
		String p1_MerId = request.getParameter("p1_MerId");
		String r0_Cmd = request.getParameter("r0_Cmd");
		String r1_Code = request.getParameter("r1_Code");
		String r2_TrxId = request.getParameter("r2_TrxId");
		String r3_Amt = request.getParameter("r3_Amt");
		String r4_Cur = request.getParameter("r4_Cur");
		String r5_Pid = request.getParameter("r5_Pid");
		String r6_Order = request.getParameter("r6_Order");
		String r7_Uid = request.getParameter("r7_Uid");
		String r8_MP = request.getParameter("r8_MP");
		String r9_BType = request.getParameter("r9_BType");
		String rb_BankId = request.getParameter("rb_BankId");
		String ro_BankOrderId = request.getParameter("ro_BankOrderId");
		String rp_PayDate = request.getParameter("rp_PayDate");
		String rq_CardNo = request.getParameter("rq_CardNo");
		String ru_Trxtime = request.getParameter("ru_Trxtime");
		// 身份校验 --- 判断是不是支付公司通知你
		String hmac = request.getParameter("hmac");
		String keyValue = ResourceBundle.getBundle("merchantInfo").getString(
				"keyValue");
		// 自己对上面数据进行加密 --- 比较支付公司发过来hamc
		boolean isValid = PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd,
				r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid,
				r8_MP, r9_BType, keyValue);
		if(isValid){
			// 响应数据有效
			if ("1".equals(r9_BType)) {
				// 浏览器重定向,不能相信为真的支付成功
				response.getWriter().write("支付成功！");
				response.setHeader("refresh","3;url=/Estore/index.jsp");
				/*
				 * 1.如果网站在公网时，这里本不应该出现这两句，因为浏览器的相应数据是不可信的，不能这样就相信真的支付成功了；
				 *    因为浏览器的响应的数据可能被人为篡改；
				 * 2.这里是因为网站是本地的，而支付网站是公网的，公网的不可能访问到本地的；
				 */
				OrderService service=BasicFactory.getFactory().getService(OrderService.class);
				service.changePayState(r6_Order,1);
				
				return;
				
			} else if ("2".equals(r9_BType)) {
				// 易宝点对点通信通知支付成功，易宝通知了，才能算是真正支付成功；
				// 修改订单状态 为已支付
				OrderService service=BasicFactory.getFactory().getService(OrderService.class);
				service.changePayState(r6_Order,1);
				// 回复支付公司
				response.getWriter().write("success");
			}
		}else{
			throw new RuntimeException("数据被篡改了！！！");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	      doGet(request,response);
	}

}
