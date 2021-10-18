package com.mindque.userdata.record

import com.mindque.userdata.model.RootEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

private const val pathToUserId = "/record/{userId}"
private const val pathToTag = "record/{userId}/tag/{tag}"

@RestController
class RecordController {
    @Autowired
    lateinit var service: RecordService

    @GetMapping(pathToUserId)
    fun getRecordByUserId(
        @PathVariable userId: String
    ) =
        service.getRecordOf(userId)

    @GetMapping(pathToTag)
    fun getRecordByUserIdAndTag(
        @PathVariable tag: String, @PathVariable userId: String
    ) {

    }

    @PostMapping(pathToUserId)
    fun postRecordForUser(
        @PathVariable userId: String, @RequestBody body: RootEntity
    ): RootEntity {
        val signedBody = assignKeyFieldsOf(theBody = body)
        return service.postRecord(entry = signedBody)
    }

    @PutMapping(pathToUserId)
    fun putRecordForUser(
        @PathVariable userId: String, @RequestBody body: RootEntity
    ) {
    }


    private fun assignKeyFieldsOf(
        theBody: RootEntity
    ): RootEntity =
        theBody.apply {
            pk = "#USER${this.userId}"
            sk = "#RECORD${this.dataPointId}"
        }

}