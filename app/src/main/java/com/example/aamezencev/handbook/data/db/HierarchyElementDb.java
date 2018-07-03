package com.example.aamezencev.handbook.data.db;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.List;

@Entity
public class HierarchyElementDb {
    @Id
    private Long primaryKey;
    private String name;

    private Long parentId;
    private Long dataHierarchyId;

    @ToOne(joinProperty = "parentId")
    private HierarchyElementDb parentHierarchyElement;
    @ToMany(referencedJoinProperty = "parentId")
    private List<HierarchyElementDb> childrenHierarchyElements;
    @ToOne(joinProperty = "dataHierarchyId")
    private DataHierarchyDb dataHierarchyDb;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 117377468)
    private transient HierarchyElementDbDao myDao;

    @Generated(hash = 956792356)
    public HierarchyElementDb(Long primaryKey, String name, Long parentId,
            Long dataHierarchyId) {
        this.primaryKey = primaryKey;
        this.name = name;
        this.parentId = parentId;
        this.dataHierarchyId = dataHierarchyId;
    }

    @Generated(hash = 908607129)
    public HierarchyElementDb() {
    }

    @Generated(hash = 497750080)
    private transient Long parentHierarchyElement__resolvedKey;
    @Generated(hash = 1585811300)
    private transient Long dataHierarchyDb__resolvedKey;

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getDataHierarchyId() {
        return dataHierarchyId;
    }

    public void setDataHierarchyId(Long dataHierarchyId) {
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

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1376359965)
    public HierarchyElementDb getParentHierarchyElement() {
        Long __key = this.parentId;
        if (parentHierarchyElement__resolvedKey == null
                || !parentHierarchyElement__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            HierarchyElementDbDao targetDao = daoSession.getHierarchyElementDbDao();
            HierarchyElementDb parentHierarchyElementNew = targetDao.load(__key);
            synchronized (this) {
                parentHierarchyElement = parentHierarchyElementNew;
                parentHierarchyElement__resolvedKey = __key;
            }
        }
        return parentHierarchyElement;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 677623975)
    public void setParentHierarchyElement(
            HierarchyElementDb parentHierarchyElement) {
        synchronized (this) {
            this.parentHierarchyElement = parentHierarchyElement;
            parentId = parentHierarchyElement == null ? null
                    : parentHierarchyElement.getPrimaryKey();
            parentHierarchyElement__resolvedKey = parentId;
        }
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1687107720)
    public DataHierarchyDb getDataHierarchyDb() {
        Long __key = this.dataHierarchyId;
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
    @Generated(hash = 552491238)
    public void setDataHierarchyDb(DataHierarchyDb dataHierarchyDb) {
        synchronized (this) {
            this.dataHierarchyDb = dataHierarchyDb;
            dataHierarchyId = dataHierarchyDb == null ? null
                    : dataHierarchyDb.getPrimaryKey();
            dataHierarchyDb__resolvedKey = dataHierarchyId;
        }
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 488566963)
    public List<HierarchyElementDb> getChildrenHierarchyElements() {
        if (childrenHierarchyElements == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            HierarchyElementDbDao targetDao = daoSession.getHierarchyElementDbDao();
            List<HierarchyElementDb> childrenHierarchyElementsNew = targetDao
                    ._queryHierarchyElementDb_ChildrenHierarchyElements(primaryKey);
            synchronized (this) {
                if (childrenHierarchyElements == null) {
                    childrenHierarchyElements = childrenHierarchyElementsNew;
                }
            }
        }
        return childrenHierarchyElements;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 382921749)
    public synchronized void resetChildrenHierarchyElements() {
        childrenHierarchyElements = null;
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
