package com.solandra.datauploader;

import java.io.File;

public interface DataUploader {
	
	public boolean uploadDataToCassandra(String keySpaceName, File file);
	

}
