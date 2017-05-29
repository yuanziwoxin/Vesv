package com.example.web;
/**
 * 添加商品
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
             Map<String,String> paramMap=new HashMap<String,String>();//自定义一个map
		  //1.上传图片
		    //（1）创建工厂类
		   DiskFileItemFactory factory=new DiskFileItemFactory();
		   factory.setSizeThreshold(1024*100);//设置内存缓冲区大小，这里设置100KB 
		   factory.setRepository(new File(this.getServletContext().getRealPath("/WEB-INF/temp")));//设置文件上传的临时目录
		    //(2).生产出文件上传的核心类
		    ServletFileUpload  fileupload=new ServletFileUpload(factory);
		    //判断上传文件的表单是否是multipart/form-data类型，不是直接抛异常；
		    if(!fileupload.isMultipartContent(request))
		    {
		    	throw new RuntimeException("不是正确的文件上传表单！");
		    }
		    fileupload.setHeaderEncoding(encode);
		    fileupload.setFileSizeMax(10*1024*1024);//设置单个文件上传大小的最大值；
		    fileupload.setSizeMax(100*1024*1024);//设置文件上传的总大小的最大值；
		     //设置文件上传进度监听
		       /**
		        *(1) long pBytesRead  ---表示已上传文件的大小
		        *     long pContentLength ---表示上传文件的总大小 
					   int pItems  ---表示正在上传文件的项数
		        */
		      ProgressListener pgListener=new ProgressListener() {
               long beginTime=System.currentTimeMillis();//获取文件上传开始的时间
			   UploadMsg uplMsg=new UploadMsg();
               public void update(long pBytesRead, long pContentLength,
						int pItems) {
            	        try {
							Thread.sleep(2000);//为了进度条显示效果，让它每次都休眠1秒
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					  //表示已上传文件的大小（转换成单位为"KB"的）
					    BigDecimal pBR=new BigDecimal(pBytesRead).divide(new BigDecimal(1024),2,BigDecimal.ROUND_HALF_UP);
					  //表示文件的总大小（转换成单位为"KB"的）
					    BigDecimal pCL=new BigDecimal(pContentLength).divide(new BigDecimal(1024),2,BigDecimal.ROUND_HALF_UP);
					    
					    //已上传文件的百分比
					    BigDecimal per=new BigDecimal(0);
					    per=pBR.multiply(new BigDecimal(100)).divide(pCL,2,BigDecimal.ROUND_HALF_UP);
					    uplMsg.setPer(per.toString());
					    //文件上传的速度
					    long nowTime=System.currentTimeMillis();//获取当前时间
					    long useTime=nowTime-beginTime;//文件上传所花时间
					    BigDecimal uTime=new BigDecimal(useTime).divide(new BigDecimal(1000),2,BigDecimal.ROUND_HALF_UP);
					    BigDecimal speed=new BigDecimal(0);
					    if(!uTime.equals(new BigDecimal(0)))
					    {//防止除数为0
					    	  speed=pBR.divide(uTime,2);
					    }
					    uplMsg.setSpeed(speed.toString());
					    
					    //上传的大致剩余时间
					       BigDecimal remainBytes=pCL.subtract(pBR);//剩余文件的字节数
					       BigDecimal remainTime=new BigDecimal(0);
					       if(!speed.equals(new BigDecimal(0)))
					       {
					    	   remainTime=remainBytes.divide(speed,0,BigDecimal.ROUND_HALF_UP);
					       }
					       uplMsg.setRemainTime(remainTime.toString());
					       
					      
					       //将文件上传的信息（uplMsg）存到 session域中
					       request.getSession().setAttribute("uplMsg",uplMsg);
					       System.out.println("per= "+per+"; remainTime= "+remainTime+"S; speed= "+speed+" KB/S");
				}
			};
			fileupload.setProgressListener(pgListener);
		    
		    //(3).利用文件上传核心类解析request
			List<FileItem> list=fileupload.parseRequest(request);
			//遍历所有FileItem
			for(FileItem item:list)
			{
				if(item.isFormField())//如果是一个上传的是普通的字段项
				{
				     String name=item.getFieldName();
				     String value=item.getString(encode);
				     paramMap.put(name, value);
				}else{
					//如果上传的是一个文件上传对象
					String filename=item.getName();//取得文件的名字
					//为防止文件名中带有路径，使用字符串切割的形式去掉文件名之前的路径
					int index=filename.lastIndexOf("\\");
					if(index!=-1)
					{
						filename=filename.substring(index+1);
					}
					 
					String uuidFilename=UUID.randomUUID().toString()+"_"+filename;
					String path=this.getServletContext().getRealPath("/WEB-INF/upload");
					int hash=uuidFilename.hashCode();//获取hash值
					String hashStr=Integer.toHexString(hash);//转换为16进制的字符串形式
					char [] cs=hashStr.toCharArray();//将字符串转化为一个个的字符，每一个字符代表一级目录；
					String imgurl="/WEB-INF/upload";
					for(char c:cs)
					{
						path+="/"+c;
						imgurl+="/"+c;
					}
					imgurl=imgurl+"/"+uuidFilename;
					paramMap.put("imgurl",imgurl);//将图片文件的存放路径封装到bean对象中；
					
					File uploadFile=new File(path);
					if(!uploadFile.exists())//如果文件夹不存在则创建
						uploadFile.mkdirs();
					InputStream in=item.getInputStream();
					OutputStream out=new FileOutputStream(new File(path,uuidFilename));
					
					IOUtils.In2Out(in, out);//进行文件的读取和写入
					IOUtils.In2OutClose(in, out);//关闭资源
					
					item.delete();//关闭FileItem的输入流后，删除临时文件；
					
					//生成缩略图
					PicUtils pic=new PicUtils(this.getServletContext().getRealPath(imgurl));
					pic.resizeByHeight(140);//高度进行缩略
					
				}
			}
					//2.调用service中的方法添加商品
					Product product=new Product();
					BeanUtils.populate(product,paramMap);
					service.addProd(product);
					
					 //3.提示添加成功，跳转回主页
					response.getWriter().write("商品添加成功！3秒后将回到主页！");
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
