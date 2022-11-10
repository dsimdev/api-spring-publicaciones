package com.dsimdev.apispringsecurity.dto;


import java.util.List;

public class PublicacionRespuesta {

    private List<PublicacionDto> contenido;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;

    public PublicacionRespuesta() {
    }

    public PublicacionRespuesta(List<PublicacionDto> contenido, int pageNo, int pageSize, long totalElements, int totalPages, boolean last) {
        this.contenido = contenido;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.last = last;
    }

    public List<PublicacionDto> getContenido() {
        return contenido;
    }

    public void setContenido(List<PublicacionDto> contenido) {
        this.contenido = contenido;
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

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }
}
