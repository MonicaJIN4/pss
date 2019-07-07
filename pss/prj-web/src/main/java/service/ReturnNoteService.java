package service;

import bean.ReturnNote;

import java.util.List;

public interface ReturnNoteService {

    List<ReturnNote> list();
    Long insert(ReturnNote bean);
    Long update(ReturnNote bean);
    Long delete(Long id);
    ReturnNote load(Long id);
    ReturnNote loadByName(String name);
    Long count();
    List<ReturnNote> pager(Long pageNum, Long pageSize);
    Long countByName(String name);
    List<ReturnNote> pagerByName(String name, Long pageNum, Long pageSize);
    List<ReturnNote> pagerByPurId(String name, Long pageNum, Long pageSize);
}
