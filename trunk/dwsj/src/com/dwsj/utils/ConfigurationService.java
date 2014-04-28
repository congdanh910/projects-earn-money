package com.dwsj.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ConfigurationService extends PropertiesConfiguration {
	private static Log logger = LogFactory.getLog(ConfigurationService.class);
	private static final String PROPS_RESOURCE = "configuration.properties";
	private static ConfigurationService instance = null;
	private String propsFile = PROPS_RESOURCE;

	public static final ConfigurationService getInstance() {
		if (instance == null) {
			instance = new ConfigurationService();
			instance.loadProperties();
		}
		return instance;
	}

	private void loadProperties() {
		try {
			File is = null;
			// Class c = getClass();
			is = new File(propsFile);
			loadProperties(is);

		} catch (Exception ioe) {
			logger.warn("Could not load engine properties", ioe);
		}
	}

	private void loadProperties(File is) throws Exception, ConfigurationException {
		try {
			instance.setFile(is);
			instance.setReloadingStrategy(new FileChangedReloadingStrategy());
			instance.setAutoSave(true);
			instance.load();
			logger.info("Loaded engine properties from " + propsFile);
		} catch (Exception e) {
			throw new IOException("Failed to load properties from " + propsFile);
		}
	}
}
