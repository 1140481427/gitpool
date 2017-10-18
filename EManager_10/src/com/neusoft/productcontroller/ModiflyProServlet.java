package com.neusoft.productcontroller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;

import com.neusoft.accountdao.DaoException;
import com.neusoft.entity.T_Product_List;
import com.neusoft.productservice.ProductService;

/**
 * Servlet implementation class ModiflyProServlet
 */
@WebServlet("/modiflypro")
public class ModiflyProServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModiflyProServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		modifly(request,response);
	}
	
	protected void modifly(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");  
	      RequestContext requestContext = new ServletRequestContext(request);  
	      if(FileUpload.isMultipartContent(requestContext)){  
	    	  //System.out.println("---------路径"+request.getSession().getServletContext().getRealPath("upload"));
	    	  DiskFileItemFactory factory = new DiskFileItemFactory();  
	    	  	//E:/javaweb/EManager_04/upload
	    	  	factory.setRepository(new File("E:/javaweb/EManager_09/WebContent/upload/"));
	            //factory.setRepository(new File(request.getSession().getServletContext().getRealPath("upload")+"/"));  
	            ServletFileUpload upload = new ServletFileUpload(factory);  
	            //upload.setHeaderEncoding("");
	            upload.setHeaderEncoding("utf-8");  
	            upload.setSizeMax(2000000);  
	            List items = new ArrayList();  
	            try {  
	               items = upload.parseRequest(request);  
	            } catch (FileUploadException e1) {  
	               System.out.println("文件上传发生错误" + e1.getMessage());  
	            }  
	  
	            Iterator it = items.iterator();
	            int pid = 0;
	            String name = null;
	            String region = null;
	            String pro_no = null;
	            String pro_spec = null;
	            String pro_img_src = null;
	            double pro_price = 0.0;
	            int pro_hits = 0;
	            int pro_rem = 0;
	            int pro_tj = 0;
	            int pro_sx = 0;
	            String edit = null;
	            while(it.hasNext()){  
	                FileItem fileItem = (FileItem) it.next();
	               
	                //如果是普通文本字段
	                if(fileItem.isFormField()){        
	                	
	                	/**
	                	 *	String name = request.getParameter("pro_name");
							String region = request.getParameter("region");
							String pro_no = request.getParameter("pro_no");
							String pro_spec = request.getParameter("pro_spec");
							String pro_img_src = request.getParameter("pro_img_src");
							
							dou pro_price =Integer.parseInt(request.getParameter("pro_price"));
							int pro_hits = Integer.parseInt(request.getParameter("pro_hits"));
							int pro_rem = Integer.parseInt(request.getParameter("pro_rem"));
							int pro_tj = Integer.parseInt(request.getParameter("pro_tj"));
							int pro_sx = Integer.parseInt(request.getParameter("pro_sx"));
							
							String edit = request.getParameter("edit");
	                	 */
	                	
	                	   if("pro_name".equals(fileItem.getFieldName())){
	                		  name = new String (fileItem.getString("utf-8"));
	                		  System.out.println(name);
	                	   }else if("region".equals(fileItem.getFieldName())){
	                		  region = new String (fileItem.getString("utf-8"));
	                		  System.out.println(region);
	                	   }else if("pro_no".equals(fileItem.getFieldName())){
	                		  pro_no = new String (fileItem.getString("utf-8"));
	                		  System.out.println(pro_no);
	                	   }else if("pro_spec".equals(fileItem.getFieldName())){
	                		  pro_spec = new String (fileItem.getString("utf-8"));
	                		  
	                		  System.out.println(pro_spec);
	                	   }else if("pro_price".equals(fileItem.getFieldName())){
	                		   	  pro_price = Double.parseDouble(new String (fileItem.getString("utf-8")));
		                		  System.out.println(pro_price);
	                	   }else if("pro_hits".equals(fileItem.getFieldName())){
		                		  pro_hits = Integer.parseInt(new String (fileItem.getString("utf-8")));
		                		  System.out.println(pro_hits);
	                	   }else if("pro_rem".equals(fileItem.getFieldName())){
	                		   	  pro_rem = Integer.parseInt(new String (fileItem.getString("utf-8")));
		                		  System.out.println(pro_rem);
	                	   }else if("pro_tj".equals(fileItem.getFieldName())){
	                		      pro_tj = Integer.parseInt(new String (fileItem.getString("utf-8")));
		                		  System.out.println(pro_tj);
	                	   }else if("pro_sx".equals(fileItem.getFieldName())){
	                		   	  pro_sx = Integer.parseInt(new String (fileItem.getString("utf-8")));
		                		  System.out.println(pro_sx);
	                	   }else if("edit".equals(fileItem.getFieldName())){
		                		  edit = new String (fileItem.getString("utf-8"));
		                		  System.out.println(edit);
	                	   }else if("pid".equals(fileItem.getFieldName())){
		                		  pid = Integer.parseInt(new String (fileItem.getString("utf-8")));
		                		  System.out.println(edit);
	                	   }
	                     /*  System.out.println(fileItem.getFieldName() + "   " + fileItem.getName() + "   " + new String  
	                                           (fileItem.getString().getBytes("iso8859-1"), "gbk"));  */
	                }else{  
	                       System.out.println(fileItem.getFieldName() + "   " +   
	                               fileItem.getName() + "   " + fileItem.isInMemory() + "    " +   
	                               fileItem.getContentType() + "   " + fileItem.getSize());  
	                       pro_img_src = new String (fileItem.getName());
	                       System.out.println("图片名称:"  + pro_img_src);
	                      
	                       if(fileItem.getName()!=null && fileItem.getSize()!=0){  
	                               File fullFile = new File(fileItem.getName());  
	                               File newFile = new File("E:/javaweb/EManager_09/WebContent/upload/" + fullFile.getName());  
	                               try {  
	                                   fileItem.write(newFile);  
	                               } catch (Exception e) {  
	                                      e.printStackTrace();  
	                               }  
	                       }else{  
	                               System.out.println("文件没有选择 或 文件内容为空");  
	                       }  
	                }  
	        
	            } 
		
		T_Product_List pd = new T_Product_List(pid, pro_no, name, region, pro_spec, pro_img_src, pro_price, pro_hits, pro_rem, pro_tj, pro_sx, edit);
		
			try {
				ProductService ps = new ProductService();
				if(ps.modiflyPro(pd)) {
					System.out.println("修改成功");
					response.sendRedirect("PageModelShowPro");
				} else {
					String error = "修改失败";
					request.setAttribute("error", error);
					request.getRequestDispatcher("showmodifly?pid="+pid).forward(request, response);;
				}
			} catch (DaoException e) {
				System.out.println(e.getMessage());
			}
	   }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
