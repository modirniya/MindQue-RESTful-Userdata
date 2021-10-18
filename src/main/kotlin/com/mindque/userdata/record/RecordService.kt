package com.mindque.userdata.record

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList
import com.mindque.userdata.core.DynamoDbRepo
import com.mindque.userdata.model.RootEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RecordService {
    @Autowired
    private lateinit var repo: DynamoDbRepo

    fun postRecord(
        entry: RootEntity
    ): RootEntity = repo.store(itemToStore = entry)

    fun getRecordOf(theUserId: String):
            PaginatedQueryList<RootEntity> = repo.query(
        getQueryExpressionOf(
            targetEntity = RootEntity().apply {
                pk = "#USER$theUserId"
            })
    )

    private fun getQueryExpressionOf(targetEntity: RootEntity):
            DynamoDBQueryExpression<RootEntity> =
        DynamoDBQueryExpression<RootEntity>().apply {
            withConsistentRead(false)
            withHashKeyValues(targetEntity)
        }

}