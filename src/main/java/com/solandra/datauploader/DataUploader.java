package com.solandra.datauploader;

import java.io.File;

/**
 * Data uploader for file
 * 
 * @author anmol
 *
 */
public interface DataUploader {
	
	/**
	 * uploads data to cassandra with given keyspacename<br>
	 * and given file. The table name (column family) would<br>
	 * be same as file name.<br>
	 * 
	 * @param keySpaceName
	 * @param file
	 * @return
	 */
	public boolean uploadDataToCassandra(String keySpaceName, File file);
	

}
