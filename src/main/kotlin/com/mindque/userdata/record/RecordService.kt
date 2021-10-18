package com.mindque.userdata.record

import com.mindque.userdata.core.DynamoDbRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RecordService {

    @Autowired
    private lateinit var repo: DynamoDbRepo


}