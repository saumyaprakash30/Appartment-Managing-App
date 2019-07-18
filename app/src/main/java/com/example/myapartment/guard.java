package com.example.myapartment;

public class guard
{private String guardName,guardNumber,guardGovId,guardNote;

    public guard(String guardName, String guardNumber, String guardGovId, String guardNote) {
        this.guardName = guardName;
        this.guardNumber = guardNumber;
        this.guardGovId = guardGovId;
        this.guardNote = guardNote;
    }

    public guard() {
    }

    public String getGuardName() {
        return guardName;
    }

    public String getGuardNumber() {
        return guardNumber;
    }

    public String getGuardGovId() {
        return guardGovId;
    }

    public String getGuardNote() {
        return guardNote;
    }

}
