package com.fanxiaoyudemo.magicalwardrobe.Tool;

import java.io.File;

/**
 * Created by fanxi on 2020/3/10.
 */
public class Cloth {
    private String ClothNum;
    private File ClothFile;

    public String getClothNum() {
        return ClothNum;
    }

    public File getClothFile() {
        return ClothFile;
    }

    public void setClothNum(String clothNum) {
        ClothNum = clothNum;
    }

    public void setClothFile(File clothFile) {
        ClothFile = clothFile;
    }

    public Cloth(String clothNum,File clothFile)
    {
        this.ClothNum=clothNum;
        this.ClothFile=clothFile;
    }
}
