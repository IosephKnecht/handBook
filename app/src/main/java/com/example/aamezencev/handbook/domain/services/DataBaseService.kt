package com.example.aamezencev.handbook.domain.services

import com.example.aamezencev.handbook.data.db.*
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

    fun insert(hierarchyElementDb: HierarchyElementDb): Observable<Long> {
        return Observable.create {
            try {
                it.onNext(daoSession.hierarchyElementDbDao.insert(hierarchyElementDb))
            } catch (e: Exception) {
                it.onError(e)
            }
            it.onComplete()
        }
    }

    fun insertHierarchyElementData(data: DataHierarchyDb): Observable<Long> {
        return Observable.create {
            try {
                it.onNext(daoSession.dataHierarchyDbDao.insert(data))
            } catch (e: Exception) {
                it.onError(e)
            }
            it.onComplete()
        }
    }

    fun insertThrModelList(model: List<ThreeDimensionalModelDb>): Observable<Unit> {
        return Observable.create {
            try {
                it.onNext(daoSession.threeDimensionalModelDbDao.insertInTx(model))
            } catch (e: Exception) {
                it.onError(e)
            }
            it.onComplete()
        }
    }

    fun insert(hierarchyElementDb: HierarchyElementDb,
               data: DataHierarchyDb,
               modelList: List<ThreeDimensionalModelDb>): Observable<Unit> {
        return insertHierarchyElementData(data)
                .flatMap { dataId ->
                    insert(hierarchyElementDb.apply { dataHierarchyId = dataId })
                            .flatMap {
                                insertThrModelList(modelList
                                        .map { it.apply { dataHierarchyId = dataId } })
                            }
                }
    }
}