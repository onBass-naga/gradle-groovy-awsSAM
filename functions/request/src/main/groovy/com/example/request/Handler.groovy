package com.example.request

import com.amazonaws.services.lambda.runtime.LambdaLogger

class Handler extends AbstractRequestHandler {

    @Override
    def handle(request, context) {
        LambdaLogger logger = context.getLogger()
        logger.log("com.example.request.Handler#handle was called\n")

        return request
    }
}
