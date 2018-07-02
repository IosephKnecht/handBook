package com.example.aamezencev.handbook.data.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.NotNull;

@Entity
public class PointerHierarchyDb {
    @Id
    private Long primaryKey;
    private Integer startIndex;
    private Integer finalIndex;
    private long refDataHierarchyDb;
    @ToOne(joinProperty = "refThreeDimensionalModel")
    private ThreeDimensionalModelDb threeDimensionalModelDb;
    private long refThreeDimensionalModel;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1781645300)
    private transient PointerHierarchyDbDao myDao;
    @Generated(hash = 87098843)
    public PointerHierarchyDb(Long primaryKey, Integer startIndex,
            Integer finalIndex, long refDataHierarchyDb,
            long refThreeDimensionalModel) {
        this.primaryKey = primaryKey;
        this.startIndex = startIndex;
        this.finalIndex = finalIndex;
        this.refDataHierarchyDb = refDataHierarchyDb;
        this.refThreeDimensionalModel = refThreeDimensionalModel;
    }
    @Generated(hash = 1994912808)
    public PointerHierarchyDb() {
    }
    public Long getPrimaryKey() {
        return this.primaryKey;
    }
    public void setPrimaryKey(Long primaryKey) {
        this.primaryKey = primaryKey;
    }
    public Integer getStartIndex() {
        return this.startIndex;
    }
    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }
    public Integer getFinalIndex() {
        return this.finalIndex;
    }
    public void setFinalIndex(Integer finalIndex) {
        this.finalIndex = finalIndex;
    }
    public long getRefDataHierarchyDb() {
        return this.refDataHierarchyDb;
    }
    public void setRefDataHierarchyDb(long refDataHierarchyDb) {
        this.refDataHierarchyDb = refDataHierarchyDb;
    }
    public long getRefThreeDimensionalModel() {
        return this.refThreeDimensionalModel;
    }
    public void setRefThreeDimensionalModel(long refThreeDimensionalModel) {
        this.refThreeDimensionalModel = refThreeDimensionalModel;
    }
    @Generated(hash = 300618627)
    private transient Long threeDimensionalModelDb__resolvedKey;
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 788792534)
    public ThreeDimensionalModelDb getThreeDimensionalModelDb() {
        long __key = this.refThreeDimensionalModel;
        if (threeDimensionalModelDb__resolvedKey == null
                || !threeDimensionalModelDb__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ThreeDimensionalModelDbDao targetDao = daoSession
                    .getThreeDimensionalModelDbDao();
            ThreeDimensionalModelDb threeDimensionalModelDbNew = targetDao
                    .load(__key);
            synchronized (this) {
                threeDimensionalModelDb = threeDimensionalModelDbNew;
                threeDimensionalModelDb__resolvedKey = __key;
            }
        }
        return threeDimensionalModelDb;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 196115187)
    public void setThreeDimensionalModelDb(
            @NotNull ThreeDimensionalModelDb threeDimensionalModelDb) {
        if (threeDimensionalModelDb == null) {
            throw new DaoException(
                    "To-one property 'refThreeDimensionalModel' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.threeDimensionalModelDb = threeDimensionalModelDb;
            refThreeDimensionalModel = threeDimensionalModelDb.getPrimaryKey();
            threeDimensionalModelDb__resolvedKey = refThreeDimensionalModel;
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
    @Generated(hash = 169298658)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getPointerHierarchyDbDao() : null;
    }
}
