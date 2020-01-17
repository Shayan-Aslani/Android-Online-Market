package com.example.finalproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Customer {

    @Expose
    @SerializedName("_links")
    private _links _links;
    @Expose
    @SerializedName("meta_data")
    private List<String> meta_data;
    @Expose
    @SerializedName("avatar_url")
    private String avatar_url;
    @Expose
    @SerializedName("is_paying_customer")
    private boolean is_paying_customer;
    @Expose
    @SerializedName("shipping")
    private Shipping shipping;
    @Expose
    @SerializedName("billing")
    private Billing billing;
    @Expose
    @SerializedName("username")
    private String username;
    @Expose
    @SerializedName("role")
    private String role;
    @Expose
    @SerializedName("last_name")
    private String last_name;
    @Expose
    @SerializedName("first_name")
    private String first_name;
    @Expose
    @SerializedName("email")
    private String email;
    @Expose
    @SerializedName("date_modified_gmt")
    private String date_modified_gmt;
    @Expose
    @SerializedName("date_modified")
    private String date_modified;
    @Expose
    @SerializedName("date_created_gmt")
    private String date_created_gmt;
    @Expose
    @SerializedName("date_created")
    private String date_created;
    @Expose
    @SerializedName("id")
    private int id;

    public _links get_links() {
        return _links;
    }

    public void set_links(_links _links) {
        this._links = _links;
    }

    public List<String> getMeta_data() {
        return meta_data;
    }

    public void setMeta_data(List<String> meta_data) {
        this.meta_data = meta_data;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public boolean getIs_paying_customer() {
        return is_paying_customer;
    }

    public void setIs_paying_customer(boolean is_paying_customer) {
        this.is_paying_customer = is_paying_customer;
    }

    public Shipping getShipping() {
        return shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    public Billing getBilling() {
        return billing;
    }

    public void setBilling(Billing billing) {
        this.billing = billing;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate_modified_gmt() {
        return date_modified_gmt;
    }

    public void setDate_modified_gmt(String date_modified_gmt) {
        this.date_modified_gmt = date_modified_gmt;
    }

    public String getDate_modified() {
        return date_modified;
    }

    public void setDate_modified(String date_modified) {
        this.date_modified = date_modified;
    }

    public String getDate_created_gmt() {
        return date_created_gmt;
    }

    public void setDate_created_gmt(String date_created_gmt) {
        this.date_created_gmt = date_created_gmt;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static class _links {
        @Expose
        @SerializedName("collection")
        private List<Collection> collection;
        @Expose
        @SerializedName("self")
        private List<Self> self;

        public List<Collection> getCollection() {
            return collection;
        }

        public void setCollection(List<Collection> collection) {
            this.collection = collection;
        }

        public List<Self> getSelf() {
            return self;
        }

        public void setSelf(List<Self> self) {
            this.self = self;
        }
    }

    public static class Collection {
        @Expose
        @SerializedName("href")
        private String href;

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }
    }

    public static class Self {
        @Expose
        @SerializedName("href")
        private String href;

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }
    }

    public static class Shipping {
        @Expose
        @SerializedName("state")
        private String state;
        @Expose
        @SerializedName("country")
        private String country;
        @Expose
        @SerializedName("postcode")
        private String postcode;
        @Expose
        @SerializedName("city")
        private String city;
        @Expose
        @SerializedName("address_2")
        private String address_2;
        @Expose
        @SerializedName("address_1")
        private String address_1;
        @Expose
        @SerializedName("company")
        private String company;
        @Expose
        @SerializedName("last_name")
        private String last_name;
        @Expose
        @SerializedName("first_name")
        private String first_name;

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getPostcode() {
            return postcode;
        }

        public void setPostcode(String postcode) {
            this.postcode = postcode;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getAddress_2() {
            return address_2;
        }

        public void setAddress_2(String address_2) {
            this.address_2 = address_2;
        }

        public String getAddress_1() {
            return address_1;
        }

        public void setAddress_1(String address_1) {
            this.address_1 = address_1;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getLast_name() {
            return last_name;
        }

        public void setLast_name(String last_name) {
            this.last_name = last_name;
        }

        public String getFirst_name() {
            return first_name;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }
    }

    public static class Billing {
        @Expose
        @SerializedName("phone")
        private String phone;
        @Expose
        @SerializedName("email")
        private String email;
        @Expose
        @SerializedName("state")
        private String state;
        @Expose
        @SerializedName("country")
        private String country;
        @Expose
        @SerializedName("postcode")
        private String postcode;
        @Expose
        @SerializedName("city")
        private String city;
        @Expose
        @SerializedName("address_2")
        private String address_2;
        @Expose
        @SerializedName("address_1")
        private String address_1;
        @Expose
        @SerializedName("company")
        private String company;
        @Expose
        @SerializedName("last_name")
        private String last_name;
        @Expose
        @SerializedName("first_name")
        private String first_name;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getPostcode() {
            return postcode;
        }

        public void setPostcode(String postcode) {
            this.postcode = postcode;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getAddress_2() {
            return address_2;
        }

        public void setAddress_2(String address_2) {
            this.address_2 = address_2;
        }

        public String getAddress_1() {
            return address_1;
        }

        public void setAddress_1(String address_1) {
            this.address_1 = address_1;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getLast_name() {
            return last_name;
        }

        public void setLast_name(String last_name) {
            this.last_name = last_name;
        }

        public String getFirst_name() {
            return first_name;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }
    }
}
