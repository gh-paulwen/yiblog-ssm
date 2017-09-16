package me.paul.yiblog_ssm.service.impl;

import me.paul.yiblog_ssm.dto.ModelContent;
import me.paul.yiblog_ssm.entity.*;
import me.paul.yiblog_ssm.mapper.*;
import me.paul.yiblog_ssm.service.PassageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.*;

@Service
public class PassageServiceImpl implements PassageService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private SubCategoryMapper subCategoryMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private ReplyMapper replyMapper;

    @Autowired
    private AnnouncementMapper announcementMapper;

    @Autowired
    private PassageMapper passageMapper;

    @Override
    public ModelContent get(long id) {
        ModelContent mc = new ModelContent("post");
        Passage passage = null;
        passage = passageMapper.getById(id);
        if (passage == null) {
            passage = passageMapper.getById(id);
        }
        if (passage == null) {
            mc.save("message", "获取失败");
            mc.setDestination("message");
            return mc;
        }
        if (passage.getAvailable() == 0) {
            mc.save("message", "获取失败");
            mc.setDestination("message");
            return mc;
        }
        List<Comment> listComment = commentMapper.getByPassage(passage.getId());
        Map<Comment, Reply> mapCommentReply = new HashMap<Comment, Reply>();
        for (Comment c : listComment) {
            Reply reply = replyMapper.getByComment(c.getId());
            mapCommentReply.put(c, reply);
        }
        mc.save("passage", passage);
        mc.save("mapCommentReply", mapCommentReply);
        Comment comment = new Comment();
        comment.setPassage(id);
        mc.save("comment", comment);
        return mc;
    }

    @Transactional
    @Override
    public synchronized ModelContent edit(Passage passage, long before) {
        ModelContent mc = new ModelContent();
        SubCategory subCategory = subCategoryMapper.getById(passage.getSubCategory().getId());
        if (Long.compare(before, passage.getSubCategory().getId()) != 0) {
            SubCategory beforeSubCategory = subCategoryMapper.getById(before);
            beforeSubCategory.setPassageCount(beforeSubCategory.getPassageCount() - 1);
            beforeSubCategory.getCategory().setPassageCount(beforeSubCategory.getCategory().getPassageCount() - 1);
            subCategory.setPassageCount(subCategory.getPassageCount() + 1);
            subCategory.getCategory().setPassageCount(subCategory.getCategory().getPassageCount() + 1);
            subCategoryMapper.update(beforeSubCategory);
            subCategoryMapper.update(subCategory);
            categoryMapper.update(beforeSubCategory.getCategory());
            categoryMapper.update(subCategory.getCategory());
        }
        passage.setSubCategory(subCategory);
        passage.setCategory(subCategory.getCategory());
        passageMapper.update(passage);
        return mc;
    }

    @Transactional
    @Override
    public ModelContent save(Passage passage, User author) {
        ModelContent mc = new ModelContent();
        synchronized (this) {
            SubCategory subCategory = subCategoryMapper.getById(passage
                    .getSubCategory().getId());
            subCategory.setPassageCount(subCategory.getPassageCount() + 1);
            Category category = categoryMapper.getById(subCategory
                    .getCategory().getId());
            Announcement lastUpdate = announcementMapper.getById(3l);
            lastUpdate.setTime(new Date());
            category.setPassageCount(category.getPassageCount() + 1);
            passage.setCategory(category);
            passage.setAuthor(author);
            passage.setWritetime(new Date());
            passageMapper.insert(passage);
            announcementMapper.update(lastUpdate);
            subCategoryMapper.update(subCategory);
            categoryMapper.update(category);
        }
        Passage completePassage = passageMapper.getById(passage.getId());
        mc.save("message", "添加成功");
        return mc;
    }

    @Override
    public ModelContent page(int page, int passagePerPage) {
        ModelContent mc = new ModelContent();
        int from = (page - 1) * passagePerPage;
        List<Passage> listPassage = passageMapper.page(from, passagePerPage);
        mc.save("listPassage", listPassage);
        int totalPassageCount = passageMapper.passageCount();
        if (totalPassageCount <= passagePerPage * page) {
            mc.save("nextPage", page);
        } else {
            mc.save("nextPage", page + 1);
        }
        if (page == 1) {
            mc.save("lastPage", page);
        } else {
            mc.save("lastPage", page - 1);
        }
        return mc;
    }

    @Override
    public ModelContent categoryPage(long category, int page, int passagePerPage) {
        ModelContent mc = new ModelContent();
        int from = (page - 1) * passagePerPage;
        List<Passage> list = passageMapper.categoryPage(category, from, passagePerPage);
        mc.save("listPassage", list);
        mc.save("category", category);
        int categoryPassageCount = passageMapper.categoryPassageCount(category);
        if (categoryPassageCount <= passagePerPage * page) {
            mc.save("nextPage", page);
        } else {
            mc.save("nextPage", page + 1);
        }
        if (page == 1) {
            mc.save("lastPage", page);
        } else {
            mc.save("lastPage", page - 1);
        }
        return mc;
    }

    @Override
    public ModelContent search(String content) throws UnsupportedEncodingException {
        ModelContent mc = new ModelContent();
        content = new String(content.getBytes("ISO-8859-1"), "UTF-8");
        content = "%" + content + "%";
        List<Passage> list = passageMapper.search(content);
        mc.save("listPassage", list);
        mc.save("hide", 1);
        return mc;
    }

    @Override
    public Passage onlyPassage(long id) {
        return passageMapper.getById(id);
    }

    @Override
    public boolean checkIdAuthor(long id, long author) {
        Passage passage = passageMapper.getByIdAndAuthor(id, author);
        return passage != null;
    }

    @Transactional
    @Override
    public synchronized ModelContent devailable(long id) {
        ModelContent mc = new ModelContent("message");
        Passage passage = passageMapper.getById(id);
        if (passage == null || passage.getAvailable() == 0) {
            mc.save("message", "修改出错");
            return mc;
        }
        passage.setAvailable(0);
        Category category = passage.getCategory();
        SubCategory subCategory = passage.getSubCategory();
        category.setPassageCount(category.getPassageCount() - 1);
        subCategory.setPassageCount(subCategory.getPassageCount() - 1);
        categoryMapper.update(category);
        subCategoryMapper.update(subCategory);
        passageMapper.update(passage);
        mc.save("message", "修改成功");
        return mc;
    }

    @Transactional
    @Override
    public synchronized ModelContent available(long id) {
        ModelContent mc = new ModelContent("message");
        Passage passage = passageMapper.getById(id);
        if (passage == null || passage.getAvailable() == 1) {
            mc.save("message", "修改出错");
            return mc;
        }
        passage.setAvailable(1);
        Category category = passage.getCategory();
        SubCategory subCategory = passage.getSubCategory();
        category.setPassageCount(category.getPassageCount() + 1);
        subCategory.setPassageCount(subCategory.getPassageCount() + 1);
        categoryMapper.update(category);
        subCategoryMapper.update(subCategory);
        passageMapper.update(passage);
        mc.save("message", "修改成功");
        return mc;
    }

}
