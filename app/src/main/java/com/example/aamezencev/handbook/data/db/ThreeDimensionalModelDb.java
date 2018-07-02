package com.example.aamezencev.handbook.data.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class ThreeDimensionalModelDb {
    @Id
    private Long primaryKey;
    private long dataHierarchyId;
    private byte[] modelArray;

    @Generated(hash = 295823407)
    public ThreeDimensionalModelDb(Long primaryKey, long dataHierarchyId,
            byte[] modelArray) {
        this.primaryKey = primaryKey;
        this.dataHierarchyId = dataHierarchyId;
        this.modelArray = modelArray;
    }

    @Generated(hash = 594339448)
    public ThreeDimensionalModelDb() {
    }

    public long getDataHierarchyId() {
        return dataHierarchyId;
    }

    public void setDataHierarchyId(long dataHierarchyId) {
        this.dataHierarchyId = dataHierarchyId;
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
