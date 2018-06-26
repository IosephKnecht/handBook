package com.example.aamezencev.handbook.data.db;

import android.support.annotation.NonNull;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

@Entity
public class HierarchyElementDb {
    @Id
    private Long primaryKey;
    @ToOne(joinProperty = "dataHierarchyId")
    private DataHierarchyDb dataHierarchyDb;
    @NonNull
    private String name;
    private Long parentId;
    private Long hierarchyId;
    private long dataHierarchyId;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 117377468)
    private transient HierarchyElementDbDao myDao;

    @Generated(hash = 78743735)
    public HierarchyElementDb(Long primaryKey, @NonNull String name, Long parentId,
            Long hierarchyId, long dataHierarchyId) {
        this.primaryKey = primaryKey;
        this.name = name;
        this.parentId = parentId;
        this.hierarchyId = hierarchyId;
        this.dataHierarchyId = dataHierarchyId;
    }

    @Generated(hash = 908607129)
    public HierarchyElementDb() {
    }

    @Generated(hash = 1585811300)
    private transient Long dataHierarchyDb__resolvedKey;

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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return this.parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getHierarchyId() {
        return this.hierarchyId;
    }

    public void setHierarchyId(Long hierarchyId) {
        this.hierarchyId = hierarchyId;
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 2033874534)
    public DataHierarchyDb getDataHierarchyDb() {
        long __key = this.dataHierarchyId;
        if (dataHierarchyDb__resolvedKey == null
                || !dataHierarchyDb__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            DataHierarchyDbDao targetDao = daoSession.getDataHierarchyDbDao();
            DataHierarchyDb dataHierarchyDbNew = targetDao.load(__key);
            synchronized (this) {
                dataHierarchyDb = dataHierarchyDbNew;
                dataHierarchyDb__resolvedKey = __key;
            }
        }
        return dataHierarchyDb;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 606537728)
    public void setDataHierarchyDb(@NonNull DataHierarchyDb dataHierarchyDb) {
        if (dataHierarchyDb == null) {
            throw new DaoException(
                    "To-one property 'dataHierarchyId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.dataHierarchyDb = dataHierarchyDb;
            dataHierarchyId = dataHierarchyDb.getPrimaryKey();
            dataHierarchyDb__resolvedKey = dataHierarchyId;
        }
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1820719)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getHierarchyElementDbDao() : null;
    }
}
