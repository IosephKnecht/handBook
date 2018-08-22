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
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "POINTER_DATA_DB".
*/
public class PointerDataDbDao extends AbstractDao<PointerDataDb, Long> {

    public static final String TABLENAME = "POINTER_DATA_DB";

    /**
     * Properties of entity PointerDataDb.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property PrimaryKey = new Property(0, Long.class, "primaryKey", true, "_id");
        public final static Property StartIndex = new Property(1, Integer.class, "startIndex", false, "START_INDEX");
        public final static Property FinalIndex = new Property(2, Integer.class, "finalIndex", false, "FINAL_INDEX");
        public final static Property DataHierarchyId = new Property(3, long.class, "dataHierarchyId", false, "DATA_HIERARCHY_ID");
        public final static Property ThrModelId = new Property(4, long.class, "thrModelId", false, "THR_MODEL_ID");
    }

    private DaoSession daoSession;

    private Query<PointerDataDb> dataHierarchyDb_PointerListQuery;

    public PointerDataDbDao(DaoConfig config) {
        super(config);
    }
    
    public PointerDataDbDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"POINTER_DATA_DB\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: primaryKey
                "\"START_INDEX\" INTEGER," + // 1: startIndex
                "\"FINAL_INDEX\" INTEGER," + // 2: finalIndex
                "\"DATA_HIERARCHY_ID\" INTEGER NOT NULL ," + // 3: dataHierarchyId
                "\"THR_MODEL_ID\" INTEGER NOT NULL );"); // 4: thrModelId
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"POINTER_DATA_DB\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, PointerDataDb entity) {
        stmt.clearBindings();
 
        Long primaryKey = entity.getPrimaryKey();
        if (primaryKey != null) {
            stmt.bindLong(1, primaryKey);
        }
 
        Integer startIndex = entity.getStartIndex();
        if (startIndex != null) {
            stmt.bindLong(2, startIndex);
        }
 
        Integer finalIndex = entity.getFinalIndex();
        if (finalIndex != null) {
            stmt.bindLong(3, finalIndex);
        }
        stmt.bindLong(4, entity.getDataHierarchyId());
        stmt.bindLong(5, entity.getThrModelId());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, PointerDataDb entity) {
        stmt.clearBindings();
 
        Long primaryKey = entity.getPrimaryKey();
        if (primaryKey != null) {
            stmt.bindLong(1, primaryKey);
        }
 
        Integer startIndex = entity.getStartIndex();
        if (startIndex != null) {
            stmt.bindLong(2, startIndex);
        }
 
        Integer finalIndex = entity.getFinalIndex();
        if (finalIndex != null) {
            stmt.bindLong(3, finalIndex);
        }
        stmt.bindLong(4, entity.getDataHierarchyId());
        stmt.bindLong(5, entity.getThrModelId());
    }

    @Override
    protected final void attachEntity(PointerDataDb entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public PointerDataDb readEntity(Cursor cursor, int offset) {
        PointerDataDb entity = new PointerDataDb( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // primaryKey
            cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1), // startIndex
            cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2), // finalIndex
            cursor.getLong(offset + 3), // dataHierarchyId
            cursor.getLong(offset + 4) // thrModelId
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, PointerDataDb entity, int offset) {
        entity.setPrimaryKey(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setStartIndex(cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1));
        entity.setFinalIndex(cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2));
        entity.setDataHierarchyId(cursor.getLong(offset + 3));
        entity.setThrModelId(cursor.getLong(offset + 4));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(PointerDataDb entity, long rowId) {
        entity.setPrimaryKey(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(PointerDataDb entity) {
        if(entity != null) {
            return entity.getPrimaryKey();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(PointerDataDb entity) {
        return entity.getPrimaryKey() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "pointerList" to-many relationship of DataHierarchyDb. */
    public List<PointerDataDb> _queryDataHierarchyDb_PointerList(long dataHierarchyId) {
        synchronized (this) {
            if (dataHierarchyDb_PointerListQuery == null) {
                QueryBuilder<PointerDataDb> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.DataHierarchyId.eq(null));
                dataHierarchyDb_PointerListQuery = queryBuilder.build();
            }
        }
        Query<PointerDataDb> query = dataHierarchyDb_PointerListQuery.forCurrentThread();
        query.setParameter(0, dataHierarchyId);
        return query.list();
    }

    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getThreeDimensionalModelDbDao().getAllColumns());
            builder.append(" FROM POINTER_DATA_DB T");
            builder.append(" LEFT JOIN THREE_DIMENSIONAL_MODEL_DB T0 ON T.\"THR_MODEL_ID\"=T0.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected PointerDataDb loadCurrentDeep(Cursor cursor, boolean lock) {
        PointerDataDb entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        ThreeDimensionalModelDb threeDimensionalModelDb = loadCurrentOther(daoSession.getThreeDimensionalModelDbDao(), cursor, offset);
         if(threeDimensionalModelDb != null) {
            entity.setThreeDimensionalModelDb(threeDimensionalModelDb);
        }

        return entity;    
    }

    public PointerDataDb loadDeep(Long key) {
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
    public List<PointerDataDb> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<PointerDataDb> list = new ArrayList<PointerDataDb>(count);
        
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
    
    protected List<PointerDataDb> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<PointerDataDb> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
