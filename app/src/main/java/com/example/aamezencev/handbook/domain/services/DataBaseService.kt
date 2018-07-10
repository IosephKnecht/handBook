package com.example.aamezencev.handbook.domain.services

import com.example.aamezencev.handbook.data.db.*
import io.reactivex.Observable

class DataBaseService(private val daoSession: DaoSession) {
    fun getHierarchyList(parentId: Long?): Observable<List<HierarchyElementDb>> {
        return Observable.create {
            val query = daoSession.hierarchyElementDbDao.queryBuilder()
            if (parentId == null) {
                query.where(HierarchyElementDbDao.Properties.ParentId.isNull)
            } else {
                query.where(HierarchyElementDbDao.Properties.ParentId.eq(parentId))
            }
            try {
                it.onNext(query.build().list())
            } catch (e: Exception) {
                it.onError(e)
            }
            it.onComplete()
        }
    }

    fun getDataElement(dataId: Long): Observable<DataHierarchyDb> {
        return Observable.create {
            val query = daoSession.dataHierarchyDbDao
                    .queryBuilder()
                    .where(DataHierarchyDbDao.Properties.PrimaryKey.eq(dataId))
                    .build()
            try {
                it.onNext(query.unique())
            } catch (e: Exception) {
                it.onError(e)
            }
            it.onComplete()
        }
    }
}