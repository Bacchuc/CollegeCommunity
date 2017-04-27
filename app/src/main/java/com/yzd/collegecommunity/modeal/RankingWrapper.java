package com.yzd.collegecommunity.modeal;

import java.util.List;

/**
 * Created by Laiyin on 2017/4/27.
 */

public class RankingWrapper {
    @Override
    public String toString() {
        return "RankingWrapper{" +
                "list=" + list +
                '}';
    }

    /**
     * list : [{"id":30,"username":"111","password":"111","gender":"男","telephone":"111","publishTimes":5,"acceptTimes":0,"finishTimes":0,"integrate":50,"path":"images/defaultPhoto.jpg","mail":"2514362803@qq.com","images":["images/defaultPhoto.jpg","images/defaultPhoto.jpg","images/defaultPhoto.jpg","images/defaultPhoto.jpg","images/ershou.png"]}]
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
         * images : ["images/defaultPhoto.jpg","images/defaultPhoto.jpg","images/defaultPhoto.jpg","images/defaultPhoto.jpg","images/ershou.png"]
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
        private List<String> images;

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

        public void setImages(List<String> images) {
            this.images = images;
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

        public List<String> getImages() {
            return images;
        }
    }
}
