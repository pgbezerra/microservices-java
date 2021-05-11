package com.pgbezerra.productservice.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.pgbezerra.productservice.http.data.request.ProductPersistDto;
import com.pgbezerra.productservice.model.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface ProductController {

    @Operation(summary = "Return a product that's corresponding with informed id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = "{\n" +
                                            "    \"id\": 1,\n" +
                                            "    \"description\": \"PC\"\n" +
                                            "}"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                value = "{\n" +
                                        "    \"errorCode\": \"X_100\",\n" +
                                        "    \"message\": \"Product with id 100 not found\",\n" +
                                        "    \"documentation\": \"/documentation\",\n" +
                                        "    \"path\": \"/products/100\",\n" +
                                        "    \"timestamp\": \"2021-05-10T22:29:07.032074Z\"\n" +
                                        "}"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = "{\n" +
                                            "    \"errorCode\": \"X_200\",\n" +
                                            "    \"message\": \"Invalid argument\",\n" +
                                            "    \"documentation\": \"/documentation\",\n" +
                                            "    \"path\": \"/products/100a\",\n" +
                                            "    \"timestamp\": \"2021-05-10T22:33:15.456241Z\"\n" +
                                            "}"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = "{\n" +
                                            "    \"errorCode\": \"X_300\",\n" +
                                            "    \"message\": \"Error\",\n" +
                                            "    \"documentation\": \"/documentation\",\n" +
                                            "    \"path\": \"/products/100\",\n" +
                                            "    \"timestamp\": \"2021-05-10T22:36:08.029220Z\"\n" +
                                            "}"
                            )
                    )
            )
    })
    @GetMapping(value = "/{id}")
    Product findById(@PathVariable(value = "id") Long id);

    @Operation(summary = "Create a new product")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = "{\n" +
                                            "    \"id\": 10,\n" +
                                            "    \"description\": \"Monitor\"\n" +
                                            "}"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = "{\n" +
                                            "    \"errorCode\": \"X_201\",\n" +
                                            "    \"message\": \"Invalid argument\",\n" +
                                            "    \"documentation\": \"/documentation\",\n" +
                                            "    \"path\": \"/products\",\n" +
                                            "    \"timestamp\": \"2021-05-10T22:31:52.843234Z\",\n" +
                                            "    \"errors\": [\n" +
                                            "        {\n" +
                                            "            \"field\": \"description\",\n" +
                                            "            \"message\": \"Product description cannot be empty or null\"\n" +
                                            "        }\n" +
                                            "    ]\n" +
                                            "}"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = "{\n" +
                                            "    \"errorCode\": \"X_300\",\n" +
                                            "    \"message\": \"Error\",\n" +
                                            "    \"documentation\": \"/documentation\",\n" +
                                            "    \"path\": \"/products/100\",\n" +
                                            "    \"timestamp\": \"2021-05-10T22:36:08.029220Z\"\n" +
                                            "}"
                            )
                    )
            )
    })
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    Product insert(@RequestBody @Valid ProductPersistDto productPersistDto);

    @Operation(summary = "Update a existing product partiality", requestBody =
        @RequestBody(required = true, content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                        value = "\n[" +
                                "{ \"op\": \"test\", \"path\": \"/description\", \"value\": \"Table\" },\n" +
                                "{ \"op\": \"replace\", \"path\": \"/description\", \"value\": \"Case\" }\n" +
                                "]\n"
                )
        )))
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = "{\n" +
                                            "    \"id\": 10,\n" +
                                            "    \"description\": \"Gamer Monitor\"\n" +
                                            "}"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = "{\n" +
                                            "    \"errorCode\": \"X_200\",\n" +
                                            "    \"message\": \"Invalid argument\",\n" +
                                            "    \"documentation\": \"http://localhost:8080/documentation\",\n" +
                                            "    \"path\": \"/products/10111aa\",\n" +
                                            "    \"timestamp\": \"2021-05-11T19:07:43.644252Z\"\n" +
                                            "}"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = "{\n" +
                                            "    \"errorCode\": \"X_202\",\n" +
                                            "    \"message\": \"value differs from expectations\",\n" +
                                            "    \"documentation\": \"http://localhost:8080/documentation\",\n" +
                                            "    \"path\": \"/products/10\",\n" +
                                            "    \"timestamp\": \"2021-05-11T19:06:11.923462Z\"\n" +
                                            "}"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = "{\n" +
                                            "    \"errorCode\": \"X_100\",\n" +
                                            "    \"message\": \"Product with id 10111 not found\",\n" +
                                            "    \"documentation\": \"http://localhost:8080/documentation\",\n" +
                                            "    \"path\": \"/products/10111\",\n" +
                                            "    \"timestamp\": \"2021-05-11T19:07:31.111325Z\"\n" +
                                            "}"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = "{\n" +
                                            "    \"errorCode\": \"X_300\",\n" +
                                            "    \"message\": \"Error\",\n" +
                                            "    \"documentation\": \"/documentation\",\n" +
                                            "    \"path\": \"/products/100\",\n" +
                                            "    \"timestamp\": \"2021-05-10T22:36:08.029220Z\"\n" +
                                            "}"
                            )
                    )
            )
    })
    @PatchMapping(value = "{id}")
    Product update(@PathVariable(value = "id") Long id, @RequestBody JsonPatch patch) throws JsonPatchException, JsonProcessingException;

    @Operation(summary = "Delete a existing product")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    content = @Content(
                            mediaType = "application/json"
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = "{\n" +
                                            "    \"errorCode\": \"X_200\",\n" +
                                            "    \"message\": \"Invalid argument\",\n" +
                                            "    \"documentation\": \"http://localhost:8080/documentation\",\n" +
                                            "    \"path\": \"/products/10111aa\",\n" +
                                            "    \"timestamp\": \"2021-05-11T19:07:43.644252Z\"\n" +
                                            "}"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = "{\n" +
                                            "    \"errorCode\": \"X_100\",\n" +
                                            "    \"message\": \"Product with id 10111 not found\",\n" +
                                            "    \"documentation\": \"http://localhost:8080/documentation\",\n" +
                                            "    \"path\": \"/products/10111\",\n" +
                                            "    \"timestamp\": \"2021-05-11T19:07:31.111325Z\"\n" +
                                            "}"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = "{\n" +
                                            "    \"errorCode\": \"X_300\",\n" +
                                            "    \"message\": \"Error\",\n" +
                                            "    \"documentation\": \"/documentation\",\n" +
                                            "    \"path\": \"/products/100\",\n" +
                                            "    \"timestamp\": \"2021-05-10T22:36:08.029220Z\"\n" +
                                            "}"
                            )
                    )
            )
    })
    @DeleteMapping(value = "{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable(value = "id") Long id);

    @Operation(summary = "Update a existing product")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = "{\n" +
                                            "    \"id\": 10,\n" +
                                            "    \"description\": \"Monitor\"\n" +
                                            "}"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = "{\n" +
                                            "    \"errorCode\": \"X_201\",\n" +
                                            "    \"message\": \"Invalid argument\",\n" +
                                            "    \"documentation\": \"/documentation\",\n" +
                                            "    \"path\": \"/products\",\n" +
                                            "    \"timestamp\": \"2021-05-10T22:31:52.843234Z\",\n" +
                                            "    \"errors\": [\n" +
                                            "        {\n" +
                                            "            \"field\": \"description\",\n" +
                                            "            \"message\": \"Product description cannot be empty or null\"\n" +
                                            "        }\n" +
                                            "    ]\n" +
                                            "}"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = "{\n" +
                                            "    \"errorCode\": \"X_300\",\n" +
                                            "    \"message\": \"Error\",\n" +
                                            "    \"documentation\": \"/documentation\",\n" +
                                            "    \"path\": \"/products/100\",\n" +
                                            "    \"timestamp\": \"2021-05-10T22:36:08.029220Z\"\n" +
                                            "}"
                            )
                    )
            )
    })
    @PutMapping(value = "{id}")
    Product updateAll(@PathVariable(value = "id") Long id, @RequestBody ProductPersistDto productPersistDto);

}
