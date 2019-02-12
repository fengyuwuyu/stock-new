package com.bdtd.card.web.stock.task.factory;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.bdtd.card.web.stock.task.ITask;

@Service
public class TaskFactory {
	
	@Autowired
	private ApplicationContext ac;

	public void start() {
		Map<String, ITask> map = ac.getBeansOfType(ITask.class);
		for (ITask task : map.values()) {
			task.start();
		}
	}
}
