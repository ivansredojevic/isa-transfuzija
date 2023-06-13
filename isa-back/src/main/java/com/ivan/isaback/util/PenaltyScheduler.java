package com.ivan.isaback.util;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ivan.isaback.service.ApplicationUserService;

@Component
public class PenaltyScheduler {
	
	private ApplicationUserService userService;
	
	public PenaltyScheduler(ApplicationUserService userService) {
		super();
		this.userService = userService;
	}

	@Transactional
	@Scheduled(cron = "0 * * * * *") // za potrebe odbrane penali se brisu svakog minuta
//	@Scheduled(cron = "0 0 0 1 * *") // okida se svakog prvog u mesecu
	public void clearPenalties() {
		userService.clearPenalties();
	}
	
}
