package com.example.util;
/**
 * 文件上传和下载IO流处理操作的工具类
 */
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOUtils {
   private IOUtils(){
	   
   }
   public static void In2Out(InputStream in,OutputStream out) throws IOException
   {
	   byte [] b=new byte[1024];
	   int i=0;
	   while((i=in.read(b))!=-1)
	   {
		   out.write(b,0,i);
	   }
   }
   public static void In2OutClose(InputStream in,OutputStream out)
   {
	   if(in!=null)
	   {
		   try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			in=null;
		}
	   }
	   if(out!=null)
	   {
		   try {
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			out=null;
		}
	   }
   }
}
