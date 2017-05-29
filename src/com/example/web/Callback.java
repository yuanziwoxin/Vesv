package com.example.web;
/**
 * ����֧��ȷ��Ӧ�����
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
    	// ��ûص���������
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
		// ���У�� --- �ж��ǲ���֧����˾֪ͨ��
		String hmac = request.getParameter("hmac");
		String keyValue = ResourceBundle.getBundle("merchantInfo").getString(
				"keyValue");
		// �Լ����������ݽ��м��� --- �Ƚ�֧����˾������hamc
		boolean isValid = PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd,
				r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid,
				r8_MP, r9_BType, keyValue);
		if(isValid){
			// ��Ӧ������Ч
			if ("1".equals(r9_BType)) {
				// ������ض���,��������Ϊ���֧���ɹ�
				response.getWriter().write("֧���ɹ���");
				response.setHeader("refresh","3;url=/Estore/index.jsp");
				/*
				 * 1.�����վ�ڹ���ʱ�����ﱾ��Ӧ�ó��������䣬��Ϊ���������Ӧ�����ǲ����ŵģ������������������֧���ɹ��ˣ�
				 *    ��Ϊ���������Ӧ�����ݿ��ܱ���Ϊ�۸ģ�
				 * 2.��������Ϊ��վ�Ǳ��صģ���֧����վ�ǹ����ģ������Ĳ����ܷ��ʵ����صģ�
				 */
				OrderService service=BasicFactory.getFactory().getService(OrderService.class);
				service.changePayState(r6_Order,1);
				
				return;
				
			} else if ("2".equals(r9_BType)) {
				// �ױ���Ե�ͨ��֪֧ͨ���ɹ����ױ�֪ͨ�ˣ�������������֧���ɹ���
				// �޸Ķ���״̬ Ϊ��֧��
				OrderService service=BasicFactory.getFactory().getService(OrderService.class);
				service.changePayState(r6_Order,1);
				// �ظ�֧����˾
				response.getWriter().write("success");
			}
		}else{
			throw new RuntimeException("���ݱ��۸��ˣ�����");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	      doGet(request,response);
	}

}
