package com.java.command;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
	public Logger logger = Logger.getLogger(Command.class.getName());
	public String logMsg = "logMsg=================";
	
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable;
	
}
