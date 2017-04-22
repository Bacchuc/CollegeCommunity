package com.yzd.collegecommunity.modeal;

import java.util.List;

/**
 * Created by Laiyin on 2017/4/21.
 */

public class TaskWrapper {

    @Override
    public String toString() {
        return "TaskWrapper{" +
                "list=" + list +
                '}';
    }

    /**
     * list : [{"id":1,"publisurDate":"2017-04-20 14:20:12","description":"请输入你要发布的内容。。。","pay":1,"state":"未接收","publish_user":{"id":30,"username":"111","password":"111","gender":"男","telephone":"111","publishTimes":5,"acceptTimes":0,"finishTimes":0,"integrate":50,"path":"images/defaultPhoto.jpg","mail":"2514362803@qq.com"},"accept_user":null,"collectTimes":0,"pic":"images/defaultPhoto.jpg"}]
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
         * id : 1
         * publisurDate : 2017-04-20 14:20:12
         * description : 请输入你要发布的内容。。。
         * pay : 1.0
         * state : 未接收
         * publish_user : {"id":30,"username":"111","password":"111","gender":"男","telephone":"111","publishTimes":5,"acceptTimes":0,"finishTimes":0,"integrate":50,"path":"images/defaultPhoto.jpg","mail":"2514362803@qq.com"}
         * accept_user : null
         * collectTimes : 0
         * pic : images/defaultPhoto.jpg
         */

        private int id;
        private String publisurDate;
        private String description;
        private double pay;
        private String state;
        private PublishUserEntity publish_user;
        private Object accept_user;
        private int collectTimes;
        private String pic;

        public void setId(int id) {
            this.id = id;
        }

        public void setPublisurDate(String publisurDate) {
            this.publisurDate = publisurDate;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setPay(double pay) {
            this.pay = pay;
        }

        public void setState(String state) {
            this.state = state;
        }

        public void setPublish_user(PublishUserEntity publish_user) {
            this.publish_user = publish_user;
        }

        public void setAccept_user(Object accept_user) {
            this.accept_user = accept_user;
        }

        public void setCollectTimes(int collectTimes) {
            this.collectTimes = collectTimes;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public int getId() {
            return id;
        }

        public String getPublisurDate() {
            return publisurDate;
        }

        public String getDescription() {
            return description;
        }

        public double getPay() {
            return pay;
        }

        public String getState() {
            return state;
        }

        public PublishUserEntity getPublish_user() {
            return publish_user;
        }

        public Object getAccept_user() {
            return accept_user;
        }

        public int getCollectTimes() {
            return collectTimes;
        }

        public String getPic() {
            return pic;
        }

        public static class PublishUserEntity {
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
