package org.bandana.resthardslightdemo.request;


public class DeleteCredReq {
    private String credid;

    public DeleteCredReq(String credid) {
        this.credid = credid;
    }

    public String getCredid() {
        return credid;
    }

    public void setCredid(String credid) {
        this.credid = credid;
    }
}
