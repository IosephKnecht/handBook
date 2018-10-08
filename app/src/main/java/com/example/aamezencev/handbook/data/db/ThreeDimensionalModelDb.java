package com.example.aamezencev.handbook.data.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class ThreeDimensionalModelDb {
    @Id
    private Long primaryKey;
    private byte[] modelArray;
    @Generated(hash = 1501799960)
    public ThreeDimensionalModelDb(Long primaryKey, byte[] modelArray) {
        this.primaryKey = primaryKey;
        this.modelArray = modelArray;
    }
    @Generated(hash = 594339448)
    public ThreeDimensionalModelDb() {
    }
    public Long getPrimaryKey() {
        return this.primaryKey;
    }
    public void setPrimaryKey(Long primaryKey) {
        this.primaryKey = primaryKey;
    }
    public byte[] getModelArray() {
        return this.modelArray;
    }
    public void setModelArray(byte[] modelArray) {
        this.modelArray = modelArray;
    }
}
