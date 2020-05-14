package cn.xy.domain;

import java.io.Serializable;

public class TbApkItem implements Serializable {
    private Integer childId;

    private String entryName;

    private String appName;

    private String version;

    private String twoBarCodes;

    private String descpd;

    public Integer getChildId() {
        return childId;
    }

    public void setChildId(Integer childId) {
        this.childId = childId;
    }

    public String getEntryName() {
        return entryName;
    }

    public void setEntryName(String entryName) {
        this.entryName = entryName == null ? null : entryName.trim();
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName == null ? null : appName.trim();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public String getTwoBarCodes() {
        return twoBarCodes;
    }

    public void setTwoBarCodes(String twoBarCodes) {
        this.twoBarCodes = twoBarCodes == null ? null : twoBarCodes.trim();
    }

    public String getDescpd() {
        return descpd;
    }

    public void setDescpd(String descpd) {
        this.descpd = descpd == null ? null : descpd.trim();
    }
}