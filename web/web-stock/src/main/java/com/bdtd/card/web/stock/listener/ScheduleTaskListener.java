package com.bdtd.card.web.stock.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.bdtd.card.web.stock.task.factory.TaskFactory;

@WebListener
public class ScheduleTaskListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(sce.getServletContext());
		TaskFactory taskFactory = ac.getBean(TaskFactory.class);
	    taskFactory.start();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}
