package me.paul.yiblog_ssm.mapper;

import me.paul.yiblog_ssm.entity.Announcement;

public interface AnnouncementMapper {
	
	Announcement getById(long id);
	
	void update(Announcement announcement);

}
