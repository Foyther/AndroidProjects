package com.zolotuhinartem.lastfminfo.LastFmApi.response.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by zolotuhinartem on 17.12.16.
 */

public class Attr {

    @SerializedName("@attr")
    @Expose
    private String _for;

    public String getFor() {
        return _for;
    }

    public void setFor(String _for) {
        this._for = _for;
    }
}
