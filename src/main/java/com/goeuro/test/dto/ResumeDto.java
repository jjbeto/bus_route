package com.goeuro.test.dto;

public class ResumeDto {

    private boolean headerLoaded = false;
    private int totalLinesInformed = 0;
    private int totalLinesFound = 0;

    public boolean isHeaderLoaded() {
        return headerLoaded;
    }

    public void setHeaderLoaded(boolean headerLoaded) {
        this.headerLoaded = headerLoaded;
    }

    public int getTotalLinesInformed() {
        return totalLinesInformed;
    }

    public void setTotalLinesInformed(int totalLinesInformed) {
        this.totalLinesInformed = totalLinesInformed;
    }

    public int getTotalLinesFound() {
        return totalLinesFound;
    }

    public void setTotalLinesFound(int totalLinesFound) {
        this.totalLinesFound = totalLinesFound;
    }
}
