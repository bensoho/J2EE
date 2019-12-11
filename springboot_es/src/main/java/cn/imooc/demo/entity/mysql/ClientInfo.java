package cn.imooc.demo.entity.mysql;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CREATE TABLE `db_client` (
 *   `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
 *   `company_id` varchar(10) NOT NULL DEFAULT '',
 *   `company_name` varchar(100) NOT NULL DEFAULT '',
 *   `address_1` varchar(100) DEFAULT NULL,
 *   `address_2` varchar(100) DEFAULT NULL,
 *   `city` varchar(50) DEFAULT NULL,
 *   `state_name` varchar(50) DEFAULT NULL,
 *   `country_code` varchar(30) DEFAULT NULL,
 *   `country_name` varchar(50) DEFAULT NULL,
 *   `zip` varchar(20) DEFAULT NULL,
 *   `tax_id` varchar(50) DEFAULT NULL,
 *   `notes` varchar(1) DEFAULT NULL COMMENT '0-shipper,1-consignee',
 *   PRIMARY KEY (`id`)
 * ) ENGINE=InnoDB AUTO_INCREMENT=1995 DEFAULT CHARSET=utf8;
 */
@Data
@Entity
@Table(name = "db_client")
public class ClientInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String companyId;

    private String companyName;
    private String address_1;
    private String address_2;
    private String city;
    private String stateName;
    private String countryCode;
    private String countryName;
    private String zip;
    private String taxId;
    private String notes;



}
