package com.ratedforum.user.logger;

import org.springframework.stereotype.Service;

import ch.qos.logback.classic.Level;

@Service
public interface LoggerFactoryService {
	
	/**
	 * Set log level for ROOT logger
	 * @param level - Level to set
	 */
	public void setLogger(final Level level);
	
	public Level getLoggerLevel();
}
