package com.example.error

import com.amazonaws.services.lambda.runtime.LambdaLogger
import com.example.request.AbstractRequestHandler

class Handler extends AbstractRequestHandler {

    @Override
    def handle(request, context) {
        LambdaLogger logger = context.getLogger()
        logger.log("com.example.error.Handler#handle was called\n")

        throw new RuntimeException("予は働きとうないのじゃ")
    }
}
