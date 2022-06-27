package org.pg7.scsp.dto;



import java.util.ArrayList;
import java.util.List;

/**
 * 分页工具类
 *
 * @author
 *
 */
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页通用类
 */
@Data
public class PageBean<T> implements Serializable {
    private int current;//当前页数
    private int size = 20;//每页显示数，这里是10
    private int pages;//总页数
    private int total;//总的记录数
    private List<T> records;//当前页面的数据集合

    private int prePage;

    private int nextPage;
    private int start;
    private int end;
    public PageBean(int pageNum, int pageSize, int totalRecord){

        this.size = pageSize;
        this.total = totalRecord;
        //计算总页数
        if (totalRecord % pageSize == 0){
            this.pages = totalRecord / pageSize;
        }else {
            this.pages = totalRecord / pageSize + 1;
        }
        pageNum = pageNum <= 0 ? 1 : pageNum;
        pageNum = Math.min(pageNum, pages);

        this.current = pageNum;
        this.nextPage = pageNum + 1;
        if(nextPage >= pages) nextPage = 1;

        this.prePage = pageNum-1;
        if(prePage <= 0) prePage = pages;

        // 开始索引
        int begin = 0;
        // 结束索引
        int end = 0;
        if (pageNum != pages) {
            begin = (pageNum - 1) * pageSize;
            end = begin + pageSize;
        } else {
            begin = (pageNum - 1) * pageSize;
            end = totalRecord;
        }
        this.start = begin;
        this.end = end;
    }
    public void addData(List<T> all){
        if(all == null || all.size() == 0)return;
        this.records = new ArrayList<>();
        for (int i = this.start; i < this.end; i++) {
            this.records.add(all.get(i));
        }
    }
}
