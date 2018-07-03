package com.example.aamezencev.handbook.data.db;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToOne;

@Entity
public class PointerDataDb {
    @Id
    private Long primaryKey;
    private Integer startIndex;
    private Integer finalIndex;

    private long dataHierarchyId;
    private long thrModelId;

    @ToOne(joinProperty = "thrModelId")
    private ThreeDimensionalModelDb threeDimensionalModelDb;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1777765120)
    private transient PointerDataDbDao myDao;

    @Generated(hash = 345985052)
    public PointerDataDb(Long primaryKey, Integer startIndex, Integer finalIndex,
            long dataHierarchyId, long thrModelId) {
        this.primaryKey = primaryKey;
        this.startIndex = startIndex;
        this.finalIndex = finalIndex;
        this.dataHierarchyId = dataHierarchyId;
        this.thrModelId = thrModelId;
    }

    @Generated(hash = 450689802)
    public PointerDataDb() {
    }

    @Generated(hash = 300618627)
    private transient Long threeDimensionalModelDb__resolvedKey;

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

    public long getThrModelId() {
        return this.thrModelId;
    }

    public void setThrModelId(long thrModelId) {
        this.thrModelId = thrModelId;
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 2026536866)
    public ThreeDimensionalModelDb getThreeDimensionalModelDb() {
        long __key = this.thrModelId;
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
    @Generated(hash = 1852354600)
    public void setThreeDimensionalModelDb(
            @NotNull ThreeDimensionalModelDb threeDimensionalModelDb) {
        if (threeDimensionalModelDb == null) {
            throw new DaoException(
                    "To-one property 'thrModelId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.threeDimensionalModelDb = threeDimensionalModelDb;
            thrModelId = threeDimensionalModelDb.getPrimaryKey();
            threeDimensionalModelDb__resolvedKey = thrModelId;
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
    @Generated(hash = 1225876558)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getPointerDataDbDao() : null;
    }
}
