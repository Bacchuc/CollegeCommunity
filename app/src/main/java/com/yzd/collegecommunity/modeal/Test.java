package com.yzd.collegecommunity.modeal;

/**
 * Created by Laiyin on 2017/3/9.
 */

public class Test {

    public String success;

    public Test(){

    }

    public Test(String success){
        this.success=success;
    }

    @Override
    public String toString() {
        return "Test{" +
                "success='" + success + '\'' +
                '}';
    }
}
