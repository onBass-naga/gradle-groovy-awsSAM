package com.example.request

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.LambdaLogger
import com.amazonaws.services.lambda.runtime.RequestStreamHandler
import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import groovy.transform.CompileStatic

import java.nio.charset.StandardCharsets

@CompileStatic
abstract class AbstractRequestHandler implements RequestStreamHandler {

    @Override
    void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {

        LambdaLogger logger = context.getLogger()
        logger.log("AbstractRequestHandler#handleRequest was called\n")

        def parser = new JsonSlurper()
        OutputStreamWriter writer = new OutputStreamWriter(output, StandardCharsets.UTF_8.toString())

        try {
            def request = parser.parse(input, StandardCharsets.UTF_8.toString())

            def result = handle(request, context)
            def responseBody = new JsonBuilder()
            responseBody(result)

            def response = new JsonBuilder()
            response([
                    statusCode: HttpStatus.OK.value(),
                    headers: ["x-custom-response-header": "my custom response header value"],
                    body: responseBody.toString()
            ])

            logger.log("row response:\n${response.toString()}\n")
            response.writeTo(writer)

        } catch (Exception e) {

            StringWriter sw = new StringWriter()
            e.printStackTrace(new PrintWriter(sw))
            logger.log("stack trace:\n${sw.toString()}\n")

            def errorBody = new JsonBuilder()
            errorBody([message: "Something went wrong..."])

            def errorResponse = new JsonBuilder()
            errorResponse([
                    statusCode: HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    headers: ["x-custom-response-header": "my custom response header value"],
                    body: errorBody.toString()
            ])
            errorResponse.writeTo(writer)

        } finally {
            writer.close()
        }
    }

    abstract Object handle(request, context)
}
