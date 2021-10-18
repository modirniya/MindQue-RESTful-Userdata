package com.mindque.userdata.core

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList
import com.mindque.userdata.model.RootEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class DynamoDbRepo {

    @Autowired
    lateinit var mapper: DynamoDBMapper

    fun retrieve(pk: String, sk: String): RootEntity? =
        mapper.load(RootEntity::class.java, pk, sk)

    fun delete(pk: String, sk: String): RootEntity? =
        retrieve(pk = pk, sk = sk)

    fun store(itemToStore: RootEntity): RootEntity =
        itemToStore.also { mapper.save(it) }

    fun query(expression: DynamoDBQueryExpression<RootEntity>)
            : PaginatedQueryList<RootEntity> =
        mapper.query(RootEntity::class.java, expression)


}
