package com.example.dam_tuca_madalin_1079;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//DriverLicense license = new DriverLicense(globals.returnUserSession(), currentUserData.getName(),
//        currentUserData.getSurname(), currentUserData.getBirthDate(), currentUserData.getCity(),
//        currentUserData.getCounty(),issueDateStr,endDateStr,policeStation,licenseId,type);
@Entity(tableName = "licenses")
public class DriverLicense {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "uId_col")
    public int uid;
    private String name;
    private String surname;
    private String birthDate;
    private String city;
    private String county;
    private String issueDate;
    private String endDate;
    private String policeStation;
    private String personalId;
    private String licenseId;
    private String type;

    public DriverLicense(int uid, String name, String surname, String birthDate, String city, String county, String issueDate, String endDate, String policeStation, String personalId, String licenseId, String type) {
        this.uid = uid;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.city = city;
        this.county = county;
        this.issueDate = issueDate;
        this.endDate = endDate;
        this.policeStation = policeStation;
        this.personalId = personalId;
        this.licenseId = licenseId;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getPoliceStation() {
        return policeStation;
    }

    public void setPoliceStation(String policeStation) {
        this.policeStation = policeStation;
    }

    public String getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(String licenseId) {
        this.licenseId = licenseId;
    }

    public String getType() {
        return type;
    }

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    public void setType(String type) {
        this.type = type;
    }
}
