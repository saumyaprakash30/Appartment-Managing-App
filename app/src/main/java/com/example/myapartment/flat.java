package com.example.myapartment;

public class flat
{
    private String flatName;
    private String flatOwner;
    private String flatResident;
    private String flatOwnerMobile;
    private String FlatResidentMobile;

    public flat(String flatName, String flatOwner, String flatResident, String flatOwnerMobile, String FlatResidentMobile) {
        this.flatName = flatName;
        this.flatOwner = flatOwner;
        this.flatResident = flatResident;
        this.flatOwnerMobile = flatOwnerMobile;
        this.FlatResidentMobile = FlatResidentMobile;
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

    public String getFlatResidentMobile() {
        return FlatResidentMobile;
    }
}
