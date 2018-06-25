package com.example.aamezencev.handbook.domain.services

import com.example.aamezencev.handbook.data.db.DaoSession
import com.example.aamezencev.handbook.data.db.HierarchyElementDb
import com.example.aamezencev.handbook.data.db.HierarchyElementDbDao
import io.reactivex.Observable

class DataBaseService(private val daoSession: DaoSession) {
    fun get(parentId: Long?): Observable<List<HierarchyElementDb>> {
        return Observable.create {
            try {
                val query = if (parentId != null) {
                    daoSession.hierarchyElementDbDao.queryBuilder().where(HierarchyElementDbDao.Properties.ParentId.eq(parentId)).build()
                } else {
                    daoSession.hierarchyElementDbDao.queryBuilder().where(HierarchyElementDbDao.Properties.ParentId.isNull).build()
                }
                it.onNext(query.list())
            } catch (e: Exception) {
                it.onError(e)
            }
            it.onComplete()
        }
    }
}