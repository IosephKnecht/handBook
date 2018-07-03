package com.example.aamezencev.handbook.domain.services

import com.example.aamezencev.handbook.data.db.*
import com.example.aamezencev.handbook.data.help.HierarchyContainerDb
import io.reactivex.Observable

class DataBaseService(private val daoSession: DaoSession) {
    fun getHierarchyElement(parentId: Long?): Observable<List<HierarchyElementDb>> {
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

    fun insertHierarchyElement(hierarchyElementDb: HierarchyElementDb): Observable<Long> {
        return Observable.create {
            try {
                it.onNext(daoSession.hierarchyElementDbDao.insert(hierarchyElementDb))
            } catch (e: Exception) {
                it.onError(e)
            }
            it.onComplete()
        }
    }

    fun insertHierarchyElementData(data: DataHierarchyDb?): Observable<Long> {
        return Observable.create {
            try {
                it.onNext(daoSession.dataHierarchyDbDao.insert(data))
            } catch (e: Exception) {
                it.onError(e)
            }
            it.onComplete()
        }
    }

//    fun insertThrModelList(model: List<ThreeDimensionalModelDb>): Observable<Unit> {
//        return Observable.create {
//            try {
//                it.onNext(daoSession.threeDimensionalModelDbDao.insertInTx(model))
//            } catch (e: Exception) {
//                it.onError(e)
//            }
//            it.onComplete()
//        }
//    }

    fun insertPointerList(pointerList: List<PointerDataDb>?): Observable<Unit> {
        return Observable.create {
            try {
                it.onNext(daoSession.pointerDataDbDao.insertInTx(pointerList))
            } catch (e: Exception) {
                it.onError(e)
            }
            it.onComplete()
        }
    }

    fun insertHierarchyElement(hierarchyElementDb: HierarchyElementDb,
                               data: DataHierarchyDb?,
                               pointerList: List<PointerDataDb>?): Observable<Unit> {
        return insertHierarchyElementData(data)
                .flatMap { dataId ->
                    insertHierarchyElement(hierarchyElementDb.apply { dataHierarchyId = dataId })
                            .flatMap {
                                insertPointerList(pointerList
                                        ?.map { it.apply { dataHierarchyId = dataId } })
                            }
                }
    }

    fun insertHierarchyList(containerDbList: List<HierarchyContainerDb>): Observable<Unit> {
        return Observable.fromIterable(containerDbList)
                .flatMap { insertHierarchyElement(it.hierarchyElementDb, it.hierarchyDataHierarchyDb, it.pointerList) }
    }

    fun getHierarchyDataElement(dataId: Long): Observable<DataHierarchyDb> {
        return Observable.create {
            try {
                val query = daoSession.dataHierarchyDbDao.queryBuilder()
                it.onNext(query.where(DataHierarchyDbDao.Properties.PrimaryKey.eq(dataId)).build().unique())
            } catch (e: Exception) {
                it.onError(e)
            }
            it.onComplete()
        }
    }
}