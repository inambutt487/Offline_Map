package com.appscottage.offline.maphd.Classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class WondersData {
    @SerializedName("version")
    @Expose
    private String version;
    @SerializedName("wonder")
    @Expose
    private List<WondersDetail> wonder;

    public WondersData() {
        this.wonder = null;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<WondersDetail> getWonder() {
        return this.wonder;
    }

    public void setWonder(List<WondersDetail> wonder) {
        this.wonder = wonder;
    }
}
