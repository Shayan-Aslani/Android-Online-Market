package com.example.finalproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Product {


    @Expose
    @SerializedName("_links")
    private _links _links;
    @Expose
    @SerializedName("meta_data")
    private List<Meta_data> meta_data;
    @Expose
    @SerializedName("menu_order")
    private int menu_order;
    @Expose
    @SerializedName("grouped_products")
    private List<String> grouped_products;
    @Expose
    @SerializedName("variations")
    private List<String> variations;
    @Expose
    @SerializedName("default_attributes")
    private List<String> default_attributes;
    @Expose
    @SerializedName("attributes")
    private List<String> attributes;
    @Expose
    @SerializedName("images")
    private List<Images> images;
    @Expose
    @SerializedName("tags")
    private List<String> tags;
    @Expose
    @SerializedName("categories")
    private List<Categories> categories;
    @Expose
    @SerializedName("purchase_note")
    private String purchase_note;
    @Expose
    @SerializedName("parent_id")
    private int parent_id;
    @Expose
    @SerializedName("cross_sell_ids")
    private List<String> cross_sell_ids;
    @Expose
    @SerializedName("upsell_ids")
    private List<String> upsell_ids;
    @Expose
    @SerializedName("related_ids")
    private List<Integer> related_ids;
    @Expose
    @SerializedName("rating_count")
    private int rating_count;
    @Expose
    @SerializedName("average_rating")
    private String average_rating;
    @Expose
    @SerializedName("reviews_allowed")
    private boolean reviews_allowed;
    @Expose
    @SerializedName("shipping_class_id")
    private int shipping_class_id;
    @Expose
    @SerializedName("shipping_class")
    private String shipping_class;
    @Expose
    @SerializedName("shipping_taxable")
    private boolean shipping_taxable;
    @Expose
    @SerializedName("shipping_required")
    private boolean shipping_required;
    @Expose
    @SerializedName("dimensions")
    private Dimensions dimensions;
    @Expose
    @SerializedName("weight")
    private String weight;
    @Expose
    @SerializedName("sold_individually")
    private boolean sold_individually;
    @Expose
    @SerializedName("backordered")
    private boolean backordered;
    @Expose
    @SerializedName("backorders_allowed")
    private boolean backorders_allowed;
    @Expose
    @SerializedName("backorders")
    private String backorders;
    @Expose
    @SerializedName("stock_status")
    private String stock_status;
    @Expose
    @SerializedName("manage_stock")
    private boolean manage_stock;
    @Expose
    @SerializedName("tax_class")
    private String tax_class;
    @Expose
    @SerializedName("tax_status")
    private String tax_status;
    @Expose
    @SerializedName("button_text")
    private String button_text;
    @Expose
    @SerializedName("external_url")
    private String external_url;
    @Expose
    @SerializedName("download_expiry")
    private int download_expiry;
    @Expose
    @SerializedName("download_limit")
    private int download_limit;
    @Expose
    @SerializedName("downloads")
    private List<String> downloads;
    @Expose
    @SerializedName("downloadable")
    private boolean downloadable;
    @Expose
    @SerializedName("virtual")
    private boolean virtual;
    @Expose
    @SerializedName("total_sales")
    private int total_sales;
    @Expose
    @SerializedName("purchasable")
    private boolean purchasable;
    @Expose
    @SerializedName("on_sale")
    private boolean on_sale;
    @Expose
    @SerializedName("price_html")
    private String price_html;
    @Expose
    @SerializedName("sale_price")
    private String sale_price;
    @Expose
    @SerializedName("regular_price")
    private String regular_price;
    @Expose
    @SerializedName("price")
    private String price;
    @Expose
    @SerializedName("sku")
    private String sku;
    @Expose
    @SerializedName("short_description")
    private String short_description;
    @Expose
    @SerializedName("description")
    private String description;
    @Expose
    @SerializedName("catalog_visibility")
    private String catalog_visibility;
    @Expose
    @SerializedName("featured")
    private boolean featured;
    @Expose
    @SerializedName("status")
    private String status;
    @Expose
    @SerializedName("type")
    private String type;
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
    @SerializedName("permalink")
    private String permalink;
    @Expose
    @SerializedName("slug")
    private String slug;
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("id")
    private int id;

    public _links get_links() {
        return _links;
    }

    public void set_links(_links _links) {
        this._links = _links;
    }

    public List<Meta_data> getMeta_data() {
        return meta_data;
    }

    public void setMeta_data(List<Meta_data> meta_data) {
        this.meta_data = meta_data;
    }

    public int getMenu_order() {
        return menu_order;
    }

    public void setMenu_order(int menu_order) {
        this.menu_order = menu_order;
    }

    public List<String> getGrouped_products() {
        return grouped_products;
    }

    public void setGrouped_products(List<String> grouped_products) {
        this.grouped_products = grouped_products;
    }

    public List<String> getVariations() {
        return variations;
    }

    public void setVariations(List<String> variations) {
        this.variations = variations;
    }

    public List<String> getDefault_attributes() {
        return default_attributes;
    }

    public void setDefault_attributes(List<String> default_attributes) {
        this.default_attributes = default_attributes;
    }

    public List<String> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<String> attributes) {
        this.attributes = attributes;
    }

    public List<Images> getImages() {
        return images;
    }

    public void setImages(List<Images> images) {
        this.images = images;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<Categories> getCategories() {
        return categories;
    }

    public void setCategories(List<Categories> categories) {
        this.categories = categories;
    }

    public String getPurchase_note() {
        return purchase_note;
    }

    public void setPurchase_note(String purchase_note) {
        this.purchase_note = purchase_note;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public List<String> getCross_sell_ids() {
        return cross_sell_ids;
    }

    public void setCross_sell_ids(List<String> cross_sell_ids) {
        this.cross_sell_ids = cross_sell_ids;
    }

    public List<String> getUpsell_ids() {
        return upsell_ids;
    }

    public void setUpsell_ids(List<String> upsell_ids) {
        this.upsell_ids = upsell_ids;
    }

    public List<Integer> getRelated_ids() {
        return related_ids;
    }

    public void setRelated_ids(List<Integer> related_ids) {
        this.related_ids = related_ids;
    }

    public int getRating_count() {
        return rating_count;
    }

    public void setRating_count(int rating_count) {
        this.rating_count = rating_count;
    }

    public String getAverage_rating() {
        return average_rating;
    }

    public void setAverage_rating(String average_rating) {
        this.average_rating = average_rating;
    }

    public boolean getReviews_allowed() {
        return reviews_allowed;
    }

    public void setReviews_allowed(boolean reviews_allowed) {
        this.reviews_allowed = reviews_allowed;
    }

    public int getShipping_class_id() {
        return shipping_class_id;
    }

    public void setShipping_class_id(int shipping_class_id) {
        this.shipping_class_id = shipping_class_id;
    }

    public String getShipping_class() {
        return shipping_class;
    }

    public void setShipping_class(String shipping_class) {
        this.shipping_class = shipping_class;
    }

    public boolean getShipping_taxable() {
        return shipping_taxable;
    }

    public void setShipping_taxable(boolean shipping_taxable) {
        this.shipping_taxable = shipping_taxable;
    }

    public boolean getShipping_required() {
        return shipping_required;
    }

    public void setShipping_required(boolean shipping_required) {
        this.shipping_required = shipping_required;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    public void setDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public boolean getSold_individually() {
        return sold_individually;
    }

    public void setSold_individually(boolean sold_individually) {
        this.sold_individually = sold_individually;
    }

    public boolean getBackordered() {
        return backordered;
    }

    public void setBackordered(boolean backordered) {
        this.backordered = backordered;
    }

    public boolean getBackorders_allowed() {
        return backorders_allowed;
    }

    public void setBackorders_allowed(boolean backorders_allowed) {
        this.backorders_allowed = backorders_allowed;
    }

    public String getBackorders() {
        return backorders;
    }

    public void setBackorders(String backorders) {
        this.backorders = backorders;
    }

    public String getStock_status() {
        return stock_status;
    }

    public void setStock_status(String stock_status) {
        this.stock_status = stock_status;
    }

    public boolean getManage_stock() {
        return manage_stock;
    }

    public void setManage_stock(boolean manage_stock) {
        this.manage_stock = manage_stock;
    }

    public String getTax_class() {
        return tax_class;
    }

    public void setTax_class(String tax_class) {
        this.tax_class = tax_class;
    }

    public String getTax_status() {
        return tax_status;
    }

    public void setTax_status(String tax_status) {
        this.tax_status = tax_status;
    }

    public String getButton_text() {
        return button_text;
    }

    public void setButton_text(String button_text) {
        this.button_text = button_text;
    }

    public String getExternal_url() {
        return external_url;
    }

    public void setExternal_url(String external_url) {
        this.external_url = external_url;
    }

    public int getDownload_expiry() {
        return download_expiry;
    }

    public void setDownload_expiry(int download_expiry) {
        this.download_expiry = download_expiry;
    }

    public int getDownload_limit() {
        return download_limit;
    }

    public void setDownload_limit(int download_limit) {
        this.download_limit = download_limit;
    }

    public List<String> getDownloads() {
        return downloads;
    }

    public void setDownloads(List<String> downloads) {
        this.downloads = downloads;
    }

    public boolean getDownloadable() {
        return downloadable;
    }

    public void setDownloadable(boolean downloadable) {
        this.downloadable = downloadable;
    }

    public boolean getVirtual() {
        return virtual;
    }

    public void setVirtual(boolean virtual) {
        this.virtual = virtual;
    }

    public int getTotal_sales() {
        return total_sales;
    }

    public void setTotal_sales(int total_sales) {
        this.total_sales = total_sales;
    }

    public boolean getPurchasable() {
        return purchasable;
    }

    public void setPurchasable(boolean purchasable) {
        this.purchasable = purchasable;
    }

    public boolean getOn_sale() {
        return on_sale;
    }

    public void setOn_sale(boolean on_sale) {
        this.on_sale = on_sale;
    }

    public String getPrice_html() {
        return price_html;
    }

    public void setPrice_html(String price_html) {
        this.price_html = price_html;
    }

    public String getSale_price() {
        return sale_price;
    }

    public void setSale_price(String sale_price) {
        this.sale_price = sale_price;
    }

    public String getRegular_price() {
        return regular_price;
    }

    public void setRegular_price(String regular_price) {
        this.regular_price = regular_price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCatalog_visibility() {
        return catalog_visibility;
    }

    public void setCatalog_visibility(String catalog_visibility) {
        this.catalog_visibility = catalog_visibility;
    }

    public boolean getFeatured() {
        return featured;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public static class Meta_data {
        @Expose
        @SerializedName("value")
        private String value;
        @Expose
        @SerializedName("key")
        private String key;
        @Expose
        @SerializedName("id")
        private int id;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public static class Images {
        @Expose
        @SerializedName("alt")
        private String alt;
        @Expose
        @SerializedName("name")
        private String name;
        @Expose
        @SerializedName("src")
        private String src;
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

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
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
    }

    public static class Categories {
        @Expose
        @SerializedName("slug")
        private String slug;
        @Expose
        @SerializedName("name")
        private String name;
        @Expose
        @SerializedName("id")
        private int id;

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public static class Dimensions {
        @Expose
        @SerializedName("height")
        private String height;
        @Expose
        @SerializedName("width")
        private String width;
        @Expose
        @SerializedName("length")
        private String length;

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getWidth() {
            return width;
        }

        public void setWidth(String width) {
            this.width = width;
        }

        public String getLength() {
            return length;
        }

        public void setLength(String length) {
            this.length = length;
        }
    }
}
