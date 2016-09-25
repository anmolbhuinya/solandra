package com.solandra.datauploader.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.solandra.datauploader.DataUploader;
import com.solandra.datauploader.impl.CsvDataUploader;

/**
 * Uploads data from UI to Cassandra
 * 
 * @author anmol
 *
 */
public class DataUploaderServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);

		// process only if its multipart content
		if (isMultipart) {
			// Create a factory for disk-based file items
			FileItemFactory factory = new DiskFileItemFactory();

			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);
			try {
				// Parse the request
				List<FileItem> multiparts = upload.parseRequest(request);
				File file = null;

				for (FileItem item : multiparts) {
					if (!item.isFormField()) {
						file = new File(item.getName());
						item.write(file);
					}
				}

				if (null != file) {
					DataUploader dataUploader = new CsvDataUploader();
					String keySpaceName = file.getName().substring(0, file.getName().indexOf("."));
					dataUploader.uploadDataToCassandra(keySpaceName, file);
					file.delete();
				}

				// File uploaded successfully
				request.setAttribute("message", "Your file has been uploaded!");
			} catch (Exception e) {
				request.setAttribute("message", "File Upload Failed due to "
						+ e);
			}
		} else {
			request.setAttribute("message",
					"This Servlet only handles file upload request");
		}
		request.getRequestDispatcher("/result.jsp").forward(request, response);
	}
}
