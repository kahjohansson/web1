package br.ufscar.dc.dsw.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet(urlPatterns = "/upload")
public class UploadController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UploadController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String complete_path = "empty";

		if (ServletFileUpload.isMultipartContent(request)) {
			try {
				List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
				for (FileItem item : multiparts) {
					if (!item.isFormField()) {
						String name = new File(item.getName()).getName();
						complete_path = "/home/mayumi/Documents/UFSCar/DSW1/web1/Projeto_1/upload_files"
								+ File.separator + name;
						item.write(new File(complete_path));
					}
				}
				// File uploaded successfully
				request.setAttribute("gurumessage", "File Uploaded Successfully");
			} catch (Exception e) {
				request.setAttribute("gurumessage", "File Upload Failed due to " + e);
			}
		} else {

			request.setAttribute("gurumessage", "No File found");
		}
		request.getSession().setAttribute("caminhocurriculo", complete_path);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/profissional/cadastra");
		rd.forward(request, response);
		// request.getRequestDispatcher("/result.jsp").forward(request, response);

	}

}
