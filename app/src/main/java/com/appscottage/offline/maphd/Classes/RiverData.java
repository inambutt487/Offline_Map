package com.appscottage.offline.maphd.Classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class RiverData {
    @SerializedName("river")
    @Expose
    private List<RiverDetail> river;
    @SerializedName("version")
    @Expose
    private String version;

    public RiverData() {
        this.river = null;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<RiverDetail> getRiver() {
        return this.river;
    }

    public void setRiver(List<RiverDetail> river) {
        this.river = river;
    }
}
