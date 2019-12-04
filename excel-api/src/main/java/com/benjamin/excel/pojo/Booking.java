package com.benjamin.excel.pojo;

public class Booking {
    private int bid;
    private String hawb_no;
    private String shipper_name;
    private String consignee_name;
    private String notify_party;

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
