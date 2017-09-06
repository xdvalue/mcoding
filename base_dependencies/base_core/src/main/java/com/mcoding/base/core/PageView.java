package com.mcoding.base.core;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LiBing on 2014/6/13.
 * 封装分页信息逻辑的类
 */
public class PageView<T extends Serializable> implements Serializable {
    private static final long serialVersionUID = 7524772792315029450L;

    //当前页号(默认第一页)
    private int pageNo = 1;

    //分页大小(默认10条)
    private int pageSize = 10;

    //分页后的总页数
    private int pageCount;

    //总的记录数
    private int rowCount;

    //数据库分页的开始行号
    private int startRowNo;

    //数据库分页的结束行号
    private int endRowNo;

    //总的记录数别名(提供给jquery dataTable 插件使用)
//    private int iTotalRecords;
    //总的记录数别名(提供给jquery dataTable 插件使用)
//    private int iTotalDisplayRecords;

    //数据库分页的开始行号(提供给jquery dataTable 插件使用)
    private int iDisplayStart;
    //数据库分页的结束行号(提供给jquery dataTable 插件使用)
    private int iDisplayLength;
    //是否启用jquery dataTable 插件模式
    private boolean jqueryMode = false;

    //分页数据
    private List<T> queryResult;

    public PageView() {
    }

    //以页号模式的分页
    public PageView(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        checkPageParams();
        initRowIndexNO();
    }

    //以数据库的行号模式的分页(提供给jquery dataTable 插件使用)
    public PageView(String iDisplayStart, String iDisplayLength) {
        this.jqueryMode = true;
        try {
            this.iDisplayStart = Integer.parseInt(iDisplayStart);
            this.iDisplayLength =  Integer.parseInt(iDisplayLength);
            if(this.iDisplayLength<=0){
                this.iDisplayLength = pageSize;
            }
        } catch (NumberFormatException e) {
            this.iDisplayStart = 0;
            this.iDisplayLength = pageSize;
        }

        this.pageSize = this.iDisplayLength;
        this.startRowNo = this.iDisplayStart;
        this.endRowNo = this.iDisplayStart + this.iDisplayLength;
    }

    //检查分页参数是否合法,非法数据,进行边界初始化
    private void checkPageParams() {
        if (pageNo <= 0) {
            pageNo = 1;
        }
        if (pageSize > 100) {
            pageSize = 100;
        }
    }

    //初始化分页的开始和结束行号
    private void initRowIndexNO() {
        startRowNo = (pageNo - 1) * pageSize;
        endRowNo = pageNo * pageSize;
        if (startRowNo < 0) {
            startRowNo = 0;
        }
        if (endRowNo >= rowCount) {
            endRowNo = rowCount;
        }
    }

    //计算出分页总数
    private void initPageCount() {
        pageCount = (rowCount % pageSize == 0 ? rowCount / pageSize : rowCount / pageSize + 1);
        if(pageNo > pageCount){
            pageNo = pageCount;
        }
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        return pageCount;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
        initPageCount();
        if(jqueryMode){
           return;
        }
        initPageCount();
        initRowIndexNO();
    }

    public int getiTotalRecords() {
        return this.getRowCount();
    }

    public int getiTotalDisplayRecords() {
        return this.getRowCount();
    }

    public int getStartRowNo() {
        return startRowNo;
    }

    public int getEndRowNo() {
        return endRowNo;
    }

    public List<T> getQueryResult() {
        return queryResult;
    }

    public void setQueryResult(List<T> result) {
        this.queryResult = result;
    }
}
