package com.mindque.userdata.record

import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList
import com.mindque.userdata.model.RootEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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
    ): ResponseEntity<PaginatedQueryList<RootEntity>> {
        val result = service.getRecordOf(userId)
        return if (result.isNotEmpty())
            ResponseEntity(result, HttpStatus.OK)
        else
            ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @GetMapping(pathToTag)
    fun getRecordByUserIdAndTag(
        @PathVariable tag: String, @PathVariable userId: String
    ): ResponseEntity<List<RootEntity>> {
        return ResponseEntity(
            service.getRecordsByTagOf(userId, tag),
            HttpStatus.OK
        )
    }

    @PostMapping(pathToUserId)
    fun postRecordForUser(
        @PathVariable userId: String, @RequestBody body: RootEntity
    ): ResponseEntity<RootEntity> {
        return ResponseEntity(
            service.postRecord(record = body),
            HttpStatus.CREATED
        )
    }

    @PutMapping(pathToUserId)
    fun putRecordForUser(
        @PathVariable userId: String, @RequestBody body: RootEntity
    ): ResponseEntity<RootEntity> {
        return ResponseEntity(
            service.postRecord(record = body),
            HttpStatus.CREATED
        )
    }
}