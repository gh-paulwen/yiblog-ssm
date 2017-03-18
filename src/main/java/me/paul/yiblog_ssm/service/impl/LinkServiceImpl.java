package me.paul.yiblog_ssm.service.impl;

import me.paul.yiblog_ssm.dto.ModelContent;
import me.paul.yiblog_ssm.entity.Link;
import me.paul.yiblog_ssm.service.LinkService;

import org.springframework.stereotype.Service;

@Service
public class LinkServiceImpl implements LinkService{

	@Override
	public ModelContent pass(long id) {
		ModelContent mc = new ModelContent();
		return mc;
	}

	@Override
	public ModelContent save(Link link) {
		ModelContent mc = new ModelContent();
		return mc;
	}

	@Override
	public ModelContent getPass() {
		ModelContent mc = new ModelContent();
		return mc;
	}

}
