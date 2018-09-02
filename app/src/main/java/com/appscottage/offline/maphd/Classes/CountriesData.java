package com.appscottage.offline.maphd.Classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class CountriesData {
    @SerializedName("countrydetail")
    @Expose
    private List<CounriesDetail> countrydetail;

    public CountriesData() {
        this.countrydetail = null;
    }

    public List<CounriesDetail> getCountrydetail() {
        return this.countrydetail;
    }

    public void setCountrydetail(List<CounriesDetail> countrydetail) {
        this.countrydetail = countrydetail;
    }
}
