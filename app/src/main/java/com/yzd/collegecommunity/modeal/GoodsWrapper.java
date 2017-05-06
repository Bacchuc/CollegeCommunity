package com.yzd.collegecommunity.modeal;

import java.util.List;

/**
 * Created by Laiyin on 2017/5/3.
 */

public class GoodsWrapper {

    /**
     * list : [{"id":15,"title":"44","publisurDate":"2017-03-16 20:33:00","description":"衣服","goods_photo":"images/ershou.jpg","price":1,"state":"确认收货","sell_user":{"id":30,"username":"111","password":"111","gender":"男","telephone":"111","publishTimes":5,"acceptTimes":0,"finishTimes":0,"integrate":50,"path":"images/defaultPhoto.jpg","mail":"2514362803@qq.com"},"buy_user":{"id":30,"username":"111","password":"111","gender":"男","telephone":"111","publishTimes":5,"acceptTimes":0,"finishTimes":0,"integrate":50,"path":"images/defaultPhoto.jpg","mail":"2514362803@qq.com"},"collectTimes":1}]
     */

    private List<ListEntity> list;

    public void setList(List<ListEntity> list) {
        this.list = list;
    }

    public List<ListEntity> getList() {
        return list;
    }

    public static class ListEntity {
        /**
         * id : 15
         * title : 44
         * publisurDate : 2017-03-16 20:33:00
         * description : 衣服
         * goods_photo : images/ershou.jpg
         * price : 1.0
         * state : 确认收货
         * sell_user : {"id":30,"username":"111","password":"111","gender":"男","telephone":"111","publishTimes":5,"acceptTimes":0,"finishTimes":0,"integrate":50,"path":"images/defaultPhoto.jpg","mail":"2514362803@qq.com"}
         * buy_user : {"id":30,"username":"111","password":"111","gender":"男","telephone":"111","publishTimes":5,"acceptTimes":0,"finishTimes":0,"integrate":50,"path":"images/defaultPhoto.jpg","mail":"2514362803@qq.com"}
         * collectTimes : 1
         */

        private int id;
        private String title;
        private String publisurDate;
        private String description;
        private String goods_photo;
        private double price;
        private String state;
        private SellUserEntity sell_user;
        private BuyUserEntity buy_user;
        private int collectTimes;

        public void setId(int id) {
            this.id = id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setPublisurDate(String publisurDate) {
            this.publisurDate = publisurDate;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setGoods_photo(String goods_photo) {
            this.goods_photo = goods_photo;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public void setState(String state) {
            this.state = state;
        }

        public void setSell_user(SellUserEntity sell_user) {
            this.sell_user = sell_user;
        }

        public void setBuy_user(BuyUserEntity buy_user) {
            this.buy_user = buy_user;
        }

        public void setCollectTimes(int collectTimes) {
            this.collectTimes = collectTimes;
        }

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getPublisurDate() {
            return publisurDate;
        }

        public String getDescription() {
            return description;
        }

        public String getGoods_photo() {
            return goods_photo;
        }

        public double getPrice() {
            return price;
        }

        public String getState() {
            return state;
        }

        public SellUserEntity getSell_user() {
            return sell_user;
        }

        public BuyUserEntity getBuy_user() {
            return buy_user;
        }

        public int getCollectTimes() {
            return collectTimes;
        }

        public static class SellUserEntity {
            /**
             * id : 30
             * username : 111
             * password : 111
             * gender : 男
             * telephone : 111
             * publishTimes : 5
             * acceptTimes : 0
             * finishTimes : 0
             * integrate : 50
             * path : images/defaultPhoto.jpg
             * mail : 2514362803@qq.com
             */

            private int id;
            private String username;
            private String password;
            private String gender;
            private String telephone;
            private int publishTimes;
            private int acceptTimes;
            private int finishTimes;
            private int integrate;
            private String path;
            private String mail;
//            private List<String> images;
//
//            public List<String> getImages() {
//                return images;
//            }
//
//            public void setImages(List<String> images) {
//                this.images = images;
//            }

            private String[] images;

            public String[] getImages() {
                return images;
            }

            public void setImages(String[] images) {
                this.images = images;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public void setTelephone(String telephone) {
                this.telephone = telephone;
            }

            public void setPublishTimes(int publishTimes) {
                this.publishTimes = publishTimes;
            }

            public void setAcceptTimes(int acceptTimes) {
                this.acceptTimes = acceptTimes;
            }

            public void setFinishTimes(int finishTimes) {
                this.finishTimes = finishTimes;
            }

            public void setIntegrate(int integrate) {
                this.integrate = integrate;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public void setMail(String mail) {
                this.mail = mail;
            }

            public int getId() {
                return id;
            }

            public String getUsername() {
                return username;
            }

            public String getPassword() {
                return password;
            }

            public String getGender() {
                return gender;
            }

            public String getTelephone() {
                return telephone;
            }

            public int getPublishTimes() {
                return publishTimes;
            }

            public int getAcceptTimes() {
                return acceptTimes;
            }

            public int getFinishTimes() {
                return finishTimes;
            }

            public int getIntegrate() {
                return integrate;
            }

            public String getPath() {
                return path;
            }

            public String getMail() {
                return mail;
            }
        }

        public static class BuyUserEntity {
            /**
             * id : 30
             * username : 111
             * password : 111
             * gender : 男
             * telephone : 111
             * publishTimes : 5
             * acceptTimes : 0
             * finishTimes : 0
             * integrate : 50
             * path : images/defaultPhoto.jpg
             * mail : 2514362803@qq.com
             */

            private int id;
            private String username;
            private String password;
            private String gender;
            private String telephone;
            private int publishTimes;
            private int acceptTimes;
            private int finishTimes;
            private int integrate;
            private String path;
            private String mail;
            private String[] images;

            public String[] getImages() {
                return images;
            }

            public void setImages(String[] images) {
                this.images = images;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public void setTelephone(String telephone) {
                this.telephone = telephone;
            }

            public void setPublishTimes(int publishTimes) {
                this.publishTimes = publishTimes;
            }

            public void setAcceptTimes(int acceptTimes) {
                this.acceptTimes = acceptTimes;
            }

            public void setFinishTimes(int finishTimes) {
                this.finishTimes = finishTimes;
            }

            public void setIntegrate(int integrate) {
                this.integrate = integrate;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public void setMail(String mail) {
                this.mail = mail;
            }

            public int getId() {
                return id;
            }

            public String getUsername() {
                return username;
            }

            public String getPassword() {
                return password;
            }

            public String getGender() {
                return gender;
            }

            public String getTelephone() {
                return telephone;
            }

            public int getPublishTimes() {
                return publishTimes;
            }

            public int getAcceptTimes() {
                return acceptTimes;
            }

            public int getFinishTimes() {
                return finishTimes;
            }

            public int getIntegrate() {
                return integrate;
            }

            public String getPath() {
                return path;
            }

            public String getMail() {
                return mail;
            }
        }
    }
}
