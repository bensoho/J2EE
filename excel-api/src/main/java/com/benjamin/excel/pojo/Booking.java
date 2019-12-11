package com.benjamin.excel.pojo;

public class Booking {
    private int bid;
    private String hawb_no;
    private String shipper_name;
    private String consignee_name;
    private String notify_party;
    private String shipper_gci;
    private String consignee_gci;

    public String getShipper_gci() {
        return shipper_gci;
    }

    public void setShipper_gci(String shipper_gci) {
        this.shipper_gci = shipper_gci;
    }

    public String getConsignee_gci() {
        return consignee_gci;
    }

    public void setConsignee_gci(String consignee_gci) {
        this.consignee_gci = consignee_gci;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getHawb_no() {
        return hawb_no;
    }

    public void setHawb_no(String hawb_no) {
        this.hawb_no = hawb_no;
    }

    public String getShipper_name() {
        return shipper_name;
    }

    public void setShipper_name(String shipper_name) {
        this.shipper_name = shipper_name;
    }

    public String getConsignee_name() {
        return consignee_name;
    }

    public void setConsignee_name(String consignee_name) {
        this.consignee_name = consignee_name;
    }

    public String getNotify_party() {
        return notify_party;
    }

    public void setNotify_party(String notify_party) {
        this.notify_party = notify_party;
    }
}
