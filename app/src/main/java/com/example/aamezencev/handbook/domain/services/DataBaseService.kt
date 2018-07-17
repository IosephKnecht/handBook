package com.example.aamezencev.handbook.domain.services

import com.example.aamezencev.handbook.data.db.*
import io.reactivex.Observable

class DataBaseService(private val daoSession: DaoSession) {
    fun getHierarchyList(parentId: Long?): Observable<List<HierarchyElementDb>> {
        return requestDb {
            daoSession.hierarchyElementDbDao
                    .queryBuilder()
                    .where(parentId?.run { HierarchyElementDbDao.Properties.ParentId.eq(parentId) }
                            ?: HierarchyElementDbDao.Properties.ParentId.isNull)
                    .build()
                    .list()
        }
    }

    fun getDataElement(dataId: Long): Observable<DataHierarchyDb> {
        return requestDb {
            daoSession.dataHierarchyDbDao
                    .queryBuilder()
                    .where(DataHierarchyDbDao.Properties.PrimaryKey.eq(dataId))
                    .unique()
        }
    }

    fun getThrModel(thrModelId: Long): Observable<ThreeDimensionalModelDb> {
        return requestDb {
            daoSession.threeDimensionalModelDbDao
                    .queryBuilder()
                    .where(ThreeDimensionalModelDbDao.Properties.PrimaryKey.eq(thrModelId))
                    .unique()
        }
    }

    private fun <T> requestDb(block: DaoSession.() -> T): Observable<T> {
        return Observable.create {
            try {
                it.onNext(block(daoSession))
            } catch (e: Exception) {
                it.onError(e)
            }
            it.onComplete()
        }
    }
}