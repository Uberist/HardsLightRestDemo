package org.bandana.resthardslightdemo.request;

public class CreateGroupsCredReq {
    private Iterable<String> listid;

    public CreateGroupsCredReq(Iterable<String> listid) {
        this.listid = listid;
    }

    public Iterable<String> getListid() {
        return listid;
    }

    public void setListid(Iterable<String> listid) {
        this.listid = listid;
    }
}
