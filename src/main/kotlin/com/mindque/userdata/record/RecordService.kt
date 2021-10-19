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
        record: RootEntity
    ): RootEntity {
        for (tag in record.tags) {
            repo.store(
                itemToStore = RootEntity().apply {
                    pk = "#USERDATA${record.userId}"
                    sk = "#${tag.uppercase()}${record.dataPointId}"
                    this.tag = "#${tag.uppercase()}"
                }
            )
        }
        val taggedEntity = tagKeyValueOf(record)
        return repo.store(itemToStore = taggedEntity)
    }

    fun getRecordOf(theUserId: String):
            PaginatedQueryList<RootEntity> = repo.query(
        expression = getQueryExpressionByHashKeyValuesOf(
            targetEntity = RootEntity().apply {
                pk = "#USER$theUserId"
            })
    )

    fun getRecordsByTagOf(strUserId: String, strTag: String): List<RootEntity> {
        val result = mutableSetOf<RootEntity>()
        val queryResult = repo.query(
            expression = DynamoDBQueryExpression<RootEntity>().apply {
                withConsistentRead(false)
                withIndexName("sk_by_tag")
                withHashKeyValues(RootEntity().apply {
                    pk = "#USERDATA${strUserId}"
                    tag = "#${strTag.uppercase()}"
                })
            }
        ).toList()
        for (eachEntity in queryResult) {
            val skId = eachEntity.sk.substring(startIndex = strTag.length + 1)
            repo.retrieve(
                pk = "#USER$strUserId",
                sk = "#RECORD$skId"
            ).also {
                if (it != null && it.tags.contains(strTag)) {
                    result.add(it)
                }
            }
        }
        return result.toList()
    }

    private fun getQueryExpressionByHashKeyValuesOf(targetEntity: RootEntity):
            DynamoDBQueryExpression<RootEntity> =
        DynamoDBQueryExpression<RootEntity>().apply {
            withConsistentRead(false)
            withHashKeyValues(targetEntity)
        }

    private fun tagKeyValueOf(
        theBody: RootEntity
    ): RootEntity =
        theBody.apply {
            pk = "#USER${this.userId}"
            sk = "#RECORD${this.dataPointId}"
        }

}