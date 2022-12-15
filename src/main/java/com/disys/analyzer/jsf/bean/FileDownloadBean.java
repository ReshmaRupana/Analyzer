/**
 * 
 */
package com.disys.analyzer.jsf.bean;

import java.io.InputStream;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 * @author Sajid
 * @since Dec 7, 2018
 */
@ManagedBean
public class FileDownloadBean {
	private String filePath;
    
    private StreamedContent file;
    
    public FileDownloadBean() {        
        InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/demo/images/optimus.jpg");
        file = new DefaultStreamedContent(stream, "image/jpg", "downloaded_optimus.jpg");
    }
 
    public StreamedContent getFile() {
        return file;
    }
    
    public void downloadExcelFile(String filePath){
    	InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/dowlnoads/"+filePath);
        file = new DefaultStreamedContent(stream, "application/vnd.ms-excel", filePath);
    }
 

	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(StreamedContent file) {
		this.file = file;
	}
}
