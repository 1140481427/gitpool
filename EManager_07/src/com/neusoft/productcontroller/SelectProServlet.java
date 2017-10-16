package com.neusoft.productcontroller;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.neusoft.productservice.ProductService;

/**
 * Servlet implementation class SelectProServlet
 */
@WebServlet("/SelectProServlet")
public class SelectProServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectProServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/xml;charset=UTF-8");
		int id = Integer.parseInt(request.getParameter("regionid"));
		Map<Integer, String> map = new ProductService().showProTowCate(id);
		Document doc = DocumentHelper.createDocument();
		Element rootElt = doc.addElement("items");
		for(Iterator<Entry<Integer, String>> iter = map.entrySet().iterator();iter.hasNext();){
		Element itemElt = rootElt.addElement("item");
		Element idElt = itemElt.addElement("id");
		Element nameElt = itemElt.addElement("name");
			Map.Entry<Integer, String> entry = iter.next();
			idElt.setText(String.valueOf(entry.getKey()));
			nameElt.setText(entry.getValue());
		}
		String xmlString = doc.asXML();
		System.out.println(xmlString);
		response.getWriter().print(xmlString);
		/*OutputFormat format  = OutputFormat.createPrettyPrint();
		format.setEncoding("GBK");
		XMLWriter xw = new XMLWriter(new FileWriter("f:\\test.xml"),format);
		xw.write(doc);
		xw.close();
		System.out.println("写文件成功!");*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
