package com.appscottage.offline.maphd.Classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class PeakData {
    @SerializedName("peak")
    @Expose
    private List<PeakDetail> peak;
    @SerializedName("version")
    @Expose
    private String version;

    public PeakData() {
        this.peak = null;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<PeakDetail> getPeak() {
        return this.peak;
    }

    public void setPeak(List<PeakDetail> peak) {
        this.peak = peak;
    }
}
