package com.mindque.userdata.record

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

private const val pathToUserId = "/record/{userId}"
private const val pathToTag = "record/{userId}/tag/{tag}"

@RestController
class RecordController {
    @Autowired
    lateinit var service: RecordService

    @PostMapping(pathToUserId)
    fun postRecordForUser(@PathVariable userId: String) {
    }

    @GetMapping(pathToUserId)
    fun getRecordByUserId(@PathVariable userId: String) {
    }

    @PutMapping(pathToUserId)
    fun putRecordForUser(@PathVariable userId: String) {
    }

    @GetMapping(pathToTag)
    fun getRecordByUserIdAndTag(@PathVariable tag: String, @PathVariable userId: String) {

    }

}