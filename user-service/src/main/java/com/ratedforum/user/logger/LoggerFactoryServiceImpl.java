package com.ratedforum.user.logger;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

@Service
class LoggerFactoryServiceImpl implements LoggerFactoryService {

	public void setLogger(final Level level){
		Logger rootLogger = (Logger)LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
		rootLogger.setLevel(level);
	}
	
	@Override
	public Level getLoggerLevel() {
		Logger rootLogger = (Logger)LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
		return rootLogger.getLevel();
	}
}
