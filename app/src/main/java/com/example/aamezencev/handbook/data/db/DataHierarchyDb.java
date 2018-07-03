package com.example.aamezencev.handbook.data.db;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;

@Entity
public class DataHierarchyDb {
    @Id
    private Long primaryKey;
    private String description;
    @ToMany(referencedJoinProperty = "dataHierarchyId")
    private List<PointerDataDb> pointerList;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 2075737267)
    private transient DataHierarchyDbDao myDao;
    @Generated(hash = 1392581399)
    public DataHierarchyDb(Long primaryKey, String description) {
        this.primaryKey = primaryKey;
        this.description = description;
    }
    @Generated(hash = 1535947472)
    public DataHierarchyDb() {
    }
    public Long getPrimaryKey() {
        return this.primaryKey;
    }
    public void setPrimaryKey(Long primaryKey) {
        this.primaryKey = primaryKey;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1868568878)
    public List<PointerDataDb> getPointerList() {
        if (pointerList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PointerDataDbDao targetDao = daoSession.getPointerDataDbDao();
            List<PointerDataDb> pointerListNew = targetDao
                    ._queryDataHierarchyDb_PointerList(primaryKey);
            synchronized (this) {
                if (pointerList == null) {
                    pointerList = pointerListNew;
                }
            }
        }
        return pointerList;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 933011918)
    public synchronized void resetPointerList() {
        pointerList = null;
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
    @Generated(hash = 181828319)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getDataHierarchyDbDao() : null;
    }
}
