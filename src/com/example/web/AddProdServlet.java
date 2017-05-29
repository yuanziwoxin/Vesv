package com.example.web;
/**
 * �����Ʒ
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.example.domain.Product;
import com.example.domain.UploadMsg;
import com.example.factory.BasicFactory;
import com.example.service.ProdService;
import com.example.util.IOUtils;
import com.example.util.PicUtils;
import org.apache.commons.beanutils.BeanUtils;

public class AddProdServlet extends HttpServlet {

	public void doGet(final HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProdService service=BasicFactory.getFactory().getService(ProdService.class);
		 try {
			 String encode=this.getServletContext().getInitParameter("encode");
             Map<String,String> paramMap=new HashMap<String,String>();//�Զ���һ��map
		  //1.�ϴ�ͼƬ
		    //��1������������
		   DiskFileItemFactory factory=new DiskFileItemFactory();
		   factory.setSizeThreshold(1024*100);//�����ڴ滺������С����������100KB 
		   factory.setRepository(new File(this.getServletContext().getRealPath("/WEB-INF/temp")));//�����ļ��ϴ�����ʱĿ¼
		    //(2).�������ļ��ϴ��ĺ�����
		    ServletFileUpload  fileupload=new ServletFileUpload(factory);
		    //�ж��ϴ��ļ��ı��Ƿ���multipart/form-data���ͣ�����ֱ�����쳣��
		    if(!fileupload.isMultipartContent(request))
		    {
		    	throw new RuntimeException("������ȷ���ļ��ϴ�����");
		    }
		    fileupload.setHeaderEncoding(encode);
		    fileupload.setFileSizeMax(10*1024*1024);//���õ����ļ��ϴ���С�����ֵ��
		    fileupload.setSizeMax(100*1024*1024);//�����ļ��ϴ����ܴ�С�����ֵ��
		     //�����ļ��ϴ����ȼ���
		       /**
		        *(1) long pBytesRead  ---��ʾ���ϴ��ļ��Ĵ�С
		        *     long pContentLength ---��ʾ�ϴ��ļ����ܴ�С 
					   int pItems  ---��ʾ�����ϴ��ļ�������
		        */
		      ProgressListener pgListener=new ProgressListener() {
               long beginTime=System.currentTimeMillis();//��ȡ�ļ��ϴ���ʼ��ʱ��
			   UploadMsg uplMsg=new UploadMsg();
               public void update(long pBytesRead, long pContentLength,
						int pItems) {
            	        try {
							Thread.sleep(2000);//Ϊ�˽�������ʾЧ��������ÿ�ζ�����1��
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					  //��ʾ���ϴ��ļ��Ĵ�С��ת���ɵ�λΪ"KB"�ģ�
					    BigDecimal pBR=new BigDecimal(pBytesRead).divide(new BigDecimal(1024),2,BigDecimal.ROUND_HALF_UP);
					  //��ʾ�ļ����ܴ�С��ת���ɵ�λΪ"KB"�ģ�
					    BigDecimal pCL=new BigDecimal(pContentLength).divide(new BigDecimal(1024),2,BigDecimal.ROUND_HALF_UP);
					    
					    //���ϴ��ļ��İٷֱ�
					    BigDecimal per=new BigDecimal(0);
					    per=pBR.multiply(new BigDecimal(100)).divide(pCL,2,BigDecimal.ROUND_HALF_UP);
					    uplMsg.setPer(per.toString());
					    //�ļ��ϴ����ٶ�
					    long nowTime=System.currentTimeMillis();//��ȡ��ǰʱ��
					    long useTime=nowTime-beginTime;//�ļ��ϴ�����ʱ��
					    BigDecimal uTime=new BigDecimal(useTime).divide(new BigDecimal(1000),2,BigDecimal.ROUND_HALF_UP);
					    BigDecimal speed=new BigDecimal(0);
					    if(!uTime.equals(new BigDecimal(0)))
					    {//��ֹ����Ϊ0
					    	  speed=pBR.divide(uTime,2);
					    }
					    uplMsg.setSpeed(speed.toString());
					    
					    //�ϴ��Ĵ���ʣ��ʱ��
					       BigDecimal remainBytes=pCL.subtract(pBR);//ʣ���ļ����ֽ���
					       BigDecimal remainTime=new BigDecimal(0);
					       if(!speed.equals(new BigDecimal(0)))
					       {
					    	   remainTime=remainBytes.divide(speed,0,BigDecimal.ROUND_HALF_UP);
					       }
					       uplMsg.setRemainTime(remainTime.toString());
					       
					      
					       //���ļ��ϴ�����Ϣ��uplMsg���浽 session����
					       request.getSession().setAttribute("uplMsg",uplMsg);
					       System.out.println("per= "+per+"; remainTime= "+remainTime+"S; speed= "+speed+" KB/S");
				}
			};
			fileupload.setProgressListener(pgListener);
		    
		    //(3).�����ļ��ϴ����������request
			List<FileItem> list=fileupload.parseRequest(request);
			//��������FileItem
			for(FileItem item:list)
			{
				if(item.isFormField())//�����һ���ϴ�������ͨ���ֶ���
				{
				     String name=item.getFieldName();
				     String value=item.getString(encode);
				     paramMap.put(name, value);
				}else{
					//����ϴ�����һ���ļ��ϴ�����
					String filename=item.getName();//ȡ���ļ�������
					//Ϊ��ֹ�ļ����д���·����ʹ���ַ����и����ʽȥ���ļ���֮ǰ��·��
					int index=filename.lastIndexOf("\\");
					if(index!=-1)
					{
						filename=filename.substring(index+1);
					}
					 
					String uuidFilename=UUID.randomUUID().toString()+"_"+filename;
					String path=this.getServletContext().getRealPath("/WEB-INF/upload");
					int hash=uuidFilename.hashCode();//��ȡhashֵ
					String hashStr=Integer.toHexString(hash);//ת��Ϊ16���Ƶ��ַ�����ʽ
					char [] cs=hashStr.toCharArray();//���ַ���ת��Ϊһ�������ַ���ÿһ���ַ�����һ��Ŀ¼��
					String imgurl="/WEB-INF/upload";
					for(char c:cs)
					{
						path+="/"+c;
						imgurl+="/"+c;
					}
					imgurl=imgurl+"/"+uuidFilename;
					paramMap.put("imgurl",imgurl);//��ͼƬ�ļ��Ĵ��·����װ��bean�����У�
					
					File uploadFile=new File(path);
					if(!uploadFile.exists())//����ļ��в������򴴽�
						uploadFile.mkdirs();
					InputStream in=item.getInputStream();
					OutputStream out=new FileOutputStream(new File(path,uuidFilename));
					
					IOUtils.In2Out(in, out);//�����ļ��Ķ�ȡ��д��
					IOUtils.In2OutClose(in, out);//�ر���Դ
					
					item.delete();//�ر�FileItem����������ɾ����ʱ�ļ���
					
					//��������ͼ
					PicUtils pic=new PicUtils(this.getServletContext().getRealPath(imgurl));
					pic.resizeByHeight(140);//�߶Ƚ�������
					
				}
			}
					//2.����service�еķ��������Ʒ
					Product product=new Product();
					BeanUtils.populate(product,paramMap);
					service.addProd(product);
					
					 //3.��ʾ��ӳɹ�����ת����ҳ
					response.getWriter().write("��Ʒ��ӳɹ���3��󽫻ص���ҳ��");
					response.setHeader("Refresh","3;url=/Estore/index.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		   
		   
		  
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
            doGet(request,response);
	}

}
