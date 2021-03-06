package com.example.aamezencev.handbook.data.db;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.SqlUtils;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "HIERARCHY_ELEMENT_DB".
*/
public class HierarchyElementDbDao extends AbstractDao<HierarchyElementDb, Long> {

    public static final String TABLENAME = "HIERARCHY_ELEMENT_DB";

    /**
     * Properties of entity HierarchyElementDb.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property PrimaryKey = new Property(0, Long.class, "primaryKey", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property ParentId = new Property(2, Long.class, "parentId", false, "PARENT_ID");
        public final static Property DataHierarchyId = new Property(3, Long.class, "dataHierarchyId", false, "DATA_HIERARCHY_ID");
    }

    private DaoSession daoSession;


    public HierarchyElementDbDao(DaoConfig config) {
        super(config);
    }
    
    public HierarchyElementDbDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"HIERARCHY_ELEMENT_DB\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: primaryKey
                "\"NAME\" TEXT," + // 1: name
                "\"PARENT_ID\" INTEGER," + // 2: parentId
                "\"DATA_HIERARCHY_ID\" INTEGER);"); // 3: dataHierarchyId
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"HIERARCHY_ELEMENT_DB\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, HierarchyElementDb entity) {
        stmt.clearBindings();
 
        Long primaryKey = entity.getPrimaryKey();
        if (primaryKey != null) {
            stmt.bindLong(1, primaryKey);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        Long parentId = entity.getParentId();
        if (parentId != null) {
            stmt.bindLong(3, parentId);
        }
 
        Long dataHierarchyId = entity.getDataHierarchyId();
        if (dataHierarchyId != null) {
            stmt.bindLong(4, dataHierarchyId);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, HierarchyElementDb entity) {
        stmt.clearBindings();
 
        Long primaryKey = entity.getPrimaryKey();
        if (primaryKey != null) {
            stmt.bindLong(1, primaryKey);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        Long parentId = entity.getParentId();
        if (parentId != null) {
            stmt.bindLong(3, parentId);
        }
 
        Long dataHierarchyId = entity.getDataHierarchyId();
        if (dataHierarchyId != null) {
            stmt.bindLong(4, dataHierarchyId);
        }
    }

    @Override
    protected final void attachEntity(HierarchyElementDb entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public HierarchyElementDb readEntity(Cursor cursor, int offset) {
        HierarchyElementDb entity = new HierarchyElementDb( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // primaryKey
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name
            cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2), // parentId
            cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3) // dataHierarchyId
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, HierarchyElementDb entity, int offset) {
        entity.setPrimaryKey(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setParentId(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
        entity.setDataHierarchyId(cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(HierarchyElementDb entity, long rowId) {
        entity.setPrimaryKey(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(HierarchyElementDb entity) {
        if(entity != null) {
            return entity.getPrimaryKey();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(HierarchyElementDb entity) {
        return entity.getPrimaryKey() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getDataHierarchyDbDao().getAllColumns());
            builder.append(" FROM HIERARCHY_ELEMENT_DB T");
            builder.append(" LEFT JOIN DATA_HIERARCHY_DB T0 ON T.\"DATA_HIERARCHY_ID\"=T0.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected HierarchyElementDb loadCurrentDeep(Cursor cursor, boolean lock) {
        HierarchyElementDb entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        DataHierarchyDb dataHierarchyDb = loadCurrentOther(daoSession.getDataHierarchyDbDao(), cursor, offset);
        entity.setDataHierarchyDb(dataHierarchyDb);

        return entity;    
    }

    public HierarchyElementDb loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<HierarchyElementDb> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<HierarchyElementDb> list = new ArrayList<HierarchyElementDb>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<HierarchyElementDb> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<HierarchyElementDb> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
