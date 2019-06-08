package com.example.myapartment;

public class flat
{
    private String flatName;
    private String flatOwner;
    private String flatResident;
    private String flatOwnerMobile;
    private String getFlatResidentMobile;

    public flat(String flatName, String flatOwner, String flatResident, String flatOwnerMobile, String getFlatResidentMobile) {
        this.flatName = flatName;
        this.flatOwner = flatOwner;
        this.flatResident = flatResident;
        this.flatOwnerMobile = flatOwnerMobile;
        this.getFlatResidentMobile = getFlatResidentMobile;
    }

    public String getFlatName() {
        return flatName;
    }

    public String getFlatOwner() {
        return flatOwner;
    }

    public String getFlatResident() {
        return flatResident;
    }

    public String getFlatOwnerMobile() {
        return flatOwnerMobile;
    }

    public String getGetFlatResidentMobile() {
        return getFlatResidentMobile;
    }
}
